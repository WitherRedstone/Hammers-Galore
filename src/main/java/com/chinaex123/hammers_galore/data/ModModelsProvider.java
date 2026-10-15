package com.chinaex123.hammers_galore.data;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.init.HGItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelsProvider extends ModelProvider {
    public ModModelsProvider(PackOutput output) {
        super(output, HammersGalore.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        // ==================== 基础锤子 ====================
        itemModels.generateFlatItem(HGItems.WOOD_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 木锤
        itemModels.generateFlatItem(HGItems.STONE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 石锤
        itemModels.generateFlatItem(HGItems.COPPER_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 铜锤
        itemModels.generateFlatItem(HGItems.IRON_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 铁锤
        itemModels.generateFlatItem(HGItems.GOLD_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 金锤
        itemModels.generateFlatItem(HGItems.DIAMOND_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 钻石锤
        itemModels.generateFlatItem(HGItems.NETHERITE_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 下界合金锤

        // ==================== 特殊锤子 ====================
        itemModels.generateFlatItem(HGItems.NETHER_STAR_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 下界之星锤
        itemModels.generateFlatItem(HGItems.HEART_OF_THE_SEA_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 海洋之心锤
        itemModels.generateFlatItem(HGItems.CONDUIT_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 潮涌之锤
        itemModels.generateFlatItem(HGItems.ENDER_PEARL_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 末影锤
        itemModels.generateFlatItem(HGItems.MAGMA_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 岩浆锤
        itemModels.generateFlatItem(HGItems.PISTON_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 活塞锤
        itemModels.generateFlatItem(HGItems.GLASS_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 玻璃锤
        itemModels.generateFlatItem(HGItems.SCULK_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 幽匿锤
        itemModels.generateFlatItem(HGItems.EMERALD_HAMMER.get(), ModelTemplates.FLAT_HANDHELD_ITEM); // 绿宝石锤
    }
}
