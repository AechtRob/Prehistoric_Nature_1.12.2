
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.creativetab.TabLepidodendron;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;


@ElementsLepidodendronMod.ModElement.Tag
public class BlockStromatoliteSticky extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:stromatolite_sticky")
	public static final Block block = null;
	public BlockStromatoliteSticky(ElementsLepidodendronMod instance) {
		super(instance, 1409);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("stromatolite_sticky"));
		//elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	public static final PropertyBool TOPSHOOT = PropertyBool.create("topshoot");
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool WEST = PropertyBool.create("west");
	
	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.ROCK);
			setTranslationKey("pf_stromatolite_sticky");
			setSoundType(SoundType.STONE);
			setHarvestLevel("pickaxe", 1);
			setHardness(1.5F);
			setResistance(6F);
			setLightLevel(0F);
			setLightOpacity(255);
			setCreativeTab(null);
			setDefaultSlipperiness(0.7f);
			useNeighborBrightness = true;
			this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, false).withProperty(SOUTH, false).withProperty(EAST, false).withProperty(WEST, false).withProperty(TOPSHOOT, false));
		}
		
		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(new ItemStack(Blocks.GRAVEL, (int) (1)));
		}

		@Override
		protected boolean canSilkHarvest()
	    {
	        return true;
	    }

		@Override
		public ItemStack getSilkTouchDrop(IBlockState state)  {
			return new ItemStack(BlockStromatolite.block, (int) (1));
		}

		public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return BlockFaceShape.SOLID;
	    }

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockStromatolite.block, (int) (1));
		}

	    @SideOnly(Side.CLIENT)
		@Override
    	public BlockRenderLayer getRenderLayer()
		{
			return BlockRenderLayer.CUTOUT;
		}
		
		@Override
		public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
			return layer == BlockRenderLayer.CUTOUT_MIPPED;
		}

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {TOPSHOOT, NORTH, SOUTH, EAST, WEST});
	    }

	    @Override
		public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos){

			if (worldIn.getBlockState(pos).getBlock() != this) {
				return state;
			}
			boolean boolNorth = false;
			boolean boolSouth = false;
			boolean boolEast = false;
			boolean boolWest = false;

			if (((worldIn.getBlockState(pos.north()).getBlock() == this)
					|| worldIn.getBlockState(pos.north()).isFullCube()
					|| worldIn.getBlockState(pos.north()).isOpaqueCube())
				&& ((!worldIn.getBlockState(pos.north()).getMaterial().isLiquid()))
				&& ((!worldIn.isAirBlock(pos.north())))) {
				boolNorth = true;
			}
			if (((worldIn.getBlockState(pos.south()).getBlock() == this)
					|| worldIn.getBlockState(pos.south()).isFullCube()
					|| worldIn.getBlockState(pos.south()).isOpaqueCube())
					&& ((!worldIn.getBlockState(pos.south()).getMaterial().isLiquid()))
					&& ((!worldIn.isAirBlock(pos.south())))) {
				boolSouth = true;
			}
			if (((worldIn.getBlockState(pos.east()).getBlock() == this)
					|| worldIn.getBlockState(pos.east()).isFullCube()
					|| worldIn.getBlockState(pos.east()).isOpaqueCube())
					&& ((!worldIn.getBlockState(pos.east()).getMaterial().isLiquid()))
					&& ((!worldIn.isAirBlock(pos.east())))) {
				boolEast = true;
			}
			if (((worldIn.getBlockState(pos.west()).getBlock() == this)
					|| worldIn.getBlockState(pos.west()).isFullCube()
					|| worldIn.getBlockState(pos.west()).isOpaqueCube())
					&& ((!worldIn.getBlockState(pos.west()).getMaterial().isLiquid()))
					&& ((!worldIn.isAirBlock(pos.west())))) {
				boolWest = true;
			}

			return state.withProperty(NORTH, boolNorth).withProperty(SOUTH, boolSouth).withProperty(EAST, boolEast).withProperty(WEST, boolWest).withProperty(TOPSHOOT, (worldIn.getBlockState(pos.up()).getBlock() != this));
		}

		//Trigger state change block face geometry updates to attached blocks:
		@Override
		public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
			super.neighborChanged(state, world, pos, neighborBlock, fromPos);
			world.setBlockState(pos, state.withProperty(TOPSHOOT,getActualState(state, world, pos).getValue(TOPSHOOT)), 3);
		}

		public IBlockState getStateFromMeta(int meta)
	    {
	        return this.getDefaultState();
	    }

	    public int getMetaFromState(IBlockState state)
	    {
	        return 0;
	    }

	}
}