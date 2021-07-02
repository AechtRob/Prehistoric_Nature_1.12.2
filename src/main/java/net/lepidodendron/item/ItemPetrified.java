
package net.lepidodendron.item;

import net.lepidodendron.creativetab.TabLepidodendronPlants;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemPetrified extends Item {

	private Block plantBlock;

	public ItemPetrified(@Nullable Block plantBlock) {
		setMaxDamage(0);
		maxStackSize = 64;
		this.plantBlock = plantBlock;
		setCreativeTab(TabLepidodendronPlants.tab);
	}

	public ItemStack getPlantStack() {
		return new ItemStack(this.plantBlock, 1);
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 0;
	}

	@Override
	public float getDestroySpeed(ItemStack par1ItemStack, IBlockState par2Block) {
		return 1F;
	}

}
