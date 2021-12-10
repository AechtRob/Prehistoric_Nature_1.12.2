package net.lepidodendron.entity.ai;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.lepidodendron.entity.base.EntityPrehistoricFloraSwimmingAmphibianBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class AmphibianWander extends AnimationAINoAnimation<EntityPrehistoricFloraSwimmingAmphibianBase> {

    protected float probability;
    protected Animation animation;
    protected double waterPreference;
    protected int executionChance;
    protected boolean mustUpdate;
    protected EntityPrehistoricFloraSwimmingAmphibianBase PrehistoricFloraAmphibianBase;

    public AmphibianWander(EntityPrehistoricFloraSwimmingAmphibianBase PrehistoricFloraAmphibianBase, Animation animation, double waterPreference, int executionchance)
    {
        super(PrehistoricFloraAmphibianBase);
        setMutexBits(1);
        this.PrehistoricFloraAmphibianBase = PrehistoricFloraAmphibianBase;
        this.animation = animation;
        this.waterPreference = waterPreference;
        this.executionChance = executionchance;
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

        if (this.PrehistoricFloraAmphibianBase.getRNG().nextFloat() < 0.3F) {
            Path path = this.PrehistoricFloraAmphibianBase.getNavigator().getPath();
            if (this.PrehistoricFloraAmphibianBase.isInWater()) {
                if (!this.PrehistoricFloraAmphibianBase.getNavigator().noPath() && !isDirectPathBetweenPoints(this.PrehistoricFloraAmphibianBase, this.PrehistoricFloraAmphibianBase.getPositionVector(), new Vec3d(path.getFinalPathPoint().x, path.getFinalPathPoint().y, path.getFinalPathPoint().z)) || path != null && path.getFinalPathPoint() != null && this.PrehistoricFloraAmphibianBase.getDistanceSq(path.getFinalPathPoint().x, path.getFinalPathPoint().y + 0.5, path.getFinalPathPoint().z + 0.5) < 3) {
                   this.PrehistoricFloraAmphibianBase.getNavigator().clearPath();
                   }
            }
            if (this.PrehistoricFloraAmphibianBase.getNavigator().noPath()) {
                //Actively seek out water targets if too far from water:
                BlockPos vec3;
                if (!(this.PrehistoricFloraAmphibianBase.isNearWater(this.entity, this.entity.getPosition()))) {
                    vec3 = this.findWaterTarget(32);
                    if (vec3 == null) {
                        vec3 = this.findLandTarget();
                        if (vec3 == null) {
                            vec3 = this.findAnyTarget();
                        }
                    }
                }
                else {
                    double chooser = this.waterPreference;
                    if (Math.random() > chooser) { //Equal chance of land or water, but sometimes stay still if it's not doing the water thing
                        if (!this.mustUpdate && !this.PrehistoricFloraAmphibianBase.isReallyInWater())
                        {
                            if (this.PrehistoricFloraAmphibianBase.getIdleTime() >= 100)
                            {
                                return false;
                            }

                            if (this.PrehistoricFloraAmphibianBase.getRNG().nextInt(this.executionChance) != 0)
                            {
                                return false;
                            }
                        }
                        vec3 = this.findLandTarget();
                    }
                    else {
                        vec3 = this.findWaterTarget(16);
                    }
                }
                if (vec3 != null) {
                    this.PrehistoricFloraAmphibianBase.getNavigator().tryMoveToXYZ(vec3.getX() + 0.5D, Math.floor(vec3.getY()) + 0.5D  , vec3.getZ() + 0.5D, 1.0);
                    this.mustUpdate = false;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return false;
    }

    public boolean isDirectPathBetweenPoints(Entity entity, Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = entity.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y + (double) entity.height * 0.5D, vec2.z), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    public BlockPos findWaterTarget(int dist) {
        Random rand = this.PrehistoricFloraAmphibianBase.getRNG();
        if (this.PrehistoricFloraAmphibianBase.getAttackTarget() == null) {
            for (int i = 0; i < 64; i++) {
                BlockPos randPos = this.PrehistoricFloraAmphibianBase.getPosition().add(rand.nextInt(dist+1) - (int) (dist/2), rand.nextInt(dist+1) - (int) (dist/2), rand.nextInt(dist+1) - (int) (dist/2));
                boolean visibility = true;
                if (this.PrehistoricFloraAmphibianBase.isReallyInWater()) {
                    visibility = this.PrehistoricFloraAmphibianBase.isDirectPathBetweenPoints(this.PrehistoricFloraAmphibianBase.getPositionVector(), new Vec3d(randPos.getX() + 0.5, randPos.getY() + 0.5, randPos.getZ() + 0.5));
                }
                if (this.PrehistoricFloraAmphibianBase.world.getBlockState(randPos).getMaterial() == Material.WATER && visibility) {
                    if (!(randPos.getY() < 1 || randPos.getY() >= 254)) {
                        return randPos;
                    }
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

    public BlockPos findLandTarget() {
        BlockPos blockpos1;
        if (this.PrehistoricFloraAmphibianBase.getAttackTarget() == null) {
            for (int i = 0; i < 16; i++) {
                Vec3d vec3d = this.entity.getRNG().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.entity, 10, 7) : RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
                if (vec3d != null) {
                    blockpos1 = new BlockPos(vec3d.x, vec3d.y, vec3d.z);
                    if ((this.PrehistoricFloraAmphibianBase.world.getBlockState(blockpos1).getMaterial() == Material.WATER)
                            || (isNearWater(this.entity, blockpos1, this.PrehistoricFloraAmphibianBase.WaterDist()))
                    ) {
                        if (!(blockpos1.getY() < 1 || blockpos1.getY() >= 254)) {
                            return blockpos1;
                        }
                    }
                }
            }
        }
        return null;
    }

    public BlockPos findAnyTarget() {
        BlockPos blockpos1;
        if (this.PrehistoricFloraAmphibianBase.getAttackTarget() == null) {
            for (int i = 0; i < 10; i++) {
                Vec3d vec3d = this.entity.getRNG().nextFloat() >= this.probability ? RandomPositionGenerator.getLandPos(this.entity, 10, 7) : RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
                if (vec3d != null) {
                    blockpos1 = new BlockPos(vec3d.x, vec3d.y, vec3d.z);
                    if (!(blockpos1.getY() < 1 || blockpos1.getY() >= 254)) {
                        return blockpos1;
                    }
                }
            }
        }
        return null;
    }

    public boolean isNearWater(Entity e, BlockPos pos, int WaterDist) {
        int distH = WaterDist;
        if (distH < 1) distH = 1;
        if (distH > 32) distH = 32;
        int distV = 4;
        boolean waterCriteria = false;
        int xct = -distH;
        int yct;
        int zct;
        while ((xct <= distH) && (!waterCriteria)) {
            yct = -distV;
            while ((yct <= distV) && (!waterCriteria)) {
                zct = -distH;
                while ((zct <= distH) && (!waterCriteria)) {
                    if ((Math.pow((int) Math.abs(xct),2) + Math.pow((int) Math.abs(zct),2) <= Math.pow((int) distH,2)) && ((e.world.getBlockState(new BlockPos(pos.getX() + xct, pos.getY() + yct, pos.getZ() + zct))).getMaterial() == Material.WATER)) {
                        waterCriteria = true;
                    }
                    zct = zct + 1;
                }
                yct = yct + 1;
            }
            xct = xct + 1;
        }

        if (waterCriteria || (WaterDist == 0)) return true;

        return false;

    }

}