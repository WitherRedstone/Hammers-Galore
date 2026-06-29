package com.chinaex123.hammers_galore;

import com.chinaex123.hammers_galore.config.ServerConfig;
import com.chinaex123.hammers_galore.init.ModCreativeTabs;
import com.chinaex123.hammers_galore.init.ModItems;
import com.chinaex123.hammers_galore.item.HammerTickHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(HammersGalore.MOD_ID)
public class HammersGalore {
    public static final String MOD_ID = "hammers_galore";

    public HammersGalore(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(HammerTickHandler.class);

        modContainer.registerConfig(ModConfig.Type.COMMON, ServerConfig.getSpec());

        ModCreativeTabs.register(modEventBus);
        ModItems.register(modEventBus);
    }
}
