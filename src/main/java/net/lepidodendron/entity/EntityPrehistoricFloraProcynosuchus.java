
package net.lepidodendron.entity;

import com.google.common.base.Predicate;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockNestProcynosuchus;
import net.lepidodendron.entity.ai.*;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableFishBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraFishBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraSwimmingAmphibianBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
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
import java.util.Random;

public class EntityPrehistoricFloraProcynosuchus extends EntityPrehistoricFloraSwimmingAmphibianBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;

	public EntityPrehistoricFloraProcynosuchus(World world) {
		super(world);
		setSize(0.6F, 0.35F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
		//minSize = 0.3F;
		//maxSize = 1.0F;
		minWidth = 0.1F;
		maxWidth = 0.4F;
		maxHeight = 0.35F;
		maxHealthAgeable = 16.0D;
	}

	public float getTravelSpeed() {
		return this.getAISpeedSwimmingAmphibian();
	}

	public static String getPeriod() {
		return "late Permian";
	}

	public static String getHabitat() {
		return "Amphibious";
	}

	@Override
	public boolean breathesAir() {
		return true;
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
		return "eggs_procynosuchus";
	}

	protected float getAISpeedSwimmingAmphibian() {
		float calcSpeed = 0.282F;
		if (this.isReallyInWater()) {
			calcSpeed = 0.315f;
		}
		if (this.getTicks() < 0) {
			return 0.0F; //Is laying eggs
		}
		return Math.min(1F, (this.getAgeScale() * 2F)) * calcSpeed;
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
	public int getAdultAge() {
		return 36000;
	}

	@Override
	public int WaterDist() {
		return 0;
	}

	@Override
	public boolean isNearWater(Entity e, BlockPos pos) {
		return true;
	}

	public AxisAlignedBB getAttackBoundingBox() {
		float size = this.getRenderSizeModifier() * 0.25F;
		return this.getEntityBoundingBox().grow(1.0F + size, 1.0F + size, 1.0F + size);
	}

	protected void initEntityAI() {
		tasks.addTask(0, new AttackAI(this, 1.0D, false, this.getAttackLength()));
		tasks.addTask(1, new ProcynosuchusFindNestAI(this, this.getNestBlock()));
		tasks.addTask(2, new AmphibianWanderNotBound(this, NO_ANIMATION, 0.65, 90));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPrehistoricFloraFishBase.class, 8.0F));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPrehistoricFloraAgeableBase.class, 8.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EatFishItemsAI(this));
		this.targetTasks.addTask(1, new EntityHurtByTargetSmallerThanMeAI(this, false));
		this.targetTasks.addTask(2, new HuntAI(this, EntityPrehistoricFloraFishBase.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
		this.targetTasks.addTask(2, new HuntSmallerThanMeAIAgeable(this, EntityPrehistoricFloraAgeableFishBase.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase, 0));
		this.targetTasks.addTask(3, new HuntAI(this, EntitySquid.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
	}

	public Block getNestBlock() {
		return BlockNestProcynosuchus.block;
	}

	@Override
	public boolean isAIDisabled() {
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
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	protected boolean canTriggerWalking() {
		return true;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return (SoundEvent) SoundEvent.REGISTRY
				.getObject(new ResourceLocation("lepidodendron:procynosuchus_idle"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return (SoundEvent) SoundEvent.REGISTRY
				.getObject(new ResourceLocation("lepidodendron:procynosuchus_hurt"));
	}

	//@Override
	//public SoundEvent getHurtSound(DamageSource ds) {
	//	return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.hurt"));
	//}

	@Override
	public SoundEvent getDeathSound() {
		return (SoundEvent) SoundEvent.REGISTRY
				.getObject(new ResourceLocation("lepidodendron:procynosuchus_death"));
	}

	//@Override
	//public SoundEvent getDeathSound() {
	//	return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.death"));
	//}

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
	protected int getExperiencePoints(EntityPlayer player) {
		return 2 + this.world.rand.nextInt(3);
	}

	@Override
	public boolean isOnLadder() {
		return false;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.renderYawOffset = this.rotationYaw;

		if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 5 && this.getAttackTarget() != null) {
			launchAttack();
			if (this.getOneHit()) {
				this.setAttackTarget(null);
				this.setRevengeTarget(null);
			}
		}

		AnimationHandler.INSTANCE.updateAnimations(this);

	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
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
			return LepidodendronMod.PROCYNOSUCHUS_LOOT_YOUNG;
		}
		return LepidodendronMod.PROCYNOSUCHUS_LOOT;
	}


	class ProcynosuchusFindNestAI extends AnimationAINoAnimation<EntityPrehistoricFloraProcynosuchus> {

		protected Block nestblock;
		protected EntityPrehistoricFloraProcynosuchus PrehistoricFloraProcynosuchus;

		public ProcynosuchusFindNestAI(EntityPrehistoricFloraProcynosuchus PrehistoricFloraProcynosuchus, Block nestblock) {
			super(PrehistoricFloraProcynosuchus);
			setMutexBits(1);
			this.PrehistoricFloraProcynosuchus = PrehistoricFloraProcynosuchus;
			this.nestblock = nestblock;
		}

		@Override
		public Animation getAnimation() {
			return null;
		}

		@Override
		public boolean isAutomatic() {
			return true;
		}

		@Override
		public void startExecuting() {
			super.startExecuting();
		}

		@Override
		public void updateTask() {
			super.updateTask();

		}

		@Override
		public boolean shouldExecute() {

			if (!(this.PrehistoricFloraProcynosuchus.laysEggs() && this.PrehistoricFloraProcynosuchus.getCanBreed() && LepidodendronConfig.doMultiplyMobs)) {
				return false;
			}

			//if (this.PrehistoricFloraProcynosuchus.isReallyInWater()) {
			//	return false;
			//}

			//System.err.println("Ticks: " + this.PrehistoricFloraLandBase.getTicks());

			int xx;
			int yy;
			int zz;
			BlockPos vec3 = null;

			Path path = this.PrehistoricFloraProcynosuchus.getNavigator().getPath();
			if (!this.PrehistoricFloraProcynosuchus.getNavigator().noPath() && path != null) {
				xx = this.PrehistoricFloraProcynosuchus.getNavigator().getPath().getFinalPathPoint().x;
				yy = this.PrehistoricFloraProcynosuchus.getNavigator().getPath().getFinalPathPoint().y;
				zz = this.PrehistoricFloraProcynosuchus.getNavigator().getPath().getFinalPathPoint().z;
				if (this.PrehistoricFloraProcynosuchus.world.getBlockState(new BlockPos(xx, yy, zz)).getBlock() == nestblock) {
					vec3 = new BlockPos(xx, yy, zz);
				} else {
					vec3 = this.findBlockTarget(32);
				}
			} else {
				vec3 = this.findBlockTarget(32);
			}
			if (vec3 != null) {
				this.PrehistoricFloraProcynosuchus.getNavigator().tryMoveToXYZ(vec3.getX() + 0.5D, Math.floor(vec3.getY()) + 0.5D, vec3.getZ() + 0.5D, 1.0);
				return true;
			}

			return false;
		}

		@Override
		public boolean shouldContinueExecuting() {
			return false;
		}

		public boolean isDirectPathBetweenPoints(Entity entity, Vec3d vec1, Vec3d vec2) {
			RayTraceResult movingobjectposition = entity.world.rayTraceBlocks(vec1, new Vec3d(vec2.x, vec2.y + (double) entity.height * 0.5D, vec2.z), false, true, false);
			return movingobjectposition == null || movingobjectposition.typeOfHit != RayTraceResult.Type.BLOCK;
		}

		public BlockPos findBlockTarget(int dist) {
			Random rand = this.PrehistoricFloraProcynosuchus.getRNG();
			if (this.PrehistoricFloraProcynosuchus.getAttackTarget() == null) {
				for (int i = 0; i < 64; i++) {
					BlockPos randPos = this.PrehistoricFloraProcynosuchus.getPosition().add(rand.nextInt(dist + 1) - (int) (dist / 2), rand.nextInt((int) (dist / 2) + 1) - (int) (dist / 4), rand.nextInt(dist + 1) - (int) (dist / 2));
					if (this.PrehistoricFloraProcynosuchus.world.getBlockState(randPos).getBlock() == nestblock) {
						if (!(randPos.getY() < 1 || randPos.getY() >= 254)) {
							return randPos;
						}
					}
				}
			}
			return null;
		}


	}
}