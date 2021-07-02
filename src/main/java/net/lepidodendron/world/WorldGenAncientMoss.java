package net.lepidodendron.world;

import net.lepidodendron.block.BlockAncientMoss;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenAncientMoss extends WorldGenerator
{
	
	public static final PropertyDirection FACING = BlockDirectional.FACING;

    public boolean generate(World worldIn, Random rand, BlockPos position) {
		boolean flag = false;

		for (int i = 0; i < 32; ++i)
		{
			BlockPos blockpos = position.add(rand.nextInt(3) - rand.nextInt(3), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(3) - rand.nextInt(3));

			if (blockpos.getY() >= worldIn.getSeaLevel()-4 && worldIn.isAirBlock(blockpos) && ((worldIn.getLight(blockpos) > 3) || (worldIn.canSeeSky(blockpos)))
					&& (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockAncientMoss.block.canPlaceBlockAt(worldIn, blockpos))
			{
				int orientation = rand.nextInt(6);
				if (orientation == 0) { //North
					if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.NORTH)) {
						worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 2);
						flag = true;
					}
				}
				else {
					if (orientation == 1) { //South
						if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.SOUTH)) {
							worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.SOUTH), 2);
							flag = true;
						}
					}
					else {
						if (orientation == 2) { //West
							if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.WEST)) {
								worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.WEST), 2);
								flag = true;
							}
						}
						else {
							if (orientation == 3) { //East
								if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.EAST)) {
									worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.EAST), 2);
									flag = true;
								}
							}
							else {
								if (orientation == 4) { //Up
									if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.UP)) {
										worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.UP), 2);
										flag = true;
									}
								}
								else {
									if (orientation == 5) { //Down
										if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.DOWN)) {
											worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.DOWN), 2);
											flag = true;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return flag;
	}

	public boolean generate(World worldIn, Random rand, BlockPos position, int altitude)
    {
        boolean flag = false;

        for (int i = 0; i < 32; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(3) - rand.nextInt(3), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(3) - rand.nextInt(3));

            if ((blockpos.getY() < worldIn.getSeaLevel()+altitude) && worldIn.isAirBlock(blockpos) && ((worldIn.getLight(blockpos) > 3) || (worldIn.canSeeSky(blockpos)))
            && (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockAncientMoss.block.canPlaceBlockAt(worldIn, blockpos))
            {
            	int orientation = rand.nextInt(6);
            	if (orientation == 0) { //North
            		if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.NORTH)) {
	                	worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 2);
	                	flag = true;
            		}
            	}
            	else {
            		if (orientation == 1) { //South
	            		if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.SOUTH)) {
		                	worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.SOUTH), 2);
		                	flag = true;
	            		}
	            	}
	            	else {
	            		if (orientation == 2) { //West
		            		if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.WEST)) {
			                	worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.WEST), 2);
			                	flag = true;
		            		}
		            	}
		            	else {
		            		if (orientation == 3) { //East
			            		if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.EAST)) {
				                	worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.EAST), 2);
				                	flag = true;
			            		}
			            	}
			            	else {
			            		if (orientation == 4) { //Up
				            		if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.UP)) {
					                	worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.UP), 2);
					                	flag = true;
				            		}
				            	}
				            	else {
				            		if (orientation == 5) { //Down
					            		if (BlockAncientMoss.block.canPlaceBlockOnSide(worldIn, blockpos, EnumFacing.DOWN)) {
						                	worldIn.setBlockState(blockpos, BlockAncientMoss.block.getDefaultState().withProperty(FACING, EnumFacing.DOWN), 2);
						                	flag = true;
					            		}
					            	}
				            	}
			            	}
		            	}
	            	}
            	}
            }
        }

        return flag;
    }
}
