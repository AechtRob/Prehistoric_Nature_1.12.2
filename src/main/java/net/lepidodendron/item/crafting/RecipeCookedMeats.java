
package net.lepidodendron.item.crafting;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.item.*;
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
		GameRegistry.addSmelting(new ItemStack(ItemIchthyostegaRaw.block, (int) (1)), new ItemStack(ItemIchthyostegaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAmphibamusRaw.block, (int) (1)), new ItemStack(ItemAmphibamusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemMixopterusRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemJaekelopterusRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPterygotusRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemMegarachneRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemLunataspisRaw.block, (int) (1)), new ItemStack(ItemLunataspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemSelenopeltisRaw.block, (int) (1)), new ItemStack(ItemSelenopeltisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemHemicyclaspisRaw.block, (int) (1)), new ItemStack(ItemHemicyclaspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAteleaspisRaw.block, (int) (1)), new ItemStack(ItemAteleaspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCephalaspisRaw.block, (int) (1)), new ItemStack(ItemCephalaspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPsarolepisRaw.block, (int) (1)), new ItemStack(ItemPsarolepisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAegirocassisRaw.block, (int) (1)), new ItemStack(ItemAegirocassisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemThelodusRaw.block, (int) (1)), new ItemStack(ItemThelodusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemTullimonstrumRaw.block, (int) (1)), new ItemStack(ItemTullimonstrumCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCladoselacheRaw.block, (int) (1)), new ItemStack(ItemCladoselacheCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemHyneriaRaw.block, (int) (1)), new ItemStack(ItemHyneriaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAcutiramusRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAiniktozoonRaw.block, (int) (1)), new ItemStack(ItemAiniktozoonCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAkmonistionRaw.block, (int) (1)), new ItemStack(ItemAkmonistionCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemBelantseaRaw.block, (int) (1)), new ItemStack(ItemBelantseaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCampbellodusRaw.block, (int) (1)), new ItemStack(ItemCampbellodusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCoccosteusRaw.block, (int) (1)), new ItemStack(ItemCoccosteusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemDracopristisRaw.block, (int) (1)), new ItemStack(ItemDracopristisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemKalbarriaRaw.block, (int) (1)), new ItemStack(ItemKalbarriaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPalaeoisopusRaw.block, (int) (1)), new ItemStack(ItemPalaeoisopusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPlatysomusRaw.block, (int) (1)), new ItemStack(ItemPlatysomusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemRhizodusRaw.block, (int) (1)), new ItemStack(ItemRhizodusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemXenacanthusRaw.block, (int) (1)), new ItemStack(ItemXenacanthusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemGemuendinaRaw.block, (int) (1)), new ItemStack(ItemGemuendinaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemParvancorinaRaw.block, (int) (1)), new ItemStack(ItemParvancorinaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCarcinosomaRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemKokomopterusRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPageaRaw.block, (int) (1)), new ItemStack(ItemEurypteridCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemEglonaspisRaw.block, (int) (1)), new ItemStack(ItemEglonaspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAcadoaradoxidesRaw.block, (int) (1)), new ItemStack(ItemAcadoaradoxidesCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAlacarisRaw.block, (int) (1)), new ItemStack(ItemAlacarisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemSiberionRaw.block, (int) (1)), new ItemStack(ItemSiberionCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemBanffiaRaw.block, (int) (1)), new ItemStack(ItemBanffiaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemYawunikRaw.block, (int) (1)), new ItemStack(ItemYawunikCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPomatrumRaw.block, (int) (1)), new ItemStack(ItemPomatrumCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemOpabiniaRaw.block, (int) (1)), new ItemStack(ItemOpabiniaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemLyrarapaxRaw.block, (int) (1)), new ItemStack(ItemLyrarapaxCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemTokummiaRaw.block, (int) (1)), new ItemStack(ItemTokummiaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPaucipodiaRaw.block, (int) (1)), new ItemStack(ItemPaucipodiaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemAnomalocarisRaw.block, (int) (1)), new ItemStack(ItemAnomalocarisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemMegalocephalusRaw.block, (int) (1)), new ItemStack(ItemMegalocephalusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemLaminacarisRaw.block, (int) (1)), new ItemStack(ItemLaminacarisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCambrorasterRaw.block, (int) (1)), new ItemStack(ItemCambrorasterCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCanadaspisRaw.block, (int) (1)), new ItemStack(ItemCanadaspisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemEllipsocephalusRaw.block, (int) (1)), new ItemStack(ItemEllipsocephalusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemElrathiaRaw.block, (int) (1)), new ItemStack(ItemElrathiaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemEoredlichiaRaw.block, (int) (1)), new ItemStack(ItemEoredlichiaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemJianshanopodiaRaw.block, (int) (1)), new ItemStack(ItemJianshanopodiaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemKodymirusRaw.block, (int) (1)), new ItemStack(ItemKodymirusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemKerygmachelaRaw.block, (int) (1)), new ItemStack(ItemKerygmachelaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemMarrellaRaw.block, (int) (1)), new ItemStack(ItemMarrellaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemNectocarisRaw.block, (int) (1)), new ItemStack(ItemNectocarisCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemParadoxidesRaw.block, (int) (1)), new ItemStack(ItemParadoxidesCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemVestinautilusRaw.block, (int) (1)), new ItemStack(ItemPalaeoCalamari.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemYohoiaRaw.block, (int) (1)), new ItemStack(ItemYohoiaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemParexusRaw.block, (int) (1)), new ItemStack(ItemParexusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemSynophalosRaw.block, (int) (1)), new ItemStack(ItemSynophalosCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPikaiaRaw.block, (int) (1)), new ItemStack(ItemPikaiaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemSpathicephalusRaw.block, (int) (1)), new ItemStack(ItemSpathicephalusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemHylonomusRaw.block, (int) (1)), new ItemStack(ItemHylonomusCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemYunnanozoonRaw.block, (int) (1)), new ItemStack(ItemYunnanozoonCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemHallucigeniaRaw.block, (int) (1)), new ItemStack(ItemHallucigeniaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemMicrodictyonRaw.block, (int) (1)), new ItemStack(ItemMicrodictyonCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemDeirocerasRaw.block, (int) (1)), new ItemStack(ItemPalaeoCalamari.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemGoniocerasRaw.block, (int) (1)), new ItemStack(ItemPalaeoCalamari.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemDianiaRaw.block, (int) (1)), new ItemStack(ItemDianiaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemMetasprigginaRaw.block, (int) (1)), new ItemStack(ItemMetasprigginaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemTegopelteRaw.block, (int) (1)), new ItemStack(ItemTegopelteCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemWiwaxiaRaw.block, (int) (1)), new ItemStack(ItemWiwaxiaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemPoleumita.block, (int) (1)), new ItemStack(ItemPalaeoEscargots.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemMaclurina.block, (int) (1)), new ItemStack(ItemPalaeoEscargots.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemOdontogriphusRaw.block, (int) (1)), new ItemStack(ItemPalaeoEscargots.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCyclonema.block, (int) (1)), new ItemStack(ItemPalaeoEscargots.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemXenusionRaw.block, (int) (1)), new ItemStack(ItemXenusionCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemMimetasterRaw.block, (int) (1)), new ItemStack(ItemMimetasterCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemOmnidensRaw.block, (int) (1)), new ItemStack(ItemOmnidensCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemBasilocerasRaw.block, (int) (1)), new ItemStack(ItemPalaeoCalamari.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemWebsteroprionRaw.block, (int) (1)), new ItemStack(ItemWebsteroprionCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemCheloniellonRaw.block, (int) (1)), new ItemStack(ItemCheloniellonCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemOttoiaRaw.block, (int) (1)), new ItemStack(ItemOttoiaCooked.block, (int) (1)), 0.35F);
		GameRegistry.addSmelting(new ItemStack(ItemHeterosteusRaw.block, (int) (1)), new ItemStack(ItemHeterosteusCooked.block, (int) (1)), 0.35F);
		//GameRegistry.addSmelting(new ItemStack(ItemIsotelusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);
		//GameRegistry.addSmelting(new ItemStack(ItemIsotelusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);
		//GameRegistry.addSmelting(new ItemStack(ItemIsotelusRaw.block, (int) (1)), new ItemStack(ItemIsotelusCooked.block, (int) (1)), 0.35F);


		GameRegistry.addSmelting(new ItemStack(ItemMonkeyPuzzleNuts.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemColumnarisNuts.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemBunyaNuts.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemAethophyllumNuts.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemAgathisNuts.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemAraucarioxylonNuts.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemMirabilisNuts.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemRedwoodNuts.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemSciadopitysNuts.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemDawnRedwoodNuts.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemAlpiaSeed.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemBrachyphyllumSeed.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemBristleconeSeed.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);
		GameRegistry.addSmelting(new ItemStack(ItemWollemiSeed.block, (int) (1)), new ItemStack(ItemRoastedPineNuts.block, (int) (1)), 0.15F);

	}
}
