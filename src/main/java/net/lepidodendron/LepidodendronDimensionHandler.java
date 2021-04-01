package net.lepidodendron;

import net.minecraft.world.DimensionType;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class LepidodendronDimensionHandler {

private int tickIndex = 0;

@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent tick) {
        if (tick.phase == TickEvent.Phase.END && (++tickIndex == 600)) {
            tickIndex = 0;
            for (int dimension : DimensionManager.getIDs()) {

				if (
					(dimension == LepidodendronConfig.dimOrdovicianSilurian)
					|| (dimension == LepidodendronConfig.dimDevonian)
					|| (dimension == LepidodendronConfig.dimCarboniferous)
					) {
				
	                WorldServer worldServer = DimensionManager.getWorld(dimension);
	                ChunkProviderServer provider = worldServer.getChunkProvider();
	                DimensionType providerType = DimensionManager.getProviderType(dimension);
	
	                String dimensionName = "";
	
	                if (providerType != null) {
	                    dimensionName = providerType.getName();
	                }
	
                   // if (!worldServer.provider.getDimensionType().shouldLoadSpawn()
                    //        && ForgeChunkManager.getPersistentChunksFor(worldServer).isEmpty()
                     //       && provider.getLoadedChunkCount() == 0
                      //      && worldServer.playerEntities.isEmpty()
                       //     && worldServer.loadedEntityList.isEmpty()
                        //    && worldServer.loadedTileEntityList.isEmpty()) {
                    if (ForgeChunkManager.getPersistentChunksFor(worldServer).isEmpty()
                            && provider.getLoadedChunkCount() == 0
                            && worldServer.playerEntities.isEmpty()
                            && worldServer.loadedEntityList.isEmpty()
                            && worldServer.loadedTileEntityList.isEmpty()) {
                        try {
                            worldServer.saveAllChunks(true, null);
                        } catch (MinecraftException e) {
                            System.err.println("Caught an exception while saving all chunks in dim: " + dimensionName);
                        } finally {
                            MinecraftForge.EVENT_BUS.post(new WorldEvent.Unload(worldServer));
                            worldServer.flush();
                            DimensionManager.setWorld(dimension, null, worldServer.getMinecraftServer());
                        }
                    }
				}
            }
        }
	}
}
