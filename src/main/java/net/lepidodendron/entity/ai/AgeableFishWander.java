package net.lepidodendron.entity.ai;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableFishBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

//public class FishWander extends EntityAIBase {
public class AgeableFishWander extends AnimationAI<EntityPrehistoricFloraAgeableFishBase> {

    protected Animation animation;
    protected EntityPrehistoricFloraAgeableFishBase PrehistoricFloraAgeableFishBase;
    protected double straightness;
    protected double surfacelove;

    public AgeableFishWander(EntityPrehistoricFloraAgeableFishBase PrehistoricFloraAgeableFishBase, Animation animation, double straightness, double surfacelove)
    {
        super(PrehistoricFloraAgeableFishBase);
        setMutexBits(4);
        this.PrehistoricFloraAgeableFishBase = PrehistoricFloraAgeableFishBase;
        this.animation = animation;
        this.straightness = straightness;
        this.surfacelove = surfacelove;
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
        if (!this.PrehistoricFloraAgeableFishBase.isInWater()) {
            return false;
        }
        if (this.PrehistoricFloraAgeableFishBase.getRNG().nextFloat() < 0.5F) {
            Path path = this.PrehistoricFloraAgeableFishBase.getNavigator().getPath();
            if (!this.PrehistoricFloraAgeableFishBase.getNavigator().noPath() && !isDirectPathBetweenPoints(this.PrehistoricFloraAgeableFishBase, this.PrehistoricFloraAgeableFishBase.getPositionVector(), new Vec3d(path.getFinalPathPoint().x, path.getFinalPathPoint().y, path.getFinalPathPoint().z)) || path != null && path.getFinalPathPoint() != null && this.PrehistoricFloraAgeableFishBase.getDistanceSq(path.getFinalPathPoint().x, path.getFinalPathPoint().y, path.getFinalPathPoint().z) < 3) {
                this.PrehistoricFloraAgeableFishBase.getNavigator().clearPath();
            }
            if (this.PrehistoricFloraAgeableFishBase.getNavigator().noPath()) {
                BlockPos vec3 = this.findWaterTarget();
                if (vec3 != null) {
                    this.PrehistoricFloraAgeableFishBase.getNavigator().tryMoveToXYZ(vec3.getX() + 0.5D, vec3.getY() + 0.5D, vec3.getZ() + 0.5D, 1.0);

                    return true;
                }
            }
        }

        //System.err.println("No path found");
        return false;
    }

    public boolean isDirectPathBetweenPoints(Entity entity, Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = entity.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y + (double) entity.height * 0.5D, vec2.z), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return false;
    }

    public BlockPos findWaterTarget() {
        Random rand = this.PrehistoricFloraAgeableFishBase.getRNG();
        if (this.PrehistoricFloraAgeableFishBase.getAttackTarget() == null) {
            for (int i = 0; i < 4; i++) {
                BlockPos randPos = this.PrehistoricFloraAgeableFishBase.getPosition().add(rand.nextInt(16) - 8, rand.nextInt(8) - 4, rand.nextInt(16) - 8);
                //System.err.println("Target " + randPos.getX() + " " + this.PrehistoricFloraAgeableFishBase.getPosition().getY() + " " + randPos.getZ());
                if (canTarget(randPos) && this.PrehistoricFloraAgeableFishBase.world.getBlockState(randPos).getMaterial() == Material.WATER && this.PrehistoricFloraAgeableFishBase.isDirectPathBetweenPoints(this.PrehistoricFloraAgeableFishBase.getPositionVector(), new Vec3d(randPos.getX() + 0.5, randPos.getY() + 0.5, randPos.getZ() + 0.5))) {
                    return randPos;
                }
            }
        } else {
            BlockPos blockpos1;
            blockpos1 = new BlockPos(this.PrehistoricFloraAgeableFishBase.getAttackTarget());
            if (canTarget(blockpos1) && this.PrehistoricFloraAgeableFishBase.world.getBlockState(blockpos1).getMaterial() == Material.WATER) {
                return blockpos1;
            }
        }
        return null;
    }

    public boolean canTarget(BlockPos pos) {
        if (Math.random() > surfacelove && this.PrehistoricFloraAgeableFishBase.world.getBlockState(pos.up(2)).getMaterial() != Material.WATER) {
            return false;
        }
        //Target to the front and prefer not to be at the surface:
        EnumFacing EntityFacing = this.PrehistoricFloraAgeableFishBase.getHorizontalFacing();
        if (Math.random() > straightness && (EntityFacing == EnumFacing.NORTH) && (pos.getZ() > this.PrehistoricFloraAgeableFishBase.getPosition().getZ())) {
            return false;
        }
        if (Math.random() > straightness && (EntityFacing == EnumFacing.SOUTH) && (pos.getZ() < this.PrehistoricFloraAgeableFishBase.getPosition().getZ())) {
            return false;
        }
        if (Math.random() > straightness && (EntityFacing == EnumFacing.WEST) && (pos.getX() > this.PrehistoricFloraAgeableFishBase.getPosition().getX())) {
            return false;
        }
        if (Math.random() > straightness && (EntityFacing == EnumFacing.EAST) && (pos.getX() < this.PrehistoricFloraAgeableFishBase.getPosition().getX())) {
            return false;
        }
        return true;
    }
}