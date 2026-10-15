package com.chinaex123.hammers_galore.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class HGServerConfig {
    public static final ModConfigSpec SPEC;

    // 木锤
    public static final ModConfigSpec.IntValue WOOD_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue WOOD_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue WOOD_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue WOOD_HAMMER_ENABLE_HUNGER_COST;
    // 石锤
    public static final ModConfigSpec.IntValue STONE_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue STONE_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue STONE_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue STONE_HAMMER_ENABLE_HUNGER_COST;
    // 铜锤
    public static final ModConfigSpec.IntValue COPPER_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue COPPER_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue COPPER_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue COPPER_HAMMER_ENABLE_HUNGER_COST;
    // 铁锤
    public static final ModConfigSpec.IntValue IRON_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue IRON_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue IRON_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue IRON_HAMMER_ENABLE_HUNGER_COST;
    // 金锤
    public static final ModConfigSpec.IntValue GOLD_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue GOLD_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue GOLD_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue GOLD_HAMMER_ENABLE_HUNGER_COST;
    // 钻石锤
    public static final ModConfigSpec.IntValue DIAMOND_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue DIAMOND_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue DIAMOND_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue DIAMOND_HAMMER_ENABLE_HUNGER_COST;
    // 下界合金锤
    public static final ModConfigSpec.IntValue NETHERITE_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue NETHERITE_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue NETHERITE_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue NETHERITE_HAMMER_ENABLE_HUNGER_COST;
    // 下界之星锤
    public static final ModConfigSpec.IntValue NETHER_STAR_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue NETHER_STAR_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue NETHER_STAR_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue NETHER_STAR_HAMMER_ENABLE_HUNGER_COST;
    public static final ModConfigSpec.DoubleValue NETHER_STAR_MAX_ATTACK_BONUS;
    public static final ModConfigSpec.DoubleValue NETHER_STAR_MAX_SPEED_BONUS;
    public static final ModConfigSpec.DoubleValue NETHER_STAR_TRIGGER_THRESHOLD_LOW;
    public static final ModConfigSpec.DoubleValue NETHER_STAR_TRIGGER_THRESHOLD_HIGH;
    // 海洋之心锤
    public static final ModConfigSpec.IntValue HEART_OF_THE_SEA_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue HEART_OF_THE_SEA_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue HEART_OF_THE_SEA_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue HEART_OF_THE_SEA_HAMMER_ENABLE_HUNGER_COST;
    // 潮涌之锤
    public static final ModConfigSpec.IntValue CONDUIT_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue CONDUIT_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue CONDUIT_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue CONDUIT_HAMMER_ENABLE_HUNGER_COST;
    public static final ModConfigSpec.IntValue CONDUIT_EFFECT_DURATION;
    public static final ModConfigSpec.IntValue CONDUIT_EFFECT_AMPLIFIER;
    // 末影锤
    public static final ModConfigSpec.IntValue ENDER_PEARL_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue ENDER_PEARL_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue ENDER_PEARL_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue ENDER_PEARL_HAMMER_ENABLE_HUNGER_COST;
    // 岩浆锤
    public static final ModConfigSpec.IntValue MAGMA_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue MAGMA_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue MAGMA_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue MAGMA_HAMMER_ENABLE_HUNGER_COST;
    // 活塞锤
    public static final ModConfigSpec.IntValue PISTON_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue PISTON_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue PISTON_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue PISTON_HAMMER_ENABLE_HUNGER_COST;
    public static final ModConfigSpec.DoubleValue PISTON_KNOCKBACK_STRENGTH;
    // 玻璃锤
    public static final ModConfigSpec.IntValue GLASS_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue GLASS_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue GLASS_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue GLASS_HAMMER_ENABLE_HUNGER_COST;
    // 幽匿锤
    public static final ModConfigSpec.IntValue SCULK_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue SCULK_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue SCULK_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue SCULK_HAMMER_ENABLE_HUNGER_COST;
    public static final ModConfigSpec.IntValue SCULK_BASE_XP_MIN;
    public static final ModConfigSpec.IntValue SCULK_BASE_XP_MAX;
    public static final ModConfigSpec.DoubleValue SCULK_ORE_XP_MULTIPLIER;
    // 绿宝石锤
    public static final ModConfigSpec.IntValue EMERALD_HAMMER_MINING_RANGE;
    public static final ModConfigSpec.IntValue EMERALD_HAMMER_DURABILITY_COST;
    public static final ModConfigSpec.BooleanValue EMERALD_HAMMER_REQUIRE_SNEAK;
    public static final ModConfigSpec.BooleanValue EMERALD_HAMMER_ENABLE_HUNGER_COST;
    public static final ModConfigSpec.DoubleValue EMERALD_HAMMER_BASE_TRIGGER_CHANCE;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.comment("木锤").push("Wood Hammer");
        WOOD_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        WOOD_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        WOOD_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        WOOD_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        builder.pop();

        builder.comment("石锤").push("Stone Hammer");
        STONE_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        STONE_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        STONE_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        STONE_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        builder.pop();

        builder.comment("铜锤").push("Copper Hammer");
        COPPER_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        COPPER_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        COPPER_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        COPPER_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        builder.pop();

        builder.comment("铁锤").push("Iron Hammer");
        IRON_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        IRON_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        IRON_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        IRON_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        builder.pop();

        builder.comment("金锤").push("Gold Hammer");
        GOLD_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        GOLD_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        GOLD_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        GOLD_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        builder.pop();

        builder.comment("钻石锤").push("Diamond Hammer");
        DIAMOND_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        DIAMOND_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        DIAMOND_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        DIAMOND_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        builder.pop();

        builder.comment("下界合金锤").push("Netherite Hammer");
        NETHERITE_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        NETHERITE_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        NETHERITE_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        NETHERITE_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        builder.pop();

        builder.comment("下界之星锤").push("Nether Star Hammer");
        NETHER_STAR_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        NETHER_STAR_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        NETHER_STAR_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        NETHER_STAR_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        NETHER_STAR_MAX_ATTACK_BONUS = builder
                .comment("当耐久低于低阈值时的最大攻击力加成")
                .comment("Maximum attack damage bonus when durability is below the low threshold.")
                .defineInRange("max_attack_bonus", 3.0, 0.0, Integer.MAX_VALUE);
        NETHER_STAR_MAX_SPEED_BONUS = builder
                .comment("当耐久低于低阈值时的最大挖掘速度加成")
                .comment("Maximum mining speed bonus when durability is below the low threshold.")
                .defineInRange("max_speed_bonus", 3.0, 0.0, Integer.MAX_VALUE);
        NETHER_STAR_TRIGGER_THRESHOLD_LOW = builder
                .comment("开始应用增益的耐久比率（0.3 = 剩余 30% 耐久）")
                .comment("Durability ratio at which the buff starts to apply (0.3 = 30% durability remaining).")
                .defineInRange("trigger_threshold_low", 0.3, 0.0, 1.0);
        NETHER_STAR_TRIGGER_THRESHOLD_HIGH = builder
                .comment("增益达到最大值时的耐久比率（0.1 = 剩余 10% 耐久）")
                .comment("Durability ratio at which the buff reaches maximum (0.1 = 10% durability remaining).")
                .defineInRange("trigger_threshold_high", 0.1, 0.0, 1.0);
        builder.pop();

        builder.comment("海洋之心锤").push("Heart of the Sea Hammer");
        HEART_OF_THE_SEA_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        HEART_OF_THE_SEA_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        HEART_OF_THE_SEA_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        HEART_OF_THE_SEA_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        builder.pop();

        builder.comment("潮涌之锤").push("Conduit Hammer");
        CONDUIT_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        CONDUIT_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        CONDUIT_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        CONDUIT_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        CONDUIT_EFFECT_DURATION = builder
                .comment("潮涌能量效果持续时间（tick）")
                .comment("Conduit Power effect duration in ticks.")
                .defineInRange("effect_duration", 60, 20, Integer.MAX_VALUE);
        CONDUIT_EFFECT_AMPLIFIER = builder
                .comment("潮涌能量效果等级")
                .comment("Conduit Power effect amplifier.")
                .defineInRange("effect_amplifier", 0, 0, 255);
        builder.pop();

        builder.comment("末影锤").push("Ender Pearl Hammer");
        ENDER_PEARL_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        ENDER_PEARL_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        ENDER_PEARL_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        ENDER_PEARL_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        builder.pop();

        builder.comment("岩浆锤").push("Magma Hammer");
        MAGMA_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        MAGMA_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        MAGMA_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        MAGMA_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        builder.pop();

        builder.comment("活塞锤").push("Piston Hammer");
        PISTON_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        PISTON_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        PISTON_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        PISTON_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        PISTON_KNOCKBACK_STRENGTH = builder
                .comment("活塞锤击退强度（格）")
                .comment("Piston Hammer knockback strength (blocks).")
                .defineInRange("knockback_strength", 3.0, 0.0, 32.0);
        builder.pop();

        builder.comment("玻璃锤").push("Glass Hammer");
        GLASS_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        GLASS_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        GLASS_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        GLASS_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        builder.pop();

        builder.comment("幽匿锤").push("Sculk Hammer");
        SCULK_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        SCULK_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        SCULK_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        SCULK_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        SCULK_BASE_XP_MIN = builder
                .comment("每个物品的最小基础经验")
                .comment("Minimum base experience per item.")
                .defineInRange("sculk_base_xp_min", 1, 0, Integer.MAX_VALUE);
        SCULK_BASE_XP_MAX = builder
                .comment("每个物品的最大基础经验")
                .comment("Maximum base experience per item.")
                .defineInRange("sculk_base_xp_max", 3, 0, Integer.MAX_VALUE);
        SCULK_ORE_XP_MULTIPLIER = builder
                .comment("矿石方块的经验倍数")
                .comment("Experience multiplier for ore blocks.")
                .defineInRange("sculk_ore_xp_multiplier", 2.5, 1, Integer.MAX_VALUE);
        builder.pop();

        builder.comment("绿宝石锤").push("Emerald Hammer");
        EMERALD_HAMMER_MINING_RANGE = builder
                .comment("挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.")
                .defineInRange("mining_range", 3, 1, 9);
        EMERALD_HAMMER_DURABILITY_COST = builder
                .comment("每个额外方块消耗的耐久")
                .comment("Durability consumed per additional block")
                .defineInRange("durability_cost", 1, 1, Integer.MAX_VALUE);
        EMERALD_HAMMER_REQUIRE_SNEAK = builder
                .comment("是否需要潜行才能范围挖掘")
                .comment("Whether sneaking is required for area mining")
                .define("require_sneak", false);
        EMERALD_HAMMER_ENABLE_HUNGER_COST = builder
                .comment("进行范围挖掘时是否消耗额外的饱食度")
                .comment("Whether area mining consumes extra hunger/saturation.")
                .define("enable_hunger_cost", false);
        EMERALD_HAMMER_BASE_TRIGGER_CHANCE = builder
                .comment("触发幸运效果的基础概率（%）")
                .comment("Base chance to trigger the lucky effect.")
                .defineInRange("base_trigger_chance", 0.25, 0.0, 1.0);
        builder.pop();

        SPEC = builder.build();
    }
}