package com.chinaex123.hammers_galore.server;

import com.chinaex123.hammers_galore.server.specialHammer.ConduitHammer;
import com.chinaex123.hammers_galore.server.specialHammer.HeartOfTheSeaHammer;
import com.chinaex123.hammers_galore.server.specialHammer.NetherStarHammer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = "hammers_galore")
public class HammerTickHandler {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();

        // 检查主手物品
        ItemStack mainHandItem = player.getMainHandItem();

        // 如果是下界之星锤，更新增益
        if (mainHandItem.getItem() instanceof NetherStarHammer netherStarHammer) {
            netherStarHammer.updateBonusFromTick(mainHandItem, player);
        } else {
            // 不在主手，移除增益（如果有）
            NetherStarHammer.removeBonusStatic(player);
        }

        // 如果是海洋之心锤，更新水中挖掘速度
        HeartOfTheSeaHammer.onPlayerTick(player);

        // 如果是潮涌之锤，应用潮涌能量效果
        ConduitHammer.onPlayerTick(player);
    }
}
