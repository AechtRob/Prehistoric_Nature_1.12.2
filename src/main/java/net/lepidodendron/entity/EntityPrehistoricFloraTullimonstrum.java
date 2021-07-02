
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.TullymonsterWander;
import net.lepidodendron.entity.base.EntityPrehistoricFloraFishBase;
import net.lepidodendron.item.entities.ItemBucketTullimonstrum;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraTullimonstrum extends EntityPrehistoricFloraFishBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;
	private int animationTick;
	public Animation FEED_ANIMATION;
	public Animation currentAnimation;
	private int feedTicks;
	private boolean isFeeding;

	public EntityPrehistoricFloraTullimonstrum(World world) {
		super(world);
		setSize(0.6F, 0.3F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
		FEED_ANIMATION = Animation.create(120);
	}

	@Override
	protected float getAISpeedFish() {
		if (this.getAnimation() == FEED_ANIMATION) {return 0.00f;}
		return 0.078f;
	}

	@Override
	protected boolean isBase() {
		return false;
	}

	@Override
	public int getAnimationTick() {
		return animationTick;
	}

	@Override
	public void setAnimationTick(int tick) {
		animationTick = tick;
	}

	@Override
	public Animation getAnimation() {
		return currentAnimation == null ? NO_ANIMATION : currentAnimation;
	}

	@Override
	public void setAnimation(Animation animation)
	{
		currentAnimation = animation;
	}

	@Override
	public Animation[] getAnimations() {
		return new Animation[]{FEED_ANIMATION};
	}

	protected void initEntityAI() {
		tasks.addTask(0, new TullymonsterWander(this, NO_ANIMATION));
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		this.feedTicks = 0;
		this.isFeeding = false;
		return super.attackEntityFrom(source, amount);
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

	public boolean isInFeedingPosition() {
		return
		super.isInWater()
		&& (this.world.getBlockState(this.getPosition().down()).getMaterial() != Material.WATER);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.renderYawOffset = this.rotationYaw;

		if (this.getAnimation() != FEED_ANIMATION) {
			setFeeding(false);
		}
		if (!this.world.isRemote) {
			if (!(this.isInWater())) {
				setFeeding(false);
				this.setAnimation(NO_ANIMATION);
			}
			else {
				if ((!getIsFeeding()) && (isHungry()) && isInFeedingPosition()) {
					if (this.getAnimation() == NO_ANIMATION) {
						setFeeding(true);
						this.feedTicks = 0;
						this.setAnimation(FEED_ANIMATION);
						//System.err.println("Set Anim");
					}
				}
			}
		}
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (!this.world.isRemote) {
			if ((!(feedTicks > 0)) || feedTicks > 3000) {
				feedTicks = 0;
			}
			feedTicks = feedTicks + rand.nextInt(3);

			//System.err.println(feedTicks);
			//System.err.println("Vector pos: " + this.getPositionVector().y);
			//System.err.println("Block pos: " + this.getPosition().getY());
			//System.err.println("Hungry: " + this.isHungry());
			//System.err.println("Feeding pos: " + this.isInFeedingPosition());
		}

		AnimationHandler.INSTANCE.updateAnimations(this);

	}

	public boolean isHungry() {
		return (this.feedTicks > 2000);
	}

	public boolean getIsFeeding() {
		return this.isFeeding;
	}

	public void setFeeding(boolean bool) {
		this.isFeeding = bool;
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		return LepidodendronMod.TULLIMONSTRUM_LOOT;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);

		if (!itemstack.isEmpty())
		{
			if (itemstack.getItem() == Items.WATER_BUCKET)
			{
				player.inventory.clearMatchingItems(new ItemStack(Items.WATER_BUCKET, (int) (1)).getItem(), -1, (int) 1, null);
				SoundEvent soundevent = SoundEvents.ITEM_BUCKET_FILL;
				player.getEntityWorld().playSound(player, player.getPosition(), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
				ItemStack itemstack1 = new ItemStack(ItemBucketTullimonstrum.block, (int) (1));
				itemstack1.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(player, itemstack1);
				this.setDead();
				return true;
			}
		}

		return super.processInteract(player, hand);
	}
}

