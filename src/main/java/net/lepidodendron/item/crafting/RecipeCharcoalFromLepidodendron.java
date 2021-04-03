
package net.lepidodendron.item.crafting;

import net.lepidodendron.block.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

import net.lepidodendron.block.BlockPodozamitesLog;
import net.lepidodendron.block.BlockWalchiaLog;
import net.lepidodendron.item.ItemCookedBreadfruit;
import net.lepidodendron.item.ItemPrototaxitesChunk;
import net.lepidodendron.item.ItemCookedPrototaxites;

import net.lepidodendron.ElementsLepidodendronMod;

@ElementsLepidodendronMod.ModElement.Tag
public class RecipeCharcoalFromLepidodendron extends ElementsLepidodendronMod.ModElement {
	public RecipeCharcoalFromLepidodendron(ElementsLepidodendronMod instance) {
		super(instance, 1092);
	}

	@Override
	public void init(FMLInitializationEvent event) {

		GameRegistry.addSmelting(new ItemStack(BlockArtocarpusFruitBlock.block, (int) (1)), new ItemStack(ItemCookedBreadfruit.block, (int) (1)), 0.5F);
		GameRegistry.addSmelting(new ItemStack(ItemPrototaxitesChunk.block, (int) (1)), new ItemStack(ItemCookedPrototaxites.block, (int) (1)), 0.5F);
		//GameRegistry.addSmelting(new ItemStack(BlockCarboniferousMud.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 0), 0.1F);

		GameRegistry.addSmelting(new ItemStack(BlockWoodenLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPodozamitesLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockWalchiaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockCordaitesLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockGlossopterisLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
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

		GameRegistry.addSmelting(new ItemStack(BlockDioonLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockDicksoniaLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockCycasLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPsaroniusLog.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
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
		GameRegistry.addSmelting(new ItemStack(BlockArchaeopterisBranch.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockCalamitesBranch.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockPsaroniusBranch.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		;
		GameRegistry.addSmelting(new ItemStack(BlockNilssoniocladusStem.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
		GameRegistry.addSmelting(new ItemStack(BlockNilssoniocladusStemNE.block, (int) (1)), new ItemStack(Items.COAL, (int) (1), 1), 0.15F);
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

	}
}
