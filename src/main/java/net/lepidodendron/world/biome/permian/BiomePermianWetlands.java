
package net.lepidodendron.world.biome.permian;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.BlockCordaitesLog;
import net.lepidodendron.block.BlockPrehistoricGroundLush;
import net.lepidodendron.block.BlockWalchiaLog;
import net.lepidodendron.world.*;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BiomePermianWetlands extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:permian_wetlands")
	public static final BiomeGenCustom biome = null;
	public BiomePermianWetlands(ElementsLepidodendronMod instance) {
		super(instance, 1591);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.DEAD);
		//BiomeDictionary.addTypes(biome, BiomeDictionary.Type.SWAMP);
		//BiomeDictionary.addTypes(biome, BiomeDictionary.Type.JUNGLE);
		//BiomeDictionary.addTypes(biome, BiomeDictionary.Type.LUSH);
	}
	static class BiomeGenCustom extends Biome {
		public BiomeGenCustom() {
			super(new BiomeProperties("Permian Wetlands").setRainfall(0.5F).setBaseHeight(-0.2F).setHeightVariation(0.05F).setTemperature(0.95F).setRainfall(0.9F).setWaterColor(8186044));
			setRegistryName("permian_wetlands");
			topBlock = BlockPrehistoricGroundLush.block.getDefaultState();
			fillerBlock = Blocks.DIRT.getStateFromMeta(1);
			decorator.treesPerChunk = 8;
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
			return -11103684;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public int getGrassColorAtPos(BlockPos pos)
		{
			return -11103684;
		}

		@Override
		public int getModdedBiomeGrassColor(int original)
		{
			return -11103684;
		}

		@Override
		public int getModdedBiomeFoliageColor(int original)
		{
			return -11103684;
		}

		protected static final WorldGenLepidodendronTree LEPIDODENDRON_TREE = new WorldGenLepidodendronTree(false);
		protected static final WorldGenSigillaria SIGILLARIA_TREE = new WorldGenSigillaria(false);
		protected static final WorldGenWalchiaTree WALCHIA_TREE = new WorldGenWalchiaTree(false);
		protected static final WorldGenZygopterisTree ZYGOPTERIS_TREE = new WorldGenZygopterisTree(false);
		protected static final WorldGenUtrechtia UTRECHTIA_GENERATOR = new WorldGenUtrechtia();
		protected static final WorldGenTietea TIETEA = new WorldGenTietea(false);
		protected static final WorldGenCordaitesTree CORDAITES_TREE = new WorldGenCordaitesTree(false);
		protected static final WorldGenPuddles PUDDLES_GENERATOR = new WorldGenPuddles();
		protected static final WorldGenGigantopterid GIGANTOPTERID_GENERATOR = new WorldGenGigantopterid();
		protected static final WorldGenEmplectopteris EMPLECTOPTERIS_GENERATOR = new WorldGenEmplectopteris();
		protected static final WorldGenAncientMoss ANCIENT_MOSS_GENERATOR = new WorldGenAncientMoss();
		protected static final WorldGenSelaginella SELAGINELLA_GENERATOR = new WorldGenSelaginella();
		protected static final WorldGenTreeRottenLog ROTTEN_LOG_GENERATOR = new WorldGenTreeRottenLog();
		protected static final WorldGenIsoetes ISOETES_GENERATOR = new WorldGenIsoetes();
		protected static final WorldGenPalaeostachya PALAEOSTACHYA_GENERATOR = new WorldGenPalaeostachya();
		protected static final WorldGenWaterHorsetail WATER_HORSETAIL_GENERATOR = new WorldGenWaterHorsetail();
		protected static final WorldGenPrehistoricGroundCoverPlants GROUNDCOVER_GENERATOR = new WorldGenPrehistoricGroundCoverPlants();
		protected static final WorldGenTreeLog CORDAITES_LOG_GENERATOR = new WorldGenTreeLog(BlockCordaitesLog.block);
    	protected static final WorldGenTreeLog WALCHIA_LOG_GENERATOR = new WorldGenTreeLog(BlockWalchiaLog.block);
    	protected static final WorldGenFern FERN_GENERATOR = new WorldGenFern();
		protected static final WorldGenMud MUD_GENERATOR = new WorldGenMud();
		public static final PropertyEnum<BlockDoublePlant.EnumPlantType> VARIANT = PropertyEnum.<BlockDoublePlant.EnumPlantType>create("variant", BlockDoublePlant.EnumPlantType.class);
		
		public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	    {
	    	int selector = rand.nextInt(10);
	    	switch (selector) {
	    		case 0 :
	    			return WALCHIA_TREE;
	       		case 1 :
	    			return WALCHIA_TREE;
	       		case 2 :
	    			return TIETEA;
	       		case 3 :
	    			return CORDAITES_TREE;
	       		case 4 :
	    			return TIETEA;
	       		case 5 :
	    			return ZYGOPTERIS_TREE;
				case 6 :
					return SIGILLARIA_TREE;
				case 7 :
					return TIETEA;
				case 8 :
					return CORDAITES_TREE;
				case 9 :
					return LEPIDODENDRON_TREE;
	    	}
	    	return WALCHIA_TREE;
	    }


		@Override
	    public void decorate(World worldIn, Random rand, BlockPos pos)
	    {
			//Spawns forcefully borrow the bush event - why not?
			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH))
			{
				//String[] MobString = LepidodendronConfig.dimPermianWetlandsMobsBespoke;
				if (LepidodendronConfig.doSpawnsPrehistoricFloraDefault) {
					//MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimPermianWetlandsMobsPF);
				}
				if (LepidodendronConfig.doSpawnsFossilsArcheology) {
					//MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimPermianWetlandsMobsFA);
				}
				if (LepidodendronConfig.doSpawnsReborn) {
					//MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimPermianWetlandsMobsReborn);
				}
				//ChunkGenSpawner.executeProcedure(false, MobString, worldIn, topBlock, pos, rand);
			}

	        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS)) {
				int i = rand.nextInt(2);

				for (int j = 0; j < i; ++j)
				{
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(16) + 8;
					BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
					if (Math.random() > 0.5) {ROTTEN_LOG_GENERATOR.generate(worldIn, rand, blockpos);}
				}

				i = rand.nextInt(2);

				for (int j = 0; j < i; ++j) {
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(16) + 8;
					BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
					if (Math.random() > 0.5) {
						WALCHIA_LOG_GENERATOR.generate(worldIn, rand, blockpos);
					}
				}
				i = rand.nextInt(2);

				for (int j = 0; j < i; ++j) {
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(16) + 8;
					BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
					if (Math.random() > 0.5) {
						CORDAITES_LOG_GENERATOR.generate(worldIn, rand, blockpos);
					}
				}
				i = rand.nextInt(2);

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
			for (int i = 0; i < 25; ++i)
			{
				int j = rand.nextInt(16) + 8;
				int k = rand.nextInt(16) + 8;
				int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
				PUDDLES_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
			}
	        
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 10; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            MUD_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 15; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            UTRECHTIA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 32; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
				GIGANTOPTERID_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
			for (int i = 0; i < 12; ++i)
			{
				int j = rand.nextInt(16) + 8;
				int k = rand.nextInt(16) + 8;
				int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
				PALAEOSTACHYA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
			}
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 24; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            EMPLECTOPTERIS_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }

	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 110; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            GROUNDCOVER_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 64; ++i)
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
