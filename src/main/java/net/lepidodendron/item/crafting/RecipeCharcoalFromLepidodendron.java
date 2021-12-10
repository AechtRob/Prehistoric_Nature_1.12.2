
package net.lepidodendron.item.crafting;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.*;
import net.lepidodendron.item.ItemCookedBreadfruit;
import net.lepidodendron.item.ItemCookedPrototaxites;
import net.lepidodendron.item.ItemPrototaxitesChunk;
import net.lepidodendron.item.ItemZircon;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@ElementsLepidodendronMod.ModElement.Tag
public class RecipeCharcoalFromLepidodendron extends ElementsLepidodendronMod.ModElement {
	public RecipeCharcoalFromLepidodendron(ElementsLepidodendronMod instance) {
		super(instance, 1092);
	}

	@Override
	public void init(FMLInitializationEvent event) {

		GameRegistry.addSmelting(new ItemStack(BlockArtocarpusFruitBlock.block, (int) (1)), new ItemStack(ItemCookedBreadfruit.block, (int) (1)), 0.5F);
		GameRegistry.addSmelting(new ItemStack(ItemPrototaxitesChunk.block, (int) (1)), new ItemStack(ItemCookedPrototaxites.block, (int) (1)), 0.5F);

		GameRegistry.addSmelting(new ItemStack(BlockZirconOre.block, (int) (1)), new ItemStack(ItemZircon.block, (int) (1)), 0.75F);

		GameRegistry.addSmelting(new ItemStack(BlockRedClay.block, (int) (1)), new ItemStack(Blocks.HARDENED_CLAY, (int) (1)), 0.35F);

		GameRegistry.addSmelting(new ItemStack(BlockCarboniferousMud.block, (int) (1)), new ItemStack(BlockDriedMud.block, (int) (1)), 0.15F);

		GameRegistry.addSmelting(new ItemStack(BlockDriedMud.block, (int) (1)), new ItemStack(BlockBrownstone.block, (int) (1)), 0.15F);
		
		GameRegistry.addSmelting(new ItemStack(BlockOrangeSponge.block, (int) (1)), new ItemStack(Blocks.SPONGE, 1,0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockRedSponge.block, (int) (1)), new ItemStack(Blocks.SPONGE, 1,0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockWhiteSponge.block, (int) (1)), new ItemStack(Blocks.SPONGE, 1,0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockDarkOrangeSponge.block, (int) (1)), new ItemStack(Blocks.SPONGE, 1,0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockBrownSponge.block, (int) (1)), new ItemStack(Blocks.SPONGE, 1,0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPinkSponge.block, (int) (1)), new ItemStack(Blocks.SPONGE, 1,0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockDarkPinkSponge.block, (int) (1)), new ItemStack(Blocks.SPONGE, 1,0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockYellowSponge.block, (int) (1)), new ItemStack(Blocks.SPONGE, 1,0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockBlueSponge.block, (int) (1)), new ItemStack(Blocks.SPONGE, 1,0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockBranchedSponge.block, (int) (1)), new ItemStack(Blocks.SPONGE, 1,0), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockGigantospongia.block, (int) (1)), new ItemStack(Blocks.SPONGE, 1,0), 0.15F);

		GameRegistry.addSmelting(new ItemStack(BlockWoodenLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPodozamitesLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockWalchiaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockCordaitesLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockGlossopterisLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockGangamopterisLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockMagnoliaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockLiriodendronLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockCalamitesLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockRedwoodLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockWollemiLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockBothrodendronLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockDiaphorodendronLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockGinkgoLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockArchaeopterisLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockMirabilisLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockBristleconeLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockKomlopterisLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockAgathisLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockHironoiaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockAlpiaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockMonkeypuzzleLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockColumnarisLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockBunyaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockTempskyaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockCycadeoideaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockTaxodiumLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockTaxodiumKnee.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockDioonLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockDicksoniaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockZygopteridaceaeLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockCycasLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPsaroniusLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockTieteaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockEncblueLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockSpinyCycadLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockValmeyerodendronLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockZamitesLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockSahnioxylonLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockWattiezaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockWilliamsoniaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockMedullosalesLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockBjuviaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPleuromeiaStem.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockLeptocycasLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockSigillariaStem.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockGigantopteridLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockEmplectopterisLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockArchaeopterisBranch.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockCalamitesBranch.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPsaroniusBranch.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockTieteaBranch.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockNilssoniocladusStem.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockNilssoniocladusStemNE.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockNilssoniaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockDawnRedwoodLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockMapleLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockArtocarpusLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockEquisitesStem.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockNothofagusLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockYewLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockBrachyphyllumLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockOdontopterisStem.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockOdontopterisStemNE.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockAnkyropterisStem.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockAnkyropterisStemNE.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPterophyllumLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockUtrechtiaStem.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPalaeostachyaStem.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockNoeggerathialesLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockBanksiaLog1.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockBanksiaLog2.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockZygopterisLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPodocarpLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPitysLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockCalamophytonLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockCtenisLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockXenocladiaStem.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockXenocladiaStemNE.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockCzekanowskiaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockAneurophytonLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockLyginopterisStem.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockLyginopterisStemNE.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPhasmatocycasLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockArthropitysLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockDicroidiumFLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockTelemachusLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockSphenobaieraLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockAnomozamitesLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPtilophyllumLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockBeechLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockSycamoreLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPlaneLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockFurculaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockToditesLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockStiffCycadLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);

	}
}
