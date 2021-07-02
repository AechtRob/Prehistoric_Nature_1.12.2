
package net.lepidodendron.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemOrdovicianSilurian extends Item {
	@GameRegistry.ObjectHolder("lepidodendron:ordovician_silurian")
	public static final Item block = null;
	public ItemOrdovicianSilurian() {
		super();
		this.maxStackSize = 1;
		setMaxDamage(64);
		setCreativeTab(null);
	}

}
