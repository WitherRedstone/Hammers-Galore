package com.chinaex123.hammers_galore;

import com.chinaex123.hammers_galore.config.ServerConfig;
import com.chinaex123.hammers_galore.item.ModItems;
import com.chinaex123.hammers_galore.server.HammerTickHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(HammersGalore.MOD_ID)
public class HammersGalore {
    // 在公共位置定义模组ID，供所有地方引用
    public static final String MOD_ID = "hammers_galore";

    public HammersGalore(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(HammerTickHandler.class); // 注册锤子 Tick 事件监听

        modContainer.registerConfig(ModConfig.Type.COMMON, ServerConfig.getSpec());

        ModCreativeTabs.register(modEventBus); // 注册自定义创造模式物品栏
        ModItems.register(modEventBus); // 注册物品
    }
}
