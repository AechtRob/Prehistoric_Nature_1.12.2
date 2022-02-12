package net.lepidodendron.entity.base;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockMobSpawn;
import net.lepidodendron.item.entities.ItemUnknownEgg;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nullable;

public abstract class EntityPrehistoricFloraAgeableBase extends EntityTameable implements IAnimatedEntity  {

    private static final DataParameter<Integer> AGETICKS = EntityDataManager.createKey(EntityPrehistoricFloraAgeableBase.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> HUNTING = EntityDataManager.createKey(EntityPrehistoricFloraAgeableBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> TICKS = EntityDataManager.createKey(EntityPrehistoricFloraAgeableBase.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> ISFAST = EntityDataManager.createKey(EntityPrehistoricFloraAgeableBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ISMOVING = EntityDataManager.createKey(EntityPrehistoricFloraAgeableBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> ONEHIT = EntityDataManager.createKey(EntityPrehistoricFloraAgeableBase.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> MATEABLE = EntityDataManager.createKey(EntityPrehistoricFloraAgeableBase.class, DataSerializers.VARINT);

    //public float minSize;
    public float minWidth;
    public float maxWidth;
    public float maxHeight;
    public double maxHealthAgeable;
    private int animationTick;
    public Animation ATTACK_ANIMATION;
    public Animation ROAR_ANIMATION;
    public Animation LAY_ANIMATION;
    private Animation currentAnimation;

    private int inPFLove;
    private boolean laying;
    private EntityItem eatTarget;
    private EntityLiving grappleTarget;
    public boolean willGrapple;

    public EntityPrehistoricFloraAgeableBase(World worldIn) {
        super(worldIn);
        this.setScaleForAge(false);
        ATTACK_ANIMATION = Animation.create(this.getAttackLength());
        ROAR_ANIMATION = Animation.create(this.getRoarLength());
        LAY_ANIMATION = Animation.create(this.getLayLength());
    }

    public boolean canSpawnOnLeaves() {
        return false;
    }

    public abstract boolean dropsEggs();

    public abstract boolean laysEggs();

    public boolean divesToLay() {
        return false;
    }

    public String tagEgg () {return "";}

    public boolean grappleEntityAsMob(Entity entity) {
        return false;
    }

    @Override
    public float getEyeHeight() {
        return Math.max(super.getEyeHeight(), 0.3F);
    }

    public int getAttackLength() {
        return 20;
    }

    public int getRoarLength() {
        return 60;
    }

    public int getLayLength() {
        return 50;
    } //Do not change this

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
        return new Animation[]{ATTACK_ANIMATION,ROAR_ANIMATION};
    }

    public SoundEvent getSoundForAnimation(Animation animation) {
        return null;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(AGETICKS, getAdultAge());
        this.dataManager.register(MATEABLE, 0);
        this.dataManager.register(TICKS, rand.nextInt(24000));
        this.dataManager.register(HUNTING, false);
        this.dataManager.register(ISFAST, false);
        this.dataManager.register(ISMOVING, false);
        this.dataManager.register(ONEHIT, false);
        this.setScaleForAge(false);
    }

    @Override
    public boolean isInWater() {
        if (this.world.isAirBlock(this.getPosition())) {return false;}
        return super.isInWater() || (this.world.getBlockState(this.getPosition()).getMaterial() == Material.WATER) || this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL);
    }

    public boolean isPFAdult() {
        double adult = (double) LepidodendronConfig.adultAge;
        if (adult > 100) {adult = 100;}
        if (adult < 0) {adult = 0;}
        adult = adult/100D;
        if (this.getAgeScale() >= adult) {
            return true;
        }
        return false;
    }

    public boolean breaksBoat() {
        return false;
    }

    public void launchAttack() {
        IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        if (getAttackTarget() != null) {
            boolean b = this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), (float) iattributeinstance.getAttributeValue());
            EntityLivingBase ee = this.getAttackTarget();
            if (ee.isRiding() && this.breaksBoat()) {
                Entity boat = ee.getRidingEntity();
                if (boat instanceof EntityBoat) {
                    boat.setDead();
                    this.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_DOOR_WOOD, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                }
            }
        }
    }

    public void launchGrapple() {
    }

    public AxisAlignedBB getAttackBoundingBox() {
        float size = this.getRenderSizeModifier() * 0.25F;
        return this.getEntityBoundingBox().grow(1.0F + size, 1.0F + size, 1.0F + size);
    }

    public AxisAlignedBB getGrappleBoundingBox() {
        return this.getAttackBoundingBox();
    }

    @Override
    public EntityAgeable createChild(EntityAgeable entity) {
        return null;
    }

    public EntityPrehistoricFloraAgeableBase createPFChild(EntityPrehistoricFloraAgeableBase entity) {
        return null;
    }

    public int getAgeTicks() {
        return this.dataManager.get(AGETICKS);
    }

    public void setAgeTicks(int ageticks) {
        this.dataManager.set(AGETICKS, ageticks);
    }

    public int getMateable() {
        return this.dataManager.get(MATEABLE);
    }

    public void setMateable(int ticks) {
        this.dataManager.set(MATEABLE, ticks);
    }

    public boolean getCanBreed() {
        return (this.isPFAdult() &&
                (this.getTicks() > 24000 || this.getTicks() < 0)); //If the mob has done not bred for a MC day
    }

    public int getTicks() {
       return this.dataManager.get(TICKS);
    }

    public void setTicks(int ticks) {
        this.dataManager.set(TICKS, ticks);
    }

    public boolean getWillHunt() {
        return this.dataManager.get(HUNTING);
    }

    public void setWillHunt(boolean willHunt) {
        this.dataManager.set(HUNTING, willHunt);
    }

    public double getMaxHealthAgeable()
    {
        return maxHealthAgeable;
    }

    public abstract int getAdultAge();

    public boolean getIsFast() {
        return this.dataManager.get(ISFAST);
    }

    public void setIsFast(boolean isFast) {
        this.dataManager.set(ISFAST, isFast);
    }

    public boolean getIsMoving() {
        return this.dataManager.get(ISMOVING);
    }

    public void setIsMoving(boolean isMoving) {
        this.dataManager.set(ISMOVING, isMoving);
    }

    public boolean getOneHit() {
        return this.dataManager.get(ONEHIT);
    }

    public void setOneHit(boolean oneHit) {
        this.dataManager.set(ONEHIT, oneHit);
    }


    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setAgeTicks(this.getAdultAge()-1);
        this.setMateable(0);
        this.setTicks(0);
        this.setIsFast(false);
        this.setWillHunt(false);
        this.heal(this.getMaxHealth());
        this.setNoAI(false);
        return livingdata;
    }

    public void selectNavigator() {
        //Used for complex navigation only
        this.moveHelper = this.getMoveHelper();
        this.navigator = this.getNavigator();
    }

    public static void summon(World worldIn, String mobToSpawn, String nbtStr, double xpos, double ypos, double zpos) {
        worldIn.getMinecraftServer().getCommandManager().executeCommand(new ICommandSender() {
            @Override
            public String getName() {
                return "";
            }

            @Override
            public boolean canUseCommand(int permission, String command) {
                return true;
            }

            @Override
            public World getEntityWorld() {
                return worldIn;
            }

            @Override
            public MinecraftServer getServer() {
                return worldIn.getMinecraftServer();
            }

            @Override
            public boolean sendCommandFeedback() {
                return false;
            }

        }, "pf_summon " + mobToSpawn + " " + xpos + " " + ypos + " " + zpos + " " + nbtStr);
    }

    @Override
    public void setScaleForAge(boolean child) {
        //System.err.println("AgeScale: " + this.getAgeScale());
        //System.err.println("width: " + (this.getAgeScale() * this.maxWidth));
        //System.err.println("height: " + (this.getAgeScale() * this.maxHeight));
        this.setSizer(this.getAgeScale() * this.maxWidth, this.getAgeScale() * this.maxHeight);
    }

    //Taken from the Entity class, not the EntityAgeable class
    protected void setSizer(float width, float height)
    {
        if (width != this.width || height != this.height)
        {
            float f = this.width;
            this.width = width;
            this.height = height;
            if (this.width < f) {
                double d0 = (double) width / 2.0D;
                this.setEntityBoundingBox(new AxisAlignedBB(this.posX - d0, this.posY, this.posZ - d0, this.posX + d0, this.posY + (double) this.height, this.posZ + d0));
            }
        }
    }

    public float getAgeScale() {
        float step = 1F / ((float)this.getAdultAge() + 1F);
        if (this.getAgeTicks() >= this.getAdultAge()) {
            return 1;
        }
        return Math.max((this.minWidth/this.maxWidth), (step * (float)this.getAgeTicks()));
    }

    //@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("AgeTicks", this.getAgeTicks());
        compound.setInteger("Ticks", this.getTicks());
        compound.setBoolean("willHunt", this.getWillHunt());
        compound.setBoolean("isFast", this.getIsFast());
        compound.setInteger("InPFLove", this.inPFLove);
        compound.setBoolean("laying", this.laying);
        compound.setInteger("mateable", this.getMateable());
    }

