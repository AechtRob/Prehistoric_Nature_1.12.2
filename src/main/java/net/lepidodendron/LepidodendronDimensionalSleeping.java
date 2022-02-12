package net.lepidodendron;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class LepidodendronDimensionalSleeping {

	@SubscribeEvent
	public void onWorldTick(TickEvent.ServerTickEvent event) throws IllegalAccessException {
		if (event.phase == TickEvent.Phase.START) {
			boolean sleepPrecambrian = false;
			boolean worldPrecambrian = false;
			WorldServer Precambrian = DimensionManager.getWorld(LepidodendronConfig.dimPrecambrian);
			if (Precambrian != null) {
				if (Precambrian.getChunkProvider().getLoadedChunkCount() != 0) {
					worldPrecambrian = true;
					List<EntityPlayer> playersInWorld = Precambrian.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);
					if (playersInWorld.isEmpty()) {
						worldPrecambrian = false;
					}
					else if (areAllPlayersAsleep(Precambrian)) {
						sleepPrecambrian = true;
					}
				}
			}
			boolean sleepCambrian = false;
			boolean worldCambrian = false;
			WorldServer Cambrian = DimensionManager.getWorld(LepidodendronConfig.dimCambrian);
			if (Cambrian != null) {
				if (Cambrian.getChunkProvider().getLoadedChunkCount() != 0) {
					worldCambrian = true;
					List<EntityPlayer> playersInWorld = Cambrian.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);
					if (playersInWorld.isEmpty()) {
						worldCambrian = false;
					}
					else if (areAllPlayersAsleep(Cambrian)) {
						sleepCambrian = true;
					}
				}
			}
			boolean sleepOrdovicianSilurian = false;
			boolean worldOrdovicianSilurian = false;
			WorldServer OrdovicianSilurian = DimensionManager.getWorld(LepidodendronConfig.dimOrdovicianSilurian);
			if (OrdovicianSilurian != null) {
				if (OrdovicianSilurian.getChunkProvider().getLoadedChunkCount() != 0) {
					worldOrdovicianSilurian = true;
					List<EntityPlayer> playersInWorld = OrdovicianSilurian.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);
					if (playersInWorld.isEmpty()) {
						worldOrdovicianSilurian = false;
					}
					else if (areAllPlayersAsleep(OrdovicianSilurian)) {
						sleepOrdovicianSilurian = true;
					}
				}
			}
			boolean sleepDevonian = false;
			boolean worldDevonian = false;
			WorldServer Devonian = DimensionManager.getWorld(LepidodendronConfig.dimDevonian);
			if (Devonian != null) {
				if (Devonian.getChunkProvider().getLoadedChunkCount() != 0) {
					worldDevonian = true;
					List<EntityPlayer> playersInWorld = Devonian.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);
					if (playersInWorld.isEmpty()) {
						worldDevonian = false;
					}
					else if (areAllPlayersAsleep(Devonian)) {
						sleepDevonian = true;
					}
				}
			}
			boolean sleepCarboniferous = false;
			boolean worldCarboniferous = false;
			WorldServer Carboniferous = DimensionManager.getWorld(LepidodendronConfig.dimCarboniferous);
			if (Carboniferous != null) {
				if (Carboniferous.getChunkProvider().getLoadedChunkCount() != 0) {
					worldCarboniferous = true;
					List<EntityPlayer> playersInWorld = Carboniferous.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);
					if (playersInWorld.isEmpty()) {
						worldCarboniferous = false;
					}
					else if (areAllPlayersAsleep(Carboniferous)) {
						sleepCarboniferous = true;
					}
				}
			}
			boolean sleepPermian = false;
			boolean worldPermian = false;
			WorldServer Permian = DimensionManager.getWorld(LepidodendronConfig.dimPermian);
			if (Permian != null) {
				if (Permian.getChunkProvider().getLoadedChunkCount() != 0) {
					worldPermian = true;
					List<EntityPlayer> playersInWorld = Permian.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);
					if (playersInWorld.isEmpty()) {
						worldPermian = false;
					}
					else if (areAllPlayersAsleep(Permian)) {
						sleepPermian = true;
					}
				}
			}
			boolean sleepTriassic = false;
			boolean worldTriassic = false;
			WorldServer Triassic = DimensionManager.getWorld(LepidodendronConfig.dimTriassic);
			if (Triassic != null) {
				if (Triassic.getChunkProvider().getLoadedChunkCount() != 0) {
					worldTriassic = true;
					List<EntityPlayer> playersInWorld = Triassic.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);
					if (playersInWorld.isEmpty()) {
						worldTriassic = false;
					}
					else if (areAllPlayersAsleep(Triassic)) {
						sleepTriassic = true;
					}
				}
			}
			boolean sleepJurassic = false;
			boolean worldJurassic = false;
			WorldServer Jurassic = DimensionManager.getWorld(LepidodendronConfig.dimJurassic);
			if (Jurassic != null) {
				if (Jurassic.getChunkProvider().getLoadedChunkCount() != 0) {
					worldJurassic = true;
					List<EntityPlayer> playersInWorld = Jurassic.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);
					if (playersInWorld.isEmpty()) {
						worldJurassic = false;
					}
					else if (areAllPlayersAsleep(Jurassic)) {
						sleepJurassic = true;
					}
				}
			}
			boolean sleepOverworld = false;
			boolean worldOverworld = false;
			WorldServer Overworld = DimensionManager.getWorld(0);
			if (Overworld != null) {
				if (Overworld.getChunkProvider().getLoadedChunkCount() != 0) {
					worldOverworld = true;
					List<EntityPlayer> playersInWorld = Overworld.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);
					if (playersInWorld.isEmpty()) {
						worldOverworld = false;
					}
					else if (areAllPlayersAsleep(Overworld)) {
						sleepOverworld = true;
						worldOverworld = true;
					}
				}
			}

			boolean testPrecambrian = true;
			boolean testCambrian = true;
			boolean testOrdovicianSilurian = true;
			boolean testDevonian = true;
			boolean testCarboniferous = true;
			boolean testPermian = true;
			boolean testTriassic = true;
			boolean testJurassic = true;
			boolean testOverworld = true;

			if (worldPrecambrian && !sleepPrecambrian) {
				testPrecambrian = false;
			}
			if (worldCambrian && !sleepCambrian) {
				testCambrian = false;
			}
			if (worldOrdovicianSilurian && !sleepOrdovicianSilurian) {
				testOrdovicianSilurian = false;
			}
			if (worldDevonian && !sleepDevonian) {
				testDevonian = false;
			}
			if (worldCarboniferous && !sleepCarboniferous) {
				testCarboniferous = false;
			}
			if (worldPermian && !sleepPermian) {
				testPermian = false;
			}
			if (worldTriassic && !sleepTriassic) {
				testTriassic = false;
			}
			if (worldJurassic && !sleepJurassic) {
				testJurassic = false;
			}
			if (worldOverworld && !sleepOverworld) {
				testOverworld = false;
			}

			//if (worldPrecambrian || worldCambrian || worldOrdovicianSilurian
			//	|| worldDevonian || worldCarboniferous || worldPermian
			//	|| worldTriassic || worldJurassic || worldOverworld) {
			//	System.err.println("testPrecambrian: " + testPrecambrian);
			//	System.err.println("testCambrian: " + testCambrian);
			//	System.err.println("testOrdovicianSilurian: " + testOrdovicianSilurian);
			//	System.err.println("testDevonian: " + testDevonian);
			//	System.err.println("testCarboniferous: " + testCarboniferous);
			//	System.err.println("testPermian: " + testPermian);
			//	System.err.println("testTriassic: " + testTriassic);
			//	System.err.println("testJurassic: " + testJurassic);
			//	System.err.println("testOverworld: " + testOverworld);
			//}

			if (testPrecambrian
				&& testCambrian
				&& testOrdovicianSilurian
				&& testDevonian
				&& testCarboniferous
				&& testPermian
				&& testTriassic
				&& testJurassic
				&& testOverworld
			) {
				if (Overworld.getGameRules().getBoolean("doDaylightCycle"))
				{
					long i = Overworld.getWorldTime() + 24000L;
					Overworld.setWorldTime(i - i % 24000L);
				}
				if (Overworld.getGameRules().getBoolean("doWeatherCycle"))
				{
					Overworld.provider.resetRainAndThunder();
				}
				wakeAllPlayers(Precambrian);
				wakeAllPlayers(Cambrian);
				wakeAllPlayers(OrdovicianSilurian);
				wakeAllPlayers(Devonian);
				wakeAllPlayers(Carboniferous);
				wakeAllPlayers(Permian);
				wakeAllPlayers(Triassic);
				wakeAllPlayers(Jurassic);
				wakeAllPlayers((WorldServer) Overworld);
			}
		}
	}

	public boolean allPlayersSleeping(WorldServer world)
	{
		if (world == null) {
			return false;
		}

		//System.err.println("allPlayersSleeping");

		List<EntityPlayer> playersInWorld = world.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);

		if (!playersInWorld.isEmpty())
		{
			int i = 0;
			int j = 0;

			for (EntityPlayer entityplayer : playersInWorld)
			{
				if (entityplayer.isSpectator())
				{
					++i;
				}
				else if (entityplayer.isPlayerSleeping())
				{
					++j;
				}
			}
			//System.err.println("allPlayersSleeping result " + (j > 0 && j >= playersInWorld.size() - i));
			return j > 0 && j >= playersInWorld.size() - i;
		}
		//System.err.println("allPlayersSleeping result false default");
		return false;
	}

	public boolean areAllPlayersAsleep(WorldServer world) throws IllegalAccessException {

		//System.err.println("areAllPlayersAsleep");

		if (allPlayersSleeping(world) && !world.isRemote)
		{
			List<EntityPlayer> playersInWorld = world.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);
			for (EntityPlayer entityplayer : playersInWorld)
			{
				if (!entityplayer.isSpectator() && !isPlayerFullyAsleep(entityplayer))
				{
					return false;
				}
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isPlayerFullyAsleep(EntityPlayer player) throws IllegalAccessException {
		Field f1 = ObfuscationReflectionHelper.findField(EntityPlayer.class, "field_71076_b");
		//f1.setAccessible(true);
		if (player.dimension == 0 && f1.getInt(player) >= 96) {
			f1.set(player, 96);
		}
		return player.isPlayerSleeping() && f1.getInt(player) >= 95;
	}

	protected void wakeAllPlayers(WorldServer world)
	{
		if (world == null) {
			return;
		}

		List<EntityPlayer> playersInWorld = world.getPlayers(EntityPlayerMP.class, (Predicate<Entity>) entity -> entity instanceof EntityLivingBase);

		for (EntityPlayer entityplayer : playersInWorld.stream().filter(EntityPlayer::isPlayerSleeping).collect(Collectors.toList()))
		{
			entityplayer.wakeUpPlayer(false, false, true);
		}
	}

}
