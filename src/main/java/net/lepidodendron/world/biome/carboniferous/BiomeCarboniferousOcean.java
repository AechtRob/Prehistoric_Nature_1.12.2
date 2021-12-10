
package net.lepidodendron.world.biome.carboniferous;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.BlockPrehistoricGroundLush;
import net.lepidodendron.util.EnumBiomeTypeCarboniferous;
import net.lepidodendron.world.biome.ChunkGenSpawner;
import net.lepidodendron.world.gen.WorldGenCordaites;
import net.lepidodendron.world.gen.WorldGenReef;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BiomeCarboniferousOcean extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:carboniferous_ocean")
	public static final BiomeGenCustom biome = null;
	public BiomeCarboniferousOcean(ElementsLepidodendronMod instance) {
		super(instance, 1591);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.DEAD);
		//BiomeDictionary.addTypes(biome, BiomeDictionary.Type.OCEAN);
		//BiomeDictionary.addTypes(biome, BiomeDictionary.Type.SWAMP);
		//BiomeDictionary.addTypes(biome, BiomeDictionary.Type.JUNGLE);
		//BiomeDictionary.addTypes(biome, BiomeDictionary.Type.LUSH);
	}
	static class BiomeGenCustom extends BiomeCarboniferous {
		public BiomeGenCustom() {
			super(new Biome.BiomeProperties("Carboniferous Deep Ocean").setRainfall(0.5F).setBaseHeight(-1.85F).setHeightVariation(0.21F));
			setRegistryName("carboniferous_ocean");
			topBlock = BlockPrehistoricGroundLush.block.getDefaultState();
			fillerBlock = Blocks.SAND.getStateFromMeta(0);
			decorator.treesPerChunk = -999;
			decorator.flowersPerChunk = 0;
			decorator.grassPerChunk = 0;
			decorator.mushroomsPerChunk = 20;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 0;
			decorator.sandPatchesPerChunk = 0;
			decorator.gravelPatchesPerChunk = 0;
			decorator.clayPerChunk = 0;
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

		protected static final WorldGenCordaites CORDAITES = new WorldGenCordaites(false);
		protected static final WorldGenReef REEF_GENERATOR = new WorldGenReef();

		public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	    {
	    	return CORDAITES;
	    }

		//public Biome.TempCategory getTempCategory()
	    //{
	    //    return Biome.TempCategory.OCEAN;
	   // }

		@Override
	    public void decorate(World worldIn, Random rand, BlockPos pos)
	    {
			//Spawns forcefully borrow the bush event - why not?
			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH))
			{
				String[] MobString = LepidodendronConfig.dimCarboniferousMobsOceanBespoke;
				if (LepidodendronConfig.doSpawnsPrehistoricFloraDefault) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimCarboniferousMobsOceanPF);
				}
				if (LepidodendronConfig.doSpawnsFossilsArcheology) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimCarboniferousMobsOceanFA);
				}
				if (LepidodendronConfig.doSpawnsReborn) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimCarboniferousMobsOceanReborn);
				}
				ChunkGenSpawner.executeProcedure(false, MobString, worldIn, topBlock, pos, rand);
			}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.ROCK))
				for (int i = 0; i < 8; ++i)
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
						REEF_GENERATOR.generate(worldIn, rand, pos1, 3);
					}
				}

	        super.decorate(worldIn, rand, pos);
	    }

		@Override
		public EnumBiomeTypeCarboniferous getBiomeType() {
			return EnumBiomeTypeCarboniferous.Ocean;
		}

	}
}