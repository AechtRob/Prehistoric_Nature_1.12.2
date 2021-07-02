
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.NautiloidWanderBottomFeed;
import net.lepidodendron.entity.base.EntityPrehistoricFloraNautiloidBase;
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

public class EntityPrehistoricFloraCameroceras extends EntityPrehistoricFloraNautiloidBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;

	public EntityPrehistoricFloraCameroceras(World world) {
		super(world);
		setSize(1.0F, 0.99F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
		minSize = 0.1F;
		maxSize = 1.0F;
		maxHealthAgeable = 32;
	}

	@Override
	public int getAdultAge() {
		return 72000;
	}

	@Override
	protected float getAISpeedNautiloid() {
		return 0.1f;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new NautiloidWanderBottomFeed(this, NO_ANIMATION));
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
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.renderYawOffset = this.rotationYaw;
		if (this.isEntityAlive() && isInWater()) {
			if (this.isAtBottom()) {
				//Feeding from bottom pose:
				BlockPos posIn = this.getPosition();
				World worldIn = this.world;
				//worldIn.playSound(player, posIn, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
				if (worldIn instanceof WorldServer) {
					if (Math.random() > 0.3) {
						for (int k = 0; k < 8; ++k) {
							((WorldServer) worldIn).spawnParticle(EnumParticleTypes.WATER_BUBBLE, posIn.getX(), posIn.getY(), posIn.getZ(), (int) 1, 1, 1, 1, 0.2, new int[0]);
						}
					}
					if (Math.random() > 0.4) {
						for (int k = 0; k < 4; ++k) {
							((WorldServer) worldIn).spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posIn.getX(), posIn.getY(), posIn.getZ(), (int) 1, 1, 1, 1, 0.1, new int[0]);
						}
					}
				}
			}
		}
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		double adult = (double) LepidodendronConfig.adultAge;
		if (adult > 100) {adult = 100;}
		if (adult < 0) {adult = 0;}
		adult = adult/100D;
		if (getAgeScale() < adult) {
			return LepidodendronMod.CAMEROCERAS_LOOT_YOUNG;
		}
		return LepidodendronMod.CAMEROCERAS_LOOT;
	}

}
