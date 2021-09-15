
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronDecorationHandler;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronPlants;
import net.lepidodendron.item.ItemReticulopterisSeeds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
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
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockReticulopteris extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:reticulopteris")
	public static final Block block = null;
	public BlockReticulopteris(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.reticulopteris);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustomFlower());
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:reticulopteris", "inventory"));
	}

	@Override
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		boolean dimensionCriteria = false;
		boolean isNetherType = false;
		if (shouldGenerateInDimension(dimID, LepidodendronConfig.dimReticulopteris))
			dimensionCriteria = true;
		if (!LepidodendronConfig.genReticulopteris && !LepidodendronConfig.genAllPlants)
			dimensionCriteria = false;
		if (LepidodendronConfig.dimCarboniferous == dimID)
			dimensionCriteria = true;
		if (!dimensionCriteria)
			return;

		boolean biomeCriteria = false;
		Biome biome = world.getBiome(new BlockPos(chunkX, 128, chunkZ));
		if ((!matchBiome(biome, LepidodendronConfig.genGlobalBlacklist)) && (!matchBiome(biome, LepidodendronConfig.genReticulopterisBlacklistBiomes))) {
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER))
				biomeCriteria = true;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE))
				biomeCriteria = true;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SWAMP))
				biomeCriteria = true;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.COLD))
				biomeCriteria = false;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.DEAD))
				biomeCriteria = false;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MUSHROOM))
				biomeCriteria = false;
		}
		if (matchBiome(biome, LepidodendronConfig.genReticulopterisOverrideBiomes))
			biomeCriteria = true;
		if (LepidodendronConfig.dimCarboniferous == dimID)
			biomeCriteria = true;
			
		if (!biomeCriteria)
			return;
			
		int GenChance = 3;
		double GenMultiplier = LepidodendronConfig.multiplierReticulopteris;
		if (GenMultiplier < 0) {GenMultiplier = 0;}
		GenChance = Math.min(15, (int) Math.round((double) GenChance * GenMultiplier));
		//Is this a transformed biome?
		if (LepidodendronDecorationHandler.matchBiome(biome, LepidodendronConfig.genTransformBiomes)) {
			//if (biome.getRegistryName().toString().substring(0, biome.getRegistryName().toString().indexOf(":")).equalsIgnoreCase("minecraft"))
				GenChance = 15;
		}

		if (LepidodendronConfig.dimCarboniferous == dimID) {
			GenChance = 25;
		}

		int maxheight = LepidodendronConfig.maxheightReticulopteris;
		int minheight = LepidodendronConfig.minheightReticulopteris;
		if (maxheight < 0) {maxheight = 0;}
		if (maxheight > 250) {maxheight = 250;}
		if (minheight < 1) {minheight = 1;}
		if (minheight > 250) {minheight = 250;}
		final int maxH = maxheight;
		final int minH = minheight;
			
		for (int i = 0; i < GenChance; i++) {
			int l6 = chunkX + random.nextInt(16) + 8;
			int i11 = random.nextInt(128);
			int l14 = chunkZ + random.nextInt(16) + 8;
			(new WorldGenReed() {
				@Override
				public boolean generate(World world, Random random, BlockPos pos) {
					for (int i = 0; i < 8; ++i) {
						BlockPos blockpos1 = pos.add(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
						if (world.isAirBlock(blockpos1) && world.isAirBlock(blockpos1.up()) && blockpos1.getY() >= minH && (blockpos1.getY() <= maxH || maxH == 0) ) {
							int j = random.nextInt(2);
							int heightCheck = 0;
							boolean spaceCheck = true;
							for (int k = 1; k <= j; ++k) {
								if (((BlockCustomFlower) block).canBlockStay(world, blockpos1)
									&& (world.isAirBlock(blockpos1.up(k))) && spaceCheck) {
									heightCheck = k;
								}
								else {
									spaceCheck = false;
								}
							}
							j = heightCheck;
							for (int k = 0; k <= j; ++k) {
								if (((BlockCustomFlower) block).canBlockStay(world, blockpos1)) {
									world.setBlockState(blockpos1.up(k), block.getDefaultState(), 2);
								}
							}
						}
					}
					return true;
				}
			}).generate(world, random, new BlockPos(l6, i11, l14));
		}
	}
	public static class BlockCustomFlower extends BlockReed {

		public static final PropertyBool BASE = PropertyBool.create("base");
		public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
		
		public BlockCustomFlower() {
			setSoundType(SoundType.PLANT);
			setCreativeTab(TabLepidodendronPlants.tab);
			setHardness(1.5F);
			setResistance(0.5F);
			setLightLevel(0F);
			setTranslationKey("pf_reticulopteris");
			setRegistryName("reticulopteris");
			this.setDefaultState(this.blockState.getBaseState().withProperty(BASE, false).withProperty(AGE, Integer.valueOf(0)));
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			if (!LepidodendronConfig.doSeeds) {
				drops.add(new ItemStack(this));
			}
			else {
				drops.add(new ItemStack(Blocks.AIR));
			}
		}
		
		@Override
		public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos){

	    	Block U1 = worldIn.getBlockState(pos.up()).getBlock();
	        return state.withProperty(BASE, (U1 == this));
	    }

	    @Override
		protected net.minecraft.block.state.BlockStateContainer createBlockState() {
			return new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{AGE, BASE});
		}
			
		@Override
		protected boolean canSilkHarvest()
	    {
	        return false;
	    }

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(Item.getItemFromBlock(this), 1, this.damageDropped(state));
		}

		@Override
		public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
			return EnumPlantType.Plains;
		}

		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	    {
	        return new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.60D, 1.0D, 0.60D);
	    }

	    @Override
	    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	    {
	        return new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.6D, 1.0D, 0.6D);
	    }

		@Override
	    public boolean canBlockStay(World worldIn, BlockPos pos)
	    {
	        return canPlaceBlockAt(worldIn, pos);
	    }
	    
		@Override
		public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	    {
	        IBlockState state = worldIn.getBlockState(pos.down());
	        Block block = state.getBlock();

		    if (block == this)
		    {
		    	return true;
	        }
	        if (block.canSustainPlant(state, worldIn, pos.down(), EnumFacing.UP, this))
	        {
	            return true;
	        }
	        else {
				return false;
			}
	    }

		@SideOnly(Side.CLIENT)
		public int colorMultiplier(IBlockAccess p_149720_1_, BlockPos pos, int pass) {
			return 16777215;
		}

		@Override
		public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
			if (world.getBlockState(pos.down()).getBlock() == block || this.checkForDrop(world, pos, state)) {

				if (world.isAirBlock(pos.up(2))) {
					int l;
					for (l = 1; world.getBlockState(pos.down(l)).getBlock() == this; ++l);
					if (l < 2) {
						int i1 = (Integer) state.getValue(AGE);
						if (i1 == 15) {
							//world.setBlockToAir(pos.up(2));
							world.setBlockState(pos.up(), this.getDefaultState());
							world.setBlockState(pos, state.withProperty(AGE, 0), 4);
						} else {
							world.setBlockState(pos, state.withProperty(AGE, i1 + 1), 4);
						}
					}
				}
			}
		}

	    @SideOnly(Side.CLIENT)
		@Override
	    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
	        if (LepidodendronConfig.showTooltips) {
				tooltip.add("Type: Seed-Plant");
				tooltip.add("Periods: Carboniferous");
				tooltip.add("Propagation: seeds");}
	        super.addInformation(stack, player, tooltip, advanced);
	    }

		public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
		{
			if ((!player.capabilities.allowEdit) || (!player.getHeldItemMainhand().isEmpty()) || !LepidodendronConfig.doSeeds)
			{
				return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
			}
			else {
				if (!((hand != player.getActiveHand()) && (hand == EnumHand.MAIN_HAND))) {
					if (Math.random() > 0.5) {
						ItemStack stackSeed = new ItemStack(ItemReticulopterisSeeds.block, (int) (1));
						stackSeed.setCount(1);
						ItemHandlerHelper.giveItemToPlayer(player, stackSeed);
						if (Math.random() > 0.75) {
							world.destroyBlock(pos, false);
						}
						return true;
					}
					else {
						if (Math.random() > 0.75) {
							world.destroyBlock(pos, false);
							return true;
						}
					}
				}
				return true;
			}
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

