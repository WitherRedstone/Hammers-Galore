package com.chinaex123.hammers_galore.item;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.server.PickaxeItems;
import com.chinaex123.hammers_galore.server.specialHammer.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ModItems {
    public static final DeferredRegister.Items ITEMS_REGISTER =
            DeferredRegister.createItems(HammersGalore.MOD_ID);

    // ==================== 基础锤子 ====================
    // 木锤
    public static final DeferredItem<@NotNull PickaxeItems> WOOD_HAMMER = ITEMS_REGISTER.registerItem("wood_hammer",
            p -> new PickaxeItems(p.rarity(Rarity.COMMON).pickaxe(ModToolMaterials.WOOD_HAMMER, 2.0f, -3.0f)));
    // 石锤
    public static final DeferredItem<@NotNull PickaxeItems> STONE_HAMMER = ITEMS_REGISTER.registerItem("stone_hammer",
            p -> new PickaxeItems(p.rarity(Rarity.COMMON).pickaxe(ModToolMaterials.STONE_HAMMER, 2.0f, -3.0f)));
    // 铜锤
    public static final DeferredItem<@NotNull PickaxeItems> COPPER_HAMMER = ITEMS_REGISTER.registerItem("copper_hammer",
            p -> new PickaxeItems(p.rarity(Rarity.COMMON).pickaxe(ModToolMaterials.COPPER_HAMMER, 2.0f, -3.0f)));
    // 铁锤
    public static final DeferredItem<@NotNull PickaxeItems> IRON_HAMMER = ITEMS_REGISTER.registerItem("iron_hammer",
            p -> new PickaxeItems(p.rarity(Rarity.UNCOMMON).pickaxe(ModToolMaterials.IRON_HAMMER, 2.0f, -3.0f)));
    // 金锤
    public static final DeferredItem<@NotNull PickaxeItems> GOLD_HAMMER = ITEMS_REGISTER.registerItem("gold_hammer",
            p -> new PickaxeItems(p.rarity(Rarity.UNCOMMON).pickaxe(ModToolMaterials.GOLD_HAMMER, 2.0f, -3.0f)));
    // 钻石锤
    public static final DeferredItem<@NotNull PickaxeItems> DIAMOND_HAMMER = ITEMS_REGISTER.registerItem("diamond_hammer",
            p -> new PickaxeItems(p.rarity(Rarity.RARE).pickaxe(ModToolMaterials.DIAMOND_HAMMER, 3.0f, -3.0f)));
    // 下界合金锤
    public static final DeferredItem<@NotNull PickaxeItems> NETHERITE_HAMMER = ITEMS_REGISTER.registerItem("netherite_hammer",
            p -> new PickaxeItems(p.rarity(Rarity.EPIC).fireResistant().pickaxe(ModToolMaterials.NETHERITE_HAMMER, 6.0f, -3.0f)));

    // ==================== 特殊锤子 ====================
    // 下界之星锤
    public static final DeferredItem<@NotNull PickaxeItems> NETHER_STAR_HAMMER = ITEMS_REGISTER.registerItem("nether_star_hammer",
            p -> new NetherStarHammer(p.rarity(Rarity.EPIC).fireResistant().pickaxe(ModToolMaterials.NETHER_STAR_HAMMER, 6.0f, -3.0f)));
    // 海洋之心锤
    public static final DeferredItem<@NotNull HeartOfTheSeaHammer> HEART_OF_THE_SEA_HAMMER = ITEMS_REGISTER.registerItem("heart_of_the_sea_hammer",
            p -> new HeartOfTheSeaHammer(p.rarity(Rarity.EPIC).pickaxe(ModToolMaterials.HEART_OF_THE_SEA_HAMMER, 3.0f, -3.0f)));
    // 潮涌之锤
    public static final DeferredItem<@NotNull ConduitHammer> CONDUIT_HAMMER = ITEMS_REGISTER.registerItem("conduit_hammer",
            p -> new ConduitHammer(p.rarity(Rarity.COMMON).pickaxe(ModToolMaterials.CONDUIT_HAMMER, 6.0f, -3.0f)));
    // 末影锤
    public static final DeferredItem<@NotNull EnderPearlHammer> ENDER_PEARL_HAMMER = ITEMS_REGISTER.registerItem("ender_pearl_hammer",
            p -> new EnderPearlHammer(p.rarity(Rarity.EPIC).pickaxe(ModToolMaterials.ENDER_PEARL_HAMMER, 3.0f, -3.0f)));
    // 岩浆锤
    public static final DeferredItem<@NotNull MagmaHammer> MAGMA_HAMMER = ITEMS_REGISTER.registerItem("magma_hammer",
            p -> new MagmaHammer(p.rarity(Rarity.EPIC).pickaxe(ModToolMaterials.MAGMA_HAMMER, 2.0f, -3.0f)));
    // 活塞锤
    public static final DeferredItem<@NotNull PistonHammer> PISTON_HAMMER = ITEMS_REGISTER.registerItem("piston_hammer",
            p -> new PistonHammer(p.rarity(Rarity.EPIC).pickaxe(ModToolMaterials.PISTON_HAMMER, 2.0f, -3.0f)));
    // 玻璃锤
    public static final DeferredItem<@NotNull Item> GLASS_HAMMER = ITEMS_REGISTER.registerItem("glass_hammer",
            p -> new Item(p.rarity(Rarity.EPIC).pickaxe(ModToolMaterials.GLASS_HAMMER, 52.0f, -3.8f)));
    // 幽匿锤
    public static final DeferredItem<@NotNull SculkHammer> SCULK_HAMMER = ITEMS_REGISTER.registerItem("sculk_hammer",
            p -> new SculkHammer(p.rarity(Rarity.EPIC).pickaxe(ModToolMaterials.SCULK_HAMMER, 2.0f, -3.0f)));
    // 绿宝石锤
    public static final DeferredItem<@NotNull EmeraldHammer> EMERALD_HAMMER = ITEMS_REGISTER.registerItem("emerald_hammer",
            p -> new EmeraldHammer(p.rarity(Rarity.EPIC).pickaxe(ModToolMaterials.EMERALD_HAMMER, 3.0f, -3.0f)));

    // 注册到游戏
    public static void register(IEventBus eventBus){
        ITEMS_REGISTER.register(eventBus);
    }
}