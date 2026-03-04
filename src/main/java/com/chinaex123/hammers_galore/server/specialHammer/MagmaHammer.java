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
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class MagmaHammer extends PickaxeItems {

    public MagmaHammer(Tier tier, Properties properties) {
        super(tier, properties);
    }

    /**
     * 重写方块的挖掘方法，实现岩浆锤的范围挖掘和自动烧炼功能
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
                mineSingleBlock(stack, level, state, pos, entity);
            }
        }

        // 挖掘成功
        return true;
    }

    /**
     * 挖掘单个方块
     */
    private void mineSingleBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (entity instanceof Player player) {
            // 检查是否可以挖掘（使用工具类）
            if (!HammerMiningHelper.canHammerMine(state)) return;
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

    /**
     * 在指定区域内进行范围挖掘，挖掘以中心位置为基准的方形区域，并将矿物烧炼后掉落
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

            // 检查是否是受重力影响的方块（沙子、沙砾等）
            if (targetState.canBeReplaced() || !targetState.isAir()) {
                // 获取方块的掉落物
                List<ItemStack> drops = Block.getDrops(targetState, level, pos, level.getBlockEntity(pos), entity, stack);

                // 移除原方块（不生成掉落实体，手动处理）
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
     * 尝试将物品烧炼，查找对应的熔炉配方并返回烧炼后的产物
     *
     * @param input 待烧炼的物品堆栈
     * @param level 当前世界等级（用于获取配方管理器）
     * @return 烧炼后的物品堆栈；如果没有对应的烧炼配方，则返回原物品
     */
    private ItemStack smeltItem(ItemStack input, Level level) {
        // 如果输入为空，返回空物品
        if (input.isEmpty()) {
            return ItemStack.EMPTY;
        }

        // 获取世界的配方管理器
        RecipeManager recipeManager = level.getRecipeManager();

        // 遍历所有烧炼配方，查找匹配的项目
        List<ResourceLocation> recipes = recipeManager.getAllRecipesFor(RecipeType.SMELTING)
                .stream()
                .map(RecipeHolder::id)
                .toList();

        // 逐个检查配方是否匹配
        for (ResourceLocation recipeId : recipes) {
            Optional<? extends RecipeHolder<?>> recipeOpt = recipeManager.byKey(recipeId);
            if (recipeOpt.isPresent()) {
                RecipeHolder<?> holder = recipeOpt.get();
                if (holder.value() instanceof SmeltingRecipe recipe) {
                    SingleRecipeInput recipeInput = new SingleRecipeInput(input);
                    if (recipe.matches(recipeInput, level)) {
                        // 找到匹配的配方，返回烧炼结果
                        return recipe.getResultItem(level.registryAccess()).copy();
                    }
                }
            }
        }

        // 没有烧炼配方，返回原物品
        return input;
    }
}
