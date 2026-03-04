package com.chinaex123.hammers_galore.server;

import com.chinaex123.hammers_galore.config.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

public class PickaxeItems extends PickaxeItem {

    public PickaxeItems(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public static Properties createProperties(Tier tier, float attackDamage, float attackSpeed) {
        return new Properties()
                .attributes(PickaxeItem.createAttributes(tier, attackDamage, attackSpeed));
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        // 获取工具等级名称
        String tierName = getTierNameFromStack(stack);

        // 检查是否需要潜行（根据当前锤子的配置）
        boolean shouldMineArea = !ServerConfig.requireSneak(tierName) || entity.isCrouching();

        if (!level.isClientSide && shouldMineArea) {
            // 只在服务端且满足条件时触发范围挖掘
            mineArea(stack, (ServerLevel) level, state, pos, entity, tierName);
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }

    private void mineArea(ItemStack stack, ServerLevel level, BlockState centerState, BlockPos centerPos, LivingEntity entity, String tierName) {
        // 获取玩家面向方向
        Direction direction = entity.getDirection();

        // 从配置获取挖掘范围 - 根据工具等级
        int radius = ServerConfig.getMiningRadius(tierName);

        // 如果半径为 0，表示禁用范围挖掘
        if (radius <= 0) return;

        // 获取耐久消耗（根据当前锤子的配置）
        int durabilityCost = ServerConfig.getDurabilityCost(tierName);

        // 检查是否启用饱食度消耗
        boolean enableHungerCost = ServerConfig.enableHungerCost(tierName);

        // 计算挖掘区域的偏移量
        List<BlockPos> areaPositions = getAreaPositions(centerPos, direction, radius);

        // 统计实际挖掘的方块数量（用于计算饱食度消耗）
        int blocksMined = 0;

        for (BlockPos pos : areaPositions) {
            // 跳过中心方块（已经由原方法处理）
            if (pos.equals(centerPos)) continue;

            BlockState targetState = level.getBlockState(pos);

            // 检查是否可以挖掘（防止破坏不可破坏的方块）
            if (!canHammerMine(targetState)) continue;

            // 检查工具耐久
            if (stack.getDamageValue() >= stack.getMaxDamage() - durabilityCost) continue;

            // 检查挖掘等级
            if (!isCorrectToolForDrops(stack, targetState)) continue;

            // 获取掉落物
            List<ItemStack> drops = Block.getDrops(targetState, level, pos, level.getBlockEntity(pos), entity, stack);

            // 移除原方块
            level.destroyBlock(pos, false, entity);

            // 掉落物品（每个方块正常掉落战利品表的数量）
            for (ItemStack drop : drops) {
                // 直接掉落，不乘以倍数
                ItemEntity itemEntity = new ItemEntity(level,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        drop.copy());
                level.addFreshEntity(itemEntity);
            }

            // 消耗耐久（根据配置的耐久消耗）
            stack.hurtAndBreak(durabilityCost, entity,
                    LivingEntity.getSlotForHand(entity.getUsedItemHand()));

            // 计数挖掘的方块
            blocksMined++;
        }

        // 如果启用了饱食度消耗，并且挖掘了方块
        if (enableHungerCost && blocksMined > 0 && entity instanceof Player player) {
            // 每挖掘一个额外方块消耗 0.5 点饥饿值
            player.causeFoodExhaustion(blocksMined * 0.5f);
        }
    }

    /**
     * 从 ItemStack 获取工具等级名称
     */
    private String getTierNameFromStack(ItemStack stack) {
        return stack.getItem().getDescriptionId()
                .replace("item.hammers_galore.", "");
    }

    private List<BlockPos> getAreaPositions(BlockPos center, Direction facing, int radius) {
        List<BlockPos> positions = new ArrayList<>();

        // 根据面向方向确定挖掘平面的两个轴
        Direction.Axis axis1, axis2;

        if (facing.getAxis() == Direction.Axis.Y) {
            // 如果面向上下，使用 X 和 Z 轴
            axis1 = Direction.Axis.X;
            axis2 = Direction.Axis.Z;
        } else {
            // 如果面向水平方向，使用垂直方向和另一个水平方向
            axis1 = Direction.UP.getAxis(); // Y 轴
            axis2 = facing.getClockWise().getAxis(); // 侧向轴
        }

        // 生成范围区域
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                BlockPos offset = center;

                // 根据轴向应用偏移
                if (axis1 == Direction.Axis.X) offset = offset.offset(i, 0, 0);
                else if (axis1 == Direction.Axis.Y) offset = offset.offset(0, i, 0);
                else if (axis1 == Direction.Axis.Z) offset = offset.offset(0, 0, i);

                if (axis2 == Direction.Axis.X) offset = offset.offset(j, 0, 0);
                else if (axis2 == Direction.Axis.Y) offset = offset.offset(0, j, 0);
                else if (axis2 == Direction.Axis.Z) offset = offset.offset(0, 0, j);

                positions.add(offset);
            }
        }

        return positions;
    }

    private boolean canHammerMine(BlockState state) {
        // 检查方块是否可以被锤子挖掘
        // 防止破坏基岩、命令方块等
        // 但允许挖掘沙子、沙砾等受重力影响的方块
        if (state.is(Tags.Blocks.RELOCATION_NOT_SUPPORTED)) {
            return false;
        }
        return !state.is(Tags.Blocks.ENDERMAN_PLACE_ON_BLACKLIST);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        // 这样包括沙子、沙砾、混凝土粉末等受重力影响的方块
        if (state.is(Tags.Blocks.RELOCATION_NOT_SUPPORTED)) {
            return false;
        }
        return !state.is(Tags.Blocks.ENDERMAN_PLACE_ON_BLACKLIST);
        // 允许挖掘所有其他方块
    }
}
