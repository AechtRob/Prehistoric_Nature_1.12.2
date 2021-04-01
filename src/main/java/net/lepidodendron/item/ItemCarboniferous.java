
package net.lepidodendron.item;

import net.minecraftforge.fml.common.registry.GameRegistry;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumActionResult;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;

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
