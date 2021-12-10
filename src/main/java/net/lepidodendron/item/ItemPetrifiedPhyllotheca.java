
package net.lepidodendron.item;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsLepidodendronMod.ModElement.Tag
public class ItemPetrifiedPhyllotheca extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:petrified_phyllotheca")
	public static final Item block = null;
	public ItemPetrifiedPhyllotheca(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.petrified_phyllotheca);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("lepidodendron:petrified_phyllotheca", "inventory"));
	}
	public static class ItemCustom extends ItemPetrified {
		public ItemCustom() {
			super(null);
			setTranslationKey("pf_petrified_phyllotheca");
			setRegistryName("petrified_phyllotheca");
		}

		@Override
		public ItemStack getPlantStack() {
			return new ItemStack(ItemPhyllothecaItem.block, 1);
		}

	}
}
