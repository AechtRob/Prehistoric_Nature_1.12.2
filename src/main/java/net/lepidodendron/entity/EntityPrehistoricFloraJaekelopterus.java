
package net.lepidodendron.entity;

import com.google.common.base.Predicate;
import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.AttackAI;
import net.lepidodendron.entity.ai.EatFishItemsAI;
import net.lepidodendron.entity.ai.EurypteridWanderBottomDweller;
import net.lepidodendron.entity.ai.HuntAI;
import net.lepidodendron.entity.base.EntityPrehistoricFloraEurypteridBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraFishBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraTrilobiteBottomBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraTrilobiteSwimBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraJaekelopterus extends EntityPrehistoricFloraEurypteridBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;

	public EntityPrehistoricFloraJaekelopterus(World world) {
		super(world);
		setSize(0.8F, 0.6F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
		minSize = 0.2F;
		maxSize = 1.0F;
		maxHealthAgeable = 18D;
	}

	@Override
	public int getAdultAge() {
		return 24000;
	}

	@Override
	public int getAttackLength() {
		return 50;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new AttackAI(this, 1.0D, false));
		tasks.addTask(1, new EurypteridWanderBottomDweller(this, NO_ANIMATION));
		tasks.addTask(2, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EatFishItemsAI(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new HuntAI(this, EntityPlayer.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
		this.targetTasks.addTask(2, new HuntAI(this, EntityPrehistoricFloraFishBase.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
		this.targetTasks.addTask(2, new HuntAI(this, EntitySquid. class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
		this.targetTasks.addTask(2, new HuntAI(this, EntityPrehistoricFloraTrilobiteBottomBase.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
		this.targetTasks.addTask(2, new HuntAI(this, EntityPrehistoricFloraTrilobiteSwimBase.class, true, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase));
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
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	protected double getSwimSpeed() {
		return this.getSwimSpeed();
	}

	@Override
	protected float getAISpeedEurypterid() {
		float AIspeed = (float) Math.min(1F, (this.getAgeScale() * 2F)) * 0.24F;
		if (this.isHunting()) {
			AIspeed = AIspeed * 1.8F;
		}
		return AIspeed;
	}

	@Override
	public boolean isInWater() {
		return super.isInWater() || this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		boolean isAtBottom = false;
		if (this.getPosition().getY() - 1 > 1) {
			BlockPos pos = new BlockPos(this.getPosition().getX(),this.getPosition().getY() - 1, this.getPosition().getZ());
			isAtBottom =  ((this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL))
					&& ((this.world.getBlockState(pos)).getMaterial() != Material.WATER));
		}
		//if (isAtBottom && this.world.getBlockState(this.getPosition().up()).getMaterial() == Material.WATER) {
		//	this.setPositionAndUpdate(this.getPosition().up().getX(), this.getPosition().up().getY(), this.getPosition().up().getZ());
		//}

		return super.attackEntityFrom(source, (amount * 0.7F));

	}

	//@Override
	//public net.minecraft.util.SoundEvent getAmbientSound() {
	//    return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
	//            .getObject(new ResourceLocation("lepidodendron:eurypterus_idle"));
	//}

	@Override
	public SoundEvent getAmbientSound() {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
	}


	//@Override
	//public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
	//    return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
	//            .getObject(new ResourceLocation("lepidodendron:eurypterus_hurt"));
	//}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.hurt"));
	}

	//@Override
	//public net.minecraft.util.SoundEvent getDeathSound() {
	//    return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
	//            .getObject(new ResourceLocation("lepidodendron:eurypterus_death"));
	//}
	@Override
	public SoundEvent getDeathSound() {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.death"));
	}

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	@Override
	protected int getExperiencePoints(EntityPlayer player) {
		return 1 + this.world.rand.nextInt(3);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		//System.err.println(this.getAnimationTick());
		if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 25 && this.getAttackTarget() != null) {
			launchAttack();
			if (this.world instanceof WorldServer) {
				for (int k = 0; k < 5; ++k) {
					((WorldServer) this.world).spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), (int) 1, 1, 1, 1, 0.2, new int[0]);
				}
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (this.getAnimation() == NO_ANIMATION) {
			this.setAnimation(ATTACK_ANIMATION);
			//System.err.println("set attack");
		}
		return false;
	}

	public void onEntityUpdate()
	{
		super.onEntityUpdate();
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		double adult = (double) LepidodendronConfig.adultAge;
		if (adult > 100) {adult = 100;}
		if (adult < 0) {adult = 0;}
		adult = adult/100D;
		if (getAgeScale() < adult) {
			return LepidodendronMod.JAEKELOPTERUS_LOOT_YOUNG;
		}
		return LepidodendronMod.JAEKELOPTERUS_LOOT;
	}

}