
package net.lepidodendron.item.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;

import net.lepidodendron.item.ItemBalticAmberChunk;
import net.lepidodendron.block.BlockSciadopitysLog;
import net.lepidodendron.ElementsLepidodendronMod;

@ElementsLepidodendronMod.ModElement.Tag
public class RecipeAmberFromLogs extends ElementsLepidodendronMod.ModElement {
	public RecipeAmberFromLogs(ElementsLepidodendronMod instance) {
		super(instance, 477);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockSciadopitysLog.block, (int) (1)), new ItemStack(ItemBalticAmberChunk.block, (int) (1)), 1F);
	}
}
