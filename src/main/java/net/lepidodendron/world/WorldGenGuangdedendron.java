package net.lepidodendron.world;

import java.util.Random;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;


import net.lepidodendron.block.BlockGuangdedendron;
import net.lepidodendron.block.BlockGuangdedendronTopNospore;
import net.lepidodendron.block.BlockGuangdedendronTop;

public class WorldGenGuangdedendron extends WorldGenerator
{
	
	public static final Block block = null;

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        boolean flag = false;

        for (int i = 0; i < 100; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockGuangdedendron.block.canPlaceBlockAt(worldIn, blockpos))
            {
            
				BlockPos blockpos2 = blockpos.down();
				int j = 1 + rand.nextInt(rand.nextInt(7) + 1);
				j = Math.min(7, j);
				for (int k = 0; k <= j; ++k){
					if (BlockGuangdedendron.block.canPlaceBlockAt(worldIn, blockpos)) {
						if (k != j) {worldIn.setBlockState(blockpos.up(k), BlockGuangdedendron.block.getDefaultState(), 2);}
						if ((k == j) 
							&& ((worldIn.isAirBlock(blockpos.up(k))) || (worldIn.getBlockState(blockpos.up(k)).getBlock() == BlockGuangdedendronTopNospore.block))
							) {
							//System.err.println("k value: " + k);
							if (k >= 7) {
								worldIn.setBlockState(blockpos.up(k), BlockGuangdedendronTop.block.getDefaultState(), 2);
							}
							else {
								worldIn.setBlockState(blockpos.up(k), BlockGuangdedendronTopNospore.block.getDefaultState(), 2);
							}
						}
					}
				}

                flag = true;
            }
        }

        return flag;
    }
}



