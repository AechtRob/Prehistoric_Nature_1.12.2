package net.lepidodendron.procedure;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockPhasmatocycasLog;
import net.lepidodendron.block.BlockPhasmatocycasShoot;
import net.lepidodendron.block.BlockPhasmatocycasShootPlaceable;
import net.minecraft.block.Block;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenPhasmatocycas extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenPhasmatocycas(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenPhasmatocycas!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenPhasmatocycas!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenPhasmatocycas!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenPhasmatocycas!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double TrunkHeight = 0;
		double counter = 0;
		
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))
			) {			
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
			
			//Trunk:
			TrunkHeight = Math.round(Math.random() * 5) - 1;
			if (Math.random() > 0.4) TrunkHeight = Math.round(TrunkHeight * 0.5);
			if (TrunkHeight < 0) TrunkHeight = 0;

			counter = 0;
			while (counter <= TrunkHeight) {
				ProcedureTreeLog.executeProcedure((int) x, (int) (y + counter), (int) z, world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
				counter = counter + 1;
				}

			Block block = world.getBlockState(new BlockPos((int) x, (int) (y + counter), (int) z)).getBlock();
			AddShoot(world, (int) x, (int) (y + counter), (int) z);

		}
			
	}

	public static void AddShoot(World world, int x, int y, int z) {
		//Trunk:
		double TrunkHeight = Math.round(Math.random() * 3);

		int counter = 0;
		while (counter < TrunkHeight) {
			ProcedureTreeLog.executeProcedure((int) x, (int) (y + counter), (int) z, world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
			counter = counter + 1;
		}

		//Shoots:
		if (TrunkHeight < 1) {
			Block block = world.getBlockState(new BlockPos((int) x, (int) (y + counter), (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + counter), (int) z)), world, new BlockPos((int) x, (int) (y + counter), (int) z))) {
				world.setBlockState(new BlockPos((int) x, (int) (y + counter), (int) z), BlockPhasmatocycasShootPlaceable.block.getDefaultState(), 3);
			}
		}

		if (TrunkHeight == 1) {
			Block block = world.getBlockState(new BlockPos((int) x, (int) (y + counter), (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + counter), (int) z)), world, new BlockPos((int) x, (int) (y + counter), (int) z))) {
				world.setBlockState(new BlockPos((int) x, (int) (y + counter), (int) z), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
			}
		}

		if (TrunkHeight == 2) {
			if (Math.random() > 0.33) {
				if (Math.random() > 0.5) {
					//North-South
					ProcedureTreeLog.executeProcedure((int) x, (int) (y + 1), (int) (z + 1), world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
					Block block = world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z + 1))).getBlock();
					if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z + 1))), world, new BlockPos((int) x, (int) (y + 2), (int) (z + 1)))) {
						world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z + 1)), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
								}
					ProcedureTreeLog.executeProcedure((int) x, (int) (y + 1), (int) (z - 1), world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
					block = world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z - 1))).getBlock();
					if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z - 1))), world, new BlockPos((int) x, (int) (y + 2), (int) (z - 1)))) {
						world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z - 1)), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
					}
				}
				else {
					//East-West
					ProcedureTreeLog.executeProcedure((int) x + 1, (int) (y + 1), (int) z, world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
					Block block = world.getBlockState(new BlockPos((int) x + 1, (int) (y + 2), (int) z)).getBlock();
					if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x + 1, (int) (y + 2), (int) z)), world, new BlockPos((int) x + 1, (int) (y + 2), (int) z))) {
						world.setBlockState(new BlockPos((int) x + 1, (int) (y + 2), (int) z), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
					}
					ProcedureTreeLog.executeProcedure((int) x - 1, (int) (y + 1), (int) z, world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
					block = world.getBlockState(new BlockPos((int) x - 1, (int) (y + 2), (int) z)).getBlock();
					if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x - 1, (int) (y + 2), (int) z)), world, new BlockPos((int) x - 1, (int) (y + 2), (int) z))) {
						world.setBlockState(new BlockPos((int) x - 1, (int) (y + 2), (int) z), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
					}
				}
			}
			else {
				Block block = world.getBlockState(new BlockPos((int) x, (int) (y + counter), (int) z)).getBlock();
				if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + counter), (int) z)), world, new BlockPos((int) x, (int) (y + counter), (int) z))) {
					world.setBlockState(new BlockPos((int) x, (int) (y + counter), (int) z), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
				}
			}
		}

		if (TrunkHeight >=3) {
			Block block = world.getBlockState(new BlockPos((int) x, (int) (y + counter), (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + counter), (int) z)), world, new BlockPos((int) x, (int) (y + counter), (int) z))) {
				world.setBlockState(new BlockPos((int) x, (int) (y + counter), (int) z), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
			}

			if (Math.random() > 0.33) {
				double randomiser = Math.random();
				if (randomiser > 0.75) {
					//North
					ProcedureTreeLog.executeProcedure((int) x, (int) (y + 1), (int) (z - 1), world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
					block = world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z - 1))).getBlock();
					if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z - 1))), world, new BlockPos((int) x, (int) (y + 2), (int) (z - 1)))) {
						world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z - 1)), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
					}
					if (TrunkHeight >=4) {
						ProcedureTreeLog.executeProcedure((int) x, (int) (y + 2), (int) (z + 1), world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
						block = world.getBlockState(new BlockPos((int) x, (int) (y + 3), (int) (z + 1))).getBlock();
						if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 3), (int) (z + 1))), world, new BlockPos((int) x, (int) (y + 3), (int) (z + 1)))) {
							world.setBlockState(new BlockPos((int) x, (int) (y + 3), (int) (z + 1)), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
						}
					}
				}
				else {
					if (randomiser > 0.5) {
						//South
						ProcedureTreeLog.executeProcedure((int) x, (int) (y + 1), (int) (z + 1), world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
						block = world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z + 1))).getBlock();
						if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z + 1))), world, new BlockPos((int) x, (int) (y + 2), (int) (z + 1)))) {
							world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z + 1)), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
						}
						if (TrunkHeight >=4) {
							ProcedureTreeLog.executeProcedure((int) x, (int) (y + 2), (int) (z - 1), world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
							block = world.getBlockState(new BlockPos((int) x, (int) (y + 3), (int) (z - 1))).getBlock();
							if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 3), (int) (z - 1))), world, new BlockPos((int) x, (int) (y + 3), (int) (z - 1)))) {
								world.setBlockState(new BlockPos((int) x, (int) (y + 3), (int) (z - 1)), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
							}
						}
					}
					else {
						if (randomiser > 0.25) {
							//East
							ProcedureTreeLog.executeProcedure((int) x + 1, (int) (y + 1), (int) z, world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
							block = world.getBlockState(new BlockPos((int) x + 1, (int) (y + 2), (int) z)).getBlock();
							if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x + 1, (int) (y + 2), (int) z)), world, new BlockPos((int) x + 1, (int) (y + 2), (int) z))) {
								world.setBlockState(new BlockPos((int) x + 1, (int) (y + 2), (int) z), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
							}
							if (TrunkHeight >=4) {
								ProcedureTreeLog.executeProcedure((int) x - 1, (int) (y + 2), (int) z, world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
								block = world.getBlockState(new BlockPos((int) x - 1, (int) (y + 3), (int) z)).getBlock();
								if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x - 1, (int) (y + 3), (int) z)), world, new BlockPos((int) x - 1, (int) (y + 3), (int) z))) {
									world.setBlockState(new BlockPos((int) x - 1, (int) (y + 3), (int) z), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
								}
							}
						}
						else {
							//West
							ProcedureTreeLog.executeProcedure((int) x - 1, (int) (y + 1), (int) z, world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
							block = world.getBlockState(new BlockPos((int) x - 1, (int) (y + 2), (int) z)).getBlock();
							if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x - 1, (int) (y + 2), (int) z)), world, new BlockPos((int) x - 1, (int) (y + 2), (int) z))) {
								world.setBlockState(new BlockPos((int) x - 1, (int) (y + 2), (int) z), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
							}
							if (TrunkHeight >=4) {
								ProcedureTreeLog.executeProcedure((int) x + 1, (int) (y + 2), (int) z, world, BlockPhasmatocycasLog.block, EnumFacing.DOWN);
								block = world.getBlockState(new BlockPos((int) x + 1, (int) (y + 3), (int) z)).getBlock();
								if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x + 1, (int) (y + 3), (int) z)), world, new BlockPos((int) x + 1, (int) (y + 3), (int) z))) {
									world.setBlockState(new BlockPos((int) x + 1, (int) (y + 3), (int) z), BlockPhasmatocycasShoot.block.getDefaultState(), 3);
								}
							}
						}
					}
				}
			}
		}
	}
}