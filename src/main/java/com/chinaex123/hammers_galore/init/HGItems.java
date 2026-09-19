package com.chinaex123.hammers_galore.init;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.item.PickaxeItems;
import com.chinaex123.hammers_galore.item.specialHammer.*;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public interface HGItems {
    DeferredRegister.Items ITEMS_REGISTER = DeferredRegister.createItems(HammersGalore.MOD_ID);

    // ==================== 基础锤子 ====================
    /** 木锤 */
    DeferredItem<@NotNull PickaxeItems> WOOD_HAMMER = ITEMS_REGISTER.registerItem("wood_hammer",
            p -> new PickaxeItems(HGToolMaterials.WOOD_HAMMER, p.rarity(Rarity.COMMON).pickaxe(HGToolMaterials.WOOD_HAMMER, 2.0f, -3.0f)));
    /** 石锤 */
    DeferredItem<@NotNull PickaxeItems> STONE_HAMMER = ITEMS_REGISTER.registerItem("stone_hammer",
            p -> new PickaxeItems(HGToolMaterials.STONE_HAMMER, p.rarity(Rarity.COMMON).pickaxe(HGToolMaterials.STONE_HAMMER, 2.0f, -3.0f)));
    /** 铜锤 */
    DeferredItem<@NotNull PickaxeItems> COPPER_HAMMER = ITEMS_REGISTER.registerItem("copper_hammer",
            p -> new PickaxeItems(HGToolMaterials.COPPER_HAMMER, p.rarity(Rarity.COMMON).pickaxe(HGToolMaterials.COPPER_HAMMER, 2.0f, -3.0f)));
    /** 铁锤 */
    DeferredItem<@NotNull PickaxeItems> IRON_HAMMER = ITEMS_REGISTER.registerItem("iron_hammer",
            p -> new PickaxeItems(HGToolMaterials.IRON_HAMMER, p.rarity(Rarity.UNCOMMON).pickaxe(HGToolMaterials.IRON_HAMMER, 2.0f, -3.0f)));
    /** 金锤 */
    DeferredItem<@NotNull PickaxeItems> GOLD_HAMMER = ITEMS_REGISTER.registerItem("gold_hammer",
            p -> new PickaxeItems(HGToolMaterials.GOLD_HAMMER, p.rarity(Rarity.UNCOMMON).pickaxe(HGToolMaterials.GOLD_HAMMER, 2.0f, -3.0f)));
    /** 钻石锤 */
    DeferredItem<@NotNull PickaxeItems> DIAMOND_HAMMER = ITEMS_REGISTER.registerItem("diamond_hammer",
            p -> new PickaxeItems(HGToolMaterials.DIAMOND_HAMMER, p.rarity(Rarity.RARE).pickaxe(HGToolMaterials.DIAMOND_HAMMER, 3.0f, -3.0f)));
    /** 下界合金锤 */
    DeferredItem<@NotNull PickaxeItems> NETHERITE_HAMMER = ITEMS_REGISTER.registerItem("netherite_hammer",
            p -> new PickaxeItems(HGToolMaterials.NETHERITE_HAMMER, p.rarity(Rarity.EPIC).fireResistant().pickaxe(HGToolMaterials.NETHERITE_HAMMER, 6.0f, -3.0f)));

    // ==================== 特殊锤子 ====================
    /** 下界之星锤 */
    DeferredItem<@NotNull PickaxeItems> NETHER_STAR_HAMMER = ITEMS_REGISTER.registerItem("nether_star_hammer",
            p -> new NetherStarHammer(HGToolMaterials.NETHER_STAR_HAMMER, p.rarity(Rarity.EPIC).fireResistant().pickaxe(HGToolMaterials.NETHER_STAR_HAMMER, 6.0f, -3.0f)));
    /** 海洋之心锤 */
    DeferredItem<@NotNull HeartOfTheSeaHammer> HEART_OF_THE_SEA_HAMMER = ITEMS_REGISTER.registerItem("heart_of_the_sea_hammer",
            p -> new HeartOfTheSeaHammer(HGToolMaterials.HEART_OF_THE_SEA_HAMMER, p.rarity(Rarity.EPIC).pickaxe(HGToolMaterials.HEART_OF_THE_SEA_HAMMER, 3.0f, -3.0f)));
    /** 潮涌之锤 */
    DeferredItem<@NotNull ConduitHammer> CONDUIT_HAMMER = ITEMS_REGISTER.registerItem("conduit_hammer",
            p -> new ConduitHammer(HGToolMaterials.CONDUIT_HAMMER, p.rarity(Rarity.COMMON).pickaxe(HGToolMaterials.CONDUIT_HAMMER, 6.0f, -3.0f)));
    /** 末影锤 */
    DeferredItem<@NotNull EnderPearlHammer> ENDER_PEARL_HAMMER = ITEMS_REGISTER.registerItem("ender_pearl_hammer",
            p -> new EnderPearlHammer(HGToolMaterials.ENDER_PEARL_HAMMER, p.rarity(Rarity.EPIC).pickaxe(HGToolMaterials.ENDER_PEARL_HAMMER, 3.0f, -3.0f)));
    /** 岩浆锤 */
    DeferredItem<@NotNull MagmaHammer> MAGMA_HAMMER = ITEMS_REGISTER.registerItem("magma_hammer",
            p -> new MagmaHammer(HGToolMaterials.MAGMA_HAMMER, p.rarity(Rarity.EPIC).pickaxe(HGToolMaterials.MAGMA_HAMMER, 2.0f, -3.0f)));
    /** 活塞锤 */
    DeferredItem<@NotNull PistonHammer> PISTON_HAMMER = ITEMS_REGISTER.registerItem("piston_hammer",
            p -> new PistonHammer(HGToolMaterials.PISTON_HAMMER, p.rarity(Rarity.EPIC).pickaxe(HGToolMaterials.PISTON_HAMMER, 2.0f, -3.0f)));
    /** 玻璃锤 */
    DeferredItem<@NotNull PickaxeItems> GLASS_HAMMER = ITEMS_REGISTER.registerItem("glass_hammer",
            p -> new PickaxeItems(HGToolMaterials.GLASS_HAMMER, p.rarity(Rarity.EPIC).pickaxe(HGToolMaterials.GLASS_HAMMER, 52.0f, -3.8f)));
    /** 幽匿锤 */
    DeferredItem<@NotNull SculkHammer> SCULK_HAMMER = ITEMS_REGISTER.registerItem("sculk_hammer",
            p -> new SculkHammer(HGToolMaterials.SCULK_HAMMER, p.rarity(Rarity.EPIC).pickaxe(HGToolMaterials.SCULK_HAMMER, 2.0f, -3.0f)));
    /** 绿宝石锤 */
    DeferredItem<@NotNull EmeraldHammer> EMERALD_HAMMER = ITEMS_REGISTER.registerItem("emerald_hammer",
            p -> new EmeraldHammer(HGToolMaterials.EMERALD_HAMMER, p.rarity(Rarity.EPIC).pickaxe(HGToolMaterials.EMERALD_HAMMER, 3.0f, -3.0f)));

    static void register(IEventBus eventBus){
        ITEMS_REGISTER.register(eventBus);
    }
}