
package net.lepidodendron.world.biome;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.BlockLavaRock;
import net.lepidodendron.world.*;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BiomePrecambrianBiome extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:precambrian_biome")
	public static final BiomeGenCustom biome = null;
	public BiomePrecambrianBiome(ElementsLepidodendronMod instance) {
		super(instance, 1591);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.COLD);
	}

	static class BiomeGenCustom extends Biome {
		public BiomeGenCustom() {
			super(new BiomeProperties("The Precambrian").setRainfall(0.95F).setBaseHeight(-0.5F).setHeightVariation(0.4F).setTemperature(0.1F));
			setRegistryName("precambrian_biome");
			topBlock = BlockLavaRock.block.getDefaultState();
			fillerBlock = BlockLavaRock.block.getDefaultState();
			decorator.treesPerChunk = -999;
			decorator.flowersPerChunk = 0;
			decorator.grassPerChunk = 0;
			decorator.mushroomsPerChunk = 0;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 0;
			decorator.sandPatchesPerChunk = 100;
			decorator.gravelPatchesPerChunk = 0;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
		}
		
		protected static final WorldGenAshes ASH_GENERATOR = new WorldGenAshes();
    	protected static final WorldGenLavaFlow LAVAFLOW_GENERATOR = new WorldGenLavaFlow();
		protected static final WorldGenToxicMud TOXIC_MUD_GENERATOR = new WorldGenToxicMud();
		protected static final WorldGenIceOnSea ICE_GENERATOR = new WorldGenIceOnSea();
		protected static final WorldGenSnow SNOW_GENERATOR = new WorldGenSnow();

		public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	    {
	        return null;
	    }

		@Override
	    public void decorate(World worldIn, Random rand, BlockPos pos)
	    {
			String mobToSpawn;
			String nbtStr = "";
			int locationID = 3;
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
				String[] var2 = LepidodendronConfig.dimPrecambrianMobs;
				int var3 = var2.length;
				for(int var4 = 0; var4 < var3; ++var4) {
					errFound = false;
					String checkEntity = var2[var4].trim() + ":3";

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

																	case 3: //Shallow water
																	default:
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
																						&& (worldIn.getBlockState(pos1.up()).getMaterial() == Material.WATER)
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

	        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ROCK))
	        {
	        	int i = rand.nextInt(2);
	            for (int j = 0; j < i; ++j)
	            {
	                int k = rand.nextInt(16) + 8;
	                int l = rand.nextInt(16) + 8;
	                BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
	                ASH_GENERATOR.generate(worldIn, rand, blockpos);
	            }
	        }
	        
	        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ROCK))
			{
				int i = rand.nextInt(12);
				for (int j = 0; j < i; ++j) {
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(16) + 8;
					BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
					LAVAFLOW_GENERATOR.generate(worldIn, rand, blockpos,30);
				}
	        }

	        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 128; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            TOXIC_MUD_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }

			if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ICE)) {
				{
					int i = rand.nextInt(32);

					for (int j = 0; j < i; ++j) {
						int k = rand.nextInt(16) + 8;
						int l = rand.nextInt(16) + 8;
						BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
						SNOW_GENERATOR.generate(worldIn, rand, blockpos);
					}
					//}

					//{
					i = rand.nextInt(64);

					for (int j = 0; j < i; ++j) {
						int k = rand.nextInt(16) + 8;
						int l = rand.nextInt(16) + 8;
						BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
						ICE_GENERATOR.generate(worldIn, rand, blockpos);
					}
				}
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
