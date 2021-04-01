
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.lepidodendron.entity.ai.FishWander;
import net.lepidodendron.item.entities.ItemAcanthodesMeat;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;

import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.FMLCommonHandler;
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
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBase;

import net.lepidodendron.ElementsLepidodendronMod;

import java.util.Iterator;
import java.util.ArrayList;

@ElementsLepidodendronMod.ModElement.Tag
public class EntityPrehistoricFloraAcanthodes extends ElementsLepidodendronMod.ModElement {
	public static final int ENTITYID = 7;
	public EntityPrehistoricFloraAcanthodes(ElementsLepidodendronMod instance) {
		super(instance, 1753);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityCustom.class)
				.id(new ResourceLocation("lepidodendron", "prehistoric_flora_acanthodes"), ENTITYID).name("prehistoric_flora_acanthodes")
				.tracker(64, 3, true).egg(-1, -1).build());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustom.class, renderManager -> {
			return new RenderLiving(renderManager, new Modelacanthodes(), 0.25f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("lepidodendron:textures/entities/acanthodes_devonian.png");
				}
			};
		});
	}

	public static class EntityCustom extends EntityPrehistoricFloraFishBase {

		public BlockPos currentTarget;
		@SideOnly(Side.CLIENT)
		public ChainBuffer chainBuffer;

		public EntityCustom(World world) {
			super(world);
			setSize(0.5F, 0.3F);
			experienceValue = 0;
			this.isImmuneToFire = false;
			setNoAI(!true);
			enablePersistence();
		}

		protected void initEntityAI() {
			tasks.addTask(0, new FishWander(this, ANIMATION_FISH_WANDER));
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

		@Override
		protected Item getDropItem() {
			return new ItemStack(ItemAcanthodesMeat.block, (int) (1)).getItem();
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

	}

	/**
	 * acanthodes - teiturerl Created using Tabula 7.1.0
	 */
	public static class Modelacanthodes extends AdvancedModelBase {
		public AdvancedModelRenderer Bodyfront;
		public AdvancedModelRenderer Gills;
		public AdvancedModelRenderer Bodymiddle;
		public AdvancedModelRenderer Leftpectoralfin;
		public AdvancedModelRenderer Rightpectoralfin;
		public AdvancedModelRenderer Head;
		public AdvancedModelRenderer Lowerjaw;
		public AdvancedModelRenderer Tail1;
		public AdvancedModelRenderer Back1;
		public AdvancedModelRenderer Belly1;
		public AdvancedModelRenderer Tail2;
		public AdvancedModelRenderer Back2;
		public AdvancedModelRenderer Belly2;
		public AdvancedModelRenderer Tail3;
		public AdvancedModelRenderer Belly3;
		public AdvancedModelRenderer Dorsalfin;
		public AdvancedModelRenderer Tailfin;
		public AdvancedModelRenderer Analfin;
		public AdvancedModelRenderer Leftpelvicfin;
		public AdvancedModelRenderer Rightpelvicfin;

		public Modelacanthodes() {
			this.textureWidth = 100;
			this.textureHeight = 100;
			this.Head = new AdvancedModelRenderer(this, 34, 34);
			this.Head.setRotationPoint(0.009999999776482582F, -1.899999976158142F, -1.5F);
			this.Head.addBox(-2.5F, -1.0F, -9.0F, 5, 5, 10, 0.0F);
			this.setRotateAngle(Head, 0.21223203437934937F, 0.0F, 0.0F);
			this.Bodymiddle = new AdvancedModelRenderer(this, 10, 34);
			this.Bodymiddle.setRotationPoint(0.0F, 2.5F, 2.299999952316284F);
			this.Bodymiddle.addBox(-2.0F, -2.5F, 0.0F, 4, 5, 15, 0.0F);
			this.setRotateAngle(Bodymiddle, 0.008726646259971648F, 0.0F, 0.0F);
			this.Back2 = new AdvancedModelRenderer(this, 19, 18);
			this.Back2.setRotationPoint(0.0F, -2.0F, 0.0F);
			this.Back2.addBox(-1.0F, -1.0F, 0.0F, 2, 1, 9, 0.0F);
			this.setRotateAngle(Back2, -0.081157811882212F, 0.0F, 0.0F);
			this.Tail3 = new AdvancedModelRenderer(this, 0, 1);
			this.Tail3.setRotationPoint(0.0F, -0.4000000059604645F, 10.5F);
			this.Tail3.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 15, 0.0F);
			this.setRotateAngle(Tail3, 0.15725416959943073F, 0.0F, 0.0F);
			this.Belly3 = new AdvancedModelRenderer(this, 18, 0);
			this.Belly3.setRotationPoint(0.0F, 2.0F, 0.0F);
			this.Belly3.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 11, 0.0F);
			this.setRotateAngle(Belly3, 0.08482300397719036F, 0.0F, 0.0F);
			this.Tailfin = new AdvancedModelRenderer(this, 49, 7);
			this.Tailfin.setRotationPoint(0.0F, 0.5F, 0.0F);
			this.Tailfin.addBox(0.0F, 0.0F, 0.0F, 0, 8, 15, 0.0F);
			this.Gills = new AdvancedModelRenderer(this, 23, 55);
			this.Gills.setRotationPoint(0.0F, 2.0999999046325684F, -7.199999809265137F);
			this.Gills.addBox(-3.0F, -1.5F, -5.0F, 6, 5, 6, 0.0F);
			this.setRotateAngle(Gills, -0.12740903872453743F, 0.0F, 0.0F);
			this.Belly1 = new AdvancedModelRenderer(this, 40, 0);
			this.Belly1.setRotationPoint(0.0F, 2.5F, 0.0F);
			this.Belly1.addBox(-1.5F, 0.0F, 0.0F, 3, 1, 15, 0.0F);
			this.setRotateAngle(Belly1, 0.03193952606051029F, 0.0F, 0.0F);
			this.Bodyfront = new AdvancedModelRenderer(this, 0, 55);
			this.Bodyfront.setRotationPoint(0.0F, 15.0F, -3.0F);
			this.Bodyfront.addBox(-2.5F, -1.0F, -9.0F, 5, 7, 12, 0.0F);
			this.Belly2 = new AdvancedModelRenderer(this, 32, 0);
			this.Belly2.setRotationPoint(0.0F, 2.0F, 0.0F);
			this.Belly2.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 9, 0.0F);
			this.setRotateAngle(Belly2, 0.08988445814218365F, 0.0F, 0.0F);
			this.Rightpelvicfin = new AdvancedModelRenderer(this, 54, 26);
			this.Rightpelvicfin.setRotationPoint(-0.699999988079071F, 1.0F, 4.0F);
			this.Rightpelvicfin.addBox(0.0F, 0.0F, 0.0F, 0, 4, 13, 0.0F);
			this.setRotateAngle(Rightpelvicfin, 0.06370451936226872F, 0.0F, 0.7641051252178287F);
			this.Tail1 = new AdvancedModelRenderer(this, 0, 33);
			this.Tail1.setRotationPoint(0.0F, 0.0F, 14.5F);
			this.Tail1.addBox(-1.5F, -2.0F, 0.0F, 3, 4, 9, 0.0F);
			this.setRotateAngle(Tail1, 0.03525565055739032F, 0.0F, 0.0F);
			this.Leftpelvicfin = new AdvancedModelRenderer(this, 54, 21);
			this.Leftpelvicfin.setRotationPoint(0.699999988079071F, 1.0F, 4.0F);
			this.Leftpelvicfin.addBox(0.0F, 0.0F, 0.0F, 0, 4, 13, 0.0F);
			this.setRotateAngle(Leftpelvicfin, 0.06370451936226872F, 0.0F, -0.7641051252178287F);
			this.Lowerjaw = new AdvancedModelRenderer(this, 42, 49);
			this.Lowerjaw.setRotationPoint(0.0F, 2.0F, -2.4000000953674316F);
			this.Lowerjaw.addBox(-2.0F, 0.0F, -7.0F, 4, 2, 9, 0.0F);
			this.setRotateAngle(Lowerjaw, 0.4F, 0.0F, 0.0F);
			this.Dorsalfin = new AdvancedModelRenderer(this, 68, 12);
			this.Dorsalfin.setRotationPoint(0.0F, -1.0F, 5.5F);
			this.Dorsalfin.addBox(0.0F, -4.0F, 0.0F, 0, 4, 5, 0.0F);
			this.Back1 = new AdvancedModelRenderer(this, 27, 17);
			this.Back1.setRotationPoint(0.0F, -2.5F, 0.0F);
			this.Back1.addBox(-1.5F, -1.0F, 0.0F, 3, 1, 15, 0.0F);
			this.setRotateAngle(Back1, -0.03176499330175842F, 0.0F, 0.0F);
			this.Leftpectoralfin = new AdvancedModelRenderer(this, 53, 50);
			this.Leftpectoralfin.setRotationPoint(2.5F, 5.5F, -7.0F);
			this.Leftpectoralfin.addBox(0.0F, 0.0F, 0.0F, 8, 0, 6, 0.0F);
			this.setRotateAngle(Leftpectoralfin, 0.10611601718967469F, -0.4457571069383183F, 0.42446406875869874F);
			this.Tail2 = new AdvancedModelRenderer(this, 0, 18);
			this.Tail2.setRotationPoint(0.0F, -1.0F, 8.199999809265137F);
			this.Tail2.addBox(-1.0F, -1.0F, 0.0F, 2, 3, 11, 0.0F);
			this.setRotateAngle(Tail2, 0.05113814824856683F, 0.0F, 0.0F);
			this.Rightpectoralfin = new AdvancedModelRenderer(this, 42, 61);
			this.Rightpectoralfin.setRotationPoint(-2.5F, 5.5F, -7.0F);
			this.Rightpectoralfin.addBox(-8.0F, 0.0F, 0.0F, 8, 0, 6, 0.0F);
			this.setRotateAngle(Rightpectoralfin, 0.10611601718967469F, 0.4457571069383183F, -0.42446406875869874F);
			this.Analfin = new AdvancedModelRenderer(this, 49, 8);
			this.Analfin.setRotationPoint(0.0F, 1.0F, 0.0F);
			this.Analfin.addBox(0.0F, 0.0F, 0.0F, 0, 4, 9, 0.0F);
			this.Gills.addChild(this.Head);
			this.Bodyfront.addChild(this.Bodymiddle);
			this.Tail1.addChild(this.Back2);
			this.Tail2.addChild(this.Tail3);
			this.Tail2.addChild(this.Belly3);
			this.Tail3.addChild(this.Tailfin);
			this.Bodyfront.addChild(this.Gills);
			this.Bodymiddle.addChild(this.Belly1);
			this.Tail1.addChild(this.Belly2);
			this.Belly1.addChild(this.Rightpelvicfin);
			this.Bodymiddle.addChild(this.Tail1);
			this.Belly1.addChild(this.Leftpelvicfin);
			this.Gills.addChild(this.Lowerjaw);
			this.Tail2.addChild(this.Dorsalfin);
			this.Bodymiddle.addChild(this.Back1);
			this.Bodyfront.addChild(this.Leftpectoralfin);
			this.Tail1.addChild(this.Tail2);
			this.Bodyfront.addChild(this.Rightpectoralfin);
			this.Belly3.addChild(this.Analfin);

			updateDefaultPose();
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			this.Bodyfront.render(f5 * 0.2F);
		}

		/**
		 * This is a helper function from Tabula to set the rotation of model parts
		 */
		public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		@Override
		public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
			super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
			this.resetToDefaultPose();
			this.Bodyfront.offsetY = 1.1F;
			this.Gills.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Gills.rotateAngleX = f4 / (180F / (float) Math.PI);

			//this.Tailfin.setScale(1.1F, 1.1F, 1.1F);
			AdvancedModelRenderer[] fishTail = {this.Tail1, this.Tail2, this.Tail3};
			float speed = 0.3F;
			if (!e.isInWater()) {
				speed = 0.7F;
			}
			if (e instanceof EntityLiving && !((EntityLiving) e).isAIDisabled()) {
				this.chainWave(fishTail, speed, 0.05F, -3, f2, 1);
				this.chainSwing(fishTail, speed, 0.5F, -3, f2, 1);
				this.swing(Bodyfront, speed, 0.3F, true, 0, 0, f2, 1);
				this.walk(Lowerjaw, (float) (speed * 0.75), 0.2F, true, 0, 0, f2, 1);
				this.walk(Leftpectoralfin, (float) (speed * 0.75), 0.2F, true, 0, 0, f2, 1);
				this.swing(Leftpectoralfin, (float) (speed * 0.75), 0.2F, true, 0, 0, f2, 1);
				this.walk(Rightpectoralfin, (float) (speed * 0.75), 0.2F, true, 0, 0, f2, 1);
				this.swing(Rightpectoralfin, (float) (speed * 0.75), 0.2F, true, 0, 0, f2, 1);
				if (!e.isInWater()) {
					this.Bodyfront.rotateAngleZ = (float) Math.toRadians(90);
					this.Bodyfront.offsetY = 1.25F;
					this.bob(Bodyfront, -speed, 5F, false, f2, 1);
				}
			}
		}
	}
}
