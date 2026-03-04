package com.chinaex123.hammers_galore.server.specialHammer;

import com.chinaex123.hammers_galore.config.ServerConfig;
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
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MagmaHammer extends PickaxeItems {

    public MagmaHammer(Tier tier, Properties properties) {
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

            // 获取掉落物
            List<ItemStack> drops = Block.getDrops(state, (ServerLevel) level, pos, level.getBlockEntity(pos), entity, stack);

            // 移除原方块
            level.destroyBlock(pos, false, entity);

            // 将矿物烧炼后掉落
            for (ItemStack drop : drops) {
                ItemStack smeltedResult = smeltItem(drop, level);

                ItemEntity itemEntity = new ItemEntity(level,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        smeltedResult.copy());
                level.addFreshEntity(itemEntity);
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

        for (BlockPos pos : areaPositions) {
            // 不再跳过中心方块，一起处理
            BlockState targetState = level.getBlockState(pos);

            if (!canHammerMine(targetState)) continue;

            if (stack.getDamageValue() >= stack.getMaxDamage() - durabilityCost) continue;

            if (!isCorrectToolForDrops(stack, targetState)) continue;

            // 检查是否是受重力影响的方块（沙子、沙砾等）
            if (targetState.canBeReplaced() || !targetState.isAir()) {
                // 获取掉落物
                List<ItemStack> drops = Block.getDrops(targetState, level, pos, level.getBlockEntity(pos), entity, stack);

                // 移除原方块（不生成掉落实体）
                level.destroyBlock(pos, false, entity);

                // 将矿物烧炼后掉落
                for (ItemStack drop : drops) {
                    ItemStack smeltedResult = smeltItem(drop, level);

                    ItemEntity itemEntity = new ItemEntity(level,
                            pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                            smeltedResult.copy());
                    level.addFreshEntity(itemEntity);
                }
            }

            // 消耗耐久
            stack.hurtAndBreak(durabilityCost, entity,
                    LivingEntity.getSlotForHand(entity.getUsedItemHand()));

            blocksMined++;
        }

        // 饱食度消耗
        if (enableHungerCost && blocksMined > 0 && entity instanceof Player player) {
            player.causeFoodExhaustion(blocksMined * 0.5f);
        }
    }

    /**
     * 尝试将物品烧炼（使用熔炉配方）
     */
    private ItemStack smeltItem(ItemStack input, Level level) {
        if (input.isEmpty()) {
            return ItemStack.EMPTY;
        }

        RecipeManager recipeManager = level.getRecipeManager();

        // 获取所有烧炼配方
        List<ResourceLocation> recipes = recipeManager.getAllRecipesFor(RecipeType.SMELTING)
                .stream()
                .map(RecipeHolder::id)
                .toList();

        for (ResourceLocation recipeId : recipes) {
            Optional<? extends RecipeHolder<?>> recipeOpt = recipeManager.byKey(recipeId);
            if (recipeOpt.isPresent()) {
                RecipeHolder<?> holder = recipeOpt.get();
                if (holder.value() instanceof SmeltingRecipe recipe) {
                    SingleRecipeInput recipeInput = new SingleRecipeInput(input);
                    if (recipe.matches(recipeInput, level)) {
                        return recipe.getResultItem(level.registryAccess()).copy();
                    }
                }
            }
        }

        // 没有烧炼配方，返回原物品
        return input;
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
