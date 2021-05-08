package net.lepidodendron.entity.ai;

import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumHand;
import net.minecraft.world.EnumDifficulty;

public class AttackAI extends EntityAIBase {
    private final EntityPrehistoricFloraAgeableBase entity;
    private final double speed;
    private final boolean memory;
    private int attackTick;
    private Path currentPath;

    public AttackAI(EntityPrehistoricFloraAgeableBase entity, double speed, boolean memory) {
        this.entity = entity;
        this.speed = speed;
        this.memory = memory;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        EntityLivingBase target = this.entity.getAttackTarget();
        if (target == null || !target.isEntityAlive()) {
            return false;
        } else if (this.entity.world.getDifficulty() == EnumDifficulty.PEACEFUL && target instanceof EntityPlayer) {
            return false;
        }
        if (!this.entity.getWillHunt() && !(target instanceof EntityPlayer)) {
            return false;
        }
        if ((!this.entity.getWillHunt() && !this.entity.getResentful()) && (target instanceof EntityPlayer)) {
            return false;
        }
        this.currentPath = this.entity.getNavigator().getPathToEntityLiving(target);
        return this.currentPath != null;
    }

    @Override
    public boolean shouldContinueExecuting() {
        EntityLivingBase entity = this.entity.getAttackTarget();
        return this.entity.getWillHunt() && entity != null && (entity.isEntityAlive() && (!this.memory ? !this.entity.getNavigator().noPath() : this.entity.isWithinHomeDistanceFromPosition(entity.getPosition())));
    }

    @Override
    public void startExecuting() {
        if (this.entity.getControllingPassenger() == null) {
            this.entity.getNavigator().setPath(this.currentPath, this.speed);
        }
    }

    @Override
    public void resetTask() {
        this.entity.getNavigator().clearPath();
    }

    @Override
    public void updateTask() {
        EntityLivingBase target = this.entity.getAttackTarget();
        if (target == null) {
            return;
        }
        if (this.entity.getControllingPassenger() == null) {
            this.entity.getNavigator().tryMoveToEntityLiving(target, this.speed);
        }
        this.attackTick = Math.max(this.attackTick - 1, 0);
        if (this.entity.getAttackBoundingBox().intersects(target.getEntityBoundingBox())) {
            this.attackTick = 20;
            this.entity.attackEntityAsMob(target);
            if (target instanceof EntityPlayer) {this.entity.setResentful(false);}
            if (this.entity.ATTACK_ANIMATION != null) {
                this.entity.setAnimation(this.entity.ATTACK_ANIMATION);
            }
        } else {
            this.entity.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);
        }
    }
}