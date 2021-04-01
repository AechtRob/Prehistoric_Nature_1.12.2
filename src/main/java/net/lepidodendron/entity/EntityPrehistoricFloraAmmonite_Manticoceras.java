
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.entity.ai.NautiloidWander;
import net.lepidodendron.entity.base.EntityPrehistoricFloraNautiloidBase;
import net.lepidodendron.entity.model.ModelAmmonite15cm;
import net.lepidodendron.entity.model.ModelAmmonite50cm;
import net.lepidodendron.item.entities.ItemAmmoniteRaw;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
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


public class EntityPrehistoricFloraAmmonite_Manticoceras extends EntityPrehistoricFloraNautiloidBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;

	public EntityPrehistoricFloraAmmonite_Manticoceras(World world) {
		super(world);
		setSize(0.5F, 0.3F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
	}

	@Override
	protected float getAISpeedNautiloid() {
		return 0.2f;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new NautiloidWander(this, ANIMATION_NAUTILOID_WANDER));
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
	public SoundEvent getAmbientSound() {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.hurt"));
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
		//return null;
		return new ItemStack(ItemAmmoniteRaw.block, (int) (1)).getItem();
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