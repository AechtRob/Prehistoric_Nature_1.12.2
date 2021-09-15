package net.lepidodendron;

import net.lepidodendron.enchantments.Enchantments;
import net.lepidodendron.palaeobotanist.entity.Villager;
import net.lepidodendron.util.Summoner;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
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

import java.util.function.Supplier;

@Mod(modid = LepidodendronMod.MODID, name = LepidodendronMod.NAME, version = LepidodendronMod.VERSION, dependencies = "required-after:llibrary@[1.7.17,)")
public class LepidodendronMod {
	public static final String MODID = "lepidodendron";
	public static final String NAME = "Prehistoric Nature";
	public static final String VERSION = "49.0";
	public static final SimpleNetworkWrapper PACKET_HANDLER = NetworkRegistry.INSTANCE.newSimpleChannel("lepidodendron:a");
	@SidedProxy(clientSide = "net.lepidodendron.ClientProxyLepidodendronMod", serverSide = "net.lepidodendron.ServerProxyLepidodendronMod")
	public static IProxyLepidodendronMod proxy;
	@Mod.Instance(MODID)
	public static LepidodendronMod instance;
	public ElementsLepidodendronMod elements = new ElementsLepidodendronMod();

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
	public static final ResourceLocation PALAEODICTYOPTERA_LOOT = LootTableList.register(new ResourceLocation(LepidodendronMod.MODID, "entity/palaeodictyoptera"));
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
		MinecraftForge.EVENT_BUS.register(new LepidodendronDimensionHandler());
		MinecraftForge.EVENT_BUS.register(new LepidodendronWandHandler());
		MinecraftForge.EVENT_BUS.register(new LepidodendronHoeHandler());
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
