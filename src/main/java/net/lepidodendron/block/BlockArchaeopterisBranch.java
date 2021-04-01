
package net.lepidodendron.block;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
//import net.minecraft.block.BlockChorusPlant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.List;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.block.state.BlockStateContainer;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.BlockDirectional;
import net.minecraft.util.BlockRenderLayer;

import net.lepidodendron.creativetab.TabLepidodendron;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockArchaeopterisLeaves;
import net.lepidodendron.block.BlockArchaeopterisLeavesPlaceable;
import net.lepidodendron.block.BlockArchaeopterisLeavesSmall;
import net.lepidodendron.block.BlockArchaeopterisLeavesSmallPlaceable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.IBlockAccess;
import net.minecraft.init.Blocks;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.item.ItemStack;

import net.minecraft.entity.item.EntityItem;


@ElementsLepidodendronMod.ModElement.Tag
public class BlockArchaeopterisBranch extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:archaeopteris_branch")
	public static final Block block = null;
	public BlockArchaeopterisBranch(ElementsLepidodendronMod instance) {
		super(instance, 286);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("archaeopteris_branch"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("lepidodendron:archaeopteris_branch", "inventory"));

	}

	public static class BlockCustom extends Block {

	public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");
    public static final PropertyBool TOPLEAVES = PropertyBool.create("topleaves");

    	public BlockCustom() {
			super(Material.WOOD);
			setTranslationKey("archaeopteris_branch");
			setSoundType(SoundType.WOOD);
			setHardness(1F);
			setResistance(10F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(TabLepidodendron.tab);
			
        	this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)).withProperty(UP, Boolean.valueOf(false)).withProperty(DOWN, Boolean.valueOf(false)).withProperty(TOPLEAVES, Boolean.valueOf(false)));
		}

		int[] surroundings;

		@Override
		public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos){
			
	    	Block block = worldIn.getBlockState(pos.down()).getBlock();
	        Block block1 = worldIn.getBlockState(pos.up()).getBlock();
	        Block block2 = worldIn.getBlockState(pos.north()).getBlock();
	        Block block3 = worldIn.getBlockState(pos.east()).getBlock();
	        Block block4 = worldIn.getBlockState(pos.south()).getBlock();
	        Block block5 = worldIn.getBlockState(pos.west()).getBlock();
	        
			Boolean attachmentblock = false;
			Boolean attachmentblock1 = false;
			Boolean attachmentblock2 = false;
			Boolean attachmentblock3 = false;
			Boolean attachmentblock4 = false;
			Boolean attachmentblock5 = false;
			Boolean topleaves = false;

			IBlockState blockState = worldIn.getBlockState(pos.down());
			if (block != this) {
				//Is the block it is standing on a full solid one?
            	if ((blockState.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID) 
                	|| blockState.isOpaqueCube() || (blockState.isFullBlock())) {
					attachmentblock = true;
				}
			}
			//Is the branch attached to a leaf with the correct orientation?
			if ((block == BlockArchaeopterisLeavesSmall.block) || (block == BlockArchaeopterisLeavesSmallPlaceable.block) || (block == BlockArchaeopterisLeaves.block) || (block == BlockArchaeopterisLeavesPlaceable.block)) {
				if (blockState.getValue(BlockDirectional.FACING) == EnumFacing.DOWN) {
					attachmentblock = true;
				}
	    	}

			IBlockState blockState1 = worldIn.getBlockState(pos.up());
			if (block != this) {
				//Is the block it is standing on a full solid one?
            	if ((blockState1.getBlockFaceShape(worldIn, pos.up(), EnumFacing.DOWN) == BlockFaceShape.SOLID) 
                	|| blockState1.isOpaqueCube() || (blockState1.isFullBlock())) {
					attachmentblock1 = true;
				}
			}
	    	//Is the branch attached to a leaf with the correct orientation?
			if ((block1 == BlockArchaeopterisLeavesSmall.block) || (block1 == BlockArchaeopterisLeavesSmallPlaceable.block) || (block1 == BlockArchaeopterisLeaves.block) || (block1 == BlockArchaeopterisLeavesPlaceable.block)) {
				if (blockState1.getValue(BlockDirectional.FACING) == EnumFacing.UP) {
					attachmentblock1 = true;
					topleaves = true;
				}
	    	}

			IBlockState blockState2 = worldIn.getBlockState(pos.north());
			if (block != this) {
				//Is the block it is standing on a full solid one?
            	if ((blockState2.getBlockFaceShape(worldIn, pos.north(), EnumFacing.SOUTH) == BlockFaceShape.SOLID) 
                	|| blockState2.isOpaqueCube() || (blockState2.isFullBlock())) {
					attachmentblock2 = true;
				}
			}
	    	//Is the branch attached to a leaf with the correct orientation?
			if ((block2 == BlockArchaeopterisLeavesSmall.block) || (block2 == BlockArchaeopterisLeavesSmallPlaceable.block) || (block2 == BlockArchaeopterisLeaves.block) || (block2 == BlockArchaeopterisLeavesPlaceable.block)) {
				if (blockState2.getValue(BlockDirectional.FACING) == EnumFacing.NORTH) {
					attachmentblock2 = true;
				}
	    	}

			IBlockState blockState3 = worldIn.getBlockState(pos.east());
			if (block != this) {
				//Is the block it is standing on a full solid one?
            	if ((blockState3.getBlockFaceShape(worldIn, pos.east(), EnumFacing.WEST) == BlockFaceShape.SOLID) 
                	|| blockState3.isOpaqueCube() || (blockState3.isFullBlock())) {
					attachmentblock3 = true;
				}
			}
	    	//Is the branch attached to a leaf with the correct orientation?
			if ((block3 == BlockArchaeopterisLeavesSmall.block) || (block3 == BlockArchaeopterisLeavesSmallPlaceable.block) || (block3 == BlockArchaeopterisLeaves.block) || (block3 == BlockArchaeopterisLeavesPlaceable.block)) {
				if (blockState3.getValue(BlockDirectional.FACING) == EnumFacing.EAST) {
					attachmentblock3 = true;
				}
	    	}

			IBlockState blockState4 = worldIn.getBlockState(pos.south());
			if (block != this) {
				//Is the block it is standing on a full solid one?
            	if ((blockState4.getBlockFaceShape(worldIn, pos.south(), EnumFacing.NORTH) == BlockFaceShape.SOLID) 
                	|| blockState4.isOpaqueCube() || (blockState4.isFullBlock())) {
					attachmentblock4 = true;
				}
			}
	    	//Is the branch attached to a leaf with the correct orientation?
			if ((block4 == BlockArchaeopterisLeavesSmall.block) || (block4 == BlockArchaeopterisLeavesSmallPlaceable.block) || (block4 == BlockArchaeopterisLeaves.block) || (block4 == BlockArchaeopterisLeavesPlaceable.block)) {
				if (blockState4.getValue(BlockDirectional.FACING) == EnumFacing.SOUTH) {
					attachmentblock4 = true;
				}
	    	}

			IBlockState blockState5 = worldIn.getBlockState(pos.west());
			if (block != this) {
				//Is the block it is standing on a full solid one?
            	if ((blockState5.getBlockFaceShape(worldIn, pos.west(), EnumFacing.EAST) == BlockFaceShape.SOLID) 
                	|| blockState5.isOpaqueCube() || (blockState5.isFullBlock())) {
					attachmentblock5 = true;
				}
			}
	    	//Is the branch attached to a leaf with the correct orientation?
			if ((block5 == BlockArchaeopterisLeavesSmall.block) || (block5 == BlockArchaeopterisLeavesSmallPlaceable.block) || (block5 == BlockArchaeopterisLeaves.block) || (block5 == BlockArchaeopterisLeavesPlaceable.block)) {
				if (blockState5.getValue(BlockDirectional.FACING) == EnumFacing.WEST) {
					attachmentblock5 = true;
				}
	    	}
			
	        return state.withProperty(DOWN, Boolean.valueOf(block == this || attachmentblock)).withProperty(UP, Boolean.valueOf(block1 == this || attachmentblock1)).withProperty(NORTH, Boolean.valueOf(block2 == this || attachmentblock2)).withProperty(EAST, Boolean.valueOf(block3 == this || attachmentblock3)).withProperty(SOUTH, Boolean.valueOf(block4 == this || attachmentblock4)).withProperty(WEST, Boolean.valueOf(block5 == this || attachmentblock5)).withProperty(TOPLEAVES, topleaves);
	    }

	    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	        state = state.getActualState(source, pos);
	        float f = 0.1875F;
	        float f1 = ((Boolean)state.getValue(WEST)).booleanValue() ? 0.0F : 0.1875F;
	        float f2 = ((Boolean)state.getValue(DOWN)).booleanValue() ? 0.0F : 0.1875F;
	        float f3 = ((Boolean)state.getValue(NORTH)).booleanValue() ? 0.0F : 0.1875F;
	        float f4 = ((Boolean)state.getValue(EAST)).booleanValue() ? 1.0F : 0.8125F;
	        float f5 = ((Boolean)state.getValue(UP)).booleanValue() ? 1.0F : 0.8125F;
	        float f6 = ((Boolean)state.getValue(SOUTH)).booleanValue() ? 1.0F : 0.8125F;
	        return new AxisAlignedBB((double)f1, (double)f2, (double)f3, (double)f4, (double)f5, (double)f6);
	    }
	
	    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
	    {
	        if (!isActualState)
	        {
	            state = state.getActualState(worldIn, pos);
	        }
	
	        float f = 0.1875F;
	        float f1 = 0.8125F;
	        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D));
	
	        if (((Boolean)state.getValue(WEST)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0D, 0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D));
	        }
	
	        if (((Boolean)state.getValue(EAST)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.8125D, 0.1875D, 0.1875D, 1.0D, 0.8125D, 0.8125D));
	        }
	
	        if (((Boolean)state.getValue(UP)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875D, 0.8125D, 0.1875D, 0.8125D, 1.0D, 0.8125D));
	        }
	
	        if (((Boolean)state.getValue(DOWN)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.1875D, 0.8125D));
	        }
	
	        if (((Boolean)state.getValue(NORTH)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875D, 0.1875D, 0.0D, 0.8125D, 0.8125D, 0.1875D));
	        }
	
	        if (((Boolean)state.getValue(SOUTH)).booleanValue())
	        {
	            addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D, 1.0D));
	        }
	    }
	
	    /**
	     * Convert the BlockState into the correct metadata value
	     */
	    public int getMetaFromState(IBlockState state)
	    {
	        return 0;
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

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}

		public boolean isFullCube(IBlockState state)
	    {
	        return false;
	    }
	
	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, SOUTH, WEST, UP, DOWN, TOPLEAVES});
	    }

		@Override
		public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
			return true;
		}

		@Override public boolean isWood(net.minecraft.world.IBlockAccess world, BlockPos pos){ 
				return true;
			}

		@Override
		public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
			int i = 4;
			int j = 5;

			if (worldIn.isAreaLoaded(pos.add(-5, -5, -5), pos.add(5, 5, 5))) {
				for (BlockPos blockpos : BlockPos.getAllInBox(pos.add(-4, -4, -4), pos.add(4, 4, 4))) {
					IBlockState iblockstate = worldIn.getBlockState(blockpos);

					if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos)) {
						iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
					}
				}
			}

			//Propagate the destruction if we have actually broken it (rather than a world-gen type replace event):
			if (worldIn.isAirBlock(pos))
			{
				Block block = worldIn.getBlockState(pos.down()).getBlock();
				Block block1 = worldIn.getBlockState(pos.up()).getBlock();
				Block block2 = worldIn.getBlockState(pos.north()).getBlock();
				Block block3 = worldIn.getBlockState(pos.east()).getBlock();
				Block block4 = worldIn.getBlockState(pos.south()).getBlock();
				Block block5 = worldIn.getBlockState(pos.west()).getBlock();

				if (block == this) {
					worldIn.destroyBlock(pos.down(), true);
				}
				if (block1 == this) {
					worldIn.destroyBlock(pos.up(), true);
				}
				if (block2 == this) {
					worldIn.destroyBlock(pos.north(), true);
				}
				if (block3 == this) {
					worldIn.destroyBlock(pos.east(), true);
				}
				if (block4 == this) {
					worldIn.destroyBlock(pos.south(), true);
				}
				if (block5 == this) {
					worldIn.destroyBlock(pos.west(), true);
				}
			}

	    }
	    
 		@Override
		public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
			super.neighborChanged(state, world, pos, neighborBlock, fromPos);
			
			//if (world.isAirBlock(pos.down()) && fromPos.getY() == pos.getY()-1) {
			//	world.destroyBlock(pos, true);
			//}
				
		}
		

		@Override
		public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
			return BlockFaceShape.UNDEFINED;
		}
		
	}	

}
