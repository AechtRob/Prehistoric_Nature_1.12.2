package net.lepidodendron.procedure;


import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.BlockMonkeypuzzleLeaves;
import net.lepidodendron.block.BlockMonkeypuzzleLog;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenMonkeyPuzzleNoCheck extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenMonkeyPuzzleNoCheck(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenMonkeyPuzzleNoCheck!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenMonkeyPuzzleNoCheck!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenMonkeyPuzzleNoCheck!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenMonkeyPuzzleNoCheck!");
			return;
		}
		if (dependencies.get("SaplingSpawn") == null) {
			System.err.println("Failed to load dependency SaplingSpawn for procedure WorldGenMonkeyPuzzleNoCheck!");
			return;
		}

		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		int xx = (int) dependencies.get("x");
		int yy = (int) dependencies.get("y");
		int zz = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		boolean SaplingSpawn = (boolean) dependencies.get("SaplingSpawn");
		double TrunkHeight = 0;
		double counter = 0;
		double counter2 = 0;
		boolean smalltree = false;
		double largeAraucariaAraucana = Math.round(LepidodendronConfig.largeAraucariaAraucana);
		
		if (largeAraucariaAraucana > 100) {largeAraucariaAraucana = 100;}
		if (largeAraucariaAraucana < 0) {largeAraucariaAraucana = 0;}
		largeAraucariaAraucana = 1 - (double) largeAraucariaAraucana/100;
		
		if ((Math.random() <= largeAraucariaAraucana) || (largeAraucariaAraucana == 1)) {
			smalltree=true;
		}
		if (largeAraucariaAraucana == 0) {smalltree=false;}
		
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))
			) {			
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
			if (!smalltree) {
				ProcedureTreeLog.executeProcedure((int) (x + 1), (int) (y - 1), (int) (z - 1), world, BlockMonkeypuzzleLog.block, EnumFacing.NORTH);
				ProcedureTreeLog.executeProcedure((int) (x + 1), (int) (y - 1), (int) z, world, BlockMonkeypuzzleLog.block, EnumFacing.NORTH);
				ProcedureTreeLog.executeProcedure((int) x, (int) (y - 1), (int) (z - 1), world, BlockMonkeypuzzleLog.block, EnumFacing.NORTH);
			}
			
			//Trunk, up to 35 blocks, but makle them uncommon at that size:
			TrunkHeight = 20 + Math.round(Math.random() * 15);
			if (smalltree || ((TrunkHeight >= 30) && (Math.random() > 0.3))) {
				TrunkHeight = Math.round(TrunkHeight * 0.7);
			}
			
			counter = 0;
			while (counter <= TrunkHeight) {
				ProcedureTreeLog.executeProcedure((int) x, (int) (y + counter), (int) z, world, BlockMonkeypuzzleLog.block, EnumFacing.NORTH);
				if (!smalltree) {
					ProcedureTreeLog.executeProcedure((int) (x + 1), (int) (y + counter), (int) (z - 1), world, BlockMonkeypuzzleLog.block, EnumFacing.NORTH);
					ProcedureTreeLog.executeProcedure((int) (x + 1), (int) (y + counter), (int) z, world, BlockMonkeypuzzleLog.block, EnumFacing.NORTH);
					ProcedureTreeLog.executeProcedure((int) x, (int) (y + counter), (int) (z - 1), world, BlockMonkeypuzzleLog.block, EnumFacing.NORTH);
				}
				counter = counter + 1;
			}

			ProcedureLeavesAroundLog.executeProcedure(x, (int) TrunkHeight + y, z, world, BlockMonkeypuzzleLeaves.block, 2, 0.2);
			if (!smalltree) {
				ProcedureLeavesAroundLog.executeProcedure((x + 1), (int) TrunkHeight + y, (z - 1), world, BlockMonkeypuzzleLeaves.block, 2, 0.2);
				ProcedureLeavesAroundLog.executeProcedure((x + 1), (int) TrunkHeight + y, z, world, BlockMonkeypuzzleLeaves.block, 2, 0.2);
				ProcedureLeavesAroundLog.executeProcedure(x, (int) TrunkHeight + y, (z - 1), world, BlockMonkeypuzzleLeaves.block, 2, 0.2);
			}
			
			//North:
			java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
			$_dependencies.put("x", x);
			$_dependencies.put("y", (int) TrunkHeight + y);
			if (smalltree) {
				$_dependencies.put("z", z - 1);
			}
			else {
				$_dependencies.put("z", z - 2);
			}
			$_dependencies.put("world", world);
			$_dependencies.put("TrunkHeight", TrunkHeight);
			$_dependencies.put("smalltree", smalltree);
			ProcedureWorldGenMonkeyPuzzleNorthBranch.executeProcedure($_dependencies);

			//South:
			//java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
			$_dependencies.put("x", x);
			$_dependencies.put("y", (int) TrunkHeight + y);
			$_dependencies.put("z", z + 1);
			$_dependencies.put("world", world);
			$_dependencies.put("TrunkHeight", TrunkHeight);
			$_dependencies.put("smalltree", smalltree);
			ProcedureWorldGenMonkeyPuzzleSouthBranch.executeProcedure($_dependencies);
			
			//East
			if (smalltree) {
				$_dependencies.put("x", x - 1);
			}
			else {
				$_dependencies.put("x", x - 2);
			}
			$_dependencies.put("y", (int) TrunkHeight + y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			$_dependencies.put("TrunkHeight", TrunkHeight);
			$_dependencies.put("smalltree", smalltree);
			ProcedureWorldGenMonkeyPuzzleEastBranch.executeProcedure($_dependencies);
			
			//West:
			$_dependencies.put("x", x - 1);
			$_dependencies.put("y", (int) TrunkHeight + y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			$_dependencies.put("TrunkHeight", TrunkHeight);
			$_dependencies.put("smalltree", smalltree);
			ProcedureWorldGenMonkeyPuzzleWestBranch.executeProcedure($_dependencies);

			//Possible nubs and leaves and 3/4 minus 2 to 5 blocks:
			//North:
			if (Math.random() >=0.8) {
				//Get a random position:
				if (smalltree) {
					xx = x;
				}
				else {
					xx = x + (int) Math.round(Math.random());
				}
				//xx = x + (int) Math.round(Math.random());
				yy = y + (int) Math.round(TrunkHeight * 0.75) - 2 - (int) (Math.round(Math.random() * 3));
				//zz = z - 2;
				if (smalltree) {
					zz = z - 1;
				}
				else {
					zz = z - 2;
				}
				//Place nub:
				ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockMonkeypuzzleLog.block, EnumFacing.WEST);
				if (Math.random() >0.6) {
					ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz - 1, world, BlockMonkeypuzzleLog.block, EnumFacing.WEST);
					ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz - 1, world, BlockMonkeypuzzleLeaves.block, 2, 0.45);
				}
				else {
					ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockMonkeypuzzleLeaves.block, 2, 0.45);
				}
			}

			//South:
			if (Math.random() >=0.8) {
				//Get a random position:
				if (smalltree) {
					xx = x;
				}
				else {
					xx = x + (int) Math.round(Math.random());
				}
				//xx = x + (int) Math.round(Math.random());
				yy = y + (int) Math.round(TrunkHeight * 0.75) - 2 - (int) (Math.round(Math.random() * 3));
				zz = z + 1;
				//Place nub:
				ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockMonkeypuzzleLog.block, EnumFacing.WEST);
				if (Math.random() >0.6) {
					ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz + 1, world, BlockMonkeypuzzleLog.block, EnumFacing.WEST);
					ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz + 1, world, BlockMonkeypuzzleLeaves.block, 2, 0.45);
				}
				else {
					ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockMonkeypuzzleLeaves.block, 2, 0.45);
				}
			}

			//East:
			if (Math.random() >=0.8) {
				//Get a random position:
				if (smalltree) {
					xx = x + 1;
				}
				else {
					xx = x + 2;
				}
				//xx = x + 2;
				yy = y + (int) Math.round(TrunkHeight * 0.75) - 2 - (int) (Math.round(Math.random() * 3));
				//zz = z - (int) Math.round(Math.random());
				if (smalltree) {
					zz = z;
				}
				else {
					zz = z - (int) Math.round(Math.random());
				}
				//Place nub:
				ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockMonkeypuzzleLog.block, EnumFacing.UP);
				if (Math.random() >0.6) {
					ProcedureTreeLog.executeProcedure((int) xx + 1, (int) yy, (int) zz, world, BlockMonkeypuzzleLog.block, EnumFacing.UP);
					ProcedureLeavesAroundLog.executeProcedure(xx + 1, yy, zz, world, BlockMonkeypuzzleLeaves.block, 2, 0.45);
				}
				else {
					ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockMonkeypuzzleLeaves.block, 2, 0.45);
				}
			}

			//West:
			if (Math.random() >=0.8) {
				//Get a random position:
				xx = x - 1;
				yy = y + (int) Math.round(TrunkHeight * 0.75) - 2 - (int) (Math.round(Math.random() * 3));
				//zz = z - (int) Math.round(Math.random());
				if (smalltree) {
					zz = z;
				}
				else {
					zz = z - (int) Math.round(Math.random());
				}
				//Place nub:
				ProcedureTreeLog.executeProcedure((int) xx, (int) yy, (int) zz, world, BlockMonkeypuzzleLog.block, EnumFacing.UP);
				if (Math.random() >0.6) {
					ProcedureTreeLog.executeProcedure((int) xx - 1, (int) yy, (int) zz, world, BlockMonkeypuzzleLog.block, EnumFacing.UP);
					ProcedureLeavesAroundLog.executeProcedure(xx - 1, yy, zz, world, BlockMonkeypuzzleLeaves.block, 2, 0.45);
				}
				else {
					ProcedureLeavesAroundLog.executeProcedure(xx, yy, zz, world, BlockMonkeypuzzleLeaves.block, 2, 0.45);
				}
				

			}

			ProcedureSpawnNilssoniocladus.executeProcedure(x, y, z, world, LepidodendronConfig.genNilssoniocladusAraucariaAraucana, SaplingSpawn);

		}
	}
}