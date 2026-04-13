package com.chinaex123.hammers_galore.init;

import com.chinaex123.hammers_galore.HammersGalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public interface ModItemTags {

    TagKey<Item> HAMMERS = bind("hammers");

    // ==================== 基础锤子材料tag ====================
    TagKey<Item> WOOD_HAMMER_TOOL_MATERIALS = bind("wood_hammer_tool_materials"); // 木锤
    TagKey<Item> STONE_HAMMER_TOOL_MATERIALS = bind("stone_hammer_tool_materials"); // 石锤
    TagKey<Item> COPPER_HAMMER_TOOL_MATERIALS = bind("copper_hammer_tool_materials"); // 铜锤
    TagKey<Item> IRON_HAMMER_TOOL_MATERIALS = bind("iron_hammer_tool_materials"); // 铁锤
    TagKey<Item> GOLD_HAMMER_TOOL_MATERIALS = bind("gold_hammer_tool_materials"); // 金锤
    TagKey<Item> DIAMOND_HAMMER_TOOL_MATERIALS = bind("diamond_hammer_tool_materials"); // 钻石锤
    TagKey<Item> NETHERITE_HAMMER_TOOL_MATERIALS = bind("netherite_hammer_tool_materials"); // 下界合金锤

    // ==================== 特殊锤子材料tag ====================
    TagKey<Item> NETHER_STAR_HAMMER_TOOL_MATERIALS = bind("nether_star_hammer_tool_materials"); // 下界之星锤
    TagKey<Item> HEART_OF_THE_SEA_HAMMER_TOOL_MATERIALS = bind("heart_of_the_sea_hammer_tool_materials"); // 海洋之心锤
    TagKey<Item> CONDUIT_TOOL_MATERIALS = bind("conduit_hammer_tool_materials"); // 潮涌之锤
    TagKey<Item> ENDER_PEARL_TOOL_MATERIALS = bind("ender_pearl_hammer_tool_materials"); // 末影锤
    TagKey<Item> MAGMA_TOOL_MATERIALS = bind("magma_hammer_tool_materials"); // 岩浆锤
    TagKey<Item> PISTON_TOOL_MATERIALS = bind("piston_hammer_tool_materials"); // 活塞锤
    TagKey<Item> GLASS_TOOL_MATERIALS = bind("glass_hammer_tool_materials"); // 玻璃锤
    TagKey<Item> SCULK_TOOL_MATERIALS = bind("sculk_hammer_tool_materials"); // 幽匿锤
    TagKey<Item> EMERALD_TOOL_MATERIALS = bind("emerald_hammer_tool_materials"); // 绿宝石锤

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(HammersGalore.MOD_ID, name));
    }
}
