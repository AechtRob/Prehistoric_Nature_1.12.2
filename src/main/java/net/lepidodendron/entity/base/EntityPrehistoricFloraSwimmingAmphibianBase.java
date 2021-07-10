package net.lepidodendron.entity.base;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.lepidodendron.entity.util.PathNavigateAmphibian;
import net.lepidodendron.entity.util.PathNavigateAmphibianFindWater;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPrehistoricFloraSwimmingAmphibianBase extends EntityPrehistoricFloraAmphibianBase {
    public BlockPos currentTarget;
    @SideOnly(Side.CLIENT)
    public ChainBuffer chainBuffer;
    private int jumpTicks;

    public EntityPrehistoricFloraSwimmingAmphibianBase(World world) {
        super(world);
        this.selectNavigator();
        //this.setPathPriority(PathNodeType.WATER, 5.0F);
        if (FMLCommonHandler.instance().getSide().isClient()) {
            this.chainBuffer = new ChainBuffer();
        }
    }

    protected abstract float getAISpeedSwimmingAmphibian();
    protected boolean isWaterNavigator;
    protected boolean isSeekingWater;
    protected void initEntityAI() {}

    @Override
    public void selectNavigator () {
        if (this.isInWater() && !this.isWaterNavigator) {
            this.moveHelper = new EntityPrehistoricFloraSwimmingAmphibianBase.SwimmingMoveHelper();
            this.navigator = new PathNavigateSwimmer(this, world);
            this.isWaterNavigator = true;
            this.isSeekingWater = false;
            //System.err.println("Navigator changed to " + this.navigator);
        }
        else {
            if (!this.isInWater() && isNearWater(this, this.getPosition()) && (this.isWaterNavigator || this.isSeekingWater)) {
                this.moveHelper = new EntityPrehistoricFloraSwimmingAmphibianBase.WanderMoveHelper();
                this.navigator = new PathNavigateAmphibian(this, world);
                this.isWaterNavigator = false;
                this.isSeekingWater = false;
                //System.err.println("Navigator changed to " + this.navigator);
            }
            else {//Find water!
                if (!this.isInWater() && !isNearWater(this, this.getPosition()) && (this.isWaterNavigator || !this.isSeekingWater)) {
                    this.moveHelper = new EntityPrehistoricFloraSwimmingAmphibianBase.WanderMoveHelper();
                    this.navigator = new PathNavigateAmphibianFindWater(this, world);
                    this.setPathPriority(PathNodeType.WATER, 10F);
                    this.isWaterNavigator = false;
                    this.isSeekingWater = true;
                    //System.err.println("Navigator changed to " + this.navigator);
                }
            }
        }
    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        //this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    public float getMaxTurnDistancePerTick() {
        return 20;
    }

    @Override
    public boolean isInWater() { //Is in water if the block UNDER it is also water. i.e. in shallow water it just walks:
        if (this.world.isAirBlock(this.getPosition())) {return false;}
        IBlockState state = this.world.getBlockState(this.getPosition().down());
        return ((this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL))
                && (state.getMaterial() == Material.WATER ));
    }

    public boolean isActuallyInWater() { //Is in water:
        //return (this.isInWater() || this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL));
        IBlockState state = this.world.getBlockState(this.getPosition());
        IBlockState stated = this.world.getBlockState(this.getPosition().down());
        return ((stated.getMaterial() != Material.WATER) && ((state.getMaterial() == Material.WATER) || this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL)));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < (double) this.world.getSeaLevel() && isInWater();
    }

    @Override
    public int getTalkInterval() {
        return 120;
    }

    @Override
    protected int getExperiencePoints(EntityPlayer player) {
        return 2 + this.world.rand.nextInt(3);
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    public boolean isReallyInWater() {
        return (this.world.getBlockState(this.getPosition()).getMaterial() == Material.WATER) || this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL);
    }

    public boolean isCollidingRim() {
        if (this.isReallyInWater()) {
            //System.err.println("collided");
            Vec3d vec3d = this.getPositionEyes(0);
            Vec3d vec3d1 = this.getLook(0);
            Vec3d vec3d2 = vec3d.add(vec3d1.x * 1, vec3d1.y * 1, vec3d1.z * 1);
            RayTraceResult rayTrace = world.rayTraceBlocks(vec3d, vec3d2, true);
            if (rayTrace != null && rayTrace.hitVec != null) {
                //System.err.println("raytraced");
                BlockPos sidePos = rayTrace.getBlockPos();
                if (world.getBlockState(sidePos).getMaterial() == Material.WATER) {
                    //System.err.println("colliding rim");
                    if (this.moveHelper.action != EntityMoveHelper.Action.JUMPING)
                        return true;
                }
            }
        }
        //System.err.println("not colliding rim");
        return false;
    }

    @Override
    public void onLivingUpdate() {

        if (!this.world.isRemote) {selectNavigator();}
        if (this.isInWater()) {
            super.onLivingUpdate();
        }
        else {
            //Updated from vanilla to allow underwater jumping:
            if (this.jumpTicks > 0) {
                --this.jumpTicks;
            }

            if (this.newPosRotationIncrements > 0 && !this.canPassengerSteer()) {
                double d0 = this.posX + (this.interpTargetX - this.posX) / (double) this.newPosRotationIncrements;
                double d1 = this.posY + (this.interpTargetY - this.posY) / (double) this.newPosRotationIncrements;
                double d2 = this.posZ + (this.interpTargetZ - this.posZ) / (double) this.newPosRotationIncrements;
                double d3 = MathHelper.wrapDegrees(this.interpTargetYaw - (double) this.rotationYaw);
                this.rotationYaw = (float) ((double) this.rotationYaw + d3 / (double) this.newPosRotationIncrements);
                this.rotationPitch = (float) ((double) this.rotationPitch + (this.interpTargetPitch - (double) this.rotationPitch) / (double) this.newPosRotationIncrements);
                --this.newPosRotationIncrements;
                this.setPosition(d0, d1, d2);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            } else if (!this.isServerWorld()) {
                this.motionX *= 0.98D;
                this.motionY *= 0.98D;
                this.motionZ *= 0.98D;
            }

            if (Math.abs(this.motionX) < 0.003D) {
                this.motionX = 0.0D;
            }

            if (Math.abs(this.motionY) < 0.003D) {
                this.motionY = 0.0D;
            }

            if (Math.abs(this.motionZ) < 0.003D) {
                this.motionZ = 0.0D;
            }

            this.world.profiler.startSection("ai");

            if (this.isMovementBlocked()) {
                this.isJumping = false;
                this.moveStrafing = 0.0F;
                this.moveForward = 0.0F;
                this.randomYawVelocity = 0.0F;
            } else if (this.isServerWorld()) {
                this.world.profiler.startSection("newAi");
                this.updateEntityActionState();
                this.world.profiler.endSection();
            }

            this.world.profiler.endSection();
            this.world.profiler.startSection("jump");

            if (this.isJumping) {
                if (this.isInWater() && this.jumpTicks == 0) {
                    this.jump();
                    this.jumpTicks = 10;
                } else if (this.isInLava()) {
                    this.handleJumpLava();
                } else if (this.onGround && this.jumpTicks == 0) {
                    this.jump();
                    this.jumpTicks = 10;
                }
            } else {
                this.jumpTicks = 0;
            }

            this.world.profiler.endSection();
            this.world.profiler.startSection("travel");
            this.moveStrafing *= 0.98F;
            this.moveForward *= 0.98F;
            this.randomYawVelocity *= 0.9F;
            //this.updateElytra();
            this.travel(this.moveStrafing, this.moveVertical, this.moveForward);
            this.world.profiler.endSection();
            this.world.profiler.startSection("push");
            this.collideWithNearbyEntities();
            this.world.profiler.endSection();
        }
    }

    public void onEntityUpdate()
    {
        super.onEntityUpdate();
    }

    public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y, vec2.z), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    @Override
    public void travel(float strafe, float vertical, float forward) {
        float f4;
        if (this.isServerWorld()) {
            if (this.isInWater()) {
                this.moveRelative(strafe, vertical, forward, 0.1F);
                f4 = 0.8F;
                float speedModifier = (float) EnchantmentHelper.getDepthStriderModifier(this);
                if (speedModifier > 3.0F) {
                    speedModifier = 3.0F;
                }

                BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ);

                if (!this.onGround) {
                    speedModifier *= 0.5F;
                }
                if (speedModifier > 0.0F) {
                    f4 += (0.54600006F - f4) * speedModifier / 3.0F;
                }
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

                if (this.collidedHorizontally && this.isCollidingRim())
                {
                    this.motionY = 0.05D;
                }

                this.motionX *= f4;
                this.motionX *= 0.9;
                this.motionY *= 0.9;
                this.motionY *= f4;
                this.motionZ *= 0.9;
                this.motionZ *= f4;
            } else {
                //System.err.println("Vanilla travel hander");
                super.travel(strafe, vertical, forward);
            }
        }
        this.prevLimbSwingAmount = this.limbSwingAmount;
        double deltaX = this.posX - this.prevPosX;
        double deltaZ = this.posZ - this.prevPosZ;
        double deltaY = this.posY - this.prevPosY;
        float delta = MathHelper.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ) * 4.0F;
        if (delta > 1.0F) {
            delta = 1.0F;
        }
        this.limbSwingAmount += (delta - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    public class WanderMoveHelper extends EntityMoveHelper {

        private final EntityPrehistoricFloraSwimmingAmphibianBase EntityBase = EntityPrehistoricFloraSwimmingAmphibianBase.this;

        public WanderMoveHelper() {
            super(EntityPrehistoricFloraSwimmingAmphibianBase.this);
        }

        public void onUpdateMoveHelper() {

            if (this.action == Action.STRAFE) {
                //float f = (float) this.EntityBase.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
                float f = getAISpeedSwimmingAmphibian();
                float f1 = (float) this.speed * f;
                float f2 = this.moveForward;
                float f3 = this.moveStrafe;
                float f4 = MathHelper.sqrt(f2 * f2 + f3 * f3);

                if (f4 < 1.0F) {
                    f4 = 1.0F;
                }

                f4 = f1 / f4;
                f2 = f2 * f4;
                f3 = f3 * f4;
                float f5 = MathHelper.sin(this.EntityBase.rotationYaw * 0.017453292F);
                float f6 = MathHelper.cos(this.EntityBase.rotationYaw * 0.017453292F);
                float f7 = f2 * f6 - f3 * f5;
                float f8 = f3 * f6 + f2 * f5;
                PathNavigate pathnavigate = this.EntityBase.getNavigator();

                if (pathnavigate != null) {
                    NodeProcessor nodeprocessor = pathnavigate.getNodeProcessor();

                    if (nodeprocessor != null && nodeprocessor.getPathNodeType(this.EntityBase.world, MathHelper.floor(this.EntityBase.posX + (double) f7), MathHelper.floor(this.EntityBase.posY), MathHelper.floor(this.EntityBase.posZ + (double) f8)) != PathNodeType.WALKABLE) {
                        this.moveForward = 1.0F;
                        this.moveStrafe = 0.0F;
                        f1 = f;
                    }
                }

                this.EntityBase.setAIMoveSpeed(f1);
                this.EntityBase.setMoveForward(this.moveForward);
                this.EntityBase.setMoveStrafing(this.moveStrafe);
                this.action = Action.WAIT;
            } else if (this.action == Action.MOVE_TO) {
                this.action = Action.WAIT;
                double d0 = this.posX - this.EntityBase.posX;
                double d1 = this.posZ - this.EntityBase.posZ;
                double d2 = this.posY - this.EntityBase.posY;
                double d3 = d0 * d0 + d2 * d2 + d1 * d1;

                if (d3 < 2.500000277905201E-7D) {
                    this.EntityBase.setMoveForward(0.0F);
                    return;
                }

                float turn = (EntityBase.getMaxTurnDistancePerTick());
                float f9 = (float) (MathHelper.atan2(d1, d0) * (180D / Math.PI)) - 90;
                this.EntityBase.rotationYaw = this.limitAngle(this.EntityBase.rotationYaw, f9, turn);
                //this.EntityBase.setAIMoveSpeed((float) (this.speed * this.EntityBase.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));

                //float speed = getAISpeedLand();
                //this.EntityBase.setAIMoveSpeed((float) (0.4f * this.speed * this.EntityBase.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
                this.EntityBase.setAIMoveSpeed((float) (0.6f * this.speed * getAISpeedSwimmingAmphibian()));


                //Land:
                if (!this.EntityBase.isInWater()) {
                    this.EntityBase.setAIMoveSpeed((float) (0.6f * this.speed * getAISpeedSwimmingAmphibian()));
                }

                if (
                    (this.EntityBase.collidedHorizontally)
                    && (d2 > (double) this.EntityBase.stepHeight && d0 * d0 + d1 * d1 < (double) Math.max(1.0F, this.EntityBase.width))
                ) {
                    this.EntityBase.getJumpHelper().setJumping();
                    this.action = Action.JUMPING;
                    //System.err.println("action: " +  this.action);
                }
            } else if (this.action == Action.JUMPING) {
                //float speed = getAISpeedLand();
                //this.EntityBase.setAIMoveSpeed((float) (speed * this.EntityBase.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
                this.EntityBase.setAIMoveSpeed((float) (this.speed * getAISpeedSwimmingAmphibian()));

                if (this.EntityBase.onGround) {
                    this.action = Action.WAIT;
                }
            } else {
                this.EntityBase.setMoveForward(0.0F);
            }
        }
    }

    public class SwimmingMoveHelper extends EntityMoveHelper {
        private final EntityPrehistoricFloraSwimmingAmphibianBase EntityBase = EntityPrehistoricFloraSwimmingAmphibianBase.this;

        public SwimmingMoveHelper() {
            super(EntityPrehistoricFloraSwimmingAmphibianBase.this);
        }

        @Override
        public void onUpdateMoveHelper() {
            //System.err.println("Action " + this.action);
            //System.err.println("NoPath " + this.EntityBase.getNavigator().noPath());
            if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.EntityBase.getNavigator().noPath()) {
                double distanceX = this.posX - this.EntityBase.posX;
                double distanceY = this.posY - this.EntityBase.posY;
                double distanceZ = this.posZ - this.EntityBase.posZ;
                double distance = Math.abs(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
                distance = MathHelper.sqrt(distance);
                distanceY /= distance;
                float angle = (float) (Math.atan2(distanceZ, distanceX) * 180.0D / Math.PI) - 90.0F;

                this.EntityBase.rotationYaw = this.limitAngle(this.EntityBase.rotationYaw, angle, 20.0F);
                float speed = getAISpeedSwimmingAmphibian();
                this.EntityBase.setAIMoveSpeed(speed);

                this.EntityBase.motionY += (double) this.EntityBase.getAIMoveSpeed() * distanceY * 0.1D;
            } else {
                this.EntityBase.setAIMoveSpeed(0.0F);
            }
        }
    }
}