
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
		GameRegistry.addSmelting(new ItemStack(ItemQilinyuRaw.block, (int) (1)), new ItemStack(ItemQilinyuCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAphetocerasRaw.block, (int) (1)), new ItemStack(ItemPalaeoCalamari.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemEndocerasRaw.block, (int) (1)), new ItemStack(ItemPalaeoCalamari.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCamerocerasRaw.block, (int) (1)), new ItemStack(ItemPalaeoCalamari.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemOrthocerasRaw.block, (int) (1)), new ItemStack(ItemPalaeoCalamari.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPoraspisRaw.block, (int) (1)), new ItemStack(ItemPoraspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPalaeodictyopteraRaw.block, (int) (1)), new ItemStack(ItemPalaeodictyopteraCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemLimnoscelisRaw.block, (int) (1)), new ItemStack(ItemLimnoscelisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemSchinderhannesRaw.block, (int) (1)), new ItemStack(ItemSchinderhannesCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemTitanichthysRaw.block, (int) (1)), new ItemStack(ItemTitanichthysCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemLimnoscelisRaw.block, (int) (1)), new ItemStack(ItemLimnoscelisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemIchthyostegaRaw.block, (int) (1)), new ItemStack(ItemIchthyostegaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAmphibamusRaw.block, (int) (1)), new ItemStack(ItemAmphibamusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemMixopterusRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemJaekelopterusRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPterygotusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemMegarachneRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemLunataspisRaw.block, (int) (1)), new ItemStack(ItemLunataspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemSelenopeltisRaw.block, (int) (1)), new ItemStack(ItemSelenopeltisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemIsotelusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemIsotelusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemHemicyclaspisRaw.block, (int) (1)), new ItemStack(ItemHemicyclaspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAteleaspisRaw.block, (int) (1)), new ItemStack(ItemAteleaspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCephalaspisRaw.block, (int) (1)), new ItemStack(ItemCephalaspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPsarolepisRaw.block, (int) (1)), new ItemStack(ItemPsarolepisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAegirocassisRaw.block, (int) (1)), new ItemStack(ItemAegirocassisCooked.block, (int) (1)), 0.35F);
		//GameRegistry.addSmelting(new ItemStack(ItemIsotelusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);
		//GameRegistry.addSmelting(new ItemStack(ItemIsotelusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);
		//GameRegistry.addSmelting(new ItemStack(ItemIsotelusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);
		//GameRegistry.addSmelting(new ItemStack(ItemIsotelusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);

	}
}
