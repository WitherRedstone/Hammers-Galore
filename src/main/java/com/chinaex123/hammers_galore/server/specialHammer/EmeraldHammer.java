package com.chinaex123.hammers_galore.server.specialHammer;

import com.chinaex123.hammers_galore.config.ServerConfig;
import com.chinaex123.hammers_galore.server.HammerMiningHelper;
import com.chinaex123.hammers_galore.server.PickaxeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import java.util.Random;

public class EmeraldHammer extends PickaxeItems {

    private static final Random RANDOM = new Random();

    public EmeraldHammer(Tier tier, Properties properties) {
        super(tier, properties);
    }

    /**
     * 重写方块挖掘方法，实现绿宝石锤的范围挖掘和幸运效果
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

        // 检查是否需要潜行（根据当前锤子的配置）
        boolean shouldMineArea = !ServerConfig.requireSneak(tierName) || entity.isCrouching();

        if (!level.isClientSide && shouldMineArea) {
            // 只在服务端且满足条件时触发范围挖掘
            mineArea(stack, (ServerLevel) level, state, pos, entity, tierName);
        }

        // 调用父类的挖掘方法处理基础逻辑
        return super.mineBlock(stack, level, state, pos, entity);
    }

    /**
     * 重写攻击敌人方法，在攻击时也有概率触发幸运效果
     *
     * @param stack 玩家手持的物品堆栈
     * @param target 被攻击的目标生物实体
     * @param attacker 发起攻击的生物实体
     * @return 如果攻击成功返回 true，否则返回 false
     */
    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        // 如果攻击者是玩家，尝试给予幸运效果
        if (attacker instanceof Player player) {
            applyLuckEffect(stack, player);
        }

        // 调用父类的攻击方法处理基础逻辑
        return super.hurtEnemy(stack, target, attacker);
    }

    /**
     * 在指定区域内进行范围挖掘，并根据耐久度给予幸运效果
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

        // 从配置获取挖掘半径
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

        for (BlockPos pos : areaPositions) {
            // 跳过中心方块（已经由原方法处理）
            if (pos.equals(centerPos)) continue;

            BlockState targetState = level.getBlockState(pos);

            // 检查是否可以挖掘（防止破坏不可破坏的方块）（使用工具类）
            if (!HammerMiningHelper.canHammerMine(targetState)) continue;

            // 检查工具耐久
            if (stack.getDamageValue() >= stack.getMaxDamage() - durabilityCost) continue;

            // 检查挖掘等级
            if (!isCorrectToolForDrops(stack, targetState)) continue;

            // 获取掉落物
            List<ItemStack> drops = Block.getDrops(targetState, level, pos, level.getBlockEntity(pos), entity, stack);

            // 移除原方块
            level.destroyBlock(pos, false, entity);

            // 掉落物品
            for (ItemStack drop : drops) {
                ItemEntity itemEntity = new ItemEntity(level,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        drop.copy());
                level.addFreshEntity(itemEntity);
            }

            // 消耗耐久
            stack.hurtAndBreak(durabilityCost, entity,
                    LivingEntity.getSlotForHand(entity.getUsedItemHand()));

            // 计数挖掘的方块
            blocksMined++;
        }

        // 如果启用了饱食度消耗，并且挖掘了方块
        if (enableHungerCost && blocksMined > 0 && entity instanceof Player player) {
            player.causeFoodExhaustion(blocksMined * 0.5f);
        }

        // 尝试给予幸运效果
        if (entity instanceof Player player) {
            applyLuckEffect(stack, player);
        }
    }

    /**
     * 根据工具耐久度给予幸运效果
     * 耐久度越低，触发概率越高，效果等级越高，持续时间越长
     *
     * @param stack 玩家手持的物品堆栈
     * @param player 玩家实体
     */
    private void applyLuckEffect(ItemStack stack, Player player) {
        // 计算耐久度比例（已损失耐久 / 最大耐久），范围 0.0 ~ 1.0
        float damageRatio = (float) stack.getDamageValue() / (float) stack.getMaxDamage();

        // 从配置获取触发概率基数
        double baseTriggerChance = ServerConfig.getEmeraldHammerBaseTriggerChance();

        // 计算实际触发概率：基础概率 + 耐久度比例 * 额外概率
        // 耐久度越低（damageRatio 越大），触发概率越高
        double triggerChance = baseTriggerChance + (damageRatio * (1.0 - baseTriggerChance));

        // 随机判定是否触发
        if (RANDOM.nextDouble() >= triggerChance) {
            return;
        }

        // 根据耐久度计算效果等级（1-3 级）
        // 耐久度低于 30% 时达到最高等级
        int effectLevel;
        if (damageRatio < 0.3) {
            effectLevel = 1;
        } else if (damageRatio < 0.6) {
            effectLevel = 2;
        } else {
            effectLevel = 3;
        }

        // 根据耐久度计算持续时间（tick）
        // 耐久度越低，持续时间越长（最长 600 tick = 30 秒）
        int duration = (int) (200 + damageRatio * 400);

        // 给予幸运效果
        player.addEffect(new MobEffectInstance(
                MobEffects.LUCK, duration, effectLevel - 1, false, true
        ));
    }
}
