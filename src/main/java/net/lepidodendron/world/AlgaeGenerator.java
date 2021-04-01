package net.lepidodendron.world;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.lepidodendron.LepidodendronConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraft.world.biome.Biome;

public class AlgaeGenerator extends WorldGenerator
{
	private Block algae;
    private IBlockState state;

    public AlgaeGenerator(Block algaeIn)
    {
        this.setGeneratedBlock(algaeIn);
    }

    public void setGeneratedBlock(Block algaeIn)
    {
        this.algae = algaeIn;
        this.state = algaeIn.getDefaultState();
    }

    //marine int: -1  = non-marine, 0 = bothk 1 = marine only
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
		int dimID = worldIn.provider.getDimension();
    	boolean dimensionCriteria = false;
		if (shouldGenerateInDimension(dimID, LepidodendronConfig.dimAlgae))
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

            if (this.algae.canPlaceBlockOnSide(worldIn, new BlockPos(j, k, l), EnumFacing.UP)
            && ((worldIn.getBlockState(new BlockPos(j, k - 1, l)).getMaterial() == Material.SAND)
				|| (worldIn.getBlockState(new BlockPos(j, k - 1, l)).getMaterial() == Material.ROCK)
				|| (worldIn.getBlockState(new BlockPos(j, k - 1, l)).getMaterial() == Material.GROUND)
				|| (worldIn.getBlockState(new BlockPos(j, k - 1, l)).getMaterial() == Material.CLAY)
				|| (worldIn.getBlockState(new BlockPos(j, k - 1, l)).getMaterial() == Material.GLASS)
				|| (worldIn.getBlockState(new BlockPos(j, k - 1, l)).getMaterial() == Material.IRON)
				|| (worldIn.getBlockState(new BlockPos(j, k - 1, l)).getMaterial() == Material.WOOD)))
            {
                worldIn.setBlockState(new BlockPos(j, k, l), this.state, 2);
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
