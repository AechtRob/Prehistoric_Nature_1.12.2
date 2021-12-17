
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.lepidodendron.LepidodendronMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraGerarus extends EntityPrehistoricFloraArchoblattina {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;
	private int animationTick;
	private Animation animation = NO_ANIMATION;

	public EntityPrehistoricFloraGerarus(World world) {
		super(world);
		setSize(0.18F, 0.145F);
		experienceValue = 0;
		this.isImmuneToFire = false;
		setNoAI(!true);
		enablePersistence();
	}

	@Override
	public int defaultFlyCooldown() {
		return 1500;
	}

	@Override
	public int defaultWanderCooldown() {
		return 1500;
	}

	public static String getPeriod() {return "Carboniferous";}

	public static String getHabitat() {return "Terrestrial";}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		super.collideWithEntity(entityIn);
		if (entityIn instanceof EntityPlayer) {
			entityIn.attackEntityFrom(DamageSource.CACTUS, (float) 2);
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack.isEmpty())
		{
			player.attackEntityFrom(DamageSource.CACTUS, (float) 2);
		}

		return super.processInteract(player, hand);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.getImmediateSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) source.getImmediateSource();
			player.attackEntityFrom(DamageSource.CACTUS, (float) 2);
		}

		return super.attackEntityFrom(source, amount);
	}

	@Override
	public String tagEgg () {
		return "insect_eggs_gerarus";
	}

	@Nullable
	protected ResourceLocation getLootTable() { return LepidodendronMod.GERARUS_LOOT;}

}

