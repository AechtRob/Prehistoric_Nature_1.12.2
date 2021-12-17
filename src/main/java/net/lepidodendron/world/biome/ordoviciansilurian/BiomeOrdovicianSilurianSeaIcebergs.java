
package net.lepidodendron.world.biome.ordoviciansilurian;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.util.EnumBiomeTypeOrdovicianSilurian;
import net.lepidodendron.world.biome.ChunkGenSpawner;
import net.lepidodendron.world.gen.*;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BiomeOrdovicianSilurianSeaIcebergs extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:ordovician_silurian_sea_icebergs")
	public static final BiomeGenCustom biome = null;
	public BiomeOrdovicianSilurianSeaIcebergs(ElementsLepidodendronMod instance) {
		super(instance, 1591);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.OCEAN);
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.WATER);
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.COLD);
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.SNOWY);
	}

	static class BiomeGenCustom extends BiomeOrdovicianSilurian {
		public BiomeGenCustom() {
			super(new BiomeProperties("Ordovician and Silurian Icebergs").setRainfall(0.5F).setBaseHeight(-0.8F).setHeightVariation(0.05F).setTemperature(-1.8F));
			setRegistryName("ordovician_silurian_sea_icebergs");
			topBlock = Blocks.GRAVEL.getDefaultState();
			fillerBlock = Blocks.STONE.getStateFromMeta(0);
			decorator.treesPerChunk = -999;
			decorator.flowersPerChunk = 0;
			decorator.grassPerChunk = 0;
			decorator.mushroomsPerChunk = 0;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 0;
			decorator.sandPatchesPerChunk = 300;
			decorator.gravelPatchesPerChunk = 0;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
		}
		
		protected static final WorldGenArchaeopterisTree ARCHAEOPTERIS_TREE = new WorldGenArchaeopterisTree(false);
		protected static final WorldGenReef REEF_GENERATOR = new WorldGenReef();
		protected static final WorldGenIceOnSea ICE_GENERATOR = new WorldGenIceOnSea();
		protected static final WorldGenSnow SNOW_GENERATOR = new WorldGenSnow();
		protected static final WorldGenIcebergs ICEBERG_GENERATOR = new WorldGenIcebergs();

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
				String[] MobString = LepidodendronConfig.dimOrdovicianSilurianMobsOceanIceBespoke;
				if (LepidodendronConfig.doSpawnsPrehistoricFloraDefault) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimOrdovicianSilurianMobsOceanIcePF);
				}
				if (LepidodendronConfig.doSpawnsFossilsArcheology) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimOrdovicianSilurianMobsFA);
				}
				if (LepidodendronConfig.doSpawnsReborn) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimOrdovicianSilurianMobsReborn);
				}
				ChunkGenSpawner.executeProcedure(false, MobString, worldIn, topBlock, pos, rand);
			}

			if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ICE)) {
				{
					int i = rand.nextInt(24);

					for (int j = 0; j < i; ++j) {
						int k = rand.nextInt(16) + 8;
						int l = rand.nextInt(16) + 8;
						BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
						SNOW_GENERATOR.generate(worldIn, rand, blockpos, 0);
					}
					//}

					//{
					i = rand.nextInt(32);

					for (int j = 0; j < i; ++j) {
						int k = rand.nextInt(16) + 8;
						int l = rand.nextInt(16) + 8;
						BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
						ICE_GENERATOR.generate(worldIn, rand, blockpos,0);
					}

					i = rand.nextInt(8);

					for (int j = 0; j < i; ++j) {
						int k = rand.nextInt(16) + 8;
						int l = rand.nextInt(16) + 8;
						BlockPos blockpos = worldIn.getHeight(pos.add(k, 0, l));
						ICEBERG_GENERATOR.generate(worldIn, rand, blockpos);
					}
				}
			}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ROCK))
				for (int i = 0; i < 2; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					BlockPos pos1 = pos.add(j, l, k);
					if (
							(pos1.getY() < worldIn.getSeaLevel() - 5)
									&& (worldIn.getBlockState(pos1).getMaterial() == Material.WATER)
									&& (worldIn.getBlockState(pos1.up()).getMaterial() == Material.WATER)
									&& (worldIn.getBlockState(pos1.up(2)).getMaterial() == Material.WATER)
					) {
						REEF_GENERATOR.generate(worldIn, rand, pos1, 2);
					}
				}

			super.decorate(worldIn, rand, pos);
		}

		@Override
		public EnumBiomeTypeOrdovicianSilurian getBiomeType() {
			return EnumBiomeTypeOrdovicianSilurian.Ocean;
		}
	}

}
