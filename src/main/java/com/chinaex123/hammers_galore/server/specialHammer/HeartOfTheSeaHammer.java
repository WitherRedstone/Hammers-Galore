package com.chinaex123.hammers_galore.server.specialHammer;

import com.chinaex123.hammers_galore.config.ServerConfig;
import com.chinaex123.hammers_galore.server.PickaxeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
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

import java.util.ArrayList;
import java.util.List;

public class HeartOfTheSeaHammer extends PickaxeItems {

    private static final ResourceLocation WATER_MINING_SPEED_ID =
            ResourceLocation.fromNamespaceAndPath("hammers_galore", "heart_of_the_sea_water_speed");

    public HeartOfTheSeaHammer(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        String tierName = getTierNameFromStack(stack);
        boolean shouldMineArea = !ServerConfig.requireSneak(tierName) || entity.isCrouching();

        if (!level.isClientSide && shouldMineArea) {
            mineArea(stack, (ServerLevel) level, state, pos, entity, tierName);
        }

        return super.mineBlock(stack, level, state, pos, entity);
    }

    /**
     * 在玩家手持此锤子且在水里时，每 tick 更新挖掘速度
     * 需要在 HammersGalore.java 中注册到 Tick 事件
     */
    public static void onPlayerTick(Player player) {
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offHandItem = player.getOffhandItem();

        // 检查主手或副手是否持有海洋之心锤
        boolean hasHammerInMainHand = mainHandItem.getItem() instanceof HeartOfTheSeaHammer;
        boolean hasHammerInOffHand = offHandItem.getItem() instanceof HeartOfTheSeaHammer;

        if (hasHammerInMainHand || hasHammerInOffHand) {
            AttributeInstance speedAttr = player.getAttribute(Attributes.BLOCK_BREAK_SPEED);
            if (speedAttr != null) {
                // 检查玩家是否在水中
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

    private void mineArea(ItemStack stack, ServerLevel level, BlockState centerState, BlockPos centerPos, LivingEntity entity, String tierName) {
        Direction direction = entity.getDirection();
        int radius = ServerConfig.getMiningRadius(tierName);

        if (radius <= 0) return;

        int durabilityCost = ServerConfig.getDurabilityCost(tierName);
        boolean enableHungerCost = ServerConfig.enableHungerCost(tierName);

        List<BlockPos> areaPositions = getAreaPositions(centerPos, direction, radius);
        int blocksMined = 0;

        for (BlockPos pos : areaPositions) {
            if (pos.equals(centerPos)) continue;

            BlockState targetState = level.getBlockState(pos);

            if (!canHammerMine(targetState)) continue;

            if (stack.getDamageValue() >= stack.getMaxDamage() - durabilityCost) continue;

            if (!isCorrectToolForDrops(stack, targetState)) continue;

            List<ItemStack> drops = Block.getDrops(targetState, level, pos, level.getBlockEntity(pos), entity, stack);

            level.destroyBlock(pos, false, entity);

            for (ItemStack drop : drops) {
                ItemEntity itemEntity = new ItemEntity(level,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        drop.copy());
                level.addFreshEntity(itemEntity);
            }

            stack.hurtAndBreak(durabilityCost, entity,
                    LivingEntity.getSlotForHand(entity.getUsedItemHand()));

            blocksMined++;
        }

        if (enableHungerCost && blocksMined > 0 && entity instanceof Player player) {
            player.causeFoodExhaustion(blocksMined * 0.5f);
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
        return !state.is(net.neoforged.neoforge.common.Tags.Blocks.RELOCATION_NOT_SUPPORTED) &&
                !state.is(net.neoforged.neoforge.common.Tags.Blocks.ENDERMAN_PLACE_ON_BLACKLIST);
    }
}
