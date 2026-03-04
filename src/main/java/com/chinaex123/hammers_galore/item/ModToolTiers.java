package com.chinaex123.hammers_galore.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

import java.util.function.Supplier;

public enum ModToolTiers implements Tier {

    // 参数: 不能有效挖掘的方块标签，耐久，速度，伤害，附魔值，维修材料

    // ==================== 基础锤子 ====================
    // 木锤
    WOOD_HAMMER(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 128, 2F, 3.0F, 6, () -> Ingredient.of(ItemTags.PLANKS)),
    // 石锤
    STONE_HAMMER(BlockTags.INCORRECT_FOR_STONE_TOOL, 512, 4F, 4.0F, 10, () -> Ingredient.of(Items.COBBLESTONE)),
    // 铜锤
    COPPER_HAMMER(BlockTags.INCORRECT_FOR_STONE_TOOL, 512, 5F, 4.0F, 13, () -> Ingredient.of(Items.COBBLESTONE)),
    // 铁锤
    IRON_HAMMER(BlockTags.INCORRECT_FOR_IRON_TOOL, 1024, 6F, 5.0F, 14, () -> Ingredient.of(Tags.Items.INGOTS_IRON)),
    // 金锤
    GOLD_HAMMER(BlockTags.INCORRECT_FOR_GOLD_TOOL, 256, 12F, 3.0F, 30, () -> Ingredient.of(Tags.Items.INGOTS_GOLD)),
    // 钻石锤
    DIAMOND_HAMMER(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2048, 8F, 6.0F, 18, () -> Ingredient.of(Tags.Items.GEMS_DIAMOND)),
    // 下界合金锤
    NETHERITE_HAMMER(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4096, 10F, 7.0F, 25, () -> Ingredient.of(Tags.Items.INGOTS_NETHERITE)),

    // ==================== 特殊锤子 ====================
    // 下界之星锤
    NETHER_STAR_HAMMER(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 8192, 14F, 10.0F, 32, () -> Ingredient.of(Tags.Items.NETHER_STARS)),
    // 海洋之心锤
    HEART_OF_THE_SEA_HAMMER(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2048, 8F, 6.0F, 18, () -> Ingredient.of(Items.HEART_OF_THE_SEA)),
    // 潮涌之锤
    CONDUIT_HAMMER(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 6144, 10F, 7.0F, 25, () -> Ingredient.of(Items.CONDUIT)),
    // 末影锤
    ENDER_PEARL_HAMMER(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2048, 8F, 6.0F, 18, () -> Ingredient.of(Tags.Items.ENDER_PEARLS)),
    // 岩浆锤
    MAGMA_HAMMER(BlockTags.INCORRECT_FOR_IRON_TOOL, 1024, 6F, 5.0F, 14, () -> Ingredient.of(Items.MAGMA_BLOCK)),
    // 活塞锤
    PISTON_HAMMER(BlockTags.INCORRECT_FOR_IRON_TOOL, 512, 6F, 5.0F, 14, () -> Ingredient.of(Items.PISTON)),
    // 玻璃锤
    GLASS_HAMMER(BlockTags.INCORRECT_FOR_IRON_TOOL, 1, 10F, 7.0F, 14, () -> Ingredient.of(Tags.Items.GLASS_BLOCKS)),
    // 幽匿锤
    SCULK_HAMMER(BlockTags.INCORRECT_FOR_IRON_TOOL, 512, 6F, 5.0F, 14, () -> Ingredient.of(Items.SCULK)),
    // 绿宝石锤
    EMERALD_HAMMER(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3096, 8F, 6.0F, 22, () -> Ingredient.of(Tags.Items.GEMS_EMERALD));


    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    ModToolTiers(TagKey<Block> incorrectBlocksForDrops, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
