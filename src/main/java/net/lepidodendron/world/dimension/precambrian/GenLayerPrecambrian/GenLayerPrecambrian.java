package net.lepidodendron.world.dimension.precambrian.GenLayerPrecambrian;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;

public class GenLayerPrecambrian {

    protected GenLayer parent;

    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, String options) {

        GenLayer biomes = new GenLayerPrecambrianBiomes(1L);
        biomes = new GenLayerFuzzyZoom(2000L, biomes);
        biomes = new GenLayerZoom(2001L, biomes);
        biomes = new GenLayerZoom(1000L, biomes);
        //biomes = new GenLayerZoom(1001L, biomes);
        biomes = new GenLayerSmooth(700L, biomes);
        biomes = new GenLayerDiversifyPrecambrian(700L, biomes);
        biomes = new GenLayerZoom(1005L, biomes);
        biomes = new GenLayerSmooth(703L, biomes);
        biomes = new GenLayerFuzzyZoom(1000L, biomes);
        biomes = new GenLayerSmooth(705L, biomes);
        biomes = new GenLayerFuzzyZoom(1001L, biomes);
        biomes = new GenLayerSmooth(706L, biomes);
        biomes = new GenLayerFuzzyZoom(1002L, biomes);
        biomes = new GenLayerZoom(1006L, biomes);
        GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
        biomes.initWorldGenSeed(seed);
        genlayervoronoizoom.initWorldGenSeed(seed);
        return (new GenLayer[] { biomes, genlayervoronoizoom });
    }

}