package com.chinaex123.hammers_galore.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class HGToolMaterials {

    // ==================== 基础锤子 ====================
    /** 木锤 */
    public static final ToolMaterial WOOD_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL, 128, 2F, 3.0F, 6, HGItemTags.WOOD_HAMMER_TOOL_MATERIALS);
    /** 石锤 */
    public static final ToolMaterial STONE_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 512, 4F, 4.0F, 10, HGItemTags.STONE_HAMMER_TOOL_MATERIALS);
    /** 铜锤 */
    public static final ToolMaterial COPPER_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL, 512, 5F, 4.0F, 13, HGItemTags.COPPER_HAMMER_TOOL_MATERIALS);
    /** 铁锤 */
    public static final ToolMaterial IRON_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 1024, 6F, 5.0F, 14, HGItemTags.IRON_HAMMER_TOOL_MATERIALS);
    /** 金锤 */
    public static final ToolMaterial GOLD_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_GOLD_TOOL, 256, 12F, 3.0F, 30, HGItemTags.GOLD_HAMMER_TOOL_MATERIALS);
    /** 钻石锤 */
    public static final ToolMaterial DIAMOND_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL, 2048, 8F, 6.0F, 18, HGItemTags.DIAMOND_HAMMER_TOOL_MATERIALS);
    /** 下界合金锤 */
    public static final ToolMaterial NETHERITE_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4096, 10F, 7.0F, 25, HGItemTags.NETHERITE_HAMMER_TOOL_MATERIALS);

    // ==================== 特殊锤子 ====================
    /** 下界之星锤 */
    public static final ToolMaterial NETHER_STAR_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 8192, 14F, 10.0F, 32, HGItemTags.NETHER_STAR_HAMMER_TOOL_MATERIALS);
    /** 海洋之心锤 */
    public static final ToolMaterial HEART_OF_THE_SEA_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2048, 8F, 6.0F, 18, HGItemTags.HEART_OF_THE_SEA_HAMMER_TOOL_MATERIALS);
    /** 潮涌之锤 */
    public static final ToolMaterial CONDUIT_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 6144, 10F, 7.0F, 25, HGItemTags.CONDUIT_TOOL_MATERIALS);
    /** 末影珍珠锤 */
    public static final ToolMaterial ENDER_PEARL_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2048, 8F, 6.0F, 18, HGItemTags.ENDER_PEARL_TOOL_MATERIALS);
    /** 岩浆锤 */
    public static final ToolMaterial MAGMA_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 1024, 6F, 5.0F, 14, HGItemTags.MAGMA_TOOL_MATERIALS);
    /** 活塞锤 */
    public static final ToolMaterial PISTON_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 512, 6F, 5.0F, 14, HGItemTags.PISTON_TOOL_MATERIALS);
    /** 玻璃锤 */
    public static final ToolMaterial GLASS_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 1, 10F, 7.0F, 14, HGItemTags.GLASS_TOOL_MATERIALS);
    /** 幽匿锤 */
    public static final ToolMaterial SCULK_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL, 512, 6F, 5.0F, 14, HGItemTags.SCULK_TOOL_MATERIALS);
    /** 绿宝石锤 */
    public static final ToolMaterial EMERALD_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3096, 8F, 6.0F, 22, HGItemTags.EMERALD_TOOL_MATERIALS);
}
