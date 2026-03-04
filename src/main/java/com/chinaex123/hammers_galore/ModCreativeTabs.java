package com.chinaex123.hammers_galore;

import com.chinaex123.hammers_galore.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HammersGalore.MOD_ID);


    // 创造模式物品栏 - 红石酱的化学元素：元素方块
    public static final Supplier<CreativeModeTab> HAMMERS_GALORE_TAB =
            CREATIVE_MODE_TAB.register("hammers_galore_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.DIAMOND_HAMMER.get()))
                    .title(Component.translatable("itemGroup.hammers_galore_tab"))
                    .displayItems((parameters, output) -> {

                        // ==================== 基础锤子 ====================
                        output.accept(ModItems.WOOD_HAMMER.get()); // 木锤
                        output.accept(ModItems.STONE_HAMMER.get()); // 石锤
                        output.accept(ModItems.COPPER_HAMMER.get()); // 铜锤
                        output.accept(ModItems.IRON_HAMMER.get()); // 铁锤
                        output.accept(ModItems.GOLD_HAMMER.get()); // 金锤
                        output.accept(ModItems.DIAMOND_HAMMER.get()); // 钻石锤
                        output.accept(ModItems.NETHERITE_HAMMER.get()); // 下界合金锤

                        // ==================== 特殊锤子 ====================
                        output.accept(ModItems.NETHER_STAR_HAMMER.get()); // 下界之星锤
                        output.accept(ModItems.HEART_OF_THE_SEA_HAMMER.get()); // 海洋之心锤
                        output.accept(ModItems.CONDUIT_HAMMER.get()); // 潮涌之锤
                        output.accept(ModItems.ENDER_PEARL_HAMMER.get()); // 末影锤
                        output.accept(ModItems.MAGMA_HAMMER.get()); // 岩浆锤
                        output.accept(ModItems.PISTON_HAMMER.get()); // 活塞锤
                        output.accept(ModItems.GLASS_HAMMER.get()); // 玻璃锤
                        output.accept(ModItems.SCULK_HAMMER.get()); // 幽匿锤

                    })
                    .build());

    // 注册到NeoForge事件总线里
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
