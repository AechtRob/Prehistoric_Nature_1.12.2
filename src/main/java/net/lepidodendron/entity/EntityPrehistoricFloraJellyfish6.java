
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.entity.ai.JellyfishWander;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsLepidodendronMod.ModElement.Tag
public class EntityPrehistoricFloraJellyfish6 extends ElementsLepidodendronMod.ModElement {
    public static final int ENTITYID = 13;
    public EntityPrehistoricFloraJellyfish6(ElementsLepidodendronMod instance) {
        super(instance, 1753);
    }

    @Override
    public void initElements() {
        elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityCustom.class)
                .id(new ResourceLocation("lepidodendron", "prehistoric_flora_jellyfish6"), ENTITYID).name("prehistoric_flora_jellyfish6")
                .tracker(64, 3, true).egg(-6210212, -1).build());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityCustom.class, renderManager -> {
            return new RenderLiving(renderManager, new Modeljellyfish(), 0.5f) {
                protected ResourceLocation getEntityTexture(Entity entity) {
                    return new ResourceLocation("lepidodendron:textures/entities/jellyfish_6.png");
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
            setSize(1.6F, 0.9F);
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
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
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
            private final EntityPrehistoricFloraJellyfish6.EntityCustom PrehistoricFloraJellyfishBase = EntityPrehistoricFloraJellyfish6.EntityCustom.this;

            public SwimmingMoveHelper() {
                super(EntityPrehistoricFloraJellyfish6.EntityCustom.this);
            }

            @Override
            public void onUpdateMoveHelper() {
                if (this.action == Action.MOVE_TO && !this.PrehistoricFloraJellyfishBase.getNavigator().noPath()) {
                    if (this.action == Action.MOVE_TO && !this.PrehistoricFloraJellyfishBase.getNavigator().noPath()) {
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

    /**
     * jellyfish - teiturerl Created using Tabula 7.1.0
     */
    public static class Modeljellyfish extends AdvancedModelBase {
        public AdvancedModelRenderer Mainbody;
        public AdvancedModelRenderer Dome;
        public AdvancedModelRenderer Oralarm1a;
        public AdvancedModelRenderer Oralarm2a;
        public AdvancedModelRenderer Oralarm3a;
        public AdvancedModelRenderer Oralarm4a;
        public AdvancedModelRenderer Tentacles1a;
        public AdvancedModelRenderer Tentacles2a;
        public AdvancedModelRenderer Tentacles3a;
        public AdvancedModelRenderer Tentacles4a;
        public AdvancedModelRenderer Dometop;
        public AdvancedModelRenderer Oralarm1b;
        public AdvancedModelRenderer Oralarm1c;
        public AdvancedModelRenderer Oralarm1d;
        public AdvancedModelRenderer Oralarm2b;
        public AdvancedModelRenderer Oralarm2c;
        public AdvancedModelRenderer Oralarm2d;
        public AdvancedModelRenderer Oralarm3b;
        public AdvancedModelRenderer Oralarm3c;
        public AdvancedModelRenderer Oralarm3d;
        public AdvancedModelRenderer Oralarm4b;
        public AdvancedModelRenderer Oralarm4c;
        public AdvancedModelRenderer Oralarm4d;
        public AdvancedModelRenderer Tentacles1b;
        public AdvancedModelRenderer Tentacles1c;
        public AdvancedModelRenderer Tentacles2b;
        public AdvancedModelRenderer Tentacles2c;
        public AdvancedModelRenderer Tentacles3b;
        public AdvancedModelRenderer Tentacles3c;
        public AdvancedModelRenderer Tentacles4b;
        public AdvancedModelRenderer Tentacles4c;

        private ModelAnimator animator;

        public Modeljellyfish() {
            this.textureWidth = 64;
            this.textureHeight = 64;
            this.Tentacles3a = new AdvancedModelRenderer(this, 0, 26);
            this.Tentacles3a.setRotationPoint(4.0F, 3.0F, 0.0F);
            this.Tentacles3a.addBox(0.0F, 0.0F, -3.5F, 0, 2, 7, 0.0F);
            this.setRotateAngle(Tentacles3a, 0.0F, 0.0F, -0.06370451936226872F);
            this.Dome = new AdvancedModelRenderer(this, 14, 0);
            this.Dome.setRotationPoint(0.0F, 0.0F, 0.0F);
            this.Dome.addBox(-3.0F, -1.0F, -3.0F, 6, 2, 6, 0.0F);
            this.Oralarm4d = new AdvancedModelRenderer(this, 1, 1);
            this.Oralarm4d.setRotationPoint(0.0F, 3.5F, 0.0F);
            this.Oralarm4d.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(Oralarm4d, 0.12740903872453743F, 0.0F, 0.0F);
            this.Tentacles4b = new AdvancedModelRenderer(this, 0, 9);
            this.Tentacles4b.setRotationPoint(0.0F, 2.0F, 0.0F);
            this.Tentacles4b.addBox(0.0F, 0.0F, -3.5F, 0, 4, 7, 0.0F);
            this.setRotateAngle(Tentacles4b, 0.0F, 0.0F, -0.04241150198859518F);
            this.Oralarm2c = new AdvancedModelRenderer(this, 30, 33);
            this.Oralarm2c.setRotationPoint(-0.009999999776482582F, 3.5F, 0.0F);
            this.Oralarm2c.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
            this.setRotateAngle(Oralarm2c, 0.10611601718967469F, 0.0F, 0.0F);
            this.Oralarm1b = new AdvancedModelRenderer(this, 0, 21);
            this.Oralarm1b.setRotationPoint(0.0F, 2.5F, 0.0F);
            this.Oralarm1b.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
            this.setRotateAngle(Oralarm1b, 0.0F, 0.7853981633974483F, 0.0F);
            this.Tentacles2a = new AdvancedModelRenderer(this, 15, 39);
            this.Tentacles2a.setRotationPoint(0.0F, 3.0F, 4.0F);
            this.Tentacles2a.addBox(-3.5F, 0.0F, 0.0F, 7, 2, 0, 0.0F);
            this.setRotateAngle(Tentacles2a, 0.06370451936226872F, 0.0F, 0.0F);
            this.Tentacles4a = new AdvancedModelRenderer(this, 15, 26);
            this.Tentacles4a.setRotationPoint(-4.0F, 3.0F, 0.0F);
            this.Tentacles4a.addBox(0.0F, 0.0F, -3.5F, 0, 2, 7, 0.0F);
            this.setRotateAngle(Tentacles4a, 0.0F, 0.0F, 0.06370451936226872F);
            this.Tentacles3c = new AdvancedModelRenderer(this, 15, 2);
            this.Tentacles3c.setRotationPoint(0.0F, 4.0F, 0.0F);
            this.Tentacles3c.addBox(0.0F, 0.0F, -3.5F, 0, 5, 7, 0.0F);
            this.Oralarm1c = new AdvancedModelRenderer(this, 35, 38);
            this.Oralarm1c.setRotationPoint(0.009999999776482582F, 3.5F, 0.0F);
            this.Oralarm1c.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
            this.setRotateAngle(Oralarm1c, -0.10611601718967469F, 0.0F, 0.0F);
            this.Oralarm2b = new AdvancedModelRenderer(this, 33, 26);
            this.Oralarm2b.setRotationPoint(0.0F, 2.5F, 0.0F);
            this.Oralarm2b.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
            this.setRotateAngle(Oralarm2b, 0.0F, -0.7853981633974483F, 0.0F);
            this.Oralarm1d = new AdvancedModelRenderer(this, 16, 1);
            this.Oralarm1d.setRotationPoint(0.0F, 3.5F, 0.0F);
            this.Oralarm1d.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(Oralarm1d, -0.12740903872453743F, 0.0F, 0.0F);
            this.Oralarm4b = new AdvancedModelRenderer(this, 33, 0);
            this.Oralarm4b.setRotationPoint(-0.009999999776482582F, 2.5F, 0.0F);
            this.Oralarm4b.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
            this.setRotateAngle(Oralarm4b, 0.0F, 0.7853981633974483F, 0.0F);
            this.Tentacles2b = new AdvancedModelRenderer(this, 30, 15);
            this.Tentacles2b.setRotationPoint(0.0F, 2.0F, 0.0F);
            this.Tentacles2b.addBox(-3.5F, 0.0F, 0.0F, 7, 4, 0, 0.0F);
            this.setRotateAngle(Tentacles2b, -0.04241150198859518F, 0.0F, 0.0F);
            this.Oralarm3b = new AdvancedModelRenderer(this, 37, 32);
            this.Oralarm3b.setRotationPoint(0.0F, 2.5F, 0.0F);
            this.Oralarm3b.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
            this.setRotateAngle(Oralarm3b, 0.0F, -0.7853981633974483F, 0.0F);
            this.Tentacles3b = new AdvancedModelRenderer(this, 0, 4);
            this.Tentacles3b.setRotationPoint(0.0F, 2.0F, 0.0F);
            this.Tentacles3b.addBox(0.0F, 0.0F, -3.5F, 0, 4, 7, 0.0F);
            this.setRotateAngle(Tentacles3b, 0.0F, 0.0F, 0.04241150198859518F);
            this.Tentacles2c = new AdvancedModelRenderer(this, 0, 36);
            this.Tentacles2c.setRotationPoint(0.0F, 4.0F, 0.0F);
            this.Tentacles2c.addBox(-3.5F, 0.0F, 0.0F, 7, 5, 0, 0.0F);
            this.Mainbody = new AdvancedModelRenderer(this, 0, 21);
            this.Mainbody.setRotationPoint(0.0F, 5.0F, 0.0F);
            this.Mainbody.addBox(-4.0F, 0.0F, -4.0F, 8, 3, 8, 0.0F);
            this.Tentacles1a = new AdvancedModelRenderer(this, 15, 36);
            this.Tentacles1a.setRotationPoint(0.0F, 3.0F, -4.0F);
            this.Tentacles1a.addBox(-3.5F, 0.0F, 0.0F, 7, 2, 0, 0.0F);
            this.setRotateAngle(Tentacles1a, -0.06370451936226872F, 0.0F, 0.0F);
            this.Oralarm3d = new AdvancedModelRenderer(this, 6, 1);
            this.Oralarm3d.setRotationPoint(0.0F, 3.5F, 0.0F);
            this.Oralarm3d.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(Oralarm3d, -0.12740903872453743F, 0.0F, 0.0F);
            this.Oralarm2a = new AdvancedModelRenderer(this, 20, 42);
            this.Oralarm2a.setRotationPoint(1.0F, 1.0F, -1.0F);
            this.Oralarm2a.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
            this.setRotateAngle(Oralarm2a, -0.10611601718967469F, 0.0F, -0.10611601718967469F);
            this.Tentacles1b = new AdvancedModelRenderer(this, 25, 21);
            this.Tentacles1b.setRotationPoint(0.0F, 2.0F, 0.0F);
            this.Tentacles1b.addBox(-3.5F, 0.0F, 0.0F, 7, 4, 0, 0.0F);
            this.setRotateAngle(Tentacles1b, 0.04241150198859518F, 0.0F, 0.0F);
            this.Oralarm1a = new AdvancedModelRenderer(this, 30, 39);
            this.Oralarm1a.setRotationPoint(1.0F, 1.0F, 1.0F);
            this.Oralarm1a.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
            this.setRotateAngle(Oralarm1a, 0.10611601718967469F, 0.0F, -0.10611601718967469F);
            this.Oralarm4c = new AdvancedModelRenderer(this, 40, 26);
            this.Oralarm4c.setRotationPoint(0.0F, 3.5F, 0.0F);
            this.Oralarm4c.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
            this.setRotateAngle(Oralarm4c, 0.10611601718967469F, 0.0F, 0.0F);
            this.Oralarm4a = new AdvancedModelRenderer(this, 39, 4);
            this.Oralarm4a.setRotationPoint(-1.0F, 1.0F, -1.0F);
            this.Oralarm4a.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
            this.setRotateAngle(Oralarm4a, -0.10611601718967469F, 0.0F, 0.10611601718967469F);
            this.Dometop = new AdvancedModelRenderer(this, 0, 6);
            this.Dometop.setRotationPoint(0.0F, -0.5F, 0.0F);
            this.Dometop.addBox(-1.5F, -1.0F, -1.5F, 3, 1, 3, 0.0F);
            this.Oralarm3c = new AdvancedModelRenderer(this, 40, 20);
            this.Oralarm3c.setRotationPoint(0.009999999776482582F, 3.5F, 0.0F);
            this.Oralarm3c.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1, 0.0F);
            this.setRotateAngle(Oralarm3c, -0.10611601718967469F, 0.0F, 0.0F);
            this.Tentacles4c = new AdvancedModelRenderer(this, 15, 8);
            this.Tentacles4c.setRotationPoint(0.0F, 4.0F, 0.0F);
            this.Tentacles4c.addBox(0.0F, 0.0F, -3.5F, 0, 5, 7, 0.0F);
            this.Oralarm3a = new AdvancedModelRenderer(this, 25, 42);
            this.Oralarm3a.setRotationPoint(-1.0F, 1.0F, 1.0F);
            this.Oralarm3a.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
            this.setRotateAngle(Oralarm3a, 0.10611601718967469F, 0.0F, 0.10611601718967469F);
            this.Oralarm2d = new AdvancedModelRenderer(this, 11, 1);
            this.Oralarm2d.setRotationPoint(0.0F, 3.5F, 0.0F);
            this.Oralarm2d.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
            this.setRotateAngle(Oralarm2d, 0.12740903872453743F, 0.0F, 0.0F);
            this.Tentacles1c = new AdvancedModelRenderer(this, 30, 9);
            this.Tentacles1c.setRotationPoint(0.0F, 4.0F, 0.0F);
            this.Tentacles1c.addBox(-3.5F, 0.0F, 0.0F, 7, 5, 0, 0.0F);
            this.Mainbody.addChild(this.Tentacles3a);
            this.Mainbody.addChild(this.Dome);
            this.Oralarm4c.addChild(this.Oralarm4d);
            this.Tentacles4a.addChild(this.Tentacles4b);
            this.Oralarm2b.addChild(this.Oralarm2c);
            this.Oralarm1a.addChild(this.Oralarm1b);
            this.Mainbody.addChild(this.Tentacles2a);
            this.Mainbody.addChild(this.Tentacles4a);
            this.Tentacles3b.addChild(this.Tentacles3c);
            this.Oralarm1b.addChild(this.Oralarm1c);
            this.Oralarm2a.addChild(this.Oralarm2b);
            this.Oralarm1c.addChild(this.Oralarm1d);
            this.Oralarm4a.addChild(this.Oralarm4b);
            this.Tentacles2a.addChild(this.Tentacles2b);
            this.Oralarm3a.addChild(this.Oralarm3b);
            this.Tentacles3a.addChild(this.Tentacles3b);
            this.Tentacles2b.addChild(this.Tentacles2c);
            this.Mainbody.addChild(this.Tentacles1a);
            this.Oralarm3c.addChild(this.Oralarm3d);
            this.Mainbody.addChild(this.Oralarm2a);
            this.Tentacles1a.addChild(this.Tentacles1b);
            this.Mainbody.addChild(this.Oralarm1a);
            this.Oralarm4b.addChild(this.Oralarm4c);
            this.Mainbody.addChild(this.Oralarm4a);
            this.Dome.addChild(this.Dometop);
            this.Oralarm3b.addChild(this.Oralarm3c);
            this.Tentacles4b.addChild(this.Tentacles4c);
            this.Mainbody.addChild(this.Oralarm3a);
            this.Oralarm2c.addChild(this.Oralarm2d);
            this.Tentacles1b.addChild(this.Tentacles1c);

            animator = ModelAnimator.create();
            updateDefaultPose();
        }

        @Override
        public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
            //setRotationAngles(f, f1, f2, f3, f4, f5, entity);
            //this.Bodyfront.render(0.5F);

            EntityCustom Jellyfish = (EntityCustom)entity;
            animate(Jellyfish, f, f1, f2, f3, f4, f5);

            this.Mainbody.render(f5 * 2F);

        }


        /**
         * This is a helper function from Tabula to set the rotation of model parts
         */
        public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
            modelRenderer.rotateAngleX = x;
            modelRenderer.rotateAngleY = y;
            modelRenderer.rotateAngleZ = z;
        }

        public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5)
        {
            EntityCustom Jellyfish = (EntityCustom)entity;
            animator.update(Jellyfish);
            setRotationAngles(f, f1, f2, f3, f4, f5, Jellyfish);

            //WANDER:
            animator.setAnimation(EntityCustom.ANIMATION_JELLYFISH_WANDER);
            //animator.startKeyframe(20);

        }

        @Override
        public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
            super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
            this.resetToDefaultPose();

            this.Mainbody.offsetY = 0.4F;
            this.Mainbody.setScaleX(1.0F);
            this.Mainbody.setScaleZ(1.0F);
            this.Mainbody.scaleChildren = false;
            AdvancedModelRenderer[] tentacle1 = {this.Tentacles1a, this.Tentacles1b, this.Tentacles1c};
            AdvancedModelRenderer[] tentacle2 = {this.Tentacles2a, this.Tentacles2b, this.Tentacles2c};
            AdvancedModelRenderer[] tentacle3 = {this.Tentacles3a, this.Tentacles3b, this.Tentacles3c};
            AdvancedModelRenderer[] tentacle4 = {this.Tentacles4a, this.Tentacles4b, this.Tentacles4c};
            AdvancedModelRenderer[] arm1 = {this.Oralarm1a, this.Oralarm1b, this.Oralarm1c};
            AdvancedModelRenderer[] arm2 = {this.Oralarm2a, this.Oralarm2b, this.Oralarm2c};
            AdvancedModelRenderer[] arm3 = {this.Oralarm3a, this.Oralarm3b, this.Oralarm3c};
            AdvancedModelRenderer[] arm4 = {this.Oralarm4a, this.Oralarm4b, this.Oralarm4c};
            float speed = 0.15F;
            if (!e.isInWater()) {
                speed = 0.0F;
            }
            if (e instanceof EntityLiving && !((EntityLiving) e).isAIDisabled()) {
                this.swing(Mainbody, speed * 0.4F,0.5F,true, 0,0, f2,1);
                this.walk(Mainbody, speed * 0.3F,0.8F,true, 0,0, f2,1);
                this.flap(Mainbody, speed * 0.2F,0.8F,true, 0,0, f2,1);
                this.chainWave(tentacle1, speed, 0.4F, -3, f2, 1);
                this.chainWave(tentacle2, speed, -0.4F, -3, f2, 1);
                this.chainFlap(tentacle3, speed, 0.4F, -3, f2, 1);
                this.chainFlap(tentacle4, speed, -0.4F, -3, f2, 1);
                this.chainWave(arm1, speed, 0.1F, -3, f2, 1);
                this.chainWave(arm2, speed, -0.1F, -3, f2, 1);
                this.chainWave(arm3, speed, 0.1F, -3, f2, 1);
                this.chainWave(arm4, speed, -0.1F, -3, f2, 1);
                this.bob(Mainbody, -speed * 0.5F, 1F, false, f2, 1);

                if (!e.isInWater()) {
                    this.resetToDefaultPose();
                    this.Mainbody.rotateAngleZ = (float) Math.toRadians(90);
                    this.Mainbody.offsetY = 0.8F;
                    this.Mainbody.scaleChildren = true;
                    this.Mainbody.setScaleX(0.2F);
                    this.Mainbody.setScaleZ(1.2F);
                }
            }
        }
    }
}
