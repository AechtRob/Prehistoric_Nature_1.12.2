package net.lepidodendron.world.dimension.ordoviciansilurian.GenLayerOrdovicianSilurian;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;

public class GenLayerOrdovicianSilurian {

    protected GenLayer parent;

    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, String options) {

        GenLayer biomes = new GenLayerOrdovicianSilurianBiomes(1L);
        biomes = new GenLayerFuzzyZoom(2000L, biomes);
        biomes = new GenLayerZoom(2001L, biomes);
        biomes = new GenLayerZoom(1000L, biomes);
        //biomes = new GenLayerZoom(1001L, biomes);
        biomes = new GenLayerSmooth(700L, biomes);
        //icebergs here:
        biomes = new GenLayerIcebergsOrdovician(666L, biomes);
        //lush patch here:
        biomes = new GenLayerLushPatchSilurian(666L, biomes);
        biomes = new GenLayerZoom(1005L, biomes);
        biomes = new GenLayerSmooth(703L, biomes);
        //"beach" here:
        biomes = new GenLayerSilurianLushBeach(666L, biomes);
        //another lush patch here:
        biomes = new GenLayerLushPatchSilurian(669L, biomes);
        biomes = new GenLayerFuzzyZoom(1000L, biomes);
        biomes = new GenLayerSilurianLushBeach(666L, biomes);
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