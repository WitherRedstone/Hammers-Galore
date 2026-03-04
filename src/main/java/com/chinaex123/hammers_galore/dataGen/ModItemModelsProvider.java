package com.chinaex123.hammers_galore.dataGen;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HammersGalore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        // ==================== 基础锤子 ====================
        basicItem(ModItems.WOOD_HAMMER.get()); // 木锤
        basicItem(ModItems.STONE_HAMMER.get()); // 石锤
        basicItem(ModItems.COPPER_HAMMER.get()); // 铜锤
        basicItem(ModItems.IRON_HAMMER.get()); // 铁锤
        basicItem(ModItems.GOLD_HAMMER.get()); //
        basicItem(ModItems.DIAMOND_HAMMER.get()); // 钻石锤
        basicItem(ModItems.NETHERITE_HAMMER.get()); // 下界合金锤

        // ==================== 特殊锤子 ====================
        basicItem(ModItems.NETHER_STAR_HAMMER.get()); // 下界之星锤
        basicItem(ModItems.HEART_OF_THE_SEA_HAMMER.get()); // 海洋之心锤
        basicItem(ModItems.CONDUIT_HAMMER.get()); // 潮涌之锤
        basicItem(ModItems.ENDER_PEARL_HAMMER.get()); // 末影锤
        basicItem(ModItems.MAGMA_HAMMER.get()); // 岩浆锤
        basicItem(ModItems.PISTON_HAMMER.get()); // 活塞锤
        basicItem(ModItems.GLASS_HAMMER.get()); // 玻璃锤
        basicItem(ModItems.SCULK_HAMMER.get()); // 幽匿锤
    }
}
