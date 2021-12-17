
package net.lepidodendron.entity;

import com.google.common.base.Optional;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.*;
import net.lepidodendron.entity.ai.*;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraLandBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
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

public class EntityPrehistoricFloraDiictodon extends EntityPrehistoricFloraLandBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;
	private boolean screaming;
	private int alarmCooldown;

	protected static final DataParameter<Optional<BlockPos>> NEST_BLOCK_POS = EntityDataManager.createKey(EntityPrehistoricFloraDiictodon.class, DataSerializers.OPTIONAL_BLOCK_POS);

	public EntityPrehistoricFloraDiictodon(World world) {
		super(world);
		//setSize(0.6F, 0.35F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
		minWidth = 0.10F;
		maxWidth = 0.30F;
		maxHeight = 0.25F;
		maxHealthAgeable = 8.0D;
	}

	public static String getPeriod() {return "Permian";}

	public static String getHabitat() {return "Terrestrial";}

	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(NEST_BLOCK_POS, Optional.absent());
	}

	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (compound.hasKey("PosX")) {
			int i = compound.getInteger("PosX");
			int j = compound.getInteger("PosY");
			int k = compound.getInteger("PosZ");
			this.dataManager.set(NEST_BLOCK_POS, Optional.of(new BlockPos(i, j, k)));
		} else {
			this.dataManager.set(NEST_BLOCK_POS, Optional.absent());
		}
	}

	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		if (this.getNestLocation() != null) {
			compound.setInteger("PosX", this.getNestLocation().getX());
			compound.setInteger("PosY", this.getNestLocation().getY());
			compound.setInteger("PosZ", this.getNestLocation().getZ());
		}
	}

	@Nullable
	public BlockPos getNestLocation() {
		return (BlockPos) ((Optional) this.dataManager.get(NEST_BLOCK_POS)).orNull();
	}

	public void setNestLocation(@Nullable BlockPos pos) {
		this.dataManager.set(NEST_BLOCK_POS, Optional.fromNullable(pos));
	}

	public BlockPos findNest(Entity entity, int dist) {
		int xx;
		int yy;
		int zz;
		BlockPos randPos;
		xx = -dist;
		while (xx <= dist) {
			yy = (int) -Math.round((double) dist / 2D);
			while (yy <= (int) Math.round((double) dist / 2D)) {
				zz = -dist;
				while (zz <= dist) {
					if (entity.getPosition().getY() + yy >= 1 && entity.getPosition().getY() + yy <= 255) {
						randPos = entity.getPosition().add(xx, yy, zz);
						if (entity.world.getBlockState(randPos).getBlock() == this.getNestBlock()) {
							if (!(randPos.getY() < 1 || randPos.getY() >= 254)) {
								return randPos;
							}
						}
					}
					zz += 1;
				}
				yy += 1;
			}
			xx += 1;
		}
		return null;
	}

	@Override
	public boolean attackEntityFrom(DamageSource ds, float i) {
		Entity e = ds.getTrueSource();
		if (e instanceof EntityLivingBase) {
			EntityLivingBase ee = (EntityLivingBase) e;
			List<EntityPrehistoricFloraDiictodon> Diictodon = this.world.getEntitiesWithinAABB(EntityPrehistoricFloraDiictodon.class, new AxisAlignedBB(this.getPosition().add(-8, -4, -8), this.getPosition().add(8, 4, 8)));
			for (EntityPrehistoricFloraDiictodon currentDiictodon : Diictodon) {
				currentDiictodon.setRevengeTarget(ee);
			}
		}
		return super.attackEntityFrom(ds, i);
	}

	public void setScreaming(boolean screaming) {
		this.screaming = screaming;
	}

	public boolean getScreaming() {
		return this.screaming;
	}

	@Override
	public Block getNestBlock() {
		return BlockNestDiictodon.block;
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
		return "eggs_diictodon";
	}
	
	protected float getAISpeedLand() {
		float speedBase = 0.348F;
		if (this.getTicks() < 0) {
			return 0.0F; //Is laying eggs
		}
		if (this.getIsFast()) {
			speedBase = speedBase * 1.25F;
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
	public boolean homesToNest() {
		return true;
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 1.05F;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new LandEntitySwimmingAI(this, 0.75, true));
		tasks.addTask(1, new NightFindNestAI(this, this.getNestBlock()));
		tasks.addTask(2, new AttackAI(this, 1.6D, false, this.getAttackLength()));
		tasks.addTask(3, new PanicFindNestAI(this, 1.6D, this.getNestBlock()));
		tasks.addTask(4, new LandWanderFindNestAI(this, this.getNestBlock()));
		tasks.addTask(5, new LandWanderAvoidWaterAI(this, 1.0D, 20));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPrehistoricFloraAgeableBase.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EatPlantItemsAI(this, 1.5));
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
	            .getObject(new ResourceLocation("lepidodendron:diictodon_idle"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
	    return (SoundEvent) SoundEvent.REGISTRY
	            .getObject(new ResourceLocation("lepidodendron:diictodon_hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
	    return (SoundEvent) SoundEvent.REGISTRY
	            .getObject(new ResourceLocation("lepidodendron:diictodon_death"));
	}

	public SoundEvent getAlarmSound() {
		return (SoundEvent) SoundEvent.REGISTRY
				.getObject(new ResourceLocation("lepidodendron:diictodon_alarm"));
	}

	public void playAlarmSound()
	{
		SoundEvent soundevent = this.getAlarmSound();
		//System.err.println("looking for alarm sound");
		if (soundevent != null)
		{
			//System.err.println("playing alarm sound");
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
			this.alarmCooldown = 20;
		}
	}

	@Override
	public void onEntityUpdate() {
		if (this.alarmCooldown > 0) {
			this.alarmCooldown -= 1;
		}
		if (this.getScreaming() && alarmCooldown <= 0) {
			this.playAlarmSound();
		}

		if (!world.isRemote) {
			if ((double) this.getTicks() / 200D == (int) Math.round((double) this.getTicks() / 200D)) {
				@Nullable BlockPos pos = this.getNestLocation();
				if (pos == null) {
					this.setNestLocation(this.findNest(this, 2));
				} else {
					if (!world.isBlockLoaded(pos)) {
						this.setNestLocation(this.findNest(this, 2));
					}
					else if (world.getBlockState(pos).getBlock() != this.getNestBlock()) {
						this.setNestLocation(this.findNest(this, 2));
					}
				}
			}
		}

		super.onEntityUpdate();
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

		if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 11 && this.getAttackTarget() != null) {
			launchAttack();
			if (this.getOneHit()) {
				this.setAttackTarget(null);
				this.setRevengeTarget(null);
			}
		}

		AnimationHandler.INSTANCE.updateAnimations(this);

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
			return LepidodendronMod.DIICTODON_LOOT_YOUNG;
		}
		return LepidodendronMod.DIICTODON_LOOT;
	}

	public static BlockPos buildBurrow(World world, BlockPos pos) {
		IBlockState bs = world.getBlockState(pos);
		int i = world.rand.nextInt(4);
		pos = pos.down();
		if (i == 0) { //North
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.north();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.down();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.north();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
			world.setBlockToAir(pos);

			if (world.rand.nextInt(2) == 0) {
				pos = pos.east();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);

				pos = pos.east();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);
			} else {
				pos = pos.west();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);

				pos = pos.west();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);
			}
		}
		else if (i == 1) { //South
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.south();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.down();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.south();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
			world.setBlockToAir(pos);

			if (world.rand.nextInt(2) == 0) {
				pos = pos.east();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);

				pos = pos.east();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);
			} else {
				pos = pos.west();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);

				pos = pos.west();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);
			}
		}
		else if (i == 2) { //East
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.east();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.down();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.east();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
			world.setBlockToAir(pos);

			if (world.rand.nextInt(2) == 0) {
				pos = pos.north();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);

				pos = pos.north();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);
			}
			else {
				pos = pos.south();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);

				pos = pos.south();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);
			}
		}
		else if (i == 3) { //West
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.west();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.down();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.east(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockToAir(pos);

			pos = pos.west();
			world.setBlockState(pos.down(), newBurrowState(world, pos));
			world.setBlockState(pos.south(), newBurrowState(world, pos));
			world.setBlockState(pos.north(), newBurrowState(world, pos));
			world.setBlockState(pos.west(), newBurrowState(world, pos));
			world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
			world.setBlockToAir(pos);

			if (world.rand.nextInt(2) == 0) {
				pos = pos.north();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);

				pos = pos.north();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.north(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);
			}
			else {
				pos = pos.south();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);

				pos = pos.south();
				world.setBlockState(pos.down(), newBurrowState(world, pos));
				world.setBlockState(pos.south(), newBurrowState(world, pos));
				world.setBlockState(pos.east(), newBurrowState(world, pos));
				world.setBlockState(pos.west(), newBurrowState(world, pos));
				world.setBlockState(pos.up(), newBurrowState(world, pos.up()));
				world.setBlockToAir(pos);
			}
		}

		return pos;
	}

	public static IBlockState newBurrowState(World world, BlockPos pos) {
		IBlockState oldBurrowState = world.getBlockState(pos);
		if (oldBurrowState == Blocks.SAND.getStateFromMeta(0)) {
			return BlockSandSticky.block.getDefaultState();
		}
		if (oldBurrowState == Blocks.SAND.getStateFromMeta(1)) {
			return BlockSandRedSticky.block.getDefaultState();
		}
		if (oldBurrowState == Blocks.GRAVEL) {
			return BlockSandRedSticky.block.getDefaultState();
		}
		if (oldBurrowState == BlockSandPangaean.block.getDefaultState()) {
			return BlockSandPangaeanSticky.block.getDefaultState();
		}
		if (world.isAirBlock(pos) ||
			(oldBurrowState.getMaterial() != Material.ROCK
				&& oldBurrowState.getMaterial() != Material.GROUND
				&& oldBurrowState.getMaterial() != Material.CLAY)
			) {
			return world.getBiome(pos).topBlock;
		}
		return oldBurrowState;
	}

}