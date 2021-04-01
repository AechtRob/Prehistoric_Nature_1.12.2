
package net.lepidodendron.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.lepidodendron.block.BlockAraucarioxylonLogPetrified;
import net.lepidodendron.block.BlockAraucarioxylonLog;
import net.lepidodendron.ElementsLepidodendronMod;

@ElementsLepidodendronMod.ModElement.Tag
public class RecipePetrifiedWoodFromLogs extends ElementsLepidodendronMod.ModElement {
	public RecipePetrifiedWoodFromLogs(ElementsLepidodendronMod instance) {
		super(instance, 476);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockAraucarioxylonLog.block, (int) (1)),
				new ItemStack(BlockAraucarioxylonLogPetrified.block, (int) (1)), 1F);
	}
}
