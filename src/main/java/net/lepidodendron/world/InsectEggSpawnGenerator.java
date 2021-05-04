package net.lepidodendron.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class InsectEggSpawnGenerator extends WorldGenerator
{
	public static final PropertyDirection FACING = BlockDirectional.FACING;
	private Block mobspawn;
    private IBlockState state;

    public InsectEggSpawnGenerator(Block mobspawnIn)
    {
        this.setGeneratedBlock(mobspawnIn);
    }

    public void setGeneratedBlock(Block mobspawnIn)
    {
        this.mobspawn = mobspawnIn;
        this.state = mobspawnIn.getDefaultState();
    }

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
		for (int i = 0; i < 8; ++i)
		{
			int j = position.getX() + rand.nextInt(8) - rand.nextInt(8);
			int k = position.getY() + rand.nextInt(4) - rand.nextInt(4);
			int l = position.getZ() + rand.nextInt(8) - rand.nextInt(8);

			if (this.mobspawn.canPlaceBlockAt(worldIn, new BlockPos(j, k, l))){
				//figure out a position and facing to place this at!
				//First try regular uprights and then the rotations:
				EnumFacing enumfacing = EnumFacing.UP;
				BlockPos pos = new BlockPos(j, k - 1, l);
				if (((worldIn.getBlockState(pos).getMaterial() == Material.SAND)
					|| (worldIn.getBlockState(pos).getMaterial() == Material.ROCK)
					|| (worldIn.getBlockState(pos).getMaterial() == Material.GROUND)
					|| (worldIn.getBlockState(pos).getMaterial() == Material.CLAY)
					|| (worldIn.getBlockState(pos).getMaterial() == Material.IRON)
					|| (worldIn.getBlockState(pos).getMaterial() == Material.WOOD))
					&& isByWater(worldIn, new BlockPos(j, k, l)))
				{
					worldIn.setBlockState(new BlockPos(j, k, l), this.state.withProperty(FACING, enumfacing), 2);
					return true;
				}
				else {
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
						if (((worldIn.getBlockState(pos).getMaterial() == Material.SAND)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.ROCK)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.GROUND)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.CLAY)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.GLASS)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.IRON)
								|| (worldIn.getBlockState(pos).getMaterial() == Material.WOOD))
								&& isByWater(worldIn, new BlockPos(j, k, l))) {
							worldIn.setBlockState(new BlockPos(j, k, l), this.state.withProperty(FACING, enumfacing1), 2);
							return true;
						}
					}
				}
			}
		}
		return true;
		}

		private boolean isByWater(World world, BlockPos pos) {
    		int x = -1;
    		int y = -1;
    		int z = -1;
    		while (x <=1) {
				while (y <=1) {
					while (z <=1) {
							if (!(x == 0 && y ==0 && z == 0)) {
								 if (world.getBlockState(pos.add(x, y, z)).getMaterial() == Material.WATER) {
								 	return true;
								 }
							}
						z = z + 1;
					}
					y = y + 1;
				}
    			x = x + 1;
			}
    		return false;
		}
}
