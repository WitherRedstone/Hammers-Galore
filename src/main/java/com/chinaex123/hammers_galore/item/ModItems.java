package com.chinaex123.hammers_galore.item;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.server.PickaxeItems;
import com.chinaex123.hammers_galore.server.specialHammer.*;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS_REGISTER =
            DeferredRegister.createItems(HammersGalore.MOD_ID);

    // ==================== 基础锤子 ====================
    // 木锤
    public static final DeferredItem<PickaxeItems> WOOD_HAMMER = ITEMS_REGISTER.register("wood_hammer",
            () -> new PickaxeItems(ModToolTiers.WOOD_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.WOOD_HAMMER, 2.0f, -3.0f).rarity(Rarity.COMMON)));
    // 石锤
    public static final DeferredItem<PickaxeItems> STONE_HAMMER = ITEMS_REGISTER.register("stone_hammer",
            () -> new PickaxeItems(ModToolTiers.STONE_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.STONE_HAMMER, 2.0f, -3.0f).rarity(Rarity.COMMON)));
    // 铜锤
    public static final DeferredItem<PickaxeItems> COPPER_HAMMER = ITEMS_REGISTER.register("copper_hammer",
            () -> new PickaxeItems(ModToolTiers.COPPER_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.COPPER_HAMMER, 2.0f, -3.0f).rarity(Rarity.COMMON)));
    // 铁锤
    public static final DeferredItem<PickaxeItems> IRON_HAMMER = ITEMS_REGISTER.register("iron_hammer",
            () -> new PickaxeItems(ModToolTiers.IRON_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.IRON_HAMMER, 2.0f, -3.0f).rarity(Rarity.UNCOMMON)));
    // 金锤
    public static final DeferredItem<PickaxeItems> GOLD_HAMMER = ITEMS_REGISTER.register("gold_hammer",
            () -> new PickaxeItems(ModToolTiers.GOLD_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.GOLD_HAMMER, 2.0f, -3.0f).rarity(Rarity.UNCOMMON)));
    // 钻石锤
    public static final DeferredItem<PickaxeItems> DIAMOND_HAMMER = ITEMS_REGISTER.register("diamond_hammer",
            () -> new PickaxeItems(ModToolTiers.DIAMOND_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.DIAMOND_HAMMER, 3.0f, -3.0f).rarity(Rarity.RARE)));
    // 下界合金锤
    public static final DeferredItem<PickaxeItems> NETHERITE_HAMMER = ITEMS_REGISTER.register("netherite_hammer",
            () -> new PickaxeItems(ModToolTiers.NETHERITE_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.NETHERITE_HAMMER, 6.0f, -3.0f).rarity(Rarity.EPIC).fireResistant()));

    // ==================== 特殊锤子 ====================
    // 下界之星锤
    public static final DeferredItem<PickaxeItems> NETHER_STAR_HAMMER = ITEMS_REGISTER.register("nether_star_hammer",
            () -> new NetherStarHammer(ModToolTiers.NETHER_STAR_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.NETHER_STAR_HAMMER, 6.0f, -3.0f).rarity(Rarity.EPIC).fireResistant()));
    // 海洋之心锤
    public static final DeferredItem<HeartOfTheSeaHammer> HEART_OF_THE_SEA_HAMMER = ITEMS_REGISTER.register("heart_of_the_sea_hammer",
            () -> new HeartOfTheSeaHammer(ModToolTiers.HEART_OF_THE_SEA_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.HEART_OF_THE_SEA_HAMMER, 3.0f, -3.0f).rarity(Rarity.EPIC)));
    // 潮涌之锤
    public static final DeferredItem<ConduitHammer> CONDUIT_HAMMER = ITEMS_REGISTER.register("conduit_hammer",
            () -> new ConduitHammer(ModToolTiers.CONDUIT_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.CONDUIT_HAMMER, 6.0f, -3.0f).rarity(Rarity.EPIC)));
    // 末影锤
    public static final DeferredItem<EnderPearlHammer> ENDER_PEARL_HAMMER = ITEMS_REGISTER.register("ender_pearl_hammer",
            () -> new EnderPearlHammer(ModToolTiers.ENDER_PEARL_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.ENDER_PEARL_HAMMER, 3.0f, -3.0f).rarity(Rarity.EPIC)));
    // 岩浆锤
    public static final DeferredItem<MagmaHammer> MAGMA_HAMMER = ITEMS_REGISTER.register("magma_hammer",
            () -> new MagmaHammer(ModToolTiers.MAGMA_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.MAGMA_HAMMER, 2.0f, -3.0f).rarity(Rarity.EPIC)));
    // 活塞锤
    public static final DeferredItem<PistonHammer> PISTON_HAMMER = ITEMS_REGISTER.register("piston_hammer",
            () -> new PistonHammer(ModToolTiers.PISTON_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.PISTON_HAMMER, 2.0f, -3.0f).rarity(Rarity.EPIC)));
    // 玻璃锤
    public static final DeferredItem<PickaxeItems> GLASS_HAMMER = ITEMS_REGISTER.register("glass_hammer",
            () -> new PickaxeItems(ModToolTiers.GLASS_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.GLASS_HAMMER, 52.0f, -3.8f).rarity(Rarity.EPIC)));
    // 幽匿锤
    public static final DeferredItem<SculkHammer> SCULK_HAMMER = ITEMS_REGISTER.register("sculk_hammer",
            () -> new SculkHammer(ModToolTiers.SCULK_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.SCULK_HAMMER, 2.0f, -3.0f).rarity(Rarity.EPIC)));
    // 绿宝石锤
    public static final DeferredItem<EmeraldHammer> EMERALD_HAMMER = ITEMS_REGISTER.register("emerald_hammer",
            () -> new EmeraldHammer(ModToolTiers.EMERALD_HAMMER,
                    PickaxeItems.createProperties(ModToolTiers.EMERALD_HAMMER, 3.0f, -3.0f).rarity(Rarity.EPIC)));


    // 注册到游戏
    public static void register(IEventBus eventBus){
        ITEMS_REGISTER.register(eventBus);
    }
}