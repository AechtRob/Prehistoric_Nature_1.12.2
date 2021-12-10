package net.lepidodendron.world.dimension.permian.GenLayerPermian;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerPermianShallowOcean extends GenLayer
{

    public Biome PERMIAN_OCEAN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:permian_ocean"));
    public  int PERMIAN_OCEAN_ID =  Biome.getIdForBiome(PERMIAN_OCEAN);
    public  Biome PERMIAN_OCEAN_SHORE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:permian_ocean_shore"));
    public  int PERMIAN_OCEAN_SHORE_ID =  Biome.getIdForBiome(PERMIAN_OCEAN_SHORE);
    public  Biome PERMIAN_MOUNTAINS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:permian_mountains"));
    public  int PERMIAN_MOUNTAINS_ID =  Biome.getIdForBiome(PERMIAN_MOUNTAINS);

    public GenLayerPermianShallowOcean(long seed, GenLayer genLayer)
    {
        super(seed);
        this.parent = genLayer;
    }

    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        return this.getIntsOcean(areaX, areaY, areaWidth, areaHeight);
    }

    private int[] getIntsOcean(int p_151626_1_, int p_151626_2_, int p_151626_3_, int p_151626_4_)
    {
        int i = p_151626_1_ - 1;
        int j = p_151626_2_ - 1;
        int k = 1 + p_151626_3_ + 1;
        int l = 1 + p_151626_4_ + 1;
        int[] aint = this.parent.getInts(i, j, k, l);
        int[] aint1 = IntCache.getIntCache(p_151626_3_ * p_151626_4_);

        for (int i1 = 0; i1 < p_151626_4_; ++i1)
        {
            for (int j1 = 0; j1 < p_151626_3_; ++j1)
            {
                this.initChunkSeed((long)(j1 + p_151626_1_), (long)(i1 + p_151626_2_));
                int k1 = aint[j1 + 1 + (i1 + 1) * k];

                if (k1 == PERMIAN_OCEAN_ID)
                {
                    int l1 = aint[j1 + 1 + (i1 + 1 - 1) * k];
                    int i2 = aint[j1 + 1 + 1 + (i1 + 1) * k];
                    int j2 = aint[j1 + 1 - 1 + (i1 + 1) * k];
                    int k2 = aint[j1 + 1 + (i1 + 1 + 1) * k];
                    boolean flag = (
                        (!hasDeepSea(l1))
                        || (!hasDeepSea(i2))
                        || (!hasDeepSea(j2))
                        || (!hasDeepSea(k2))
                    );
                    if (flag)
                    {
                        k1 = PERMIAN_OCEAN_SHORE_ID;
                    }
                }

                aint1[j1 + i1 * p_151626_3_] = k1;
            }
        }

        return aint1;
    }

    private boolean hasDeepSea(int biomeID) {
        if (biomeID == PERMIAN_MOUNTAINS_ID || biomeID == PERMIAN_OCEAN_ID || biomeID == PERMIAN_OCEAN_SHORE_ID) {
            return true;
        }
        return false;
    }
    
}
