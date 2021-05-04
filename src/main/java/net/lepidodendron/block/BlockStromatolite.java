
package net.lepidodendron.block;

import net.lepidodendron.item.ItemBottleOfResin;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenReed;

import net.minecraft.world.IBlockAccess;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.init.Blocks;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.properties.IProperty;
import net.minecraft.world.World;

import net.lepidodendron.creativetab.TabLepidodendron;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;
import java.util.Random;


@ElementsLepidodendronMod.ModElement.Tag
public class BlockStromatolite extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:stromatolite")
	public static final Block block = null;
	public BlockStromatolite(ElementsLepidodendronMod instance) {
		super(instance, 1409);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("stromatolite"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:stromatolite", "inventory"));
	}

	@Override
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		
		boolean dimensionCriteria = false;
		if (shouldGenerateInDimension(dimID, LepidodendronConfig.dimStromatolite))
			dimensionCriteria = true;
		if (dimID == LepidodendronConfig.dimOrdovicianSilurian
			|| dimID == LepidodendronConfig.dimCambrian
			|| dimID == LepidodendronConfig.dimPrecambrian) {
			dimensionCriteria = true;
		}
		if (!dimensionCriteria)
			return;

		boolean biomeCriteria = false;
		Biome biome = world.getBiome(new BlockPos(chunkX, world.getSeaLevel(), chunkZ));
		if (!matchBiome(biome, LepidodendronConfig.genStromatoliteBlacklistBiomes)) {
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OCEAN))
				biomeCriteria = true;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.BEACH))
				biomeCriteria = true;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.DEAD))
				biomeCriteria = false;
		}
		if (matchBiome(biome, LepidodendronConfig.genStromatoliteOverrideBiomes))
			biomeCriteria = true;
			
		if (dimID == LepidodendronConfig.dimOrdovicianSilurian) {
			biomeCriteria = true;
		}
		if (dimID == LepidodendronConfig.dimCambrian) {
			biomeCriteria = true;
		}
		if (dimID == LepidodendronConfig.dimPrecambrian) {
			biomeCriteria = true;
		}

		if (!biomeCriteria)
			return;

		double genChance = 0.99;
		if (dimID == LepidodendronConfig.dimOrdovicianSilurian) {genChance = 0.65;}
		if (dimID == LepidodendronConfig.dimCambrian) {genChance = 0.8;}
		if (dimID == LepidodendronConfig.dimPrecambrian) {genChance = 0.9;}
		
		if (Math.random() > genChance) {
			for (int i = 0; i < 10; i++) {
				int l6 = chunkX + random.nextInt(16) + 8;
				int i11 = random.nextInt(128);
				if (dimID != LepidodendronConfig.dimPrecambrian) {i11 = Math.max(1, random.nextInt(7) - 5 + world.getSeaLevel());}
				int l14 = chunkZ + random.nextInt(16) + 8;

				(new WorldGenReed() {
					@Override
					public boolean generate(World world, Random random, BlockPos pos) {
						int c = 14;
						if (dimID == LepidodendronConfig.dimPrecambrian) {c = 32;}
						for (int i = 0; i < c; ++i) {
							BlockPos blockpos1 = pos.add(random.nextInt(6) - random.nextInt(6), 0, random.nextInt(6) - random.nextInt(6));
							//BlockPos blockpos2 = blockpos1.down();

							if (dimID != LepidodendronConfig.dimPrecambrian) {
								int j = random.nextInt(3) + 1;
								j = Math.min(3, j);
								for (int k = 0; k < j; ++k) {
									//System.err.println("SeaLavel: " + (world.getSeaLevel()));
									//System.err.println("Try to spawn at: "  + blockpos1.up(k).getY());
									if (
										//((world.isAirBlock(blockpos1.down()) || ((BlockFalling) block).canFallThrough(world.getBlockState(blockpos1.down()))) && blockpos1.getY() >= 0)
										//&& ((world.isAirBlock(blockpos1.up(k).down()) || ((BlockFalling) block).canFallThrough(world.getBlockState(blockpos1.up(k).down()))) && blockpos1.up(k).getY() >= 0)
											((world.getBlockState(blockpos1.down()).getMaterial() == Material.SAND)
													|| (world.getBlockState(blockpos1.down()).getMaterial() == Material.ROCK)
													|| (world.getBlockState(blockpos1.down()).getBlock() == block))
													&& ((world.getBlockState(blockpos1.up(k).down()).getMaterial() == Material.SAND)
													|| (world.getBlockState(blockpos1.up(k).down()).getMaterial() == Material.ROCK)
													|| (world.getBlockState(blockpos1.up(k).down()).getBlock() == block))
													&& (blockpos1.up(k).getY() >= (world.getSeaLevel() - 5))
													&& (blockpos1.up(k).getY() <= (world.getSeaLevel()))
													&& ((world.isAirBlock(blockpos1.up(k)))
													|| (world.getBlockState(blockpos1.up(k)).getMaterial() == Material.WATER))
													&&
													!((world.getBlockState(blockpos1.up(k).down()).getBlock() == block)
															&& blockpos1.up(k).getY() == (world.getSeaLevel()))
									) {
										world.setBlockState(blockpos1.up(k), block.getDefaultState(), 2);
									}
								}
							}
							else {
								if ( //Precambrian generation is different:
									//((world.isAirBlock(blockpos1.down()) || ((BlockFalling) block).canFallThrough(world.getBlockState(blockpos1.down()))) && blockpos1.getY() >= 0)
									//&& ((world.isAirBlock(blockpos1.up(k).down()) || ((BlockFalling) block).canFallThrough(world.getBlockState(blockpos1.up(k).down()))) && blockpos1.up(k).getY() >= 0)
										((world.getBlockState(blockpos1.down()).getMaterial() == Material.SAND)
											|| (world.getBlockState(blockpos1.down()).getMaterial() == Material.ROCK)
											|| (world.getBlockState(blockpos1.down()).getMaterial() == Material.GROUND)
											|| (world.getBlockState(blockpos1.down()).getBlock() == BlockStromatolite.block))
											&& (world.getBlockState(blockpos1).getMaterial() == Material.WATER)
											&& (world.getBlockState(blockpos1.up()).getMaterial() == Material.WATER)
								)
								{
									world.setBlockState(blockpos1, block.getDefaultState(), 2);
									if (Math.random() > 0.55) {
										blockpos1 = blockpos1.up();
										if ((world.getBlockState(blockpos1).getMaterial() == Material.WATER)
											&& (world.getBlockState(blockpos1.up()).getMaterial() == Material.WATER)
										) {
											world.setBlockState(blockpos1, block.getDefaultState(), 2);
											if (Math.random() > 0.8) {
												blockpos1 = blockpos1.up();
												if ((world.getBlockState(blockpos1).getMaterial() == Material.WATER)
														&& (world.getBlockState(blockpos1.up()).getMaterial() == Material.WATER)
												) {
													world.setBlockState(blockpos1, block.getDefaultState(), 2);
													if (Math.random() > 0.8) {
														blockpos1 = blockpos1.up();
														if ((world.getBlockState(blockpos1).getMaterial() == Material.WATER)
																&& (world.getBlockState(blockpos1.up()).getMaterial() == Material.WATER)
														) {
															world.setBlockState(blockpos1, block.getDefaultState(), 2);
														}
													}
												}
											}
										}
									}
								}
							}
						}
						return true;
					}
				}).generate(world, random, new BlockPos(l6, i11, l14));
			}
		}
	}

	public static final PropertyBool TOPSHOOT = PropertyBool.create("topshoot");
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool WEST = PropertyBool.create("west");
	
	public static class BlockCustom extends BlockFalling {
		public BlockCustom() {
			super(Material.ROCK);
			setTranslationKey("pf_stromatolite");
			setSoundType(SoundType.STONE);
			setHarvestLevel("pickaxe", 1);
			setHardness(1.5F);
			setResistance(6F);
			setLightLevel(0F);
			setLightOpacity(255);
			setCreativeTab(TabLepidodendron.tab);
			setDefaultSlipperiness(0.7f);
			useNeighborBrightness = true;
			this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, false).withProperty(SOUTH, false).withProperty(EAST, false).withProperty(WEST, false).withProperty(TOPSHOOT, true));
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

		public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	    {
	        return BlockFaceShape.SOLID;
	    }

		//@Override
		//public boolean isOpaqueCube(IBlockState state) {
		//	return false;
		//}

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

		@Override
		public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entity, EnumHand hand, EnumFacing direction,
										float hitX, float hitY, float hitZ) {

			if (entity.getHeldItemMainhand().getItem() == new ItemStack(ItemBottleOfResin.block, (int) (1)).getItem()) {
				SoundEvent soundevent = SoundEvents.BLOCK_SLIME_PLACE;
				entity.getEntityWorld().playSound(entity, entity.getPosition(), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
				entity.inventory.clearMatchingItems(new ItemStack(ItemBottleOfResin.block, (int) (1)).getItem(), -1, (int) 1, null);
				ItemStack _setstack = new ItemStack(Items.GLASS_BOTTLE, (int) (1));
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(entity, _setstack);
				world.setBlockState(pos, BlockStromatoliteSticky.block.getDefaultState());
				return true;
			}
			return super.onBlockActivated(world, pos, state, entity, hand, direction, hitX, hitY, hitZ);
		}

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

 	public static boolean matchBiome(Biome biome, String[] biomesList) {
    	
    	//String regName = biome.getRegistryName().toString();
    	
        String[] var2 = biomesList;
        int var3 = biomesList.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String checkBiome = var2[var4];
            if (!checkBiome.contains(":")) {
            	//System.err.println("modid test: " + biome.getRegistryName().toString().substring(0, biome.getRegistryName().toString().indexOf(":") - 1));
	            if (checkBiome.equalsIgnoreCase(
	            	biome.getRegistryName().toString().substring(0, biome.getRegistryName().toString().indexOf(":"))
	            	)) {
	                return true;
	            }
        	}
        	if (checkBiome.equalsIgnoreCase(biome.getRegistryName().toString())) {
                return true;
            }
        }

        return false;
    }
}
