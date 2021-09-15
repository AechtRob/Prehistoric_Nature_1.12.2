
package net.lepidodendron.world.biome.devonian;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.BlockArchaeopterisLog;
import net.lepidodendron.world.*;
import net.lepidodendron.world.biome.ChunkGenSpawner;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BiomeDevonianFloodplain extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:devonian_floodplain")
	public static final BiomeGenCustom biome = null;
	public BiomeDevonianFloodplain(ElementsLepidodendronMod instance) {
		super(instance, 1591);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.DEAD);
		//BiomeDictionary.addTypes(biome, BiomeDictionary.Type.BEACH);
	}
	static class BiomeGenCustom extends Biome {
		public BiomeGenCustom() {
			super(new Biome.BiomeProperties("The Devonian Period").setRainfall(0.5F).setBaseHeight(-0.6F).setHeightVariation(0.1F).setTemperature(0.5F).setWaterColor(14745518));
			setRegistryName("devonian_floodplain");
			topBlock = Blocks.SAND.getStateFromMeta(0);
			fillerBlock = Blocks.SAND.getStateFromMeta(0);
			decorator.treesPerChunk = 0;
			decorator.flowersPerChunk = 0;
			decorator.grassPerChunk = 0;
			decorator.mushroomsPerChunk = 0;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 0;
			decorator.sandPatchesPerChunk = 0;
			decorator.gravelPatchesPerChunk = 0;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
		}
		

		protected static final WorldGenArchaeopterisTree ARCHAEOPTERIS_TREE = new WorldGenArchaeopterisTree(false);
		protected static final WorldGenTreeRottenLog ROTTEN_LOG_GENERATOR = new WorldGenTreeRottenLog();
		protected static final WorldGenRhacophyton RHACOPHYTON_GENERATOR = new WorldGenRhacophyton();
		protected static final WorldGenTreeLog ARCHAEOPTERIS_LOG_GENERATOR = new WorldGenTreeLog(BlockArchaeopterisLog.block);
    	protected static final WorldGenWattieza WATTIEZA_GENERATOR = new WorldGenWattieza();
		protected static final WorldGenCalamophyton CALAMOPHYTON_GENERATOR = new WorldGenCalamophyton();
		protected static final WorldGenAneurophyton ANEUROPHYTON_GENERATOR = new WorldGenAneurophyton();
		protected static final WorldGenPsilophyton PSILOPHYTON_GENERATOR = new WorldGenPsilophyton();
		protected static final WorldGenAsteroxylon ASTEROXYLON_GENERATOR = new WorldGenAsteroxylon();
		protected static final WorldGenFoozia FOOZIA_GENERATOR = new WorldGenFoozia();
		protected static final WorldGenPertica PERTICA_GENERATOR = new WorldGenPertica();
		protected static final WorldGenLeclercqia LECLERCQIA_GENERATOR = new WorldGenLeclercqia();
		protected static final WorldGenGuangdedendron GUANGDEDENDRON_GENERATOR = new WorldGenGuangdedendron();
		protected static final WorldGenTopSoil TOPSOIL_GENERATOR = new WorldGenTopSoil();
		protected static final WorldGenSandyDirt SANDY_DIRT_GENERATOR = new WorldGenSandyDirt();
		protected static final WorldGenWaterSideSandyDirt WATERSIDE_SANDY_DIRT_GENERATOR = new WorldGenWaterSideSandyDirt();
		protected static final WorldGenAncientMoss ANCIENT_MOSS_GENERATOR = new WorldGenAncientMoss();
		protected static final WorldGenSelaginella SELAGINELLA_GENERATOR = new WorldGenSelaginella();
		protected static final WorldGenIsoetes ISOETES_GENERATOR = new WorldGenIsoetes();
		protected static final WorldGenBaragwanathia BARAGWANATHIA_GENERATOR = new WorldGenBaragwanathia();
		protected static final WorldGenRhynia RHYNIA_GENERATOR = new WorldGenRhynia();
		protected static final WorldGenPrehistoricGroundCoverSandy GROUNDCOVER_GENERATOR = new WorldGenPrehistoricGroundCoverSandy();
		protected static final WorldGenReef REEF_GENERATOR = new WorldGenReef();
		protected static final WorldGenBlueHole BLUE_HOLE_GENERATOR = new WorldGenBlueHole();

		public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	    {
	        return ARCHAEOPTERIS_TREE;
	    }


		@Override
	    public void decorate(World worldIn, Random rand, BlockPos pos)
	    {

			//Spawns forcefully borrow the bush event - why not?
			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH))
			{
				String[] MobString = LepidodendronConfig.dimDevonianMobsBespoke;
				if (LepidodendronConfig.doSpawnsPrehistoricFloraDefault) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimDevonianMobsPF);
				}
				if (LepidodendronConfig.doSpawnsFossilsArcheology) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimDevonianMobsFA);
				}
				if (LepidodendronConfig.doSpawnsReborn) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimDevonianMobsReborn);
				}
				ChunkGenSpawner.executeProcedure(false, MobString, worldIn, topBlock, pos, rand);
			}

	        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        {
	        	int i = rand.nextInt(2);

	            for (int j = 0; j < i; ++j)
	            {
	                int k = rand.nextInt(16) + 8;
	                int l = rand.nextInt(16) + 8;
	                BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
	                if (Math.random() > 0.5) {ARCHAEOPTERIS_LOG_GENERATOR.generate(worldIn, rand, blockpos);}
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
					if (Math.random() > 0.5) {ROTTEN_LOG_GENERATOR.generate(worldIn, rand, blockpos);}
				}
			}
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 5; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            TOPSOIL_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 12; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					SANDY_DIRT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 64; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					WATERSIDE_SANDY_DIRT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 5; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            FOOZIA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 3; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            PERTICA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 5; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            RHACOPHYTON_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 12; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					LECLERCQIA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}

	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 2; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					WATTIEZA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 4; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					CALAMOPHYTON_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 4; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					ANEUROPHYTON_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}

	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 20; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            PSILOPHYTON_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }

	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 20; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            ASTEROXYLON_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }

	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 30; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            GUANGDEDENDRON_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }

	        
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 20; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            GROUNDCOVER_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 8; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            ANCIENT_MOSS_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 8; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            SELAGINELLA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 20; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            ISOETES_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
	        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
	        for (int i = 0; i < 15; ++i)
	        {
	            int j = rand.nextInt(16) + 8;
	            int k = rand.nextInt(16) + 8;
	            int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
	            BARAGWANATHIA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
	        }
			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 15; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					RHYNIA_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}

	        if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.ROCK)) {

				int j = rand.nextInt(16) + 8;
				int k = rand.nextInt(16) + 8;
				int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
				BlockPos pos1 = pos.add(j, l, k);
				if (
					(pos1.getY() < worldIn.getSeaLevel() - 6)
					&& (worldIn.getBlockState(pos1).getMaterial() == Material.WATER)
					&& (worldIn.getBlockState(pos1.up()).getMaterial() == Material.WATER)
					&& (worldIn.getBlockState(pos1.up(2)).getMaterial() == Material.WATER)
				) {
					BLUE_HOLE_GENERATOR.generate(worldIn, rand, pos1, 14);
				}

				for (int i = 0; i < 5; ++i) {
					j = rand.nextInt(16) + 8;
					k = rand.nextInt(16) + 8;
					l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					pos1 = pos.add(j, l, k);
					if (
						(pos1.getY() < worldIn.getSeaLevel() - 5)
						&& (worldIn.getBlockState(pos1).getMaterial() == Material.WATER)
						&& (worldIn.getBlockState(pos1.up()).getMaterial() == Material.WATER)
						&& (worldIn.getBlockState(pos1.up(2)).getMaterial() == Material.WATER)
					) {
						REEF_GENERATOR.generate(worldIn, rand, pos1, 14);
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
