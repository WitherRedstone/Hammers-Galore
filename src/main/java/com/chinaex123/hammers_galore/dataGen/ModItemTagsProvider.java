package com.chinaex123.hammers_galore.dataGen;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.item.ModItems;
import com.chinaex123.hammers_galore.tags.ModItemTags;
import joptsimple.internal.Rows;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
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
    }
}
