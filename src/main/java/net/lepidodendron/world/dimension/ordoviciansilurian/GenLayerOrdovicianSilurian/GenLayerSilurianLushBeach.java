package net.lepidodendron.world.dimension.ordoviciansilurian.GenLayerOrdovicianSilurian;

//import net.lepidodendron.world.biome.devonian.
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSilurianLushBeach extends GenLayer
{

    public  Biome OS_OCEAN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:ordovician_silurian_sea"));
    public  int OS_OCEAN_ID =  Biome.getIdForBiome(OS_OCEAN);
    public  Biome OS_OCEAN_ICE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:ordovician_silurian_sea_ice"));
    public  int OS_OCEAN_ICE_ID =  Biome.getIdForBiome(OS_OCEAN_ICE);

    public  Biome OS_LAND = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:ordovician_silurian_land"));
    public  int OS_LAND_ID =  Biome.getIdForBiome(OS_LAND);

    public  Biome OS_LUSH = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:silurian_lush_patch"));
    public  int OS_LUSH_ID =  Biome.getIdForBiome(OS_LUSH);

    private final int LandBiomes[] = new int[] {
            OS_LAND_ID,
            OS_LAND_ID,
            OS_LAND_ID,
            OS_LAND_ID,
            OS_LAND_ID,
            OS_LAND_ID,
            OS_LAND_ID,
            OS_LAND_ID,
            OS_LUSH_ID
    };

    public GenLayerSilurianLushBeach(long seed, GenLayer genLayer)
    {
        super(seed);
        this.parent = genLayer;
    }

    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                this.initChunkSeed((long)(j + areaX), (long)(i + areaY));
                int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];
                Biome biome = Biome.getBiome(k);

                if (k == OS_LAND_ID)
                {
                    int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                    if (!isOcean(l1) && !isOcean(k2) && !isOcean(j3) && !isOcean(i4))
                    {
                        aint1[j + i * areaWidth] = k;
                    }
                    else
                    {
                        aint1[j + i * areaWidth] = LandBiomes[nextInt(LandBiomes.length)];
                    }
                }
                else
                {
                    aint1[j + i * areaWidth] = k;
                }
            }
        }

        return aint1;
    }

    private boolean isOcean(int biomeID) {
        if (biomeID == OS_OCEAN_ID || biomeID == OS_OCEAN_ICE_ID) {
            return true;
        }
        return false;
    }

}
