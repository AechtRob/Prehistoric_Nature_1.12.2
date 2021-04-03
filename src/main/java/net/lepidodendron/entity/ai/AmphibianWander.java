package net.lepidodendron.entity.ai;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAmphibianBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

//public class FishWander extends EntityAIBase {
public class AmphibianWander extends AnimationAI<EntityPrehistoricFloraAmphibianBase> {

    protected Animation animation;
    protected EntityPrehistoricFloraAmphibianBase PrehistoricFloraAmphibianBase;

    public AmphibianWander(EntityPrehistoricFloraAmphibianBase PrehistoricFloraAmphibianBase, Animation animation)
    {
        super(PrehistoricFloraAmphibianBase);
        setMutexBits(4);
        this.PrehistoricFloraAmphibianBase = PrehistoricFloraAmphibianBase;
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
        //PrehistoricFloraAmphibianBase.currentAnim = this;
    }

    @Override
    public void updateTask() {
        super.updateTask();

    }

    @Override
    public boolean shouldExecute() {
        if (!this.PrehistoricFloraAmphibianBase.isInWater()) {
            Path path = this.PrehistoricFloraAmphibianBase.getNavigator().getPath();
            //System.err.println("Not in water");
            Vec3d vec3d = this.PrehistoricFloraAmphibianBase.getRNG().nextFloat() >= 0.001D ? RandomPositionGenerator.getLandPos(this.PrehistoricFloraAmphibianBase, 10, 7) : RandomPositionGenerator.findRandomTarget(this.PrehistoricFloraAmphibianBase, 10, 7);
            if (vec3d != null){
                if (this.PrehistoricFloraAmphibianBase.getNavigator().noPath()) {
                    //this.mustUpdate = false;
                    //System.err.println("Setting path!");
                    this.PrehistoricFloraAmphibianBase.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0);

                    return true;
                }
            }
        }
        else if (this.PrehistoricFloraAmphibianBase.getRNG().nextFloat() < 0.5F) {
            Path path = this.PrehistoricFloraAmphibianBase.getNavigator().getPath();
            if (!this.PrehistoricFloraAmphibianBase.getNavigator().noPath() && !isDirectPathBetweenPoints(this.PrehistoricFloraAmphibianBase, this.PrehistoricFloraAmphibianBase.getPositionVector(), new Vec3d(path.getFinalPathPoint().x, path.getFinalPathPoint().y, path.getFinalPathPoint().z))) {
                this.PrehistoricFloraAmphibianBase.getNavigator().clearPath();
            }
            if (this.PrehistoricFloraAmphibianBase.getNavigator().noPath()) {
                BlockPos targetPos = this.findWaterTarget();
                if (targetPos != null) {
                    this.PrehistoricFloraAmphibianBase.getNavigator().tryMoveToXYZ(targetPos.getX() + 0.5D, targetPos.getY() - 0.99D  , targetPos.getZ() + 0.5D, 1.0);

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

    public boolean isAtBottom(BlockPos blockpos) {
        //System.err.println("Testing position");
        if (blockpos.getY() - 1 > 1) {
            BlockPos pos = blockpos.down();
            return (((this.PrehistoricFloraAmphibianBase.world.getBlockState(blockpos)).getMaterial() == Material.WATER || (this.PrehistoricFloraAmphibianBase.world.getBlockState(blockpos)).getMaterial() == Material.CORAL)
                && ((this.PrehistoricFloraAmphibianBase.world.getBlockState(pos)).getMaterial() != Material.WATER));
        }
        return true;
    }

    public BlockPos findWaterTarget() {
        Random rand = this.PrehistoricFloraAmphibianBase.getRNG();
        if (this.PrehistoricFloraAmphibianBase.getAttackTarget() == null) {
            for (int i = 0; i < 10; i++) {
                BlockPos randPos = this.PrehistoricFloraAmphibianBase.getPosition().add(rand.nextInt(16) - 8, rand.nextInt(8) - 4, rand.nextInt(16) - 8);
                //System.err.println("Target " + randPos.getX() + " " + this.PrehistoricFloraFishBase.getPosition().getY() + " " + randPos.getZ());
                if (this.PrehistoricFloraAmphibianBase.world.getBlockState(randPos).getMaterial() == Material.WATER && this.PrehistoricFloraAmphibianBase.isDirectPathBetweenPoints(this.PrehistoricFloraAmphibianBase.getPositionVector(), new Vec3d(randPos.getX() + 0.5, randPos.getY() + 0.5, randPos.getZ() + 0.5))) {
                    return randPos;
                }
            }
        } else {
            BlockPos blockpos1;
            blockpos1 = new BlockPos(this.PrehistoricFloraAmphibianBase.getAttackTarget());
            if (this.PrehistoricFloraAmphibianBase.world.getBlockState(blockpos1).getMaterial() == Material.WATER) {
                return blockpos1;
            }
        }
        return null;
    }
}