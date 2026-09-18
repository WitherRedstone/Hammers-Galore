package com.chinaex123.hammers_galore.dataGen;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.init.HGItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HammersGalore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        // ==================== 基础锤子 ====================
        basicItem(HGItems.WOOD_HAMMER.get()); // 木锤
        basicItem(HGItems.STONE_HAMMER.get()); // 石锤
        basicItem(HGItems.COPPER_HAMMER.get()); // 铜锤
        basicItem(HGItems.IRON_HAMMER.get()); // 铁锤
        basicItem(HGItems.GOLD_HAMMER.get()); //
        basicItem(HGItems.DIAMOND_HAMMER.get()); // 钻石锤
        basicItem(HGItems.NETHERITE_HAMMER.get()); // 下界合金锤

        // ==================== 特殊锤子 ====================
        basicItem(HGItems.NETHER_STAR_HAMMER.get()); // 下界之星锤
        basicItem(HGItems.HEART_OF_THE_SEA_HAMMER.get()); // 海洋之心锤
        basicItem(HGItems.CONDUIT_HAMMER.get()); // 潮涌之锤
        basicItem(HGItems.ENDER_PEARL_HAMMER.get()); // 末影锤
        basicItem(HGItems.MAGMA_HAMMER.get()); // 岩浆锤
        basicItem(HGItems.PISTON_HAMMER.get()); // 活塞锤
        basicItem(HGItems.GLASS_HAMMER.get()); // 玻璃锤
        basicItem(HGItems.SCULK_HAMMER.get()); // 幽匿锤
        basicItem(HGItems.EMERALD_HAMMER.get()); // 绿宝石锤
    }
}