    //@Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setAgeTicks(compound.getInteger("AgeTicks"));
        this.setTicks(compound.getInteger("Ticks"));
        this.setWillHunt(compound.getBoolean("willHunt"));
        this.setIsFast(compound.getBoolean("isFast"));
        this.inPFLove = compound.getInteger("InPFLove");
        this.laying = compound.getBoolean("laying");
        this.setMateable(compound.getInteger("mateable"));
    }

    @Override
    protected void updateAITasks()
    {
        if (!this.isPFAdult())
        {
            this.inPFLove = 0;
        }

        super.updateAITasks();
    }

    @Override
    public boolean attackEntityFrom(DamageSource ds, float i) {
        //if (ds == DamageSource.IN_WALL) {
        //    return false;
        //}
        if (this.getHurtSound(DamageSource.GENERIC) != null && i >= 1 && ds != DamageSource.IN_WALL) {
            //if (this.getAnimation() != null) {
                if (this.getAnimation() == NO_ANIMATION) {
                    this.setAnimation(ROAR_ANIMATION);
                }
            //}
        }
        if (this.isEntityInvulnerable(ds))
        {
            return false;
        }
        else
        {
            this.inPFLove = 0;
            return super.attackEntityFrom(ds, i);
        }
    }

    @Nullable
    public EntityItem getEatTarget()
    {
        return this.eatTarget;
    }

    public void setEatTarget(@Nullable EntityItem entityItem)
    {
        this.eatTarget = entityItem;
        //net.minecraftforge.common.ForgeHooks.onLivingSetAttackTarget(this, entityItem);
    }

    @Nullable
    public EntityLiving getGrappleTarget()
    {
        return this.grappleTarget;
    }

    public void setGrappleTarget(@Nullable EntityLiving entityGrappleTarget)
    {
        this.grappleTarget = entityGrappleTarget;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!world.isRemote) {
            if (this.getAttackTarget() != null) {
                if (this.getAttackTarget().isDead) {
                    this.setAttackTarget(null);
                }
            }
            if (this.getEatTarget() != null) {
                if (this.getEatTarget().isDead) {
                    this.setEatTarget(null);
                }
            }
            this.setIsFast(this.getAttackTarget() != null || this.getEatTarget() != null);

        }

        if (!this.isPFAdult())
        {
            this.inPFLove = 0;
        }

        if (this.inPFLove > 0)
        {
            --this.inPFLove;
        }

        if (this.getMateable() < 0) {
            this.setMateable(this.getMateable() + 1);
        }

        //Grapple with mates?
        if (rand.nextInt(500) == 0) {
            //Are there any nearby to grapple with?
            this.findGrappleTarget();
        }

        if (this.willGrapple && this.getAnimation() == this.getGrappleAnimation() && this.getAnimationTick() == 5 && this.getGrappleTarget() != null) {
            this.faceEntity(this.getGrappleTarget(), 100F, 100F);
            launchGrapple();
            if (this.getOneHit()) {
                this.setGrappleTarget(null);
            }
        }
    }

    public Animation getGrappleAnimation() {
        return this.ATTACK_ANIMATION;
    }

    @Nullable
    public boolean findGrappleTarget() {
        return false;
    }

    @Override
    public void playLivingSound() {
        //super.playLivingSound();
        if (!this.world.isRemote) {
            if (this.getAnimation() == NO_ANIMATION) {
                //this.setAnimation(ROAR_ANIMATION);
                this.setAnimation(ROAR_ANIMATION);
                //System.err.println("Playing roar sound on remote: " + (world.isRemote));
                super.playLivingSound();
            }
       }
    }

    public void onEntityUpdate()
    {
        super.onEntityUpdate();

        if (this.getAttackTarget() != null) {
            if (this.getAttackTarget() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) this.getAttackTarget();
                if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL || player.isCreative()) {
                    this.setAttackTarget(null);
                }
            }
        }

        //General ticker (for babies etc.)
        int ii = this.getTicks();
        if (this.isEntityAlive())
        {
            ++ii;
            if (!LepidodendronConfig.doMultiplyMobs) {
                if (ii <= 10) {
                    //Don't use this ticker if multiplication is off, unless we are over breeding limit:
                    //this ticker is set to 24000 by some AI, so we do want it to run up at that level sometimes
                    ii = 0;
                }
            }
            //limit at 24000 + 6000 (one MC day plus 5 minutes) and then reset:
            if (ii >= 30000) {ii = 0;}
            this.setTicks(ii);
        }

        //if (world.isRemote) {
        //this.setScaleForAge(false);
        //}
        if (this.getGrowingAge() < 0) {
            this.setGrowingAge(0); //Resetting vanilla methods which we don't use
        }

        int i = this.getAgeTicks();
        if (this.isEntityAlive())
        {
            ++i;
            //throttle at limit:
            if (i > this.getAdultAge()) {i = this.getAdultAge();}
            this.setAgeTicks(i);
        }

        this.setScaleForAge(false);

        double oldHealthMax = this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
        float oldHealth = this.getHealth();
        //System.err.println("Old Max Health: " + oldHealthMax);
        //System.err.println("Old Health: " + oldHealth);

        float oldHealthRatio = (float) (oldHealth / (float) oldHealthMax);
        //System.err.println("Old Health Ratio: " + oldHealthRatio);

        double newHealthMax = (double) (getMaxHealthAgeable() * getAgeScale());
        newHealthMax = Math.min((Math.round((newHealthMax * 2D)))/2D, getMaxHealthAgeable());
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(newHealthMax);

        float newHealth = (float) newHealthMax * oldHealthRatio;
        newHealth = (Math.round((newHealth * 2F)))/2.0F;
        newHealth = Math.min(newHealth,(float)newHealthMax);
        this.setHealth(newHealth);
        //System.err.println("New Health Max: " + newHealthMax);
        //System.err.println("New Health: " + newHealth);

        double aHealth = (double) LepidodendronConfig.attackHealth;
        if (aHealth > 100) {aHealth = 100;}
        if (aHealth < 0) {aHealth = 0;}
        aHealth = aHealth/100D;
        EntityLivingBase attackTarget = this.getAttackTarget();
        if (attackTarget != null ) {
            if (attackTarget instanceof EntityPlayer && LepidodendronConfig.attackPlayerAlways) {
                this.setWillHunt(true);
            }
            else {
                this.setWillHunt(oldHealthRatio < (float) aHealth);
            }
        }
        else {
            this.setWillHunt(oldHealthRatio < (float) aHealth);
        }
        double adult = (double) LepidodendronConfig.adultAge;
        if (adult > 100) {adult = 100;}
        if (adult < 0) {adult = 0;}
        adult = adult/100D;
        if (getAgeScale() < adult) {
            setWillHunt(false);
        }

        //Drop an egg perhaps:
        if (!world.isRemote && this.getCanBreed() && this.dropsEggs() && (LepidodendronConfig.doMultiplyMobs || this.getLaying())) {
            //if (Math.random() > 0.5) {
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
                this.setLaying(false);
            //}
            this.setTicks(0);
        }

        //Lay eggs perhaps:
        if (!world.isRemote && this.laysEggs() && this.getCanBreed() && (LepidodendronConfig.doMultiplyMobs || this.getLaying())
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

                    if (te instanceof TileEntityLockableLoot) {
                        ItemStack _setstack = new ItemStack(Blocks.STONE, (int) (1), 0);
                        _setstack.setCount(1);
                        ((TileEntityLockableLoot) te).setInventorySlotContents((int) (0), _setstack);
                    }



                    IBlockState state = world.getBlockState(this.getPosition());
                    world.notifyBlockUpdate(this.getPosition(), state, state, 3);
                    this.setLaying(false);
                } else if (this.testLay(world, this.getPosition().down())) {
                    this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                    TileEntity te = world.getTileEntity(this.getPosition().down());
                    if (te != null) {
                        te.getTileData().setString("egg", stringEgg);
                    }
                    IBlockState state = world.getBlockState(this.getPosition().down());
                    world.notifyBlockUpdate(this.getPosition().down(), state, state, 3);
                    this.setLaying(false);
                }
                this.setTicks(0);
            }
        }
    }

    public boolean testLay(World world, BlockPos pos) {
        return false;
    }

    public boolean spaceCheckEggs() {
        int xct;
        int yct;
        int zct;
        yct = -4;
        while (yct <= 4) {
            xct = -4;
            while (xct <= 4) {
                zct = -4;
                while (zct <= 4) {
                    if (world.getBlockState(this.getPosition().add(xct, yct, zct)).getBlock() instanceof BlockMobSpawn) {
                        return false;
                    }
                    zct += 1;
                }
                xct += 1;
            }
            yct += 1;
        }
        return true;
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
                System.err.println("setting attack anim");
                this.setAnimation(ATTACK_ANIMATION);
                if (this.getAnimation() == ATTACK_ANIMATION && !world.isRemote) {
                    System.err.println("is attacking");
                }
                SoundEvent soundevent = SoundEvents.ENTITY_GENERIC_EAT;
                this.getEntityWorld().playSound(null, this.getPosition(), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
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

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return false;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty())
        {
            if (this.isBreedingItem(itemstack) && this.isPFAdult() && this.inPFLove <= 0 && this.getMateable() == 0)
            {
                this.consumeItemFromStack(player, itemstack);
                if (this.isPFAdult()) {
                    this.inPFLove = 600;
                    this.world.setEntityState(this, (byte) 18);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInLove()
    {
        return this.inPFLove > 0;
    }

    @Override
    public void resetInLove()
    {
        this.inPFLove = 0;
    }

    public void setNotMateable()
    {
        this.setMateable(-6000);
    }

    public void setLaying(boolean bool)
    {
        this.laying = bool;
    }

    public boolean getLaying()
    {
        return this.laying;
    }

}
