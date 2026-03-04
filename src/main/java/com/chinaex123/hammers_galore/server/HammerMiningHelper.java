package com.chinaex123.hammers_galore.server;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

/**
 * 锤子挖掘辅助工具类
 */
public class HammerMiningHelper {

    /**
     * 根据玩家视角和方块位置计算挖掘方向
     *
     * @param pos 被挖掘的方块位置
     * @param entity 进行挖掘的生物实体
     * @return 计算得出的挖掘方向
     */
    public static Direction getFacingFromBlock(BlockPos pos, LivingEntity entity) {
        // 计算从玩家到方块中心的向量
        double dx = pos.getX() + 0.5 - entity.getX();
        double dy = pos.getY() + 0.5 - entity.getY();
        double dz = pos.getZ() + 0.5 - entity.getZ();

        // 找出最大的轴向作为主要方向
        double absX = Math.abs(dx);
        double absY = Math.abs(dy);
        double absZ = Math.abs(dz);

        if (absY > absX && absY > absZ) {
            // Y 轴最大，玩家主要在抬头或低头
            return dy > 0 ? Direction.UP : Direction.DOWN;
        } else if (absX > absZ) {
            // X 轴最大
            return dx > 0 ? Direction.EAST : Direction.WEST;
        } else {
            // Z 轴最大
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
        // 检查方块的破坏速度，-1.0F 表示无法破坏（如基岩、命令方块等）
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
