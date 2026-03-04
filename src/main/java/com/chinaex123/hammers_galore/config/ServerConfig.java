package com.chinaex123.hammers_galore.config;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = "hammers_galore")
public class ServerConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // 下界之星锤特殊配置
    public static final ModConfigSpec.DoubleValue NETHER_STAR_MAX_ATTACK_BONUS;
    public static final ModConfigSpec.DoubleValue NETHER_STAR_MAX_SPEED_BONUS;
    public static final ModConfigSpec.DoubleValue NETHER_STAR_TRIGGER_THRESHOLD_LOW;
    public static final ModConfigSpec.DoubleValue NETHER_STAR_TRIGGER_THRESHOLD_HIGH;

    // 潮涌之锤特殊配置
    public static final ModConfigSpec.IntValue CONDUIT_EFFECT_DURATION;
    public static final ModConfigSpec.IntValue CONDUIT_EFFECT_AMPLIFIER;

    // 活塞锤特殊配置
    public static final ModConfigSpec.DoubleValue PISTON_KNOCKBACK_STRENGTH;

    // 幽匿锤特殊配置
    public static final ModConfigSpec.IntValue SCULK_BASE_XP_MIN;
    public static final ModConfigSpec.IntValue SCULK_BASE_XP_MAX;
    public static final ModConfigSpec.IntValue SCULK_ORE_XP_MULTIPLIER;
    // 绿宝石特殊配置
    public static final ModConfigSpec.DoubleValue EMERALD_HAMMER_BASE_TRIGGER_CHANCE;

    private static ModConfigSpec spec;

    // 使用 Map 存储每个锤子的通用配置
    private static final Map<String, HammerConfig> hammerConfigs = new HashMap<>();

    // 缓存的下界之星锤特殊配置
    private static double cachedNetherStarAttackBonus;
    private static double cachedNetherStarSpeedBonus;
    private static double cachedNetherStarThresholdLow;
    private static double cachedNetherStarThresholdHigh;
    // 缓存的潮涌之锤配置
    private static int cachedConduitDuration;
    private static int cachedConduitAmplifier;
    // 缓存的活塞锤配置
    private static double cachedPistonKnockback;
    // 缓存的幽匿锤配置
    private static int cachedSculkBaseXPMin;
    private static int cachedSculkBaseXPMax;
    private static int cachedSculkOreXPMultiplier;
    // 缓存的绿宝石锤配置
    private static double cachedEmeraldHammerBaseTriggerChance;

    // 静态类存储单个锤子的通用配置
    private static class HammerConfig {
        public final ModConfigSpec.IntValue miningRange;
        public final ModConfigSpec.IntValue durabilityCost;
        public final ModConfigSpec.BooleanValue requireSneak;
        public final ModConfigSpec.BooleanValue enableHungerCost;

        public int cachedRange;
        public int cachedDurabilityCost;
        public boolean cachedRequireSneak;
        public boolean cachedEnableHungerCost;

        HammerConfig(String tierName) {
            miningRange = BUILDER
                    .comment("Hammer mining range (3x3, 5x5, 7x7, or 9x9). Set to 1 to disable.",
                            "挖掘范围 (3x3, 5x5, 7x7, 或 9x9)。设置为 1 禁用。")
                    .defineInRange("mining_range", 3, 1, 9);

            durabilityCost = BUILDER
                    .comment("Hammer durability cost per extra block.",
                            "每个额外方块消耗的耐久")
                    .defineInRange("durability_cost", 1, 1, 10);

            requireSneak = BUILDER
                    .comment("Require sneak for area mining.",
                            "使用此锤子进行范围挖掘时是否需要潜行")
                    .define("require_sneak", true);

            enableHungerCost = BUILDER
                    .comment("Consume additional saturation when mining multiple blocks.",
                            "使用此锤子进行范围挖掘时是否消耗额外的饱食度")
                    .define("enable_hunger_cost", false);
        }
    }

    // 静态初始化块
    static {
        BUILDER.push("锤子多多");

        // 基础锤子
        registerHammerInternal("wood_hammer");
        registerHammerInternal("stone_hammer");
        registerHammerInternal("iron_hammer");
        registerHammerInternal("gold_hammer");
        registerHammerInternal("diamond_hammer");
        registerHammerInternal("netherite_hammer");

        // 特殊锤子 - 想加新锤子直接在这里添加一行即可
        registerHammerInternal("nether_star_hammer");
        registerHammerInternal("heart_of_the_sea_hammer");
        registerHammerInternal("conduit_hammer");
        registerHammerInternal("ender_pearl_hammer");
        registerHammerInternal("magma_hammer");
        registerHammerInternal("piston_hammer");
        registerHammerInternal("glass_hammer");
        registerHammerInternal("sculk_hammer");
        registerHammerInternal("emerald_hammer");

        // 下界之星锤特殊配置
        BUILDER.push("特殊锤子功能配置");
        
        BUILDER.push("[Features]nether_star_hammer");
        NETHER_STAR_MAX_ATTACK_BONUS = BUILDER
                .comment("Maximum attack damage bonus when durability is below low threshold.",
                        "当耐久低于低阈值时的最大攻击力加成")
                .defineInRange("max_attack_bonus", 3.0, 0.0, 100.0);
        NETHER_STAR_MAX_SPEED_BONUS = BUILDER
                .comment("Maximum mining speed bonus when durability is below low threshold.",
                        "当耐久低于低阈值时的最大挖掘速度加成")
                .defineInRange("max_speed_bonus", 3.0, 0.0, 100.0);
        NETHER_STAR_TRIGGER_THRESHOLD_LOW = BUILDER
                .comment("Durability ratio (0.0-1.0) at which bonus starts to apply. 0.3 = 30% durability remaining.",
                        "开始应用增益的耐久比率。0.3 = 剩余 30% 耐久")
                .defineInRange("trigger_threshold_low", 0.3, 0.0, 1.0);
        NETHER_STAR_TRIGGER_THRESHOLD_HIGH = BUILDER
                .comment("Durability ratio (0.0-1.0) at which bonus reaches maximum. 0.1 = 10% durability remaining.",
                        "增益达到最大值时的耐久比率。0.1 = 剩余 10% 耐久")
                .defineInRange("trigger_threshold_high", 0.1, 0.0, 1.0);
        BUILDER.pop();

        // 潮涌之锤配置
        BUILDER.push("[Features]conduit_hammer");
        CONDUIT_EFFECT_DURATION = BUILDER
                .comment("Conduit Power effect duration in ticks (20 ticks = 1 second). Default: 50 (2.5 seconds)",
                        "潮涌能量效果持续时间（tick）。20 tick = 1 秒。默认：50（2.5 秒）")
                .defineInRange("effect_duration", 50, 20, 600);
        CONDUIT_EFFECT_AMPLIFIER = BUILDER
                .comment("Conduit Power effect amplifier (0 = Level I, 1 = Level II, etc.). Default: 0",
                        "潮涌能量效果等级（0 = I 级，1 = II 级，以此类推）。默认：0")
                .defineInRange("effect_amplifier", 0, 0, 10);
        BUILDER.pop();

        // 活塞锤配置
        BUILDER.push("[Features]piston_hammer");
        PISTON_KNOCKBACK_STRENGTH = BUILDER
                .comment("Piston Hammer knockback strength. Default: 3.0 (blocks)",
                        "活塞锤击退强度。默认：3.0（格）")
                .defineInRange("knockback_strength", 3.0, 0.0, 32.0);
        BUILDER.pop();

        // 幽匿锤特殊配置
        BUILDER.push("[Features]sculk_hammer_special");
        SCULK_BASE_XP_MIN = BUILDER
                .comment("Sculk hammer minimum base XP per item (default: 1).",
                        "幽匿锤每个物品的最小基础经验（默认：1）")
                .defineInRange("sculk_base_xp_min", 1, 0, 100);

        SCULK_BASE_XP_MAX = BUILDER
                .comment("Sculk hammer maximum base XP per item (default: 3).",
                        "幽匿锤每个物品的最大基础经验（默认：3）")
                .defineInRange("sculk_base_xp_max", 3, 0, 100);

        SCULK_ORE_XP_MULTIPLIER = BUILDER
                .comment("Sculk hammer XP multiplier for ore blocks (default: 2).",
                        "幽匿锤矿石方块的经验倍数（默认：2）")
                .defineInRange("sculk_ore_xp_multiplier", 2, 1, 10);
        BUILDER.pop();

        // 绿宝石锤配置
        BUILDER.push("[Features]emerald_hammer");
        EMERALD_HAMMER_BASE_TRIGGER_CHANCE = BUILDER
                .comment("Emerald hammer base trigger chance for luck effect (default: 0.2 = 20%).",
                        "绿宝石锤触发幸运效果的基础概率（默认：0.2 = 20%）。",
                        "实际触发概率 = 基础概率 + 耐久度比例 * (1 - 基础概率)",
                        "耐久度越低，触发概率越高")
                .defineInRange("base_trigger_chance", 0.25, 0.0, 1.0);
        BUILDER.pop();
        
        
        
        BUILDER.pop();

        BUILDER.pop();
    }

    /**
     * 内部方法：注册单个锤子的配置到配置系统中
     *
     * @param hammerName 锤子的注册名称（如 "wood_hammer", "nether_star_hammer" 等）
     */
    private static void registerHammerInternal(String hammerName) {
        // 将配置构建器推入当前锤子的配置节点
        BUILDER.push(hammerName);
        // 创建新的锤子配置对象并存入 Map 中
        hammerConfigs.put(hammerName, new HammerConfig(hammerName));
        // 弹出配置节点，返回到上一级
        BUILDER.pop();
    }

    /**
     * 根据锤子名称获取挖掘半径
     *
     * @param tierName 锤子的注册名称（如 "wood_hammer", "diamond_hammer" 等）
     * @return 挖掘半径值。如果配置中范围 ≤ 1 则返回 0（禁用范围挖掘），否则返回 (范围 -1)/2；
     *         如果未找到对应锤子配置，则返回默认值 3
     */
    public static int getMiningRadius(String tierName) {
        // 从配置 Map 中获取对应锤子的配置对象
        HammerConfig config = hammerConfigs.get(tierName);
        if (config != null) {
            // 获取缓存的挖掘范围值
            int range = config.cachedRange;
            // 如果范围 ≤ 1，表示禁用范围挖掘，返回半径 0
            if (range <= 1) return 0;
            // 将范围转换为半径：3x3→1, 5x5→2, 7x7→3, 9x9→4
            return (range - 1) / 2;
        }
        return 3;
    }

    /**
     * 根据锤子名称获取挖掘范围大小
     *
     * @param tierName 锤子的注册名称（如 "wood_hammer", "diamond_hammer" 等）
     * @return 挖掘范围值（3、5、7、9 等），表示挖掘区域为该值 x 该值的正方形；
     *         如果未找到对应锤子配置，则返回默认值 3
     */
    public static int getMiningRange(String tierName) {
        // 从配置 Map 中获取对应锤子的配置对象
        HammerConfig config = hammerConfigs.get(tierName);
        if (config != null) {
            // 返回缓存的挖掘范围值
            return config.cachedRange;
        }
        return 3;
    }

    /**
     * 根据锤子名称获取耐久消耗值
     *
     * @param tierName 锤子的注册名称（如 "wood_hammer", "diamond_hammer" 等）
     * @return 每个额外方块消耗的耐久值；如果未找到对应锤子配置，则返回默认值 1
     */
    public static int getDurabilityCost(String tierName) {
        // 从配置 Map 中获取对应锤子的配置对象
        HammerConfig config = hammerConfigs.get(tierName);
        if (config != null) {
            // 返回缓存的耐久消耗值
            return config.cachedDurabilityCost;
        }
        return 1;
    }

    /**
     * 根据锤子名称检查是否需要潜行才能进行范围挖掘
     *
     * @param tierName 锤子的注册名称（如 "wood_hammer", "diamond_hammer" 等）
     * @return 如果需要潜行则返回 true，否则返回 false；如果未找到对应锤子配置，则返回默认值 true
     */
    public static boolean requireSneak(String tierName) {
        // 从配置 Map 中获取对应锤子的配置对象
        HammerConfig config = hammerConfigs.get(tierName);
        if (config != null) {
            // 返回缓存的潜行要求值
            return config.cachedRequireSneak;
        }
        return true;
    }

    /**
     * 根据锤子名称检查是否启用饱食度消耗
     *
     * @param tierName 锤子的注册名称（如 "wood_hammer", "diamond_hammer" 等）
     * @return 如果启用饱食度消耗则返回 true，否则返回 false；如果未找到对应锤子配置，则返回默认值 false
     */
    public static boolean enableHungerCost(String tierName) {
        // 从配置 Map 中获取对应锤子的配置对象
        HammerConfig config = hammerConfigs.get(tierName);
        if (config != null) {
            // 返回缓存的饱食度消耗启用状态
            return config.cachedEnableHungerCost;
        }
        return false;
    }

    /**
     * 获取下界之星锤的最大攻击力加成
     */
    public static double getNetherStarAttackBonus() {
        return cachedNetherStarAttackBonus;
    }

    /**
     * 获取下界之星锤的最大挖掘速度加成
     */
    public static double getNetherStarSpeedBonus() {
        return cachedNetherStarSpeedBonus;
    }

    /**
     * 获取下界之星锤的低阈值
     */
    public static double getNetherStarThresholdLow() {
        return cachedNetherStarThresholdLow;
    }

    /**
     * 获取下界之星锤的高阈值
     */
    public static double getNetherStarThresholdHigh() {
        return cachedNetherStarThresholdHigh;
    }

    /**
     * 获取潮涌之锤的效果持续时间
     */
    public static int getConduitEffectDuration() {
        return cachedConduitDuration;
    }

    /**
     * 获取潮涌之锤的效果等级
     */
    public static int getConduitEffectAmplifier() {
        return cachedConduitAmplifier;
    }

    /**
     * 获取活塞锤的击退强度
     */
    public static double getPistonKnockbackStrength() {
        return cachedPistonKnockback;
    }

    /**
     * 获取幽匿锤最小基础经验
     */
    public static int getSculkBaseXPMin() {
        return cachedSculkBaseXPMin;
    }

    /**
     * 获取幽匿锤最大基础经验
     */
    public static int getSculkBaseXPMax() {
        return cachedSculkBaseXPMax;
    }

    /**
     * 获取幽匿锤矿石经验倍数
     */
    public static int getSculkOreXPMultiplier() {
        return cachedSculkOreXPMultiplier;
    }

    /**
     * 获取绿宝石锤的基础触发概率
     *
     * @return 基础触发概率（0.0-1.0）
     */
    public static double getEmeraldHammerBaseTriggerChance() {
        return cachedEmeraldHammerBaseTriggerChance;
    }

    /**
     * 验证挖掘范围值是否有效
     * 有效的范围为：1（禁用）或 3-9 之间的奇数（3x3, 5x5, 7x7, 9x9）
     *
     * @param value 要验证的挖掘范围值
     * @return 如果值有效返回 true，否则返回 false
     */
    public static boolean isValidRange(int value) {
        // 检查是否为 1（禁用）或 3-9 之间的奇数
        return value == 1 || (value >= 3 && value <= 9 && value % 2 == 1);
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent.Loading event) {
        updateCache();
    }

    @SubscribeEvent
    static void onReload(final ModConfigEvent.Reloading event) {
        updateCache();
    }


    /**
     * 更新所有锤子配置的缓存值
     * 在配置加载或重新加载时调用，以提高运行时性能
     */
    private static void updateCache() {
        // 遍历所有锤子配置对象，更新其缓存值
        for (HammerConfig config : hammerConfigs.values()) {
            // 从配置对象读取实际值
            config.cachedRange = config.miningRange.get();
            config.cachedDurabilityCost = config.durabilityCost.get();
            config.cachedRequireSneak = config.requireSneak.get();
            config.cachedEnableHungerCost = config.enableHungerCost.get();
        }

        // 更新下界之星锤的配置缓存
        cachedNetherStarAttackBonus = NETHER_STAR_MAX_ATTACK_BONUS.get();
        cachedNetherStarSpeedBonus = NETHER_STAR_MAX_SPEED_BONUS.get();
        cachedNetherStarThresholdLow = NETHER_STAR_TRIGGER_THRESHOLD_LOW.get();
        cachedNetherStarThresholdHigh = NETHER_STAR_TRIGGER_THRESHOLD_HIGH.get();
        // 更新潮涌之锤配置
        cachedConduitDuration = CONDUIT_EFFECT_DURATION.get();
        cachedConduitAmplifier = CONDUIT_EFFECT_AMPLIFIER.get();
        // 更新活塞锤配置
        cachedPistonKnockback = PISTON_KNOCKBACK_STRENGTH.get();
        // 更新幽匿锤配置
        cachedSculkBaseXPMin = SCULK_BASE_XP_MIN.get();
        cachedSculkBaseXPMax = SCULK_BASE_XP_MAX.get();
        cachedSculkOreXPMultiplier = SCULK_ORE_XP_MULTIPLIER.get();
        // 更新绿宝石锤配置
        cachedEmeraldHammerBaseTriggerChance = EMERALD_HAMMER_BASE_TRIGGER_CHANCE.get();
    }

    public static ModConfigSpec getSpec() {
        if (spec == null) {
            spec = BUILDER.build();
        }
        return spec;
    }
}
