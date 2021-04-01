
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.lepidodendron.entity.ai.JellyfishWander;
import net.lepidodendron.entity.model.ModelJellyfish1;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;

import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderLiving;

import net.lepidodendron.ElementsLepidodendronMod;

@ElementsLepidodendronMod.ModElement.Tag
public class EntityPrehistoricFloraJellyfish1 extends ElementsLepidodendronMod.ModElement {
    public static final int ENTITYID = 8;
    public EntityPrehistoricFloraJellyfish1(ElementsLepidodendronMod instance) {
        super(instance, 1753);
    }

    @Override
    public void initElements() {
        elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityCustom.class)
                .id(new ResourceLocation("lepidodendron", "prehistoric_flora_jellyfish1"), ENTITYID).name("prehistoric_flora_jellyfish1")
                .tracker(64, 3, true).egg(-16724737, -1).build());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityCustom.class, renderManager -> {
            return new RenderLiving(renderManager, new ModelJellyfish1(), 0.25f) {
                protected ResourceLocation getEntityTexture(Entity entity) {
                    return new ResourceLocation("lepidodendron:textures/entities/jellyfish_1.png");
                }
            };
        });
    }

    public static class EntityCustom extends EntityPrehistoricFloraJellyfishBase {

        public BlockPos currentTarget;
        @SideOnly(Side.CLIENT)
        public ChainBuffer chainBuffer;

        public EntityCustom(World world) {
            super(world);
            setSize(0.8F, 0.5F);
            experienceValue = 0;
            this.isImmuneToFire = false;
            setNoAI(!true);
            enablePersistence();
        }

        private Animation animation = NO_ANIMATION;
        private int animationTick;

        public static final Animation ANIMATION_JELLYFISH_WANDER = Animation.create(20);
        public static final Animation ANIMATION_JELLYFISH_LAND = Animation.create(20);

        private static final Animation[] ANIMATIONS = {ANIMATION_JELLYFISH_WANDER,ANIMATION_JELLYFISH_LAND};

        public AnimationAI currentAnim;

        @Override
        public int getAnimationTick() {
            return animationTick;
        }

        @Override
        public void setAnimationTick(int tick)
        {
            animationTick = tick;
        }

        @Override
        public Animation getAnimation()
        {
            return this.animation;
        }

        @Override
        public void setAnimation(Animation animation)
        {
            if (animation == NO_ANIMATION){
                onAnimationFinish(this.animation);
                setAnimationTick(0);
            }
            this.animation = animation;
        }

        @Override
        public Animation[] getAnimations()
        {
            return ANIMATIONS;
        }

        protected void onAnimationFinish(Animation animation)
        {}

        protected void initEntityAI() {
            tasks.addTask(0, new JellyfishWander(this, ANIMATION_JELLYFISH_WANDER));
            //this.tasks.addTask(0, new FishWander(this));
            //this.tasks.addTask(1, new EntityAILookIdle(this));
        }

        @Override
        public boolean isAIDisabled() {
            return false;
        }

        @Override
        public String getTexture() {
            return this.getTexture();
        }

        @Override
        public EnumCreatureAttribute getCreatureAttribute() {
            return EnumCreatureAttribute.UNDEFINED;
        }

        @Override
        protected boolean canDespawn() {
            return false;
        }

        @Override
        protected void applyEntityAttributes() {
            super.applyEntityAttributes();
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        }

        @Override
        protected boolean canTriggerWalking() {
            return false;
        }

        @Override
        protected double getSwimSpeed() {
            return this.getSwimSpeed();
        }

        @Override
        public boolean isInWater() {
            return super.isInWater() || this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL);
        }

        @Override
        public net.minecraft.util.SoundEvent getAmbientSound() {
            return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
        }

        @Override
        public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
            return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.hurt"));
        }

        @Override
        public net.minecraft.util.SoundEvent getDeathSound() {
            return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.death"));
        }

        @Override
        protected float getSoundVolume() {
            return 1.0F;
        }

        @Override
        public boolean canBreatheUnderwater() {
            return true;
        }

        @Override
        public boolean getCanSpawnHere() {
            return this.posY < (double) this.world.getSeaLevel() && this.isInWater();
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

        @Override
        public void onLivingUpdate() {
            super.onLivingUpdate();
            this.renderYawOffset = this.rotationYaw;

            if (getAnimation() != NO_ANIMATION)
            {
                animationTick++;
            }
            if (world.isRemote && animationTick >= animation.getDuration())
            {
                setAnimation(NO_ANIMATION);
            }
        }

        public void onEntityUpdate()
        {
            int i = this.getAir();
            super.onEntityUpdate();

            if (this.isEntityAlive() && !isInWater())
            {
                --i;
                this.setAir(i);

                if (this.getAir() == -20)
                {
                    this.setAir(0);
                    this.attackEntityFrom(DamageSource.DROWN, 2.0F);
                }
            }
            else
            {
                this.setAir(300);
            }
        }

        public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
            RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y, vec2.z), false, true, false);
            return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
        }

        //@Override
        //protected double getSwimSpeed() {
        //	return this.getSwimSpeed();
        //}

        @Override
        protected void collideWithEntity(Entity entityIn) {
            super.collideWithEntity(entityIn);
            if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityPrehistoricFloraJellyfishBase)
                && !(entityIn instanceof EntityMob)) {
                entityIn.attackEntityFrom(DamageSource.CACTUS, (float) 2);
            }
        }

        @Override
        protected Item getDropItem() {
            //return new ItemStack(ItemJellyfishMeat.block, (int) (1)).getItem();
            return null;
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

        class SwimmingMoveHelper extends EntityMoveHelper {
            private final EntityPrehistoricFloraJellyfish1.EntityCustom PrehistoricFloraJellyfishBase = EntityPrehistoricFloraJellyfish1.EntityCustom.this;

            public SwimmingMoveHelper() {
                super(EntityPrehistoricFloraJellyfish1.EntityCustom.this);
            }

            @Override
            public void onUpdateMoveHelper() {
                if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.PrehistoricFloraJellyfishBase.getNavigator().noPath()) {
                    if (this.action == EntityMoveHelper.Action.MOVE_TO && !this.PrehistoricFloraJellyfishBase.getNavigator().noPath()) {
                        double distanceX = this.posX - this.PrehistoricFloraJellyfishBase.posX;
                        double distanceY = this.posY - this.PrehistoricFloraJellyfishBase.posY;
                        double distanceZ = this.posZ - this.PrehistoricFloraJellyfishBase.posZ;
                        double distance = Math.abs(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
                        distance = MathHelper.sqrt(distance);
                        distanceY /= distance;
                        float angle = (float) (Math.atan2(distanceZ, distanceX) * 180.0D / Math.PI) - 90.0F;
                        this.PrehistoricFloraJellyfishBase.rotationYaw = this.limitAngle(this.PrehistoricFloraJellyfishBase.rotationYaw, angle, 30.0F);
                        this.PrehistoricFloraJellyfishBase.setAIMoveSpeed(0.65F);
                        this.PrehistoricFloraJellyfishBase.motionY += (double) this.PrehistoricFloraJellyfishBase.getAIMoveSpeed() * distanceY * 0.1D;
                    } else {
                        this.PrehistoricFloraJellyfishBase.setAIMoveSpeed(0.0F);
                    }
                }
            }
        }

    }

}
