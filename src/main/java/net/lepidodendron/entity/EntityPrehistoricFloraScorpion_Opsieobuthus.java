
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.AttackAIClimbingBase;
import net.lepidodendron.entity.ai.AvoidWaterWanderAI;
import net.lepidodendron.entity.ai.EatMeatItemsAIClimbingBase;
import net.lepidodendron.entity.ai.EntityHurtByTargetSmallerThanMeAI;
import net.lepidodendron.entity.base.EntityPrehistoricFloraScorpion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraScorpion_Opsieobuthus extends EntityPrehistoricFloraScorpion {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;

	public EntityPrehistoricFloraScorpion_Opsieobuthus(World world) {
		super(world);
	}

	public static String getPeriod() {return "late Carboniferous - early Permian";}

	public static String getHabitat() {return "Terrestrial";}

	@Override
	public int getAdultAge() {
		return 12000;
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		if (this.getBabies() && (!this.getIsBaby())) {
			return LepidodendronMod.OPSIEOBUTHUS_LOOT;
		}
		return null;
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAILeapAtTarget(this, 0.16F));
		tasks.addTask(1, new AttackAIClimbingBase(this, 1.0D, false, this.getAttackLength()));
		tasks.addTask(2, new EntityAISwimming(this));
		tasks.addTask(3, new AvoidWaterWanderAI(this, 0.8D));
		tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EatMeatItemsAIClimbingBase(this));
		this.targetTasks.addTask(1, new EntityHurtByTargetSmallerThanMeAI(this, false));
		}

	@Override
	public void onDeath(DamageSource cause) {
		if (!world.isRemote && this.getBabies() && (!this.getIsBaby())) {
			int ii = rand.nextInt(5);
			for (int i = 0; i <= ii; i++) {
				//Spawn babies:
				Entity entity = null;
				entity = ItemMonsterPlacer.spawnCreature(this.world, EntityList.getKey(EntityPrehistoricFloraScorpion_Opsieobuthus.class), (double)this.getPosition().getX() + 0.5D, (double)this.getPosition().getY() + 0.5D, (double)this.getPosition().getZ() + 0.5D);
				EntityPrehistoricFloraScorpion_Opsieobuthus baby = (EntityPrehistoricFloraScorpion_Opsieobuthus) entity;
				baby.setAgeTicks(1);
				baby.setIsBaby(true);
				if (Math.random() >= 0.8) {
					baby.setBabies(true);
				}
				Entity target = cause.getTrueSource();
				if (target instanceof EntityLivingBase) {
					if (target instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) target;
						if (!player.capabilities.isCreativeMode) {
							baby.setAttackTarget((EntityLivingBase) target);
						}
					}
					else {
						baby.setAttackTarget((EntityLivingBase) target);
					}
				}
			}
		}
		super.onDeath(cause);
	}

}