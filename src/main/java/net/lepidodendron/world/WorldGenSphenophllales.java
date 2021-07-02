package net.lepidodendron.world;

import net.lepidodendron.block.BlockSphenophyllales1;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSphenophllales extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        boolean flag = false;

        for (int i = 0; i < 25; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (blockpos.getY() >= worldIn.getSeaLevel()-4 && worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockSphenophyllales1.block.canPlaceBlockAt(worldIn, blockpos))
            {
                worldIn.setBlockState(blockpos, BlockSphenophyllales1.block.getDefaultState(), 2);
                flag = true;
            }
        }

        return flag;
    }
}
