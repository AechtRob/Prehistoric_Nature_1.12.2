package net.lepidodendron.world;

import java.util.Random;

import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.lepidodendron.LepidodendronConfig;

public class RugosaGenerator extends WorldGenerator
{
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	private Block rugosa;
	private IBlockState state;

	public RugosaGenerator(Block rugosaIn)
	{
		this.setGeneratedBlock(rugosaIn);
	}

	public void setGeneratedBlock(Block rugosaIn)
	{
		this.rugosa = rugosaIn;
		this.state = rugosaIn.getDefaultState();
	}

	//marine int: -1  = non-marine, 0 = bothk 1 = marine only
	public boolean generate(World worldIn, Random rand, BlockPos position)
	{
		int dimID = worldIn.provider.getDimension();
		boolean dimensionCriteria = false;
		if (shouldGenerateInDimension(dimID, LepidodendronConfig.dimRugosa))
			dimensionCriteria = true;
		if ((dimID == LepidodendronConfig.dimDevonian)
				|| (dimID == LepidodendronConfig.dimOrdovicianSilurian)
				|| (dimID == LepidodendronConfig.dimCarboniferous)
		) {
			dimensionCriteria = true;
		}
		if (!dimensionCriteria)
			return true;

		for (int i = 0; i < 64; ++i)
		{
			int j = position.getX() + rand.nextInt(8) - rand.nextInt(8);
			int k = position.getY() + rand.nextInt(4) - rand.nextInt(4);
			int l = position.getZ() + rand.nextInt(8) - rand.nextInt(8);

			if (this.rugosa.canPlaceBlockAt(worldIn, new BlockPos(j, k, l))
					&& (worldIn.getBlockState(new BlockPos(j, k, l)).getMaterial() == Material.WATER)){
				//figure out a position and facing to place this at!
				//First try regular uprights and then the rotations:
				EnumFacing enumfacing = EnumFacing.UP;
				BlockPos pos = new BlockPos(j, k - 1, l);
				if ((worldIn.getBlockState(pos).getMaterial() == Material.SAND)
						|| (worldIn.getBlockState(pos).getMaterial() == Material.ROCK)
						|| (worldIn.getBlockState(pos).getMaterial() == Material.GROUND)
						|| (worldIn.getBlockState(pos).getMaterial() == Material.CLAY))
				{
					worldIn.setBlockState(new BlockPos(j, k, l), this.state.withProperty(FACING, enumfacing), 2);
					return true;
				}
				else {
					//if ( //exclude algae which can't go sideways!
					//		(this.rugosa != BlockGreenCharaAlgae.block )
					//				&& (this.rugosa != BlockGreenLeafyAlgae.block )
					//) {
					for (EnumFacing enumfacing1 : FACING.getAllowedValues())
					{
						pos = new BlockPos(j, k, l);
						if (enumfacing1 == EnumFacing.NORTH) {
							pos = new BlockPos(j, k, l + 1);
						}
						if (enumfacing1 == EnumFacing.SOUTH) {
							pos = new BlockPos(j, k, l - 1);
						}
						if (enumfacing1 == EnumFacing.EAST) {
							pos = new BlockPos(j - 1, k, l);
						}
						if (enumfacing1 == EnumFacing.WEST) {
							pos = new BlockPos(j + 1, k, l);
						}
						if ((worldIn.getBlockState(pos).getMaterial() == Material.SAND)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.ROCK)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.GROUND)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.CLAY)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.GLASS)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.IRON)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.WOOD)) {
							worldIn.setBlockState(new BlockPos(j, k, l), this.state.withProperty(FACING, enumfacing1), 2);
							return true;
						}
						//}
					}
				}
			}
		}
		return true;
	}

	public boolean shouldGenerateInDimension(int id, int[] dims) {
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
}
