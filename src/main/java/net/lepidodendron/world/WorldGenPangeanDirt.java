package net.lepidodendron.world;

import net.lepidodendron.block.BlockCoarseSandyDirtPangaean;
import net.lepidodendron.block.BlockGlossopterisLeafLitter;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenPangeanDirt extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        boolean flag = false;

        for (int i = 0; i < 128; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if ((!worldIn.provider.isNether() || blockpos.getY() < 254) && worldIn.isAirBlock(blockpos)
            	&& (
            		((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.GROUND)
            		|| ((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.GRASS)
            		|| ((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.SAND)
                    || ((worldIn.getBlockState(blockpos.down())).getBlock() == BlockGlossopterisLeafLitter.block)
            	)
            )
            {
                worldIn.setBlockState(blockpos.down(), BlockCoarseSandyDirtPangaean.block.getDefaultState(), 2);
                flag = true;
            }
        }

        return flag;
    }
}
