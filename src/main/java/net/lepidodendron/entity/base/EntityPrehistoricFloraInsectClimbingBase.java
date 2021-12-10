package net.lepidodendron.entity.base;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.util.PathNavigateClimberNoWater;
import net.lepidodendron.item.entities.ItemUnknownEgg;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public abstract class EntityPrehistoricFloraInsectClimbingBase extends EntityCreature implements IAnimatedEntity {
    public BlockPos currentTarget;
    @SideOnly(Side.CLIENT)
    public ChainBuffer chainBuffer;
    private int animationTick;
    private int jumpTicks;
    private static final DataParameter<Integer> TICKS = EntityDataManager.createKey(EntityPrehistoricFloraInsectClimbingBase.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> ISMOVING = EntityDataManager.createKey(EntityPrehistoricFloraInsectClimbingBase.class, DataSerializers.BOOLEAN);

    public Animation ATTACK_ANIMATION;
    public Animation currentAnimation;

    public EntityPrehistoricFloraInsectClimbingBase(World world) {
        super(world);
        if (FMLCommonHandler.instance().getSide().isClient()) {
            this.chainBuffer = new ChainBuffer();
        }
        ATTACK_ANIMATION = Animation.create(this.getAttackLength());
    }

    public boolean getIsMoving() {
        return this.dataManager.get(ISMOVING);
    }

    public void setIsMoving(boolean isMoving) {
        this.dataManager.set(ISMOVING, isMoving);
    }

    public int getAttackLength() {
        return 20;
    }

    public abstract boolean dropsEggs();

    public abstract boolean laysEggs();

    public String tagEgg () {return "";}

    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityPrehistoricFloraInsectClimbingBase.class, DataSerializers.BYTE);

    protected PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateClimberNoWater(this, worldIn);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(CLIMBING, Byte.valueOf((byte)0));
        this.dataManager.register(TICKS, 0);
        this.dataManager.register(ISMOVING, false);
    }

    @Override
    public boolean attackEntityFrom(DamageSource ds, float f) {
        if (ds == DamageSource.FALL) {
            return false;
        }
        return super.attackEntityFrom(ds, f);
    }

    @Override
    public int getAnimationTick() {
        return animationTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return currentAnimation == null ? NO_ANIMATION : currentAnimation;
    }

    @Override
    public void setAnimation(Animation animation) {
        if (this.getAnimation() != animation) {
            this.currentAnimation = animation;
            setAnimationTick(0);
        }
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{ATTACK_ANIMATION};
    }

    protected void initEntityAI() {}

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    public abstract String getTexture();

    public int getTicks() {
       return this.dataManager.get(TICKS);
    }

    public void setTicks(int ticks) {
        this.dataManager.set(TICKS, ticks);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setTicks(0);
        return livingdata;
    }

    public boolean getCanBreed() {
        return this.getTicks() > 24000; //If the mob has done not bred for a MC day
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("Ticks", this.getTicks());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setTicks(compound.getInteger("Ticks"));
    }

    public void onEntityUpdate() {
        //AnimationHandler.INSTANCE.updateAnimations(this);
        super.onEntityUpdate();

        //General ticker (for babies etc.)
        int ii = this.getTicks();
        if (this.isEntityAlive()) {
            ++ii;
            //limit at 48000 (two MC days) and then reset:
            if (ii >= 48000) {
                ii = 0;
            }
            this.setTicks(ii);
        }

        //Drop an egg perhaps:
        if (!world.isRemote && this.getCanBreed() && this.dropsEggs() && LepidodendronConfig.doMultiplyMobs) {
            if (Math.random() > 0.5) {
                ItemStack itemstack = new ItemStack(ItemUnknownEgg.block, (int) (1));
                if (!itemstack.hasTagCompound()) {
                    itemstack.setTagCompound(new NBTTagCompound());
                }
                String stringEgg = EntityRegistry.getEntry(this.getClass()).getRegistryName().toString();
                itemstack.getTagCompound().setString("creature", stringEgg);
                EntityItem entityToSpawn = new EntityItem(world, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), itemstack);
                entityToSpawn.setPickupDelay(10);
                this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                world.spawnEntity(entityToSpawn);
            }
            this.setTicks(0);
        }

        //Lay eggs perhaps:
        if (!world.isRemote && this.laysEggs() && LepidodendronConfig.doMultiplyMobs && this.getCanBreed()
        ) {
            if (((this.testLay(world, this.getPosition()) || this.testLay(world, this.getPosition().down())) && this.getTicks() > 0)
            ) {
                if (Math.random() > 0.5) {
                    this.setTicks(-50); //Flag this as stationary for egg-laying
                }
            }
            if ((this.testLay(world, this.getPosition()) || this.testLay(world, this.getPosition().down())) && this.getTicks() > -30 && this.getTicks() < 0) {
                //Is stationary for egg-laying:
                String stringEgg = LepidodendronMod.MODID + ":" + this.tagEgg();
                if (this.testLay(world, this.getPosition())) {
                    this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                    TileEntity te = world.getTileEntity(this.getPosition());
                    if (te != null) {
                        te.getTileData().setString("egg", stringEgg);
                    }
                    IBlockState state = world.getBlockState(this.getPosition());
                    world.notifyBlockUpdate(this.getPosition(), state, state, 3);
                } else if (this.testLay(world, this.getPosition().down())) {
                    this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                    TileEntity te = world.getTileEntity(this.getPosition().down());
                    if (te != null) {
                        te.getTileData().setString("egg", stringEgg);
                    }
                    IBlockState state = world.getBlockState(this.getPosition().down());
                    world.notifyBlockUpdate(this.getPosition().down(), state, state, 3);
                }
                this.setTicks(-20);
            }
        }
    }

    public boolean testLay(World world, BlockPos pos) {
        return false;
    }

    public void onUpdate()
    {
        super.onUpdate();

        if (!this.world.isRemote)
        {
            boolean isCollided = this.collidedHorizontally;
            boolean isFacing = false;
            boolean isSolid = false;

            Vec3d vec3d = this.getPositionEyes(0);
            Vec3d vec3d1 = this.getLook(0);
            //Vec3d vec3d1 = this.getForward();
            if (vec3d1 != null & vec3d != null) {
                Vec3d vec3d2 = vec3d.add(vec3d1.x * 1, vec3d1.y * 1, vec3d1.z * 1);
                RayTraceResult rayTrace = world.rayTraceBlocks(vec3d, vec3d2, true);
                if (rayTrace != null && rayTrace.hitVec != null) {
                    BlockPos sidePos = rayTrace.getBlockPos();
                    if (world.isSideSolid(sidePos, rayTrace.sideHit)) {
                        isFacing = true;
                        isSolid = world.getBlockState(sidePos).getBlockFaceShape(world, sidePos, rayTrace.sideHit) == BlockFaceShape.SOLID;
                    }
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
            Vec3d vec3d1 = this.getLook(0);
            //Vec3d vec3d1 = this.getForward();
            if (vec3d1 != null & vec3d != null) {
                Vec3d vec3d2 = vec3d.add(vec3d1.x * 1, vec3d1.y * 1, vec3d1.z * 1);
                RayTraceResult rayTrace = world.rayTraceBlocks(vec3d, vec3d2, true);
                if (rayTrace != null && rayTrace.hitVec != null) {
                    BlockPos sidePos = rayTrace.getBlockPos();
                    if (world.isSideSolid(sidePos, rayTrace.sideHit)) {
                        return rayTrace.sideHit.getOpposite();
                    }
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

    public void eatItem(ItemStack stack) {
        if (stack != null && stack.getItem() != null) {
            float itemHealth = 0.5F; //Default minimal nutrition
            if (stack.getItem() instanceof ItemFood) {
                itemHealth = ((ItemFood) stack.getItem()).getHealAmount(stack);
            }
            this.setHealth(Math.min(this.getHealth() + itemHealth, (float) this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()));
            stack.shrink(1);
            if (this.getAnimation() == NO_ANIMATION && !world.isRemote) {
                //this.setAnimation(ATTACK_ANIMATION);
                this.setAnimation(ATTACK_ANIMATION);
                SoundEvent soundevent = SoundEvents.ENTITY_GENERIC_EAT;
                this.getEntityWorld().playSound(null, this.getPosition(), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

    public AxisAlignedBB getAttackBoundingBox() {
        float size = 1 * 0.25F;
        return this.getEntityBoundingBox().grow(1.0F + size, 1.0F + size, 1.0F + size);
    }

    @Override
    public void travel(float strafe, float vertical, float forward)
    {
        if (this.isServerWorld() || this.canPassengerSteer())
        {
            if (!this.isInWater())
            {
                if (!this.isInLava())
                {
                    if (this.isElytraFlying())
                    {
                        if (this.motionY > -0.5D)
                        {
                            this.fallDistance = 1.0F;
                        }

                        Vec3d vec3d = this.getLookVec();
                        float f = this.rotationPitch * 0.017453292F;
                        double d6 = Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z);
                        double d8 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
                        double d1 = vec3d.length();
                        float f4 = MathHelper.cos(f);
                        f4 = (float)((double)f4 * (double)f4 * Math.min(1.0D, d1 / 0.4D));
                        this.motionY += -0.08D + (double)f4 * 0.06D;

                        if (this.motionY < 0.0D && d6 > 0.0D)
                        {
                            double d2 = this.motionY * -0.1D * (double)f4;
                            this.motionY += d2;
                            this.motionX += vec3d.x * d2 / d6;
                            this.motionZ += vec3d.z * d2 / d6;
                        }

                        if (f < 0.0F)
                        {
                            double d10 = d8 * (double)(-MathHelper.sin(f)) * 0.04D;
                            this.motionY += d10 * 3.2D;
                            this.motionX -= vec3d.x * d10 / d6;
                            this.motionZ -= vec3d.z * d10 / d6;
                        }

                        if (d6 > 0.0D)
                        {
                            this.motionX += (vec3d.x / d6 * d8 - this.motionX) * 0.1D;
                            this.motionZ += (vec3d.z / d6 * d8 - this.motionZ) * 0.1D;
                        }

                        this.motionX *= 0.9900000095367432D;
                        this.motionY *= 0.9800000190734863D;
                        this.motionZ *= 0.9900000095367432D;
                        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

                        if (this.collidedHorizontally && !this.world.isRemote)
                        {
                            double d11 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
                            double d3 = d8 - d11;
                            float f5 = (float)(d3 * 10.0D - 3.0D);

                            if (f5 > 0.0F)
                            {
                                this.playSound(this.getFallSound((int)f5), 1.0F, 1.0F);
                                this.attackEntityFrom(DamageSource.FLY_INTO_WALL, f5);
                            }
                        }

                        if (this.onGround && !this.world.isRemote)
                        {
                            this.setFlag(7, false);
                        }
                    }
                    else
                    {
                        float f6 = 0.91F;
                        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ);

                        if (this.onGround)
                        {
                            IBlockState underState = this.world.getBlockState(blockpos$pooledmutableblockpos);
                            f6 = underState.getBlock().getSlipperiness(underState, this.world, blockpos$pooledmutableblockpos, this) * 0.91F;
                        }

                        float f7 = 0.16277136F / (f6 * f6 * f6);
                        float f8;

                        if (this.onGround)
                        {
                            f8 = this.getAIMoveSpeed() * f7;
                        }
                        else
                        {
                            f8 = this.jumpMovementFactor;
                        }

                        this.moveRelative(strafe, vertical, forward, f8);
                        f6 = 0.91F;

                        if (this.onGround)
                        {
                            IBlockState underState = this.world.getBlockState(blockpos$pooledmutableblockpos.setPos(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ));
                            f6 = underState.getBlock().getSlipperiness(underState, this.world, blockpos$pooledmutableblockpos, this) * 0.91F;
                        }

                        if (this.isOnLadder())
                        {
                            float f9 = 0.15F;
                            this.motionX = MathHelper.clamp(this.motionX, -0.15000000596046448D, 0.15000000596046448D);
                            this.motionZ = MathHelper.clamp(this.motionZ, -0.15000000596046448D, 0.15000000596046448D);
                            this.fallDistance = 0.0F;

                            if (this.motionY < -0.15D)
                            {
                                this.motionY = -0.15D;
                            }

                        }

                        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

                        if (this.motionX != 0 || this.motionZ != 0) {
                            this.setIsMoving(true);
                        }
                        else {
                            this.setIsMoving(false);
                        }

                        if (this.collidedHorizontally && this.isOnLadder())
                        {
                            this.motionY = 0.2D;
                        }

                        if (this.isPotionActive(MobEffects.LEVITATION))
                        {
                            this.motionY += (0.05D * (double)(this.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motionY) * 0.2D;
                        }
                        else
                        {
                            blockpos$pooledmutableblockpos.setPos(this.posX, 0.0D, this.posZ);

                            if (!this.world.isRemote || this.world.isBlockLoaded(blockpos$pooledmutableblockpos) && this.world.getChunk(blockpos$pooledmutableblockpos).isLoaded())
                            {
                                if (!this.hasNoGravity())
                                {
                                    this.motionY -= 0.08D;
                                }
                            }
                            else if (this.posY > 0.0D)
                            {
                                this.motionY = -0.1D;
                            }
                            else
                            {
                                this.motionY = 0.0D;
                            }
                        }

                        this.motionY *= 0.9800000190734863D;
                        this.motionX *= (double)f6;
                        this.motionZ *= (double)f6;
                        blockpos$pooledmutableblockpos.release();
                    }
                }
                else
                {
                    double d4 = this.posY;
                    this.moveRelative(strafe, vertical, forward, 0.02F);
                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                    this.motionX *= 0.5D;
                    this.motionY *= 0.5D;
                    this.motionZ *= 0.5D;

                    if (!this.hasNoGravity())
                    {
                        this.motionY -= 0.02D;
                    }

                    if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d4, this.motionZ))
                    {
                        this.motionY = 0.30000001192092896D;
                    }
                }
            }
            else
            {
                double d0 = this.posY;
                float f1 = this.getWaterSlowDown();
                float f2 = 0.02F;
                float f3 = (float) EnchantmentHelper.getDepthStriderModifier(this);

                if (f3 > 3.0F)
                {
                    f3 = 3.0F;
                }

                if (!this.onGround)
                {
                    f3 *= 0.5F;
                }

                if (f3 > 0.0F)
                {
                    f1 += (0.54600006F - f1) * f3 / 3.0F;
                    f2 += (this.getAIMoveSpeed() - f2) * f3 / 3.0F;
                }

                this.moveRelative(strafe, vertical, forward, f2);
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                this.motionX *= (double)f1;
                this.motionY *= 0.800000011920929D;
                this.motionZ *= (double)f1;

                if (!this.hasNoGravity())
                {
                    this.motionY -= 0.02D;
                }

                if (this.collidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ))
                {
                    this.motionY = 0.30000001192092896D;
                }
            }
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d5 = this.posX - this.prevPosX;
        double d7 = this.posZ - this.prevPosZ;
        double d9 = this instanceof net.minecraft.entity.passive.EntityFlying ? this.posY - this.prevPosY : 0.0D;
        float f10 = MathHelper.sqrt(d5 * d5 + d9 * d9 + d7 * d7) * 4.0F;

        if (f10 > 1.0F)
        {
            f10 = 1.0F;
        }

        this.limbSwingAmount += (f10 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

}