package net.lepidodendron;

import net.lepidodendron.block.BlockFirePF;
import net.lepidodendron.enchantments.Enchantments;
import net.lepidodendron.palaeobotanist.entity.Villager;
import net.lepidodendron.util.ModTriggers;
import net.lepidodendron.util.Summoner;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;
import java.util.function.Supplier;

@Mod(modid = LepidodendronMod.MODID, name = LepidodendronMod.NAME, version = LepidodendronMod.VERSION, dependencies = "required-after:llibrary@[1.7.17,)")
public class LepidodendronMod {
	public static final String MODID = "lepidodendron";
	public static final String NAME = "Prehistoric Nature";
	public static final String VERSION = "50.0";
	public static final SimpleNetworkWrapper PACKET_HANDLER = NetworkRegistry.INSTANCE.newSimpleChannel("lepidodendron:a");
	@SidedProxy(clientSide = "net.lepidodendron.ClientProxyLepidodendronMod", serverSide = "net.lepidodendron.ServerProxyLepidodendronMod")
	public static IProxyLepidodendronMod proxy;
	@Mod.Instance(MODID)
	public static LepidodendronMod instance;
	public ElementsLepidodendronMod elements = new ElementsLepidodendronMod();

	public static final ResourceLocation PALAEOBOTANIST_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "palaeobotanist_chest"));

	public static final int ENTITY_WALLISEROPS = 1;
	public static final ResourceLocation WALLISEROPS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/walliserops"));
	public static final int ENTITY_PNEUMODESMUS = 2;
	public static final int ENTITY_CHEIRURUS = 3;
	public static final ResourceLocation CHEIRURUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cheirurus"));
	public static final int ENTITY_ISOTELUS = 4;
	public static final ResourceLocation ISOTELUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/isotelus"));
	public static final int ENTITY_ASAPHUS = 5;
	public static final ResourceLocation ASAPHUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/asaphus"));
	public static final int ENTITY_PROMISSUM = 6;
	public static final ResourceLocation PROMISSUM_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/promissum"));
	public static final int ENTITY_ACANTHODES = 7;
	public static final ResourceLocation ACANTHODES_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/acanthodes"));
	public static final int ENTITY_JELLYFISH1 = 8;
	public static final int ENTITY_JELLYFISH2 = 9;
	public static final int ENTITY_JELLYFISH3 = 10;
	public static final int ENTITY_JELLYFISH4 = 11;
	public static final int ENTITY_JELLYFISH5 = 12;
	public static final int ENTITY_JELLYFISH6 = 13;
	public static final int ENTITY_JELLYFISH7 = 14;
	public static final int ENTITY_BOTHRIOLEPIS = 15;
	public static final ResourceLocation BOTHRIOLEPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/bothriolepis"));
	public static final int ENTITY_EURYPTERUS = 16;
	public static final ResourceLocation EURYPTERUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/eurypterus"));
	public static final ResourceLocation EURYPTERUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/eurypterus_young"));
	public static final int ENTITY_PTERASPIS = 17;
	public static final ResourceLocation PTERASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pteraspis"));
	public static final int ENTITY_CYRTOCERAS = 18;
	public static final ResourceLocation CYRTOCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cyrtoceras"));
	public static final ResourceLocation CYRTOCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cyrtoceras_young"));
	public static final int ENTITY_ARANDASPIS = 19;
	public static final ResourceLocation ARANDASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/arandaspis"));
	public static final int ENTITY_ANTHRACOMEDUSA = 20;
	public static final int ENTITY_LIMNOSCELIS = 21;
	public static final ResourceLocation LIMNOSCELIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/limnoscelis"));
	public static final ResourceLocation LIMNOSCELIS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/limnoscelis_young"));
	public static final int ENTITY_SACABAMBASPIS = 22;
	public static final ResourceLocation SACABAMBASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/sacabambaspis"));
	public static final int ENTITY_HIBERNASPIS = 23;
	public static final ResourceLocation HIBERNASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/hibernaspis"));
	public static final int ENTITY_EOARTHROPLEURA = 24;
	public static final int ENTITY_AMMONITE_MANTICOCERAS = 25;
	public static final ResourceLocation MANTICOCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_manticoceras"));
	public static final ResourceLocation MANTICOCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_manticoceras_young"));
	public static final int ENTITY_AMMONITE_GONIATITES = 26;
	public static final ResourceLocation GONIATITES_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_goniatites"));
	public static final ResourceLocation GONIATITES_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_goniatites_young"));
	public static final int ENTITY_AMMONITE_CYLOLOBUS = 27;
	public static final ResourceLocation CYLOLOBUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_cylolobus"));
	public static final ResourceLocation CYLOLOBUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_cylolobus_young"));
	public static final int ENTITY_AMMONITE_PARAPUZOSIA = 28;
	public static final ResourceLocation PARAPUZOSIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_parapuzosia"));
	public static final ResourceLocation PARAPUZOSIA_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_parapuzosia_young"));
	public static final int ENTITY_AMMONITE_DACTYLIOCERAS = 29;
	public static final ResourceLocation DACTYLIOCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_dactylioceras"));
	public static final ResourceLocation DACTYLIOCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_dactylioceras_young"));
	public static final int ENTITY_AMMONITE_TITANITES = 30;
	public static final ResourceLocation TITANITES_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_titanites"));
	public static final ResourceLocation TITANITES_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_titanites_young"));
	public static final int ENTITY_AMMONITE_ASTEROCERAS  = 31;
	public static final ResourceLocation ASTEROCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_asteroceras"));
	public static final ResourceLocation ASTEROCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_asteroceras_young"));
	public static final int ENTITY_AMMONITE_PACHYDESMOCERAS  = 32;
	public static final ResourceLocation PACHYDESMOCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_pachydesmoceras"));
	public static final ResourceLocation PACHYDESMOCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_pachydesmoceras_young"));
	public static final int ENTITY_AMMONITE_PACHYDISCUS  = 33;
	public static final ResourceLocation PACHYDISCUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_pachydiscus"));
	public static final ResourceLocation PACHYDISCUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_pachydiscus_young"));
	public static final int ENTITY_AMMONITE_CORONICERAS = 34;
	public static final ResourceLocation CORONICERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_coroniceras"));
	public static final ResourceLocation CORONICERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_coroniceras_young"));
	public static final int ENTITY_AMMONITE_CERATITES = 35;
	public static final ResourceLocation CERATITES_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_ceratites"));
	public static final ResourceLocation CERATITES_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ammonite_ceratites_young"));
	public static final int ENTITY_HIBBERTOPTERUS = 36;
	public static final ResourceLocation HIBBERTOPTERUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/hibbertopterus"));
	public static final ResourceLocation HIBBERTOPTERUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/hibbertopterus_young"));
	public static final int ENTITY_TERATASPIS = 37;
	public static final ResourceLocation TERATASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/terataspis"));
	public static final int ENTITY_FURCACAUDA = 38;
	public static final ResourceLocation FURCACAUDA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/furcacauda"));
	public static final int ENTITY_SQUATINACTIS = 39;
	public static final ResourceLocation SQUATINACTIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/squatinactis"));
	public static final int ENTITY_PALAEODICTYOPTERA_NYMPH = 40;
	public static final int ENTITY_QILINYU = 41;
	public static final ResourceLocation QILINYU_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/qilinyu"));
	public static final int ENTITY_PORASPIS = 42;
	public static final ResourceLocation PORASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/poraspis"));
	public static final int ENTITY_APHETOCERAS  = 43;
	public static final ResourceLocation APHETOCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/aphetoceras"));
	public static final ResourceLocation APHETOCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/aphetoceras_young"));
	public static final int ENTITY_ENDOCERAS = 44;
	public static final ResourceLocation ENDOCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/endoceras"));
	public static final ResourceLocation ENDOCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/endoceras_young"));
	public static final int ENTITY_CAMEROCERAS = 45;
	public static final ResourceLocation CAMEROCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cameroceras"));
	public static final ResourceLocation CAMEROCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cameroceras_young"));
	public static final int ENTITY_ORTHOCERAS = 46;
	public static final ResourceLocation ORTHOCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/orthoceras"));
	public static final ResourceLocation ORTHOCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/orthoceras_young"));
	public static final int ENTITY_PALAEODICTYOPTERA_DELITZSCHALA = 47;
	public static final int ENTITY_PALAEODICTYOPTERA_DUNBARIA = 48;
	public static final int ENTITY_PALAEODICTYOPTERA_HOMALONEURA = 49;
	public static final int ENTITY_PALAEODICTYOPTERA_HOMOIOPTERA = 50;
	public static final int ENTITY_PALAEODICTYOPTERA_LITHOMANTIS = 51;
	public static final int ENTITY_PALAEODICTYOPTERA_LYCOCERCUS = 52;
	public static final int ENTITY_PALAEODICTYOPTERA_SINODUNBARIA = 53;
	public static final int ENTITY_PALAEODICTYOPTERA_STENODICTYA = 54;
	public static final ResourceLocation PALAEODICTYOPTERA_DELITZSCHALA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/palaeodictyoptera_delitzschala"));
	public static final ResourceLocation PALAEODICTYOPTERA_DUNBARIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/palaeodictyoptera_dunbaria"));
	public static final ResourceLocation PALAEODICTYOPTERA_HOMALONEURA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/palaeodictyoptera_homaloneura"));
	public static final ResourceLocation PALAEODICTYOPTERA_HOMOIOPTERA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/palaeodictyoptera_homoioptera"));
	public static final ResourceLocation PALAEODICTYOPTERA_LITHOMANTIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/palaeodictyoptera_lithomantis"));
	public static final ResourceLocation PALAEODICTYOPTERA_LYCOCERCUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/palaeodictyoptera_lycocercus"));
	public static final ResourceLocation PALAEODICTYOPTERA_SINODUNBARIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/palaeodictyoptera_sinodunbaria"));
	public static final ResourceLocation PALAEODICTYOPTERA_STENODICTYA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/palaeodictyoptera_stenodictya"));
	public static final int ENTITY_JELLYFISH_PRECAMBRIAN = 55;
	public static final int ENTITY_SCHINDERHANNES = 56;
	public static final ResourceLocation SCHINDERHANNES_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/schinderhannes"));
	public static final int ENTITY_TITANICTHYS = 57;
	public static final ResourceLocation TITANICHTHYS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/titanichthys"));
	public static final ResourceLocation TITANICHTHYS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/titanichthys_young"));
	public static final int ENTITY_ICHTHYOSTEGA = 58;
	public static final ResourceLocation ICHTHYOSTEGA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ichthyostega"));
	public static final ResourceLocation ICHTHYOSTEGA_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ichthyostega_young"));
	public static final int ENTITY_MIXOPTERUS = 59;
	public static final ResourceLocation MIXOPTERUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/mixopterus"));
	public static final ResourceLocation MIXOPTERUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/mixopterus_young"));
	public static final int ENTITY_ACUTIRAMUS = 60;
	public static final ResourceLocation ACUTIRAMUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/acutiramus"));
	public static final ResourceLocation ACUTIRAMUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/acutiramus_young"));
	public static final int ENTITY_PTERYGOTUS = 61;
	public static final ResourceLocation PTERYGOTUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pterygotus"));
	public static final ResourceLocation PTERYGOTUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pterygotus_young"));
	public static final int ENTITY_CARCINOSOMA = 62;
	public static final ResourceLocation CARCINOSOMA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/carcinosoma"));
	public static final ResourceLocation CARCINOSOMA_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/carcinosoma_young"));
	public static final int ENTITY_AEGIROCASSIS = 63;
	public static final ResourceLocation AEGIROCASSIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/aegirocassis"));
	public static final ResourceLocation AEGIROCASSIS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/aegirocassis_young"));
	public static final int ENTITY_PSAROLEPIS = 64;
	public static final ResourceLocation PSAROLEPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/psarolepis"));
	public static final int ENTITY_LUNATASPIS = 65;
	public static final ResourceLocation LUNATASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/lunataspis"));
	public static final int ENTITY_SELENOPELTIS = 66;
	public static final ResourceLocation SELENOPELTIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/selenopeltis"));
	public static final int ENTITY_HEMICYCLASPIS = 67;
	public static final ResourceLocation HEMICYCLASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/hemicyclaspis"));
	public static final int ENTITY_ATELEASPIS = 68;
	public static final ResourceLocation ATELEASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ateleaspis"));
	public static final int ENTITY_CEPHALASPIS = 69;
	public static final ResourceLocation CEPHALASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cephalaspis"));
	public static final int ENTITY_KOKOMOPTERUS = 70;
	public static final ResourceLocation KOKOMOPTERUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/kokomopterus"));
	public static final ResourceLocation KOKOMOPTERUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/kokomopterus_young"));
	public static final int ENTITY_CLADOSELACHE = 71;
	public static final ResourceLocation CLADOSELACHE_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cladoselache"));
	public static final ResourceLocation CLADOSELACHE_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cladoselache_young"));
	public static final int ENTITY_HYNERIA = 72;
	public static final ResourceLocation HYNERIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/hyneria"));
	public static final ResourceLocation HYNERIA_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/hyneria_young"));
	public static final int ENTITY_XENACANTHUS = 73;
	public static final ResourceLocation XENACANTHUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/xenacanthus"));
	public static final ResourceLocation XENACANTHUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/xenacanthus_young"));
	public static final int ENTITY_JAEKELOPTERUS = 74;
	public static final ResourceLocation JAEKELOPTERUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/jaekelopterus"));
	public static final ResourceLocation JAEKELOPTERUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/jaekelopterus_young"));
	public static final int ENTITY_MEGARACHNE = 75;
	public static final ResourceLocation MEGARACHNE_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/megarachne"));
	public static final ResourceLocation MEGARACHNE_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/megarachne_young"));
	public static final int ENTITY_TULLIMONSTRUM = 76;
	public static final ResourceLocation TULLIMONSTRUM_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/tullimonstrum"));
	public static final int ENTITY_AKMONISTION = 77;
	public static final ResourceLocation AKMONISTION_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/akmonistion"));
	public static final int ENTITY_RHIZODUS = 78;
	public static final ResourceLocation RHIZODUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/rhizodus"));
	public static final ResourceLocation RHIZODUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/rhizodus_young"));
	public static final int ENTITY_AMPHIBAMUS = 79;
	public static final ResourceLocation AMPHIBAMUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/amphibamus"));
	public static final ResourceLocation AMPHIBAMUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/amphibamus_young"));
	public static final int ENTITY_MEGALOCEPHALUS = 80;
	public static final ResourceLocation MEGALOCEPHALUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/megalocephalus"));
	public static final ResourceLocation MEGALOCEPHALUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/megalocephalus_young"));
	public static final int ENTITY_DRACOPRISTIS = 81;
	public static final ResourceLocation DRACOPRISTIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/dracopristis"));
	public static final ResourceLocation DRACOPRISTIS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/dracopristis_young"));
	public static final int ENTITY_YUNNANOZOON = 82;
	public static final ResourceLocation YUNNANOZOON_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/yunnanozoon"));
	public static final int ENTITY_THELODUS = 83;
	public static final ResourceLocation THELODUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/thelodus"));
	public static final int ENTITY_POLEUMITA = 84;
	public static final int ENTITY_TRIGONOTARBID_PALAEOTARBUS = 85;
	public static final int ENTITY_TRIGONOTARBID_PALAEOCHARINUS = 86;
	public static final int ENTITY_TRIGONOTARBID_EOPHRYNUS = 87;
	public static final int ENTITY_TRIGONOTARBID_KREISCHERIA = 88;
	public static final int ENTITY_TRIGONOTARBID_CRYPTOMARTUS = 89;
	public static final int ENTITY_TRIGONOTARBID_PERMOTARBUS = 90;
	public static final ResourceLocation TRIGONOTARBID_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/trigonotarbid"));
	public static final int ENTITY_ATTERCOPUS = 91;
	public static final ResourceLocation ATTERCOPUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/attercopus"));
	public static final int ENTITY_DICKINSONIA = 92;
	public static final int ENTITY_KIMBERELLA = 93;
	public static final int ENTITY_EOANDROMEDA = 94;
	public static final int ENTITY_PARVANCORINA = 95;
	public static final ResourceLocation PARVANCORINA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/parvancorina"));
	public static final int ENTITY_SPRIGGINA = 96;
	public static final int ENTITY_YILINGIA = 97;
	public static final int ENTITY_YORGIA = 98;
	public static final int ENTITY_MONOGRAPTUS = 99;
	public static final int ENTITY_DIDYMOGRAPTUS = 100;
	public static final int ENTITY_TETRAGRAPTUS = 101;
	public static final int ENTITY_MACLURINA = 102;
	public static final int ENTITY_CYCLONEMA = 103;
	public static final int ENTITY_BELANTSEA = 104;
	public static final ResourceLocation BELANTSEA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/belantsea"));
	public static final int ENTITY_CAMPBELLODUS = 105;
	public static final ResourceLocation CAMPBELLODUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/campbellodus"));
	public static final int ENTITY_PLATYSOMUS = 106;
	public static final ResourceLocation PLATYSOMUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/platysomus"));
	public static final int ENTITY_COCCOSTEUS = 107;
	public static final ResourceLocation COCCOSTEUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/coccosteus"));
	public static final int ENTITY_GEMUENDINA = 108;
	public static final ResourceLocation GEMUENDINA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/gemuendina"));
	public static final int ENTITY_PAGEA = 109;
	public static final ResourceLocation PAGEA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pagea"));
	public static final ResourceLocation PAGEA_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pagea_young"));
	public static final int ENTITY_ONYCHODUS = 110;
	public static final ResourceLocation ONYCHODUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/onychodus"));
	public static final ResourceLocation ONYCHODUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/onychodus_young"));
	public static final int ENTITY_FURCASTER = 111;
	public static final ResourceLocation FURCASTER_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/furcaster"));
	public static final int ENTITY_KALBARRIA = 112;
	public static final ResourceLocation KALBARRIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/kalbarria"));
	public static final int ENTITY_PALAEOISOPUS = 113;
	public static final ResourceLocation PALAEOISOPUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/palaeoisopus"));
	public static final int ENTITY_AINIKTOZOON = 114;
	public static final ResourceLocation AINIKTOZOON_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ainiktozoon"));
	public static final int ENTITY_TEMPEROCERAS = 115;
	public static final ResourceLocation TEMPEROCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/temperoceras"));
	public static final ResourceLocation TEMPEROCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/temperoceras_young"));
	public static final int ENTITY_VESTINAUTILUS = 116;
	public static final ResourceLocation VESTINAUTILUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/vestinautilus"));
	public static final int ENTITY_EGLONASPIS = 117;
	public static final ResourceLocation EGLONASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/eglonaspis"));
	public static final int ENTITY_ACADOARADOXIDES = 118;
	public static final ResourceLocation ACADOARADOXIDES_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/acadoaradoxides"));
	public static final int ENTITY_ALACARIS = 119;
	public static final ResourceLocation ALACARIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/alacaris"));
	public static final int ENTITY_SIBERION = 120;
	public static final ResourceLocation SIBERION_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/siberion"));
	public static final int ENTITY_PAUCIPODIA = 121;
	public static final ResourceLocation PAUCIPODIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/paucipodia"));
	public static final int ENTITY_LYRARAPAX = 122;
	public static final ResourceLocation LYRARAPAX_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/lyrarapax"));
	public static final int ENTITY_POMATRUM = 123;
	public static final ResourceLocation POMATRUM_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pomatrum"));
	public static final int ENTITY_BANFFIA = 124;
	public static final ResourceLocation BANFFIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/banffia"));
	public static final int ENTITY_YAWUNIK = 125;
	public static final ResourceLocation YAWUNIK_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/yawunik"));
	public static final int ENTITY_OPABINIA = 126;
	public static final ResourceLocation OPABINIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/opabinia"));
	public static final int ENTITY_TOKUMMIA = 127;
	public static final ResourceLocation TOKUMMIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/tokummia"));
	public static final int ENTITY_ODARAIA = 128;
	public static final ResourceLocation ODARAIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/odaraia"));
	public static final int ENTITY_AMPLECTOBELUA = 129;
	public static final ResourceLocation AMPLECTOBELUA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/amplectobelua"));
	public static final int ENTITY_ANOMALOCARIS = 130;
	public static final ResourceLocation ANOMALOCARIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/anomalocaris"));
	public static final int ENTITY_SPATHICEPHALUS = 131;
	public static final ResourceLocation SPATHICEPHALUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/spathicephalus"));
	public static final ResourceLocation SPATHICEPHALUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/spathicephalus_young"));
	public static final int ENTITY_LAMINACARIS = 132;
	public static final ResourceLocation LAMINACARIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/laminacaris"));
	public static final int ENTITY_CAMBRORASTER = 133;
	public static final ResourceLocation CAMBRORASTER_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cambroraster"));
	public static final int ENTITY_CANADASPIS = 134;
	public static final ResourceLocation CANADASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/canadaspis"));
	public static final int ENTITY_ELLIPSOCEPHALUS = 135;
	public static final ResourceLocation ELLIPSOCEPHALUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ellipsocephalus"));
	public static final int ENTITY_ELRATHIA = 136;
	public static final ResourceLocation ELRATHIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/elrathia"));
	public static final int ENTITY_EOREDLICHIA = 137;
	public static final ResourceLocation EOREDLICHIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/eoredlichia"));
	public static final int ENTITY_JIANSHANOPODIA = 138;
	public static final ResourceLocation JIANSHANOPODIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/jianshanopodia"));
	public static final int ENTITY_KODYMIRUS = 139;
	public static final ResourceLocation KODYMIRUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/kodymirus"));
	public static final int ENTITY_KERYGMACHELA = 140;
	public static final ResourceLocation KERYGMACHELA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/kerygmachela"));
	public static final int ENTITY_MARRELLA = 141;
	public static final ResourceLocation MARRELLA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/marrella"));
	public static final int ENTITY_NECTOCARIS = 142;
	public static final ResourceLocation NECTOCARIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/nectocaris"));
	public static final int ENTITY_PALAEOJELLY1 = 143;
	public static final int ENTITY_PALAEOJELLY2 = 144;
	public static final int ENTITY_PALAEOJELLY3 = 145;
	public static final int ENTITY_PALAEOJELLY4 = 146;
	public static final int ENTITY_GEMMACTENA = 147;
	public static final int ENTITY_BATOFASCICULUS = 148;
	public static final int ENTITY_PARADOXIDES = 149;
	public static final ResourceLocation PARADOXIDES_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/paradoxides"));
	public static final int ENTITY_YOHOIA = 150;
	public static final ResourceLocation YOHOIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/yohoia"));
	public static final int ENTITY_PAREXUS = 151;
	public static final ResourceLocation PAREXUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/parexus"));
	public static final int ENTITY_SYNOPHALOS = 152;
	public static final ResourceLocation SYNOPHALOS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/synophalos"));
	public static final int ENTITY_PIKAIA = 153;
	public static final ResourceLocation PIKAIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pikaia"));
	public static final int ENTITY_HALLUCIGENIA = 154;
	public static final ResourceLocation HALLUCIGENIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/hallucigenia"));
	public static final int ENTITY_PALAEODICTYOPTERA_NYMPH_PERM = 155;
	public static final int ENTITY_HYLONOMUS = 156;
	public static final ResourceLocation HYLONOMUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/hylonomus"));
	public static final ResourceLocation HYLONOMUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/hylonomus_young"));
	public static final int ENTITY_MICRODICTYON = 157;
	public static final ResourceLocation MICRODICTYON_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/microdictyon"));
	public static final int ENTITY_DEIROCERAS = 158;
	public static final ResourceLocation DEIROCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/deiroceras"));
	public static final ResourceLocation DEIROCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/deiroceras_young"));
	public static final int ENTITY_GONIOCERAS = 159;
	public static final ResourceLocation GONIOCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/gonioceras"));
	public static final ResourceLocation GONIOCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/gonioceras_young"));
	public static final int ENTITY_DIANIA = 160;
	public static final ResourceLocation DIANIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/diania"));
	public static final int ENTITY_METASPRIGGINA = 161;
	public static final ResourceLocation METASPRIGGINA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/metaspriggina"));
	public static final int ENTITY_TEGOPELTE = 162;
	public static final ResourceLocation TEGOPELTE_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/tegopelte"));
	public static final int ENTITY_WIWAXIA = 163;
	public static final ResourceLocation WIWAXIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/wiwaxia"));
	public static final int ENTITY_ODONTOGRIPHUS = 164;
	public static final ResourceLocation ODONTOGRIPHUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/odontogriphus"));
	public static final int ENTITY_XENUSION = 165;
	public static final ResourceLocation XENUSION_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/xenusion"));
	public static final int ENTITY_MIMETASTER = 166;
	public static final ResourceLocation MIMETASTER_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/mimetaster"));
	public static final int ENTITY_OMNIDENS = 167;
	public static final ResourceLocation OMNIDENS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/omnidens"));
	public static final ResourceLocation OMNIDENS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/omnidens_young"));
	public static final int ENTITY_BASILOCERAS = 168;
	public static final ResourceLocation BASILOCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/basiloceras"));
	public static final ResourceLocation BASILOCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/basiloceras_young"));
	public static final int ENTITY_WEBSTEROPRION_HOLE = 169;
	public static final int ENTITY_WEBSTEROPRION = 170;
	public static final ResourceLocation WEBSTEROPRION_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/websteroprion"));
	public static final int ENTITY_CHELONIELLON = 171;
	public static final ResourceLocation CHELONIELLON_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cheloniellon"));
	public static final int ENTITY_SCORPION_GIGANTOSCORPIO = 172;
	public static final ResourceLocation GIGANTOSCORPIO_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/scorpion_gigantoscorpio"));
	public static final int ENTITY_SCORPION_OPSIEOBUTHUS = 173;
	public static final ResourceLocation OPSIEOBUTHUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/scorpion_opsieobuthus"));
	public static final int ENTITY_SCORPION_PULMONOSCORPIUS = 174;
	public static final ResourceLocation PULMONOSCORPIUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/scorpion_pulmonoscorpius"));
	public static final int ENTITY_SCORPION_GONDWANASCORPIO = 175;
	public static final ResourceLocation GONDWANASCORPIO_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/scorpion_gondwanascorpio"));
	public static final int ENTITY_OTTOIA = 176;
	public static final ResourceLocation OTTOIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ottoia"));
	public static final int ENTITY_HETEROSTEUS = 177;
	public static final ResourceLocation HETEROSTEUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/heterosteus"));
	public static final ResourceLocation HETEROSTEUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/heterosteus_young"));
	public static final int ENTITY_MOSCHOPS = 178;
	public static final ResourceLocation MOSCHOPS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/moschops"));
	public static final ResourceLocation MOSCHOPS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/moschops_young"));
	public static final int ENTITY_ERYOPS = 179;
	public static final ResourceLocation ERYOPS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/eryops"));
	public static final ResourceLocation ERYOPS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/eryops_young"));
	public static final int ENTITY_COTYLORHYNCHUS = 180;
	public static final ResourceLocation COTYLORHYNCHUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cotylorhynchus"));
	public static final ResourceLocation COTYLORHYNCHUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/cotylorhynchus_young"));
	public static final int ENTITY_OPHIACODON = 181;
	public static final ResourceLocation OPHIACODON_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ophiacodon"));
	public static final ResourceLocation OPHIACODON_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/ophiacodon_young"));
	public static final int ENTITY_ACROLEPIS = 182;
	public static final ResourceLocation ACROLEPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/acrolepis"));
	public static final int ENTITY_COELACANTHUS = 183;
	public static final ResourceLocation COELACANTHUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/coelacanthus"));
	public static final ResourceLocation COELACANTHUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/coelacanthus_young"));
	public static final int ENTITY_HELICOPRION = 184;
	public static final ResourceLocation HELICOPRION_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/helicoprion"));
	public static final ResourceLocation HELICOPRION_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/helicoprion_young"));
	public static final int ENTITY_PEDERPES = 185;
	public static final ResourceLocation PEDERPES_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pederpes"));
	public static final ResourceLocation PEDERPES_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pederpes_young"));
	public static final int ENTITY_DIMETRODON = 186;
	public static final ResourceLocation DIMETRODON_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/dimetrodon"));
	public static final ResourceLocation DIMETRODON_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/dimetrodon_young"));
	public static final int ENTITY_GORGONOPS = 187;
	public static final ResourceLocation GORGONOPS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/gorgonops"));
	public static final ResourceLocation GORGONOPS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/gorgonops_young"));
	public static final int ENTITY_INOSTRANCEVIA = 188;
	public static final ResourceLocation INOSTRANCEVIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/inostrancevia"));
	public static final ResourceLocation INOSTRANCEVIA_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/inostrancevia_young"));
	public static final int ENTITY_DUNKLEOSTEUS = 189;
	public static final ResourceLocation DUNKLEOSTEUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/dunkleosteus"));
	public static final ResourceLocation DUNKLEOSTEUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/dunkleosteus_young"));
	public static final int ENTITY_ACANTHOSTEGA = 190;
	public static final ResourceLocation ACANTHOSTEGA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/acanthostega"));
	public static final ResourceLocation ACANTHOSTEGA_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/acanthostega_young"));
	public static final int ENTITY_SPHENACODON = 191;
	public static final ResourceLocation SPHENACODON_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/sphenacodon"));
	public static final ResourceLocation SPHENACODON_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/sphenacodon_young"));
	public static final int ENTITY_SCUTOSAURUS = 192;
	public static final ResourceLocation SCUTOSAURUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/scutosaurus"));
	public static final ResourceLocation SCUTOSAURUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/scutosaurus_young"));
	public static final int ENTITY_VIVAXOSAURUS = 193;
	public static final ResourceLocation VIVAXOSAURUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/vivaxosaurus"));
	public static final ResourceLocation VIVAXOSAURUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/vivaxosaurus_young"));
	public static final int ENTITY_ESTEMMENOSUCHUS = 194;
	public static final ResourceLocation ESTEMMENOSUCHUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/estemmenosuchus"));
	public static final ResourceLocation ESTEMMENOSUCHUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/estemmenosuchus_young"));
	public static final int ENTITY_PLATYHYSTRIX = 195;
	public static final ResourceLocation PLATYHYSTRIX_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/platyhystrix"));
	public static final ResourceLocation PLATYHYSTRIX_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/platyhystrix_young"));
	public static final int ENTITY_TRAQUAIRIUS = 196;
	public static final ResourceLocation TRAQUAIRIUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/traquairius"));
	public static final int ENTITY_DIICTODON = 197;
	public static final ResourceLocation DIICTODON_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/diictodon"));
	public static final ResourceLocation DIICTODON_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/diictodon_young"));
	public static final int ENTITY_EOSIMOPS = 198;
	public static final ResourceLocation EOSIMOPS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/eosimops"));
	public static final ResourceLocation EOSIMOPS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/eosimops_young"));
	public static final int ENTITY_PROSICTODON = 199;
	public static final ResourceLocation PROSICTODON_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/prosictodon"));
	public static final ResourceLocation PROSICTODON_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/prosictodon_young"));
	public static final int ENTITY_ROBERTIA = 200;
	public static final ResourceLocation ROBERTIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/robertia"));
	public static final ResourceLocation ROBERTIA_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/robertia_young"));
	public static final int ENTITY_BUSHIZHEIA = 201;
	public static final ResourceLocation BUSHIZHEIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/bushizheia"));
	public static final int ENTITY_PHANTASPIS = 202;
	public static final ResourceLocation PHANTASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/phantaspis"));
	public static final int ENTITY_SIDNEYIA = 203;
	public static final ResourceLocation SIDNEYIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/sidneyia"));
	public static final int ENTITY_PHARYNGOLEPIS = 204;
	public static final ResourceLocation PHARYNGOLEPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pharyngolepis"));
	public static final int ENTITY_PLATYCARASPIS = 205;
	public static final ResourceLocation PLATYCARASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/platycaraspis"));
	public static final int ENTITY_CROTALOCEPHALUS = 206;
	public static final ResourceLocation CROTALOCEPHALUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/crotalocephalus"));
	public static final int ENTITY_DALMANITES = 207;
	public static final ResourceLocation DALMANITES_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/dalmanites"));
	public static final int ENTITY_DREPANASPIS = 208;
	public static final ResourceLocation DREPANASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/drepanaspis"));
	public static final int ENTITY_EUSTHENOPTERON = 209;
	public static final ResourceLocation EUSTHENOPTERON_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/eusthenopteron"));
	public static final int ENTITY_LUNASPIS = 210;
	public static final ResourceLocation LUNASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/lunaspis"));
	public static final int ENTITY_LUNGMENSHANASPIS = 211;
	public static final ResourceLocation LUNGMENSHANASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/lungmenshanaspis"));
	public static final int ENTITY_PANDERICHTHYS = 212;
	public static final ResourceLocation PANDERICHTHYS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/panderichthys"));
	public static final int ENTITY_BANDRINGA = 213;
	public static final ResourceLocation BANDRINGA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/bandringa"));
	public static final int ENTITY_EDESTUS = 214;
	public static final ResourceLocation EDESTUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/edestus"));
	public static final ResourceLocation EDESTUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/edestus_young"));
	public static final int ENTITY_GYRACANTHIDES = 215;
	public static final ResourceLocation GYRACANTHIDES_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/gyracanthides"));
	public static final int ENTITY_INIOPTERYX = 216;
	public static final ResourceLocation INIOPTERYX_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/iniopteryx"));
	public static final int ENTITY_JANASSA = 217;
	public static final ResourceLocation JANASSA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/janassa"));
	public static final int ENTITY_MOOREOCERAS = 218;
	public static final ResourceLocation MOOREOCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/mooreoceras"));
	public static final ResourceLocation MOOREOCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/mooreoceras_young"));
	public static final int ENTITY_PHANEROTINUS = 219;
	public static final int ENTITY_RAYONNOCERAS = 220;
	public static final ResourceLocation RAYONNOCERAS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/rayonnoceras"));
	public static final ResourceLocation RAYONNOCERAS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/rayonnoceras_young"));
	public static final int ENTITY_PHLEGETHONTIA = 221;
	public static final ResourceLocation PHLEGETHONTIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/phlegethontia"));
	public static final int ENTITY_PHOLIDERPETON = 222;
	public static final ResourceLocation PHOLIDERPETON_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pholiderpeton"));
	public static final ResourceLocation PHOLIDERPETON_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/pholiderpeton_young"));
	public static final int ENTITY_PROTEROGYRINUS = 223;
	public static final ResourceLocation PROTEROGYRINUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/proterogyrinus"));
	public static final ResourceLocation PROTEROGYRINUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/proterogyrinus_young"));
	public static final int ENTITY_GNATHORHIZA = 224;
	public static final ResourceLocation GNATHORHIZA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/gnathorhiza"));
	public static final int ENTITY_KAIBABVENATOR = 225;
	public static final ResourceLocation KAIBABVENATOR_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/kaibabvenator"));
	public static final ResourceLocation KAIBABVENATOR_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/kaibabvenator_young"));
	public static final int ENTITY_LISTRACANTHUS = 226;
	public static final ResourceLocation LISTRACANTHUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/listracanthus"));
	public static final ResourceLocation LISTRACANTHUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/listracanthus_young"));
	public static final int ENTITY_MENASPIS = 227;
	public static final ResourceLocation MENASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/menaspis"));
	public static final int ENTITY_MESOSAURUS = 228;
	public static final ResourceLocation MESOSAURUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/mesosaurus"));
	public static final ResourceLocation MESOSAURUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/mesosaurus_young"));
	public static final int ENTITY_ACANTHOSTOMATOPS = 229;
	public static final ResourceLocation ACANTHOSTOMATOPS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/acanthostomatops"));
	public static final ResourceLocation ACANTHOSTOMATOPS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/acanthostomatops_young"));
	public static final int ENTITY_DIADECTES = 230;
	public static final ResourceLocation DIADECTES_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/diadectes"));
	public static final ResourceLocation DIADECTES_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/diadectes_young"));
	public static final int ENTITY_DVINIA = 231;
	public static final ResourceLocation DVINIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/dvinia"));
	public static final ResourceLocation DVINIA_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/dvinia_young"));
	public static final int ENTITY_LABIDOSAURUS = 232;
	public static final ResourceLocation LABIDOSAURUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/labidosaurus"));
	public static final ResourceLocation LABIDOSAURUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/labidosaurus_young"));
	public static final int ENTITY_PROCYNOSUCHUS = 233;
	public static final ResourceLocation PROCYNOSUCHUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/procynosuchus"));
	public static final ResourceLocation PROCYNOSUCHUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/procynosuchus_young"));
	public static final int ENTITY_ARCHOBLATTINA = 234;
	public static final ResourceLocation BUG_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/bug"));
	public static final int ENTITY_PLATYLOMASPIS = 235;
	public static final ResourceLocation PLATYLOMASPIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/platylomaspis"));
	public static final int ENTITY_ARCHOBLATTINA_NYMPH = 236;
	public static final int ENTITY_ROACHOID_ARID = 237;
	public static final int ENTITY_GERARUS = 238;
	public static final ResourceLocation GERARUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/gerarus"));
	public static final int ENTITY_ROACHOID_FOREST = 239;
	public static final int ENTITY_ROACHOID_SWAMP = 240;
	public static final int ENTITY_MEGANEUROPSIS = 241;
	public static final ResourceLocation MEGANEUROPSIS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/meganeuropsis"));
	public static final int ENTITY_MEGANEUROPSIS_NYMPH = 242;
	public static final int ENTITY_REMIGIOMONTANUS = 243;
	public static final ResourceLocation REMIGIOMONTANUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/remigiomontanus"));
	public static final ResourceLocation REMIGIOMONTANUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/remigiomontanus_young"));
	public static final int ENTITY_PRIONOSUCHUS = 244;
	public static final ResourceLocation PRIONOSUCHUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/prionosuchus"));
	public static final ResourceLocation PRIONOSUCHUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/prionosuchus_young"));
	public static final int ENTITY_CLAUDIOSAURUS = 245;
	public static final ResourceLocation CLAUDIOSAURUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/claudiosaurus"));
	public static final ResourceLocation CLAUDIOSAURUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/claudiosaurus_young"));
	public static final int ENTITY_SAIVODUS = 246;
	public static final ResourceLocation SAIVODUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/saivodus"));
	public static final ResourceLocation SAIVODUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/saivodus_young"));
	public static final int ENTITY_TAPINOCEPHALUS = 247;
	public static final ResourceLocation TAPINOCEPHALUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/tapinocephalus"));
	public static final ResourceLocation TAPINOCEPHALUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/tapinocephalus_young"));
	public static final int ENTITY_ANTEOSAURUS = 248;
	public static final ResourceLocation ANTEOSAURUS_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/anteosaurus"));
	public static final ResourceLocation ANTEOSAURUS_LOOT_YOUNG = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/anteosaurus_young"));
	public static final int ENTITY_ALBERTONIA = 249;
	public static final ResourceLocation ALBERTONIA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/albertonia"));

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LepidodendronConfig.load(event);
		MinecraftForge.EVENT_BUS.register(this);
		GameRegistry.registerWorldGenerator(elements, 5);
		GameRegistry.registerFuelHandler(elements);
		//NetworkRegistry.INSTANCE.registerGuiHandler(this, new ElementsLepidodendronMod.GuiHandler());
		elements.preInit(event);
		MinecraftForge.EVENT_BUS.register(elements);
		elements.getElements().forEach(element -> element.preInit(event));
		EntityRegistries.registerEntities();
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		elements.getElements().forEach(element -> element.init(event));
		proxy.init(event);
		MinecraftForge.TERRAIN_GEN_BUS.register(new LepidodendronDecorationHandler());
		MinecraftForge.ORE_GEN_BUS.register(new LepidodendronOreHandler());
		MinecraftForge.EVENT_BUS.register(new LepidodendronEventSubscribers());
		MinecraftForge.EVENT_BUS.register(new LepidodendronFogSubscribers());
		MinecraftForge.EVENT_BUS.register(new LepidodendronWandHandler());
		MinecraftForge.EVENT_BUS.register(new LepidodendronHoeHandler());

		ModTriggers.registerTriggers();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		elements.getElements().forEach(element -> element.serverLoad(event));
		proxy.serverLoad(event);
		event.registerServerCommand(new Summoner());
	}

	@SubscribeEvent
	public void registerVillagers(RegistryEvent.Register<VillagerRegistry.VillagerProfession> event) {
		event.getRegistry().registerAll(Villager.PALAEOBOTANIST_PROFESSION);
		Villager.register();
	}

	@SubscribeEvent
	public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().registerAll(Enchantments.TIME_REVERSAL);
	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(elements.getBlocks().stream().map(Supplier::get).toArray(Block[]::new));

		BlockFirePF newFire = (BlockFirePF) (new BlockFirePF()).setHardness(0.0F).setLightLevel(1.0F).setTranslationKey("fire").setRegistryName(Objects.requireNonNull(Blocks.FIRE.getRegistryName()));

		event.getRegistry().register(newFire);

	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(elements.getItems().stream().map(Supplier::get).toArray(Item[]::new));
	}

	@SubscribeEvent
	public void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().registerAll(elements.getBiomes().stream().map(Supplier::get).toArray(Biome[]::new));
	}

	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		event.getRegistry().registerAll(elements.getEntities().stream().map(Supplier::get).toArray(EntityEntry[]::new));
	}

	@SubscribeEvent
	public void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll(elements.getPotions().stream().map(Supplier::get).toArray(Potion[]::new));
	}

	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
		elements.registerSounds(event);
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		elements.getElements().forEach(element -> element.registerModels(event));
	}
	static {
		FluidRegistry.enableUniversalBucket();
	}
}
