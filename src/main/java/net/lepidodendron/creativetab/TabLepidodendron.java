
package net.lepidodendron.creativetab;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.NonNullList;

import net.lepidodendron.block.BlockAntarcticycas;
import net.lepidodendron.ElementsLepidodendronMod;

@ElementsLepidodendronMod.ModElement.Tag
public class TabLepidodendron extends ElementsLepidodendronMod.ModElement {
	public TabLepidodendron(ElementsLepidodendronMod instance) {
		super(instance, 173);
	}

	@Override
	public void initElements() {
		tab = new CreativeTabs("tablepidodendron") {
			@SideOnly(Side.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(BlockAntarcticycas.block, (int) (1));
			}

			@SideOnly(Side.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}

		}.setBackgroundImageName("item_search.png");
	}
	public static CreativeTabs tab;
}
