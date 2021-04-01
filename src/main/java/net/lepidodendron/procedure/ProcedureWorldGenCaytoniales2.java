package net.lepidodendron.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.Block;

import net.lepidodendron.block.BlockCaytoniales2;
import net.lepidodendron.block.BlockCaytonialesTop2;
import net.lepidodendron.ElementsLepidodendronMod;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenCaytoniales2 extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenCaytoniales2(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldCaytoniales2!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldCaytoniales2!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldCaytoniales2!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldCaytoniales2!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))
			) {			
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));

			Block block = world.getBlockState(new BlockPos((int) x, (int) (y), (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y), (int) z)), world, new BlockPos((int) x, (int) (y), (int) z))) {
				block = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).getBlock();
				if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), world, new BlockPos((int) x, (int) (y + 1), (int) z))) {
					world.setBlockState(new BlockPos((int) x, (int) (y), (int) z), BlockCaytoniales2.block.getDefaultState(), 3);
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), BlockCaytonialesTop2.block.getDefaultState(), 3);
				}
			}
		}
			
	}
}