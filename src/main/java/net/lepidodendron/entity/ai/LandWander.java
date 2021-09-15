package net.lepidodendron.entity.ai;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.lepidodendron.entity.base.EntityPrehistoricFloraLandBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

//public class FishWander extends EntityAIBase {
public class LandWander extends AnimationAINoAnimation<EntityPrehistoricFloraLandBase> {

    protected float probability;
    protected Animation animation;
    protected EntityPrehistoricFloraLandBase PrehistoricFloraLandBase;

    public LandWander(EntityPrehistoricFloraLandBase PrehistoricFloraLandBase, Animation animation)
    {
        super(PrehistoricFloraLandBase);
        setMutexBits(1);
        this.PrehistoricFloraLandBase = PrehistoricFloraLandBase;
        this.animation = animation;
    }

    @Override
    public Animation getAnimation()
    {
        return animation;
    }

    @Override
    public boolean isAutomatic() {
        return true;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }

    @Override
    public void updateTask() {
        super.updateTask();

    }

    @Override
    public boolean shouldExecute() {
        Path path = this.PrehistoricFloraLandBase.getNavigator().getPath();
        //System.err.println("Not in water");
        Vec3d vec3d = getPosition();
        if (vec3d != null){
            if (this.PrehistoricFloraLandBase.getNavigator().noPath()) {
                //this.mustUpdate = false;
                //System.err.println("Setting path!");
                this.PrehistoricFloraLandBase.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0);

                return true;
            }
        }
        //System.err.println("No path found");
        return false;
    }

    @Nullable
    protected Vec3d getPosition()
    {
        if (this.entity.isInWater())
        {
            Vec3d vec3d = RandomPositionGenerator.getLandPos(this.entity, 15, 7);
            return vec3d == null ? RandomPositionGenerator.findRandomTarget(this.entity, 10, 7) : vec3d;
        }
        else
        {
            return this.entity.getRNG().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.entity, 10, 7) : RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
        }
    }

    public boolean isDirectPathBetweenPoints(Entity entity, Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = entity.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y + (double) entity.height * 0.5D, vec2.z), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return false;
    }

}