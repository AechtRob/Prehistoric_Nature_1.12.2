
package net.lepidodendron.item.crafting;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@ElementsLepidodendronMod.ModElement.Tag
public class RecipeGlass extends ElementsLepidodendronMod.ModElement {
	public RecipeGlass(ElementsLepidodendronMod instance) {
		super(instance, 436);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.addSmelting(new ItemStack(BlockSandPangaean.block, (int) (1)), new ItemStack(Blocks.GLASS, (int) (1)), 0.1F);
		GameRegistry.addSmelting(new ItemStack(BlockSandPangaeanSticky.block, (int) (1)), new ItemStack(Blocks.GLASS, (int) (1)), 0.1F);
		GameRegistry.addSmelting(new ItemStack(BlockSandSticky.block, (int) (1)), new ItemStack(Blocks.GLASS, (int) (1)), 0.1F);
		GameRegistry.addSmelting(new ItemStack(BlockSandRedWavy.block, (int) (1)), new ItemStack(Blocks.GLASS, (int) (1)), 0.1F);
		GameRegistry.addSmelting(new ItemStack(BlockSandPangaeanWavy.block, (int) (1)), new ItemStack(Blocks.GLASS, (int) (1)), 0.1F);
		GameRegistry.addSmelting(new ItemStack(BlockSandPangaeanWavySticky.block, (int) (1)), new ItemStack(Blocks.GLASS, (int) (1)), 0.1F);
		GameRegistry.addSmelting(new ItemStack(BlockSandRedSticky.block, (int) (1)), new ItemStack(Blocks.GLASS, (int) (1)), 0.1F);
		GameRegistry.addSmelting(new ItemStack(BlockSandRedWavySticky.block, (int) (1)), new ItemStack(Blocks.GLASS, (int) (1)), 0.1F);
		GameRegistry.addSmelting(new ItemStack(BlockSandWavy.block, (int) (1)), new ItemStack(Blocks.GLASS, (int) (1)), 0.1F);
		GameRegistry.addSmelting(new ItemStack(BlockSandWavySticky.block, (int) (1)), new ItemStack(Blocks.GLASS, (int) (1)), 0.1F);
	}
}
