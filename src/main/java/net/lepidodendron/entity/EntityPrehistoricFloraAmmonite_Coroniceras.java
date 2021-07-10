
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.NautiloidWander;
import net.lepidodendron.entity.base.EntityPrehistoricFloraNautiloidBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;


public class EntityPrehistoricFloraAmmonite_Coroniceras extends EntityPrehistoricFloraNautiloidBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;

	public EntityPrehistoricFloraAmmonite_Coroniceras(World world) {
		super(world);
		setSize(0.5F, 0.3F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
		//minSize = 0.5F;
		//maxSize = 1.0F;
		minWidth = 0.1F;
		maxWidth = 0.5F;
		maxHeight = 0.3F;
		maxHealthAgeable = 4;
	}

	@Override
	public int getAdultAge() {
		return 36000;
	}

	@Override
	protected float getAISpeedNautiloid() {
		return 0.2f;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new NautiloidWander(this, NO_ANIMATION));
		tasks.addTask(1, new EntityAILookIdle(this));
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

	@Nullable
	protected ResourceLocation getLootTable() {
		double adult = (double) LepidodendronConfig.adultAge;
		if (adult > 100) {adult = 100;}
		if (adult < 0) {adult = 0;}
		adult = adult/100D;
		if (getAgeScale() < adult) {
			return LepidodendronMod.CORONICERAS_LOOT_YOUNG;
		}
		return LepidodendronMod.CORONICERAS_LOOT;
	}

}

