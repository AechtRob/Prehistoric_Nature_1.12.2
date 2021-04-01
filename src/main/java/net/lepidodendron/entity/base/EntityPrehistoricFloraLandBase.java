package net.lepidodendron.entity.base;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityPrehistoricFloraLandBase extends EntityCreature implements IAnimatedEntity {
    public BlockPos currentTarget;
    @SideOnly(Side.CLIENT)
    public ChainBuffer chainBuffer;
    private int jumpTicks;

    public EntityPrehistoricFloraLandBase(World world) {
        super(world);
        this.setPathPriority(PathNodeType.WATER, -1.0F);
        this.moveHelper = new EntityPrehistoricFloraLandBase.WanderMoveHelper();
        this.navigator = new PathNavigateGround(this, world);
        if (FMLCommonHandler.instance().getSide().isClient()) {
            this.chainBuffer = new ChainBuffer();
        }
    }

    protected abstract float getAISpeedLand();

    private Animation animation = NO_ANIMATION;

    public static final Animation ANIMATION_WANDER = Animation.create(0);

    protected void initEntityAI() {}

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    public abstract String getTexture();

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    public float getMaxTurnDistancePerTick() {
        return 20;
    }

    public boolean isNotColliding() {
        return this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
    }

    @Override
    public int getTalkInterval() {
        return 120;
    }

    @Override
    protected int getExperiencePoints(EntityPlayer player) {
        return 1 + this.world.rand.nextInt(3);
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    public void onEntityUpdate()
    {
        super.onEntityUpdate();
    }

    public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y, vec2.z), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    public void onLivingUpdate()
    {
        if (this.jumpTicks > 0)
        {
            --this.jumpTicks;
        }

        if (this.newPosRotationIncrements > 0 && !this.canPassengerSteer())
        {
            double d0 = this.posX + (this.interpTargetX - this.posX) / (double)this.newPosRotationIncrements;
            double d1 = this.posY + (this.interpTargetY - this.posY) / (double)this.newPosRotationIncrements;
            double d2 = this.posZ + (this.interpTargetZ - this.posZ) / (double)this.newPosRotationIncrements;
            double d3 = MathHelper.wrapDegrees(this.interpTargetYaw - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + d3 / (double)this.newPosRotationIncrements);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.interpTargetPitch - (double)this.rotationPitch) / (double)this.newPosRotationIncrements);
            --this.newPosRotationIncrements;
            this.setPosition(d0, d1, d2);
            this.setRotation(this.rotationYaw, this.rotationPitch);
        }
        else if (!this.isServerWorld())
        {
            this.motionX *= 0.98D;
            this.motionY *= 0.98D;
            this.motionZ *= 0.98D;
        }

        if (Math.abs(this.motionX) < 0.003D)
        {
            this.motionX = 0.0D;
        }

        if (Math.abs(this.motionY) < 0.003D)
        {
            this.motionY = 0.0D;
        }

        if (Math.abs(this.motionZ) < 0.003D)
        {
            this.motionZ = 0.0D;
        }

        this.world.profiler.startSection("ai");

        if (this.isMovementBlocked())
        {
            this.isJumping = false;
            this.moveStrafing = 0.0F;
            this.moveForward = 0.0F;
            this.randomYawVelocity = 0.0F;
        }
        else if (this.isServerWorld())
        {
            this.world.profiler.startSection("newAi");
            this.updateEntityActionState();
            this.world.profiler.endSection();
        }

        this.world.profiler.endSection();
        this.world.profiler.startSection("jump");

        if (this.isJumping)
        {
            if (this.isInWater() && this.jumpTicks == 0)
            {
                this.jump();
                this.jumpTicks = 10;
            }
            else if (this.isInLava())
            {
                this.handleJumpLava();
            }
            else if (this.onGround && this.jumpTicks == 0)
            {
                this.jump();
                this.jumpTicks = 10;
            }
        }
        else
        {
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

    public class WanderMoveHelper extends EntityMoveHelper {

        private final EntityPrehistoricFloraLandBase EntityBase = EntityPrehistoricFloraLandBase.this;

        public WanderMoveHelper() {
            super(EntityPrehistoricFloraLandBase.this);
        }

        public void onUpdateMoveHelper() {
            if (this.action == Action.STRAFE) {
                //float f = (float) this.EntityBase.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
                float f = getAISpeedLand();
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
                this.EntityBase.setAIMoveSpeed((float) (0.4f * this.speed * getAISpeedLand()));


                //Testing mode:
                //this.EntityBase.setAIMoveSpeed(0f);

                if ((this.EntityBase.collidedHorizontally)
                    && (d2 > (double) this.EntityBase.stepHeight && d0 * d0 + d1 * d1 < (double) Math.max(1.0F, this.EntityBase.width))
                ) {
                    this.EntityBase.getJumpHelper().setJumping();
                    this.action = Action.JUMPING;
                }
            } else if (this.action == Action.JUMPING) {
                //float speed = getAISpeedLand();
                //this.EntityBase.setAIMoveSpeed((float) (speed * this.EntityBase.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
                this.EntityBase.setAIMoveSpeed((float) (this.speed * getAISpeedLand()));

                if (this.EntityBase.onGround) {
                    this.action = Action.WAIT;
                }
            } else {
                this.EntityBase.setMoveForward(0.0F);
            }
        }
    }
}