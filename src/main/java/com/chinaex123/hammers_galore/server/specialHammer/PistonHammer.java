package com.chinaex123.hammers_galore.server.specialHammer;

import com.chinaex123.hammers_galore.config.ServerConfig;
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

import java.util.ArrayList;
import java.util.List;

public class PistonHammer extends PickaxeItems {

    private static final ResourceLocation KNOCKBACK_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("hammers_galore", "piston_hammer_knockback");

    public PistonHammer(Tier tier, Properties properties) {
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

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 应用击退效果
        applyKnockback(target, attacker);

        return super.hurtEnemy(stack, target, attacker);
    }

    /**
     * 应用击退效果
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
