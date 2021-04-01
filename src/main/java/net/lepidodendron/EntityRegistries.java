package net.lepidodendron;

import net.lepidodendron.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityRegistries {

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
        EntityRegistry.registerModEntity(new ResourceLocation(LepidodendronMod.MODID + ":" + name), entity, name, id, LepidodendronMod.instance, range, 1, true, color1, color2);
    }

    public static void registerEntities() {
        registerEntity("prehistoric_flora_walliserops", EntityPrehistoricFloraWalliserops.class, LepidodendronMod.ENTITY_WALLISEROPS, 32,-8896764,-10867366);
        registerEntity("prehistoric_flora_pneumodesmus", EntityPrehistoricFloraPneumodesmus.class, LepidodendronMod.ENTITY_PNEUMODESMUS, 32,-3497691,-10867366);
        registerEntity("prehistoric_flora_cheirurus", EntityPrehistoricFloraCheirurus.class, LepidodendronMod.ENTITY_CHEIRURUS, 32,-11715316,-11583424);
        registerEntity("prehistoric_flora_isotelus", EntityPrehistoricFloraIsotelus.class, LepidodendronMod.ENTITY_ISOTELUS, 32,-11715316,-4682937);
        registerEntity("prehistoric_flora_asaphus", EntityPrehistoricFloraAsaphus.class, LepidodendronMod.ENTITY_ASAPHUS, 32,-3689350,-11583424);
        registerEntity("prehistoric_flora_promissum", EntityPrehistoricFloraPromissum.class, LepidodendronMod.ENTITY_PROMISSUM, 32,-12301749,-1);
        registerEntity("prehistoric_flora_acanthodes", EntityPrehistoricFloraAcanthodes.class, LepidodendronMod.ENTITY_ACANTHODES, 32,-6569759,-1);
        registerEntity("prehistoric_flora_jellyfish1", EntityPrehistoricFloraJellyfish1.class, LepidodendronMod.ENTITY_JELLYFISH1, 32,-16724737,-1);
        registerEntity("prehistoric_flora_jellyfish2", EntityPrehistoricFloraJellyfish2.class, LepidodendronMod.ENTITY_JELLYFISH2, 32,-1586000,-1);
        registerEntity("prehistoric_flora_jellyfish3", EntityPrehistoricFloraJellyfish3.class, LepidodendronMod.ENTITY_JELLYFISH3, 32,-3571402,-1);
        registerEntity("prehistoric_flora_jellyfish4", EntityPrehistoricFloraJellyfish4.class, LepidodendronMod.ENTITY_JELLYFISH4, 32,-6452876,-1);
        registerEntity("prehistoric_flora_jellyfish5", EntityPrehistoricFloraJellyfish5.class, LepidodendronMod.ENTITY_JELLYFISH5, 32,-6785123,-1);
        registerEntity("prehistoric_flora_jellyfish6", EntityPrehistoricFloraJellyfish6.class, LepidodendronMod.ENTITY_JELLYFISH6, 32,-6210212,-1);
        registerEntity("prehistoric_flora_jellyfish7", EntityPrehistoricFloraJellyfish7.class, LepidodendronMod.ENTITY_JELLYFISH7, 32,-1731924,-1);
        registerEntity("prehistoric_flora_bothriolepis", EntityPrehistoricFloraBothriolepis.class, LepidodendronMod.ENTITY_BOTHRIOLEPIS, 32,-3373540,-1);
        registerEntity("prehistoric_flora_eurypterus", EntityPrehistoricFloraEurypterus.class, LepidodendronMod.ENTITY_EURYPTERUS, 32,-11451354,-1);
        registerEntity("prehistoric_flora_pteraspis", EntityPrehistoricFloraPteraspis.class, LepidodendronMod.ENTITY_PTERASPIS, 32,-4266538,-855410);
        registerEntity("prehistoric_flora_cyrtoceras", EntityPrehistoricFloraCyrtoceras.class, LepidodendronMod.ENTITY_CYRTOCERAS, 32,-1,-3698552);
        registerEntity("prehistoric_flora_arandaspis", EntityPrehistoricFloraArandaspis.class, LepidodendronMod.ENTITY_ARANDASPIS, 32,-3134187,-14763792);
        registerEntity("prehistoric_flora_anthracomedusa", EntityPrehistoricFloraAnthracomedusa.class, LepidodendronMod.ENTITY_ANTHRACOMEDUSA, 32,-16724737,-1);
        registerEntity("prehistoric_flora_limnoscelis", EntityPrehistoricFloraLimnoscelis.class, LepidodendronMod.ENTITY_LIMNOSCELIS, 32,-6306328,-1572272);
        registerEntity("prehistoric_flora_sacabambaspis", EntityPrehistoricFloraSacabambaspis.class, LepidodendronMod.ENTITY_SACABAMBASPIS, 32,-9168057,-13694436);
        registerEntity("prehistoric_flora_hibernaspis", EntityPrehistoricFloraHibernaspis.class, LepidodendronMod.ENTITY_HIBERNASPIS, 32,-5200768,-11384532);
        registerEntity("prehistoric_flora_eoarthropleura", EntityPrehistoricFloraEoarthropleura.class, LepidodendronMod.ENTITY_EOARTHROPLEURA, 32,-4122343,-10867366);
        registerEntity("prehistoric_flora_ammonite_manticoceras", EntityPrehistoricFloraAmmonite_Manticoceras.class, LepidodendronMod.ENTITY_AMMONITE_MANTICOCERAS, 32,-5308448,-6199863);
        registerEntity("prehistoric_flora_ammonite_goniatites", EntityPrehistoricFloraAmmonite_Goniatites.class, LepidodendronMod.ENTITY_AMMONITE_GONIATITES, 32,-4084109,-2070938);
        registerEntity("prehistoric_flora_ammonite_cylolobus", EntityPrehistoricFloraAmmonite_Cylolobus.class, LepidodendronMod.ENTITY_AMMONITE_CYLOLOBUS, 32,-9137749,-2775363);
        registerEntity("prehistoric_flora_ammonite_parapuzosia", EntityPrehistoricFloraAmmonite_Parapuzosia.class, LepidodendronMod.ENTITY_AMMONITE_PARAPUZOSIA, 32,-6323152,-4222976);
        registerEntity("prehistoric_flora_ammonite_dactylioceras", EntityPrehistoricFloraAmmonite_Dactylioceras.class, LepidodendronMod.ENTITY_AMMONITE_DACTYLIOCERAS, 32,-9162414,0);
        registerEntity("prehistoric_flora_ammonite_titanites", EntityPrehistoricFloraAmmonite_Titanites.class, LepidodendronMod.ENTITY_AMMONITE_TITANITES, 32,-13661992,0);
        registerEntity("prehistoric_flora_ammonite_asteroceras", EntityPrehistoricFloraAmmonite_Asteroceras.class, LepidodendronMod.ENTITY_AMMONITE_ASTEROCERAS, 32,-5947328,-10092544);
        registerEntity("prehistoric_flora_ammonite_pachydesmoceras", EntityPrehistoricFloraAmmonite_Pachydesmoceras .class, LepidodendronMod.ENTITY_AMMONITE_PACHYDESMOCERAS, 32,-14846373,-5947328);
        registerEntity("prehistoric_flora_ammonite_pachydiscus", EntityPrehistoricFloraAmmonite_Pachydiscus .class, LepidodendronMod.ENTITY_AMMONITE_PACHYDISCUS, 32,-10092544,-1);
        registerEntity("prehistoric_flora_ammonite_coroniceras", EntityPrehistoricFloraAmmonite_Coroniceras.class, LepidodendronMod.ENTITY_AMMONITE_CORONICERAS, 32,-9137749,0);
        registerEntity("prehistoric_flora_ammonite_ceratites", EntityPrehistoricFloraAmmonite_Ceratites.class, LepidodendronMod.ENTITY_AMMONITE_CERATITES, 32,-7186664,-5947328);
        registerEntity("prehistoric_flora_hibbertopterus", EntityPrehistoricFloraHibbertopterus.class, LepidodendronMod.ENTITY_HIBBERTOPTERUS, 32,-2524036,-8171236);
        registerEntity("prehistoric_flora_terataspis", EntityPrehistoricFloraTerataspis.class, LepidodendronMod.ENTITY_TERATASPIS, 32,-1000314,-4546685);
        registerEntity("prehistoric_flora_furcacauda", EntityPrehistoricFloraFurcacauda.class, LepidodendronMod.ENTITY_FURCACAUDA, 32,-855513,-14342909);

    }

}
