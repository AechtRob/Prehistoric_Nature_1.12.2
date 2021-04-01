package net.lepidodendron.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.lepidodendron.ElementsLepidodendronMod;

import net.minecraft.tileentity.TileEntity;


@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenRedwood extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenRedwood(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenRedwood!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenRedwood!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenRedwood!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenRedwood!");
			return;
		}
		if (dependencies.get("SaplingSpawn") == null) {
			System.err.println("Failed to load dependency SaplingSpawn for procedure WorldGenRedwood!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		boolean SaplingSpawn = (boolean) dependencies.get("SaplingSpawn");
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))
			&& (world.canSeeSky(new BlockPos((int) (x + 1), (int) (y + 1), (int) (z - 1))))
			&& (world.canSeeSky(new BlockPos((int) (x + 1), (int) (y + 1), (int) z)))
			&& (world.canSeeSky(new BlockPos((int) x, (int) (y + 1), (int) (z - 1))))
			) {			
				world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
				world.setBlockToAir(new BlockPos((int) (x + 1), (int) y, (int) (z - 1)));
				world.setBlockToAir(new BlockPos((int) (x + 1), (int) y, (int) z));
				world.setBlockToAir(new BlockPos((int) x, (int) y, (int) (z - 1)));
				
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				$_dependencies.put("SaplingSpawn", SaplingSpawn);
				ProcedureWorldGenRedwoodNoCheck.executeProcedure($_dependencies);
		}
	}
}	