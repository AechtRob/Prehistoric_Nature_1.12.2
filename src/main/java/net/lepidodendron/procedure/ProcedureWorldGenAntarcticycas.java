package net.lepidodendron.procedure;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockAntarcticycas;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenAntarcticycas extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenAntarcticycas(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldAntarcticycas!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldAntarcticycas!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldAntarcticycas!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldAntarcticycas!");
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
					world.setBlockState(new BlockPos((int) x, (int) (y), (int) z), BlockAntarcticycas.block.getDefaultState(), 3);
					//world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), BlockAntarcticycasTop.block.getDefaultState(), 3);
				}
			}
		}
			
	}
}