package net.lepidodendron.entity.base;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public abstract class EntityPrehistoricFloraInsectClimbingBase extends EntityCreature implements IAnimatedEntity {
    public BlockPos currentTarget;
    @SideOnly(Side.CLIENT)
    public ChainBuffer chainBuffer;
    private int jumpTicks;

    public EntityPrehistoricFloraInsectClimbingBase(World world) {
        super(world);
        if (FMLCommonHandler.instance().getSide().isClient()) {
            this.chainBuffer = new ChainBuffer();
        }
    }

    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityPrehistoricFloraInsectClimbingBase.class, DataSerializers.BYTE);

    protected PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateClimber(this, worldIn);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(CLIMBING, Byte.valueOf((byte)0));
    }

    @Override
    public boolean attackEntityFrom(DamageSource ds, float f) {
        if (ds == DamageSource.FALL) {
            return false;
        }
        return super.attackEntityFrom(ds, f);
    }

    private Animation animation = NO_ANIMATION;

    public static final Animation ANIMATION_WANDER = Animation.create(0);

    protected void initEntityAI() {}

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    public abstract String getTexture();

    public void onUpdate()
    {
        super.onUpdate();

        if (!this.world.isRemote)
        {
            boolean isCollided = this.collidedHorizontally;
            boolean isFacing = false;
            boolean isSolid = false;

            Vec3d vec3d = this.getPositionEyes(0);
            //Vec3d vec3d1 = this.getLook(0);
            Vec3d vec3d1 = this.getForward();
            Vec3d vec3d2 = vec3d.add(vec3d1.x * 1, vec3d1.y * 1, vec3d1.z * 1);
            RayTraceResult rayTrace = world.rayTraceBlocks(vec3d, vec3d2, true);
            if (rayTrace != null && rayTrace.hitVec != null) {
                BlockPos sidePos = rayTrace.getBlockPos();
                if (world.isSideSolid(sidePos, rayTrace.sideHit)) {
                    isFacing = true;
                    isSolid = world.getBlockState(sidePos).getBlockFaceShape(world, sidePos, rayTrace.sideHit) == BlockFaceShape.SOLID;
                }
            }

            this.setBesideClimbableBlock(isCollided && isFacing && isSolid && (this.getMoveHelper().getSpeed() != 0));
        }
    }

    public boolean getUpBlocked() {
        return this.world.getBlockState(this.getPosition().up()).getBlock().isPassable(this.world, this.getPosition().up());
    }

    @Override
    protected SoundEvent getSwimSound()
    {
        return SoundEvents.ENTITY_HOSTILE_SWIM;
    }

    @Override
    protected SoundEvent getSplashSound()
    {
        return SoundEvents.ENTITY_HOSTILE_SPLASH;
    }

    @Override
    protected SoundEvent getFallSound(int heightIn)
    {
        return null;
    }

    @Override
    public boolean isOnLadder()
    {
        return this.isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock()
    {
        return (((Byte)this.dataManager.get(CLIMBING)).byteValue() & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing)
    {
        byte b0 = ((Byte)this.dataManager.get(CLIMBING)).byteValue();

        if (climbing)
        {
            b0 = (byte)(b0 | 1);
        }
        else
        {
            b0 = (byte)(b0 & -2);
        }

        this.dataManager.set(CLIMBING, Byte.valueOf(b0));
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public boolean getClimbing() {
        return this.dataManager.get(CLIMBING) != Byte.valueOf((byte)0);
    }

    public EnumFacing getClimbingFacing() {
        if (getClimbing()) {
            Vec3d vec3d = this.getPositionEyes(0);
            //Vec3d vec3d1 = this.getLook(0);
            Vec3d vec3d1 = this.getForward();
            Vec3d vec3d2 = vec3d.add(vec3d1.x * 1, vec3d1.y * 1, vec3d1.z * 1);
            RayTraceResult rayTrace = world.rayTraceBlocks(vec3d, vec3d2, true);
            if (rayTrace != null && rayTrace.hitVec != null) {
                BlockPos sidePos = rayTrace.getBlockPos();
                if (world.isSideSolid(sidePos, rayTrace.sideHit)) {
                    return rayTrace.sideHit.getOpposite();
                }
            }
        }
        return EnumFacing.DOWN;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2.0D);
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        //this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable()
    {
        return null;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public int getTalkInterval() {
        return 120;
    }

    @Override
    protected int getExperiencePoints(EntityPlayer player) {
        return 1 + this.world.rand.nextInt(1);
    }

    @Override
    public float getEyeHeight()
    {
        return this.height/2;
    }

    public boolean isDirectPathBetweenPoints(Entity entity, Vec3d vec1, Vec3d vec2) {
        RayTraceResult movingobjectposition = entity.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y + (double) entity.height * 0.5D, vec2.z), false, true, false);
        return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
    }

    @Override
    public void onLivingUpdate()
    {
        if (this.getClimbing()) {
            switch (getClimbingFacing()) {
                case NORTH:
                default:
                    this.renderYawOffset = 180.0F;
                    this.prevRenderYawOffset = 180.0F;
                    this.rotationYaw = 180.0F;
                    this.prevRotationYaw = 180.0F;
                    this.rotationYawHead = 180.0F;
                    this.prevRotationYawHead = 180.0F;
                    this.motionX = 0.0D;
                    //this.motionZ = 0.0D;
                    break;

                case SOUTH:
                    this.renderYawOffset = 0;
                    this.prevRenderYawOffset = 0;
                    this.rotationYaw = 0;
                    this.prevRotationYaw = 0;
                    this.rotationYawHead = 0;
                    this.prevRotationYawHead = 0;
                    this.motionX = 0.0D;
                    //this.motionZ = 0.0D;
                    break;

                case WEST:
                    this.renderYawOffset = 90.0F;
                    this.prevRenderYawOffset = 90.0F;
                    this.rotationYaw = 90.0F;
                    this.prevRotationYaw = 90.0F;
                    this.rotationYawHead = 90.0F;
                    this.prevRotationYawHead = 90.0F;
                    //this.motionX = 0.0D;
                    this.motionZ = 0.0D;
                    break;

                case EAST:
                    this.renderYawOffset = 270.0F;
                    this.prevRenderYawOffset = 270.0F;
                    this.rotationYaw = 270.0F;
                    this.prevRotationYaw = 270.0F;
                    this.rotationYawHead = 270.0F;
                    this.prevRotationYawHead = 270.0F;
                    //this.motionX = 0.0D;
                    this.motionZ = 0.0D;
                    break;
            }
        }
        else {
            this.renderYawOffset = this.rotationYaw;
        }

        //Brought in from vanilla in case it needs edits:

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
            if (this.isInWater())
            {
                this.handleJumpWater();
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

}