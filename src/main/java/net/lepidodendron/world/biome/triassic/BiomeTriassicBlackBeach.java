
package net.lepidodendron.world.biome.triassic;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.util.EnumBiomeTypeTriassic;
import net.lepidodendron.world.gen.WorldGenBrachyphyllumTree;
import net.lepidodendron.world.gen.WorldGenNullTree;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BiomeTriassicBlackBeach extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:triassic_beach_black")
	public static final BiomeGenCustom biome = null;
	public BiomeTriassicBlackBeach(ElementsLepidodendronMod instance) {
		super(instance, 1591);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.BEACH);
	}

	static class BiomeGenCustom extends BiomeTriassic {
		public BiomeGenCustom() {
			super(new BiomeProperties("Triassic Pumice Beach").setBaseHeight(-0.05F).setHeightVariation(0.002F).setTemperature(2.8F).setRainfall(1.8F).setWaterColor(13038245));
			setRegistryName("triassic_beach_black");
			topBlock = Blocks.GRAVEL.getDefaultState();
			fillerBlock = Blocks.GRAVEL.getDefaultState();
			decorator.treesPerChunk = -999;
			decorator.flowersPerChunk = 0;
			decorator.grassPerChunk = 0;
			decorator.mushroomsPerChunk = 0;
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

		protected static final WorldGenBrachyphyllumTree BRACHYPHYLLUM = new WorldGenBrachyphyllumTree(false);
		protected static final WorldGenNullTree NULL_TREE = new WorldGenNullTree(false);



		public WorldGenAbstractTree getRandomTreeFeature(Random rand)
	    {
	    	return NULL_TREE;
	    }

		@Override
		public void decorate(World worldIn, Random rand, BlockPos pos)
		{

			//Spawns forcefully borrow the bush event - why not?
			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH))
			{
				String[] MobString = LepidodendronConfig.dimPermianMobsOceanBespoke;
				if (LepidodendronConfig.doSpawnsPrehistoricFloraDefault) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimPermianMobsOceanPF);
				}
				if (LepidodendronConfig.doSpawnsFossilsArcheology) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimPermianMobsOceanFA);
				}
				if (LepidodendronConfig.doSpawnsReborn) {
					MobString = ArrayUtils.addAll(MobString, LepidodendronConfig.dimPermianMobsOceanReborn);
				}
				//ChunkGenSpawner.executeProcedure(false, MobString, worldIn, topBlock, pos, rand);
			}



			super.decorate(worldIn, rand, pos);
		}

		@Override
		public EnumBiomeTypeTriassic getBiomeType() {
			return EnumBiomeTypeTriassic.Ocean;
		}

	}

}
