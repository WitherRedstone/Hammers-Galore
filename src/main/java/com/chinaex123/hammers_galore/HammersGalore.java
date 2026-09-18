package com.chinaex123.hammers_galore;

import com.chinaex123.hammers_galore.config.HGServerConfig;
import com.chinaex123.hammers_galore.init.HGCreativeTabs;
import com.chinaex123.hammers_galore.init.HGItems;
import com.chinaex123.hammers_galore.item.HammerTickHandler;
import net.minecraft.resources.Identifier;
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

        modContainer.registerConfig(ModConfig.Type.COMMON, HGServerConfig.SPEC);

        HGCreativeTabs.register(modEventBus);
        HGItems.register(modEventBus);
    }

    public static Identifier id(String name) {
        return Identifier.tryBuild(HammersGalore.MOD_ID, name);
    }
}
