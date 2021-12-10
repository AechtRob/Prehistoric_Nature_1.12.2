package net.lepidodendron.entity.ai;

import com.google.common.base.Predicate;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

public class HuntSmallerThanMeAIAgeable<T extends EntityLivingBase> extends EntityAINearestAttackableTarget {
    private final EntityPrehistoricFloraAgeableBase entity;

    public HuntSmallerThanMeAIAgeable(EntityPrehistoricFloraAgeableBase entity, Class<T> classTarget, boolean checkSight, Predicate<? super T> targetSelector) {
        super(entity, classTarget, 0, checkSight, true, targetSelector);
        this.entity = entity;
    }

    @Override
    public boolean shouldExecute() {
        //System.err.println("Testing AI");
        boolean preliminaryTarget = super.shouldExecute();
        if (!this.entity.getWillHunt()) {
            this.entity.setIsFast(false);
            return false;
        }
        if (this.targetEntity != null) {
           if ((this.entity.getEntityBoundingBox().getAverageEdgeLength() <= this.targetEntity.getEntityBoundingBox().getAverageEdgeLength())
                || (this.entity.getMaxHealth() * 1.5 <= this.targetEntity.getMaxHealth())
                ) {
               this.entity.setIsFast(false);
               return false;
            }
        }
        if (preliminaryTarget) {
            this.entity.setIsFast(true);
        }
        else {
            this.entity.setIsFast(false);
        }

        //System.err.println("Target " + this.entity.getAttackTarget());

        return preliminaryTarget;
    }

    @Override
    public void updateTask() {
        if (!this.entity.world.isRemote) {this.entity.selectNavigator();}
        super.updateTask();
        }

}