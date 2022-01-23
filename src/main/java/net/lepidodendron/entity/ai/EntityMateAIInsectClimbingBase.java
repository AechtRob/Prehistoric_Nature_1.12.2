package net.lepidodendron.entity.ai;

import net.lepidodendron.entity.base.EntityPrehistoricFloraInsectClimbingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EntityMateAIInsectClimbingBase extends EntityAIBase
{
    private final EntityPrehistoricFloraInsectClimbingBase animal;
    private final Class <? extends EntityPrehistoricFloraInsectClimbingBase > mateClass;
    World world;
    private EntityPrehistoricFloraInsectClimbingBase targetMate;
    int spawnBabyDelay;
    double moveSpeed;

    public EntityMateAIInsectClimbingBase(EntityPrehistoricFloraInsectClimbingBase animal, double speedIn)
    {
        this(animal, speedIn, animal.getClass());
    }

    public EntityMateAIInsectClimbingBase(EntityPrehistoricFloraInsectClimbingBase animal, double p_i47306_2_, Class <? extends EntityPrehistoricFloraInsectClimbingBase > p_i47306_4_)
    {
        this.animal = animal;
        this.world = animal.world;
        this.mateClass = p_i47306_4_;
        this.moveSpeed = p_i47306_2_;
        this.setMutexBits(3);
    }

    public boolean shouldExecute()
    {
        if (!this.animal.isInLove())
        {
            return false;
        }
        else
        {
            this.targetMate = this.getNearbyMate();
            return this.targetMate != null;
        }
    }

    public boolean shouldContinueExecuting()
    {
        return this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60;
    }

    public void resetTask()
    {
        this.targetMate = null;
        this.spawnBabyDelay = 0;
    }

    public void updateTask()
    {
        this.animal.getLookHelper().setLookPositionWithEntity(this.targetMate, 10.0F, (float)this.animal.getVerticalFaceSpeed());
        this.animal.getNavigator().tryMoveToEntityLiving(this.targetMate, this.moveSpeed);
        ++this.spawnBabyDelay;

        if (this.spawnBabyDelay >= 60 && this.animal.getDistanceSq(this.targetMate) < 9.0D)
        {
            this.spawnBaby();
        }
    }

    private EntityPrehistoricFloraInsectClimbingBase getNearbyMate()
    {
        List<EntityPrehistoricFloraInsectClimbingBase> list = this.world.<EntityPrehistoricFloraInsectClimbingBase>getEntitiesWithinAABB(this.mateClass, this.animal.getEntityBoundingBox().grow(8.0D));
        double d0 = Double.MAX_VALUE;
        EntityPrehistoricFloraInsectClimbingBase EntityPrehistoricFloraInsectClimbingBase = null;

        for (EntityPrehistoricFloraInsectClimbingBase EntityPrehistoricFloraInsectClimbingBase1 : list)
        {
            if (this.animal.canMateWith(EntityPrehistoricFloraInsectClimbingBase1) && this.animal.getDistanceSq(EntityPrehistoricFloraInsectClimbingBase1) < d0)
            {
                EntityPrehistoricFloraInsectClimbingBase = EntityPrehistoricFloraInsectClimbingBase1;
                d0 = this.animal.getDistanceSq(EntityPrehistoricFloraInsectClimbingBase1);
            }
        }

        return EntityPrehistoricFloraInsectClimbingBase;
    }

    private void spawnBaby() {
        Random random = this.animal.getRNG();
        for (int i = 0; i < 7; ++i)
        {
            double d0 = random.nextGaussian() * 0.02D;
            double d1 = random.nextGaussian() * 0.02D;
            double d2 = random.nextGaussian() * 0.02D;
            double d3 = random.nextDouble() * (double)this.animal.width * 2.0D - (double)this.animal.width;
            double d4 = 0.5D + random.nextDouble() * (double)this.animal.height;
            double d5 = random.nextDouble() * (double)this.animal.width * 2.0D - (double)this.animal.width;
            this.world.spawnParticle(EnumParticleTypes.HEART, this.animal.posX + d3, this.animal.posY + d4, this.animal.posZ + d5, d0, d1, d2);
        }

        this.animal.setTicks(24000);
        this.animal.setLaying(true);
        this.animal.resetInLove();
        this.targetMate.resetInLove();
        this.animal.setNotMateable();
        this.targetMate.setNotMateable();
    }

}
