package net.lepidodendron.entity.render;

import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.*;
import net.lepidodendron.entity.*;
import net.lepidodendron.entity.render.entity.*;
import net.lepidodendron.entity.render.tile.*;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void RegisterEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraWalliserops.class, new IRenderFactory<EntityPrehistoricFloraWalliserops>() {
            @Override
            public Render<? super EntityPrehistoricFloraWalliserops> createRenderFor(RenderManager manager) {
                return new RenderWalliserops(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPneumodesmus.class, new IRenderFactory<EntityPrehistoricFloraPneumodesmus>() {
            @Override
            public Render<? super EntityPrehistoricFloraPneumodesmus> createRenderFor(RenderManager manager) {
                return new RenderPneumodesmus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCheirurus.class, new IRenderFactory<EntityPrehistoricFloraCheirurus>() {
            @Override
            public Render<? super EntityPrehistoricFloraCheirurus> createRenderFor(RenderManager manager) {
                return new RenderCheirurus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraIsotelus.class, new IRenderFactory<EntityPrehistoricFloraIsotelus>() {
            @Override
            public Render<? super EntityPrehistoricFloraIsotelus> createRenderFor(RenderManager manager) {
                return new RenderIsotelus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAsaphus.class, new IRenderFactory<EntityPrehistoricFloraAsaphus>() {
            @Override
            public Render<? super EntityPrehistoricFloraAsaphus> createRenderFor(RenderManager manager) {
                return new RenderAsaphus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPromissum.class, new IRenderFactory<EntityPrehistoricFloraPromissum>() {
            @Override
            public Render<? super EntityPrehistoricFloraPromissum> createRenderFor(RenderManager manager) {
                return new RenderPromissum(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAcanthodes.class, new IRenderFactory<EntityPrehistoricFloraAcanthodes>() {
            @Override
            public Render<? super EntityPrehistoricFloraAcanthodes> createRenderFor(RenderManager manager) {
                return new RenderAcanthodes(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish1.class, new IRenderFactory<EntityPrehistoricFloraJellyfish1>() {
            @Override
            public Render<? super EntityPrehistoricFloraJellyfish1> createRenderFor(RenderManager manager) {
                return new RenderJellyfish1(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish2.class, new IRenderFactory<EntityPrehistoricFloraJellyfish2>() {
            @Override
            public Render<? super EntityPrehistoricFloraJellyfish2> createRenderFor(RenderManager manager) {
                return new RenderJellyfish2(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish3.class, new IRenderFactory<EntityPrehistoricFloraJellyfish3>() {
            @Override
            public Render<? super EntityPrehistoricFloraJellyfish3> createRenderFor(RenderManager manager) {
                return new RenderJellyfish3(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish4.class, new IRenderFactory<EntityPrehistoricFloraJellyfish4>() {
            @Override
            public Render<? super EntityPrehistoricFloraJellyfish4> createRenderFor(RenderManager manager) {
                return new RenderJellyfish4(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish5.class, new IRenderFactory<EntityPrehistoricFloraJellyfish5>() {
            @Override
            public Render<? super EntityPrehistoricFloraJellyfish5> createRenderFor(RenderManager manager) {
                return new RenderJellyfish5(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish6.class, new IRenderFactory<EntityPrehistoricFloraJellyfish6>() {
            @Override
            public Render<? super EntityPrehistoricFloraJellyfish6> createRenderFor(RenderManager manager) {
                return new RenderJellyfish6(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish7.class, new IRenderFactory<EntityPrehistoricFloraJellyfish7>() {
            @Override
            public Render<? super EntityPrehistoricFloraJellyfish7> createRenderFor(RenderManager manager) {
                return new RenderJellyfish7(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraBothriolepis.class, new IRenderFactory<EntityPrehistoricFloraBothriolepis>() {
            @Override
            public Render<? super EntityPrehistoricFloraBothriolepis> createRenderFor(RenderManager manager) {
                return new RenderBothriolepis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEurypterus.class, new IRenderFactory<EntityPrehistoricFloraEurypterus>() {
            @Override
            public Render<? super EntityPrehistoricFloraEurypterus> createRenderFor(RenderManager manager) {
                return new RenderEurypterus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPteraspis.class, new IRenderFactory<EntityPrehistoricFloraPteraspis>() {
            @Override
            public Render<? super EntityPrehistoricFloraPteraspis> createRenderFor(RenderManager manager) {
                return new RenderPteraspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCyrtoceras.class, new IRenderFactory<EntityPrehistoricFloraCyrtoceras>() {
            @Override
            public Render<? super EntityPrehistoricFloraCyrtoceras> createRenderFor(RenderManager manager) {
                return new RenderCyrtoceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraArandaspis.class, new IRenderFactory<EntityPrehistoricFloraArandaspis>() {
            @Override
            public Render<? super EntityPrehistoricFloraArandaspis> createRenderFor(RenderManager manager) {
                return new RenderArandaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAnthracomedusa.class, new IRenderFactory<EntityPrehistoricFloraAnthracomedusa>() {
            @Override
            public Render<? super EntityPrehistoricFloraAnthracomedusa> createRenderFor(RenderManager manager) {
                return new RenderAnthracomedusa(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraLimnoscelis.class, new IRenderFactory<EntityPrehistoricFloraLimnoscelis>() {
            @Override
            public Render<? super EntityPrehistoricFloraLimnoscelis> createRenderFor(RenderManager manager) {
                return new RenderLimnoscelis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraSacabambaspis.class, new IRenderFactory<EntityPrehistoricFloraSacabambaspis>() {
            @Override
            public Render<? super EntityPrehistoricFloraSacabambaspis> createRenderFor(RenderManager manager) {
                return new RenderSacabambaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraHibernaspis.class, new IRenderFactory<EntityPrehistoricFloraHibernaspis>() {
            @Override
            public Render<? super EntityPrehistoricFloraHibernaspis> createRenderFor(RenderManager manager) {
                return new RenderHibernaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEoarthropleura.class, new IRenderFactory<EntityPrehistoricFloraEoarthropleura>() {
            @Override
            public Render<? super EntityPrehistoricFloraEoarthropleura> createRenderFor(RenderManager manager) {
                return new RenderEoarthropleura(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmmonite_Manticoceras.class, new IRenderFactory<EntityPrehistoricFloraAmmonite_Manticoceras>() {
            @Override
            public Render<? super EntityPrehistoricFloraAmmonite_Manticoceras> createRenderFor(RenderManager manager) {
                return new RenderAmmonite_Manticoceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmmonite_Goniatites.class, new IRenderFactory<EntityPrehistoricFloraAmmonite_Goniatites>() {
            @Override
            public Render<? super EntityPrehistoricFloraAmmonite_Goniatites> createRenderFor(RenderManager manager) {
                return new RenderAmmonite_Goniatites(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmmonite_Cylolobus.class, new IRenderFactory<EntityPrehistoricFloraAmmonite_Cylolobus>() {
            @Override
            public Render<? super EntityPrehistoricFloraAmmonite_Cylolobus> createRenderFor(RenderManager manager) {
                return new RenderAmmonite_Cylolobus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmmonite_Parapuzosia.class, new IRenderFactory<EntityPrehistoricFloraAmmonite_Parapuzosia>() {
            @Override
            public Render<? super EntityPrehistoricFloraAmmonite_Parapuzosia> createRenderFor(RenderManager manager) {
                return new RenderAmmonite_Parapuzosia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmmonite_Dactylioceras.class, new IRenderFactory<EntityPrehistoricFloraAmmonite_Dactylioceras>() {
            @Override
            public Render<? super EntityPrehistoricFloraAmmonite_Dactylioceras> createRenderFor(RenderManager manager) {
                return new RenderAmmonite_Dactylioceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmmonite_Titanites.class, new IRenderFactory<EntityPrehistoricFloraAmmonite_Titanites>() {
            @Override
            public Render<? super EntityPrehistoricFloraAmmonite_Titanites> createRenderFor(RenderManager manager) {
                return new RenderAmmonite_Titanites(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmmonite_Asteroceras.class, new IRenderFactory<EntityPrehistoricFloraAmmonite_Asteroceras>() {
            @Override
            public Render<? super EntityPrehistoricFloraAmmonite_Asteroceras> createRenderFor(RenderManager manager) {
                return new RenderAmmonite_Asteroceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmmonite_Pachydesmoceras.class, new IRenderFactory<EntityPrehistoricFloraAmmonite_Pachydesmoceras>() {
            @Override
            public Render<? super EntityPrehistoricFloraAmmonite_Pachydesmoceras> createRenderFor(RenderManager manager) {
                return new RenderAmmonite_Pachydesmoceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmmonite_Pachydiscus.class, new IRenderFactory<EntityPrehistoricFloraAmmonite_Pachydiscus>() {
            @Override
            public Render<? super EntityPrehistoricFloraAmmonite_Pachydiscus> createRenderFor(RenderManager manager) {
                return new RenderAmmonite_Pachydiscus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmmonite_Coroniceras.class, new IRenderFactory<EntityPrehistoricFloraAmmonite_Coroniceras>() {
            @Override
            public Render<? super EntityPrehistoricFloraAmmonite_Coroniceras> createRenderFor(RenderManager manager) {
                return new RenderAmmonite_Coroniceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmmonite_Ceratites.class, new IRenderFactory<EntityPrehistoricFloraAmmonite_Ceratites>() {
            @Override
            public Render<? super EntityPrehistoricFloraAmmonite_Ceratites> createRenderFor(RenderManager manager) {
                return new RenderAmmonite_Ceratites(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraHibbertopterus.class, new IRenderFactory<EntityPrehistoricFloraHibbertopterus>() {
            @Override
            public Render<? super EntityPrehistoricFloraHibbertopterus> createRenderFor(RenderManager manager) {
                return new RenderHibbertopterus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTerataspis.class, new IRenderFactory<EntityPrehistoricFloraTerataspis>() {
            @Override
            public Render<? super EntityPrehistoricFloraTerataspis> createRenderFor(RenderManager manager) {
                return new RenderTerataspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraFurcacauda.class, new IRenderFactory<EntityPrehistoricFloraFurcacauda>() {
            @Override
            public Render<? super EntityPrehistoricFloraFurcacauda> createRenderFor(RenderManager manager) {
                return new RenderFurcacauda(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraSquatinactis.class, new IRenderFactory<EntityPrehistoricFloraSquatinactis>() {
            @Override
            public Render<? super EntityPrehistoricFloraSquatinactis> createRenderFor(RenderManager manager) {
                return new RenderSquatinactis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPalaeodictyopteraNymph.class, new IRenderFactory<EntityPrehistoricFloraPalaeodictyopteraNymph>() {
            @Override
            public Render<? super EntityPrehistoricFloraPalaeodictyopteraNymph> createRenderFor(RenderManager manager) {
                return new RenderPalaeodictyopteraNymph(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraQilinyu.class, new IRenderFactory<EntityPrehistoricFloraQilinyu>() {
            @Override
            public Render<? super EntityPrehistoricFloraQilinyu> createRenderFor(RenderManager manager) {
                return new RenderQilinyu(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPoraspis.class, new IRenderFactory<EntityPrehistoricFloraPoraspis>() {
            @Override
            public Render<? super EntityPrehistoricFloraPoraspis> createRenderFor(RenderManager manager) {
                return new RenderPoraspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAphetoceras.class, new IRenderFactory<EntityPrehistoricFloraAphetoceras>() {
            @Override
            public Render<? super EntityPrehistoricFloraAphetoceras> createRenderFor(RenderManager manager) {
                return new RenderAphetoceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEndoceras.class, new IRenderFactory<EntityPrehistoricFloraEndoceras>() {
            @Override
            public Render<? super EntityPrehistoricFloraEndoceras> createRenderFor(RenderManager manager) {
                return new RenderEndoceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCameroceras.class, new IRenderFactory<EntityPrehistoricFloraCameroceras>() {
            @Override
            public Render<? super EntityPrehistoricFloraCameroceras> createRenderFor(RenderManager manager) {
                return new RenderCameroceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraOrthoceras.class, new IRenderFactory<EntityPrehistoricFloraOrthoceras>() {
            @Override
            public Render<? super EntityPrehistoricFloraOrthoceras> createRenderFor(RenderManager manager) {
                return new RenderOrthoceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPalaeodictyoptera_Delitzschala.class, new IRenderFactory<EntityPrehistoricFloraPalaeodictyoptera_Delitzschala>() {
            @Override
            public Render<? super EntityPrehistoricFloraPalaeodictyoptera_Delitzschala> createRenderFor(RenderManager manager) {
                return new RenderPalaeodictyoptera_Delitzschala(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPalaeodictyoptera_Dunbaria.class, new IRenderFactory<EntityPrehistoricFloraPalaeodictyoptera_Dunbaria>() {
            @Override
            public Render<? super EntityPrehistoricFloraPalaeodictyoptera_Dunbaria> createRenderFor(RenderManager manager) {
                return new RenderPalaeodictyoptera_Dunbaria(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPalaeodictyoptera_Homaloneura.class, new IRenderFactory<EntityPrehistoricFloraPalaeodictyoptera_Homaloneura>() {
            @Override
            public Render<? super EntityPrehistoricFloraPalaeodictyoptera_Homaloneura> createRenderFor(RenderManager manager) {
                return new RenderPalaeodictyoptera_Homaloneura(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPalaeodictyoptera_Homoioptera.class, new IRenderFactory<EntityPrehistoricFloraPalaeodictyoptera_Homoioptera>() {
            @Override
            public Render<? super EntityPrehistoricFloraPalaeodictyoptera_Homoioptera> createRenderFor(RenderManager manager) {
                return new RenderPalaeodictyoptera_Homoioptera(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPalaeodictyoptera_Lithomantis.class, new IRenderFactory<EntityPrehistoricFloraPalaeodictyoptera_Lithomantis>() {
            @Override
            public Render<? super EntityPrehistoricFloraPalaeodictyoptera_Lithomantis> createRenderFor(RenderManager manager) {
                return new RenderPalaeodictyoptera_Lithomantis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPalaeodictyoptera_Lycocercus.class, new IRenderFactory<EntityPrehistoricFloraPalaeodictyoptera_Lycocercus>() {
            @Override
            public Render<? super EntityPrehistoricFloraPalaeodictyoptera_Lycocercus> createRenderFor(RenderManager manager) {
                return new RenderPalaeodictyoptera_Lycocercus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPalaeodictyoptera_Sinodunbaria.class, new IRenderFactory<EntityPrehistoricFloraPalaeodictyoptera_Sinodunbaria>() {
            @Override
            public Render<? super EntityPrehistoricFloraPalaeodictyoptera_Sinodunbaria> createRenderFor(RenderManager manager) {
                return new RenderPalaeodictyoptera_Sinodunbaria(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPalaeodictyoptera_Stenodictya.class, new IRenderFactory<EntityPrehistoricFloraPalaeodictyoptera_Stenodictya>() {
            @Override
            public Render<? super EntityPrehistoricFloraPalaeodictyoptera_Stenodictya> createRenderFor(RenderManager manager) {
                return new RenderPalaeodictyoptera_Stenodictya(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish_Precambrian.class, new IRenderFactory<EntityPrehistoricFloraJellyfish_Precambrian>() {
            @Override
            public Render<? super EntityPrehistoricFloraJellyfish_Precambrian> createRenderFor(RenderManager manager) {
                return new RenderJellyfish_Precambrian(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraSchinderhannes.class, new IRenderFactory<EntityPrehistoricFloraSchinderhannes>() {
            @Override
            public Render<? super EntityPrehistoricFloraSchinderhannes> createRenderFor(RenderManager manager) {
                return new RenderSchinderhannes(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTitanichthys.class, new IRenderFactory<EntityPrehistoricFloraTitanichthys>() {
        @Override
            public Render<? super EntityPrehistoricFloraTitanichthys> createRenderFor(RenderManager manager) {
                return new RenderTitanicthys(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraIchthyostega.class, new IRenderFactory<EntityPrehistoricFloraIchthyostega>() {
        @Override
        public Render<? super EntityPrehistoricFloraIchthyostega> createRenderFor(RenderManager manager) {
                return new RenderIchthyostega(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmphibamus.class, new IRenderFactory<EntityPrehistoricFloraAmphibamus>() {
        @Override
        public Render<? super EntityPrehistoricFloraAmphibamus> createRenderFor(RenderManager manager) {
                return new RenderAmphibamus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMixopterus.class, new IRenderFactory<EntityPrehistoricFloraMixopterus>() {
        @Override
        public Render<? super EntityPrehistoricFloraMixopterus> createRenderFor(RenderManager manager) {
                return new RenderMixopterus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJaekelopterus.class, new IRenderFactory<EntityPrehistoricFloraJaekelopterus>() {
        @Override
        public Render<? super EntityPrehistoricFloraJaekelopterus> createRenderFor(RenderManager manager) {
                return new RenderJaekelopterus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPterygotus.class, new IRenderFactory<EntityPrehistoricFloraPterygotus>() {
        @Override
        public Render<? super EntityPrehistoricFloraPterygotus> createRenderFor(RenderManager manager) {
                return new RenderPterygotus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMegarachne.class, new IRenderFactory<EntityPrehistoricFloraMegarachne>() {
        @Override
        public Render<? super EntityPrehistoricFloraMegarachne> createRenderFor(RenderManager manager) {
                return new RenderMegarachne(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraLunataspis.class, new IRenderFactory<EntityPrehistoricFloraLunataspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraLunataspis> createRenderFor(RenderManager manager) {
                return new RenderLunataspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraSelenopeltis.class, new IRenderFactory<EntityPrehistoricFloraSelenopeltis>() {
        @Override
        public Render<? super EntityPrehistoricFloraSelenopeltis> createRenderFor(RenderManager manager) {
                return new RenderSelenopeltis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraHemicyclaspis.class, new IRenderFactory<EntityPrehistoricFloraHemicyclaspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraHemicyclaspis> createRenderFor(RenderManager manager) {
                return new RenderHemicyclaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAteleaspis.class, new IRenderFactory<EntityPrehistoricFloraAteleaspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraAteleaspis> createRenderFor(RenderManager manager) {
                return new RenderAteleaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCephalaspis.class, new IRenderFactory<EntityPrehistoricFloraCephalaspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraCephalaspis> createRenderFor(RenderManager manager) {
                return new RenderCephalaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPsarolepis.class, new IRenderFactory<EntityPrehistoricFloraPsarolepis>() {
        @Override
        public Render<? super EntityPrehistoricFloraPsarolepis> createRenderFor(RenderManager manager) {
                return new RenderPsarolepis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAegirocassis.class, new IRenderFactory<EntityPrehistoricFloraAegirocassis>() {
        @Override
        public Render<? super EntityPrehistoricFloraAegirocassis> createRenderFor(RenderManager manager) {
                return new RenderAegirocassis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraThelodus.class, new IRenderFactory<EntityPrehistoricFloraThelodus>() {
        @Override
        public Render<? super EntityPrehistoricFloraThelodus> createRenderFor(RenderManager manager) {
                return new RenderThelodus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTullimonstrum.class, new IRenderFactory<EntityPrehistoricFloraTullimonstrum>() {
        @Override
        public Render<? super EntityPrehistoricFloraTullimonstrum> createRenderFor(RenderManager manager) {
                return new RenderTullimonstrum(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAttercopus.class, new IRenderFactory<EntityPrehistoricFloraAttercopus>() {
        @Override
        public Render<? super EntityPrehistoricFloraAttercopus> createRenderFor(RenderManager manager) {
                return new RenderAttercopus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Palaeotarbus.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Palaeotarbus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Palaeotarbus> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Palaeotarbus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Palaeocharinus.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Palaeocharinus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Palaeocharinus> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Palaeocharinus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Eophrynus.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Eophrynus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Eophrynus> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Eophrynus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Kreischeria.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Kreischeria>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Kreischeria> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Kreischeria(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Cryptomartus.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Cryptomartus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Cryptomartus> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Cryptomartus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Permotarbus.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Permotarbus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Permotarbus> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Permotarbus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDickinsonia.class, new IRenderFactory<EntityPrehistoricFloraDickinsonia>() {
        @Override
        public Render<? super EntityPrehistoricFloraDickinsonia> createRenderFor(RenderManager manager) {
                return new RenderDickinsonia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraKimberella.class, new IRenderFactory<EntityPrehistoricFloraKimberella>() {
        @Override
        public Render<? super EntityPrehistoricFloraKimberella> createRenderFor(RenderManager manager) {
                return new RenderKimberella(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEoandromeda.class, new IRenderFactory<EntityPrehistoricFloraEoandromeda>() {
        @Override
        public Render<? super EntityPrehistoricFloraEoandromeda> createRenderFor(RenderManager manager) {
                return new RenderEoandromeda(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraParvancorina.class, new IRenderFactory<EntityPrehistoricFloraParvancorina>() {
        @Override
        public Render<? super EntityPrehistoricFloraParvancorina> createRenderFor(RenderManager manager) {
                return new RenderParvancorina(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraSpriggina.class, new IRenderFactory<EntityPrehistoricFloraSpriggina>() {
        @Override
        public Render<? super EntityPrehistoricFloraSpriggina> createRenderFor(RenderManager manager) {
                return new RenderSpriggina(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraYilingia.class, new IRenderFactory<EntityPrehistoricFloraYilingia>() {
        @Override
        public Render<? super EntityPrehistoricFloraYilingia> createRenderFor(RenderManager manager) {
                return new RenderYilingia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraYorgia.class, new IRenderFactory<EntityPrehistoricFloraYorgia>() {
        @Override
        public Render<? super EntityPrehistoricFloraYorgia> createRenderFor(RenderManager manager) {
                return new RenderYorgia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Palaeotarbus.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Palaeotarbus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Palaeotarbus> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Palaeotarbus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Palaeocharinus.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Palaeocharinus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Palaeocharinus> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Palaeocharinus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Eophrynus.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Eophrynus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Eophrynus> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Eophrynus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Kreischeria.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Kreischeria>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Kreischeria> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Kreischeria(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Cryptomartus.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Cryptomartus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Cryptomartus> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Cryptomartus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTrigonotarbid_Permotarbus.class, new IRenderFactory<EntityPrehistoricFloraTrigonotarbid_Permotarbus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTrigonotarbid_Permotarbus> createRenderFor(RenderManager manager) {
                return new RenderTrigonotarbid_Permotarbus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMonograptus.class, new IRenderFactory<EntityPrehistoricFloraMonograptus>() {
        @Override
        public Render<? super EntityPrehistoricFloraMonograptus> createRenderFor(RenderManager manager) {
                return new RenderMonograptus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDidymograptus.class, new IRenderFactory<EntityPrehistoricFloraDidymograptus>() {
        @Override
        public Render<? super EntityPrehistoricFloraDidymograptus> createRenderFor(RenderManager manager) {
                return new RenderDidymograptus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTetragraptus.class, new IRenderFactory<EntityPrehistoricFloraTetragraptus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTetragraptus> createRenderFor(RenderManager manager) {
                return new RenderTetragraptus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPoleumita.class, new IRenderFactory<EntityPrehistoricFloraPoleumita>() {
        @Override
        public Render<? super EntityPrehistoricFloraPoleumita> createRenderFor(RenderManager manager) {
                return new RenderPoleumita(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMaclurina.class, new IRenderFactory<EntityPrehistoricFloraMaclurina>() {
        @Override
        public Render<? super EntityPrehistoricFloraMaclurina> createRenderFor(RenderManager manager) {
                return new RenderMaclurina(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCyclonema.class, new IRenderFactory<EntityPrehistoricFloraCyclonema>() {
        @Override
        public Render<? super EntityPrehistoricFloraCyclonema> createRenderFor(RenderManager manager) {
                return new RenderCyclonema(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraXenacanthus.class, new IRenderFactory<EntityPrehistoricFloraXenacanthus>() {
        @Override
        public Render<? super EntityPrehistoricFloraXenacanthus> createRenderFor(RenderManager manager) {
                return new RenderXenacanthus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraBelantsea.class, new IRenderFactory<EntityPrehistoricFloraBelantsea>() {
        @Override
        public Render<? super EntityPrehistoricFloraBelantsea> createRenderFor(RenderManager manager) {
                return new RenderBelantsea(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCampbellodus.class, new IRenderFactory<EntityPrehistoricFloraCampbellodus>() {
        @Override
        public Render<? super EntityPrehistoricFloraCampbellodus> createRenderFor(RenderManager manager) {
                return new RenderCampbellodus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPlatysomus.class, new IRenderFactory<EntityPrehistoricFloraPlatysomus>() {
        @Override
        public Render<? super EntityPrehistoricFloraPlatysomus> createRenderFor(RenderManager manager) {
                return new RenderPlatysomus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCoccosteus.class, new IRenderFactory<EntityPrehistoricFloraCoccosteus>() {
        @Override
        public Render<? super EntityPrehistoricFloraCoccosteus> createRenderFor(RenderManager manager) {
                return new RenderCoccosteus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAkmonistion.class, new IRenderFactory<EntityPrehistoricFloraAkmonistion>() {
        @Override
        public Render<? super EntityPrehistoricFloraAkmonistion> createRenderFor(RenderManager manager) {
                return new RenderAkmonistion(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDracopristis.class, new IRenderFactory<EntityPrehistoricFloraDracopristis>() {
        @Override
        public Render<? super EntityPrehistoricFloraDracopristis> createRenderFor(RenderManager manager) {
                return new RenderDracopristis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCladoselache.class, new IRenderFactory<EntityPrehistoricFloraCladoselache>() {
        @Override
        public Render<? super EntityPrehistoricFloraCladoselache> createRenderFor(RenderManager manager) {
                return new RenderCladoselache(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraGemuendina.class, new IRenderFactory<EntityPrehistoricFloraGemuendina>() {
        @Override
        public Render<? super EntityPrehistoricFloraGemuendina> createRenderFor(RenderManager manager) {
                return new RenderGemuendina(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraHyneria.class, new IRenderFactory<EntityPrehistoricFloraHyneria>() {
        @Override
        public Render<? super EntityPrehistoricFloraHyneria> createRenderFor(RenderManager manager) {
                return new RenderHyneria(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraOnychodus.class, new IRenderFactory<EntityPrehistoricFloraOnychodus>() {
        @Override
        public Render<? super EntityPrehistoricFloraOnychodus> createRenderFor(RenderManager manager) {
                return new RenderOnychodus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraRhizodus.class, new IRenderFactory<EntityPrehistoricFloraRhizodus>() {
        @Override
        public Render<? super EntityPrehistoricFloraRhizodus> createRenderFor(RenderManager manager) {
                return new RenderRhizodus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraFurcaster.class, new IRenderFactory<EntityPrehistoricFloraFurcaster>() {
        @Override
        public Render<? super EntityPrehistoricFloraFurcaster> createRenderFor(RenderManager manager) {
                return new RenderFurcaster(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraKalbarria.class, new IRenderFactory<EntityPrehistoricFloraKalbarria>() {
        @Override
        public Render<? super EntityPrehistoricFloraKalbarria> createRenderFor(RenderManager manager) {
                return new RenderKalbarria(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPalaeoisopus.class, new IRenderFactory<EntityPrehistoricFloraPalaeoisopus>() {
        @Override
        public Render<? super EntityPrehistoricFloraPalaeoisopus> createRenderFor(RenderManager manager) {
                return new RenderPalaeoisopus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAiniktozoon.class, new IRenderFactory<EntityPrehistoricFloraAiniktozoon>() {
        @Override
        public Render<? super EntityPrehistoricFloraAiniktozoon> createRenderFor(RenderManager manager) {
                return new RenderAiniktozoon(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAcutiramus.class, new IRenderFactory<EntityPrehistoricFloraAcutiramus>() {
        @Override
        public Render<? super EntityPrehistoricFloraAcutiramus> createRenderFor(RenderManager manager) {
                return new RenderAcutiramus(manager);
            }
        });


        //Tile Entities Ediacaran:
        if (LepidodendronConfig.renderAnimations) {
            ClientRegistry.bindTileEntitySpecialRenderer(BlockCharnia.TileEntityCustom.class, new RenderCharnia());
            ClientRegistry.bindTileEntitySpecialRenderer(BlockCharniodiscus.TileEntityCustom.class, new RenderCharniodiscus());
            ClientRegistry.bindTileEntitySpecialRenderer(BlockArborea.TileEntityCustom.class, new RenderArborea());
            ClientRegistry.bindTileEntitySpecialRenderer(BlockBomakellia.TileEntityCustom.class, new RenderBomakellia());
            ClientRegistry.bindTileEntitySpecialRenderer(BlockPambikalbae.TileEntityCustom.class, new RenderPambikalbae());
            ClientRegistry.bindTileEntitySpecialRenderer(BlockPrimocandelabrum1.TileEntityCustom.class, new RenderPrimocandelabrum1());
            ClientRegistry.bindTileEntitySpecialRenderer(BlockPrimocandelabrum2.TileEntityCustom.class, new RenderPrimocandelabrum2());
            ClientRegistry.bindTileEntitySpecialRenderer(BlockParviscopa.TileEntityCustom.class, new RenderParviscopa());
            ClientRegistry.bindTileEntitySpecialRenderer(BlockFunisia.TileEntityCustom.class, new RenderFunisia());
            ClientRegistry.bindTileEntitySpecialRenderer(BlockHapsidophyllas.TileEntityCustom.class, new RenderHapsidophyllas());
        }
        //These two not converted:
        ClientRegistry.bindTileEntitySpecialRenderer(BlockPteridinium.TileEntityCustom.class, new RenderPteridinium());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockCoronacollina.TileEntityCustom.class, new RenderCoronacollina());

        //Tile Entities Nautiloid shells etc:
        ClientRegistry.bindTileEntitySpecialRenderer(BlockFurcaster.TileEntityCustom.class, new RenderFurcasterItem());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNautiloidShellAmmonite_Asteroceras.TileEntityCustom.class, new RenderNautiloidShellAmmonite_Asteroceras());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNautiloidShellAmmonite_Ceratites.TileEntityCustom.class, new RenderNautiloidShellAmmonite_Ceratites());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNautiloidShellAmmonite_Manticoceras.TileEntityCustom.class, new RenderNautiloidShellAmmonite_Manticoceras());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNautiloidShellAmmonite_Goniatites.TileEntityCustom.class, new RenderNautiloidShellAmmonite_Goniatites());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNautiloidShellAmmonite_Cylolobus.TileEntityCustom.class, new RenderNautiloidShellAmmonite_Cylolobus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNautiloidShellAmmonite_Parapuzosia.TileEntityCustom.class, new RenderNautiloidShellAmmonite_Parapuzosia());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNautiloidShellAmmonite_Dactylioceras.TileEntityCustom.class, new RenderNautiloidShellAmmonite_Dactylioceras());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNautiloidShellAmmonite_Titanites.TileEntityCustom.class, new RenderNautiloidShellAmmonite_Titanites());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNautiloidShellAmmonite_Pachydesmoceras.TileEntityCustom.class, new RenderNautiloidShellAmmonite_Pachydesmoceras());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNautiloidShellAmmonite_Pachydiscus.TileEntityCustom.class, new RenderNautiloidShellAmmonite_Pachydiscus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNautiloidShellAmmonite_Coroniceras.TileEntityCustom.class, new RenderNautiloidShellAmmonite_Coroniceras());
    }
}
