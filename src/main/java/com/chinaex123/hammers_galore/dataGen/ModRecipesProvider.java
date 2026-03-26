package com.chinaex123.hammers_galore.dataGen;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.item.ModItems;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends RecipeProvider {
    private final HolderLookup.Provider provider;
    private final RecipeOutput output;

    public ModRecipesProvider(HolderLookup.Provider provider, RecipeOutput output) {
        super(provider, output);
        this.provider = provider;
        this.output = output;
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(packOutput, lookupProvider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new ModRecipesProvider(provider, output);
        }

        @Override
        public @NotNull String getName() {
            return HammersGalore.MOD_ID;
        }
    }

    @Override
    protected void buildRecipes() {
        HolderGetter<Item> itemRegistryLookup = this.registries.lookupOrThrow(Registries.ITEM);

        // ==================== 基础工具 ====================
        // 木锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.WOOD_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', ItemTags.LOGS)
                .unlockedBy("has_wood_hammer", has(ItemTags.LOGS))
                .save(output);
        // 石锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.STONE_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Items.COBBLESTONE)
                .unlockedBy("has_stone_hammer", has(Items.COBBLESTONE))
                .save(output);
        // 铜锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.COPPER_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.STORAGE_BLOCKS_COPPER)
                .unlockedBy("has_copper_hammer", has(Tags.Items.STORAGE_BLOCKS_COPPER))
                .save(output);
        // 铁锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.IRON_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.STORAGE_BLOCKS_IRON)
                .unlockedBy("has_iron_hammer", has(Tags.Items.STORAGE_BLOCKS_IRON))
                .save(output);
        // 金锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.GOLD_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.STORAGE_BLOCKS_GOLD)
                .unlockedBy("has_gold_hammer", has(Tags.Items.STORAGE_BLOCKS_GOLD))
                .save(output);
        // 钻石锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.DIAMOND_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.STORAGE_BLOCKS_DIAMOND)
                .unlockedBy("has_diamond_hammer", has(Tags.Items.STORAGE_BLOCKS_DIAMOND))
                .save(output);
        // 下界合金锤
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.DIAMOND_HAMMER),
                        Ingredient.of(itemRegistryLookup.getOrThrow(Tags.Items.INGOTS_NETHERITE)),
                        RecipeCategory.MISC, ModItems.NETHERITE_HAMMER.get()
                )
                .unlocks("has_netherite_hammer", has(Tags.Items.INGOTS_NETHERITE))
                .save(output, String.valueOf(Identifier.fromNamespaceAndPath(HammersGalore.MOD_ID, "netherite_hammer")));

        // ==================== 特殊锤子 ====================
        // 下界之星锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.NETHER_STAR_HAMMER.get())
                .pattern("BCB")
                .pattern("CAC")
                .pattern("BCB")
                .define('A', ModItems.NETHERITE_HAMMER)
                .define('B', Tags.Items.GEMS_AMETHYST)
                .define('C', Tags.Items.NETHER_STARS)
                .unlockedBy("has_nether_star_hammer", has(ModItems.NETHERITE_HAMMER))
                .save(output);
        // 海洋之心锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.HEART_OF_THE_SEA_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Items.HEART_OF_THE_SEA)
                .unlockedBy("has_heart_of_the_sea_hammer", has(Items.HEART_OF_THE_SEA))
                .save(output);
        // 潮涌之锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.CONDUIT_HAMMER.get())
                .pattern("BCB")
                .pattern("CAC")
                .pattern("BCB")
                .define('A', ModItems.HEART_OF_THE_SEA_HAMMER)
                .define('B', Tags.Items.GEMS_AMETHYST)
                .define('C', Items.CONDUIT)
                .unlockedBy("has_conduit_hammer", has(Items.CONDUIT))
                .save(output);
        // 末影锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.ENDER_PEARL_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.ENDER_PEARLS)
                .unlockedBy("has_ender_pearl_hammer", has(Tags.Items.ENDER_PEARLS))
                .save(output);
        // 岩浆锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.MAGMA_HAMMER.get())
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('A', ModItems.IRON_HAMMER)
                .define('B', Items.MAGMA_BLOCK)
                .unlockedBy("has_magma_hammer", has(Items.MAGMA_BLOCK))
                .save(output);
        // 活塞锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.PISTON_HAMMER.get())
                .pattern("BBB")
                .pattern("CAC")
                .pattern("BBB")
                .define('A', ModItems.IRON_HAMMER)
                .define('B', Items.PISTON)
                .define('C', Items.GLOW_INK_SAC)
                .unlockedBy("has_piston_hammer", has(Items.PISTON))
                .save(output);
        // 玻璃锤
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.NETHERITE_HAMMER),
                        Ingredient.of(itemRegistryLookup.getOrThrow(Tags.Items.GLASS_BLOCKS)),
                        RecipeCategory.MISC, ModItems.GLASS_HAMMER.get()
                )
                .unlocks("has_glass_hammer", has(Tags.Items.GLASS_BLOCKS))
                .save(output, String.valueOf(Identifier.fromNamespaceAndPath(HammersGalore.MOD_ID, "glass_hammer")));
        // 幽匿锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.SCULK_HAMMER.get())
                .pattern("BBB")
                .pattern("CAC")
                .pattern("BBB")
                .define('A', ModItems.IRON_HAMMER)
                .define('B', Items.SCULK)
                .define('C', Items.EXPERIENCE_BOTTLE)
                .unlockedBy("has_sculk_hammer", has(Items.SCULK))
                .save(output);
        // 绿宝石锤
        ShapedRecipeBuilder.shaped(itemRegistryLookup, RecipeCategory.MISC, ModItems.EMERALD_HAMMER.get())
                .pattern(" BA")
                .pattern(" AB")
                .pattern("A  ")
                .define('A', Tags.Items.RODS_WOODEN)
                .define('B', Tags.Items.STORAGE_BLOCKS_EMERALD)
                .unlockedBy("has_emerald_hammer", has(Tags.Items.STORAGE_BLOCKS_EMERALD))
                .save(output);
    }
}
