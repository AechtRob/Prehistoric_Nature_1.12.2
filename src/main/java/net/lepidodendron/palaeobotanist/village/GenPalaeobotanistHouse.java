package net.lepidodendron.palaeobotanist.village;

import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.palaeobotanist.entity.Villager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.Random;

public class GenPalaeobotanistHouse extends WorldGenerator {

    private static final ResourceLocation HOUSE = new ResourceLocation(LepidodendronMod.MODID, "palaeobotanisthouse");
    private static final ResourceLocation CHEST = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "palaeobotanist_chest"));
    private VillageComponentPalaeobotanistHouse component;
    private Rotation rotation;
    private EnumFacing facing;

    public GenPalaeobotanistHouse(VillageComponentPalaeobotanistHouse component, EnumFacing facing) {
        this.component = component;
        this.facing = facing;
        switch (facing) {
            case SOUTH:
                rotation = Rotation.CLOCKWISE_180;
                break;
            case EAST:
                rotation = Rotation.CLOCKWISE_90;
                break;
            case WEST:
                rotation = Rotation.COUNTERCLOCKWISE_90;
                break;
            default:
                rotation = Rotation.NONE;
                break;
        }
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (worldIn == null) {
            return false;
        }
        double chance = (double) LepidodendronConfig.genPalaeobotanist;
        if (chance < 0) {chance = 0D;}
        if (chance > 100) {chance = 100D;}
        chance = chance/100D;
        if (Math.random() > chance) {
            return true;
        }
        MinecraftServer server = worldIn.getMinecraftServer();
        TemplateManager templateManager = worldIn.getSaveHandler().getStructureTemplateManager();
        PlacementSettings settings = new PlacementSettings().setRotation(rotation).setMirror(Mirror.NONE);
        Template template = templateManager.getTemplate(server, HOUSE);
        Biome biome = worldIn.getBiome(position);
        int xSize = template.getSize().getX() / 2;
        int zSize = template.getSize().getZ() / 2;

        //System.err.println("Spawn house");

        template.addBlocksToWorld(worldIn, position.up(3).offset(EnumFacing.NORTH, xSize).offset(EnumFacing.SOUTH, zSize), new PalaeobotanisthouseBlocks(position.up(3), settings, CHEST, biome, facing.getOpposite()), settings, 2);

        BlockPos center = position.add(-template.getSize().getX() / 2, 4, -template.getSize().getZ() / 2);

        if (component.villagerCount < 1) {
            EntityVillager villager = new EntityVillager(worldIn);
            villager.setProfession(Villager.PALAEOBOTANIST_PROFESSION);
            BlockPos villagerPos = center;

            if (rotation == Rotation.NONE) {
                villagerPos = villagerPos.south(4);
            }
            if (rotation == Rotation.CLOCKWISE_180) {
                villagerPos = villagerPos.north(4);
            }
            if (rotation == Rotation.CLOCKWISE_90) {
                villagerPos = villagerPos.west(4);
            }
            if (rotation == Rotation.CLOCKWISE_180) {
                villagerPos = villagerPos.east(4);
            }

            villager.setLocationAndAngles(villagerPos.getX() + 0.5D, villagerPos.getY() + 0.5D, villagerPos.getZ() + 0.5D, 0, 0);
            worldIn.spawnEntity(villager);
            //System.err.println("Spawn villager: " + villagerPos.getX() + " " + villagerPos.getY() + " " + villagerPos.getZ());
            component.villagerCount++;
        }
        return true;
    }
}