
package net.lepidodendron.item.crafting;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.item.entities.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@ElementsLepidodendronMod.ModElement.Tag
public class RecipeCookedMeats extends ElementsLepidodendronMod.ModElement {
	public RecipeCookedMeats(ElementsLepidodendronMod instance) {
		super(instance, 1092);
	}

	@Override
	public void init(FMLInitializationEvent event) {

		GameRegistry.addSmelting(new ItemStack(ItemAcanthodesMeat.block, (int) (1)), new ItemStack(ItemCookedAcanthodes.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemBothriolepisMeat.block, (int) (1)), new ItemStack(ItemCookedBothriolepis.block, (int) (1)), 0.35F);

	}
}
