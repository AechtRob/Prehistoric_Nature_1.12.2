package net.lepidodendron.world;

import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockRottenLog;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenTreeRottenLog extends WorldGenerator
{
    private final Block block;

    public WorldGenTreeRottenLog()
    {
        super(false);
        this.block = BlockRottenLog.block;
    }

	public static final PropertyDirection FACING = BlockDirectional.FACING;
		
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	boolean flag = false;
    	BlockPos blockpos = position;

		Material material = worldIn.getBlockState(blockpos.down()).getMaterial();

        if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254)
        && (material == Material.GROUND || material == Material.GRASS || material == Material.SAND || material == Material.ROCK))
        {
			if (Math.random() > 0.8) { //Standing log
				worldIn.setBlockState(blockpos, this.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 4);
				PlaceEggs(rand, worldIn, blockpos);
				worldIn.setBlockState(blockpos.up(), this.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 4);
				if (Math.random() > 0.5) {
					worldIn.setBlockState(blockpos.up(2), this.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 4);
					if (Math.random() > 0.5) {
						worldIn.setBlockState(blockpos.up().east(), this.block.getDefaultState().withProperty(FACING, EnumFacing.UP), 4);
						PlaceEggs(rand, worldIn, blockpos.up().east());
					}
					if (Math.random() > 0.6) {
						worldIn.setBlockState(blockpos.up(3), this.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 4);
					}
				}
			}
				else {
				int direction = rand.nextInt(2);
				if (direction == 1) {
					//North-South
					worldIn.setBlockState(blockpos, this.block.getDefaultState().withProperty(FACING, EnumFacing.EAST), 4);
					PlaceEggs(rand, worldIn, blockpos);
					worldIn.setBlockState(blockpos.north(), this.block.getDefaultState().withProperty(FACING, EnumFacing.EAST), 4);
					PlaceEggs(rand, worldIn, blockpos.north());
					if (Math.random() > 0.5 && !worldIn.isAirBlock(blockpos.down().north(2)) ) {
						worldIn.setBlockState(blockpos.north(2), this.block.getDefaultState().withProperty(FACING, EnumFacing.EAST), 4);
						PlaceEggs(rand, worldIn, blockpos.north(2));
						if (Math.random() > 0.5) {
							worldIn.setBlockState(blockpos.north().up(), this.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 4);
							PlaceEggs(rand, worldIn, blockpos.north().up());
						}
						if (Math.random() > 0.6 && !worldIn.isAirBlock(blockpos.down().north(3)) ) {
							worldIn.setBlockState(blockpos.north(3), this.block.getDefaultState().withProperty(FACING, EnumFacing.EAST), 4);
							PlaceEggs(rand, worldIn, blockpos.north(3));
						}
					}
				}
				else {
					//East-West
					worldIn.setBlockState(blockpos, this.block.getDefaultState().withProperty(FACING, EnumFacing.UP), 4);
					PlaceEggs(rand, worldIn, blockpos);
					worldIn.setBlockState(blockpos.east(), this.block.getDefaultState().withProperty(FACING, EnumFacing.UP), 4);
					PlaceEggs(rand, worldIn, blockpos.east());
					if (Math.random() > 0.5 && !worldIn.isAirBlock(blockpos.down().east(2))) {
						worldIn.setBlockState(blockpos.east(2), this.block.getDefaultState().withProperty(FACING, EnumFacing.UP), 4);
						PlaceEggs(rand, worldIn, blockpos.east(2));
						if (Math.random() > 0.5) {
							worldIn.setBlockState(blockpos.east().up(), this.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 4);
							PlaceEggs(rand, worldIn, blockpos.east().up());
						}
						if (Math.random() > 0.6 && !worldIn.isAirBlock(blockpos.down().east(3)) ) {
							worldIn.setBlockState(blockpos.east(3), this.block.getDefaultState().withProperty(FACING, EnumFacing.UP), 4);
							PlaceEggs(rand, worldIn, blockpos.east(3));
						}
					}
					
				}
			}
			
            flag = true;
        }
            
        return flag;
    }

    public void PlaceEggs(Random rand, World worldIn, BlockPos pos) {
    	if (Math.random() > 0.925) {
			int dimID = worldIn.provider.getDimension();
    		String stringEgg = "";
    		int i;

    		if (dimID == LepidodendronConfig.dimDevonian) {
    			i = rand.nextInt(4); //Attercopus, Trigonotarbid, Pneumodesmus or Eoarthropleura
				if (i == 0) {stringEgg = LepidodendronMod.MODID + ":insect_eggs_trigonotarbid_dev";}
				if (i == 1) {stringEgg = LepidodendronMod.MODID + ":insect_eggs_eoarthropleura";}
				if (i == 2) {stringEgg = LepidodendronMod.MODID + ":insect_eggs_pneumodesmus";}
				if (i == 3) {stringEgg = LepidodendronMod.MODID + ":insect_eggs_attercopus";}
			}

			if (dimID == LepidodendronConfig.dimCarboniferous) {
				i = rand.nextInt(2); //Trigonotarbid or Hylonomus
				if (i == 0) {stringEgg = LepidodendronMod.MODID + ":insect_eggs_trigonotarbid_carb";}
				if (i == 1) {stringEgg = LepidodendronMod.MODID + ":hylonomus_eggs";}
				//if (i == 1) {stringEgg = LepidodendronMod.MODID + ":hylonomus_eggs";}
			}

			//if (dimID == LepidodendronConfig.dimPermianWetlands) {
			//	i = rand.nextInt(2); //Permian Trigonotarbid
			//	if (i == 0) {stringEgg = LepidodendronMod.MODID + ":insect_eggs_trigonotarbid_perm";}
			//	if (i == 1) {stringEgg = LepidodendronMod.MODID + ":insect_eggs_trigonotarbid_perm";}
			//}

			TileEntity te = worldIn.getTileEntity(pos);
			if (te != null) {
				te.getTileData().setString("egg", stringEgg);
			}
			IBlockState state = worldIn.getBlockState(pos);
			worldIn.notifyBlockUpdate(pos, state, state, 3);
			//System.err.println("Eggs placed: " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
		}
	}
}

