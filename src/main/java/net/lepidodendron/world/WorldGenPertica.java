package net.lepidodendron.world;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.lepidodendron.block.BlockPertica;
import net.lepidodendron.block.BlockPerticaSpore;

public class WorldGenPertica extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        boolean flag = false;

        for (int i = 0; i < 100; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockPertica.block.canPlaceBlockAt(worldIn, blockpos))
            {
            	if (Math.random() < 0.7) {
					worldIn.setBlockState(blockpos, BlockPertica.block.getDefaultState(), 2);
				}
				else {
					worldIn.setBlockState(blockpos, BlockPerticaSpore.block.getDefaultState(), 2);
				}
                flag = true;
            }
        }

        return flag;
    }
}
