package net.lepidodendron.procedure;

import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

import net.lepidodendron.ElementsLepidodendronMod;

import java.util.Map;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureTreeLeaf extends ElementsLepidodendronMod.ModElement {
	public ProcedureTreeLeaf(ElementsLepidodendronMod instance) {
		super(instance, 202);
	}

		public static void executeProcedure(int x, int y, int z, World world, Block blockLeaf) {

		Block block = world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlock();
		if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), world,
			new BlockPos((int) x, (int) y, (int) z))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), blockLeaf.getDefaultState(), 3);
			}
	}
}
