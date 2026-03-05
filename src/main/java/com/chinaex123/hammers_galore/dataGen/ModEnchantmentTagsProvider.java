package com.chinaex123.hammers_galore.dataGen;

import com.chinaex123.hammers_galore.HammersGalore;
import com.chinaex123.hammers_galore.tags.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagsProvider extends TagsProvider<Enchantment> {
    public ModEnchantmentTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, Registries.ENCHANTMENT, lookupProvider, HammersGalore.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModItemTags.HAMMER_ENCHANTMENTS)
                .add(
                        Enchantments.SHARPNESS,           // 锋利

                        Enchantments.EFFICIENCY,           // 效率
                        Enchantments.UNBREAKING,           // 耐久
                        Enchantments.FORTUNE,               // 时运
                        Enchantments.SILK_TOUCH,            // 精准采集
                        Enchantments.MENDING                 // 经验修补
                );
    }
}