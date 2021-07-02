
package net.lepidodendron.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCarboniferous extends Item {
	@GameRegistry.ObjectHolder("lepidodendron:carboniferous")
	public static final Item block = null;
	public ItemCarboniferous() {
		super();
		this.maxStackSize = 1;
		setMaxDamage(64);
		setCreativeTab(null);
	}

}
