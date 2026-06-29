package com.chinaex123.hammers_galore.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterials {

    // 参数: 不能有效挖掘的方块标签，耐久，速度，伤害，附魔值，维修材料
    // ==================== 基础锤子 ====================
    // 木锤
    public static final ToolMaterial WOOD_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL, 128, 2F, 3.0F, 6, ModItemTags.WOOD_HAMMER_TOOL_MATERIALS);
    // 石锤
    public static final ToolMaterial STONE_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 512, 4F, 4.0F, 10, ModItemTags.STONE_HAMMER_TOOL_MATERIALS);
    // 铜锤
    public static final ToolMaterial COPPER_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 512, 5F, 4.0F, 13, ModItemTags.COPPER_HAMMER_TOOL_MATERIALS);
    // 铁锤
    public static final ToolMaterial IRON_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 1024, 6F, 5.0F, 14, ModItemTags.IRON_HAMMER_TOOL_MATERIALS);
    // 金锤
    public static final ToolMaterial GOLD_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_GOLD_TOOL, 256, 12F, 3.0F, 30, ModItemTags.GOLD_HAMMER_TOOL_MATERIALS);
    // 钻石锤
    public static final ToolMaterial DIAMOND_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL, 2048, 8F, 6.0F, 18, ModItemTags.DIAMOND_HAMMER_TOOL_MATERIALS);
    // 下界合金锤
    public static final ToolMaterial NETHERITE_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4096, 10F, 7.0F, 25, ModItemTags.NETHERITE_HAMMER_TOOL_MATERIALS);

    // ==================== 特殊锤子 ====================
    // 下界之星锤
    public static final ToolMaterial NETHER_STAR_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 8192, 14F, 10.0F, 32, ModItemTags.NETHER_STAR_HAMMER_TOOL_MATERIALS);
    // 海洋之心锤
    public static final ToolMaterial HEART_OF_THE_SEA_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2048, 8F, 6.0F, 18, ModItemTags.HEART_OF_THE_SEA_HAMMER_TOOL_MATERIALS);
    // 潮涌之锤
    public static final ToolMaterial CONDUIT_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 6144, 10F, 7.0F, 25, ModItemTags.CONDUIT_TOOL_MATERIALS);
    // 末影锤
    public static final ToolMaterial ENDER_PEARL_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2048, 8F, 6.0F, 18, ModItemTags.ENDER_PEARL_TOOL_MATERIALS);
    // 岩浆锤
    public static final ToolMaterial MAGMA_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 1024, 6F, 5.0F, 14, ModItemTags.MAGMA_TOOL_MATERIALS);
    // 活塞锤
    public static final ToolMaterial PISTON_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 512, 6F, 5.0F, 14, ModItemTags.PISTON_TOOL_MATERIALS);
    // 玻璃锤
    public static final ToolMaterial GLASS_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 1, 10F, 7.0F, 14, ModItemTags.GLASS_TOOL_MATERIALS);
    // 幽匿锤
    public static final ToolMaterial SCULK_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 512, 6F, 5.0F, 14, ModItemTags.SCULK_TOOL_MATERIALS);
    // 绿宝石锤
    public static final ToolMaterial EMERALD_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3096, 8F, 6.0F, 22, ModItemTags.EMERALD_TOOL_MATERIALS);
}
