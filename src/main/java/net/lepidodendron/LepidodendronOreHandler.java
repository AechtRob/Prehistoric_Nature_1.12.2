package net.lepidodendron;

import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;


public class LepidodendronOreHandler {
	
	@SubscribeEvent(receiveCanceled=true)
	public void onEvent(GenerateMinable event){
		//System.err.println("Coal gen");
		if(event.getType().equals(EventType.COAL )){
			if (LepidodendronConfig.dimDevonian == (int) event.getWorld().provider.getDimension() 
			|| LepidodendronConfig.dimOrdovicianSilurian == (int) event.getWorld().provider.getDimension()
			)
            	event.setResult(Event.Result.DENY);
		}
	}

}