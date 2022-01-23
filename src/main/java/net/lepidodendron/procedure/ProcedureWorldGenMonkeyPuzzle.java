package net.lepidodendron.procedure;


import net.lepidodendron.ElementsLepidodendronMod;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenMonkeyPuzzle extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenMonkeyPuzzle(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenMonkeyPuzzle!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenMonkeyPuzzle!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenMonkeyPuzzle!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenMonkeyPuzzle!");
			return;
		}
		if (dependencies.get("SaplingSpawn") == null) {
			System.err.println("Failed to load dependency SaplingSpawn for procedure WorldGenMonkeyPuzzle!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		boolean SaplingSpawn = (boolean) dependencies.get("SaplingSpawn");
		Material material = world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getMaterial();
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))
			&& material != Material.GRASS
			&& material != Material.GROUND
			&& material != Material.GLASS
			&& material != Material.IRON
			&& material != Material.ROCK
			&& material != Material.SAND
			&& material != Material.WOOD
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
				ProcedureWorldGenMonkeyPuzzleNoCheck.executeProcedure($_dependencies);

		}
	}
}