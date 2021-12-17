package net.lepidodendron.entity.base;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.FlyingLandWanderAvoidWaterAI;
import net.lepidodendron.entity.util.PathNavigateFlyingNoWater;
import net.lepidodendron.entity.util.PathNavigateGroundNoWater;
import net.lepidodendron.item.entities.ItemUnknownEgg;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class EntityPrehistoricInsectFlyingWalkingBase extends EntityTameable implements IAnimatedEntity {
    public BlockPos currentTarget;
    @SideOnly(Side.CLIENT)
    public ChainBuffer chainBuffer;
    public Animation LAY_ANIMATION;
    private static final DataParameter<Integer> TICKS = EntityDataManager.createKey(EntityPrehistoricInsectFlyingWalkingBase.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> ISMOVING = EntityDataManager.createKey(EntityPrehistoricInsectFlyingWalkingBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> FLYING = EntityDataManager.createKey(EntityPrehistoricInsectFlyingWalkingBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> FLYCOOLDOWN = EntityDataManager.createKey(EntityPrehistoricInsectFlyingWalkingBase.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> WANDERCOOLDOWN = EntityDataManager.createKey(EntityPrehistoricInsectFlyingWalkingBase.class, DataSerializers.VARINT);

    public EntityPrehistoricInsectFlyingWalkingBase(World world) {
        super(world);
        //this.spawnableBlock = Blocks.WATER;
        this.selectNavigator();
        this.getNavigator().getNodeProcessor().setCanSwim(false);
        if (FMLCommonHandler.instance().getSide().isClient()) {
            this.chainBuffer = new ChainBuffer();
        }
        LAY_ANIMATION = Animation.create(this.getLayLength());
    }

    public void selectNavigator () {
        if (this.getIsFlying()) {
            if ((!(this.moveHelper instanceof EntityPrehistoricInsectFlyingWalkingBase.FlightMoveHelper))
            || (!(this.navigator instanceof PathNavigateFlyingNoWater))) {
                this.moveHelper = new EntityPrehistoricInsectFlyingWalkingBase.FlightMoveHelper(this);
                this.navigator = new PathNavigateFlyingNoWater(this, world);
                //if (this instanceof EntityPrehistoricFloraGerarus) {
                //    System.err.println(this.getClass() + " Navigator changed to " + this.navigator);
                //}
            }
        }
        else {
            if ((!(this.moveHelper instanceof EntityPrehistoricInsectFlyingWalkingBase.WanderMoveHelper))
            || (!(this.navigator instanceof PathNavigateGroundNoWater))) {
                this.moveHelper = new EntityPrehistoricInsectFlyingWalkingBase.WanderMoveHelper();
                this.navigator = new PathNavigateGroundNoWater(this, world);
                //if (this instanceof EntityPrehistoricFloraGerarus) {
               //    System.err.println(this.getClass() + " Navigator changed to " + this.navigator);
                //}
            }
        }
        this.getNavigator().getNodeProcessor().setCanSwim(false);
    }

    public int getLayLength() {
        return 50;
    } //Do not change this

    protected abstract float getAISpeedLand();

    public abstract boolean laysEggs();

    public abstract boolean dropsEggs();

    public abstract IBlockState getEggBlockState();

    public float getMaxTurnDistancePerTick() {
        return 20;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(FLYING, false);
        this.dataManager.register(ISMOVING, false);
        this.dataManager.register(TICKS, 0);
        this.dataManager.register(FLYCOOLDOWN, 0);
        this.dataManager.register(WANDERCOOLDOWN, 0);
    }

    public boolean getIsMoving() {
        return this.dataManager.get(ISMOVING);
    }

    public void setIsMoving(boolean isMoving) {
        this.dataManager.set(ISMOVING, isMoving);
    }

    public int getWanderCooldown() {
        return this.dataManager.get(WANDERCOOLDOWN);
    }

    public void setWanderCooldown(int WanderCooldown) {
        this.dataManager.set(WANDERCOOLDOWN, WanderCooldown);
    }

    public int getFlyCooldown() {
        return this.dataManager.get(FLYCOOLDOWN);
    }

    public void setFlyCooldown(int FlyCooldown) {
        this.dataManager.set(FLYCOOLDOWN, FlyCooldown);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setTicks(0);
        return livingdata;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entity) {
        return null;
    }

    public boolean getIsFlying() {
        return this.dataManager.get(FLYING);
    }

    public void setFlying(boolean flying) {
        this.dataManager.set(FLYING, flying);
    }

    public static BlockPos getPositionRelativetoGround(Entity entity, World world, double x, double z, Random rand) {
        BlockPos pos = new BlockPos(x, entity.posY, z);
        while ((world.getBlockState(pos.down()).getMaterial() != Material.WATER) && (world.getBlockState(pos.down()).getMaterial() != Material.LAVA) && world.isAirBlock(pos.down()) && pos.getY() > 0) {
            pos = pos.down();
        }
        return pos.up(2 + rand.nextInt(3));
    }

    //@Override
    //public boolean isMovementBlocked() {
    //    return isSitting();
    //}

    public boolean isMovementBlockedSoft() {
        return isMovementBlocked() ;
    }

    public int getTicks() {
       return this.dataManager.get(TICKS);
    }

    public void setTicks(int ticks) {
        this.dataManager.set(TICKS, ticks);
    }

    public boolean getCanBreed() {
        return this.getTicks() > 24000; //If the mob has done not bred for a MC day
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setFlyCooldown(compound.getInteger("FlyCooldown"));
        this.setWanderCooldown(compound.getInteger("WanderCooldown"));
        this.setTicks(compound.getInteger("Ticks"));
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("FlyCooldown", this.getFlyCooldown());
        compound.setInteger("WanderCooldown", this.getWanderCooldown());
        compound.setInteger("Ticks", this.getTicks());
    }

    private Animation animation = NO_ANIMATION;

    public static final Animation ANIMATION_WANDER = Animation.create(20);

    protected void initEntityAI() {
        this.tasks.addTask(0, new AIWanderInsect());
        this.tasks.addTask(1, new FlyingLandWanderAvoidWaterAI(this, 1, 10));
        this.tasks.addTask(2, new EntityAILookIdle(this));

    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    public abstract String getTexture();

    public String tagEgg () {return "";}

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    public int defaultFlyCooldown() {
        return 2000;
    }

    public int defaultWanderCooldown() {
        return 500;
    }

    @Override
    public boolean attackEntityFrom(DamageSource ds, float f) {
        if (ds == DamageSource.IN_WALL) {
            return false;
        }
        if (ds == DamageSource.FALL) {
            return false;
        }
        if (this.getIsFlying()) {
            this.setFlying(false);
        }
        else {
            this.setFlying(true);
        }
        this.setFlyCooldown(this.defaultFlyCooldown() + rand.nextInt(500));
        this.setWanderCooldown(this.defaultWanderCooldown() + rand.nextInt(500));
        return super.attackEntityFrom(ds, f);
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    protected abstract float getAISpeedInsect();

    @Override
    public boolean canBreatheUnderwater() {
        return false;
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
        return 1 + this.world.rand.nextInt(3);
    }

    @Override
    public boolean isOnLadder() {
        return false;
    }

    public void onEntityUpdate()
    {
        super.onEntityUpdate();
        if (!this.world.isRemote) {
            if (this.onGround && this.getIsFlying() && this.getWanderCooldown() <= 0) {
                this.setFlying(false);
                this.setFlyCooldown(rand.nextInt(500));
                this.setWanderCooldown(this.defaultWanderCooldown() + rand.nextInt(500));
            } else {
                if (this.getFlyCooldown() >= 0 && !this.getIsFlying()) {
                    this.setFlyCooldown(this.getFlyCooldown() - 1);
                }
                if (this.getWanderCooldown() >= 0 && this.getIsFlying()) {
                    this.setWanderCooldown(this.getWanderCooldown() - 1);
                }
            }
        }

        //if (this instanceof EntityPrehistoricFloraGerarus) {
        //    System.err.println(this.getClass() + " FlyCooldown: " + this.getFlyCooldown());
        //    System.err.println(this.getClass() + " WanderCooldown: " + this.getWanderCooldown());
        //}

        //General ticker (for babies etc.)
        int ii = this.getTicks();
        if (this.isEntityAlive())
        {
            ++ii;
            //limit at 24000 + 3600 (one MC day plus 3 minutes) and then reset:
            if (ii >= 27600) {ii = 0;}
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
        if (!world.isRemote && this.laysEggs() && this.getCanBreed() && LepidodendronConfig.doMultiplyMobs
        ) {
            if ((this.testLay(world, this.getPosition()) || this.testLay(world, this.getPosition().down())) && this.getTicks() > 0
            ) {
                if (Math.random() > 0.5) {
                    this.setTicks(-50); //Flag this as stationary for egg-laying
                    this.setAnimation(LAY_ANIMATION);
                }
            }
            if ((this.testLay(world, this.getPosition()) || this.testLay(world, this.getPosition().down())) && this.getTicks() > -30 && this.getTicks() < 0) {
                //Is stationary for egg-laying:
                //System.err.println("Laying an egg in it");

                String stringEgg = LepidodendronMod.MODID + ":" + this.tagEgg();
                //this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
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
                this.setTicks(0);
            }
        }
    }

    public boolean testLay(World world, BlockPos pos) {
        return false;
    }

    @Override
    public void onLivingUpdate() {

        if (!this.world.isRemote) {
            this.fallDistance = 0;
        }

        if (!this.world.isRemote) {
            if (this.isInWater() && !this.getIsFlying()) {
                this.setWanderCooldown(this.defaultWanderCooldown() + rand.nextInt(500));
                this.setFlyCooldown(this.defaultFlyCooldown() + rand.nextInt(500));
                setFlying(true);
            }

            //System.err.println("Fly cooldown " + this.flyCooldown + " Wander cooldown " + this.wanderCooldown);

            selectNavigator();
        }

        super.onLivingUpdate();

        if (!this.world.isRemote) {
            if (this.getFlyCooldown() <= 0) {
                setFlying(true);
                this.setFlyCooldown(this.defaultFlyCooldown() + rand.nextInt(500));
                this.setWanderCooldown(this.defaultWanderCooldown() + rand.nextInt(500));
            }

            if (this.getWanderCooldown() <= 0) {
                setFlying(false);
                this.setFlyCooldown(this.defaultFlyCooldown() + rand.nextInt(500));
                this.setWanderCooldown(this.defaultWanderCooldown() + rand.nextInt(500));
            }

            if (this.getIsFlying() && !this.isMovementBlockedSoft()) {
                this.motionY += 0.08D;
            }

            if (this.getIsFlying() && this.ticksExisted % 20 == 0 && !world.isRemote) {
                this.playSound((net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
                        .getObject(this.FlightSound()), this.getSoundVolume(), 1);
            }

            //if (this instanceof EntityPrehistoricFloraGerarus) {
            //    System.err.println(this.getClass() + " FlyCooldown: " + this.getFlyCooldown());
            //    System.err.println(this.getClass() + " WanderCooldown: " + this.getWanderCooldown());
            //    System.err.println(this.getClass() + " Flying: " + this.getIsFlying());
            //}
        }

    }

    public ResourceLocation FlightSound() {
        return new ResourceLocation("lepidodendron:bug_flight");
    }

    @Override
    public SoundEvent getAmbientSound() {
        return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("lepidodendron:bug_hurt"));
    }



    @Override
    public SoundEvent getDeathSound() {
        return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("lepidodendron:bug_death"));
    }

    public boolean isDirectPathBetweenPoints(Vec3d target) {
        RayTraceResult rayTrace = world.rayTraceBlocks(this.getPositionVector().add(0, -0.25, 0), target, true);
        if (rayTrace != null && rayTrace.hitVec != null) {
            BlockPos sidePos = rayTrace.getBlockPos();
            BlockPos pos = new BlockPos(rayTrace.hitVec);
            if (!world.isAirBlock(pos) || !world.isAirBlock(sidePos)) {
                return true;
            } else {
                return rayTrace.typeOfHit != RayTraceResult.Type.MISS;
            }
        }
        return true;
    }

    @Override
    public void travel(float strafe, float vertical, float forward) {
        float f4;
        if (this.isServerWorld()) {
            if (isInWater()) {
                this.moveRelative(strafe, vertical, forward, 0.1F);
                f4 = 0.8F;
                float speedModifier = (float) EnchantmentHelper.getDepthStriderModifier(this);
                if (speedModifier > 3.0F) {
                    speedModifier = 3.0F;
                }
                if (!this.onGround) {
                    speedModifier *= 0.5F;
                }
                if (speedModifier > 0.0F) {
                    f4 += (0.54600006F - f4) * speedModifier / 3.0F;
                }
                this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                this.motionX *= f4;
                this.motionX *= 0.9;
                this.motionY *= 0.9;
                this.motionY *= f4;
                this.motionZ *= 0.9;
                this.motionZ *= f4;
            } else {
                super.travel(strafe, vertical, forward);

                if (this.motionX != 0 || this.motionZ != 0) {
                    this.setIsMoving(true);
                }
                else {
                    this.setIsMoving(false);
                }

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

    public class FlightMoveHelper extends EntityMoveHelper {
        public FlightMoveHelper(EntityPrehistoricInsectFlyingWalkingBase insect) {
            super(insect);
            this.speed = EntityPrehistoricInsectFlyingWalkingBase.this.getAISpeedInsect();
        }

        public void onUpdateMoveHelper() {
            if (this.action == Action.MOVE_TO) {
                if (EntityPrehistoricInsectFlyingWalkingBase.this.collidedHorizontally) {
                    EntityPrehistoricInsectFlyingWalkingBase.this.rotationYaw += 180.0F;
                    this.speed = 0.1F;
                    BlockPos target = EntityPrehistoricInsectFlyingWalkingBase.getPositionRelativetoGround(EntityPrehistoricInsectFlyingWalkingBase.this, EntityPrehistoricInsectFlyingWalkingBase.this.world, EntityPrehistoricInsectFlyingWalkingBase.this.posX + EntityPrehistoricInsectFlyingWalkingBase.this.rand.nextInt(15) - 7, EntityPrehistoricInsectFlyingWalkingBase.this.posZ + EntityPrehistoricInsectFlyingWalkingBase.this.rand.nextInt(15) - 7, EntityPrehistoricInsectFlyingWalkingBase.this.rand);
                    this.posX = target.getX();
                    this.posY = target.getY();
                    this.posZ = target.getZ();
                }
                double d0 = this.posX - EntityPrehistoricInsectFlyingWalkingBase.this.posX;
                double d1 = this.posY - EntityPrehistoricInsectFlyingWalkingBase.this.posY;
                double d2 = this.posZ - EntityPrehistoricInsectFlyingWalkingBase.this.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = MathHelper.sqrt(d3);

                if (d3 < EntityPrehistoricInsectFlyingWalkingBase.this.getEntityBoundingBox().getAverageEdgeLength()) {
                    this.action = Action.WAIT;
                    EntityPrehistoricInsectFlyingWalkingBase.this.motionX *= 0.5D;
                    EntityPrehistoricInsectFlyingWalkingBase.this.motionY *= 0.5D;
                    EntityPrehistoricInsectFlyingWalkingBase.this.motionZ *= 0.5D;
                } else {
                    EntityPrehistoricInsectFlyingWalkingBase.this.motionX += d0 / d3 * 0.1D * this.speed;
                    EntityPrehistoricInsectFlyingWalkingBase.this.motionY += d1 / d3 * 0.1D * this.speed;
                    EntityPrehistoricInsectFlyingWalkingBase.this.motionZ += d2 / d3 * 0.1D * this.speed;

                    if (EntityPrehistoricInsectFlyingWalkingBase.this.getAttackTarget() == null) {
                        EntityPrehistoricInsectFlyingWalkingBase.this.rotationYaw = -((float) MathHelper.atan2(EntityPrehistoricInsectFlyingWalkingBase.this.motionX, EntityPrehistoricInsectFlyingWalkingBase.this.motionZ)) * (180F / (float) Math.PI);
                        EntityPrehistoricInsectFlyingWalkingBase.this.renderYawOffset = EntityPrehistoricInsectFlyingWalkingBase.this.rotationYaw;
                    } else {
                        double d4 = EntityPrehistoricInsectFlyingWalkingBase.this.getAttackTarget().posX - EntityPrehistoricInsectFlyingWalkingBase.this.posX;
                        double d5 = EntityPrehistoricInsectFlyingWalkingBase.this.getAttackTarget().posZ - EntityPrehistoricInsectFlyingWalkingBase.this.posZ;
                        EntityPrehistoricInsectFlyingWalkingBase.this.rotationYaw = -((float) MathHelper.atan2(d4, d5)) * (180F / (float) Math.PI);
                        EntityPrehistoricInsectFlyingWalkingBase.this.renderYawOffset = EntityPrehistoricInsectFlyingWalkingBase.this.rotationYaw;
                    }
                }
            }
        }
    }

    public class WanderMoveHelper extends EntityMoveHelper {

        private final EntityPrehistoricInsectFlyingWalkingBase EntityBase = EntityPrehistoricInsectFlyingWalkingBase.this;

        public WanderMoveHelper() {
            super(EntityPrehistoricInsectFlyingWalkingBase.this);
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

    class AIWanderInsect extends EntityAIBase {
        BlockPos target;

        public AIWanderInsect() {
            this.setMutexBits(1);
        }

        public boolean shouldExecute() {
            if (!EntityPrehistoricInsectFlyingWalkingBase.this.getIsFlying()) {
                return false;
            }

            target = EntityPrehistoricInsectFlyingWalkingBase.getPositionRelativetoGround(EntityPrehistoricInsectFlyingWalkingBase.this, EntityPrehistoricInsectFlyingWalkingBase.this.world, EntityPrehistoricInsectFlyingWalkingBase.this.posX + EntityPrehistoricInsectFlyingWalkingBase.this.rand.nextInt(17) - 8, EntityPrehistoricInsectFlyingWalkingBase.this.posZ + EntityPrehistoricInsectFlyingWalkingBase.this.rand.nextInt(17) - 8, EntityPrehistoricInsectFlyingWalkingBase.this.rand);
            Material material = world.getBlockState(new BlockPos(target)).getMaterial();
            Material material1 = world.getBlockState(new BlockPos(target).up()).getMaterial();
            return (material1 != Material.LAVA)
                && (material1 != Material.WATER) && (material != Material.LAVA) && (material != Material.WATER) && EntityPrehistoricInsectFlyingWalkingBase.this.isDirectPathBetweenPoints(new Vec3d(target).add(0.5D, 0.5D, 0.5D)) && EntityPrehistoricInsectFlyingWalkingBase.this.rand.nextInt(4) == 0;
        }

        public boolean shouldContinueExecuting() {
            return false;
        }

        public void updateTask() {
            if (!EntityPrehistoricInsectFlyingWalkingBase.this.isDirectPathBetweenPoints(new Vec3d(target))) {
                target = EntityPrehistoricInsectFlyingWalkingBase.getPositionRelativetoGround(EntityPrehistoricInsectFlyingWalkingBase.this, EntityPrehistoricInsectFlyingWalkingBase.this.world, EntityPrehistoricInsectFlyingWalkingBase.this.posX + EntityPrehistoricInsectFlyingWalkingBase.this.rand.nextInt(15) - 7, EntityPrehistoricInsectFlyingWalkingBase.this.posZ + EntityPrehistoricInsectFlyingWalkingBase.this.rand.nextInt(15) - 7, EntityPrehistoricInsectFlyingWalkingBase.this.rand);
            }
            if (EntityPrehistoricInsectFlyingWalkingBase.this.world.isAirBlock(target)) {
                EntityPrehistoricInsectFlyingWalkingBase.this.moveHelper.setMoveTo((double) target.getX() + 0.5D, (double) target.getY() + 0.5D, (double) target.getZ() + 0.5D, 0.15D);
                if (EntityPrehistoricInsectFlyingWalkingBase.this.getAttackTarget() == null) {
                    EntityPrehistoricInsectFlyingWalkingBase.this.getLookHelper().setLookPosition((double) target.getX() + 0.5D, (double) target.getY() + 0.5D, (double) target.getZ() + 0.5D, 180.0F, 20.0F);

                }
            }
        }
    }

}