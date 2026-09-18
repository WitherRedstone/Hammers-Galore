package com.chinaex123.hammers_galore.dataGen;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.init.HGItems;
import com.chinaex123.hammers_galore.init.HGItemTags;
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

        tag(HGItemTags.HAMMERS)
                // ==================== 基础锤子 ====================
                .add(HGItems.WOOD_HAMMER.get()) // 木锤
                .add(HGItems.STONE_HAMMER.get()) // 石锤
                .add(HGItems.COPPER_HAMMER.get()) // 铜锤
                .add(HGItems.IRON_HAMMER.get()) // 铁锤
                .add(HGItems.GOLD_HAMMER.get()) //
                .add(HGItems.DIAMOND_HAMMER.get()) // 钻石锤
                .add(HGItems.NETHERITE_HAMMER.get()) // 下界合金锤

                // ==================== 特殊锤子 ====================
                .add(HGItems.NETHER_STAR_HAMMER.get()) // 下界之星锤
                .add(HGItems.HEART_OF_THE_SEA_HAMMER.get()) // 海洋之心锤
                .add(HGItems.CONDUIT_HAMMER.get()) // 潮涌之锤
                .add(HGItems.ENDER_PEARL_HAMMER.get()) // 末影锤
                .add(HGItems.MAGMA_HAMMER.get()) // 岩浆锤
                .add(HGItems.PISTON_HAMMER.get()) // 活塞锤
                .add(HGItems.GLASS_HAMMER.get()) // 玻璃锤
                .add(HGItems.SCULK_HAMMER.get()) // 幽匿锤
                .add(HGItems.EMERALD_HAMMER.get()); // 绿宝石锤

        tag(Tags.Items.TOOLS)
                .addTag(HGItemTags.HAMMERS);

        tag(Tags.Items.MINING_TOOL_TOOLS)
                .addTag(HGItemTags.HAMMERS);


        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(HGItemTags.HAMMERS);

        tag(ItemTags.MINING_ENCHANTABLE)
                .addTag(HGItemTags.HAMMERS);
        tag(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addTag(HGItemTags.HAMMERS);

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .addTag(HGItemTags.HAMMERS);

        // ==================== 基础锤子材料tag ====================
        // 木锤
        tag(HGItemTags.WOOD_HAMMER_TOOL_MATERIALS)
                .addTag(ItemTags.LOGS);
        // 石锤
        tag(HGItemTags.STONE_HAMMER_TOOL_MATERIALS)
                .add(Items.COBBLESTONE);
        // 铜锤
        tag(HGItemTags.COPPER_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.INGOTS_COPPER);
        // 铁锤
        tag(HGItemTags.IRON_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.INGOTS_IRON);
        // 金锤
        tag(HGItemTags.GOLD_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.INGOTS_GOLD);
        // 钻石锤
        tag(HGItemTags.DIAMOND_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.GEMS_DIAMOND);
        // 下界合金锤
        tag(HGItemTags.NETHERITE_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.INGOTS_NETHERITE);

        // ==================== 特殊锤子材料tag ====================
        // 下界之星锤
        tag(HGItemTags.NETHER_STAR_HAMMER_TOOL_MATERIALS)
                .addTag(Tags.Items.NETHER_STARS);
        // 海洋之心锤
        tag(HGItemTags.HEART_OF_THE_SEA_HAMMER_TOOL_MATERIALS)
                .add(Items.HEART_OF_THE_SEA);
        // 潮涌之锤
        tag(HGItemTags.CONDUIT_TOOL_MATERIALS)
                .add(Items.CONDUIT);
        // 潮涌之锤
        tag(HGItemTags.ENDER_PEARL_TOOL_MATERIALS)
                .addTag(Tags.Items.ENDER_PEARLS);
        // 岩浆锤
        tag(HGItemTags.MAGMA_TOOL_MATERIALS)
                .add(Items.MAGMA_BLOCK);
        // 活塞锤
        tag(HGItemTags.PISTON_TOOL_MATERIALS)
                .add(Items.PISTON);
        // 玻璃锤
        tag(HGItemTags.GLASS_TOOL_MATERIALS)
                .addTag(Tags.Items.GLASS_BLOCKS);
        // 幽匿锤
        tag(HGItemTags.SCULK_TOOL_MATERIALS)
                .add(Items.SCULK);
        // 绿宝石锤
        tag(HGItemTags.EMERALD_TOOL_MATERIALS)
                .addTag(Tags.Items.GEMS_EMERALD);
    }
}
