package net.lepidodendron.world;

import java.util.Random;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.lepidodendron.block.BlockStauropteris;

public class WorldGenPuddles extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        boolean flag = false;

        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) 
            	&& !worldIn.isAirBlock(blockpos.down(2)) && !worldIn.isAirBlock(blockpos.down().east()) 
            	&& !worldIn.isAirBlock(blockpos.down().west()) 
            	&& !worldIn.isAirBlock(blockpos.down().south()) && !worldIn.isAirBlock(blockpos.down().north()) 
            	&& BlockStauropteris.block.canPlaceBlockAt(worldIn, blockpos)
            )
            {
                worldIn.setBlockState(blockpos.down(), Blocks.FLOWING_WATER.getDefaultState(), 2);
                flag = true;
            }
        }

        return flag;
    }
}
