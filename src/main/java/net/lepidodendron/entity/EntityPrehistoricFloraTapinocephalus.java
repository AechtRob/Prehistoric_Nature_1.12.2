
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockNestTapinocephalus;
import net.lepidodendron.entity.ai.*;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraLandBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class EntityPrehistoricFloraTapinocephalus extends EntityPrehistoricFloraLandBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;

	public EntityPrehistoricFloraTapinocephalus(World world) {
		super(world);
		//setSize(0.6F, 0.35F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
		minWidth = 0.18F;
		maxWidth = 0.85F;
		maxHeight = 1.01F;
		maxHealthAgeable = 21.0D;
	}

	public static String getPeriod() {return "Permian";}

	public static String getHabitat() {return "Terrestrial";}

	@Override
	public Block getNestBlock() {
		return BlockNestTapinocephalus.block;
	}

	@Override
	public int getAttackLength() {
		return 20;
	}

	@Override
	public String getTexture() {
		return this.getTexture();
	}

	@Override
	public boolean dropsEggs() {
		return false;
	}

	@Override
	public boolean laysEggs() {
		return true;
	}

	@Override
	public String tagEgg() {
		return "eggs_tapinocephalus";
	}
	
	protected float getAISpeedLand() {
		float speedBase = 0.315F;
		if (this.getTicks() < 0) {
			return 0.0F; //Is laying eggs
		}
		if (this.getIsFast()) {
			speedBase = speedBase * 1.66F;
		}
		return speedBase;
	}

	@Override
	public int getTalkInterval() {
		return 80;
	}

	@Override
	public int getAdultAge() {
		return 64000;
	}

	public AxisAlignedBB getAttackBoundingBox() {
		float size = this.getRenderSizeModifier() * 0.25F;
		return this.getEntityBoundingBox().grow(1.0F + size, 1.0F + size, 1.0F + size);
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 1.05F;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new LandEntitySwimmingAI(this, 0.75, false));
		tasks.addTask(1, new AttackAI(this, 1.0D, false, this.getAttackLength()));
		tasks.addTask(2, new PanicAI(this, 1.6D));
		tasks.addTask(3, new LandWanderFindNestAI(this, this.getNestBlock()));
		tasks.addTask(4, new GrappleAI(this, 1.0D, false, this.getAttackLength(), this.getGrappleAnimation(), 0.85));
		tasks.addTask(5, new LandWanderAvoidWaterAI(this, 1.0D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPrehistoricFloraAgeableBase.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EatPlantItemsAI(this, 1D));
		this.targetTasks.addTask(1, new EntityHurtByTargetSmallerThanMeAI(this, false));
	}

	@Override
	public boolean findGrappleTarget() {
		//System.err.println("finding grapple target");
		if (this.willGrapple) {
			return false;
		}
		List<EntityPrehistoricFloraTapinocephalus> Tapinocephalus = world.getEntitiesWithinAABB(EntityPrehistoricFloraTapinocephalus.class, new AxisAlignedBB(this.getPosition().add(-8, -4, -8), this.getPosition().add(8, 4, 8)));
		for (EntityPrehistoricFloraTapinocephalus currentTapinocephalus : Tapinocephalus) {
			if (currentTapinocephalus.isPFAdult() && this.isPFAdult() && currentTapinocephalus != this && !currentTapinocephalus.willGrapple) {
				this.setGrappleTarget(currentTapinocephalus);
				currentTapinocephalus.willGrapple=true;
				this.willGrapple = true;
				currentTapinocephalus.setGrappleTarget(this);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean grappleEntityAsMob(Entity entity) {
		if (this.getAnimation() == NO_ANIMATION) {
			this.setAnimation(this.getGrappleAnimation());
			//System.err.println("set attack");
		}
		return false;
	}

	public Animation getGrappleAnimation() {
		return this.ATTACK_ANIMATION;
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
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	public SoundEvent getAmbientSound() {
	    return (SoundEvent) SoundEvent.REGISTRY
	            .getObject(new ResourceLocation("lepidodendron:tapinocephalus_idle"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
	    return (SoundEvent) SoundEvent.REGISTRY
	            .getObject(new ResourceLocation("lepidodendron:tapinocephalus_hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
	    return (SoundEvent) SoundEvent.REGISTRY
	            .getObject(new ResourceLocation("lepidodendron:tapinocephalus_death"));
	}

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	@Override
	public boolean getCanSpawnHere() {
		return this.posY < (double) this.world.getSeaLevel() && this.isInWater();
	}
	
	@Override
	protected int getExperiencePoints(EntityPlayer player) {
		return 2 + this.world.rand.nextInt(3);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.renderYawOffset = this.rotationYaw;

		if ((!this.willGrapple) && this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 11 && this.getAttackTarget() != null) {
			this.launchAttack();
		}

		AnimationHandler.INSTANCE.updateAnimations(this);

	}

	@Override
	public void launchAttack() {
		if (this.getAttackTarget() != null) {
			IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttackTarget().addVelocity(0, 0.1, 0);
			boolean b = this.getAttackTarget().attackEntityFrom(DamageSource.causeMobDamage(this), (float) iattributeinstance.getAttributeValue());
			if (this.getOneHit()) {
				this.setAttackTarget(null);
				this.setRevengeTarget(null);
			}
		}
	}

	public ResourceLocation HeadbutSound() {
		return new ResourceLocation("lepidodendron:tapinocephalus_headbut");
	}

	@Override
	public void launchGrapple() {
		if (this.getGrappleTarget() != null) {
			if (!this.world.isRemote) {
				this.playSound((net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
						.getObject(this.HeadbutSound()), this.getSoundVolume(), 1);
			}
			double d1 = this.posX - this.getGrappleTarget().posX;
			double d0;

			for (d0 = this.posZ -  this.getGrappleTarget().posZ; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D)
			{
				d1 = (Math.random() - Math.random()) * 0.01D;
			}
			this.getGrappleTarget().knockBack(this, 0.4F, d1, d0);

			this.getGrappleTarget().addVelocity(0, 0.065, 0);

			if (this.getGrappleTarget() instanceof EntityPrehistoricFloraAgeableBase) {
				EntityPrehistoricFloraAgeableBase grappleTarget = (EntityPrehistoricFloraAgeableBase) this.getGrappleTarget();
				grappleTarget.setGrappleTarget(null);
				grappleTarget.willGrapple = false;
			}
			this.setGrappleTarget(null);
			this.willGrapple = false;

		}
	}

	public static final PropertyDirection FACING = BlockDirectional.FACING;

	public boolean testLay(World world, BlockPos pos) {

		//System.err.println("Testing laying conditions");

		if (
				world.getBlockState(pos).getBlock() == this.getNestBlock()
		) {
			String eggRenderType = new Object() {
				public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
				}
			}.getValue(new BlockPos(pos), "egg");
			if (eggRenderType.equals("")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (this.getAnimation() == NO_ANIMATION) {
			this.setAnimation(ATTACK_ANIMATION);
			//System.err.println("set attack");
		}
		return false;
	}

	public boolean isDirectPathBetweenPoints(Vec3d vec1, Vec3d vec2) {
		RayTraceResult movingobjectposition = this.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y, vec2.z), false, true, false);
		return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		if (!this.isPFAdult()) {
			return LepidodendronMod.TAPINOCEPHALUS_LOOT_YOUNG;
		}
		return LepidodendronMod.TAPINOCEPHALUS_LOOT;
	}

}