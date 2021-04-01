package net.lepidodendron.procedure;

import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.BlockDirectional;

import net.lepidodendron.ElementsLepidodendronMod;

import java.util.Map;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureTreeLog extends ElementsLepidodendronMod.ModElement {
	public ProcedureTreeLog(ElementsLepidodendronMod instance) {
		super(instance, 200);
	}
	
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	
	public static void executeProcedure(int x, int y, int z, World world, Block blockLog, EnumFacing facing) {

		Block block = world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlock();
		if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), world,
			new BlockPos((int) x, (int) y, (int) z))
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == Material.VINE)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == Material.SNOW)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == Material.WEB)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == Material.WATER)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == Material.PLANTS)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == Material.LEAVES)) {

			try {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), blockLog.getDefaultState().withProperty(FACING,facing), 3);
			}
			catch (Exception e) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), blockLog.getDefaultState(), 3);
			}
		}
	}
}
