
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronPlants;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.item.EntityItem;
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
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockTreefernBlackShootPlaceable extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:black_treefern_shoot")
	public static final Block block = null;
	public BlockTreefernBlackShootPlaceable(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.black_treefern_shoot);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("black_treefern_shoot"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:black_treefern_shoot", "inventory"));
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockLeaves.DECAYABLE, BlockLeaves.CHECK_DECAY).build());
	}
	
	public static class BlockCustom extends BlockLeaves {
		
		public static final PropertyDirection FACING = BlockHorizontal.FACING;
		
		public BlockCustom() {
			super();
			setTranslationKey("pf_black_treefern_shoot");
			setSoundType(SoundType.PLANT);
			setHardness(0.2F);
			setResistance(0.2F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(TabLepidodendronPlants.tab);
			this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
		}

		@Override
		public BlockPlanks.EnumType getWoodType(int meta) {
			return null;
		}

		@Override
		public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
			return NonNullList.withSize(1, new ItemStack(BlockTreefernBlackShootPlaceable.block, (int) (1)));
		}

		@Override
		protected net.minecraft.block.state.BlockStateContainer createBlockState() {
			return new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{CHECK_DECAY, DECAYABLE});
		}

		public IBlockState getStateFromMeta(int meta) {
			return this.getDefaultState().withProperty(DECAYABLE, (meta & 1) != 0).withProperty(CHECK_DECAY, (meta & 2) != 0);
		}

		public int getMetaFromState(IBlockState state) {
			int i = 0;
			if (!(Boolean) state.getValue(DECAYABLE))
				i |= 1;
			if ((Boolean) state.getValue(CHECK_DECAY))
				i |= 2;
			return i;
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

		@Override
		public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
			return 60;
		}

		@Override
		public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
			return 30;
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockTreefernBlackShootPlaceable.block, (int) (1));
		}

		@Override
		public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos pos) {
			return MapColor.FOLIAGE;
		}

		@Override
		protected int getSaplingDropChance(IBlockState state) {
			return 1;
		}

		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune) {
			if (LepidodendronConfig.doSpores) {
				// Drop air and use the spores method instead:
				return new ItemStack(Blocks.AIR, (int) (1)).getItem();
			}
			else {
				return Item.getItemFromBlock(BlockTreefernBlackSapling.block);
			}
		}

		public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        	return false;
    	}

		@Override
		protected boolean canSilkHarvest()
	    {
	        return true;
	    }

	    @Override
        public ItemStack getSilkTouchDrop(IBlockState state)  {
            return new ItemStack(BlockTreefernBlackShootPlaceable.block, (int) (1));
        }

	    @Override
		public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
			
			super.neighborChanged(state, world, pos, neighborBlock, fromPos);

			Block block = world.getBlockState(pos.up()).getBlock();
			if (block != BlockTreefernBlackShoot02.block) {
				world.setBlockToAir(pos);
				
				//Small chance of an additional sapling:
				if ((Math.random() > 0.8) && (!LepidodendronConfig.doSpores)) {
					//Spawn another sapling:
					if (!world.isRemote) {
						EntityItem entityToSpawn = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(BlockTreefernBlackSapling.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.spawnEntity(entityToSpawn);
					}
				}
			}
		}

		@Override
		public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
			
			boolean TreeCheck = true;
			//Check there is space:
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			int counter;
			counter = -3;
			while (counter <= 3 && TreeCheck) {
				if (!worldIn.isAirBlock(new BlockPos((int) x + counter, (int) y + 2, (int) z))) TreeCheck = false;
				counter = counter + 1;
			}
			counter = -3;
			while (counter <= 3 && TreeCheck) {
				if (!worldIn.isAirBlock(new BlockPos((int) x, (int) y + 2, (int) z + counter))) TreeCheck = false;
				counter = counter + 1;
			}
			counter = -3;
			while (counter <= 3 && TreeCheck) {
				if (!worldIn.isAirBlock(new BlockPos((int) x + counter, (int) y + 3, (int) z))) TreeCheck = false;
				counter = counter + 1;
			}
			counter = -3;
			while (counter <= 3 && TreeCheck) {
				if (!worldIn.isAirBlock(new BlockPos((int) x, (int) y + 3, (int) z + counter))) TreeCheck = false;
				counter = counter + 1;
			}
	        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up()) && worldIn.isAirBlock(pos.up(2)) && worldIn.isAirBlock(pos.up(3)) && TreeCheck;
	    }

	    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
			world.setBlockState(pos.up(), BlockTreefernBlackShoot02.block.getDefaultState(), 3);
			world.setBlockState(pos.up(2), BlockTreefernBlackShoot03.block.getDefaultState(), 3);

			world.setBlockState((pos.up()).north(2), BlockTreefernBlackShootSide02.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 3);
			world.setBlockState((pos.up()).south(2), BlockTreefernBlackShootSide02.block.getDefaultState().withProperty(FACING, EnumFacing.SOUTH), 3);
			world.setBlockState((pos.up()).east(2), BlockTreefernBlackShootSide02.block.getDefaultState().withProperty(FACING, EnumFacing.EAST), 3);
			world.setBlockState((pos.up()).west(2), BlockTreefernBlackShootSide02.block.getDefaultState().withProperty(FACING, EnumFacing.WEST), 3);
			
			world.setBlockState((pos.up(2)).north(2), BlockTreefernBlackShootSide03.block.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 3);
			world.setBlockState((pos.up(2)).south(2), BlockTreefernBlackShootSide03.block.getDefaultState().withProperty(FACING, EnumFacing.SOUTH), 3);
			world.setBlockState((pos.up(2)).east(2), BlockTreefernBlackShootSide03.block.getDefaultState().withProperty(FACING, EnumFacing.EAST), 3);
			world.setBlockState((pos.up(2)).west(2), BlockTreefernBlackShootSide03.block.getDefaultState().withProperty(FACING, EnumFacing.WEST), 3);

			super.onBlockAdded(world, pos, state);
			
	    }

	   	@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		}

		@Override
	    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return BlockFaceShape.UNDEFINED;
	    }

		@Override
	    public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
	    {
	        return true;
	    }

	}
}
