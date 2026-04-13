package com.chinaex123.hammers_galore.dataGen;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.init.ModItems;
import com.chinaex123.hammers_galore.init.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, HammersGalore.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        tag(ModItemTags.HAMMERS)
                // ==================== 基础锤子 ====================
                .add(ModItems.WOOD_HAMMER.get()) // 木锤
                .add(ModItems.STONE_HAMMER.get()) // 石锤
                .add(ModItems.COPPER_HAMMER.get()) // 铜锤
                .add(ModItems.IRON_HAMMER.get()) // 铁锤
                .add(ModItems.GOLD_HAMMER.get()) //
                .add(ModItems.DIAMOND_HAMMER.get()) // 钻石锤
                .add(ModItems.NETHERITE_HAMMER.get()) // 下界合金锤

                // ==================== 特殊锤子 ====================
                .add(ModItems.NETHER_STAR_HAMMER.get()) // 下界之星锤
                .add(ModItems.HEART_OF_THE_SEA_HAMMER.get()) // 海洋之心锤
                .add(ModItems.CONDUIT_HAMMER.get()) // 潮涌之锤
                .add(ModItems.ENDER_PEARL_HAMMER.get()) // 末影锤
                .add(ModItems.MAGMA_HAMMER.get()) // 岩浆锤
                .add(ModItems.PISTON_HAMMER.get()) // 活塞锤
                .add(ModItems.GLASS_HAMMER.get()) // 玻璃锤
                .add(ModItems.SCULK_HAMMER.get()) // 幽匿锤
                .add(ModItems.EMERALD_HAMMER.get()); // 绿宝石锤

        tag(Tags.Items.TOOLS)
                .addTag(ModItemTags.HAMMERS);

        tag(Tags.Items.MINING_TOOL_TOOLS)
                .addTag(ModItemTags.HAMMERS);


        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(ModItemTags.HAMMERS);

        tag(ItemTags.MINING_ENCHANTABLE)
                .addTag(ModItemTags.HAMMERS);
        tag(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addTag(ModItemTags.HAMMERS);

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .addTag(ModItemTags.HAMMERS);

        // ==================== 基础锤子材料tag ====================
        // 木锤
        tag(ModItemTags.WOOD_HAMMER_TOOL_MATERIALS)
                .addTag(ItemTags.LOGS);
        // 石锤
        tag(ModItemTags.STONE_HAMMER_TOOL_MATERIALS)
                .add(Items.COBBLESTONE);
        // 铜锤
        tag(ModItemTags.COPPER_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.INGOTS_COPPER);
        // 铁锤
        tag(ModItemTags.IRON_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.INGOTS_IRON);
        // 金锤
        tag(ModItemTags.GOLD_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.INGOTS_GOLD);
        // 钻石锤
        tag(ModItemTags.DIAMOND_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.GEMS_DIAMOND);
        // 下界合金锤
        tag(ModItemTags.NETHERITE_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.INGOTS_NETHERITE);

        // ==================== 特殊锤子材料tag ====================
        // 下界之星锤
        tag(ModItemTags.NETHER_STAR_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.NETHER_STARS);
        // 海洋之心锤
        tag(ModItemTags.HEART_OF_THE_SEA_HAMMER_TOOL_MATERIALS)
                .add(Items.HEART_OF_THE_SEA);
        // 潮涌之锤
        tag(ModItemTags.CONDUIT_TOOL_MATERIALS)
                .add(Items.CONDUIT);
        // 潮涌之锤
        tag(ModItemTags.ENDER_PEARL_TOOL_MATERIALS)
                .addTag(Tags.Items.ENDER_PEARLS);
        // 岩浆锤
        tag(ModItemTags.MAGMA_TOOL_MATERIALS)
                .add(Items.MAGMA_BLOCK);
        // 活塞锤
        tag(ModItemTags.PISTON_TOOL_MATERIALS)
                .add(Items.PISTON);
        // 玻璃锤
        tag(ModItemTags.GLASS_TOOL_MATERIALS)
                .addTag(Tags.Items.GLASS_BLOCKS);
        // 幽匿锤
        tag(ModItemTags.SCULK_TOOL_MATERIALS)
                .add(Items.SCULK);
        // 绿宝石锤
        tag(ModItemTags.EMERALD_TOOL_MATERIALS)
                .addTag(Tags.Items.GEMS_EMERALD);
    }
}
