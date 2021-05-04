package net.lepidodendron.world;

import java.util.Random;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.lepidodendron.block.BlockMedullosalesSapling;
import net.lepidodendron.block.BlockMedullosalesLog;
import net.lepidodendron.procedure.ProcedureWorldGenMedullosales;
import java.util.Map;
import java.util.HashMap;


public class WorldGenMedullosales extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        boolean flag = false;

        for (int i = 0; i < 50; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (blockpos.getY() >= worldIn.getSeaLevel()-4 && worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockMedullosalesSapling.block.canPlaceBlockAt(worldIn, blockpos)
            	&& (worldIn.getBlockState(blockpos.east()).getBlock() != BlockMedullosalesLog.block)
            	&& (worldIn.getBlockState(blockpos.west()).getBlock() != BlockMedullosalesLog.block)
            	&& (worldIn.getBlockState(blockpos.north()).getBlock() != BlockMedullosalesLog.block)
            	&& (worldIn.getBlockState(blockpos.south()).getBlock() != BlockMedullosalesLog.block)
            	)
            {
                java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("x", blockpos.getX());
					$_dependencies.put("y", blockpos.getY());
					$_dependencies.put("z", blockpos.getZ());
					$_dependencies.put("world", worldIn);
				ProcedureWorldGenMedullosales.executeProcedure($_dependencies);
                flag = true;
            }
        }

        return flag;
    }
}
