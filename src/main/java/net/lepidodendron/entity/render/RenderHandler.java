package net.lepidodendron.entity.render;

import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.*;
import net.lepidodendron.entity.*;
import net.lepidodendron.entity.render.entity.*;
import net.lepidodendron.entity.render.tile.*;
import net.lepidodendron.tileentity.TileEntityFacivermis;
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
                return new RenderTitanichthys(manager);
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
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTemperoceras.class, new IRenderFactory<EntityPrehistoricFloraTemperoceras>() {
        @Override
        public Render<? super EntityPrehistoricFloraTemperoceras> createRenderFor(RenderManager manager) {
                return new RenderTemperoceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCarcinosoma.class, new IRenderFactory<EntityPrehistoricFloraCarcinosoma>() {
        @Override
        public Render<? super EntityPrehistoricFloraCarcinosoma> createRenderFor(RenderManager manager) {
                return new RenderCarcinosoma(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraKokomopterus.class, new IRenderFactory<EntityPrehistoricFloraKokomopterus>() {
        @Override
        public Render<? super EntityPrehistoricFloraKokomopterus> createRenderFor(RenderManager manager) {
                return new RenderKokomopterus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPagea.class, new IRenderFactory<EntityPrehistoricFloraPagea>() {
        @Override
        public Render<? super EntityPrehistoricFloraPagea> createRenderFor(RenderManager manager) {
                return new RenderPagea(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEglonaspis.class, new IRenderFactory<EntityPrehistoricFloraEglonaspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraEglonaspis> createRenderFor(RenderManager manager) {
                return new RenderEglonaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAcadoaradoxides.class, new IRenderFactory<EntityPrehistoricFloraAcadoaradoxides>() {
        @Override
        public Render<? super EntityPrehistoricFloraAcadoaradoxides> createRenderFor(RenderManager manager) {
                return new RenderAcadoaradoxides(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAlacaris.class, new IRenderFactory<EntityPrehistoricFloraAlacaris>() {
        @Override
        public Render<? super EntityPrehistoricFloraAlacaris> createRenderFor(RenderManager manager) {
                return new RenderAlacaris(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraSiberion.class, new IRenderFactory<EntityPrehistoricFloraSiberion>() {
        @Override
        public Render<? super EntityPrehistoricFloraSiberion> createRenderFor(RenderManager manager) {
                return new RenderSiberion(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraBanffia.class, new IRenderFactory<EntityPrehistoricFloraBanffia>() {
        @Override
        public Render<? super EntityPrehistoricFloraBanffia> createRenderFor(RenderManager manager) {
                return new RenderBanffia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraYawunik.class, new IRenderFactory<EntityPrehistoricFloraYawunik>() {
        @Override
        public Render<? super EntityPrehistoricFloraYawunik> createRenderFor(RenderManager manager) {
                return new RenderYawunik(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPomatrum.class, new IRenderFactory<EntityPrehistoricFloraPomatrum>() {
        @Override
        public Render<? super EntityPrehistoricFloraPomatrum> createRenderFor(RenderManager manager) {
                return new RenderPomatrum(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraOpabinia.class, new IRenderFactory<EntityPrehistoricFloraOpabinia>() {
        @Override
        public Render<? super EntityPrehistoricFloraOpabinia> createRenderFor(RenderManager manager) {
                return new RenderOpabinia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraLyrarapax.class, new IRenderFactory<EntityPrehistoricFloraLyrarapax>() {
        @Override
        public Render<? super EntityPrehistoricFloraLyrarapax> createRenderFor(RenderManager manager) {
                return new RenderLyrarapax(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTokummia.class, new IRenderFactory<EntityPrehistoricFloraTokummia>() {
        @Override
        public Render<? super EntityPrehistoricFloraTokummia> createRenderFor(RenderManager manager) {
                return new RenderTokummia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraOdaraia.class, new IRenderFactory<EntityPrehistoricFloraOdaraia>() {
        @Override
        public Render<? super EntityPrehistoricFloraOdaraia> createRenderFor(RenderManager manager) {
                return new RenderOdaraia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAmplectobelua .class, new IRenderFactory<EntityPrehistoricFloraAmplectobelua >() {
        @Override
        public Render<? super EntityPrehistoricFloraAmplectobelua > createRenderFor(RenderManager manager) {
                return new RenderAmplectobelua (manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPaucipodia.class, new IRenderFactory<EntityPrehistoricFloraPaucipodia>() {
        @Override
        public Render<? super EntityPrehistoricFloraPaucipodia> createRenderFor(RenderManager manager) {
                return new RenderPaucipodia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAnomalocaris.class, new IRenderFactory<EntityPrehistoricFloraAnomalocaris>() {
        @Override
        public Render<? super EntityPrehistoricFloraAnomalocaris> createRenderFor(RenderManager manager) {
                return new RenderAnomalocaris(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMegalocephalus.class, new IRenderFactory<EntityPrehistoricFloraMegalocephalus>() {
        @Override
        public Render<? super EntityPrehistoricFloraMegalocephalus> createRenderFor(RenderManager manager) {
                return new RenderMegalocephalus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraLaminacaris.class, new IRenderFactory<EntityPrehistoricFloraLaminacaris>() {
        @Override
        public Render<? super EntityPrehistoricFloraLaminacaris> createRenderFor(RenderManager manager) {
                return new RenderLaminacaris(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCambroraster.class, new IRenderFactory<EntityPrehistoricFloraCambroraster>() {
        @Override
        public Render<? super EntityPrehistoricFloraCambroraster> createRenderFor(RenderManager manager) {
                return new RenderCambroraster(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCanadaspis.class, new IRenderFactory<EntityPrehistoricFloraCanadaspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraCanadaspis> createRenderFor(RenderManager manager) {
                return new RenderCanadaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEllipsocephalus.class, new IRenderFactory<EntityPrehistoricFloraEllipsocephalus>() {
        @Override
        public Render<? super EntityPrehistoricFloraEllipsocephalus> createRenderFor(RenderManager manager) {
                return new RenderEllipsocephalus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraElrathia.class, new IRenderFactory<EntityPrehistoricFloraElrathia>() {
        @Override
        public Render<? super EntityPrehistoricFloraElrathia> createRenderFor(RenderManager manager) {
                return new RenderElrathia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEoredlichia.class, new IRenderFactory<EntityPrehistoricFloraEoredlichia>() {
        @Override
        public Render<? super EntityPrehistoricFloraEoredlichia> createRenderFor(RenderManager manager) {
                return new RenderEoredlichia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJianshanopodia.class, new IRenderFactory<EntityPrehistoricFloraJianshanopodia>() {
        @Override
        public Render<? super EntityPrehistoricFloraJianshanopodia> createRenderFor(RenderManager manager) {
                return new RenderJianshanopodia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraKodymirus.class, new IRenderFactory<EntityPrehistoricFloraKodymirus>() {
        @Override
        public Render<? super EntityPrehistoricFloraKodymirus> createRenderFor(RenderManager manager) {
                return new RenderKodymirus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraKerygmachela.class, new IRenderFactory<EntityPrehistoricFloraKerygmachela>() {
        @Override
        public Render<? super EntityPrehistoricFloraKerygmachela> createRenderFor(RenderManager manager) {
                return new RenderKerygmachela(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMarrella.class, new IRenderFactory<EntityPrehistoricFloraMarrella>() {
        @Override
        public Render<? super EntityPrehistoricFloraMarrella> createRenderFor(RenderManager manager) {
                return new RenderMarrella(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraNectocaris.class, new IRenderFactory<EntityPrehistoricFloraNectocaris>() {
        @Override
        public Render<? super EntityPrehistoricFloraNectocaris> createRenderFor(RenderManager manager) {
                return new RenderNectocaris(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish_Palaeo1.class, new IRenderFactory<EntityPrehistoricFloraJellyfish_Palaeo1>() {
        @Override
        public Render<? super EntityPrehistoricFloraJellyfish_Palaeo1> createRenderFor(RenderManager manager) {
                return new RenderPalaeojelly1(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish_Palaeo2.class, new IRenderFactory<EntityPrehistoricFloraJellyfish_Palaeo2>() {
            @Override
            public Render<? super EntityPrehistoricFloraJellyfish_Palaeo2> createRenderFor(RenderManager manager) {
                return new RenderPalaeojelly2(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish_Palaeo3.class, new IRenderFactory<EntityPrehistoricFloraJellyfish_Palaeo3>() {
            @Override
            public Render<? super EntityPrehistoricFloraJellyfish_Palaeo3> createRenderFor(RenderManager manager) {
                return new RenderPalaeojelly3(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJellyfish_Palaeo4.class, new IRenderFactory<EntityPrehistoricFloraJellyfish_Palaeo4>() {
            @Override
            public Render<? super EntityPrehistoricFloraJellyfish_Palaeo4> createRenderFor(RenderManager manager) {
                return new RenderPalaeojelly4(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraParadoxides.class, new IRenderFactory<EntityPrehistoricFloraParadoxides>() {
        @Override
        public Render<? super EntityPrehistoricFloraParadoxides> createRenderFor(RenderManager manager) {
                return new RenderParadoxides(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraYohoia.class, new IRenderFactory<EntityPrehistoricFloraYohoia>() {
        @Override
        public Render<? super EntityPrehistoricFloraYohoia> createRenderFor(RenderManager manager) {
                return new RenderYohoia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraGemmactena.class, new IRenderFactory<EntityPrehistoricFloraGemmactena>() {
        @Override
        public Render<? super EntityPrehistoricFloraGemmactena> createRenderFor(RenderManager manager) {
                return new RenderGemmactena(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraBatofasciculus.class, new IRenderFactory<EntityPrehistoricFloraBatofasciculus>() {
        @Override
        public Render<? super EntityPrehistoricFloraBatofasciculus> createRenderFor(RenderManager manager) {
                return new RenderBatofasciculus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraVestinautilus.class, new IRenderFactory<EntityPrehistoricFloraVestinautilus>() {
        @Override
        public Render<? super EntityPrehistoricFloraVestinautilus> createRenderFor(RenderManager manager) {
                return new RenderVestinautilus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraParexus.class, new IRenderFactory<EntityPrehistoricFloraParexus>() {
        @Override
        public Render<? super EntityPrehistoricFloraParexus> createRenderFor(RenderManager manager) {
                return new RenderParexus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraSynophalos.class, new IRenderFactory<EntityPrehistoricFloraSynophalos>() {
        @Override
        public Render<? super EntityPrehistoricFloraSynophalos> createRenderFor(RenderManager manager) {
                return new RenderSynophalos(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPikaia.class, new IRenderFactory<EntityPrehistoricFloraPikaia>() {
        @Override
        public Render<? super EntityPrehistoricFloraPikaia> createRenderFor(RenderManager manager) {
                return new RenderPikaia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPalaeodictyopteraNymphPerm.class, new IRenderFactory<EntityPrehistoricFloraPalaeodictyopteraNymphPerm>() {
        @Override
        public Render<? super EntityPrehistoricFloraPalaeodictyopteraNymphPerm> createRenderFor(RenderManager manager) {
                return new RenderPalaeodictyopteraNymphPerm(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraHylonomus.class, new IRenderFactory<EntityPrehistoricFloraHylonomus>() {
        @Override
        public Render<? super EntityPrehistoricFloraHylonomus> createRenderFor(RenderManager manager) {
                return new RenderHylonomus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraSpathicephalus.class, new IRenderFactory<EntityPrehistoricFloraSpathicephalus>() {
        @Override
        public Render<? super EntityPrehistoricFloraSpathicephalus> createRenderFor(RenderManager manager) {
                return new RenderSpathicephalus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraYunnanozoon.class, new IRenderFactory<EntityPrehistoricFloraYunnanozoon>() {
        @Override
        public Render<? super EntityPrehistoricFloraYunnanozoon> createRenderFor(RenderManager manager) {
                return new RenderYunnanozoon(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraHallucigenia.class, new IRenderFactory<EntityPrehistoricFloraHallucigenia>() {
        @Override
        public Render<? super EntityPrehistoricFloraHallucigenia> createRenderFor(RenderManager manager) {
                return new RenderHallucigenia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMicrodictyon.class, new IRenderFactory<EntityPrehistoricFloraMicrodictyon>() {
        @Override
        public Render<? super EntityPrehistoricFloraMicrodictyon> createRenderFor(RenderManager manager) {
                return new RenderMicrodictyon(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDeiroceras .class, new IRenderFactory<EntityPrehistoricFloraDeiroceras >() {
        @Override
        public Render<? super EntityPrehistoricFloraDeiroceras > createRenderFor(RenderManager manager) {
                return new RenderDeiroceras (manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraGonioceras.class, new IRenderFactory<EntityPrehistoricFloraGonioceras>() {
        @Override
        public Render<? super EntityPrehistoricFloraGonioceras> createRenderFor(RenderManager manager) {
                return new RenderGonioceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDiania.class, new IRenderFactory<EntityPrehistoricFloraDiania>() {
        @Override
        public Render<? super EntityPrehistoricFloraDiania> createRenderFor(RenderManager manager) {
                return new RenderDiania(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMetaspriggina.class, new IRenderFactory<EntityPrehistoricFloraMetaspriggina>() {
        @Override
        public Render<? super EntityPrehistoricFloraMetaspriggina> createRenderFor(RenderManager manager) {
                return new RenderMetaspriggina(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTegopelte.class, new IRenderFactory<EntityPrehistoricFloraTegopelte>() {
        @Override
        public Render<? super EntityPrehistoricFloraTegopelte> createRenderFor(RenderManager manager) {
                return new RenderTegopelte(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraWiwaxia.class, new IRenderFactory<EntityPrehistoricFloraWiwaxia>() {
        @Override
        public Render<? super EntityPrehistoricFloraWiwaxia> createRenderFor(RenderManager manager) {
                return new RenderWiwaxia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraOdontogriphus.class, new IRenderFactory<EntityPrehistoricFloraOdontogriphus>() {
        @Override
        public Render<? super EntityPrehistoricFloraOdontogriphus> createRenderFor(RenderManager manager) {
                return new RenderOdontogriphus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraXenusion.class, new IRenderFactory<EntityPrehistoricFloraXenusion>() {
        @Override
        public Render<? super EntityPrehistoricFloraXenusion> createRenderFor(RenderManager manager) {
                return new RenderXenusion(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMimetaster.class, new IRenderFactory<EntityPrehistoricFloraMimetaster>() {
        @Override
        public Render<? super EntityPrehistoricFloraMimetaster> createRenderFor(RenderManager manager) {
                return new RenderMimetaster(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraOmnidens.class, new IRenderFactory<EntityPrehistoricFloraOmnidens>() {
        @Override
        public Render<? super EntityPrehistoricFloraOmnidens> createRenderFor(RenderManager manager) {
                return new RenderOmnidens(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraBasiloceras.class, new IRenderFactory<EntityPrehistoricFloraBasiloceras>() {
        @Override
        public Render<? super EntityPrehistoricFloraBasiloceras> createRenderFor(RenderManager manager) {
                return new RenderBasiloceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraWebsteroprion.class, new IRenderFactory<EntityPrehistoricFloraWebsteroprion>() {
        @Override
        public Render<? super EntityPrehistoricFloraWebsteroprion> createRenderFor(RenderManager manager) {
                return new RenderWebsteroprion(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraWebsteroprionHole.class, new IRenderFactory<EntityPrehistoricFloraWebsteroprionHole>() {
            @Override
            public Render<? super EntityPrehistoricFloraWebsteroprionHole> createRenderFor(RenderManager manager) {
                return new RenderWebsteroprionHole(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCheloniellon.class, new IRenderFactory<EntityPrehistoricFloraCheloniellon>() {
        @Override
        public Render<? super EntityPrehistoricFloraCheloniellon> createRenderFor(RenderManager manager) {
                return new RenderCheloniellon(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraScorpion_Gigantoscorpio.class, new IRenderFactory<EntityPrehistoricFloraScorpion_Gigantoscorpio>() {
        @Override
        public Render<? super EntityPrehistoricFloraScorpion_Gigantoscorpio> createRenderFor(RenderManager manager) {
                return new RenderScorpion_Gigantoscorpio(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraScorpion_Opsieobuthus.class, new IRenderFactory<EntityPrehistoricFloraScorpion_Opsieobuthus>() {
        @Override
        public Render<? super EntityPrehistoricFloraScorpion_Opsieobuthus> createRenderFor(RenderManager manager) {
                return new RenderScorpion_Opsieobuthus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraScorpion_Pulmonoscorpius.class, new IRenderFactory<EntityPrehistoricFloraScorpion_Pulmonoscorpius>() {
        @Override
        public Render<? super EntityPrehistoricFloraScorpion_Pulmonoscorpius> createRenderFor(RenderManager manager) {
                return new RenderScorpion_Pulmonoscorpius(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraScorpion_Gondwanascorpio.class, new IRenderFactory<EntityPrehistoricFloraScorpion_Gondwanascorpio>() {
        @Override
        public Render<? super EntityPrehistoricFloraScorpion_Gondwanascorpio> createRenderFor(RenderManager manager) {
                return new RenderScorpion_Gondwanascorpio(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraOttoia.class, new IRenderFactory<EntityPrehistoricFloraOttoia>() {
        @Override
        public Render<? super EntityPrehistoricFloraOttoia> createRenderFor(RenderManager manager) {
                return new RenderOttoia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraHeterosteus.class, new IRenderFactory<EntityPrehistoricFloraHeterosteus>() {
        @Override
        public Render<? super EntityPrehistoricFloraHeterosteus> createRenderFor(RenderManager manager) {
                return new RenderHeterosteus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMoschops.class, new IRenderFactory<EntityPrehistoricFloraMoschops>() {
        @Override
        public Render<? super EntityPrehistoricFloraMoschops> createRenderFor(RenderManager manager) {
                return new RenderMoschops(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEryops.class, new IRenderFactory<EntityPrehistoricFloraEryops>() {
        @Override
        public Render<? super EntityPrehistoricFloraEryops> createRenderFor(RenderManager manager) {
                return new RenderEryops(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCotylorhynchus.class, new IRenderFactory<EntityPrehistoricFloraCotylorhynchus>() {
        @Override
        public Render<? super EntityPrehistoricFloraCotylorhynchus> createRenderFor(RenderManager manager) {
                return new RenderCotylorhynchus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraOphiacodon.class, new IRenderFactory<EntityPrehistoricFloraOphiacodon>() {
        @Override
        public Render<? super EntityPrehistoricFloraOphiacodon> createRenderFor(RenderManager manager) {
                return new RenderOphiacodon(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAcrolepis.class, new IRenderFactory<EntityPrehistoricFloraAcrolepis>() {
        @Override
        public Render<? super EntityPrehistoricFloraAcrolepis> createRenderFor(RenderManager manager) {
                return new RenderAcrolepis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCoelacanthus.class, new IRenderFactory<EntityPrehistoricFloraCoelacanthus>() {
        @Override
        public Render<? super EntityPrehistoricFloraCoelacanthus> createRenderFor(RenderManager manager) {
                return new RenderCoelacanthus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraHelicoprion.class, new IRenderFactory<EntityPrehistoricFloraHelicoprion>() {
        @Override
        public Render<? super EntityPrehistoricFloraHelicoprion> createRenderFor(RenderManager manager) {
                return new RenderHelicoprion(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPederpes.class, new IRenderFactory<EntityPrehistoricFloraPederpes>() {
        @Override
        public Render<? super EntityPrehistoricFloraPederpes> createRenderFor(RenderManager manager) {
                return new RenderPederpes(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDimetrodon.class, new IRenderFactory<EntityPrehistoricFloraDimetrodon>() {
        @Override
        public Render<? super EntityPrehistoricFloraDimetrodon> createRenderFor(RenderManager manager) {
                return new RenderDimetrodon(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraGorgonops.class, new IRenderFactory<EntityPrehistoricFloraGorgonops>() {
        @Override
        public Render<? super EntityPrehistoricFloraGorgonops> createRenderFor(RenderManager manager) {
                return new RenderGorgonops(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraInostrancevia.class, new IRenderFactory<EntityPrehistoricFloraInostrancevia>() {
        @Override
        public Render<? super EntityPrehistoricFloraInostrancevia> createRenderFor(RenderManager manager) {
                return new RenderInostrancevia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDunkleosteus.class, new IRenderFactory<EntityPrehistoricFloraDunkleosteus>() {
        @Override
        public Render<? super EntityPrehistoricFloraDunkleosteus> createRenderFor(RenderManager manager) {
                return new RenderDunkleosteus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAcanthostega.class, new IRenderFactory<EntityPrehistoricFloraAcanthostega>() {
        @Override
        public Render<? super EntityPrehistoricFloraAcanthostega> createRenderFor(RenderManager manager) {
                return new RenderAcanthostega(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraSphenacodon.class, new IRenderFactory<EntityPrehistoricFloraSphenacodon>() {
        @Override
        public Render<? super EntityPrehistoricFloraSphenacodon> createRenderFor(RenderManager manager) {
                return new RenderSphenacodon(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPlatyhystrix.class, new IRenderFactory<EntityPrehistoricFloraPlatyhystrix>() {
        @Override
        public Render<? super EntityPrehistoricFloraPlatyhystrix> createRenderFor(RenderManager manager) {
                return new RenderPlatyhystrix(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraScutosaurus.class, new IRenderFactory<EntityPrehistoricFloraScutosaurus>() {
        @Override
        public Render<? super EntityPrehistoricFloraScutosaurus> createRenderFor(RenderManager manager) {
                return new RenderScutosaurus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraVivaxosaurus.class, new IRenderFactory<EntityPrehistoricFloraVivaxosaurus>() {
        @Override
        public Render<? super EntityPrehistoricFloraVivaxosaurus> createRenderFor(RenderManager manager) {
                return new RenderVivaxosaurus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEstemmenosuchus.class, new IRenderFactory<EntityPrehistoricFloraEstemmenosuchus>() {
        @Override
        public Render<? super EntityPrehistoricFloraEstemmenosuchus> createRenderFor(RenderManager manager) {
                return new RenderEstemmenosuchus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTraquairius.class, new IRenderFactory<EntityPrehistoricFloraTraquairius>() {
        @Override
        public Render<? super EntityPrehistoricFloraTraquairius> createRenderFor(RenderManager manager) {
                return new RenderTraquairius(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDiictodon.class, new IRenderFactory<EntityPrehistoricFloraDiictodon>() {
        @Override
        public Render<? super EntityPrehistoricFloraDiictodon> createRenderFor(RenderManager manager) {
                return new RenderDiictodon(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEosimops.class, new IRenderFactory<EntityPrehistoricFloraEosimops>() {
        @Override
        public Render<? super EntityPrehistoricFloraEosimops> createRenderFor(RenderManager manager) {
                return new RenderEosimops(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraProsictodon.class, new IRenderFactory<EntityPrehistoricFloraProsictodon>() {
        @Override
        public Render<? super EntityPrehistoricFloraProsictodon> createRenderFor(RenderManager manager) {
                return new RenderProsictodon(manager);
                }
                });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraRobertia.class, new IRenderFactory<EntityPrehistoricFloraRobertia>() {
        @Override
        public Render<? super EntityPrehistoricFloraRobertia> createRenderFor(RenderManager manager) {
                return new RenderRobertia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraArchoblattina.class, new IRenderFactory<EntityPrehistoricFloraArchoblattina>() {
        @Override
        public Render<? super EntityPrehistoricFloraArchoblattina> createRenderFor(RenderManager manager) {
                return new RenderArchoblattina(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraBushizheia.class, new IRenderFactory<EntityPrehistoricFloraBushizheia>() {
        @Override
        public Render<? super EntityPrehistoricFloraBushizheia> createRenderFor(RenderManager manager) {
                return new RenderBushizheia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPhantaspis.class, new IRenderFactory<EntityPrehistoricFloraPhantaspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraPhantaspis> createRenderFor(RenderManager manager) {
                return new RenderPhantaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraSidneyia.class, new IRenderFactory<EntityPrehistoricFloraSidneyia>() {
        @Override
        public Render<? super EntityPrehistoricFloraSidneyia> createRenderFor(RenderManager manager) {
                return new RenderSidneyia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPharyngolepis.class, new IRenderFactory<EntityPrehistoricFloraPharyngolepis>() {
        @Override
        public Render<? super EntityPrehistoricFloraPharyngolepis> createRenderFor(RenderManager manager) {
                return new RenderPharyngolepis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPlatycaraspis.class, new IRenderFactory<EntityPrehistoricFloraPlatycaraspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraPlatycaraspis> createRenderFor(RenderManager manager) {
                return new RenderPlatycaraspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCrotalocephalus.class, new IRenderFactory<EntityPrehistoricFloraCrotalocephalus>() {
        @Override
        public Render<? super EntityPrehistoricFloraCrotalocephalus> createRenderFor(RenderManager manager) {
                return new RenderCrotalocephalus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDalmanites.class, new IRenderFactory<EntityPrehistoricFloraDalmanites>() {
        @Override
        public Render<? super EntityPrehistoricFloraDalmanites> createRenderFor(RenderManager manager) {
                return new RenderDalmanites(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDrepanaspis.class, new IRenderFactory<EntityPrehistoricFloraDrepanaspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraDrepanaspis> createRenderFor(RenderManager manager) {
                return new RenderDrepanaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEusthenopteron.class, new IRenderFactory<EntityPrehistoricFloraEusthenopteron>() {
        @Override
        public Render<? super EntityPrehistoricFloraEusthenopteron> createRenderFor(RenderManager manager) {
                return new RenderEusthenopteron(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraLunaspis.class, new IRenderFactory<EntityPrehistoricFloraLunaspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraLunaspis> createRenderFor(RenderManager manager) {
                return new RenderLunaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraLungmenshanaspis.class, new IRenderFactory<EntityPrehistoricFloraLungmenshanaspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraLungmenshanaspis> createRenderFor(RenderManager manager) {
                return new RenderLungmenshanaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPanderichthys.class, new IRenderFactory<EntityPrehistoricFloraPanderichthys>() {
        @Override
        public Render<? super EntityPrehistoricFloraPanderichthys> createRenderFor(RenderManager manager) {
                return new RenderPanderichthys(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPlatylomaspis.class, new IRenderFactory<EntityPrehistoricFloraPlatylomaspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraPlatylomaspis> createRenderFor(RenderManager manager) {
                return new RenderPlatylomaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraArchoblattinaNymph.class, new IRenderFactory<EntityPrehistoricFloraArchoblattinaNymph>() {
        @Override
        public Render<? super EntityPrehistoricFloraArchoblattinaNymph> createRenderFor(RenderManager manager) {
                return new RenderArchoblattinaNymph(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraRoachoidArid.class, new IRenderFactory<EntityPrehistoricFloraRoachoidArid>() {
        @Override
        public Render<? super EntityPrehistoricFloraRoachoidArid> createRenderFor(RenderManager manager) {
                return new RenderRoachoidArid(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraGerarus.class, new IRenderFactory<EntityPrehistoricFloraGerarus>() {
        @Override
        public Render<? super EntityPrehistoricFloraGerarus> createRenderFor(RenderManager manager) {
                return new RenderGerarus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraRoachoidForest.class, new IRenderFactory<EntityPrehistoricFloraRoachoidForest>() {
        @Override
        public Render<? super EntityPrehistoricFloraRoachoidForest> createRenderFor(RenderManager manager) {
                return new RenderRoachoidForest(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraRoachoidSwamp.class, new IRenderFactory<EntityPrehistoricFloraRoachoidSwamp>() {
        @Override
        public Render<? super EntityPrehistoricFloraRoachoidSwamp> createRenderFor(RenderManager manager) {
                return new RenderRoachoidSwamp(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMeganeuropsis.class, new IRenderFactory<EntityPrehistoricFloraMeganeuropsis>() {
        @Override
        public Render<? super EntityPrehistoricFloraMeganeuropsis> createRenderFor(RenderManager manager) {
                return new RenderMeganeuropsis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMeganeuropsisNymph.class, new IRenderFactory<EntityPrehistoricFloraMeganeuropsisNymph>() {
            @Override
            public Render<? super EntityPrehistoricFloraMeganeuropsisNymph> createRenderFor(RenderManager manager) {
                return new RenderMeganeuropsisNymph(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraBandringa.class, new IRenderFactory<EntityPrehistoricFloraBandringa>() {
        @Override
        public Render<? super EntityPrehistoricFloraBandringa> createRenderFor(RenderManager manager) {
                return new RenderBandringa(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraGyracanthides.class, new IRenderFactory<EntityPrehistoricFloraGyracanthides>() {
        @Override
        public Render<? super EntityPrehistoricFloraGyracanthides> createRenderFor(RenderManager manager) {
                return new RenderGyracanthides(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraJanassa.class, new IRenderFactory<EntityPrehistoricFloraJanassa>() {
        @Override
        public Render<? super EntityPrehistoricFloraJanassa> createRenderFor(RenderManager manager) {
                return new RenderJanassa(manager);
                }
            });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPhanerotinus.class, new IRenderFactory<EntityPrehistoricFloraPhanerotinus>() {
        @Override
        public Render<? super EntityPrehistoricFloraPhanerotinus> createRenderFor(RenderManager manager) {
                return new RenderPhanerotinus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraProterogyrinus.class, new IRenderFactory<EntityPrehistoricFloraProterogyrinus>() {
        @Override
        public Render<? super EntityPrehistoricFloraProterogyrinus> createRenderFor(RenderManager manager) {
                return new RenderProterogyrinus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraListracanthus.class, new IRenderFactory<EntityPrehistoricFloraListracanthus>() {
        @Override
        public Render<? super EntityPrehistoricFloraListracanthus> createRenderFor(RenderManager manager) {
                return new RenderListracanthus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPrionosuchus.class, new IRenderFactory<EntityPrehistoricFloraPrionosuchus>() {
        @Override
        public Render<? super EntityPrehistoricFloraPrionosuchus> createRenderFor(RenderManager manager) {
                return new RenderPrionosuchus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMooreoceras.class, new IRenderFactory<EntityPrehistoricFloraMooreoceras>() {
        @Override
        public Render<? super EntityPrehistoricFloraMooreoceras> createRenderFor(RenderManager manager) {
                return new RenderMooreoceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraRayonnoceras.class, new IRenderFactory<EntityPrehistoricFloraRayonnoceras>() {
        @Override
        public Render<? super EntityPrehistoricFloraRayonnoceras> createRenderFor(RenderManager manager) {
                return new RenderRayonnoceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraRemigiomontanus .class, new IRenderFactory<EntityPrehistoricFloraRemigiomontanus >() {
        @Override
        public Render<? super EntityPrehistoricFloraRemigiomontanus > createRenderFor(RenderManager manager) {
                return new RenderRemigiomontanus (manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEdestus.class, new IRenderFactory<EntityPrehistoricFloraEdestus>() {
        @Override
        public Render<? super EntityPrehistoricFloraEdestus> createRenderFor(RenderManager manager) {
                return new RenderEdestus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraPholiderpeton.class, new IRenderFactory<EntityPrehistoricFloraPholiderpeton>() {
        @Override
        public Render<? super EntityPrehistoricFloraPholiderpeton> createRenderFor(RenderManager manager) {
                return new RenderPholiderpeton(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMesosaurus.class, new IRenderFactory<EntityPrehistoricFloraMesosaurus>() {
        @Override
        public Render<? super EntityPrehistoricFloraMesosaurus> createRenderFor(RenderManager manager) {
                return new RenderMesosaurus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraIniopteryx.class, new IRenderFactory<EntityPrehistoricFloraIniopteryx>() {
        @Override
        public Render<? super EntityPrehistoricFloraIniopteryx> createRenderFor(RenderManager manager) {
                return new RenderIniopteryx(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraMenaspis.class, new IRenderFactory<EntityPrehistoricFloraMenaspis>() {
        @Override
        public Render<? super EntityPrehistoricFloraMenaspis> createRenderFor(RenderManager manager) {
                return new RenderMenaspis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraGnathorhiza.class, new IRenderFactory<EntityPrehistoricFloraGnathorhiza>() {
        @Override
        public Render<? super EntityPrehistoricFloraGnathorhiza> createRenderFor(RenderManager manager) {
                return new RenderGnathorhiza(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraClaudiosaurus.class, new IRenderFactory<EntityPrehistoricFloraClaudiosaurus>() {
        @Override
        public Render<? super EntityPrehistoricFloraClaudiosaurus> createRenderFor(RenderManager manager) {
                return new RenderClaudiosaurus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAcanthostomatops.class, new IRenderFactory<EntityPrehistoricFloraAcanthostomatops>() {
        @Override
        public Render<? super EntityPrehistoricFloraAcanthostomatops> createRenderFor(RenderManager manager) {
                return new RenderAcanthostomatops(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDiadectes.class, new IRenderFactory<EntityPrehistoricFloraDiadectes>() {
        @Override
        public Render<? super EntityPrehistoricFloraDiadectes> createRenderFor(RenderManager manager) {
                return new RenderDiadectes(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraLabidosaurus.class, new IRenderFactory<EntityPrehistoricFloraLabidosaurus>() {
        @Override
        public Render<? super EntityPrehistoricFloraLabidosaurus> createRenderFor(RenderManager manager) {
                return new RenderLabidosaurus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDvinia.class, new IRenderFactory<EntityPrehistoricFloraDvinia>() {
        @Override
        public Render<? super EntityPrehistoricFloraDvinia> createRenderFor(RenderManager manager) {
                return new RenderDvinia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraProcynosuchus.class, new IRenderFactory<EntityPrehistoricFloraProcynosuchus>() {
        @Override
        public Render<? super EntityPrehistoricFloraProcynosuchus> createRenderFor(RenderManager manager) {
                return new RenderProcynosuchus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraKaibabvenator.class, new IRenderFactory<EntityPrehistoricFloraKaibabvenator>() {
        @Override
        public Render<? super EntityPrehistoricFloraKaibabvenator> createRenderFor(RenderManager manager) {
                return new RenderKaibabvenator(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraSaivodus.class, new IRenderFactory<EntityPrehistoricFloraSaivodus>() {
        @Override
        public Render<? super EntityPrehistoricFloraSaivodus> createRenderFor(RenderManager manager) {
                return new RenderSaivodus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraTapinocephalus.class, new IRenderFactory<EntityPrehistoricFloraTapinocephalus>() {
        @Override
        public Render<? super EntityPrehistoricFloraTapinocephalus> createRenderFor(RenderManager manager) {
                return new RenderTapinocephalus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAnteosaurus.class, new IRenderFactory<EntityPrehistoricFloraAnteosaurus>() {
        @Override
        public Render<? super EntityPrehistoricFloraAnteosaurus> createRenderFor(RenderManager manager) {
                return new RenderAnteosaurus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraAlbertonia.class, new IRenderFactory<EntityPrehistoricFloraAlbertonia>() {
        @Override
        public Render<? super EntityPrehistoricFloraAlbertonia> createRenderFor(RenderManager manager) {
                return new RenderAlbertonia(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCoelophysis.class, new IRenderFactory<EntityPrehistoricFloraCoelophysis>() {
        @Override
        public Render<? super EntityPrehistoricFloraCoelophysis> createRenderFor(RenderManager manager) {
                return new RenderCoelophysis(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraEbenaqua.class, new IRenderFactory<EntityPrehistoricFloraEbenaqua>() {
        @Override
        public Render<? super EntityPrehistoricFloraEbenaqua> createRenderFor(RenderManager manager) {
                return new RenderEbenaqua(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraCooperoceras.class, new IRenderFactory<EntityPrehistoricFloraCooperoceras>() {
        @Override
        public Render<? super EntityPrehistoricFloraCooperoceras> createRenderFor(RenderManager manager) {
                return new RenderCooperoceras(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraDorypterus.class, new IRenderFactory<EntityPrehistoricFloraDorypterus>() {
        @Override
        public Render<? super EntityPrehistoricFloraDorypterus> createRenderFor(RenderManager manager) {
                return new RenderDorypterus(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(EntityPrehistoricFloraLystrosaurus.class, new IRenderFactory<EntityPrehistoricFloraLystrosaurus>() {
        @Override
        public Render<? super EntityPrehistoricFloraLystrosaurus> createRenderFor(RenderManager manager) {
                return new RenderLystrosaurus(manager);
            }
        });




        //Tile Entities Ediacaran (etc):
        if (LepidodendronConfig.renderAnimations) {
            //Ediacaran:
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
            //Cambrian:
            ClientRegistry.bindTileEntitySpecialRenderer(BlockStromatoveris.TileEntityCustom.class, new RenderStromatoveris());
            ClientRegistry.bindTileEntitySpecialRenderer(BlockThaumaptilon.TileEntityCustom.class, new RenderThaumaptilon());
        }

        //These do not have block models available:
        //Ediacaran:
        ClientRegistry.bindTileEntitySpecialRenderer(BlockPteridinium.TileEntityCustom.class, new RenderPteridinium());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockCoronacollina.TileEntityCustom.class, new RenderCoronacollina());
        //Cambrian:
        ClientRegistry.bindTileEntitySpecialRenderer(BlockHerpetogaster.TileEntityCustom.class, new RenderHerpetogaster());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFacivermis.class, new RenderFacivermis());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockOesia.TileEntityCustom.class, new RenderOesia());

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

        //Flowers:
        ClientRegistry.bindTileEntitySpecialRenderer(BlockMagnoliaFlower.TileEntityCustom.class, new RenderFlowerMagnolia());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockLiriodendronFlower.TileEntityCustom.class, new RenderFlowerLiriodendron());

        //Rotten log and eggs:
        ClientRegistry.bindTileEntitySpecialRenderer(BlockRottenLog.TileEntityCustom.class, new RenderRottenLog());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsAttercopus.TileEntityCustom.class, new RenderEggsAttercopus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsEoarthropleura.TileEntityCustom.class, new RenderEggsEoarthropleura());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsPneumodesmus.TileEntityCustom.class, new RenderEggsPneumodesmus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsTrigonotarbidOS.TileEntityCustom.class, new RenderEggsTrigonotarbidOS());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsTrigonotarbidDev.TileEntityCustom.class, new RenderEggsTrigonotarbidDev());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsTrigonotarbidCarb.TileEntityCustom.class, new RenderEggsTrigonotarbidCarb());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsTrigonotarbidPerm.TileEntityCustom.class, new RenderEggsTrigonotarbidPerm());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsGerarus.TileEntityCustom.class, new RenderEggsGerarus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsArchoblattina.TileEntityCustom.class, new RenderEggsArchoblattina());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsRoachoidArid.TileEntityCustom.class, new RenderEggsRoachoidArid());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsRoachoidForest.TileEntityCustom.class, new RenderEggsRoachoidForest());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockInsectEggsRoachoidSwamp.TileEntityCustom.class, new RenderEggsRoachoidSwamp());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockDollyphyton.TileEntityCustom.class, new RenderDollyphyton());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEdwardsiphyton.TileEntityCustom.class, new RenderEdwardsiphyton());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockAncientMoss.TileEntityCustom.class, new RenderAncientMoss());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockSelaginella.TileEntityCustom.class, new RenderSelaginella());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsHylonomus.TileEntityEggsHylonomus.class, new RenderEggsHylonomus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsOphiacodon.TileEntityEggsOphiacodon.class, new RenderEggsOphiacodon());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsLabidosaurus.TileEntityEggsLabidosaurus.class, new RenderEggsLabidosaurus());

        //Nests and eggs:
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestMoschops.TileEntityCustom.class, new RenderNestMoschops());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsMoschops.TileEntityEggMoschops.class, new RenderEggMoschops());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestCotylorhynchus.TileEntityCustom.class, new RenderNestCotylorhynchus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsCotylorhynchus.TileEntityEggCotylorhynchus.class, new RenderEggCotylorhynchus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestDimetrodon.TileEntityCustom.class, new RenderNestDimetrodon());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsDimetrodon.TileEntityEggDimetrodon.class, new RenderEggDimetrodon());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestSphenacodon.TileEntityCustom.class, new RenderNestSphenacodon());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsSphenacodon.TileEntityEggSphenacodon.class, new RenderEggSphenacodon());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestScutosaurus.TileEntityCustom.class, new RenderNestScutosaurus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsScutosaurus.TileEntityEggScutosaurus.class, new RenderEggScutosaurus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestVivaxosaurus.TileEntityCustom.class, new RenderNestVivaxosaurus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsVivaxosaurus.TileEntityEggVivaxosaurus.class, new RenderEggVivaxosaurus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestEstemmenosuchus.TileEntityCustom.class, new RenderNestEstemmenosuchus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsEstemmenosuchus.TileEntityEggEstemmenosuchus.class, new RenderEggEstemmenosuchus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestDiictodon.TileEntityCustom.class, new RenderNestDiictodon());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsDiictodon.TileEntityEggDiictodon.class, new RenderEggDiictodon());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestEosimops.TileEntityCustom.class, new RenderNestEosimops());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsEosimops.TileEntityEggEosimops.class, new RenderEggEosimops());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestProsictodon.TileEntityCustom.class, new RenderNestProsictodon());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsProsictodon.TileEntityEggProsictodon.class, new RenderEggProsictodon());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestRobertia.TileEntityCustom.class, new RenderNestRobertia());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsRobertia.TileEntityEggRobertia.class, new RenderEggRobertia());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestInostrancevia.TileEntityCustom.class, new RenderNestInostrancevia());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsInostrancevia.TileEntityEggInostrancevia.class, new RenderEggInostrancevia());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestGorgonops.TileEntityCustom.class, new RenderNestGorgonops());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsGorgonops.TileEntityEggGorgonops.class, new RenderEggGorgonops());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestRemigiomontanus.TileEntityCustom.class, new RenderNestRemigiomontanus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsRemigiomontanus.TileEntityEggRemigiomontanus.class, new RenderEggRemigiomontanus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestClaudiosaurus.TileEntityCustom.class, new RenderNestClaudiosaurus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsClaudiosaurus.TileEntityEggClaudiosaurus.class, new RenderEggClaudiosaurus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestDiadectes.TileEntityCustom.class, new RenderNestDiadectes());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsDiadectes.TileEntityEggDiadectes.class, new RenderEggDiadectes());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestDvinia.TileEntityCustom.class, new RenderNestDvinia());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsDvinia.TileEntityEggDvinia.class, new RenderEggDvinia());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestProcynosuchus.TileEntityCustom.class, new RenderNestProcynosuchus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsProcynosuchus.TileEntityEggProcynosuchus.class, new RenderEggProcynosuchus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestTapinocephalus.TileEntityCustom.class, new RenderNestTapinocephalus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsTapinocephalus.TileEntityEggTapinocephalus.class, new RenderEggTapinocephalus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestAnteosaurus.TileEntityCustom.class, new RenderNestAnteosaurus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsAnteosaurus.TileEntityEggAnteosaurus.class, new RenderEggAnteosaurus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestCoelophysis.TileEntityCustom.class, new RenderNestCoelophysis());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsCoelophysis.TileEntityEggCoelophysis.class, new RenderEggCoelophysis());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockNestLystrosaurus.TileEntityCustom.class, new RenderNestLystrosaurus());
        ClientRegistry.bindTileEntitySpecialRenderer(BlockEggsLystrosaurus.TileEntityEggLystrosaurus.class, new RenderEggLystrosaurus());

    }
}
