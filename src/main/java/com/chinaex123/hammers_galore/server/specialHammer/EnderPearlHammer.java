package com.chinaex123.hammers_galore.server.specialHammer;

import com.chinaex123.hammers_galore.config.ServerConfig;
import com.chinaex123.hammers_galore.server.HammerMiningHelper;
import com.chinaex123.hammers_galore.server.PickaxeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EnderPearlHammer extends PickaxeItems {

    public EnderPearlHammer(Tier tier, Properties properties) {
        super(tier, properties);
    }

    /**
     * 重写方块的挖掘方法，实现末影锤的范围挖掘和自动收集掉落物功能
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
        // 从物品堆栈获取锤子名称（使用工具类）
        String tierName = HammerMiningHelper.getTierNameFromStack(stack);

        // 检查是否应该进行范围挖掘：不需要潜行或者玩家正在潜行
        boolean shouldMineArea = !ServerConfig.requireSneak(tierName) || entity.isCrouching();

        // 仅在服务端且满足条件时执行范围挖掘（包括中心方块）
        if (!level.isClientSide && shouldMineArea) {
            mineArea(stack, (ServerLevel) level, state, pos, entity, tierName);
        } else {
            // 如果需要潜行但玩家没有潜行，只挖掘中心方块
            if (!shouldMineArea) {
                mineCenterBlock(stack, level, state, pos, entity);
            }
        }

        // 挖掘成功
        return true;
    }

    /**
     * 挖掘单个方块（中心方块），并将掉落物直接收入玩家背包
     *
     * @param stack 玩家手持的物品堆栈
     * @param level 当前世界等级
     * @param state 被挖掘方块的状态
     * @param pos 被挖掘方块的位置
     * @param entity 进行挖掘的生物实体
     */
    private void mineCenterBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        // 只有当挖掘实体是玩家时才处理
        if (entity instanceof Player player) {
            // 获取方块的掉落物列表
            List<ItemStack> drops = Block.getDrops(state, (ServerLevel) level, pos, level.getBlockEntity(pos), entity, stack);

            // 移除原方块（不生成掉落实体，手动处理）
            level.destroyBlock(pos, false, entity);

            // 尝试将掉落物添加到玩家背包
            for (ItemStack drop : drops) {
                // 尝试将物品放入玩家背包
                boolean added = player.getInventory().add(drop);

                // 如果背包已满无法添加，则生成掉落物实体
                if (!added && !drop.isEmpty()) {
                    ItemEntity itemEntity = new ItemEntity(level,
                            pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                            drop.copy());
                    level.addFreshEntity(itemEntity);
                }
            }

            // 消耗工具耐久
            stack.hurtAndBreak(1, entity,
                    LivingEntity.getSlotForHand(entity.getUsedItemHand()));
        }
    }

    /**
     * 在指定区域内进行范围挖掘，并将所有掉落物直接收入玩家背包
     *
     * @param stack 玩家手持的物品堆栈
     * @param level 服务端世界等级
     * @param centerState 中心方块的方块状态
     * @param centerPos 中心方块的位置坐标
     * @param entity 进行挖掘的生物实体
     * @param tierName 锤子的注册名称（用于获取配置）
     */
    private void mineArea(ItemStack stack, ServerLevel level, BlockState centerState, BlockPos centerPos, LivingEntity entity, String tierName) {
        // 获取玩家面向方向
        Direction direction = entity.getDirection();

        // 从配置获取挖掘半径
        int radius = ServerConfig.getMiningRadius(tierName);

        // 如果半径为 0 或负数，表示禁用范围挖掘，直接返回
        if (radius <= 0) return;

        // 从配置获取耐久消耗和饱食度消耗设置
        int durabilityCost = ServerConfig.getDurabilityCost(tierName);
        boolean enableHungerCost = ServerConfig.enableHungerCost(tierName);

        // 计算挖掘区域内的所有方块位置（使用工具类）
        List<BlockPos> areaPositions = HammerMiningHelper.getAreaPositions(centerPos, direction, radius);

        // 统计实际挖掘的方块数量（用于计算饱食度消耗）
        int blocksMined = 0;

        // 遍历挖掘区域内的所有位置
        for (BlockPos pos : areaPositions) {
            // 获取目标位置的方块状态（包括中心方块一起处理）
            BlockState targetState = level.getBlockState(pos);

            // 检查锤子是否可以挖掘此方块（使用工具类）
            if (!HammerMiningHelper.canHammerMine(targetState)) continue;

            // 检查工具耐久是否足够
            if (stack.getDamageValue() >= stack.getMaxDamage() - durabilityCost) continue;

            // 检查锤子是否能从此方块获取掉落物
            if (!isCorrectToolForDrops(stack, targetState)) continue;

            // 获取方块的掉落物
            List<ItemStack> drops = Block.getDrops(targetState, level, pos, level.getBlockEntity(pos), entity, stack);

            // 移除原方块（不生成掉落实体，手动处理）
            level.destroyBlock(pos, false, entity);

            // 将掉落物放入玩家背包或生成掉落实体
            if (entity instanceof Player player) {
                // 如果是玩家，尝试将物品放入背包
                for (ItemStack drop : drops) {
                    // 尝试将物品放入玩家背包
                    boolean added = player.getInventory().add(drop);

                    // 如果背包已满无法添加，则生成掉落物实体
                    if (!added && !drop.isEmpty()) {
                        ItemEntity itemEntity = new ItemEntity(level,
                                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                                drop.copy());
                        level.addFreshEntity(itemEntity);
                    }
                }
            } else {
                // 如果不是玩家挖掘，正常生成掉落物实体
                for (ItemStack drop : drops) {
                    ItemEntity itemEntity = new ItemEntity(level,
                            pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                            drop.copy());
                    level.addFreshEntity(itemEntity);
                }
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
}
