package com.chinaex123.hammers_galore.init;

import com.chinaex123.hammers_galore.HammersGalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HGCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HammersGalore.MOD_ID);

    public static final Supplier<CreativeModeTab> HAMMERS_GALORE_TAB =
            CREATIVE_MODE_TAB.register("hammers_galore_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(HGItems.DIAMOND_HAMMER.get()))
                    .title(Component.translatable("itemGroup.hammers_galore_tab"))
                    .displayItems((parameters, output) -> {

                        // ==================== 基础锤子 ====================
                        output.accept(HGItems.WOOD_HAMMER.get());
                        output.accept(HGItems.STONE_HAMMER.get());
                        output.accept(HGItems.COPPER_HAMMER.get());
                        output.accept(HGItems.IRON_HAMMER.get());
                        output.accept(HGItems.GOLD_HAMMER.get());
                        output.accept(HGItems.DIAMOND_HAMMER.get());
                        output.accept(HGItems.NETHERITE_HAMMER.get());

                        // ==================== 特殊锤子 ====================
                        output.accept(HGItems.NETHER_STAR_HAMMER.get());
                        output.accept(HGItems.HEART_OF_THE_SEA_HAMMER.get());
                        output.accept(HGItems.CONDUIT_HAMMER.get());
                        output.accept(HGItems.ENDER_PEARL_HAMMER.get());
                        output.accept(HGItems.MAGMA_HAMMER.get());
                        output.accept(HGItems.PISTON_HAMMER.get());
                        output.accept(HGItems.GLASS_HAMMER.get());
                        output.accept(HGItems.SCULK_HAMMER.get());
                        output.accept(HGItems.EMERALD_HAMMER.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
