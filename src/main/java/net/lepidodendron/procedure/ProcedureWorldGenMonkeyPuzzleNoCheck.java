package net.lepidodendron.procedure;


import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.BlockLygodium;
import net.lepidodendron.block.BlockMonkeypuzzleLeaves;
import net.lepidodendron.block.BlockMonkeypuzzleLog;
import net.lepidodendron.world.biome.jurassic.BiomeJurassicFloodplainForested;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Random;


@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenMonkeyPuzzleNoCheck extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenMonkeyPuzzleNoCheck(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static final PropertyBool UP = PropertyBool.create("up");
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");

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
		
		Material material = world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getMaterial();
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))
			&& material != Material.GRASS
			&& material != Material.GROUND
			&& material != Material.GLASS
			&& material != Material.IRON
			&& material != Material.ROCK
			&& material != Material.SAND
			&& material != Material.WOOD
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



			//Random placement of lygodium:
			boolean SpawnLygodium = true;

			boolean dimensionCriteria = false;
			if (shouldGenerateInDimension(world.provider.getDimension(), LepidodendronConfig.dimLygodium))
				dimensionCriteria = true;
			if (!LepidodendronConfig.genLygodiumMonkeypuzzle && !LepidodendronConfig.genAllPlants)
				dimensionCriteria = false;
			if (!dimensionCriteria)
				SpawnLygodium = false;

			boolean biomeCriteria = false;
			Biome biome = world.getBiome(new BlockPos(x, y, z));
			if ((!matchBiome(biome, LepidodendronConfig.genGlobalBlacklist)) && (!matchBiome(biome, LepidodendronConfig.genLygodiumBlacklistBiomes))) {
				biomeCriteria = true;
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.DEAD))
					biomeCriteria = false;
				if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MUSHROOM))
					biomeCriteria = false;
			}
			if (matchBiome(biome, LepidodendronConfig.genLygodiumOverrideBiomes))
				biomeCriteria = true;
			if (!biomeCriteria)
				SpawnLygodium = false;
			if ((world.provider.getDimension() == LepidodendronConfig.dimJurassic)
			){
				if (biome == BiomeJurassicFloodplainForested.biome) {
					SpawnLygodium = true;
				}
				else {
					SpawnLygodium = false;
				}
			}

			BlockPos posVine;
			Random rand = new Random();
			int vineLength;
			int vineCount;
			counter = y;
			int xct = -5;
			int zct = -5;
			while (counter <= (y + TrunkHeight + 4)) {
				xct = -10;
				while (xct <= 10) {
					zct = -10;
					while (zct <= 10) {

						if ((world.getBlockState(new BlockPos((int) x + xct, (int) TrunkHeight + counter, (int) z + zct))).getBlock() == BlockMonkeypuzzleLeaves.block) {
							//Lygodium:
							if ((!SaplingSpawn) & (SpawnLygodium)) {
								//System.err.println("Trying to spawn vines");
								//North
								if ((Math.random() > 0.88)
										&& (world.isAirBlock(new BlockPos(x + xct, (int) TrunkHeight + counter, (int) z + zct + 1)))) {
									posVine = new BlockPos(x + xct, (int) TrunkHeight + counter, (int) z + zct + 1);
									world.setBlockState(posVine, BlockLygodium.block.getDefaultState().withProperty(UP, false).withProperty(NORTH, true).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
									vineLength = rand.nextInt((int)TrunkHeight) + 1;
									vineCount = 1;
									while (world.isAirBlock(posVine.down(vineCount)) && vineCount <= vineLength) {
										world.setBlockState(posVine.down(vineCount), BlockLygodium.block.getDefaultState().withProperty(UP, false).withProperty(NORTH, true).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
										vineCount += 1;
									}
								}
								//South
								if ((Math.random() > 0.88)
										&& (world.isAirBlock(new BlockPos(x + xct, (int) TrunkHeight + counter, (int) z + zct - 1)))) {
									posVine = new BlockPos(x + xct, (int) TrunkHeight + counter, (int) z + zct - 1);
									world.setBlockState(posVine, BlockLygodium.block.getDefaultState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, true).withProperty(WEST, false));
									vineLength = rand.nextInt((int)TrunkHeight) + 1;
									vineCount = 1;
									while (world.isAirBlock(posVine.down(vineCount)) && vineCount <= vineLength) {
										world.setBlockState(posVine.down(vineCount), BlockLygodium.block.getDefaultState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, true).withProperty(WEST, false));
										vineCount += 1;
									}
								}
								//East
								if ((Math.random() > 0.88)
										&& (world.isAirBlock(new BlockPos(x + xct - 1, (int) TrunkHeight + counter, (int) z + zct)))) {
									posVine = new BlockPos(x + xct - 1, (int) TrunkHeight + counter, (int) z + zct);
									world.setBlockState(posVine, BlockLygodium.block.getDefaultState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, true).withProperty(SOUTH, false).withProperty(WEST, false));
									vineLength = rand.nextInt((int)TrunkHeight) + 1;
									vineCount = 1;
									while (world.isAirBlock(posVine.down(vineCount)) && vineCount <= vineLength) {
										world.setBlockState(posVine.down(vineCount), BlockLygodium.block.getDefaultState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, true).withProperty(SOUTH, false).withProperty(WEST, false));
										vineCount += 1;
									}
								}
								//West
								if ((Math.random() > 0.88)
										&& (world.isAirBlock(new BlockPos(x + xct + 1, (int) TrunkHeight + counter, (int) z + zct)))) {
									posVine = new BlockPos(x + xct + 1, (int) TrunkHeight + counter, (int) z + zct);
									world.setBlockState(posVine, BlockLygodium.block.getDefaultState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, true));
									vineLength = rand.nextInt((int)TrunkHeight) + 1;
									vineCount = 1;
									while (world.isAirBlock(posVine.down(vineCount)) && vineCount <= vineLength) {
										world.setBlockState(posVine.down(vineCount), BlockLygodium.block.getDefaultState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, true));
										vineCount += 1;
									}
								}
							}
						}

						zct = zct + 1;
					}
					xct = xct + 1;
				}
				counter = counter + 1;
			}


			ProcedureSpawnNilssoniocladus.executeProcedure(x, y, z, world, LepidodendronConfig.genNilssoniocladusAraucariaAraucana, SaplingSpawn);

		}
	}

	public static boolean shouldGenerateInDimension(int id, int[] dims) {
		int[] var2 = dims;
		int var3 = dims.length;
		for (int var4 = 0; var4 < var3; ++var4) {
			int dim = var2[var4];
			if (dim == id) {
				return true;
			}
		}
		return false;
	}

	public static boolean matchBiome(Biome biome, String[] biomesList) {

		//String regName = biome.getRegistryName().toString();

		String[] var2 = biomesList;
		int var3 = biomesList.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String checkBiome = var2[var4];
			if (!checkBiome.contains(":")) {
				//System.err.println("modid test: " + biome.getRegistryName().toString().substring(0, biome.getRegistryName().toString().indexOf(":") - 1));
				if (checkBiome.equalsIgnoreCase(
						biome.getRegistryName().toString().substring(0, biome.getRegistryName().toString().indexOf(":"))
				)) {
					return true;
				}
			}
			if (checkBiome.equalsIgnoreCase(biome.getRegistryName().toString())) {
				return true;
			}
		}

		return false;
	}
}