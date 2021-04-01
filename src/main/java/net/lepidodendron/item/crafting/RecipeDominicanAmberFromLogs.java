
package net.lepidodendron.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.lepidodendron.item.ItemDominicanAmberChunk;
import net.lepidodendron.block.BlockHymenaeaLog;
import net.lepidodendron.ElementsLepidodendronMod;

@ElementsLepidodendronMod.ModElement.Tag
public class RecipeDominicanAmberFromLogs extends ElementsLepidodendronMod.ModElement {
	public RecipeDominicanAmberFromLogs(ElementsLepidodendronMod instance) {
		super(instance, 839);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockHymenaeaLog.block, (int) (1)), new ItemStack(ItemDominicanAmberChunk.block, (int) (1)), 1F);
	}
}
