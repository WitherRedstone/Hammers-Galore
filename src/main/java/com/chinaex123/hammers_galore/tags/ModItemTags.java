package com.chinaex123.hammers_galore.tags;

import com.chinaex123.hammers_galore.HammersGalore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModItemTags {

    public static final TagKey<Item> HAMMERS = bind("hammers");
    public static final TagKey<Enchantment> HAMMER_ENCHANTMENTS =
            TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(HammersGalore.MOD_ID, "hammer_enchantments"));
    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(HammersGalore.MOD_ID, name));
    }
}
