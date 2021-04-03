
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

		GameRegistry.addSmelting(new ItemStack(ItemAcanthodesRaw.block, (int) (1)), new ItemStack(ItemAcanthodesCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemBothriolepisRaw.block, (int) (1)), new ItemStack(ItemBothriolepisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemArandaspisRaw.block, (int) (1)), new ItemStack(ItemArandaspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPteraspisRaw.block, (int) (1)), new ItemStack(ItemPteraspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemEurypterusRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCyrtocerasRaw.block, (int) (1)), new ItemStack(ItemPalaeoCalamari.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPromissumRaw.block, (int) (1)), new ItemStack(ItemPromissumCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemSacabambaspisRaw.block, (int) (1)), new ItemStack(ItemSacabambaspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemHibernaspisRaw.block, (int) (1)), new ItemStack(ItemHibernaspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAmmoniteRaw.block, (int) (1)), new ItemStack(ItemPalaeoCalamari.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemHibbertopterusRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemFurcacaudaRaw.block, (int) (1)), new ItemStack(ItemFurcacaudaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemTerataspisRaw.block, (int) (1)), new ItemStack(ItemTerataspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemIsotelusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemSquatinactisRaw.block, (int) (1)), new ItemStack(ItemSquatinactisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCheirurusRaw.block, (int) (1)), new ItemStack(ItemCheirurusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAsaphusRaw.block, (int) (1)), new ItemStack(ItemAsaphusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemWalliseropsRaw.block, (int) (1)), new ItemStack(ItemWalliseropsCooked.block, (int) (1)), 0.35F);
		//GameRegistry.addSmelting(new ItemStack(ItemIsotelusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);
	}
}
