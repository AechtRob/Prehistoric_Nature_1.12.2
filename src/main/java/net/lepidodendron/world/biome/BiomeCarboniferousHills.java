
package net.lepidodendron.world.biome;

import net.lepidodendron.world.*;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.BiomeDictionary;

import net.minecraft.server.MinecraftServer;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.lepidodendron.LepidodendronConfig;
import net.minecraft.block.material.Material;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.biome.Biome;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.properties.PropertyEnum;

import net.lepidodendron.block.BlockPrehistoricGroundLush;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockWalchiaLog;
import net.lepidodendron.block.BlockCordaitesLog;

import net.minecraft.block.BlockDoublePlant;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BiomeCarboniferousHills extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:carboniferous_hills")
	public static final BiomeGenCustom biome = null;
	public BiomeCarboniferousHills(ElementsLepidodendronMod instance) {
		super(instance, 1591);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.SWAMP);
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.JUNGLE);
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.LUSH);
	}
	static class BiomeGenCustom extends Biome {
		public BiomeGenCustom() {
			super(new Biome.BiomeProperties("The Carboniferous Period").setRainfall(0.5F).setBaseBiome("lepidodendron:carboniferous_swamp_hills").setBaseHeight(0.8F).setHeightVariation(0.2F).setTemperature(0.75F).setRainfall(0.9F).setWaterColor(8186044));
			setRegistryName("carboniferous_hills");
			topBlock = BlockPrehistoricGroundLush.block.getDefaultState();
			fillerBlock = Blocks.DIRT.getStateFromMeta(1);
			decorator.treesPerChunk = 30;
			decorator.flowersPerChunk = 0;
			decorator.grassPerChunk = 0;
			decorator.mushroomsPerChunk = 20;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 0;
			decorator.sandPatchesPerChunk = 0;
			decorator.gravelPatchesPerChunk = 30;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
		}

		@Override
		@SideOnly(Side.CLIENT)
		public int getFoliageColorAtPos(BlockPos pos)
		{
			return -15424749;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public int getGrassColorAtPos(BlockPos pos)
		{
			return -15424749;
		}

		@Override
		public int getModdedBiomeGrassColor(int original)
		{
			return -15424749;
		}

		@Override
		public int getModdedBiomeFoliageColor(int original)
		{
			return -15424749;
		}

		protected static final WorldGenLepidodendronTree LEPIDODENDRON_TREE = new WorldGenLepidodendronTree(false);
		protected static final WorldGenWalchiaTree WALCHIA_TREE = new WorldGenWalchiaTree(false);
		protected static final WorldGenCordaites CORDAITES_TREE = new WorldGenCordaites(false);
		protected static final WorldGenPsaronius PSARONIUS = new WorldGenPsaronius(false);
		protected static final WorldGenAlethopterisTree ALETHOPTERIS_TREE = new WorldGenAlethopterisTree(false);
		protected static final WorldGenPuddles PUDDLES_GENERATOR = new WorldGenPuddles();
		protected static final WorldGenTopSoil TOPSOIL_GENERATOR = new WorldGenTopSoil();
		protected static final WorldGenStauropteris STAUROPTERIS_GENERATOR = new WorldGenStauropteris();
		protected static final WorldGenSphenopteris SPHENOPTERIS_GENERATOR = new WorldGenSphenopteris();
		protected static final WorldGenMedullosales MEDULLOSALES_GENERATOR = new WorldGenMedullosales();
		protected static final WorldGenAncientMoss ANCIENT_MOSS_GENERATOR = new WorldGenAncientMoss();
		protected static final WorldGenSelaginella SELAGINELLA_GENERATOR = new WorldGenSelaginella();
		protected static final WorldGenMarattia MARATTIA_GENERATOR = new WorldGenMarattia();
		protected static final WorldGenZygopteridaceaeShoot ZYGOPTERIDACEAE_SHOOT_GENERATOR = new WorldGenZygopteridaceaeShoot();
		protected static final WorldGenIsoetes ISOETES_GENERATOR = new WorldGenIsoetes();
		protected static final WorldGenWaterHorsetail WATER_HORSETAIL_GENERATOR = new WorldGenWaterHorsetail();
		protected static final WorldGenPrehistoricGroundCoverLush GROUNDCOVER_GENERATOR = new WorldGenPrehistoricGroundCoverLush();
		protected static final WorldGenTreeLog WALCHIA_LOG_GENERATOR = new WorldGenTreeLog(BlockWalchiaLog.block);
    	protected static final WorldGenTreeLog CORDAITES_LOG_GENERATOR = new WorldGenTreeLog(BlockCordaitesLog.block);
    	protected static final WorldGenTreeLog LEPIDODENDRON_LOG_GENERATOR = new WorldGenTreeLog(BlockWalchiaLog.block);
		protected static final WorldGenFern FERN_GENERATOR = new WorldGenFern();
		public static final PropertyEnum<BlockDoublePlant.EnumPlantType> VARIANT = PropertyEnum.<BlockDoublePlant.EnumPlantType>create("variant", BlockDoublePlant.EnumPlantType.class);
		
		public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	    {
	    	int selector = rand.nextInt(9);
	    	switch (selector) {
	    		case 0 :
	    			return LEPIDODENDRON_TREE;
	       		case 1 :
	    			return ALETHOPTERIS_TREE;
	       		case 2 :
	    			return ALETHOPTERIS_TREE;
	       		case 3 :
	    			return WALCHIA_TREE;
	       		case 4 :
	    			return WALCHIA_TREE;
	       		case 5 :
	    			return CORDAITES_TREE;
	       		case 6 :
	    			return CORDAITES_TREE;
	       		case 7 :
	    			return PSARONIUS;
	       		case 8 :
	    			return PSARONIUS;
	    	}
	    	return WALCHIA_TREE;
	    }


		@Override
	    public void decorate(World worldIn, Random rand, BlockPos pos)
	    {
	        String mobToSpawn;
	        String nbtStr = "";
			int locationID = 1;
	        boolean errFound;
	        boolean posCheck;
	        int strPos1;
	        int strPos2;
	        int strPos3;
	        int strPos4;
	        int strPos5;
	        //final int i18;
	        
			//Spawns forcefully borrow the bush event - why not?
			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH))
			{
				String[] var2 = LepidodendronConfig.dimCarboniferousMobs;
		        int var3 = var2.length;
		        for(int var4 = 0; var4 < var3; ++var4) {
		        	errFound = false;
		            String checkEntity = var2[var4].trim();
		            
		            //System.err.println("checkEntity: " + checkEntity);

					strPos1 = 0;
					strPos2 = 0;
					strPos3 = 0;
					strPos4 = 0;
					strPos5 = 0;
					nbtStr = "";

					strPos1 = checkEntity.indexOf(":");
					if (!(strPos1 > 0)) {
						errFound = true;
					}
					else {
						String modid = checkEntity.substring(0, strPos1);
					
						//System.err.println("modid: " + modid);
						
						//Get the last two bits, first the rightmost colon:
						for(int i=checkEntity.length(); i > 0; i--){
					        if(checkEntity.charAt(i - 1) == ':'){
					             strPos2 = i;
					             break;
					        }
					    }
			
					    if (strPos2 <= strPos1) {
					    	//The position means that something is missing
					    	errFound = true;
					    }
					    else {
							String locationStr = checkEntity.substring(checkEntity.length()-1, checkEntity.length());
							if (!(locationStr.equals((String) "1") || locationStr.equals((String) "2") || locationStr.equals((String) "3"))) {
								errFound = true;
							}
							else {
								locationID =  (int) Integer.parseInt(locationStr);
								//System.err.println("locationID: " + locationID);
								
								//Get the next last bit:
								strPos3 = 0;
								String tmpStr = checkEntity.substring(0,checkEntity.length()-2);
								for(int i=tmpStr.length(); i > 0; i--){
							        if(tmpStr.charAt(i - 1) == ':'){
							             strPos3 = i;
							             break;
							        }
							    }
							    if (strPos3 <= strPos1) {
							    	//The position means that something is missing
							    	errFound = true;
							    }
							    else {
									tmpStr = tmpStr.substring(strPos3, tmpStr.length());
									if (!isNumeric(tmpStr)) {
										errFound = true;
									}
									else {
										int weight = (int) Integer.parseInt(tmpStr);
										if (weight < 0 || weight > 100) {
											errFound = true;
										}
										else {

											//Get the next last bit:
											strPos5 = 0;
											tmpStr = checkEntity.substring(0, strPos3 - 1);
											for(int i=tmpStr.length(); i > 0; i--){
												if(tmpStr.charAt(i - 1) == ':'){
													strPos5 = i;
													break;
												}
											}
											if (strPos5 <= strPos1) {
												//The position means that something is missing
												errFound = true;
											}
											else {
												tmpStr = tmpStr.substring(strPos5, tmpStr.length());
												if (!isNumeric(tmpStr)) {
													errFound = true;
												} else {
													int maxSpawn = (int) Integer.parseInt(tmpStr);
													if (maxSpawn < 1 || maxSpawn > 20) {
														errFound = true;
													}


													//Everything left is the mod plus nbt (which may be wrong of course!)
													else { //Get the mob:

														mobToSpawn = checkEntity.substring(0, strPos5 - 1);
														//Is there nbt?
														strPos4 = 0;
														strPos4 = mobToSpawn.indexOf("{");
														if (strPos4 > 0) {
															//we have nbt:
															nbtStr = mobToSpawn.substring(strPos4, mobToSpawn.length());
															mobToSpawn = mobToSpawn.substring(0, strPos4);
														}
														//System.err.println("mobToSpawn: " + mobToSpawn);
														//System.err.println("nbt: " + nbtStr);

														if (findEntity(mobToSpawn) != null) {
															//Spawn routine (finally!)

															int k7 = rand.nextInt(16) + 8;
															int j11 = rand.nextInt(16) + 8;
															int l14 = worldIn.getHeight(pos.add(k7, 0, j11)).getY() * 2;
															int i2;

															if (l14 > 0) {
																int i18 = rand.nextInt(l14);

																switch (locationID) {

																	case 1: //Land
																	default:
																		//System.err.println("Case: 1");
																		//We'll check a block is the biome topblock and that there are at least 4 blocks of non-solid blocks above it:
																		posCheck = false;
																		i2 = 0;
																		while (i2 < 14 && !posCheck) {
																			k7 = rand.nextInt(16) + 8;
																			j11 = rand.nextInt(16) + 8;

																			i18 = worldIn.getSeaLevel() - 1;
																			while (i18 < 255 && !posCheck) {
																				i18 = i18 + 1;
																				BlockPos pos1 = new BlockPos(pos.getX() + k7, i18, pos.getZ() + j11);
																				//System.err.println("y: " + i18);
																				//System.err.println("block: " + (worldIn.getBlockState(pos1).getBlock()));
																				//System.err.println("topblock: " + topBlock.getBlock());
																				if ((worldIn.getBlockState(pos1.down()).getBlock() == topBlock.getBlock())
																						&& ((worldIn.isAirBlock(pos1)) || (worldIn.getBlockState(pos1).getMaterial() == Material.PLANTS))
																						&& ((worldIn.isAirBlock(pos1.up())) || (worldIn.getBlockState(pos1.up()).getMaterial() == Material.PLANTS))
																						&& ((worldIn.isAirBlock(pos1.up(2))) || (worldIn.getBlockState(pos1.up(2)).getMaterial() == Material.PLANTS))
																						&& ((worldIn.isAirBlock(pos1.up(3))) || (worldIn.getBlockState(pos1.up(3)).getMaterial() == Material.PLANTS))
																				) {
																					posCheck = true;
																					////System.err.println("Spawnable " + checkEntity);
																					//break;
																				}
																			}
																			i2 = i2 + 1;
																		}
																		break;

																	case 2: //Deep water
																		//System.err.println("Case: 2");
																		posCheck = false;
																		i2 = 0;
																		while (i2 < 14 && !posCheck) {
																			k7 = rand.nextInt(16) + 8;
																			j11 = rand.nextInt(16) + 8;

																			i18 = 1;
																			while (i18 < 255 && !posCheck) {
																				i18 = i18 + 1;
																				BlockPos pos1 = new BlockPos(pos.getX() + k7, i18, pos.getZ() + j11);
																				//System.err.println("y: " + i18);
																				//System.err.println("block: " + (worldIn.getBlockState(pos1).getBlock()));
																				//System.err.println("topblock: " + topBlock.getBlock());
																				if ((worldIn.getBlockState(pos1).getMaterial() == Material.WATER)
																						&& (worldIn.isAirBlock(pos1.up(3)))
																						&& (worldIn.getBlockState(pos1.up()).getMaterial() == Material.WATER)
																						&& (worldIn.getBlockState(pos1.up(2)).getMaterial() == Material.WATER)
																						&& (worldIn.getBlockState(pos1.down()).getMaterial() == Material.WATER)
																						&& (worldIn.getBlockState(pos1.down(2)).getMaterial() == Material.WATER)
																				) {
																					posCheck = true;
																				}
																				int xx = -4;
																				while (xx <= 4 && posCheck) {
																					int zz = -4;
																					while (zz <= 4 && posCheck) {
																						if (worldIn.getBlockState(pos1.add(xx, 0, zz)).getMaterial() != Material.WATER) {
																							posCheck = false;
																						}
																						zz = zz + 1;
																					}
																					xx = xx + 1;
																				}
																			}
																			i2 = i2 + 1;
																		}
																		break;

																	case 3: //Shallow water
																		//System.err.println("Case: 3");
																		posCheck = false;
																		i2 = 0;
																		while (i2 < 14 && !posCheck) {
																			k7 = rand.nextInt(16) + 8;
																			j11 = rand.nextInt(16) + 8;

																			i18 = 1;
																			while (i18 < 255 && !posCheck) {
																				i18 = i18 + 1;
																				BlockPos pos1 = new BlockPos(pos.getX() + k7, i18, pos.getZ() + j11);
																				//System.err.println("y: " + i18);
																				//System.err.println("block: " + (worldIn.getBlockState(pos1).getBlock()));
																				//System.err.println("topblock: " + topBlock.getBlock());
																				if ((worldIn.getBlockState(pos1).getMaterial() == Material.WATER)
																						&& (worldIn.isAirBlock(pos1.up()))
																						&& (pos1.up().getY() >= worldIn.getSeaLevel())
																						&& (worldIn.getBlockState(pos1.down(4)).getMaterial() != Material.WATER)
																				) {
																					posCheck = true;
																				}
																				int xx = -1;
																				while (xx <= 1 && posCheck) {
																					int zz = -1;
																					while (zz <= 1 && posCheck) {
																						if (worldIn.getBlockState(pos1.add(xx, 0, zz)).getMaterial() != Material.WATER) {
																							posCheck = false;
																						}
																						zz = zz + 1;
																					}
																					xx = xx + 1;
																				}
																			}
																			i2 = i2 + 1;
																		}
																		break;

																}

																//System.err.println("Poscheck: " + posCheck);
																//System.err.println("errFound: " + errFound);
																if (posCheck && !errFound) {
																	//System.err.println("Spawning " + mobToSpawn + " at: " + pos.add(k7, i18, j11).getX() + " " + pos.add(k7, i18, j11).getY() + " " + pos.add(k7, i18, j11).getZ());
																	//System.err.println("maxSpawn: " + maxSpawn);
																	//System.err.println("weight: " + weight);
																	double weighter = 500;
																	if (locationID == 2) {
																		weighter = 800;
																	}
																	if ((Math.random() * weighter) <= weight) {
																		//System.err.println("Trying......");
																		int spawnQty = rand.nextInt(maxSpawn) + 1;
																		//System.err.println("spawnQty: " + spawnQty);
																		for (int i = 0; i < spawnQty; ++i) {
																			//Spawn the mob via a command to facilitate water spawns:
																			if (!worldIn.isRemote && worldIn.getMinecraftServer() != null) {
																				//System.err.println("summon " + mobToSpawn + " " + pos.add(k7, i18, j11).getX() + " " + pos.add(k7, i18, j11).getY() + " " + pos.add(k7, i18, j11).getZ() + " " + nbtStr);
																				worldIn.getMinecraftServer().getCommandManager().executeCommand(new ICommandSender() {
																					@Override
																					public String getName() {
																						return "";
																					}

																					@Override
																					public boolean canUseCommand(int permission, String command) {
																						return true;
																					}

																					@Override
																					public World getEntityWorld() {
																						return worldIn;
																					}

																					@Override
																					public MinecraftServer getServer() {
																						return worldIn.getMinecraftServer();
																					}

																					@Override
																					public boolean sendCommandFeedback() {
																						return false;
																					}

																					//@Override
																					//public BlockPos getPosition() {
																					//	return pos.add(k7, i18, j11);
																					//}

																					//@Override
																					//public Vec3d getPositionVector() {
																					//	return new Vec3d(pos.add(k7, i18, j11).getX(), pos.add(k7, i18, j11).getY(), pos.add(k7, i18, j11).getZ());
																					//}
																				}, "summon " + mobToSpawn + " " + pos.add(k7, i18, j11).getX() + " " + pos.add(k7, i18, j11).getY() + " " + pos.add(k7, i18, j11).getZ() + " " + nbtStr);
																			}
																			//System.err.println("Spawned");
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
				    } 
				    if (errFound) {
		            	System.err.println("Syntax error in mob spawn config: '" + checkEntity + "'!");
		            }
		        }
			}

	        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        {
	        	int i = rand.nextInt(2);

	            for (int j = 0; j < i; ++j)
	            {
	                int k = rand.nextInt(16) + 8;
	                int l = rand.nextInt(16) + 8;
	                BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
	                if (Math.random() > 0.5) {WALCHIA_LOG_GENERATOR.generate(worldIn, rand, blockpos);}
	            }
	            
				 i = rand.nextInt(2);

	            for (int j = 0; j < i; ++j)
	            {
	                int k = rand.nextInt(16) + 8;
	                int l = rand.nextInt(16) + 8;
	                BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
	                if (Math.random() > 0.5) {CORDAITES_LOG_GENERATOR.generate(worldIn, rand, blockpos);}
	            }
	            i = rand.nextInt(2);

	            for (int j = 0; j < i; ++j)
	            {
	                int k = rand.nextInt(16) + 8;
	                int l = rand.nextInt(16) + 8;
	                BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
	                if (Math.random() > 0.5) {LEPIDODENDRON_LOG_GENERATOR.generate(worldIn, rand, blockpos);}
	            }


	        }

	        DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.FERN);
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i1 = 0; i1 < 20; ++i1)
	        {
	            int j1 = rand.nextInt(16) + 8;
	            int k1 = rand.nextInt(16) + 8;
	            int l1 = rand.nextInt(worldIn.getHeight(pos.add(j1, 0, k1)).getY() + 32);
	            DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j1, l1, k1));
	        }
	        
			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 80; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            FERN_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 10; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            PUDDLES_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 25; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            TOPSOIL_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 4; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            MEDULLOSALES_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 80; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	          	STAUROPTERIS_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 80; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            SPHENOPTERIS_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 25; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            MARATTIA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 60; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            ZYGOPTERIDACEAE_SHOOT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        
	        
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 120; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            GROUNDCOVER_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 50; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            ANCIENT_MOSS_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        } 
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 25; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            SELAGINELLA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 50; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            ISOETES_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 80; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            WATER_HORSETAIL_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }

	        super.decorate(worldIn, rand, pos);
	    }
	}
	
	private static Class<? extends Entity> findEntity(String entity) {
        Class<? extends Entity> entityClass;
        EntityEntry ee = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entity));
        entityClass = ee == null ? null : ee.getEntityClass();
        if (entityClass == null) {
            System.err.println("Unknown mob requested for spawn: '" + entity + "'!");
            return null;
        }
        return entityClass;
    }		

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
