package com.chinaex123.hammers_galore.server.specialHammer;

import com.chinaex123.hammers_galore.config.ServerConfig;
import com.chinaex123.hammers_galore.server.HammerMiningHelper;
import com.chinaex123.hammers_galore.server.PickaxeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HeartOfTheSeaHammer extends PickaxeItems {

    private static final ResourceLocation WATER_MINING_SPEED_ID =
            ResourceLocation.fromNamespaceAndPath("hammers_galore", "heart_of_the_sea_water_speed");

    public HeartOfTheSeaHammer(Tier tier, Properties properties) {
        super(tier, properties);
    }

    /**
     * 重写方块的挖掘方法，实现海洋之心锤的范围挖掘功能
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
     * 玩家 Tick 事件处理方法，在玩家手持海洋之心锤且在水中时应用挖掘速度加成
     *
     * @param player 当前玩家实体
     */
    public static void onPlayerTick(Player player) {
        // 获取玩家主手的物品
        ItemStack mainHandItem = player.getMainHandItem();

        // 检查主手是否持有海洋之心锤
        boolean hasHammerInMainHand = mainHandItem.getItem() instanceof HeartOfTheSeaHammer;

        // 如果玩家主手持有海洋之心锤
        if (hasHammerInMainHand) {
            // 获取玩家的挖掘速度属性
            AttributeInstance speedAttr = player.getAttribute(Attributes.BLOCK_BREAK_SPEED);
            if (speedAttr != null) {
                // 检查玩家是否在水中或气泡中
                if (player.isInWaterOrBubble()) {
                    // 在水里：应用 5 倍速度加成（抵消水下惩罚）
                    if (!speedAttr.hasModifier(WATER_MINING_SPEED_ID)) {
                        speedAttr.addTransientModifier(new AttributeModifier(
                                WATER_MINING_SPEED_ID,
                                4.0,  // +400% = 总共 5 倍速度
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        ));
                    }
                } else {
                    // 不在水里：移除加成
                    speedAttr.removeModifier(WATER_MINING_SPEED_ID);
                }
            }
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
