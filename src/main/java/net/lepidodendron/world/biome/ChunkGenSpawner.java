package net.lepidodendron.world.biome;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraDiictodon;
import net.lepidodendron.entity.base.EntityPrehistoricFloraLandBase;
import net.lepidodendron.util.EnumBiomeTypeOrdovicianSilurian;
import net.lepidodendron.world.biome.cambrian.BiomeCambrianBiome;
import net.lepidodendron.world.biome.cambrian.BiomeCambrianSea;
import net.lepidodendron.world.biome.carboniferous.BiomeCarboniferous;
import net.lepidodendron.world.biome.carboniferous.BiomeCarboniferousOcean;
import net.lepidodendron.world.biome.carboniferous.BiomeCarboniferousOceanShore;
import net.lepidodendron.world.biome.devonian.BiomeDevonian;
import net.lepidodendron.world.biome.devonian.BiomeDevonianOcean;
import net.lepidodendron.world.biome.devonian.BiomeDevonianOceanDeep;
import net.lepidodendron.world.biome.ordoviciansilurian.BiomeOrdovicianSilurian;
import net.lepidodendron.world.biome.permian.BiomePermian;
import net.lepidodendron.world.biome.permian.BiomePermianOcean;
import net.lepidodendron.world.biome.permian.BiomePermianOceanShore;
import net.lepidodendron.world.biome.triassic.BiomeTriassic;
import net.lepidodendron.world.biome.triassic.BiomeTriassicOcean;
import net.lepidodendron.world.biome.triassic.BiomeTriassicOceanShore;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class ChunkGenSpawner extends ElementsLepidodendronMod.ModElement {
    public ChunkGenSpawner(ElementsLepidodendronMod instance) {
        super(instance, 42);
    }

    public static void executeProcedure(boolean onlyWater, String[] MobString, World world, IBlockState topBlock, BlockPos pos, Random rand) {

        String mobToSpawn;
        String nbtStr = "";
        int locationID = 1;
        if (onlyWater) {locationID = 4;}
        boolean errFound;
        boolean posCheck;
        int strPos1;
        int strPos2;
        int strPos3;
        int strPos4;
        int strPos5;

        String[] var2 = MobString;
        //Shuffle the string so we pick a random mob later:
        List<String> list = Arrays.asList(var2);
        Collections.shuffle(list);
        list.toArray(var2);

        int var3 = var2.length;
        for(int var4 = 0; var4 < var3; ++var4) {
            errFound = false;
            String checkEntity = var2[var4].trim();
            if (onlyWater) {
                checkEntity = var2[var4].trim() + ":4";
            }

            strPos1 = 0;
            strPos2 = 0;
            strPos3 = 0;
            strPos4 = 0;
            strPos5 = 0;
            nbtStr = "";

            strPos1 = checkEntity.indexOf(":");
            if (!(strPos1 > 0)) {
                errFound = true;
            }
            else {
                String modid = checkEntity.substring(0, strPos1);

                //Get the last two bits, first the rightmost colon:
                for(int i=checkEntity.length(); i > 0; i--){
                    if(checkEntity.charAt(i - 1) == ':'){
                        strPos2 = i;
                        break;
                    }
                }

                if (strPos2 <= strPos1) {
                    //The position means that something is missing
                    errFound = true;
                }
                else {
                    String locationStr = checkEntity.substring(checkEntity.length()-1, checkEntity.length());
                    if (!(locationStr.equals((String) "1") || locationStr.equals((String) "2") || locationStr.equals((String) "3") ||locationStr.equals((String) "4"))) {
                        errFound = true;
                    }
                    else {
                        locationID =  (int) Integer.parseInt(locationStr);

                        //Get the next last bit:
                        strPos3 = 0;
                        String tmpStr = checkEntity.substring(0,checkEntity.length()-2);
                        for(int i=tmpStr.length(); i > 0; i--){
                            if(tmpStr.charAt(i - 1) == ':'){
                                strPos3 = i;
                                break;
                            }
                        }
                        if (strPos3 <= strPos1) {
                            //The position means that something is missing
                            errFound = true;
                        }
                        else {
                            tmpStr = tmpStr.substring(strPos3, tmpStr.length());
                            if (!isNumeric(tmpStr)) {
                                errFound = true;
                            }
                            else {
                                int weight = (int) Integer.parseInt(tmpStr);
                                if (weight < 0 || weight > 100) {
                                    errFound = true;
                                }
                                else {

                                    //Get the next last bit:
                                    strPos5 = 0;
                                    tmpStr = checkEntity.substring(0, strPos3 - 1);
                                    for(int i=tmpStr.length(); i > 0; i--){
                                        if(tmpStr.charAt(i - 1) == ':'){
                                            strPos5 = i;
                                            break;
                                        }
                                    }
                                    if (strPos5 <= strPos1) {
                                        //The position means that something is missing
                                        errFound = true;
                                    }
                                    else {
                                        tmpStr = tmpStr.substring(strPos5, tmpStr.length());
                                        if (!isNumeric(tmpStr)) {
                                            errFound = true;
                                        } else {
                                            int maxSpawn = (int) Integer.parseInt(tmpStr);
                                            if (maxSpawn < 1 || maxSpawn > 20) {
                                                errFound = true;
                                            }

                                            //Everything left is the mod plus nbt (which may be wrong of course!)
                                            else { //Get the mob:

                                                mobToSpawn = checkEntity.substring(0, strPos5 - 1);
                                                //Is there nbt?
                                                strPos4 = 0;
                                                strPos4 = mobToSpawn.indexOf("{");
                                                if (strPos4 > 0) {
                                                    //we have nbt:
                                                    nbtStr = mobToSpawn.substring(strPos4, mobToSpawn.length());
                                                    mobToSpawn = mobToSpawn.substring(0, strPos4);
                                                }

                                                if (findEntity(mobToSpawn) != null) {
                                                    //Spawn routine (finally!)

                                                    int k7 = rand.nextInt(16) + 8;
                                                    int j11 = rand.nextInt(16) + 8;
                                                    int l14 = world.getHeight(pos.add(k7, 0, j11)).getY() * 2;
                                                    int i2;

                                                    if (l14 > 0) {
                                                        int i18 = rand.nextInt(l14);

                                                        //System.err.println(locationID);

                                                        switch (locationID) {

                                                            case 1: //Land
                                                            default:
                                                                //System.err.println("Case: 1");
                                                                //We'll check a block is the biome topblock and that there are at least 4 blocks of non-solid blocks above it:
                                                                posCheck = false;
                                                                i2 = 0;
                                                                while (i2 < 14 && !posCheck) {
                                                                    k7 = rand.nextInt(16) + 8;
                                                                    j11 = rand.nextInt(16) + 8;
                                                                    i18 = 0;

                                                                    while (i18 < 4 && !posCheck) {
                                                                        i18 = i18 + 1;
                                                                        BlockPos pos1 = world.getTopSolidOrLiquidBlock(new BlockPos(pos.getX() + k7, 0, pos.getZ() + j11));
                                                                        //BlockPos pos1 = new BlockPos(pos.getX() + k7, i18, pos.getZ() + j11);

                                                                        //System.err.println("block: " + (world.getBlockState(pos1).getBlock()) + " " + pos1.getX() + " " + pos1.getY() + " " + pos1.getZ());
                                                                        if (
                                                                            //(world.getBlockState(pos1.down()).getBlock() == topBlock.getBlock())
                                                                            world.getBlockState(pos1.down()).getBlock().isSideSolid(world.getBlockState(pos1.down()), world, pos1.down(), EnumFacing.UP)
                                                                            && (world.getBlockState(pos1.down()).getMaterial() != Material.WOOD)
                                                                            && ((world.isAirBlock(pos1)) || (world.getBlockState(pos1).getMaterial() == Material.PLANTS))
                                                                            && ((world.isAirBlock(pos1.up())) || (world.getBlockState(pos1.up()).getMaterial() == Material.PLANTS))
                                                                            && ((world.isAirBlock(pos1.up(2))) || (world.getBlockState(pos1.up(2)).getMaterial() == Material.PLANTS))
                                                                            && ((world.isAirBlock(pos1.up(3))) || (world.getBlockState(pos1.up(3)).getMaterial() == Material.PLANTS))
                                                                        ) {
                                                                            posCheck = true;
                                                                            //System.err.println("Spawnable " + checkEntity);
                                                                            k7 = pos1.getX() - pos.getX();
                                                                            i18 = pos1.getY() - pos.getY();
                                                                            j11 = pos1.getZ() - pos.getZ();
                                                                        }
                                                                    }
                                                                    i2 = i2 + 1;
                                                                }
                                                                break;

                                                            case 2: //Deep water
                                                                //System.err.println("Case: 2");
                                                                posCheck = false;
                                                                i2 = 0;
                                                                while (i2 < 14 && !posCheck) {
                                                                    k7 = rand.nextInt(16) + 8;
                                                                    j11 = rand.nextInt(16) + 8;

                                                                    i18 = 1;
                                                                    while (i18 < 255 && !posCheck) {
                                                                        i18 = i18 + 1;
                                                                        BlockPos pos1 = new BlockPos(pos.getX() + k7, i18, pos.getZ() + j11);
                                                                        //System.err.println("y: " + i18);
                                                                        //System.err.println("block: " + (worldIn.getBlockState(pos1).getBlock()));
                                                                        //System.err.println("topblock: " + topBlock.getBlock());

                                                                        //First check if we are in an ocean biome with distinct shallow and deep parts:
                                                                        Biome biome = world.getBiome(pos1);
                                                                        if (biome instanceof BiomeDevonian || biome instanceof BiomeCarboniferous || biome instanceof BiomePermian || biome instanceof BiomeTriassic) {
                                                                            if (biome == BiomeDevonianOceanDeep.biome ||biome == BiomeCarboniferousOcean.biome || biome == BiomePermianOcean.biome || biome == BiomeTriassicOcean.biome) {
                                                                                if ((world.getBlockState(pos1).getMaterial() == Material.WATER)
                                                                                && (pos1.getY() > world.getSeaLevel() - 40)) {
                                                                                    EntityEntry ee = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(mobToSpawn));
                                                                                    EntityLiving entity = (EntityLiving) ee.newInstance(world);
                                                                                    if (entity.height < 0.9) {
                                                                                        posCheck = true;
                                                                                    } else if (world.getBlockState(pos1.up()).getMaterial() == Material.WATER) {
                                                                                        posCheck = true;
                                                                                    }
                                                                                }
                                                                            }
                                                                            else if ((world.getBlockState(pos1).getMaterial() == Material.WATER)
                                                                                    && (world.isAirBlock(pos1.up(3)) || world.getBlockState(pos1.up(3)).getMaterial() == Material.ICE)
                                                                                    && (world.getBlockState(pos1.up()).getMaterial() == Material.WATER)
                                                                                    && (world.getBlockState(pos1.up(2)).getMaterial() == Material.WATER)
                                                                                    && (world.getBlockState(pos1.down()).getMaterial() == Material.WATER)
                                                                                    && (world.getBlockState(pos1.down(2)).getMaterial() == Material.WATER)
                                                                            ) {
                                                                                posCheck = true;
                                                                            }
                                                                        }
                                                                        else if ((world.getBlockState(pos1).getMaterial() == Material.WATER)
                                                                                && (world.isAirBlock(pos1.up(3)) || world.getBlockState(pos1.up(3)).getMaterial() == Material.ICE)
                                                                                && (world.getBlockState(pos1.up()).getMaterial() == Material.WATER)
                                                                                && (world.getBlockState(pos1.up(2)).getMaterial() == Material.WATER)
                                                                                && (world.getBlockState(pos1.down()).getMaterial() == Material.WATER)
                                                                                && (world.getBlockState(pos1.down(2)).getMaterial() == Material.WATER)
                                                                        ) {
                                                                            posCheck = true;
                                                                        }

                                                                        int xx = -4;
                                                                        while (xx <= 4 && posCheck) {
                                                                            int zz = -4;
                                                                            while (zz <= 4 && posCheck) {
                                                                                if (world.getBlockState(pos1.add(xx, 0, zz)).getMaterial() != Material.WATER) {
                                                                                    posCheck = false;
                                                                                }
                                                                                zz = zz + 1;
                                                                            }
                                                                            xx = xx + 1;
                                                                        }
                                                                    }
                                                                    i2 = i2 + 1;
                                                                }
                                                                break;

                                                            case 3: //Shallow water
                                                                //System.err.println("Case: 3");
                                                                posCheck = false;
                                                                i2 = 0;
                                                                while (i2 < 14 && !posCheck) {
                                                                    k7 = rand.nextInt(16) + 8;
                                                                    j11 = rand.nextInt(16) + 8;

                                                                    i18 = 1;
                                                                    while (i18 < 255 && !posCheck) {
                                                                        i18 = i18 + 1;
                                                                        BlockPos pos1 = new BlockPos(pos.getX() + k7, i18, pos.getZ() + j11);
                                                                        //System.err.println("y: " + i18);
                                                                        //System.err.println("block: " + (worldIn.getBlockState(pos1).getBlock()));
                                                                        //System.err.println("topblock: " + topBlock.getBlock());
                                                                        //First check if we are in an ocean biome with distinct shallow and deep parts:
                                                                        Biome biome = world.getBiome(pos1);
                                                                        if (biome instanceof BiomeDevonian || biome instanceof BiomeCarboniferous || biome instanceof BiomePermian || biome instanceof BiomeTriassic) {
                                                                            if (biome == BiomeDevonianOcean.biome || biome == BiomeCarboniferousOceanShore.biome || biome == BiomePermianOceanShore.biome || biome == BiomeTriassicOceanShore.biome) {
                                                                                if ((world.getBlockState(pos1).getMaterial() == Material.WATER)
                                                                                    && (pos1.getY() > world.getSeaLevel() - 12)) {
                                                                                    EntityEntry ee = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(mobToSpawn));
                                                                                    EntityLiving entity = (EntityLiving) ee.newInstance(world);
                                                                                    if (entity.height < 0.9) {
                                                                                        posCheck = true;
                                                                                    } else if (world.getBlockState(pos1.up()).getMaterial() == Material.WATER) {
                                                                                        posCheck = true;
                                                                                    }
                                                                                }
                                                                            }
                                                                            else if ((world.getBlockState(pos1).getMaterial() == Material.WATER)
                                                                                    && (world.isAirBlock(pos1.up()) || world.getBlockState(pos1.up()).getMaterial() == Material.ICE)
                                                                                    && (pos1.up(2).getY() >= world.getSeaLevel())
                                                                                    && (world.getBlockState(pos1.down(4)).getMaterial() != Material.WATER)
                                                                            ) {
                                                                                posCheck = true;
                                                                            }
                                                                        }
                                                                        else if (biome instanceof BiomeOrdovicianSilurian) {
                                                                            BiomeOrdovicianSilurian biomeOS = (BiomeOrdovicianSilurian) biome;
                                                                            if (biomeOS.getBiomeType() == EnumBiomeTypeOrdovicianSilurian.Ocean) {
                                                                                if ((world.getBlockState(pos1).getMaterial() == Material.WATER)
                                                                                        && (!world.isAirBlock(pos1.up()))
                                                                                        && (pos1.getY() > world.getSeaLevel() - 25)) {
                                                                                    EntityEntry ee = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(mobToSpawn));
                                                                                    EntityLiving entity = (EntityLiving) ee.newInstance(world);
                                                                                    if (entity.height < 0.9) {
                                                                                        posCheck = true;
                                                                                    } else if (world.getBlockState(pos1.up()).getMaterial() == Material.WATER) {
                                                                                        posCheck = true;
                                                                                    }
                                                                                }
                                                                            }
                                                                            else if ((world.getBlockState(pos1).getMaterial() == Material.WATER)
                                                                                    && (world.isAirBlock(pos1.up()) || world.getBlockState(pos1.up()).getMaterial() == Material.ICE)
                                                                                    && (pos1.up(2).getY() >= world.getSeaLevel())
                                                                                    && (world.getBlockState(pos1.down(4)).getMaterial() != Material.WATER)
                                                                            ) {
                                                                                posCheck = true;
                                                                            }
                                                                        }
                                                                        else if (biome == BiomeCambrianSea.biome || biome == BiomeCambrianBiome.biome) {
                                                                            if ((world.getBlockState(pos1).getMaterial() == Material.WATER)
                                                                                    && (!world.isAirBlock(pos1.up()))
                                                                                    && (pos1.getY() > world.getSeaLevel() - 25)) {
                                                                                EntityEntry ee = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(mobToSpawn));
                                                                                EntityLiving entity = (EntityLiving) ee.newInstance(world);
                                                                                if (entity.height < 0.9) {
                                                                                    posCheck = true;
                                                                                } else if (world.getBlockState(pos1.up()).getMaterial() == Material.WATER) {
                                                                                    posCheck = true;
                                                                                }
                                                                            }
                                                                        }
                                                                        else if ((world.getBlockState(pos1).getMaterial() == Material.WATER)
                                                                            && (world.isAirBlock(pos1.up()) || world.getBlockState(pos1.up()).getMaterial() == Material.ICE)
                                                                            && (pos1.up(2).getY() >= world.getSeaLevel())
                                                                            && (world.getBlockState(pos1.down(4)).getMaterial() != Material.WATER)
                                                                        ) {
                                                                            posCheck = true;
                                                                        }


                                                                        int xx = -1;
                                                                        while (xx <= 1 && posCheck) {
                                                                            int zz = -1;
                                                                            while (zz <= 1 && posCheck) {
                                                                                if (world.getBlockState(pos1.add(xx, 0, zz)).getMaterial() != Material.WATER) {
                                                                                    posCheck = false;
                                                                                }
                                                                                zz = zz + 1;
                                                                            }
                                                                            xx = xx + 1;
                                                                        }
                                                                    }
                                                                    i2 = i2 + 1;
                                                                }
                                                                break;

                                                            case 4: //In a 5x5 block of water anywhere under sea-level
                                                                //System.err.println("Case: 4");
                                                                posCheck = false;

                                                                i2 = 0;
                                                                while (i2 < 14 && !posCheck) {
                                                                    k7 = rand.nextInt(16) + 8;
                                                                    j11 = rand.nextInt(16) + 8;

                                                                    i18 = 1;
                                                                    while (i18 < 255 && !posCheck) {
                                                                        i18 = i18 + 1;
                                                                        BlockPos pos1 = new BlockPos(pos.getX() + k7, i18, pos.getZ() + j11);
                                                                        //System.err.println("y: " + i18);
                                                                        //System.err.println("block: " + (worldIn.getBlockState(pos1).getBlock()));
                                                                        //System.err.println("topblock: " + topBlock.getBlock());
                                                                        if ((world.getBlockState(pos1).getMaterial() == Material.WATER)
                                                                                && (world.getBlockState(pos1.up()).getMaterial() == Material.WATER)
                                                                                && (world.getBlockState(pos1.up(2)).getMaterial() == Material.WATER)
                                                                                && (world.getBlockState(pos1.down()).getMaterial() == Material.WATER)
                                                                                && (world.getBlockState(pos1.down(2)).getMaterial() == Material.WATER)
                                                                                && (pos1.getY() < (world.getSeaLevel() - 2))
                                                                        ) {
                                                                            posCheck = true;
                                                                        }
                                                                        int xx = -2;
                                                                        while (xx <= 2 && posCheck) {
                                                                            int zz = -2;
                                                                            while (zz <= 2 && posCheck) {
                                                                                if (world.getBlockState(pos1.add(xx, 0, zz)).getMaterial() != Material.WATER) {
                                                                                    posCheck = false;
                                                                                }
                                                                                zz = zz + 1;
                                                                            }
                                                                            xx = xx + 1;
                                                                        }
                                                                    }
                                                                    i2 = i2 + 1;
                                                                }
                                                                break;

                                                        }

                                                        //System.err.println("Poscheck: " + posCheck);
                                                        //System.err.println("errFound: " + errFound);
                                                        if (posCheck && !errFound) {
                                                            //System.err.println("Spawning " + mobToSpawn + " with locationID " + locationID + " at: " + pos.add(k7, i18, j11).getX() + " " + pos.add(k7, i18, j11).getY() + " " + pos.add(k7, i18, j11).getZ());
                                                            //System.err.println("maxSpawn: " + maxSpawn);
                                                            //System.err.println("weight: " + weight);
                                                            double weighter = 500;
                                                            if (locationID == 2) {
                                                                weighter = 800;
                                                            }
                                                            if (locationID == 1) {
                                                                weighter = 100;
                                                            }
                                                            if ((Math.random() * weighter) <= (double)weight) {
                                                                //System.err.println("Trying......");
                                                                int spawnQty = rand.nextInt(maxSpawn) + 1;
                                                                pos = pos.add(k7, i18, j11);
                                                                //System.err.println("spawnQty: " + spawnQty);
                                                                //Check if an associated block or structure is required:
                                                                EntityEntry ee = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(mobToSpawn));
                                                                EntityLiving entity = (EntityLiving) ee.newInstance(world);
                                                                if (entity instanceof EntityPrehistoricFloraDiictodon) {
                                                                    EntityPrehistoricFloraLandBase EntityLandBase = (EntityPrehistoricFloraLandBase) entity;
                                                                    if (EntityLandBase.getNestBlock() != null && (EntityLandBase.homesToNest()) ) {
                                                                        //Spawn a nest and burrow for the:
                                                                        IBlockState NestBlock = EntityLandBase.getNestBlock().getDefaultState();
                                                                        //Buildburrow:
                                                                        pos = EntityPrehistoricFloraDiictodon.buildBurrow(world, pos);
                                                                        world.setBlockState(pos, NestBlock);
                                                                    }
                                                                }
                                                                else if (entity instanceof EntityPrehistoricFloraLandBase) {
                                                                    if (Math.random() > 0.8) { // 1:5 chance of  nest coming too
                                                                        EntityPrehistoricFloraLandBase EntityLandBase = (EntityPrehistoricFloraLandBase) entity;
                                                                        if (EntityLandBase.getNestBlock() != null) {
                                                                            //Spawn a nest under the mob:
                                                                            IBlockState NestBlock = EntityLandBase.getNestBlock().getDefaultState();
                                                                            world.setBlockState(pos, NestBlock);
                                                                        }
                                                                    }
                                                                }
                                                                for (int i = 0; i < spawnQty; ++i) {
                                                                    //Spawn the mob via a command:
                                                                    if (!world.isRemote && world.getMinecraftServer() != null) {
                                                                        //System.err.println("summon " + mobToSpawn + " " + pos.add(k7, i18, j11).getX() + " " + pos.add(k7, i18, j11).getY() + " " + pos.add(k7, i18, j11).getZ() + " " + nbtStr);
                                                                        world.getMinecraftServer().getCommandManager().executeCommand(new ICommandSender() {
                                                                            @Override
                                                                            public String getName() {
                                                                                return "";
                                                                            }

                                                                            @Override
                                                                            public boolean canUseCommand(int permission, String command) {
                                                                                return true;
                                                                            }

                                                                            @Override
                                                                            public World getEntityWorld() {
                                                                                return world;
                                                                            }

                                                                            @Override
                                                                            public MinecraftServer getServer() {
                                                                                return world.getMinecraftServer();
                                                                            }

                                                                            @Override
                                                                            public boolean sendCommandFeedback() {
                                                                                return false;
                                                                            }

                                                                        }, "pf_summon " + mobToSpawn + " " + pos.getX() + " " + pos.getY() + " " + pos.getZ() + " " + nbtStr);
                                                                    }
                                                                    //System.err.println("Spawned at " + pos.add(k7, i18, j11).getX() + " " + pos.add(k7, i18, j11).getY() + " " + pos.add(k7, i18, j11).getZ());

                                                                }
                                                                return; //Stop as we have spawned our group in this chunk now
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (errFound) {
                System.err.println("Syntax error in mob spawn config: '" + checkEntity + "'!");
            }
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
