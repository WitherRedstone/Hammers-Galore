package com.chinaex123.hammers_galore.init;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.item.PickaxeItems;
import com.chinaex123.hammers_galore.item.specialHammer.*;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface HGItems {
    DeferredRegister.Items ITEMS_REGISTER = DeferredRegister.createItems(HammersGalore.MOD_ID);

    // ==================== 基础锤子 ====================
    /** 木锤 */
    DeferredItem<PickaxeItems> WOOD_HAMMER = ITEMS_REGISTER.register("wood_hammer",
            () -> new PickaxeItems(HGToolTiers.WOOD_HAMMER, PickaxeItems.createProperties(HGToolTiers.WOOD_HAMMER, 2.0f, -3.0f).rarity(Rarity.COMMON)));
    /** 石头锤 */
    DeferredItem<PickaxeItems> STONE_HAMMER = ITEMS_REGISTER.register("stone_hammer",
            () -> new PickaxeItems(HGToolTiers.STONE_HAMMER, PickaxeItems.createProperties(HGToolTiers.STONE_HAMMER, 2.0f, -3.0f).rarity(Rarity.COMMON)));
    /** 铜锤 */
    DeferredItem<PickaxeItems> COPPER_HAMMER = ITEMS_REGISTER.register("copper_hammer",
            () -> new PickaxeItems(HGToolTiers.COPPER_HAMMER, PickaxeItems.createProperties(HGToolTiers.COPPER_HAMMER, 2.0f, -3.0f).rarity(Rarity.COMMON)));
    /** 铁锤 */
    DeferredItem<PickaxeItems> IRON_HAMMER = ITEMS_REGISTER.register("iron_hammer",
            () -> new PickaxeItems(HGToolTiers.IRON_HAMMER, PickaxeItems.createProperties(HGToolTiers.IRON_HAMMER, 2.0f, -3.0f).rarity(Rarity.UNCOMMON)));
    /** 金金锤 */
    DeferredItem<PickaxeItems> GOLD_HAMMER = ITEMS_REGISTER.register("gold_hammer",
            () -> new PickaxeItems(HGToolTiers.GOLD_HAMMER, PickaxeItems.createProperties(HGToolTiers.GOLD_HAMMER, 2.0f, -3.0f).rarity(Rarity.UNCOMMON)));
    /** 钻石锤 */
    DeferredItem<PickaxeItems> DIAMOND_HAMMER = ITEMS_REGISTER.register("diamond_hammer",
            () -> new PickaxeItems(HGToolTiers.DIAMOND_HAMMER, PickaxeItems.createProperties(HGToolTiers.DIAMOND_HAMMER, 3.0f, -3.0f).rarity(Rarity.RARE)));
    /** 下界合金锤 */
    DeferredItem<PickaxeItems> NETHERITE_HAMMER = ITEMS_REGISTER.register("netherite_hammer",
            () -> new PickaxeItems(HGToolTiers.NETHERITE_HAMMER, PickaxeItems.createProperties(HGToolTiers.NETHERITE_HAMMER, 6.0f, -3.0f).rarity(Rarity.EPIC).fireResistant()));

    // ==================== 特殊锤子 ====================
    /** 下界之星锤 */
    DeferredItem<PickaxeItems> NETHER_STAR_HAMMER = ITEMS_REGISTER.register("nether_star_hammer",
            () -> new NetherStarHammer(HGToolTiers.NETHER_STAR_HAMMER, PickaxeItems.createProperties(HGToolTiers.NETHER_STAR_HAMMER, 6.0f, -3.0f).rarity(Rarity.EPIC).fireResistant()));
    /** 海洋之心锤 */
    DeferredItem<HeartOfTheSeaHammer> HEART_OF_THE_SEA_HAMMER = ITEMS_REGISTER.register("heart_of_the_sea_hammer",
            () -> new HeartOfTheSeaHammer(HGToolTiers.HEART_OF_THE_SEA_HAMMER, PickaxeItems.createProperties(HGToolTiers.HEART_OF_THE_SEA_HAMMER, 3.0f, -3.0f).rarity(Rarity.EPIC)));
    /** 潮涌之锤 */
    DeferredItem<ConduitHammer> CONDUIT_HAMMER = ITEMS_REGISTER.register("conduit_hammer",
            () -> new ConduitHammer(HGToolTiers.CONDUIT_HAMMER, PickaxeItems.createProperties(HGToolTiers.CONDUIT_HAMMER, 6.0f, -3.0f).rarity(Rarity.EPIC)));
    /** 末影锤 */
    DeferredItem<EnderPearlHammer> ENDER_PEARL_HAMMER = ITEMS_REGISTER.register("ender_pearl_hammer",
            () -> new EnderPearlHammer(HGToolTiers.ENDER_PEARL_HAMMER, PickaxeItems.createProperties(HGToolTiers.ENDER_PEARL_HAMMER, 3.0f, -3.0f).rarity(Rarity.EPIC)));
    /** 岩浆锤 */
    DeferredItem<MagmaHammer> MAGMA_HAMMER = ITEMS_REGISTER.register("magma_hammer",
            () -> new MagmaHammer(HGToolTiers.MAGMA_HAMMER, PickaxeItems.createProperties(HGToolTiers.MAGMA_HAMMER, 2.0f, -3.0f).rarity(Rarity.EPIC)));
    /** 活塞锤 */
    DeferredItem<PistonHammer> PISTON_HAMMER = ITEMS_REGISTER.register("piston_hammer",
            () -> new PistonHammer(HGToolTiers.PISTON_HAMMER, PickaxeItems.createProperties(HGToolTiers.PISTON_HAMMER, 2.0f, -3.0f).rarity(Rarity.EPIC)));
    /** 玻璃锤 */
    DeferredItem<PickaxeItems> GLASS_HAMMER = ITEMS_REGISTER.register("glass_hammer",
            () -> new PickaxeItems(HGToolTiers.GLASS_HAMMER,
                    PickaxeItems.createProperties(HGToolTiers.GLASS_HAMMER, 52.0f, -3.8f).rarity(Rarity.EPIC)));
    /** 幽匿锤 */
    DeferredItem<SculkHammer> SCULK_HAMMER = ITEMS_REGISTER.register("sculk_hammer",
            () -> new SculkHammer(HGToolTiers.SCULK_HAMMER, PickaxeItems.createProperties(HGToolTiers.SCULK_HAMMER, 2.0f, -3.0f).rarity(Rarity.EPIC)));
    /** 绿宝石锤 */
    DeferredItem<EmeraldHammer> EMERALD_HAMMER = ITEMS_REGISTER.register("emerald_hammer",
            () -> new EmeraldHammer(HGToolTiers.EMERALD_HAMMER, PickaxeItems.createProperties(HGToolTiers.EMERALD_HAMMER, 3.0f, -3.0f).rarity(Rarity.EPIC)));

    static void register(IEventBus eventBus){
        ITEMS_REGISTER.register(eventBus);
    }
}