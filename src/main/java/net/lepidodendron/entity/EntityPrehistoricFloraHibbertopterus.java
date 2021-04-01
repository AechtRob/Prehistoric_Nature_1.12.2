
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.entity.ai.HibbertopterusWander;
import net.lepidodendron.entity.ai.TrilobiteWanderBottom;
import net.lepidodendron.entity.base.EntityPrehistoricFloraHibbertopterusBase;
import net.lepidodendron.entity.model.ModelHibbertopterus;
import net.lepidodendron.item.entities.ItemHibbertopterusRaw;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

	public class EntityPrehistoricFloraHibbertopterus extends EntityPrehistoricFloraHibbertopterusBase {

		public BlockPos currentTarget;
		@SideOnly(Side.CLIENT)
		public ChainBuffer chainBuffer;
		private int animationTick;
		private Animation animation = NO_ANIMATION;

		public EntityPrehistoricFloraHibbertopterus(World world) {
			super(world);
			setSize(0.3F, 0.3F);
			experienceValue = 0;
			this.isImmuneToFire = false;
			setNoAI(!true);
			enablePersistence();
		}

		@Override
		public int getAnimationTick() {
			return getAnimationTick();
		}

		@Override
		protected float getAISpeedHibbertopterus() {
			return 0.15f;
		}

		@Override
		public void setAnimationTick(int tick) {
			animationTick = tick;
		}

		@Override
		public Animation getAnimation() {
			return null;
		}

		@Override
		public void setAnimation(Animation animation) {
			this.animation = animation;
		}

		@Override
		public Animation[] getAnimations() {
			return null;
		}

		protected void initEntityAI() {
			tasks.addTask(0, new HibbertopterusWander(this, ANIMATION_WANDER));
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
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
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

		@Override
		public boolean isNearWater(BlockPos pos) {
			int distH = (int) LepidodendronConfig.waterHibbertopterus;
			if (distH < 1) distH = 1;
			if (distH > 16) distH = 16;
			int distV = 8;
			if (distV < 1) distV = 1;
			if (distV > 6) distV = 6;
			boolean waterCriteria = false;
			int xct = -distH;
			int yct;
			int zct;
			while ((xct <= distH) && (!waterCriteria)) {
				yct = -distV;
				while ((yct <= 1) && (!waterCriteria)) {
					zct = -distH;
					while ((zct <= distH) && (!waterCriteria)) {
						if ((Math.pow((int) Math.abs(xct),2) + Math.pow((int) Math.abs(zct),2) <= Math.pow((int) distH,2)) && ((this.world.getBlockState(new BlockPos(pos.getX() + xct, pos.getY() + yct, pos.getZ() + zct))).getMaterial() == Material.WATER)) {
							waterCriteria = true;
						}
						zct = zct + 1;
					}
					yct = yct + 1;
				}
				xct = xct + 1;
			}

			if (waterCriteria) return true;

			return super.isInWater() || this.isInsideOfMaterial(Material.WATER) || this.isInsideOfMaterial(Material.CORAL);
		}

		public void onEntityUpdate()
		{
			int i = this.getAir();
			super.onEntityUpdate();

			if ((this.isEntityAlive() && !isInWater())
					&& (!isNearWater(this.getPosition())) //Is not NEAR water
			)
			{
				--i;
				this.setAir(i);

				if (this.getAir() == -20)
				{
					this.setAir(0);
					this.attackEntityFrom(DamageSource.DROWN, 1.0F);
				}
			}
			else
			{
				this.setAir(300);
			}
		}

		@Override
		protected Item getDropItem() {
			//return null;
			return new ItemStack(ItemHibbertopterusRaw.block, (int) (1)).getItem();
		}

	}

