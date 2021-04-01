package net.lepidodendron.procedure;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.Block;

import net.lepidodendron.block.BlockLadiniaShootTop;
import net.lepidodendron.block.BlockLadiniaShoot;
import net.lepidodendron.block.BlockLadiniaShootMaleTop;
import net.lepidodendron.block.BlockLadiniaShootMale;
import net.lepidodendron.block.BlockLadiniaLog;
import net.lepidodendron.ElementsLepidodendronMod;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenLadinia extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenLadinia(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenLadinia!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenLadinia!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenLadinia!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenLadinia!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))
			) {			
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
			
			//Trunk:
			ProcedureTreeLog.executeProcedure((int) x, (int) y, (int) z, world, BlockLadiniaLog.block, EnumFacing.UP);
			//Shoots:
			if (Math.random() <= 0.5) {
				Block block = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).getBlock();
				if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), world, new BlockPos((int) x, (int) (y + 1), (int) z))) {
					block = world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) z)).getBlock();
					if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) z)), world, new BlockPos((int) x, (int) (y + 2), (int) z))) {
						world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), BlockLadiniaShoot.block.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) z), BlockLadiniaShootTop.block.getDefaultState(), 3);
					}
				}
			}
			else {
				Block block = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).getBlock();
				if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), world, new BlockPos((int) x, (int) (y + 1), (int) z))) {
					block = world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) z)).getBlock();
					if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 2), (int) z)), world, new BlockPos((int) x, (int) (y + 2), (int) z))) {
						world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), BlockLadiniaShootMale.block.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) z), BlockLadiniaShootMaleTop.block.getDefaultState(), 3);
					}
				}
			}
		}
	}
}