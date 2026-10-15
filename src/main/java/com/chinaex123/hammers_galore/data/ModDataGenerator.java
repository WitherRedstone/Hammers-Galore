package com.chinaex123.hammers_galore.data;

import com.chinaex123.hammers_galore.HammersGalore;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;

@EventBusSubscriber(modid = HammersGalore.MOD_ID)
public class ModDataGenerator {

    @SubscribeEvent
    public static void gatherRegistryEntries(GatherDataRegistryEntriesEvent event) {
        event.add(RecipeProvider.asBootstrap(ModRecipesProvider::new));
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {

        event.createProvider(ModModelsProvider::new);
        event.createProvider(ModItemTagsProvider::new);
    }
}