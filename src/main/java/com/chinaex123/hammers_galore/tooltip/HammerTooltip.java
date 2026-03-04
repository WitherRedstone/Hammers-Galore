package com.chinaex123.hammers_galore.tooltip;

import com.chinaex123.hammers_galore.config.ServerConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@EventBusSubscriber(modid = "hammers_galore")
public class HammerTooltip {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack itemStack = event.getItemStack();
        List<Component> tooltip = event.getToolTip();
        TooltipFlag flag = event.getFlags();

        // 获取物品注册名
        String itemName = itemStack.getItem().getDescriptionId()
                .replace("item.hammers_galore.", "");

        // 检查是否是锤子
        if (!itemName.endsWith("_hammer")) {
            return;
        }


        // 添加空行分隔
        tooltip.add(Component.literal(""));

        int miningLevel = getMiningLevel(itemStack);
        tooltip.add(Component.translatable("tooltip.hammers_galore.mining_level",
                        getMiningLevelName(miningLevel))
                .withStyle(ChatFormatting.GOLD));

        // 显示挖掘范围
        int miningRange = ServerConfig.getMiningRange(itemName);
        if (miningRange <= 1) {
            tooltip.add(Component.translatable("tooltip.hammers_galore.mining_range.disabled")
                    .withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.add(Component.translatable("tooltip.hammers_galore.mining_range.value", miningRange, miningRange)
                    .withStyle(ChatFormatting.GREEN));
        }

        // 显示是否需要潜行
        boolean requireSneak = ServerConfig.requireSneak(itemName);
        if (requireSneak) {
            tooltip.add(Component.translatable("tooltip.hammers_galore.require_sneak.yes")
                    .withStyle(ChatFormatting.YELLOW));
        } else {
            tooltip.add(Component.translatable("tooltip.hammers_galore.require_sneak.no")
                    .withStyle(ChatFormatting.DARK_GREEN));
        }

        // 显示耐久消耗
        int durabilityCost = ServerConfig.getDurabilityCost(itemName);
        tooltip.add(Component.translatable("tooltip.hammers_galore.durability_cost", durabilityCost)
                .withStyle(ChatFormatting.BLUE));

        // 显示是否消耗饱食度
        boolean enableHungerCost = ServerConfig.enableHungerCost(itemName);
        if (enableHungerCost) {
            tooltip.add(Component.translatable("tooltip.hammers_galore.hunger_cost.yes")
                    .withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(Component.translatable("tooltip.hammers_galore.hunger_cost.no")
                    .withStyle(ChatFormatting.GREEN));
        }

        // ==================== 特殊锤子 ====================
        // 下界之星锤特殊提示
        if ("nether_star_hammer".equals(itemName)) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.hammers_galore.specialhammers.bonus")
                    .withStyle(ChatFormatting.DARK_PURPLE));

            double attackBonus = ServerConfig.getNetherStarAttackBonus();
            double speedBonus = ServerConfig.getNetherStarSpeedBonus();
            double thresholdLow = ServerConfig.getNetherStarThresholdLow();
            double thresholdHigh = ServerConfig.getNetherStarThresholdHigh();

            // 转换为剩余耐久百分比
            int startPercent = (int) (thresholdLow * 100);     // 30% 剩余
            int maxPercent = (int) (thresholdHigh * 100);      // 10% 剩余

            tooltip.add(Component.translatable("tooltip.hammers_galore.nether_star.attack_bonus", attackBonus)
                    .withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("tooltip.hammers_galore.nether_star.speed_bonus", speedBonus)
                    .withStyle(ChatFormatting.AQUA));
            tooltip.add(Component.translatable("tooltip.hammers_galore.nether_star.threshold", startPercent, maxPercent)
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
        }

        // 海洋之心锤特殊提示
        if ("heart_of_the_sea_hammer".equals(itemName)) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.hammers_galore.specialhammers.bonus")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("tooltip.hammers_galore.heart_of_the_sea.water_mining")
                    .withStyle(ChatFormatting.AQUA));
        }

        // 潮涌之锤锤特殊提示
        if ("conduit_hammer".equals(itemName)) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.hammers_galore.specialhammers.bonus")
                    .withStyle(ChatFormatting.DARK_PURPLE));

            int duration = ServerConfig.getConduitEffectDuration();
            int amplifier = ServerConfig.getConduitEffectAmplifier();
            double durationSeconds = duration / 20.0;

            tooltip.add(Component.translatable("tooltip.hammers_galore.conduit_hammer.water_power",
                            amplifier + 1, String.format("%.1f", durationSeconds))
                    .withStyle(ChatFormatting.AQUA));
        }

        // 末影锤特殊提示
        if ("ender_pearl_hammer".equals(itemName)) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.hammers_galore.specialhammers.bonus")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("tooltip.hammers_galore.ender_pearl.auto_collect")
                    .withStyle(ChatFormatting.AQUA));
        }

        // 岩浆锤特殊提示
        if ("magma_hammer".equals(itemName)) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.hammers_galore.specialhammers.bonus")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("tooltip.hammers_galore.magma.auto_smelt")
                    .withStyle(ChatFormatting.AQUA));
        }

        // 活塞锤特殊提示
        if ("piston_hammer".equals(itemName)) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.hammers_galore.specialhammers.bonus")
                    .withStyle(ChatFormatting.DARK_PURPLE));

            double knockback = ServerConfig.getPistonKnockbackStrength();
            tooltip.add(Component.translatable("tooltip.hammers_galore.piston.knockback",
                            String.format("%.1f", knockback))
                    .withStyle(ChatFormatting.AQUA));
        }

        // 玻璃锤特殊提示
        if ("glass_hammer".equals(itemName)) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.hammers_galore.specialhammers.bonus")
                    .withStyle(ChatFormatting.DARK_PURPLE));
            tooltip.add(Component.translatable("tooltip.hammers_galore.glass.low_durability")
                    .withStyle(ChatFormatting.AQUA));
        }

        // 幽匿锤特殊提示
        if ("sculk_hammer".equals(itemName)) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.hammers_galore.specialhammers.bonus")
                    .withStyle(ChatFormatting.DARK_PURPLE));

            int minXp = ServerConfig.getSculkBaseXPMin();
            int maxXp = ServerConfig.getSculkBaseXPMax();
            int oreMultiplier = ServerConfig.getSculkOreXPMultiplier();

            String xpRange = (minXp == maxXp) ? String.valueOf(minXp) : minXp + "-" + maxXp;

            tooltip.add(Component.translatable("tooltip.hammers_galore.sculk.xp_drop",
                            xpRange, oreMultiplier)
                    .withStyle(ChatFormatting.AQUA));
        }

        // 绿宝石特殊提示
        if ("emerald_hammer".equals(itemName)) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.hammers_galore.specialhammers.bonus")
                    .withStyle(ChatFormatting.DARK_PURPLE));

            double luckChance = ServerConfig.getEmeraldHammerBaseTriggerChance();
            String percentChance = String.format("%.1f%%", luckChance * 100);

            tooltip.add(Component.translatable("tooltip.hammers_galore.emerald_hammer.chance",
                            percentChance)
                    .withStyle(ChatFormatting.AQUA));
        }
    }

    /**
     * 从 ItemStack 获取挖掘等级
     */
    private static int getMiningLevel(ItemStack stack) {
        if (stack.getItem() instanceof net.minecraft.world.item.TieredItem tieredItem) {
            Tier tier = tieredItem.getTier();

            // 通过检测方块标签来判断等级
            if (tier.getIncorrectBlocksForDrops() == BlockTags.INCORRECT_FOR_WOODEN_TOOL) return 0;
            if (tier.getIncorrectBlocksForDrops() == BlockTags.INCORRECT_FOR_STONE_TOOL) return 1;
            if (tier.getIncorrectBlocksForDrops() == BlockTags.INCORRECT_FOR_IRON_TOOL) return 2;
            if (tier.getIncorrectBlocksForDrops() == BlockTags.INCORRECT_FOR_DIAMOND_TOOL) return 3;
            if (tier.getIncorrectBlocksForDrops() == BlockTags.INCORRECT_FOR_NETHERITE_TOOL) return 4;
        }

        // 默认返回 0
        return 0;
    }

    /**
     * 将挖掘等级转换为可读名称（使用本地化）
     */
    private static Component getMiningLevelName(int level) {
        String translationKey = switch (level) {
            case 0 -> "tooltip.hammers_galore.mining_level.wood";
            case 1 -> "tooltip.hammers_galore.mining_level.stone";
            case 2 -> "tooltip.hammers_galore.mining_level.iron";
            case 3 -> "tooltip.hammers_galore.mining_level.diamond";
            case 4 -> "tooltip.hammers_galore.mining_level.netherite";
            default -> null;
        };

        if (translationKey != null) {
            return Component.translatable(translationKey);
        } else {
            // 如果等级不在预设范围内，显示数字
            return Component.literal(level + "级");
        }
    }
}
