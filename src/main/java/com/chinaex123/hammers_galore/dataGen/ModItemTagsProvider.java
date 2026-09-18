package com.chinaex123.hammers_galore.dataGen;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.init.HGItems;
import com.chinaex123.hammers_galore.init.HGItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, HammersGalore.MOD_ID, existingFileHelper);
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
    }
}
