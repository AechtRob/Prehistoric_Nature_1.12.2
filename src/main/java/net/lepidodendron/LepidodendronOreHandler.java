package net.lepidodendron;

import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class LepidodendronOreHandler {
	
	@SubscribeEvent(receiveCanceled=true)
	public void onEvent(GenerateMinable event){
		//System.err.println("Coal gen");
		if (event.getType().equals(EventType.COAL )){
			if (LepidodendronConfig.dimDevonian == (int) event.getWorld().provider.getDimension() 
			|| LepidodendronConfig.dimOrdovicianSilurian == (int) event.getWorld().provider.getDimension()
			|| LepidodendronConfig.dimCambrian == (int) event.getWorld().provider.getDimension()
			|| LepidodendronConfig.dimPrecambrian == (int) event.getWorld().provider.getDimension()
			)
            	event.setResult(Event.Result.DENY);
		}
		if (event.getType().equals(EventType.IRON)){
			if (LepidodendronConfig.dimCambrian == (int) event.getWorld().provider.getDimension()
				|| LepidodendronConfig.dimPrecambrian == (int) event.getWorld().provider.getDimension()
			)
				event.setResult(Event.Result.DENY);
		}
		if (event.getType().equals(EventType.DIRT )){
			if (LepidodendronConfig.dimOrdovicianSilurian == (int) event.getWorld().provider.getDimension()
				|| LepidodendronConfig.dimCambrian == (int) event.getWorld().provider.getDimension()
				|| LepidodendronConfig.dimPrecambrian == (int) event.getWorld().provider.getDimension()
			)
				event.setResult(Event.Result.DENY);
		}
		if (event.getType().equals(EventType.ANDESITE )){
			if (LepidodendronConfig.dimPrecambrian == (int) event.getWorld().provider.getDimension()
			)
				event.setResult(Event.Result.DENY);
		}
		if (event.getType().equals(EventType.GRANITE )){
			if (LepidodendronConfig.dimPrecambrian == (int) event.getWorld().provider.getDimension()
			)
				event.setResult(Event.Result.DENY);
		}
		if (event.getType().equals(EventType.DIORITE )){
			if (LepidodendronConfig.dimPrecambrian == (int) event.getWorld().provider.getDimension()
			)
				event.setResult(Event.Result.DENY);
		}
		if (event.getType().equals(EventType.SILVERFISH )){
			if (LepidodendronConfig.dimDevonian == (int) event.getWorld().provider.getDimension()
				|| LepidodendronConfig.dimOrdovicianSilurian == (int) event.getWorld().provider.getDimension()
				|| LepidodendronConfig.dimCambrian == (int) event.getWorld().provider.getDimension()
				|| LepidodendronConfig.dimPrecambrian == (int) event.getWorld().provider.getDimension()
				|| LepidodendronConfig.dimCarboniferous == (int) event.getWorld().provider.getDimension()
			)
				event.setResult(Event.Result.DENY);
		}
	}

}