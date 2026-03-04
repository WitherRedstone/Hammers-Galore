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
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PickaxeItems extends PickaxeItem {

    public PickaxeItems(Tier tier, Properties properties) {
        super(tier, properties);
    }

    /**
     * 创建锤子物品的属性配置
     *
     * @param tier 工具等级（决定挖掘速度和耐久度）
     * @param attackDamage 基础攻击力值
     * @param attackSpeed 攻击速度值
     * @return 配置好属性的 Properties 对象
     */
    public static Properties createProperties(Tier tier, float attackDamage, float attackSpeed) {
        // 设置物品属性，包括攻击力和攻击速度
        return new Properties()
                .attributes(PickaxeItem.createAttributes(tier, attackDamage, attackSpeed));
    }

    /**
     * 重写方块挖掘方法，实现锤子的范围挖掘功能
     * 根据玩家视角自动调整挖掘平面方向
     *
     * @param stack 玩家手持的物品堆栈
     * @param level 当前世界等级
     * @param state 被挖掘方块的状态
     * @param pos 被挖掘方块的位置
     * @param entity 进行挖掘的生物实体
     * @return 如果挖掘成功返回 true，否则返回 false
     */
    @Override
    public boolean mineBlock(@NotNull ItemStack stack, Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity entity) {
        // 获取工具等级名称（使用工具类）
        String tierName = HammerMiningHelper.getTierNameFromStack(stack);

        // 检查是否需要潜行（根据当前锤子的配置）
        boolean shouldMineArea = !ServerConfig.requireSneak(tierName) || entity.isCrouching();

        // 只在服务端且满足条件时触发范围挖掘
        if (!level.isClientSide && shouldMineArea) {
            mineArea(stack, (ServerLevel) level, state, pos, entity, tierName);
        }

        // 调用父类的挖掘方法处理基础逻辑
        return super.mineBlock(stack, level, state, pos, entity);
    }

    /**
     * 在指定区域内进行范围挖掘，根据玩家视角自动调整挖掘平面
     *
     * @param stack 玩家手持的物品堆栈
     * @param level 服务端世界等级
     * @param centerState 中心方块的方块状态
     * @param centerPos 中心方块的位置坐标
     * @param entity 进行挖掘的生物实体
     * @param tierName 锤子的注册名称（用于获取配置）
     */
    private void mineArea(ItemStack stack, ServerLevel level, BlockState centerState, BlockPos centerPos, LivingEntity entity, String tierName) {
        // 根据玩家视角计算挖掘方向（使用工具类）
        Direction direction = HammerMiningHelper.getFacingFromBlock(centerPos, entity);

        // 从配置获取挖掘范围 - 根据工具等级
        int radius = ServerConfig.getMiningRadius(tierName);

        // 如果半径为 0，表示禁用范围挖掘
        if (radius <= 0) return;

        // 获取耐久消耗（根据当前锤子的配置）
        int durabilityCost = ServerConfig.getDurabilityCost(tierName);

        // 检查是否启用饱食度消耗
        boolean enableHungerCost = ServerConfig.enableHungerCost(tierName);

        // 计算挖掘区域的偏移量（使用工具类）
        List<BlockPos> areaPositions = HammerMiningHelper.getAreaPositions(centerPos, direction, radius);

        // 统计实际挖掘的方块数量（用于计算饱食度消耗）
        int blocksMined = 0;

        // 遍历挖掘区域内的所有位置
        for (BlockPos pos : areaPositions) {
            // 跳过中心方块（已经由原方法处理）
            if (pos.equals(centerPos)) continue;

            // 获取目标位置的方块状态
            BlockState targetState = level.getBlockState(pos);

            // 检查是否可以挖掘（防止破坏不可破坏的方块）（使用工具类）
            if (!HammerMiningHelper.canHammerMine(targetState)) continue;

            // 检查工具耐久
            if (stack.getDamageValue() >= stack.getMaxDamage() - durabilityCost) continue;

            // 检查挖掘等级
            if (!isCorrectToolForDrops(stack, targetState)) continue;

            // 获取方块的掉落物
            List<ItemStack> drops = Block.getDrops(targetState, level, pos, level.getBlockEntity(pos), entity, stack);

            // 移除原方块
            level.destroyBlock(pos, false, entity);

            // 生成掉落物品实体
            for (ItemStack drop : drops) {
                ItemEntity itemEntity = new ItemEntity(level,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        drop.copy());
                level.addFreshEntity(itemEntity);
            }

            // 消耗工具耐久
            stack.hurtAndBreak(durabilityCost, entity,
                    LivingEntity.getSlotForHand(entity.getUsedItemHand()));

            // 增加已挖掘方块计数
            blocksMined++;
        }

        // 如果启用了饱食度消耗且挖掘了方块，对玩家造成饥饿消耗
        if (enableHungerCost && blocksMined > 0 && entity instanceof Player player) {
            player.causeFoodExhaustion(blocksMined * 0.5f);
        }
    }

    /**
     * 检查锤子是否能从指定方块获取掉落物
     * 允许挖掘沙子、沙砾、混凝土粉末等受重力影响的方块
     *
     * @param stack 玩家手持的物品堆栈
     * @param state 要检查的方块状态
     * @return 如果锤子能从此方块获取掉落物返回 true，否则返回 false
     */
    @Override
    public boolean isCorrectToolForDrops(@NotNull ItemStack stack, BlockState state) {
        // 检查方块是否不可移动（如基岩、命令方块等）
        if (state.is(Tags.Blocks.RELOCATION_NOT_SUPPORTED)) {
            return false;
        }

        // 检查方块是否在末影人黑名单中
        return !state.is(Tags.Blocks.ENDERMAN_PLACE_ON_BLACKLIST);
    }
}
