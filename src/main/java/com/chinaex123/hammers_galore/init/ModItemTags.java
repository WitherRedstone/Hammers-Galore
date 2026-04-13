package com.chinaex123.hammers_galore.init;

import com.chinaex123.hammers_galore.HammersGalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public interface ModItemTags {

    TagKey<Item> HAMMERS = bind("hammers");

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(HammersGalore.MOD_ID, name));
    }
}
