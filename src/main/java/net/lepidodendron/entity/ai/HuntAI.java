package net.lepidodendron.entity.ai;

import com.google.common.base.Predicate;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

public class HuntAI<T extends EntityLivingBase> extends EntityAINearestAttackableTarget {
    private final EntityPrehistoricFloraAgeableBase entity;

    public HuntAI(EntityPrehistoricFloraAgeableBase entity, Class<T> classTarget, boolean checkSight, Predicate<? super T> targetSelector) {
        super(entity, classTarget, 0, checkSight, true, targetSelector);
        this.entity = entity;
    }

    @Override
    public boolean shouldExecute() {
        if (!this.entity.getWillHunt()) {
            //System.err.println(this.entity.getWillAttack());
            return false;
        }
        return super.shouldExecute();
    }

    @Override
    public void updateTask() {
        if (!this.entity.world.isRemote) {this.entity.selectNavigator();}
        super.updateTask();
        }

}