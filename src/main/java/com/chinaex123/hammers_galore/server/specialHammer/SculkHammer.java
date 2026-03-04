package com.chinaex123.hammers_galore.server.specialHammer;

import com.chinaex123.hammers_galore.config.ServerConfig;
import com.chinaex123.hammers_galore.server.PickaxeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

public class SculkHammer extends PickaxeItems {

    public SculkHammer(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        String tierName = getTierNameFromStack(stack);
        boolean shouldMineArea = !ServerConfig.requireSneak(tierName) || entity.isCrouching();

        if (!level.isClientSide && shouldMineArea) {
            // 处理范围挖掘（包括中心方块）
            mineArea(stack, (ServerLevel) level, state, pos, entity, tierName);
        } else {
            // 如果不需要潜行或没有潜行，只挖掘中心方块
            if (!shouldMineArea) {
                mineSingleBlock(stack, level, state, pos, entity);
            }
        }

        return true;
    }

    /**
     * 挖掘单个方块
     */
    private void mineSingleBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (entity instanceof Player player) {
            // 检查是否可以挖掘
            if (!canHammerMine(state)) return;
            if (!isCorrectToolForDrops(stack, state)) return;

            // 获取掉落物并计算经验
            List<ItemStack> drops = Block.getDrops(state, (ServerLevel) level, pos, level.getBlockEntity(pos), entity, stack);

            // 移除原方块
            level.destroyBlock(pos, false, entity);

            // 将掉落物转换为经验
            int totalXP = calculateXPFromDrops(drops, state);
            if (totalXP > 0) {
                spawnExperienceOrb(level, pos, totalXP);
            }

            // 消耗耐久
            stack.hurtAndBreak(1, entity,
                    LivingEntity.getSlotForHand(entity.getUsedItemHand()));
        }
    }

    private void mineArea(ItemStack stack, ServerLevel level, BlockState centerState, BlockPos centerPos, LivingEntity entity, String tierName) {
        Direction direction = entity.getDirection();
        int radius = ServerConfig.getMiningRadius(tierName);

        if (radius <= 0) return;

        int durabilityCost = ServerConfig.getDurabilityCost(tierName);
        boolean enableHungerCost = ServerConfig.enableHungerCost(tierName);

        List<BlockPos> areaPositions = getAreaPositions(centerPos, direction, radius);
        int blocksMined = 0;
        int totalXP = 0;

        for (BlockPos pos : areaPositions) {
            // 不再跳过中心方块，一起处理
            BlockState targetState = level.getBlockState(pos);

            if (!canHammerMine(targetState)) continue;

            if (stack.getDamageValue() >= stack.getMaxDamage() - durabilityCost) continue;

            if (!isCorrectToolForDrops(stack, targetState)) continue;

            // 获取掉落物并计算经验
            List<ItemStack> drops = Block.getDrops(targetState, level, pos, level.getBlockEntity(pos), entity, stack);

            // 移除原方块（不生成掉落实体）
            level.destroyBlock(pos, false, entity);

            // 累加经验值
            totalXP += calculateXPFromDrops(drops, targetState);

            // 消耗耐久
            stack.hurtAndBreak(durabilityCost, entity,
                    LivingEntity.getSlotForHand(entity.getUsedItemHand()));

            blocksMined++;
        }

        // 生成经验球
        if (totalXP > 0) {
            spawnExperienceOrb(level, centerPos, totalXP);
        }

        // 饱食度消耗
        if (enableHungerCost && blocksMined > 0 && entity instanceof Player player) {
            player.causeFoodExhaustion(blocksMined * 0.5f);
        }
    }

    /**
     * 根据掉落物计算经验值
     */
    private int calculateXPFromDrops(List<ItemStack> drops, BlockState state) {
        int totalXP = 0;

        // 从配置获取经验范围
        int minXp = ServerConfig.getSculkBaseXPMin();
        int maxXp = ServerConfig.getSculkBaseXPMax();

        // 确保最大值不小于最小值
        if (maxXp < minXp) {
            maxXp = minXp;
        }

        // 根据方块类型和掉落物数量计算经验
        for (ItemStack drop : drops) {
            if (!drop.isEmpty()) {
                // 基础经验：minXp-maxXp XP（可配置，默认 1-3）
                int xpPerItem = minXp + (int)(Math.random() * (maxXp - minXp + 1));
                totalXP += xpPerItem * drop.getCount();
            }
        }

        // 如果是矿石类方块，给予更多经验（可配置倍数，默认 2 倍）
        String blockName = state.getBlock().getDescriptionId();
        if (blockName.contains("ore")) {
            totalXP *= ServerConfig.getSculkOreXPMultiplier();
        }

        return totalXP;
    }

    /**
     * 在指定位置生成经验球
     */
    private void spawnExperienceOrb(Level level, BlockPos pos, int xp) {
        if (xp <= 0) return;

        // 在方块中心生成经验球
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;

        // Minecraft 的经验球会自动合并，所以可以直接生成
        while (xp > 0) {
            // 每次最多生成一个价值 100 XP 的球，避免单个球价值过高
            int orbXp = Math.min(xp, 100);
            ExperienceOrb.award((ServerLevel) level, new Vec3(x, y, z), orbXp);
            xp -= orbXp;
        }
    }

    private String getTierNameFromStack(ItemStack stack) {
        return stack.getItem().getDescriptionId()
                .replace("item.hammers_galore.", "");
    }

    private List<BlockPos> getAreaPositions(BlockPos center, Direction facing, int radius) {
        List<BlockPos> positions = new ArrayList<>();

        Direction.Axis axis1, axis2;

        if (facing.getAxis() == Direction.Axis.Y) {
            axis1 = Direction.Axis.X;
            axis2 = Direction.Axis.Z;
        } else {
            axis1 = Direction.UP.getAxis();
            axis2 = facing.getClockWise().getAxis();
        }

        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                BlockPos offset = center;

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
        return !state.is(Tags.Blocks.RELOCATION_NOT_SUPPORTED) &&
                !state.is(Tags.Blocks.ENDERMAN_PLACE_ON_BLACKLIST);
    }
}
