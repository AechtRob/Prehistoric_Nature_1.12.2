
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.HibbertopterusWander;
import net.lepidodendron.entity.ai.TrilobiteWanderBottom;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAmphibianBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraHibbertopterusBase;
import net.lepidodendron.entity.model.ModelHibbertopterus;
import net.lepidodendron.entity.util.PathNavigateAmphibian;
import net.lepidodendron.entity.util.PathNavigateAmphibianFindWater;
import net.lepidodendron.item.entities.ItemHibbertopterusRaw;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraHibbertopterus extends EntityPrehistoricFloraHibbertopterusBase {

		public BlockPos currentTarget;
		@SideOnly(Side.CLIENT)
		public ChainBuffer chainBuffer;

		public EntityPrehistoricFloraHibbertopterus(World world) {
			super(world);
			setSize(0.3F, 0.3F);
			experienceValue = 0;
			this.isImmuneToFire = false;
			setNoAI(!true);
			enablePersistence();
			if (this.isInWater()) {
				this.moveHelper = new EntityPrehistoricFloraAmphibianBase.WanderMoveHelper();
				this.navigator = new PathNavigateAmphibian(this, world);
			}
			else {
				if (this.isNearWater(this, this.getPosition())) {
					this.moveHelper = new EntityPrehistoricFloraAmphibianBase.WanderMoveHelper();
					this.navigator = new PathNavigateAmphibian(this, world);
				}
				else {//Find water!
					this.moveHelper = new EntityPrehistoricFloraAmphibianBase.WanderMoveHelper();
					this.navigator = new PathNavigateAmphibianFindWater(this, world);
					this.setPathPriority(PathNodeType.WATER, 10F);
				}
			}
		}

		@Override
		public int WaterDist() {return 2;}

		@Override
		protected float getAISpeedHibbertopterus() {
			return 0.15f;
		}

		@Override
		protected void initEntityAI() {
			tasks.addTask(0, new HibbertopterusWander(this, NO_ANIMATION));
			tasks.addTask(1, new EntityAILookIdle(this));
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
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
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
		public void onLivingUpdate() {
			super.onLivingUpdate();
			this.renderYawOffset = this.rotationYaw;
		}

		public void onEntityUpdate()
		{
			int i = this.getAir();
			super.onEntityUpdate();

			if ((this.isEntityAlive() && !isInWater())
					&& (!isNearWater(this, this.getPosition())) //Is not NEAR water
			)
			{
				--i;
				this.setAir(i);

				if (this.getAir() == -20)
				{
					this.setAir(200);
					this.attackEntityFrom(DamageSource.DROWN, 0.25F);
				}
			}
			else
			{
				this.setAir(1000);
			}
		}

	@Nullable
	protected ResourceLocation getLootTable() {
		return LepidodendronMod.HIBBERTOPTERUS_LOOT;
	}

	}

