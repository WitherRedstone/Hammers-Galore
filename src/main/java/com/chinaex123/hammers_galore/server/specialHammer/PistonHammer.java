package com.chinaex123.hammers_galore.server.specialHammer;

import com.chinaex123.hammers_galore.config.ServerConfig;
import com.chinaex123.hammers_galore.server.HammerMiningHelper;
import com.chinaex123.hammers_galore.server.PickaxeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
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

public class PistonHammer extends PickaxeItems {

    private static final ResourceLocation KNOCKBACK_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("hammers_galore", "piston_hammer_knockback");

    public PistonHammer(Tier tier, Properties properties) {
        super(tier, properties);
    }

    /**
     * 重写方块的挖掘方法，实现活塞锤的范围挖掘功能
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

        // 仅在服务端且满足条件时执行范围挖掘
        if (!level.isClientSide && shouldMineArea) {
            mineArea(stack, (ServerLevel) level, state, pos, entity, tierName);
        }

        // 调用父类的挖掘方法处理基础逻辑
        return super.mineBlock(stack, level, state, pos, entity);
    }

    /**
     * 重写攻击敌人方法，在攻击时应用强力的击退效果
     *
     * @param stack 玩家手持的物品堆栈
     * @param target 被攻击的目标生物实体
     * @param attacker 发起攻击的生物实体
     * @return 如果攻击成功返回 true，否则返回 false
     */
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 应用击退效果
        applyKnockback(target, attacker);

        // 调用父类的攻击方法处理基础逻辑
        return super.hurtEnemy(stack, target, attacker);
    }

    /**
     * 为被攻击的目标应用击退效果
     *
     * @param target 被攻击的目标生物实体
     * @param attacker 发起攻击的生物实体
     */
    private void applyKnockback(LivingEntity target, LivingEntity attacker) {
        // 从配置获取击退强度
        double knockbackStrength = ServerConfig.getPistonKnockbackStrength();

        // 计算击退方向
        double dx = target.getX() - attacker.getX();
        double dz = target.getZ() - attacker.getZ();

        // 归一化并应用击退
        double distance = Math.sqrt(dx * dx + dz * dz);
        if (distance > 0) {
            dx /= distance;
            dz /= distance;

            // 应用击退
            target.setDeltaMovement(
                    dx * knockbackStrength,
                    0.5, // 向上击飞
                    dz * knockbackStrength
            );
            target.hasImpulse = true;
        }
    }

    /**
     * 在指定区域内进行范围挖掘，挖掘以中心位置为基准的方形区域
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
            // 跳过中心方块（由父类方法处理）
            if (pos.equals(centerPos)) continue;

            // 获取目标位置的方块状态
            BlockState targetState = level.getBlockState(pos);

            // 检查锤子是否可以挖掘此方块（使用工具类）
            if (!HammerMiningHelper.canHammerMine(targetState)) continue;

            // 检查工具耐久是否足够
            if (stack.getDamageValue() >= stack.getMaxDamage() - durabilityCost) continue;

            // 检查锤子是否能从此方块获取掉落物
            if (!isCorrectToolForDrops(stack, targetState)) continue;

            // 获取方块的掉落物
            List<ItemStack> drops = Block.getDrops(targetState, level, pos, level.getBlockEntity(pos), entity, stack);

            // 移除原方块（不生成掉落实体，手动生成）
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
}
