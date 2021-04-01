
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.entity.ai.FishWanderBottomDweller;
import net.lepidodendron.item.entities.ItemBothriolepisMeat;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsLepidodendronMod.ModElement.Tag
public class EntityPrehistoricFloraBothriolepis extends ElementsLepidodendronMod.ModElement {
	public static final int ENTITYID = 15;
	public EntityPrehistoricFloraBothriolepis(ElementsLepidodendronMod instance) {
		super(instance, 1753);
	}

	@Override
	public void initElements() {
		elements.entities.add(() -> EntityEntryBuilder.create().entity(EntityCustom.class)
				.id(new ResourceLocation("lepidodendron", "prehistoric_flora_bothriolepis"), ENTITYID).name("prehistoric_flora_bothriolepis")
				.tracker(64, 3, true).egg(-3373540, -1).build());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustom.class, renderManager -> {
			return new RenderLiving(renderManager, new Modelbothriolepis(), 0.25f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("lepidodendron:textures/entities/bothriolepis.png");
				}
			};
		});
	}

	public static class EntityCustom extends EntityPrehistoricFloraFishBottomDwellerBase {

		public BlockPos currentTarget;
		@SideOnly(Side.CLIENT)
		public ChainBuffer chainBuffer;

		public EntityCustom(World world) {
			super(world);
			setSize(0.6F, 0.5F);
			experienceValue = 0;
			this.isImmuneToFire = false;
			setNoAI(!true);
			enablePersistence();
		}

		protected void initEntityAI() {
			tasks.addTask(0, new FishWanderBottomDweller(this, ANIMATION_FISH_WANDER));
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
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
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
		public SoundEvent getAmbientSound() {
			return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
		}

		@Override
		public SoundEvent getHurtSound(DamageSource ds) {
			return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.hurt"));
		}

        @Override
        public boolean attackEntityFrom(DamageSource source, float amount) {
			boolean isAtBottom = false;
			if (this.getPosition().getY() - 1 > 1) {
				BlockPos pos = new BlockPos(this.getPosition().getX(),this.getPosition().getY() - 1, this.getPosition().getZ());
				isAtBottom =  ((this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL))
						&& ((this.world.getBlockState(pos)).getMaterial() != Material.WATER));
			}
            if (isAtBottom && this.world.getBlockState(this.getPosition().up()).getMaterial() == Material.WATER) {
                this.setPositionAndUpdate(this.getPosition().up().getX(), this.getPosition().up().getY(), this.getPosition().up().getZ());
            }

			return super.attackEntityFrom(source, (amount * 0.1F));

        }

		@Override
		public SoundEvent getDeathSound() {
			return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.death"));
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
			return new ItemStack(ItemBothriolepisMeat.block, (int) (1)).getItem();
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
	 * bothriolepis - teiturerl
	 * Created using Tabula 7.1.0
	 */
	public class Modelbothriolepis extends AdvancedModelBase {
		public AdvancedModelRenderer Body;
		public AdvancedModelRenderer Shelltop2;
		public AdvancedModelRenderer Shelltop1;
		public AdvancedModelRenderer Head;
		public AdvancedModelRenderer Tail1;
		public AdvancedModelRenderer Leftpectoralfin1;
		public AdvancedModelRenderer Rightpectoralfin1;
		public AdvancedModelRenderer Shelltop3;
		public AdvancedModelRenderer Gills;
		public AdvancedModelRenderer Headfront;
		public AdvancedModelRenderer Lowerjaw;
		public AdvancedModelRenderer Tail2;
		public AdvancedModelRenderer Dorsalfin1;
		public AdvancedModelRenderer Leftpelvicfin;
		public AdvancedModelRenderer Rightpelvicfin;
		public AdvancedModelRenderer Tail3;
		public AdvancedModelRenderer Tail4;
		public AdvancedModelRenderer Dorsalfin2;
		public AdvancedModelRenderer Tail5;
		public AdvancedModelRenderer Tail6;
		public AdvancedModelRenderer Tailfin1;
		public AdvancedModelRenderer Tailfin2;
		public AdvancedModelRenderer Leftpectoralfin2;
		public AdvancedModelRenderer Rightpectoralfin2;

		public Modelbothriolepis() {
			this.textureWidth = 100;
			this.textureHeight = 100;
			this.Shelltop1 = new AdvancedModelRenderer(this, 29, 66);
			this.Shelltop1.setRotationPoint(0.0F, -6.300000190734863F, 6.800000190734863F);
			this.Shelltop1.addBox(-2.0F, 0.0F, -9.0F, 4, 2, 9, 0.0F);
			this.setRotateAngle(Shelltop1, 0.09512044090473959F, 0.0F, 0.0F);
			this.Tail6 = new AdvancedModelRenderer(this, 0, 8);
			this.Tail6.setRotationPoint(0.0F, 0.05000000074505806F, 7.5F);
			this.Tail6.addBox(-0.5F, -1.0F, 0.0F, 1, 2, 7, 0.0F);
			this.setRotateAngle(Tail6, -0.21223203437934937F, 0.0F, 0.0F);
			this.Gills = new AdvancedModelRenderer(this, 53, 93);
			this.Gills.setRotationPoint(0.009999999776482582F, 4.800000190734863F, -5.199999809265137F);
			this.Gills.addBox(-4.5F, -3.0F, 0.0F, 9, 3, 4, 0.0F);
			this.setRotateAngle(Gills, -0.42446406875869874F, 0.0F, 0.0F);
			this.Lowerjaw = new AdvancedModelRenderer(this, 67, 88);
			this.Lowerjaw.setRotationPoint(0.0F, 4.5F, -3.5F);
			this.Lowerjaw.addBox(-2.5F, -3.0F, 0.0F, 5, 3, 1, 0.0F);
			this.setRotateAngle(Lowerjaw, 0.38205256260891435F, 0.0F, 0.0F);
			this.Tail2 = new AdvancedModelRenderer(this, 0, 54);
			this.Tail2.setRotationPoint(0.0F, 0.800000011920929F, 5.0F);
			this.Tail2.addBox(-3.0F, -1.5F, -0.5F, 6, 5, 5, 0.0F);
			this.setRotateAngle(Tail2, -0.04241150198859518F, 0.0F, 0.0F);
			this.Tailfin1 = new AdvancedModelRenderer(this, 17, 7);
			this.Tailfin1.setRotationPoint(0.0F, -0.5F, 0.30000001192092896F);
			this.Tailfin1.addBox(0.0F, -6.0F, 0.0F, 0, 10, 9, 0.0F);
			this.setRotateAngle(Tailfin1, -0.36093409463874954F, 0.0F, 0.0F);
			this.Dorsalfin2 = new AdvancedModelRenderer(this, 16, 33);
			this.Dorsalfin2.setRotationPoint(0.0F, -1.0F, 0.0F);
			this.Dorsalfin2.addBox(0.0F, -6.0F, 0.0F, 0, 6, 9, 0.0F);
			this.Leftpelvicfin = new AdvancedModelRenderer(this, 29, 47);
			this.Leftpelvicfin.setRotationPoint(1.5F, 5.0F, 1.0F);
			this.Leftpelvicfin.addBox(0.0F, 0.0F, 0.0F, 5, 0, 6, 0.0F);
			this.setRotateAngle(Leftpelvicfin, 0.0F, -0.1485275233394591F, 0.4457571069383183F);
			this.Rightpectoralfin1 = new AdvancedModelRenderer(this, 51, 63);
			this.Rightpectoralfin1.setRotationPoint(-5.199999809265137F, 0.5F, -6.599999904632568F);
			this.Rightpectoralfin1.addBox(-2.5F, -1.0F, -0.5F, 3, 2, 9, 0.0F);
			this.setRotateAngle(Rightpectoralfin1, 0.12740903872453743F, -0.1485275233394591F, -0.36093409463874954F);
			this.Tail5 = new AdvancedModelRenderer(this, 0, 18);
			this.Tail5.setRotationPoint(0.0F, 0.0F, 6.0F);
			this.Tail5.addBox(-1.0F, -1.0F, 0.0F, 2, 3, 9, 0.0F);
			this.setRotateAngle(Tail5, 0.27593656206399647F, 0.0F, 0.0F);
			this.Rightpectoralfin2 = new AdvancedModelRenderer(this, 50, 53);
			this.Rightpectoralfin2.setRotationPoint(-1.7000000476837158F, 0.0F, 8.0F);
			this.Rightpectoralfin2.addBox(-0.5F, -0.5F, 0.0F, 2, 1, 8, 0.0F);
			this.setRotateAngle(Rightpectoralfin2, 0.12740903872453743F, 0.2546435405291338F, 0.06370451936226872F);
			this.Headfront = new AdvancedModelRenderer(this, 76, 89);
			this.Headfront.setRotationPoint(0.0F, 0.0F, -4.0F);
			this.Headfront.addBox(-3.5F, 0.0F, -4.0F, 7, 3, 4, 0.0F);
			this.setRotateAngle(Headfront, 0.7429866572476639F, 0.0F, 0.0F);
			this.Tail4 = new AdvancedModelRenderer(this, 0, 31);
			this.Tail4.setRotationPoint(0.0F, 0.30000001192092896F, 6.5F);
			this.Tail4.addBox(-1.5F, -1.0F, -0.5F, 3, 3, 7, 0.0F);
			this.setRotateAngle(Tail4, 0.10611601718967469F, 0.0F, 0.0F);
			this.Tailfin2 = new AdvancedModelRenderer(this, 10, 1);
			this.Tailfin2.setRotationPoint(0.009999999776482582F, -0.30000001192092896F, 1.0F);
			this.Tailfin2.addBox(0.0F, -2.5F, 0.0F, 0, 5, 8, 0.0F);
			this.setRotateAngle(Tailfin2, -0.08482300397719036F, 0.0F, 0.0F);
			this.Tail1 = new AdvancedModelRenderer(this, 0, 65);
			this.Tail1.setRotationPoint(0.0F, -3.5F, 6.5F);
			this.Tail1.addBox(-4.0F, -1.0F, -0.5F, 8, 6, 6, 0.0F);
			this.setRotateAngle(Tail1, -0.06370451936226872F, 0.0F, 0.0F);
			this.Tail3 = new AdvancedModelRenderer(this, 0, 42);
			this.Tail3.setRotationPoint(0.0F, -0.10000000149011612F, 4.300000190734863F);
			this.Tail3.addBox(-2.0F, -1.0F, -0.5F, 4, 4, 7, 0.0F);
			this.setRotateAngle(Tail3, 0.12740903872453743F, 0.0F, 0.0F);
			this.Head = new AdvancedModelRenderer(this, 38, 82);
			this.Head.setRotationPoint(0.0F, -4.599999904632568F, -8.199999809265137F);
			this.Head.addBox(-4.5F, 0.0F, -4.0F, 9, 5, 5, 0.0F);
			this.setRotateAngle(Head, 0.40334560078853393F, 0.0F, 0.0F);
			this.Dorsalfin1 = new AdvancedModelRenderer(this, 23, 60);
			this.Dorsalfin1.setRotationPoint(0.0F, -0.5F, 1.0F);
			this.Dorsalfin1.addBox(0.0F, -4.0F, 0.0F, 0, 4, 6, 0.0F);
			this.Leftpectoralfin2 = new AdvancedModelRenderer(this, 67, 62);
			this.Leftpectoralfin2.setRotationPoint(1.7000000476837158F, 0.0F, 8.0F);
			this.Leftpectoralfin2.addBox(-1.5F, -0.5F, 0.0F, 2, 1, 8, 0.0F);
			this.setRotateAngle(Leftpectoralfin2, 0.12740903872453743F, -0.2546435405291338F, -0.06370451936226872F);
			this.Leftpectoralfin1 = new AdvancedModelRenderer(this, 63, 49);
			this.Leftpectoralfin1.setRotationPoint(5.199999809265137F, 0.5F, -6.599999904632568F);
			this.Leftpectoralfin1.addBox(-0.5F, -1.0F, -0.5F, 3, 2, 9, 0.0F);
			this.setRotateAngle(Leftpectoralfin1, 0.12740903872453743F, 0.1485275233394591F, 0.36093409463874954F);
			this.Body = new AdvancedModelRenderer(this, 0, 78);
			this.Body.setRotationPoint(0.0F, 20.0F, 0.0F);
			this.Body.addBox(-5.5F, -5.0F, -8.0F, 11, 7, 15, 0.0F);
			this.Shelltop3 = new AdvancedModelRenderer(this, 35, 41);
			this.Shelltop3.setRotationPoint(0.009999999776482582F, -1.0F, 0.0F);
			this.Shelltop3.addBox(-4.0F, 0.0F, -4.0F, 8, 1, 4, 0.0F);
			this.setRotateAngle(Shelltop3, 0.33964105645913F, 0.0F, 0.0F);
			this.Shelltop2 = new AdvancedModelRenderer(this, 23, 54);
			this.Shelltop2.setRotationPoint(0.0F, -4.900000095367432F, -4.199999809265137F);
			this.Shelltop2.addBox(-4.0F, -1.0F, 0.0F, 8, 1, 10, 0.0F);
			this.setRotateAngle(Shelltop2, -0.09337511331722095F, 0.0F, 0.0F);
			this.Rightpelvicfin = new AdvancedModelRenderer(this, 40, 47);
			this.Rightpelvicfin.setRotationPoint(-1.5F, 5.0F, 1.0F);
			this.Rightpelvicfin.addBox(-5.0F, 0.0F, 0.0F, 5, 0, 6, 0.0F);
			this.setRotateAngle(Rightpelvicfin, 0.0F, 0.1485275233394591F, -0.4457571069383183F);
			this.Body.addChild(this.Shelltop1);
			this.Tail5.addChild(this.Tail6);
			this.Head.addChild(this.Gills);
			this.Headfront.addChild(this.Lowerjaw);
			this.Tail1.addChild(this.Tail2);
			this.Tail5.addChild(this.Tailfin1);
			this.Tail3.addChild(this.Dorsalfin2);
			this.Tail1.addChild(this.Leftpelvicfin);
			this.Body.addChild(this.Rightpectoralfin1);
			this.Tail4.addChild(this.Tail5);
			this.Rightpectoralfin1.addChild(this.Rightpectoralfin2);
			this.Head.addChild(this.Headfront);
			this.Tail3.addChild(this.Tail4);
			this.Tail6.addChild(this.Tailfin2);
			this.Body.addChild(this.Tail1);
			this.Tail2.addChild(this.Tail3);
			this.Body.addChild(this.Head);
			this.Tail1.addChild(this.Dorsalfin1);
			this.Leftpectoralfin1.addChild(this.Leftpectoralfin2);
			this.Body.addChild(this.Leftpectoralfin1);
			this.Shelltop2.addChild(this.Shelltop3);
			this.Body.addChild(this.Shelltop2);
			this.Tail1.addChild(this.Rightpelvicfin);

			updateDefaultPose();
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			//setRotationAngles(f, f1, f2, f3, f4, f5, entity);
			//this.Bodyfront.render(0.5F);

			this.Body.render(f5 * 0.4F);

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
			this.Body.offsetY = 0.95F;

			//this.Tailfin.setScale(1.1F, 1.1F, 1.1F);
			AdvancedModelRenderer[] fishTail = {this.Tail1, this.Tail2, this.Tail3, this.Tail4, this.Tail5, this.Tail6};
			AdvancedModelRenderer[] fishfinL = {this.Leftpectoralfin1, this.Leftpectoralfin2};
			AdvancedModelRenderer[] fishfinR = {this.Rightpectoralfin1, this.Rightpectoralfin2};

			float speed = 0.4F;
			float taildegree = 0.35F;

			if (!e.isInWater()) {
				speed = 0.7F;
			}

			boolean isAtBottom = false;
			if (e.getPosition().getY() - 1 > 1) {
				BlockPos pos = new BlockPos(e.getPosition().getX(),e.getPosition().getY() - 1, e.getPosition().getZ());
				isAtBottom =  ((e.isInsideOfMaterial(Material.WATER) || e.isInsideOfMaterial(Material.CORAL))
						&& ((e.world.getBlockState(pos)).getMaterial() != Material.WATER));
			}
			if (isAtBottom) {
				//System.err.println("Animation at bottom");
				speed = 0.15F;
				taildegree = 0.15F;
			}

			if (e instanceof EntityLiving && !((EntityLiving) e).isAIDisabled()) {
				this.chainWave(fishTail, speed, 0.05F, -3, f2, 1);
				this.chainSwing(fishTail, speed, taildegree, -3, f2, 1);
				this.chainSwing(fishfinL, speed, -0.15F, -3, f2, 1);
				this.chainWave(fishfinL, speed, 0.15F, -3, f2, 1);
				this.chainSwing(fishfinR, speed, 0.15F, -3, f2, 1);
				this.chainWave(fishfinR, speed, 0.15F, -3, f2, 1);
				this.swing(Body, speed, 0.3F, true, 0, 0, f2, 1);
				this.walk(Lowerjaw, (float) (speed * 0.75), -0.3F, true, 0, 0, f2, 1);
				this.walk(Leftpelvicfin, (float) (speed * 0.75), 0.12F, true, 0, 0, f2, 1);
				this.swing(Leftpelvicfin, (float) (speed * 0.75), 0.12F, true, 0, 0, f2, 1);
				this.walk(Rightpelvicfin, (float) (speed * 0.75), 0.12F, true, 0, 0, f2, 1);
				this.swing(Rightpelvicfin, (float) (speed * 0.75), 0.12F, true, 0, 0, f2, 1);
				if (!e.isInWater()) {
					//this.Bodyfront.rotateAngleZ = (float) Math.toRadians(90);
					this.Body.offsetY = 0.95F;
					this.bob(Body, -speed, 2F, false, f2, 1);
					this.chainWave(fishTail, speed, 0.2F, -3, f2, 1);
				}
			}
		}
	}
}
