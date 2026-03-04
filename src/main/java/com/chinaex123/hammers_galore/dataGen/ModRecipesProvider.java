package com.chinaex123.hammers_galore.dataGen;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {

        // ==================== 基础工具 ====================
        // 木锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.WOOD_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', ItemTags.LOGS)
                .unlockedBy("has_wood_hammer", has(ItemTags.LOGS))
                .save(recipeOutput);
        // 石锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.STONE_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Items.COBBLESTONE)
                .unlockedBy("has_stone_hammer", has(Items.COBBLESTONE))
                .save(recipeOutput);
        // 铜锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.COPPER_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.INGOTS_COPPER)
                .unlockedBy("has_copper_hammer", has(Tags.Items.INGOTS_COPPER))
                .save(recipeOutput);
        // 铁锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.IRON_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.INGOTS_IRON)
                .unlockedBy("has_iron_hammer", has(Tags.Items.INGOTS_IRON))
                .save(recipeOutput);
        // 金锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.GOLD_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.INGOTS_GOLD)
                .unlockedBy("has_gold_hammer", has(Tags.Items.INGOTS_GOLD))
                .save(recipeOutput);
        // 钻石锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.DIAMOND_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.GEMS_DIAMOND)
                .unlockedBy("has_diamond_hammer", has(Tags.Items.GEMS_DIAMOND))
                .save(recipeOutput);
        // 下界合金锤
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.DIAMOND_HAMMER),
                        Ingredient.of(Tags.Items.INGOTS_NETHERITE),
                        RecipeCategory.MISC, ModItems.NETHERITE_HAMMER.get()
                )
                .unlocks("has_netherite_hammer", has(Tags.Items.INGOTS_NETHERITE))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(HammersGalore.MOD_ID, "netherite_hammer"));

        // ==================== 特殊锤子 ====================
        // 下界之星锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.NETHER_STAR_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.NETHER_STARS)
                .unlockedBy("has_nether_star_hammer", has(Tags.Items.NETHER_STARS))
                .save(recipeOutput);
        // 海洋之心锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.HEART_OF_THE_SEA_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Items.HEART_OF_THE_SEA)
                .unlockedBy("has_heart_of_the_sea_hammer", has(Items.HEART_OF_THE_SEA))
                .save(recipeOutput);
        // 潮涌之锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.CONDUIT_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Items.CONDUIT)
                .unlockedBy("has_conduit_hammer", has(Items.CONDUIT))
                .save(recipeOutput);
        // 末影锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.ENDER_PEARL_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.ENDER_PEARLS)
                .unlockedBy("has_ender_pearl_hammer", has(Tags.Items.ENDER_PEARLS))
                .save(recipeOutput);
        // 岩浆锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.MAGMA_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Items.MAGMA_BLOCK)
                .unlockedBy("has_magma_hammer", has(Items.MAGMA_BLOCK))
                .save(recipeOutput);
        // 活塞锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.PISTON_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Items.PISTON)
                .unlockedBy("has_piston_hammer", has(Items.PISTON))
                .save(recipeOutput);
        // 玻璃锤
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.NETHERITE_HAMMER),
                        Ingredient.of(Tags.Items.GLASS_BLOCKS),
                        RecipeCategory.MISC, ModItems.GLASS_HAMMER.get()
                )
                .unlocks("has_glass_hammer", has(Tags.Items.GLASS_BLOCKS))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(HammersGalore.MOD_ID, "glass_hammer"));
        // 幽匿锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.SCULK_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Items.SCULK)
                .unlockedBy("has_sculk_hammer", has(Items.SCULK))
                .save(recipeOutput);
        // 绿宝石锤
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                        ModItems.EMERALD_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.GEMS_EMERALD)
                .unlockedBy("has_emerald_hammer", has(Tags.Items.GEMS_EMERALD))
                .save(recipeOutput);
    }
}
