
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronDecorationHandler;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronPlants;
import net.lepidodendron.util.EnumBiomeTypeCarboniferous;
import net.lepidodendron.util.EnumBiomeTypePermian;
import net.lepidodendron.util.EnumBiomeTypeTriassic;
import net.lepidodendron.world.biome.carboniferous.BiomeCarboniferous;
import net.lepidodendron.world.biome.permian.*;
import net.lepidodendron.world.biome.triassic.BiomeTriassic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
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
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockGiantHorsetail extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:giant_horsetail")
	public static final Block block = null;
	public BlockGiantHorsetail(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.giant_horsetail);
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
				new ModelResourceLocation("lepidodendron:giant_horsetail", "inventory"));
	}

	@Override
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		boolean dimensionCriteria = false;
		boolean isNetherType = false;
		if (shouldGenerateInDimension(dimID, LepidodendronConfig.dimHorsetail))
			dimensionCriteria = true;
		if (!LepidodendronConfig.genHorsetail && !LepidodendronConfig.genAllPlants)
			dimensionCriteria = false;
		if ((LepidodendronConfig.dimCarboniferous == dimID
		|| dimID == LepidodendronConfig.dimPermian
		|| dimID == LepidodendronConfig.dimTriassic)
			)
			dimensionCriteria = true;

		if (!dimensionCriteria)
			return;

		boolean biomeCriteria = false;
		Biome biome = world.getBiome(new BlockPos(chunkX, 128, chunkZ));
		if ((!matchBiome(biome, LepidodendronConfig.genGlobalBlacklist)) && (!matchBiome(biome, LepidodendronConfig.genHorsetailBlacklistBiomes))) {
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SWAMP))
				biomeCriteria = true;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.RIVER))
				biomeCriteria = true;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.LUSH))
				biomeCriteria = true;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.JUNGLE))
				biomeCriteria = true;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.DEAD))
				biomeCriteria = false;
			if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MUSHROOM))
				biomeCriteria = false;
		}
		if (matchBiome(biome, LepidodendronConfig.genHorsetailOverrideBiomes))
			biomeCriteria = true;
		if (LepidodendronConfig.dimCarboniferous == dimID)
			biomeCriteria = true;

		if (biome instanceof BiomePermian) {
			BiomePermian biomePermian = (BiomePermian) biome;
			if (biomePermian.getBiomeType() == EnumBiomeTypePermian.Wetlands
				|| biomePermian.getBiomeType() == EnumBiomeTypePermian.Ocean) {
				biomeCriteria = true;
			}
			if (biome == BiomePermianLowlandsForest.biome
				|| biome == BiomePermianLowlands.biome
					|| biome == BiomePermianLowlandFloodplain.biome
					|| biome == BiomePermianHighlands.biome) {
				biomeCriteria = true;
			}
		}

		if (biome instanceof BiomeCarboniferous) {
			BiomeCarboniferous biomePermian = (BiomeCarboniferous) biome;
			if (biomePermian.getBiomeType() == EnumBiomeTypeCarboniferous.Ice
				|| biomePermian.getBiomeType() == EnumBiomeTypeCarboniferous.Ocean) {
				biomeCriteria = false;
			}
		}

		if (biome instanceof BiomeTriassic) {
			BiomeTriassic biomeTriassic = (BiomeTriassic) biome;
			if (biomeTriassic.getBiomeType() == EnumBiomeTypeTriassic.Cool
				|| biomeTriassic.getBiomeType() == EnumBiomeTypeTriassic.Ocean
				|| biomeTriassic.getBiomeType() == EnumBiomeTypeTriassic.River
				|| biomeTriassic.getBiomeType() == EnumBiomeTypeTriassic.Warm) {
				biomeCriteria = true;
			}
		}

		if (!biomeCriteria)
			return;
			
		int GenChance = 5;
		double GenMultiplier = LepidodendronConfig.multiplierHorsetail;
		if (GenMultiplier < 0) {GenMultiplier = 0;}
		GenChance = Math.min(15, (int) Math.round((double) GenChance * GenMultiplier));
		//Is this a transformed biome?
		if (LepidodendronDecorationHandler.matchBiome(biome, LepidodendronConfig.genTransformBiomes)) {
			//if (biome.getRegistryName().toString().substring(0, biome.getRegistryName().toString().indexOf(":")).equalsIgnoreCase("minecraft"))
				GenChance = 15;
		}
		
		if (LepidodendronConfig.dimCarboniferous == dimID
				|| dimID == LepidodendronConfig.dimPermian
				|| dimID == LepidodendronConfig.dimTriassic) {
			GenChance = 25;
		}
		if (biome == BiomePermianLowlands.biome) {
			GenChance = 92;
		}
		if (biome == BiomePermianLowlandFloodplain.biome) {
			GenChance = 156;
		}
		if (biome == BiomePermianHighlands.biome) {
			GenChance = 64;
		}

		int maxheight = LepidodendronConfig.maxheightHorsetail;
		int minheight = LepidodendronConfig.minheightHorsetail;
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
					for (int i = 0; i < 20; ++i) {
						BlockPos blockpos1 = pos.add(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
						if (world.isAirBlock(blockpos1) && world.isAirBlock(blockpos1.up()) && blockpos1.getY() >= minH && (blockpos1.getY() <= maxH || maxH == 0) ) {
							int j = 1 + random.nextInt(random.nextInt(10) + 1);
							j = Math.max(2, j);
							int heightCheck = 2;
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
							for (int k = 0; k <= j; ++k)
 {
								if (((BlockCustomFlower) block).canBlockStay(world, blockpos1) && ((BlockCustomFlower) block).hasWater(world, blockpos1))
 {
									if (k <= (j - 1)) {world.setBlockState(blockpos1.up(k), block.getDefaultState(), 2);
}
									if (k == j) {
										world.setBlockState(blockpos1.up(k), BlockGiantHorsetailShoot.block.getDefaultState(), 2);
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
	public static class BlockCustomFlower extends BlockReed {
		public BlockCustomFlower() {
			setSoundType(SoundType.PLANT);
			setCreativeTab(TabLepidodendronPlants.tab);
			setHardness(0F);
			setResistance(0F);
			setLightLevel(0F);
			setTranslationKey("pf_giant_horsetail");
			setRegistryName("giant_horsetail");
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			if (!LepidodendronConfig.doSpores) {
				drops.add(new ItemStack(this));
			}
			else {
				drops.add(new ItemStack(Blocks.AIR));
			}
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

		public boolean hasWater(IBlockAccess world, BlockPos pos) {
			Biome biome = world.getBiome(pos);
			if (biome == BiomePermianLowlands.biome || biome == BiomePermianHighlands.biome) {
				boolean waterCriteria = false;
				//Is there water nearby?
				int xct = -3;
				int yct;
				int zct;
				while ((xct < 4) && (!waterCriteria)) {
					yct = -6;
					while ((yct <= 1) && (!waterCriteria)) {
						zct = -3;
						while ((zct < 4) && (!waterCriteria)) {
							if ((world.getBlockState(pos.add(xct, yct, zct))).getMaterial() == Material.WATER) {
								waterCriteria = true;
							}
							zct = zct + 1;
						}
						yct = yct + 1;
					}
					xct = xct + 1;
				}
				return waterCriteria;
			}
			return true;
		}

		@Override
		public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
			return EnumPlantType.Plains;
		}

		@Override
		public boolean canPlaceBlockAt(World world, BlockPos pos) {
			Block block2 = world.getBlockState(pos.down()).getBlock();
			return (block2.canSustainPlant(world.getBlockState(pos.down()), world, pos.down(), EnumFacing.UP, this) || block2 == block);
		}

		@SideOnly(Side.CLIENT)
		public int colorMultiplier(IBlockAccess p_149720_1_, BlockPos pos, int pass) {
			return 16777215;
		}

		@Override
		public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
			if (world.getBlockState(pos.down()).getBlock() == block || this.checkForDrop(world, pos, state)) {
				if (world.isAirBlock(pos.up())) {
					world.setBlockState(pos.up(), BlockGiantHorsetailShoot.block.getDefaultState(), 2);
				}
				else {
					if ((world.isAirBlock(pos.up(2))) && ((world.getBlockState(pos.up())).getBlock() == BlockGiantHorsetailShoot.block)) {
						int l;
						for (l = 1; world.getBlockState(pos.down(l)).getBlock() == this; ++l);
						if (l < 10) {
							int i1 = (Integer) state.getValue(AGE);
							if (i1 == 15) {
								//world.setBlockToAir(pos.up());
								world.setBlockState(pos.up(), this.getDefaultState());
								world.setBlockState(pos, state.withProperty(AGE, 0), 4);
								if (world.isAirBlock(pos.up(2))) {
									world.setBlockState(pos.up(2), BlockGiantHorsetailShoot.block.getDefaultState(), 2);
								}
							} else {
								world.setBlockState(pos, state.withProperty(AGE, i1 + 1), 4);
							}
						}
					}
				}
			}
			if ((world.getBlockState(pos.down()).getBlock() != block) && (Math.random() > 0.75)){
				//Sometimes it's a new plants, but mostly it's flowers
				//Send up flower stems sometimes:
				boolean flowered = false;
				BlockPos block1 = pos.east();
				if (!flowered && (world.isAirBlock(block1)) && (world.isAirBlock(block1.up()))) {
					Block block2 = world.getBlockState(block1.down()).getBlock();
					if (block2.canSustainPlant(world.getBlockState(block1.down()), world, block1.down(), EnumFacing.UP, this)) {
						world.setBlockState(block1, BlockGiantHorsetailFlower.block.getDefaultState(), 2);
						world.setBlockState(block1.up(), BlockGiantHorsetailFlower.block.getDefaultState(), 2);
						flowered = true;
					}
				}
				block1 = pos.west();
				if (!flowered && (world.isAirBlock(block1)) && (world.isAirBlock(block1.up()))) {
					Block block2 = world.getBlockState(block1.down()).getBlock();
					if (block2.canSustainPlant(world.getBlockState(block1.down()), world, block1.down(), EnumFacing.UP, this)) {
						world.setBlockState(block1, BlockGiantHorsetailFlower.block.getDefaultState(), 2);
						world.setBlockState(block1.up(), BlockGiantHorsetailFlower.block.getDefaultState(), 2);
						flowered = true;
					}
				}
				block1 = pos.north();
				if (!flowered && (world.isAirBlock(block1)) && (world.isAirBlock(block1.up()))) {
					Block block2 = world.getBlockState(block1.down()).getBlock();
					if (block2.canSustainPlant(world.getBlockState(block1.down()), world, block1.down(), EnumFacing.UP, this)) {
						world.setBlockState(block1, BlockGiantHorsetailFlower.block.getDefaultState(), 2);
						world.setBlockState(block1.up(), BlockGiantHorsetailFlower.block.getDefaultState(), 2);
						flowered = true;
					}
				}
				block1 = pos.south();
				if (!flowered && (world.isAirBlock(block1)) && (world.isAirBlock(block1.up()))) {
					Block block2 = world.getBlockState(block1.down()).getBlock();
					if (block2.canSustainPlant(world.getBlockState(block1.down()), world, block1.down(), EnumFacing.UP, this)) {
						world.setBlockState(block1, BlockGiantHorsetailFlower.block.getDefaultState(), 2);
						world.setBlockState(block1.up(), BlockGiantHorsetailFlower.block.getDefaultState(), 2);
						flowered = true;
					}
				}
			}
		}

	    @SideOnly(Side.CLIENT)
		@Override
	    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
	        if (LepidodendronConfig.showTooltips) {
				tooltip.add("Type: Horsetail shrub");
	        tooltip.add("Periods: Carboniferous - Permian - Triassic - Jurassic - Cretaceous - Paleogene - Neogene - Quaternary");
	        tooltip.add("Propagation: spores");}
	        super.addInformation(stack, player, tooltip, advanced);
	    }

		@Override
	    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
	    	if (world.isAirBlock(pos.up())) {
				world.setBlockState(pos.up(), BlockGiantHorsetailShoot.block.getDefaultState(), 3);
	    	}
			super.onBlockAdded(world, pos, state);
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

