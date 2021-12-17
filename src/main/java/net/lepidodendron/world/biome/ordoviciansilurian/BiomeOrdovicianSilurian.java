package net.lepidodendron.world.biome.ordoviciansilurian;

import net.lepidodendron.util.EnumBiomeTypeOrdovicianSilurian;
import net.minecraft.world.biome.Biome;

public abstract class BiomeOrdovicianSilurian extends Biome {
    public BiomeOrdovicianSilurian(BiomeProperties properties) {
        super(properties);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
    }

    public abstract EnumBiomeTypeOrdovicianSilurian getBiomeType();

}