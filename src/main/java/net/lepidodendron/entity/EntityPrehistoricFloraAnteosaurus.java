
package net.lepidodendron.entity;

import com.google.common.base.Predicate;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockNestAnteosaurus;
import net.lepidodendron.entity.ai.*;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraLandBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
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

public class EntityPrehistoricFloraAnteosaurus extends EntityPrehistoricFloraLandBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;
	public Animation GRAPPLE_ANIMATION;

	public EntityPrehistoricFloraAnteosaurus(World world) {
		super(world);
		//setSize(0.6F, 0.35F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
		minWidth = 0.18F;
		maxWidth = 1.1F;
		maxHeight = 1.3F;
		maxHealthAgeable = 50.0D;
		GRAPPLE_ANIMATION = Animation.create(this.getGrappleLength());
	}

	public int getGrappleLength() {
		return 200;
	}

	@Override
	public Animation[] getAnimations() {
		return new Animation[]{GRAPPLE_ANIMATION, ATTACK_ANIMATION, ROAR_ANIMATION, LAY_ANIMATION, EAT_ANIMATION};
	}


	public static String getPeriod() {return "Permian";}

	public static String getHabitat() {return "Terrestrial";}

	@Override
	public Block getNestBlock() {
		return BlockNestAnteosaurus.block;
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
		return "eggs_anteosaurus";
	}
	
	protected float getAISpeedLand() {
		float speedBase = 0.415F;
		if (this.getTicks() < 0) {
			return 0.0F; //Is laying eggs
		}
		if (this.getAnimation() == GRAPPLE_ANIMATION && this.getAnimationTick() < this.headbutTick()) {
			return 0.0F; //Is roaring prior to a headbut
		}
		if (this.getIsFast()) {
			speedBase = speedBase * 2.37F;
		}
		return speedBase;
	}

	@Override
	public int getTalkInterval() {
		return 180;
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
	public AxisAlignedBB getGrappleBoundingBox() {
		float size = this.getRenderSizeModifier() * 0.25F;
		return this.getEntityBoundingBox().grow(2.0F + size, 2.0F + size, 2.0F + size);
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 1.05F;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new LandEntitySwimmingAI(this, 0.75, false));
		tasks.addTask(1, new AttackAI(this, 1, false, this.getAttackLength()));
		tasks.addTask(2, new LandWanderFindNestAI(this, this.getNestBlock()));
		tasks.addTask(3, new GrappleAI(this, 1.0D, false, this.getAttackLength(), this.getGrappleAnimation(), 0.85));
		tasks.addTask(4, new LandWanderAvoidWaterAI(this, 1.0D, 45));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPrehistoricFloraAgeableBase.class, 8.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EatMeatItemsAI(this));
		this.targetTasks.addTask(1, new EntityHurtByTargetSmallerThanMeAI(this, false));
		this.targetTasks.addTask(2, new HuntPlayerAlwaysAI(this, EntityPlayer.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
		this.targetTasks.addTask(3, new HuntSmallerThanMeAIAgeable(this, EntityPrehistoricFloraAgeableBase.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase, 0.2D));
		this.targetTasks.addTask(4, new HuntAI(this, EntityPlayer.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
		this.targetTasks.addTask(5, new HuntSmallerThanMeAIAgeable(this, EntityLivingBase.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase, 0.2D));
	}

	@Override
	public boolean findGrappleTarget() {
		//System.err.println("finding grapple target");
		if (this.willGrapple) {
			return false;
		}
		List<EntityPrehistoricFloraAnteosaurus> Anteosaurus = world.getEntitiesWithinAABB(EntityPrehistoricFloraAnteosaurus.class, new AxisAlignedBB(this.getPosition().add(-8, -4, -8), this.getPosition().add(8, 4, 8)));
		for (EntityPrehistoricFloraAnteosaurus currentAnteosaurus : Anteosaurus) {
			if (currentAnteosaurus.isPFAdult() && this.isPFAdult() && currentAnteosaurus != this && !currentAnteosaurus.willGrapple) {
				this.setGrappleTarget(currentAnteosaurus);
				currentAnteosaurus.willGrapple=true;
				this.willGrapple = true;
				currentAnteosaurus.setGrappleTarget(this);
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
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	public SoundEvent getAmbientSound() {
	    return (SoundEvent) SoundEvent.REGISTRY
	            .getObject(new ResourceLocation("lepidodendron:anteosaurus_idle"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
	    return (SoundEvent) SoundEvent.REGISTRY
	            .getObject(new ResourceLocation("lepidodendron:anteosaurus_hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
	    return (SoundEvent) SoundEvent.REGISTRY
	            .getObject(new ResourceLocation("lepidodendron:anteosaurus_death"));
	}

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	public Animation getGrappleAnimation() {
		return this.GRAPPLE_ANIMATION;
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
	public int headbutTick() {
		return (200 - 30) + 10;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.renderYawOffset = this.rotationYaw;

		if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 11 && this.getAttackTarget() != null) {
			launchAttack();
			if (this.getOneHit()) {
				this.setAttackTarget(null);
				this.setRevengeTarget(null);
			}
		}

		if (this.willGrapple && this.getAnimation() == this.getGrappleAnimation() && this.getGrappleTarget() != null) {
			this.faceEntity(this.getGrappleTarget(), 100F, 100F);
			if (!this.world.isRemote && this.getAnimationTick() == 110) {
				this.playSound((net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
						.getObject(this.ThreatSound()), this.getSoundVolume(), 1);
			}
		}

		AnimationHandler.INSTANCE.updateAnimations(this);

	}

	public ResourceLocation ThreatSound() {
		return new ResourceLocation("lepidodendron:anteosaurus_threat");
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
			return LepidodendronMod.ANTEOSAURUS_LOOT_YOUNG;
		}
		return LepidodendronMod.ANTEOSAURUS_LOOT;
	}

}