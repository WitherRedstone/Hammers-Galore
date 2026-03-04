package com.chinaex123.hammers_galore.server.specialHammer;

import com.chinaex123.hammers_galore.config.ServerConfig;
import com.chinaex123.hammers_galore.server.PickaxeItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class NetherStarHammer extends PickaxeItems {

    private static final ResourceLocation ATTACK_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("hammers_galore", "nether_star_hammer_attack_bonus");
    private static final ResourceLocation SPEED_MODIFIER_ID =
            ResourceLocation.fromNamespaceAndPath("hammers_galore", "nether_star_hammer_speed_bonus");

    // 最大攻击力加成（在耐久 30% 时）
    private static final float MAX_ATTACK_BONUS = 3.0f;

    // 最大挖掘速度加成（在耐久 30% 时）
    private static final float MAX_SPEED_BONUS = 3.0f;

    public NetherStarHammer(Tier tier, Properties properties) {
        super(tier, properties);
    }

    /**
     * 从 Tick 事件中调用，更新增益效果
     */
    public void updateBonusFromTick(ItemStack stack, LivingEntity entity) {
        updateBonus(stack, entity);
    }

    /**
     * 静态方法，用于从事件处理器中移除增益
     */
    public static void removeBonusStatic(LivingEntity entity) {
        removeBonus(entity);
    }

    /**
     * 重写方块挖掘方法，在挖掘时更新增益效果
     *
     * @param stack 玩家手持的物品堆栈
     * @param level 当前世界等级
     * @param state 被挖掘方块的状态
     * @param pos 被挖掘方块的位置
     * @param entity 进行挖掘的生物实体
     * @return 如果挖掘成功返回 true，否则返回 false
     */
    @Override
    public boolean mineBlock(@NotNull ItemStack stack, Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity entity) {
        // 更新挖掘者的增益效果
        updateBonus(stack, entity);

        // 调用父类的挖掘方法处理基础逻辑
        return super.mineBlock(stack, level, state, pos, entity);
    }

    /**
     * 重写攻击敌人方法，在攻击时更新增益效果
     *
     * @param stack 玩家手持的物品堆栈
     * @param target 被攻击的目标生物实体
     * @param attacker 发起攻击的生物实体
     * @return 如果攻击成功返回 true，否则返回 false
     */
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 更新攻击者的增益效果
        updateBonus(stack, attacker);

        // 调用父类的攻击方法处理基础逻辑
        return super.hurtEnemy(stack, target, attacker);
    }

    /**
     * 根据工具当前耐久度动态计算并应用增益效果
     * 耐久度越低，增益效果越强
     *
     * @param stack 玩家手持的物品堆栈
     * @param entity 进行挖掘或攻击的生物实体
     */
    private void updateBonus(ItemStack stack, LivingEntity entity) {
        // 计算当前耐久度比例（已损失耐久 / 最大耐久）
        float durabilityRatio = (float) stack.getDamageValue() / (float) stack.getMaxDamage();

        // 从配置获取阈值
        double thresholdLow = ServerConfig.getNetherStarThresholdLow();
        double thresholdHigh = ServerConfig.getNetherStarThresholdHigh();

        // 耐久高于阈值时无增益
        if (durabilityRatio < thresholdLow) {
            removeBonus(entity);
            return;
        }

        // 计算当前增益倍率 (0.0 ~ 1.0)
        double bonusMultiplier = Math.max(0.0, Math.min(1.0, (durabilityRatio - thresholdLow) / (thresholdHigh - thresholdLow)));

        // 从配置获取最大加成
        float maxAttackBonus = (float) ServerConfig.getNetherStarAttackBonus();
        float maxSpeedBonus = (float) ServerConfig.getNetherStarSpeedBonus();

        // 计算实际的攻击力和挖掘速度加成
        float attackBonus = maxAttackBonus * (float)bonusMultiplier;
        float speedBonus = maxSpeedBonus * (float)bonusMultiplier;

        // 应用计算好的增益效果
        applyBonus(entity, attackBonus, speedBonus);
    }

    /**
     * 为生物实体应用攻击力和挖掘速度增益效果
     *
     * @param entity 要应用增益的生物实体
     * @param attackBonus 攻击力加成值
     * @param speedBonus 挖掘速度加成值
     */
    private void applyBonus(LivingEntity entity, float attackBonus, float speedBonus) {
        // 应用攻击力加成
        AttributeInstance attackAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackAttr != null) {
            // 先移除旧的修改器
            attackAttr.removeModifier(ATTACK_MODIFIER_ID);

            // 如果加成为 0，不需要添加修改器
            if (attackBonus > 0) {
                attackAttr.addTransientModifier(new AttributeModifier(
                        ATTACK_MODIFIER_ID,
                        attackBonus,
                        AttributeModifier.Operation.ADD_VALUE
                ));
            }
        }

        // 应用挖掘速度加成
        AttributeInstance speedAttr = entity.getAttribute(Attributes.BLOCK_BREAK_SPEED);
        if (speedAttr != null) {
            // 先移除旧的修改器
            speedAttr.removeModifier(SPEED_MODIFIER_ID);

            // 如果加成为 0，不需要添加修改器
            if (speedBonus > 0) {
                speedAttr.addTransientModifier(new AttributeModifier(
                        SPEED_MODIFIER_ID,
                        speedBonus,
                        AttributeModifier.Operation.ADD_VALUE
                ));
            }
        }
    }

    /**
     * 移除下界之星锤的攻击力和挖掘速度增益效果
     *
     * @param entity 需要移除增益效果的生物实体
     */
    private static void removeBonus(LivingEntity entity) {
        // 移除攻击力加成
        AttributeInstance attackAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackAttr != null && attackAttr.hasModifier(ATTACK_MODIFIER_ID)) {
            attackAttr.removeModifier(ATTACK_MODIFIER_ID);
        }

        // 移除挖掘速度加成
        AttributeInstance speedAttr = entity.getAttribute(Attributes.BLOCK_BREAK_SPEED);
        if (speedAttr != null && speedAttr.hasModifier(SPEED_MODIFIER_ID)) {
            speedAttr.removeModifier(SPEED_MODIFIER_ID);
        }
    }
}
