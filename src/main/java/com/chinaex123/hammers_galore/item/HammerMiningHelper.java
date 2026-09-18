package com.chinaex123.hammers_galore.item;

import com.chinaex123.hammers_galore.config.HGServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 锤子挖掘辅助工具类
 */
public class HammerMiningHelper {

    public record HammerCfg(
            ModConfigSpec.IntValue miningRange,
            ModConfigSpec.BooleanValue requireSneak,
            ModConfigSpec.IntValue durabilityCost,
            ModConfigSpec.BooleanValue enableHungerCost) {}

    public static final Map<String, HammerCfg> HAMMER_CONFIGS = Map.ofEntries(
            Map.entry("wood_hammer", new HammerCfg(HGServerConfig.WOOD_HAMMER_MINING_RANGE, HGServerConfig.WOOD_HAMMER_REQUIRE_SNEAK, HGServerConfig.WOOD_HAMMER_DURABILITY_COST, HGServerConfig.WOOD_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("stone_hammer", new HammerCfg(HGServerConfig.STONE_HAMMER_MINING_RANGE, HGServerConfig.STONE_HAMMER_REQUIRE_SNEAK, HGServerConfig.STONE_HAMMER_DURABILITY_COST, HGServerConfig.STONE_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("copper_hammer", new HammerCfg(HGServerConfig.COPPER_HAMMER_MINING_RANGE, HGServerConfig.COPPER_HAMMER_REQUIRE_SNEAK, HGServerConfig.COPPER_HAMMER_DURABILITY_COST, HGServerConfig.COPPER_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("iron_hammer", new HammerCfg(HGServerConfig.IRON_HAMMER_MINING_RANGE, HGServerConfig.IRON_HAMMER_REQUIRE_SNEAK, HGServerConfig.IRON_HAMMER_DURABILITY_COST, HGServerConfig.IRON_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("gold_hammer", new HammerCfg(HGServerConfig.GOLD_HAMMER_MINING_RANGE, HGServerConfig.GOLD_HAMMER_REQUIRE_SNEAK, HGServerConfig.GOLD_HAMMER_DURABILITY_COST, HGServerConfig.GOLD_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("diamond_hammer", new HammerCfg(HGServerConfig.DIAMOND_HAMMER_MINING_RANGE, HGServerConfig.DIAMOND_HAMMER_REQUIRE_SNEAK, HGServerConfig.DIAMOND_HAMMER_DURABILITY_COST, HGServerConfig.DIAMOND_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("netherite_hammer", new HammerCfg(HGServerConfig.NETHERITE_HAMMER_MINING_RANGE, HGServerConfig.NETHERITE_HAMMER_REQUIRE_SNEAK, HGServerConfig.NETHERITE_HAMMER_DURABILITY_COST, HGServerConfig.NETHERITE_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("nether_star_hammer", new HammerCfg(HGServerConfig.NETHER_STAR_HAMMER_MINING_RANGE, HGServerConfig.NETHER_STAR_HAMMER_REQUIRE_SNEAK, HGServerConfig.NETHER_STAR_HAMMER_DURABILITY_COST, HGServerConfig.NETHER_STAR_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("heart_of_the_sea_hammer", new HammerCfg(HGServerConfig.HEART_OF_THE_SEA_HAMMER_MINING_RANGE, HGServerConfig.HEART_OF_THE_SEA_HAMMER_REQUIRE_SNEAK, HGServerConfig.HEART_OF_THE_SEA_HAMMER_DURABILITY_COST, HGServerConfig.HEART_OF_THE_SEA_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("conduit_hammer", new HammerCfg(HGServerConfig.CONDUIT_HAMMER_MINING_RANGE, HGServerConfig.CONDUIT_HAMMER_REQUIRE_SNEAK, HGServerConfig.CONDUIT_HAMMER_DURABILITY_COST, HGServerConfig.CONDUIT_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("ender_pearl_hammer", new HammerCfg(HGServerConfig.ENDER_PEARL_HAMMER_MINING_RANGE, HGServerConfig.ENDER_PEARL_HAMMER_REQUIRE_SNEAK, HGServerConfig.ENDER_PEARL_HAMMER_DURABILITY_COST, HGServerConfig.ENDER_PEARL_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("magma_hammer", new HammerCfg(HGServerConfig.MAGMA_HAMMER_MINING_RANGE, HGServerConfig.MAGMA_HAMMER_REQUIRE_SNEAK, HGServerConfig.MAGMA_HAMMER_DURABILITY_COST, HGServerConfig.MAGMA_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("piston_hammer", new HammerCfg(HGServerConfig.PISTON_HAMMER_MINING_RANGE, HGServerConfig.PISTON_HAMMER_REQUIRE_SNEAK, HGServerConfig.PISTON_HAMMER_DURABILITY_COST, HGServerConfig.PISTON_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("glass_hammer", new HammerCfg(HGServerConfig.GLASS_HAMMER_MINING_RANGE, HGServerConfig.GLASS_HAMMER_REQUIRE_SNEAK, HGServerConfig.GLASS_HAMMER_DURABILITY_COST, HGServerConfig.GLASS_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("sculk_hammer", new HammerCfg(HGServerConfig.SCULK_HAMMER_MINING_RANGE, HGServerConfig.SCULK_HAMMER_REQUIRE_SNEAK, HGServerConfig.SCULK_HAMMER_DURABILITY_COST, HGServerConfig.SCULK_HAMMER_ENABLE_HUNGER_COST)),
            Map.entry("emerald_hammer", new HammerCfg(HGServerConfig.EMERALD_HAMMER_MINING_RANGE, HGServerConfig.EMERALD_HAMMER_REQUIRE_SNEAK, HGServerConfig.EMERALD_HAMMER_DURABILITY_COST, HGServerConfig.EMERALD_HAMMER_ENABLE_HUNGER_COST))
    );

    public static HammerCfg getHammerCfg(String tierName) {
        return HAMMER_CONFIGS.getOrDefault(tierName, new HammerCfg(null, null, null, null));
    }

    /**
     * 将采矿范围转换为半径。
     *
     * <p>范围 ≤ 1 时返回 0，否则返回 (miningRange - 1) / 2。
     *
     * @param miningRange 采矿范围（通常为奇数直径）
     * @return 对应的半径
     */
    public static int toRadius(int miningRange) {
        return miningRange <= 1 ? 0 : (miningRange - 1) / 2;
    }


    /**
     * 根据玩家视角和方块位置计算挖掘方向
     *
     * @param pos 被挖掘的方块位置
     * @param entity 进行挖掘的生物实体
     * @return 计算得出的挖掘方向
     */
    public static Direction getFacingFromBlock(BlockPos pos, LivingEntity entity) {
        // 优先使用玩家的视线方向（更准确）
        if (entity instanceof Player player) {
            // 获取玩家的 pitch 角度（-90 到 90）
            float pitch = player.getXRot();
            
            // 如果抬头或低头角度超过 45 度，认为是垂直挖掘
            if (pitch > 45) {
                return Direction.DOWN; // 低头看地面
            } else if (pitch < -45) {
                return Direction.UP; // 抬头看天空
            }
            
            // 否则使用水平方向
            float yaw = player.getYRot();
            
            // 将 yaw 转换为 -180 到 180 的范围
            yaw = yaw % 360;
            if (yaw > 180) yaw -= 360;
            if (yaw < -180) yaw += 360;
            
            // 根据 yaw 角度判断水平方向
            if (yaw > -45 && yaw <= 45) {
                return Direction.SOUTH; // 看向 Z+ 方向
            } else if (yaw > 45 && yaw <= 135) {
                return Direction.WEST; // 看向 X- 方向
            } else if (yaw > 135 || yaw <= -135) {
                return Direction.NORTH; // 看向 Z- 方向
            } else {
                return Direction.EAST; // 看向 X+ 方向
            }
        }
        
        // 如果不是玩家，回退到原来的方法
        double dx = pos.getX() + 0.5 - entity.getX();
        double dy = pos.getY() + 0.5 - entity.getY();
        double dz = pos.getZ() + 0.5 - entity.getZ();

        double absX = Math.abs(dx);
        double absY = Math.abs(dy);
        double absZ = Math.abs(dz);

        if (absY > absX && absY > absZ) {
            return dy > 0 ? Direction.UP : Direction.DOWN;
        } else if (absX > absZ) {
            return dx > 0 ? Direction.EAST : Direction.WEST;
        } else {
            return dz > 0 ? Direction.SOUTH : Direction.NORTH;
        }
    }

    /**
     * 计算以中心位置为基准的方形挖掘区域内的所有方块位置
     *
     * @param center 中心方块的坐标位置
     * @param facing 挖掘方向，用于确定挖掘平面的朝向
     * @param radius 挖掘半径（从中心向四周扩展的格数）
     * @return 包含挖掘区域内所有方块位置的列表
     */
    public static List<BlockPos> getAreaPositions(BlockPos center, Direction facing, int radius) {
        // 创建位置列表存储挖掘区域内的所有坐标
        List<BlockPos> positions = new ArrayList<>();

        // 定义两个轴向，用于构建挖掘平面
        Direction.Axis axis1, axis2;

        // 根据挖掘方向确定挖掘平面的两个轴
        if (facing.getAxis() == Direction.Axis.Y) {
            // 如果面向上下（垂直方向），使用 X 和 Z 轴构建水平面
            axis1 = Direction.Axis.X;
            axis2 = Direction.Axis.Z;
        } else {
            // 如果面向水平方向，使用垂直方向（Y 轴）和侧向轴构建垂直面
            axis1 = Direction.UP.getAxis();
            axis2 = facing.getClockWise().getAxis();
        }

        // 在两个轴向上遍历范围区域，生成正方形网格
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                // 从中心位置开始计算偏移
                BlockPos offset = center;

                // 沿第一个轴应用偏移
                if (axis1 == Direction.Axis.X) offset = offset.offset(i, 0, 0);
                else if (axis1 == Direction.Axis.Y) offset = offset.offset(0, i, 0);
                else if (axis1 == Direction.Axis.Z) offset = offset.offset(0, 0, i);

                // 沿第二个轴应用偏移
                if (axis2 == Direction.Axis.X) offset = offset.offset(j, 0, 0);
                else if (axis2 == Direction.Axis.Y) offset = offset.offset(0, j, 0);
                else if (axis2 == Direction.Axis.Z) offset = offset.offset(0, 0, j);

                // 将计算好的位置添加到列表中
                positions.add(offset);
            }
        }

        // 返回包含所有挖掘位置的列表
        return positions;
    }

    /**
     * 检查锤子是否可以挖掘指定的方块
     *
     * @param state 要检查的方块状态
     * @return 如果锤子可以挖掘此方块返回 true，否则返回 false。
     *         会跳过不可移动的方块（如基岩、命令方块）和末影人黑名单方块
     */
    public static boolean canHammerMine(BlockState state) {
        // 检查方块的破坏速度，-1.0F 表示无法破坏
        if (state.getDestroySpeed(null, BlockPos.ZERO) == -1.0F) {
            return false; // 无法破坏的方块
        }

        // 检查是否在末影人黑名单中
        if (state.is(Tags.Blocks.ENDERMAN_PLACE_ON_BLACKLIST)) {
            return false;
        }

        // 检查是否是不可移动的方块（活塞无法推动的方块）
        return !state.is(Tags.Blocks.RELOCATION_NOT_SUPPORTED);
    }

    /**
     * 从物品堆栈中提取锤子的注册名称
     *
     * @param stack 玩家手持的物品堆栈
     * @return 锤子的注册名称（如 "ender_pearl_hammer"、"wood_hammer" 等）
     */
    public static String getTierNameFromStack(ItemStack stack) {
        return stack.getItem().getDescriptionId()
                .replace("item.hammers_galore.", "");
    }
}
