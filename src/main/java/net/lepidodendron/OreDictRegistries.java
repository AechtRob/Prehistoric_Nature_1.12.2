/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class ElementsLepidodendronMod.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it in
 * "Workspace" -> "Source" menu.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.lepidodendron;

import net.lepidodendron.item.entities.*;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import net.lepidodendron.block.*;
import net.lepidodendron.item.*;

import net.minecraft.block.Block;

@ElementsLepidodendronMod.ModElement.Tag
public class OreDictRegistries extends ElementsLepidodendronMod.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public OreDictRegistries(ElementsLepidodendronMod instance) {
		super(instance, 66);
	}

	@Override
	public void init(FMLInitializationEvent event) {

        String[] var2 = LepidodendronConfig.genLogResin;
        int var3 = var2.length;
        int meta;

		try {			
			for(Object block : Block.REGISTRY){ 
				//String name = Block.REGISTRY.getNameForObject((Block)block).toString();
				if(true) {;
					//outstream.write(name);
					//outstream.newLine();
					//System.err.println("BLOCK FOUND: " + name);

			        for(int var4 = 0; var4 < var3; ++var4) {
			            String checkBlock = var2[var4];
			            //Is there a metadata tag? If so, keep it and strip it out of the string:
						int strPos = checkBlock.indexOf(":", checkBlock.indexOf(":") + 1);
						//System.err.println("ORGBLOCK: " + checkBlock);
						//System.err.println("SECOND COLON: " + strPos);
						if (strPos > 0) {
							meta = (int) Integer.parseInt(checkBlock.substring(strPos + 1));
							checkBlock = checkBlock.substring(0, strPos);
							//System.err.println("REVBLOCK: " + checkBlock + " meta: " + meta);
						}
						else {
							meta = -1;
						}

			            if (checkBlock.equalsIgnoreCase(Block.REGISTRY.getNameForObject((Block)block).toString())) {
			            	if(meta == -1) {
			            		OreDictionary.registerOre("logResin", new ItemStack((Block)block, (int) (1)));
			            	}
			            	else {
			            		OreDictionary.registerOre("logResin", new ItemStack((Block)block, (int) (1), meta));
			            	}
			            }
	
					}
				}
			}
		} catch (Exception e) {
		}		

		var2 = LepidodendronConfig.genPlantPrehistoric;
        var3 = var2.length;

		try {			
			for(Object block : Block.REGISTRY){ 
				//String name = Block.REGISTRY.getNameForObject((Block)block).toString();
				if(true) {;
					//outstream.write(name);
					//outstream.newLine();
					//System.err.println("BLOCK FOUND: " + name);

			        for(int var4 = 0; var4 < var3; ++var4) {
			            String checkBlock = var2[var4];
			            //Is there a metadata tag? If so, keep it and strip it out of the string:
						int strPos = checkBlock.indexOf(":", checkBlock.indexOf(":") + 1);
						//System.err.println("ORGBLOCK: " + checkBlock);
						//System.err.println("SECOND COLON: " + strPos);
						if (strPos > 0) {
							meta = (int) Integer.parseInt(checkBlock.substring(strPos + 1));
							checkBlock = checkBlock.substring(0, strPos);
							//System.err.println("REVBLOCK: " + checkBlock + " meta: " + meta);
						}
						else {
							meta = -1;
						}

			            if (checkBlock.equalsIgnoreCase(Block.REGISTRY.getNameForObject((Block)block).toString())) {
			            	if(meta == -1) {
			            		OreDictionary.registerOre("plantPrehistoric", new ItemStack((Block)block, (int) (1)));
			            	}
			            	else {
			            		OreDictionary.registerOre("plantPrehistoric", new ItemStack((Block)block, (int) (1), meta));
			            	}
			            }
	
					}
				}
			}
		} catch (Exception e) {
		}

		OreDictionary.registerOre("listAllfishraw", Items.FISH);
		OreDictionary.registerOre("listAllmeatraw", Items.FISH);
		OreDictionary.registerOre("foodMeat", Items.FISH);
		OreDictionary.registerOre("listAllfishcooked", Items.COOKED_FISH);
		OreDictionary.registerOre("foodCooked", Items.COOKED_FISH);
		OreDictionary.registerOre("foodMeat", Items.COOKED_FISH);
		OreDictionary.registerOre("listAllmeatcooked", Items.COOKED_FISH);

		OreDictionary.registerOre("listAllmeatraw", Items.BEEF);
		OreDictionary.registerOre("foodMeat", Items.BEEF);
		OreDictionary.registerOre("foodCooked", Items.COOKED_BEEF);
		OreDictionary.registerOre("foodMeat", Items.COOKED_BEEF);
		OreDictionary.registerOre("listAllmeatcooked", Items.COOKED_BEEF);

		OreDictionary.registerOre("listAllmeatraw", Items.PORKCHOP);
		OreDictionary.registerOre("foodMeat", Items.PORKCHOP);
		OreDictionary.registerOre("foodCooked", Items.COOKED_PORKCHOP);
		OreDictionary.registerOre("foodMeat", Items.COOKED_PORKCHOP);
		OreDictionary.registerOre("listAllmeatcooked", Items.COOKED_PORKCHOP);

		OreDictionary.registerOre("listAllmeatraw", Items.MUTTON);
		OreDictionary.registerOre("foodMeat", Items.MUTTON);
		OreDictionary.registerOre("foodCooked", Items.COOKED_MUTTON);
		OreDictionary.registerOre("foodMeat", Items.COOKED_MUTTON);
		OreDictionary.registerOre("listAllmeatcooked", Items.COOKED_MUTTON);

		OreDictionary.registerOre("listAllmeatraw", Items.CHICKEN);
		OreDictionary.registerOre("foodMeat", Items.CHICKEN);
		OreDictionary.registerOre("foodCooked", Items.COOKED_CHICKEN);
		OreDictionary.registerOre("foodMeat", Items.COOKED_CHICKEN);
		OreDictionary.registerOre("listAllmeatcooked", Items.COOKED_CHICKEN);

		OreDictionary.registerOre("listAllmeatraw", Items.RABBIT);
		OreDictionary.registerOre("foodMeat", Items.RABBIT);
		OreDictionary.registerOre("foodCooked", Items.COOKED_RABBIT);
		OreDictionary.registerOre("foodMeat", Items.COOKED_RABBIT);
		OreDictionary.registerOre("listAllmeatcooked", Items.COOKED_RABBIT);

		OreDictionary.registerOre("listAllfishraw", ItemAcanthodesRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemAcanthodesRaw.block);
		OreDictionary.registerOre("foodMeat", ItemAcanthodesRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemAcanthodesCooked.block);
		OreDictionary.registerOre("foodCooked", ItemAcanthodesCooked.block);
		OreDictionary.registerOre("foodMeat", ItemAcanthodesCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemAcanthodesCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemBothriolepisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemBothriolepisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemBothriolepisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemBothriolepisCooked.block);
		OreDictionary.registerOre("foodCooked", ItemBothriolepisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemBothriolepisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemBothriolepisCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemPteraspisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemPteraspisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemPteraspisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemPteraspisCooked.block);
		OreDictionary.registerOre("foodCooked", ItemPteraspisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemPteraspisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemPteraspisCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemArandaspisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemArandaspisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemArandaspisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemArandaspisCooked.block);
		OreDictionary.registerOre("foodCooked", ItemArandaspisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemArandaspisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemArandaspisCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemCyrtocerasRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemCyrtocerasRaw.block);
		OreDictionary.registerOre("foodMeat", ItemCyrtocerasRaw.block);
		OreDictionary.registerOre("listAllfishraw", ItemAphetocerasRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemAphetocerasRaw.block);
		OreDictionary.registerOre("foodMeat", ItemAphetocerasRaw.block);
		OreDictionary.registerOre("listAllfishraw", ItemCamerocerasRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemCamerocerasRaw.block);
		OreDictionary.registerOre("foodMeat", ItemCamerocerasRaw.block);
		OreDictionary.registerOre("listAllfishraw", ItemEndocerasRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemEndocerasRaw.block);
		OreDictionary.registerOre("foodMeat", ItemEndocerasRaw.block);
		OreDictionary.registerOre("listAllfishraw", ItemOrthocerasRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemOrthocerasRaw.block);
		OreDictionary.registerOre("foodMeat", ItemOrthocerasRaw.block);
		OreDictionary.registerOre("listAllfishraw", ItemAmmoniteRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemAmmoniteRaw.block);
		OreDictionary.registerOre("foodMeat", ItemAmmoniteRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemPalaeoCalamari.block);
		OreDictionary.registerOre("foodCooked", ItemPalaeoCalamari.block);
		OreDictionary.registerOre("foodMeat", ItemPalaeoCalamari.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemPalaeoCalamari.block);

		OreDictionary.registerOre("listAllfishraw", ItemEurypterusRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemEurypterusRaw.block);
		OreDictionary.registerOre("foodMeat", ItemEurypterusRaw.block);
		OreDictionary.registerOre("listAllfishraw", ItemHibbertopterusRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemHibbertopterusRaw.block);
		OreDictionary.registerOre("foodMeat", ItemHibbertopterusRaw.block);
		OreDictionary.registerOre("listAllfishraw", ItemJaekelopterusRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemJaekelopterusRaw.block);
		OreDictionary.registerOre("foodMeat", ItemJaekelopterusRaw.block);
		OreDictionary.registerOre("listAllfishraw", ItemMixopterusRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemMixopterusRaw.block);
		OreDictionary.registerOre("foodMeat", ItemMixopterusRaw.block);
		OreDictionary.registerOre("listAllfishraw", ItemPterygotusRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemPterygotusRaw.block);
		OreDictionary.registerOre("foodMeat", ItemPterygotusRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemEurypteridCooked.block);
		OreDictionary.registerOre("foodCooked", ItemEurypteridCooked.block);
		OreDictionary.registerOre("foodMeat", ItemEurypteridCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemEurypteridCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemSacabambaspisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemSacabambaspisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemSacabambaspisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemSacabambaspisCooked.block);
		OreDictionary.registerOre("foodCooked", ItemSacabambaspisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemSacabambaspisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemSacabambaspisCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemHibernaspisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemHibernaspisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemHibernaspisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemHibernaspisCooked.block);
		OreDictionary.registerOre("foodCooked", ItemHibernaspisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemHibernaspisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemHibernaspisCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemPromissumRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemPromissumRaw.block);
		OreDictionary.registerOre("foodMeat", ItemPromissumRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemPromissumCooked.block);
		OreDictionary.registerOre("foodCooked", ItemPromissumCooked.block);
		OreDictionary.registerOre("foodMeat", ItemPromissumCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemPromissumCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemFurcacaudaRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemFurcacaudaRaw.block);
		OreDictionary.registerOre("foodMeat", ItemFurcacaudaRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemFurcacaudaCooked.block);
		OreDictionary.registerOre("foodCooked", ItemFurcacaudaCooked.block);
		OreDictionary.registerOre("foodMeat", ItemFurcacaudaCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemFurcacaudaCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemIsotelusRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemIsotelusRaw.block);
		OreDictionary.registerOre("foodMeat", ItemIsotelusRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemFurcacaudaCooked.block);
		OreDictionary.registerOre("foodCooked", ItemFurcacaudaCooked.block);
		OreDictionary.registerOre("foodMeat", ItemFurcacaudaCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemFurcacaudaCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemTerataspisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemTerataspisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemTerataspisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemFurcacaudaCooked.block);
		OreDictionary.registerOre("foodCooked", ItemFurcacaudaCooked.block);
		OreDictionary.registerOre("foodMeat", ItemFurcacaudaCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemFurcacaudaCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemSquatinactisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemSquatinactisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemSquatinactisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemSquatinactisCooked.block);
		OreDictionary.registerOre("foodCooked", ItemSquatinactisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemSquatinactisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemSquatinactisCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemCheirurusRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemCheirurusRaw.block);
		OreDictionary.registerOre("foodMeat", ItemCheirurusRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemCheirurusCooked.block);
		OreDictionary.registerOre("foodCooked", ItemCheirurusCooked.block);
		OreDictionary.registerOre("foodMeat", ItemCheirurusCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemCheirurusCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemAsaphusRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemAsaphusRaw.block);
		OreDictionary.registerOre("foodMeat", ItemAsaphusRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemAsaphusRaw.block);
		OreDictionary.registerOre("foodCooked", ItemAsaphusCooked.block);
		OreDictionary.registerOre("foodMeat", ItemAsaphusCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemAsaphusCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemWalliseropsRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemWalliseropsRaw.block);
		OreDictionary.registerOre("foodMeat", ItemWalliseropsRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemWalliseropsCooked.block);
		OreDictionary.registerOre("foodCooked", ItemWalliseropsCooked.block);
		OreDictionary.registerOre("foodMeat", ItemWalliseropsCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemWalliseropsCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemQilinyuRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemQilinyuRaw.block);
		OreDictionary.registerOre("foodMeat", ItemQilinyuRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemQilinyuCooked.block);
		OreDictionary.registerOre("foodCooked", ItemQilinyuCooked.block);
		OreDictionary.registerOre("foodMeat", ItemQilinyuCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemQilinyuCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemPoraspisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemPoraspisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemPoraspisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemPoraspisCooked.block);
		OreDictionary.registerOre("foodCooked", ItemPoraspisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemPoraspisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemPoraspisCooked.block);

		OreDictionary.registerOre("listAllmeatraw", ItemPalaeodictyopteraRaw.block);
		OreDictionary.registerOre("foodMeat", ItemPalaeodictyopteraRaw.block);
		OreDictionary.registerOre("foodCooked", ItemPalaeodictyopteraCooked.block);
		OreDictionary.registerOre("foodMeat", ItemPalaeodictyopteraCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemPalaeodictyopteraCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemSchinderhannesRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemSchinderhannesRaw.block);
		OreDictionary.registerOre("foodMeat", ItemSchinderhannesRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemSchinderhannesCooked.block);
		OreDictionary.registerOre("foodCooked", ItemSchinderhannesCooked.block);
		OreDictionary.registerOre("foodMeat", ItemSchinderhannesCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemSchinderhannesCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemTitanichthysRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemTitanichthysRaw.block);
		OreDictionary.registerOre("foodMeat", ItemTitanichthysRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemTitanichthysCooked.block);
		OreDictionary.registerOre("foodCooked", ItemTitanichthysCooked.block);
		OreDictionary.registerOre("foodMeat", ItemTitanichthysCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemTitanichthysCooked.block);

		OreDictionary.registerOre("listAllmeatraw", ItemLimnoscelisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemLimnoscelisRaw.block);
		OreDictionary.registerOre("foodCooked", ItemLimnoscelisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemLimnoscelisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemLimnoscelisCooked.block);

		OreDictionary.registerOre("listAllmeatraw", ItemIchthyostegaRaw.block);
		OreDictionary.registerOre("foodMeat", ItemIchthyostegaRaw.block);
		OreDictionary.registerOre("foodCooked", ItemIchthyostegaCooked.block);
		OreDictionary.registerOre("foodMeat", ItemIchthyostegaCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemIchthyostegaCooked.block);

		OreDictionary.registerOre("listAllmeatraw", ItemAmphibamusRaw.block);
		OreDictionary.registerOre("foodMeat", ItemAmphibamusRaw.block);
		OreDictionary.registerOre("foodCooked", ItemAmphibamusCooked.block);
		OreDictionary.registerOre("foodMeat", ItemAmphibamusCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemAmphibamusCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemLunataspisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemLunataspisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemLunataspisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemLunataspisRaw.block);
		OreDictionary.registerOre("foodCooked", ItemLunataspisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemLunataspisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemLunataspisCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemSelenopeltisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemSelenopeltisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemSelenopeltisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemSelenopeltisRaw.block);
		OreDictionary.registerOre("foodCooked", ItemSelenopeltisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemSelenopeltisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemSelenopeltisCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemHemicyclaspisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemHemicyclaspisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemHemicyclaspisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemHemicyclaspisRaw.block);
		OreDictionary.registerOre("foodCooked", ItemHemicyclaspisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemHemicyclaspisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemHemicyclaspisCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemAteleaspisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemAteleaspisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemAteleaspisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemAteleaspisRaw.block);
		OreDictionary.registerOre("foodCooked", ItemAteleaspisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemAteleaspisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemAteleaspisCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemCephalaspisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemCephalaspisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemCephalaspisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemCephalaspisRaw.block);
		OreDictionary.registerOre("foodCooked", ItemCephalaspisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemCephalaspisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemCephalaspisCooked.block);

		OreDictionary.registerOre("listAllfishraw", ItemPsarolepisRaw.block);
		OreDictionary.registerOre("listAllmeatraw", ItemPsarolepisRaw.block);
		OreDictionary.registerOre("foodMeat", ItemPsarolepisRaw.block);
		OreDictionary.registerOre("listAllfishcooked", ItemPsarolepisRaw.block);
		OreDictionary.registerOre("foodCooked", ItemPsarolepisCooked.block);
		OreDictionary.registerOre("foodMeat", ItemPsarolepisCooked.block);
		OreDictionary.registerOre("listAllmeatcooked", ItemPsarolepisCooked.block);



		OreDictionary.registerOre("dirt", BlockPrehistoricGroundLush.block);
		OreDictionary.registerOre("dirt", BlockPrehistoricGroundBasic.block);
		OreDictionary.registerOre("dirt", BlockSandyDirt.block);
		OreDictionary.registerOre("dirt", BlockCoarseSandyDirtPangaean.block);
		OreDictionary.registerOre("dirt", BlockSandyDirtPangaean.block);
		OreDictionary.registerOre("dirt", Blocks.DIRT);

		OreDictionary.registerOre("blockSulphur", BlockSulphur.block);
		OreDictionary.registerOre("blockSulfur", BlockSulphur.block);

		OreDictionary.registerOre("stone", BlockPrehistoricGroundMossy.block);
		OreDictionary.registerOre("stone", BlockLavaRock.block);
		OreDictionary.registerOre("stone", BlockLavaRockPolished.block);
		OreDictionary.registerOre("stone", BlockPlantFossil.block);

		OreDictionary.registerOre("sandstone", BlockSandstonePangaean.block);
		OreDictionary.registerOre("sandstone", BlockSandstonePangaeanCarved.block);
		OreDictionary.registerOre("sandstone", BlockSandstonePangaeanSmooth.block);
		OreDictionary.registerOre("sandstone", Blocks.SANDSTONE);
		OreDictionary.registerOre("sandstone", Blocks.RED_SANDSTONE);

		OreDictionary.registerOre("sand", BlockPrehistoricGroundSand.block);
		OreDictionary.registerOre("sand", BlockSandPangaean.block);
		OreDictionary.registerOre("sand", Blocks.SAND);
		
		//Probably some compats in here:
		OreDictionary.registerOre("stone", BlockAraucarioxylonLogPetrified.block);
		OreDictionary.registerOre("stone", BlockAraucarioxylonPlanksPetrified.block);
		OreDictionary.registerOre("slabStone", BlockAraucarioxylonSlabPetrified.block);
		OreDictionary.registerOre("stairStone", BlockAraucarioxylonStairsPetrified.block);
		OreDictionary.registerOre("wallStone", BlockAraucarioxylonWall.block);
		OreDictionary.registerOre("stone", BlockAraucarioxylonPlanksPetrifiedBricks.block);
		OreDictionary.registerOre("slabStone", BlockAraucarioxylonSlabPetrifiedBricks.block);
		OreDictionary.registerOre("stairStone", BlockAraucarioxylonStairsPetrifiedBricks.block);
		OreDictionary.registerOre("wallStone", BlockAraucarioxylonWallPetrifiedBricks.block);
		OreDictionary.registerOre("oreAmber", BlockBalticAmberOre.block);
		OreDictionary.registerOre("oreAmber", BlockDominicanAmberOre.block);
		OreDictionary.registerOre("gemAmber", ItemBalticAmberChunk.block);
		OreDictionary.registerOre("gemAmber", ItemDominicanAmberChunk.block);
		OreDictionary.registerOre("blockAmber", BlockBalticAmberBlock.block);
		OreDictionary.registerOre("blockAmber", BlockDominicanAmberBlock.block);
		OreDictionary.registerOre("stairAmber", BlockBalticAmberStairs.block);
		OreDictionary.registerOre("stairAmber", BlockDominicanAmberStairs.block);
		OreDictionary.registerOre("slabAmber", BlockBalticAmberSlab.block);
		OreDictionary.registerOre("slabAmber", BlockDominicanAmberSlab.block);
		OreDictionary.registerOre("wallAmber", BlockBalticAmberWall.block);
		OreDictionary.registerOre("wallAmber", BlockDominicanAmberWall.block);
		OreDictionary.registerOre("blockAmber", BlockBalticAmberBricks.block);
		OreDictionary.registerOre("blockAmber", BlockDominicanAmberBricks.block);
		OreDictionary.registerOre("stairAmber", BlockBalticAmberBrickStairs.block);
		OreDictionary.registerOre("stairAmber", BlockDominicanAmberBrickStairs.block);
		OreDictionary.registerOre("slabAmber", BlockBalticAmberBrickSlab.block);
		OreDictionary.registerOre("slabAmber", BlockDominicanAmberBrickSlab.block);
		OreDictionary.registerOre("wallAmber", BlockBalticAmberBrickWall.block);
		OreDictionary.registerOre("wallAmber", BlockDominicanAmberBrickWall.block);

		OreDictionary.registerOre("oreZircon", BlockZirconOre.block);
		OreDictionary.registerOre("gemZircon", ItemZircon.block);
		OreDictionary.registerOre("blockZircon", BlockZircon.block);

		OreDictionary.registerOre("leavesPsaronius", BlockPsaroniusLeavesPlaceable.block);
		OreDictionary.registerOre("leavesPsaronius", BlockPsaroniusLeavesSmallPlaceable.block);
		OreDictionary.registerOre("leavesTietea", BlockTieteaLeavesPlaceable.block);
		OreDictionary.registerOre("leavesTietea", BlockTieteaLeavesSmallPlaceable.block);
		OreDictionary.registerOre("leavesArchaeopteris", BlockArchaeopterisLeavesPlaceable.block);
		OreDictionary.registerOre("leavesArchaeopteris", BlockArchaeopterisLeavesSmallPlaceable.block);
		OreDictionary.registerOre("leavesHorsetail", BlockCalamitesLeavesPlaceable.block);
		OreDictionary.registerOre("leavesHorsetail", BlockEquisitesShootPlaceable.block);
		OreDictionary.registerOre("stemHorsetail", ItemWaterHorsetailItem.block);
		OreDictionary.registerOre("leavesHorsetail", BlockWoodHorsetail.block);
		OreDictionary.registerOre("leavesHorsetail", BlockFieldHorsetail.block);
		OreDictionary.registerOre("stemHorsetail", BlockCalamitesBranch.block);
		OreDictionary.registerOre("stemHorsetail", BlockGiantHorsetail.block);
		OreDictionary.registerOre("stemHorsetail", ItemNeocalamitesItem.block);
		OreDictionary.registerOre("stemHorsetail", BlockPseudobornia.block);
		
		//OreDictionary.registerOre("leavesHay", new ItemStack(Blocks.REEDS, 1));
		OreDictionary.registerOre("leavesHay", new ItemStack(Blocks.TALLGRASS, 1, 1));
		OreDictionary.registerOre("leavesHay", new ItemStack(Blocks.DOUBLE_PLANT, 1, 2));
		OreDictionary.registerOre("leavesHay", ItemPrimevalGrassItem.block);
		
		OreDictionary.registerOre("slabWood", BlockWoodenSlab.block);
		OreDictionary.registerOre("slabWood", BlockGlossopterisSlab.block);
		OreDictionary.registerOre("slabWood", BlockGangamopterisSlab.block);
		OreDictionary.registerOre("slabWood", BlockLiriodendronSlab.block);
		OreDictionary.registerOre("slabWood", BlockMagnoliaSlab.block);
		OreDictionary.registerOre("slabWood", BlockRedwoodSlab.block);
		OreDictionary.registerOre("slabWood", BlockWollemiSlab.block);
		OreDictionary.registerOre("slabWood", BlockBothrodendronSlab.block);
		OreDictionary.registerOre("slabWood", BlockDiaphorodendronSlab.block);
		OreDictionary.registerOre("slabWood", BlockGinkgoSlab.block);
		OreDictionary.registerOre("slabWood", BlockAgathisSlab.block);
		OreDictionary.registerOre("slabWood", BlockMonkeyPuzzleSlab.block);
		OreDictionary.registerOre("slabWood", BlockSigillariaSlab.block);
		OreDictionary.registerOre("slabWood", BlockAraucarioxylonSlab.block);
		OreDictionary.registerOre("slabWood", BlockSciadopitysSlab.block);
		OreDictionary.registerOre("slabWood", BlockCordaitesSlab.block);
		OreDictionary.registerOre("slabWood", BlockPodozamitesSlab.block);
		OreDictionary.registerOre("slabWood", BlockWalchiaSlab.block);
		OreDictionary.registerOre("slabWood", BlockHymenaeaSlab.block);
		OreDictionary.registerOre("slabWood", BlockKomlopterisSlab.block);
		OreDictionary.registerOre("slabWood", BlockBristleconeSlab.block);
		OreDictionary.registerOre("slabWood", BlockHironoiaSlab.block);
		OreDictionary.registerOre("slabWood", BlockArchaeopterisSlab.block);
		OreDictionary.registerOre("slabWood", BlockDawnRedwoodSlab.block);
		OreDictionary.registerOre("slabWood", BlockMapleSlab.block);
		OreDictionary.registerOre("slabWood", BlockArtocarpusSlab.block);
		OreDictionary.registerOre("slabWood", BlockNothofagusSlab.block);
		OreDictionary.registerOre("slabWood", BlockYewSlab.block);
		OreDictionary.registerOre("slabWood", BlockBrachyphyllumSlab.block);
		OreDictionary.registerOre("slabWood", BlockTaxodiumSlab.block);
	
		OreDictionary.registerOre("fenceWood", BlockWoodenFence.block);
		OreDictionary.registerOre("fenceWood", BlockGlossopterisFence.block);
		OreDictionary.registerOre("fenceWood", BlockGangamopterisFence.block);
		OreDictionary.registerOre("fenceWood", BlockLiriodendronFence.block);
		OreDictionary.registerOre("fenceWood", BlockMagnoliaFence.block);
		OreDictionary.registerOre("fenceWood", BlockRedwoodFence.block);
		OreDictionary.registerOre("fenceWood", BlockWollemiFence.block);
		OreDictionary.registerOre("fenceWood", BlockBothrodendronFence.block);
		OreDictionary.registerOre("fenceWood", BlockDiaphorodendronFence.block);
		OreDictionary.registerOre("fenceWood", BlockGinkgoFence.block);
		OreDictionary.registerOre("fenceWood", BlockAgathisFence.block);
		OreDictionary.registerOre("fenceWood", BlockAraucariaFence.block);
		OreDictionary.registerOre("fenceWood", BlockSigillariaFence.block);
		OreDictionary.registerOre("fenceWood", BlockAraucarioxylonFence.block);
		OreDictionary.registerOre("fenceWood", BlockSciadopitysFence.block);
		OreDictionary.registerOre("fenceWood", BlockCordaitesFence.block);
		OreDictionary.registerOre("fenceWood", BlockPodozamitesFence.block);
		OreDictionary.registerOre("fenceWood", BlockWalchiaFence.block);
		OreDictionary.registerOre("fenceWood", BlockHymenaeaFence.block);
		OreDictionary.registerOre("fenceWood", BlockKomlopterisFence.block);
		OreDictionary.registerOre("fenceWood", BlockBristleconeFence.block);
		OreDictionary.registerOre("fenceWood", BlockHironoiaFence.block);
		OreDictionary.registerOre("fenceWood", BlockArchaeopterisFence.block);
		OreDictionary.registerOre("fenceWood", BlockDawnRedwoodFence.block);
		OreDictionary.registerOre("fenceWood", BlockMapleFence.block);
		OreDictionary.registerOre("fenceWood", BlockArtocarpusFence.block);
		OreDictionary.registerOre("fenceWood", BlockNothofagusFence.block);
		OreDictionary.registerOre("fenceWood", BlockYewFence.block);
		OreDictionary.registerOre("fenceWood", BlockBrachyphyllumFence.block);
		OreDictionary.registerOre("fenceWood", BlockTaxodiumFence.block);

		//Thin fences:
		OreDictionary.registerOre("fenceWood", BlockSigillariaThinFence.block);
		OreDictionary.registerOre("fenceWood", BlockArchaeopterisFenceThin.block);
		OreDictionary.registerOre("fenceWood", BlockPsaroniusFence.block);
		OreDictionary.registerOre("fenceWood", BlockTieteaFence.block);
		OreDictionary.registerOre("fenceWood", BlockEmplectopterisFence.block);
		OreDictionary.registerOre("fenceWood", BlockGigantopteridFence.block);
		OreDictionary.registerOre("fenceWood", BlockLeptocycasFence.block);
		OreDictionary.registerOre("fenceWood", BlockDicroidiumFence.block);
		OreDictionary.registerOre("fenceWood", BlockMedullosalesFence.block);
		OreDictionary.registerOre("fenceWood", BlockPleuromeiaFence.block);
		OreDictionary.registerOre("fenceWood", BlockUtrechtiaFence.block);
		OreDictionary.registerOre("fenceWood", BlockPterophyllumFence.block);

		OreDictionary.registerOre("fenceGateWood", BlockWoodenFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockGlossopterisFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockGangamopterisFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockLiriodendronFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockMagnoliaFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockRedwoodFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockWollemiFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockBothrodendronFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockDiaphorodendronFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockGinkgoFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockAgathisFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockAraucariaFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockSigillariaFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockAraucarioxylonFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockSciadopitysFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockCordaitesFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockPodozamitesFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockWalchiaFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockHymenaeaFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockKomlopterisFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockBristleconeFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockHironoiaFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockArchaeopterisFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockDawnRedwoodFence.block);
		OreDictionary.registerOre("fenceGateWood", BlockMapleFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockArtocarpusFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockNothofagusFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockYewFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockBrachyphyllumFenceGate.block);
		OreDictionary.registerOre("fenceGateWood", BlockTaxodiumFenceGate.block);

		//Thin fences:
		OreDictionary.registerOre("fenceWood", BlockSigillariaThinFenceGate.block);
		OreDictionary.registerOre("fenceWood", BlockArchaeopterisFenceGateThin.block);
		OreDictionary.registerOre("fenceWood", BlockPsaroniusFenceGate.block);
		OreDictionary.registerOre("fenceWood", BlockTieteaFenceGate.block);
		OreDictionary.registerOre("fenceWood", BlockEmplectopterisFenceGate.block);
		OreDictionary.registerOre("fenceWood", BlockGigantopteridFenceGate.block);
		OreDictionary.registerOre("fenceWood", BlockLeptocycasFenceGate.block);
		OreDictionary.registerOre("fenceWood", BlockDicroidiumFenceGate.block);
		OreDictionary.registerOre("fenceWood", BlockMedullosalesFenceGate.block);
		OreDictionary.registerOre("fenceWood", BlockPleuromeiaFenceGate.block);
		OreDictionary.registerOre("fenceWood", BlockUtrechtiaFenceGate.block);
		OreDictionary.registerOre("fenceWood", BlockPterophyllumFenceGate.block);

		OreDictionary.registerOre("doorWood", ItemLepidodendronDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemGlossopterisDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemGangamopterisDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemKomlopterisDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemDiaphorodendronDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemYewDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemAgathisDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemAlpiaDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemAraucariaDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemAraucarioxylonDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemCordaitesDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemDawnRedwoodDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemPodozamitesDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemRedwoodDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemSciadopitysDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemWalchiaDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemWollemiDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemBristleconeDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemGinkgoDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemBrachyphyllumDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemMagnoliaDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemBothrodendronDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemArtocarpusDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemHironoiaDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemLepidodendronDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemMapleDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemNothofagusDoorItem.block);
		OreDictionary.registerOre("doorWood", ItemTaxodiumDoorItem.block);

		//Compat for Calamites:
		OreDictionary.registerOre("listAllfiber", ItemCalamitesDoorItem.block);
		OreDictionary.registerOre("listAllfiber", BlockCalamitesLog.block);
		OreDictionary.registerOre("listAllfiber", BlockCalamitesSlab.block);
		OreDictionary.registerOre("listAllfiber", BlockCalamitesPlanks.block);
		OreDictionary.registerOre("listAllfiber", BlockCalamitesStairs.block);
		OreDictionary.registerOre("listAllfiber", BlockCalamitesTrapdoor.block);
		
		OreDictionary.registerOre("logWood", BlockWoodenLog.block);
		OreDictionary.registerOre("logWood", BlockGlossopterisLog.block);
		OreDictionary.registerOre("logWood", BlockGangamopterisLog.block);
		OreDictionary.registerOre("logWood", BlockLiriodendronLog.block);
		OreDictionary.registerOre("logWood", BlockMagnoliaLog.block);
		OreDictionary.registerOre("logWood", BlockRedwoodLog.block);
		OreDictionary.registerOre("logWood", BlockWollemiLog.block);
		OreDictionary.registerOre("logWood", BlockBothrodendronLog.block);
		OreDictionary.registerOre("logWood", BlockDiaphorodendronLog.block);
		OreDictionary.registerOre("logWood", BlockGinkgoLog.block);
		OreDictionary.registerOre("logWood", BlockAgathisLog.block);
		OreDictionary.registerOre("logWood", BlockMonkeypuzzleLog.block);
		OreDictionary.registerOre("logWood", BlockColumnarisLog.block);
		OreDictionary.registerOre("logWood", BlockBunyaLog.block);
		OreDictionary.registerOre("logWood", BlockTempskyaLog.block);
		OreDictionary.registerOre("logWood", BlockWilliamsoniaLog.block);
		OreDictionary.registerOre("logWood", BlockCycadeoideaLog.block);
		OreDictionary.registerOre("logWood", BlockZamitesLog.block);
		OreDictionary.registerOre("logWood", BlockSahnioxylonLog.block);
		OreDictionary.registerOre("logWood", BlockSigillariaLog.block);
		OreDictionary.registerOre("logWood", BlockSigillariaStem.block);
		OreDictionary.registerOre("logWood", BlockAraucarioxylonLog.block);
		OreDictionary.registerOre("logWood", BlockSciadopitysLog.block);
		OreDictionary.registerOre("logWood", BlockPleuromeiaStem.block);
		OreDictionary.registerOre("logWood", BlockBjuviaLog.block);
		OreDictionary.registerOre("logWood", BlockEncblueLog.block);
		OreDictionary.registerOre("logWood", BlockCycasLog.block);
		OreDictionary.registerOre("logWood", BlockLeptocycasLog.block);
		OreDictionary.registerOre("logWood", BlockDioonLog.block);
		OreDictionary.registerOre("logWood", BlockSpinyCycadLog.block);
		OreDictionary.registerOre("logWood", BlockPsaroniusLog.block);
		OreDictionary.registerOre("logWood", BlockTieteaLog.block);
		OreDictionary.registerOre("logWood", BlockDicksoniaLog.block);
		OreDictionary.registerOre("logWood", BlockZygopteridaceaeLog.block);
		OreDictionary.registerOre("logWood", BlockCordaitesLog.block);
		OreDictionary.registerOre("logWood", BlockWattiezaLog.block);
		OreDictionary.registerOre("logWood", BlockPodozamitesLog.block);
		OreDictionary.registerOre("logWood", BlockValmeyerodendronLog.block);
		OreDictionary.registerOre("logWood", BlockWalchiaLog.block);
		OreDictionary.registerOre("logWood", BlockHymenaeaLog.block);
		OreDictionary.registerOre("logWood", BlockKomlopterisLog.block);
		OreDictionary.registerOre("logWood", BlockMedullosalesLog.block);
		OreDictionary.registerOre("logWood", BlockGigantopteridLog.block);
		OreDictionary.registerOre("logWood", BlockEmplectopterisLog.block);
		OreDictionary.registerOre("logWood", BlockBristleconeLog.block);
		OreDictionary.registerOre("logWood", BlockHironoiaLog.block);
		OreDictionary.registerOre("logWood", BlockArchaeopterisLog.block);
		OreDictionary.registerOre("logWood", BlockNilssoniocladusStem.block);
		OreDictionary.registerOre("logWood", BlockNilssoniocladusStemNE.block);
		OreDictionary.registerOre("logWood", BlockNilssoniaLog.block);
		OreDictionary.registerOre("logWood", BlockDawnRedwoodLog.block);
		OreDictionary.registerOre("logWood", BlockAlethopterisLog.block);
		OreDictionary.registerOre("logWood", BlockMapleLog.block);
		OreDictionary.registerOre("logWood", BlockArtocarpusLog.block);
		OreDictionary.registerOre("logWood", BlockOmphalophloiosBase.block);
		OreDictionary.registerOre("logWood", BlockNothofagusLog.block);
		OreDictionary.registerOre("logWood", BlockYewLog.block);
		OreDictionary.registerOre("logWood", BlockBrachyphyllumLog.block);
		OreDictionary.registerOre("logWood", BlockAnkyropterisStem.block);
		OreDictionary.registerOre("logWood", BlockAnkyropterisStemNE.block);
		OreDictionary.registerOre("logWood", BlockOdontopterisStem.block);
		OreDictionary.registerOre("logWood", BlockOdontopterisStemNE.block);
		OreDictionary.registerOre("logWood", BlockNoeggerathialesLog.block);
		OreDictionary.registerOre("logWood", BlockBanksiaLog1.block);
		OreDictionary.registerOre("logWood", BlockBanksiaLog2.block);
		OreDictionary.registerOre("logWood", BlockTaxodiumLog.block);

		OreDictionary.registerOre("plankWood", BlockWoodenPlanks.block);
		OreDictionary.registerOre("plankWood", BlockGlossopterisPlanks.block);
		OreDictionary.registerOre("plankWood", BlockGangamopterisPlanks.block);
		OreDictionary.registerOre("plankWood", BlockLiriodendronPlanks.block);
		OreDictionary.registerOre("plankWood", BlockMagnoliaPlanks.block);
		OreDictionary.registerOre("plankWood", BlockRedwoodPlanks.block);
		OreDictionary.registerOre("plankWood", BlockWollemiPlanks.block);
		OreDictionary.registerOre("plankWood", BlockBothrodendronPlanks.block);
		OreDictionary.registerOre("plankWood", BlockDiaphorodendronPlanks.block);
		OreDictionary.registerOre("plankWood", BlockGinkgoPlanks.block);
		OreDictionary.registerOre("plankWood", BlockAgathisPlanks.block);
		OreDictionary.registerOre("plankWood", BlockMonkeyPuzzlePlanks.block);
		OreDictionary.registerOre("plankWood", BlockSigillariaPlanks.block);
		OreDictionary.registerOre("plankWood", BlockAraucarioxylonPlanks.block);
		OreDictionary.registerOre("plankWood", BlockSciadopitysPlanks.block);
		OreDictionary.registerOre("plankWood", BlockCordaitesPlanks.block);
		OreDictionary.registerOre("plankWood", BlockPodozamitesPlanks.block);
		OreDictionary.registerOre("plankWood", BlockWalchiaPlanks.block);
		OreDictionary.registerOre("plankWood", BlockHymenaeaPlanks.block);
		OreDictionary.registerOre("plankWood", BlockKomlopterisPlanks.block);
		OreDictionary.registerOre("plankWood", BlockBristleconePlanks.block);
		OreDictionary.registerOre("plankWood", BlockHironoiaPlanks.block);
		OreDictionary.registerOre("plankWood", BlockArchaeopterisPlanks.block);
		OreDictionary.registerOre("plankWood", BlockDawnRedwoodPlanks.block);
		OreDictionary.registerOre("plankWood", BlockMaplePlanks.block);
		OreDictionary.registerOre("plankWood", BlockArtocarpusPlanks.block);
		OreDictionary.registerOre("plankWood", BlockNothofagusPlanks.block);
		OreDictionary.registerOre("plankWood", BlockYewPlanks.block);
		OreDictionary.registerOre("plankWood", BlockBrachyphyllumPlanks.block);
		OreDictionary.registerOre("plankWood", BlockTaxodiumPlanks.block);

		OreDictionary.registerOre("treeSapling", BlockSapling.block);
		OreDictionary.registerOre("treeSapling", BlockGlossopterisSapling.block);
		OreDictionary.registerOre("treeSapling", BlockGangamopterisSapling.block);
		OreDictionary.registerOre("treeSapling", BlockLiriodendronSapling.block);
		OreDictionary.registerOre("treeSapling", BlockMagnoliaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockRedwoodSapling.block);
		OreDictionary.registerOre("treeSapling", BlockWollemiSapling.block);
		OreDictionary.registerOre("treeSapling", BlockBothrodendronSapling.block);
		OreDictionary.registerOre("treeSapling", BlockDiaphorodendronSapling.block);
		OreDictionary.registerOre("treeSapling", BlockGinkgoSapling.block);
		OreDictionary.registerOre("treeSapling", BlockAgathisSapling.block);
		OreDictionary.registerOre("treeSapling", BlockMonkeypuzzleSapling.block);
		OreDictionary.registerOre("treeSapling", BlockColumnarisSapling.block);
		OreDictionary.registerOre("treeSapling", BlockBunyaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockTempskyaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockWilliamsoniaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockCycadeoideaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockZamitesSapling.block);
		OreDictionary.registerOre("treeSapling", BlockSahnioxylonSapling.block);
		OreDictionary.registerOre("treeSapling", BlockSigillariaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockCalamitesSapling.block);
		OreDictionary.registerOre("treeSapling", BlockAraucarioxylonSapling.block);
		OreDictionary.registerOre("treeSapling", BlockSciadopitysSapling.block);
		OreDictionary.registerOre("treeSapling", BlockPleuromeiaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockBjuviaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockEncblueSapling.block);
		OreDictionary.registerOre("treeSapling", BlockCycasSapling.block);
		OreDictionary.registerOre("treeSapling", BlockLeptocycasSapling.block);
		OreDictionary.registerOre("treeSapling", BlockDioonSapling.block);
		OreDictionary.registerOre("treeSapling", BlockSpinyCycadSapling.block);
		OreDictionary.registerOre("treeSapling", BlockPsaroniusSapling.block);
		OreDictionary.registerOre("treeSapling", BlockTieteaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockDicksoniaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockZygopteridaceaeSapling.block);
		OreDictionary.registerOre("treeSapling", BlockCordaitesSapling.block);
		OreDictionary.registerOre("treeSapling", BlockWattiezaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockPodozamitesSapling.block);
		OreDictionary.registerOre("treeSapling", BlockWalchiaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockProteaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockProteaSapling1.block);
		OreDictionary.registerOre("treeSapling", BlockHymenaeaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockKomlopterisSapling.block);
		OreDictionary.registerOre("treeSapling", BlockMedullosalesSapling.block);
		OreDictionary.registerOre("treeSapling", BlockGigantopteridSapling.block);
		OreDictionary.registerOre("treeSapling", BlockEmplectopterisSapling.block);
		OreDictionary.registerOre("treeSapling", BlockBristleconeSapling.block);
		OreDictionary.registerOre("treeSapling", BlockHironoiaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockArchaeopterisSapling.block);
		OreDictionary.registerOre("treeSapling", BlockNilssoniocladusSapling.block);
		OreDictionary.registerOre("treeSapling", BlockNilssoniaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockDawnRedwoodSapling.block);
		OreDictionary.registerOre("treeSapling", BlockAlethopterisSapling.block);
		OreDictionary.registerOre("treeSapling", BlockMapleSapling.block);
		OreDictionary.registerOre("treeSapling", BlockArtocarpusSapling.block);
		OreDictionary.registerOre("treeSapling", BlockEquisitesSapling.block);
		OreDictionary.registerOre("treeSapling", BlockOmphalophloiosSapling.block);
		OreDictionary.registerOre("treeSapling", BlockNothofagusSapling.block);
		OreDictionary.registerOre("treeSapling", BlockYewSapling.block);
		OreDictionary.registerOre("treeSapling", BlockBrachyphyllumSapling.block);
		OreDictionary.registerOre("treeSapling", BlockAnkyropterisSapling.block);
		OreDictionary.registerOre("treeSapling", BlockOdontopterisSapling.block);
		OreDictionary.registerOre("treeSapling", BlockPalaeostachyaSapling.block);
		OreDictionary.registerOre("treeSapling", BlockSchizoneuraSapling.block);
		OreDictionary.registerOre("treeSapling", BlockNoeggerathialesSapling.block);
		OreDictionary.registerOre("treeSapling", BlockBanksia1Sapling.block);
		OreDictionary.registerOre("treeSapling", BlockBanksia2Sapling.block);
		OreDictionary.registerOre("treeSapling", BlockProtolepidodendropsisSapling.block);
		OreDictionary.registerOre("treeSapling", BlockTaxodiumSapling.block);

		OreDictionary.registerOre("stairWood", BlockWoodenStairs.block);
		OreDictionary.registerOre("stairWood", BlockGlossopterisStairs.block);
		OreDictionary.registerOre("stairWood", BlockGangamopterisStairs.block);
		OreDictionary.registerOre("stairWood", BlockLiriodendronStairs.block);
		OreDictionary.registerOre("stairWood", BlockMagnoliaStairs.block);
		OreDictionary.registerOre("stairWood", BlockRedwoodStairs.block);
		OreDictionary.registerOre("stairWood", BlockWollemiStairs.block);
		OreDictionary.registerOre("stairWood", BlockBothrodendronStairs.block);
		OreDictionary.registerOre("stairWood", BlockDiaphorodendronStairs.block);
		OreDictionary.registerOre("stairWood", BlockGinkgoStairs.block);
		OreDictionary.registerOre("stairWood", BlockAgathisStairs.block);
		OreDictionary.registerOre("stairWood", BlockMonkeyPuzzleStairs.block);
		OreDictionary.registerOre("stairWood", BlockSigillariaStairs.block);
		OreDictionary.registerOre("stairWood", BlockAraucarioxylonStairs.block);
		OreDictionary.registerOre("stairWood", BlockSciadopitysStairs.block);
		OreDictionary.registerOre("stairWood", BlockCordaitesStairs.block);
		OreDictionary.registerOre("stairWood", BlockPodozamitesStairs.block);
		OreDictionary.registerOre("stairWood", BlockWalchiaStairs.block);
		OreDictionary.registerOre("stairWood", BlockHymenaeaStairs.block);
		OreDictionary.registerOre("stairWood", BlockKomlopterisStairs.block);
		OreDictionary.registerOre("stairWood", BlockBristleconeStairs.block);
		OreDictionary.registerOre("stairWood", BlockHironoiaStairs.block);
		OreDictionary.registerOre("stairWood", BlockArchaeopterisStairs.block);
		OreDictionary.registerOre("stairWood", BlockDawnRedwoodStairs.block);
		OreDictionary.registerOre("stairWood", BlockMapleStairs.block);
		OreDictionary.registerOre("stairWood", BlockArtocarpusStairs.block);
		OreDictionary.registerOre("stairWood", BlockNothofagusStairs.block);
		OreDictionary.registerOre("stairWood", BlockYewStairs.block);
		OreDictionary.registerOre("stairWood", BlockBrachyphyllumStairs.block);
		OreDictionary.registerOre("stairWood", BlockTaxodiumStairs.block);

		OreDictionary.registerOre("treeLeaves", BlockTreeLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockGlossopterisTreeLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockGangamopterisLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockLiriodendronLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockMagnoliaLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockRedwoodLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockWollemiLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockBothrodendronLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockDiaphorodendronLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockGinkgoLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockAgathisLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockMonkeypuzzleLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockColumnarisLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockBunyaLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockTempskyaLeavesPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockWilliamsoniaShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockWilliamsoniaLeavesPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockCycadeoideaLeavesPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockZamitesShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockZamitesLeavesPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockSahnioxylonShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockSahnioxylonFlowerPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockSigillariaShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockCalamitesShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockCalamitesLeavesPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockAraucarioxylonLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockSciadopitysLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockPleuromeiaShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockEncblueShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockCycasShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockBjuviaShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockLeptocycasShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockDioonShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockSpinyCycadShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockPsaroniusLeavesPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockPsaroniusLeavesSmallPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockTieteaLeavesPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockTieteaLeavesSmallPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockDicksoniaLeavesPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockZygopteridaceaeLeavesPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockCordaitesLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockWattiezaShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockPodozamitesLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockProteaLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockProteaLeaves1.block);
		OreDictionary.registerOre("treeLeaves", BlockWalchiaLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockHymenaeaLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockKomlopterisLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockMedullosalesShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockGigantopteridShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockEmplectopterisShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockBristleconeLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockHironoiaLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockArchaeopterisLeavesPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockArchaeopterisLeavesSmallPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockNilssoniocladusShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockNilssoniaShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockDawnRedwoodLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockAlethopterisLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockMapleLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockArtocarpusLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockOmphalophloiosPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockNothofagusLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockYewLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockBrachyphyllumLeaves.block);
		OreDictionary.registerOre("treeLeaves", BlockOdontopterisShootPlaceable.block);
		OreDictionary.registerOre("treeLeaves", BlockTaxodiumLeaves.block);

		OreDictionary.registerOre("trapdoorWood", BlockWoodenTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockGlossopterisTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockGangamopterisTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockLiriodendronTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockMagnoliaTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockRedwoodTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockWollemiTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockBothrodendronTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockDiaphorodendronTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockGinkgoTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockAgathisTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockMonkeyPuzzleTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockSigillariaTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockAraucarioxylonTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockSciadopitysTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockCordaitesTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockPodozamitesTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockWalchiaTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockHymenaeaTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockKomlopterisTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockBristleconeTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockHironoiaTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockArchaeopterisTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockDawnRedwoodTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockMapleTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockArtocarpusTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockNothofagusTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockYewTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockBrachyphyllumTrapdoor.block);
		OreDictionary.registerOre("trapdoorWood", BlockTaxodiumTrapdoor.block);

		OreDictionary.registerOre("plantPrehistoric", BlockSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockGlossopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockGangamopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockLiriodendronSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockMagnoliaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockRedwoodSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWollemiSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBothrodendronSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockDiaphorodendronSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockGinkgoSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockAgathisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockMonkeypuzzleSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockColumnarisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBunyaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTempskyaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWilliamsoniaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCycadeoideaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockZamitesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSahnioxylonSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSigillariaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCalamitesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockAraucarioxylonSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSciadopitysSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPleuromeiaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBjuviaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockEncblueSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCycasSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockLeptocycasSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockDioonSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSpinyCycadSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPsaroniusSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTieteaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockDicksoniaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockZygopteridaceaeSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCordaitesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWattiezaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPodozamitesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockValmeyerodendronSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockProteaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockProteaSapling1.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWalchiaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockHymenaeaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockKomlopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockMedullosalesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockGigantopteridSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockEmplectopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBristleconeSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockHironoiaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockArchaeopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNilssoniocladusSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNilssoniaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockDawnRedwoodSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockAlethopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockMapleSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockArtocarpusSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockEquisitesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockOmphalophloiosSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNothofagusSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockYewSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBrachyphyllumSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPalaeostachyaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSchizoneuraSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNoeggerathialesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBanksia1Sapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBanksia2Sapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockProtolepidodendropsisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTaxodiumSapling.block);

		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedGlossopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedGangamopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedLiriodendronSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedMagnoliaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedRedwoodSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedWollemiSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedBothrodendronSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedDiaphorodendronSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedGinkgoSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedAgathisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedMonkeypuzzleSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedColumnarisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedBunyaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedTempskyaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedWilliamsoniaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedCycadeoideaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedZamitesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedSahnioxylonSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedSigillariaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedCalamitesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedAraucarioxylonSaping.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedSciadopitysSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPleuromeiaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedBjuviaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedEncblueSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedCycasSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedLeptocycasSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedDioonSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedSpinyCycadSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedAntarcticycas.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPsaroniusSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedTieteaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedDicksoniaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedZygopteridaceaeSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedCaytoniales.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedCaytoniales2.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedCordaitesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedSphenophyllales.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedGiantHorsetail.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedBolbitis.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedCooksonia.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPsilophyton.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedAsteroxylon.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedNematophyta.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPrototaxites.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedWattiezaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPodozamitesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedValmeyerodendronSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedProteaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedProteaSapling1.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedWalchiaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedHymenaeaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedKomlopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedMedullosalesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedGigantopteridSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedEmplectopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedBristleconeSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedHironoiaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedArchaeopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedNilssoniocladusSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedNilssoniaSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedDawnRedwoodSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedAlethopterisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedMapleSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedArtocarpusSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedEquisetitesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedOmphalophloiosSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedNothofagusSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedYewSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedBrachyphyllumSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedAnkyropteris.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedOdontopteris.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPalaeostachya.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedSchizoneura.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedNoeggerathialesSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedBanksia1Sapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedBanksia2Sapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedProtolepidodendropsisSapling.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedTaxodiumSapling.block);
		
		OreDictionary.registerOre("plantPrehistoric", BlockTreeLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockGlossopterisTreeLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockGangamopterisLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockLiriodendronLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockMagnoliaLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockRedwoodLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWollemiLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBothrodendronLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockDiaphorodendronLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockGinkgoLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockAgathisLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockMonkeypuzzleLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockColumnarisLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBunyaLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTempskyaLeavesPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWilliamsoniaShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWilliamsoniaLeavesPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCycadeoideaLeavesPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockZamitesShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockZamitesLeavesPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSahnioxylonShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSahnioxylonFlowerPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSigillariaShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCalamitesShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCalamitesLeavesPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCalamitesBranch.block);
		OreDictionary.registerOre("plantPrehistoric", BlockAraucarioxylonLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSciadopitysLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPleuromeiaShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockEncblueShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCycasShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBjuviaShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockLeptocycasShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockDioonShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSpinyCycadShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPsaroniusLeavesPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPsaroniusLeavesSmallPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTieteaLeavesPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTieteaLeavesSmallPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockDicksoniaLeavesPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockZygopteridaceaeLeavesPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCordaitesLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWattiezaShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPodozamitesLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockProteaLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockProteaLeaves1.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWalchiaLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockHymenaeaLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockKomlopterisLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockMedullosalesShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockGigantopteridShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockEmplectopterisShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBristleconeLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockHironoiaLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockArchaeopterisLeavesPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockArchaeopterisLeavesSmallPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNilssoniocladusShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNilssoniaShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockDawnRedwoodLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockAlethopterisLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockMapleLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockArtocarpusLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockEquisitesStem.block);
		OreDictionary.registerOre("plantPrehistoric", BlockEquisitesShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockOmphalophloiosPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNothofagusLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockYewLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBrachyphyllumLeaves.block);
		OreDictionary.registerOre("plantPrehistoric", BlockOdontopterisShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNoeggerathialesShootPlaceable.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTaxodiumLeaves.block);
		
		OreDictionary.registerOre("plantPrehistoric", BlockAntarcticycas.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCaytoniales.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCaytoniales2.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSphenophyllales.block);
		OreDictionary.registerOre("plantPrehistoric", BlockGiantHorsetail.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBolbitis.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCooksonia.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPsilophyton.block);
		OreDictionary.registerOre("plantPrehistoric", BlockAsteroxylon.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPrototaxites.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNematophyta.block);
		OreDictionary.registerOre("plantPrehistoric", BlockLepidopteris.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedLepidopteris.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBaikalophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedBaikalophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", BlockBaiera.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedBaiera.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPolyspermophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPolyspermophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNystroemia.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedNystroemia.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTrichopitys.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedTrichopitys.block);
		OreDictionary.registerOre("plantPrehistoric", ItemIsoetesItem.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedIsoetes.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSphenophyllales1.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedSphenophyllales1.block);
		OreDictionary.registerOre("plantPrehistoric", BlockClaytosmunda.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedClaytosmunda.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWachtleria.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedWachtleria.block);
		OreDictionary.registerOre("plantPrehistoric", BlockScytophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedScytophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", BlockAethophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedAethophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", ItemBaragwanathiaItem.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedBaragwanathia.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPrehistoricGroundCoverPlants.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPrehistoricGroundCoverPlantsLush.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPrehistoricGroundCoverPlantsSandy.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPrehistoricGroundCoverPlantsPangaean.block);
		OreDictionary.registerOre("plantPrehistoric", BlockAncientMoss.block);
		OreDictionary.registerOre("plantPrehistoric", BlockMatonia.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedSmallMatonia.block);
		OreDictionary.registerOre("plantPrehistoric", BlockMatoniaLarge.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedLargeMatonia.block);
		OreDictionary.registerOre("plantPrehistoric", BlockDollyphyton.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedDollyphyton.block);
		OreDictionary.registerOre("plantPrehistoric", BlockEdwardsiphyton.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedEdwardsiphyton.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedNeuropteridium.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNeuropteridium.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedStauropteris.block);
		OreDictionary.registerOre("plantPrehistoric", BlockStauropteris.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedOsmunda.block);
		OreDictionary.registerOre("plantPrehistoric", BlockOsmunda.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedRhacophyton.block);
		OreDictionary.registerOre("plantPrehistoric", BlockRhacophyton.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCinnamonFern.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedCinnamonFern.block);
		OreDictionary.registerOre("plantPrehistoric", BlockMarattia.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedMarattia.block);
		OreDictionary.registerOre("plantPrehistoric", BlockGuangdedendron.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedGuangdedendron.block);
		OreDictionary.registerOre("plantPrehistoric", BlockEdwardsiphyton.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedEdwardsiphyton.block);
		OreDictionary.registerOre("plantPrehistoric", BlockEphedra.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedEphedra.block);
		OreDictionary.registerOre("plantPrehistoric", ItemArchaefructusItem.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedArchaefructus.block);
		OreDictionary.registerOre("plantPrehistoric", ItemWaterHorsetailItem.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedWaterHorsetail.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWoodHorsetail.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedWoodHorsetail.block);
		OreDictionary.registerOre("plantPrehistoric", BlockFieldHorsetail.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedFieldHorsetail.block);
		OreDictionary.registerOre("plantPrehistoric", BlockUmaltolepis.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedUmaltolepis.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSphenopteris.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedSphenopteris.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTyrmia.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedTyrmia.block);
		OreDictionary.registerOre("plantPrehistoric", BlockWielandiella.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedWielandiella.block);
		OreDictionary.registerOre("plantPrehistoric", ItemNathorstianaItem.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedNathorstiana.block);
		OreDictionary.registerOre("plantPrehistoric", ItemNeocalamitesItem.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedNeocalamites.block);
		OreDictionary.registerOre("plantPrehistoric", BlockSelaginella.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedSelaginella.block);
		OreDictionary.registerOre("plantPrehistoric", ItemCobbaniaItem.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedCobbania.block);
		OreDictionary.registerOre("plantPrehistoric", BlockElkinsia.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedElkinsia.block);
		OreDictionary.registerOre("plantPrehistoric", BlockCallistophytales.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedCallistophytales.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPalaeognetaleana.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPalaeognetaleana.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPseudobornia.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPseudobornia.block);
		OreDictionary.registerOre("plantPrehistoric", BlockAdoketophyton.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedAdoketophyton.block);
		OreDictionary.registerOre("plantPrehistoric", BlockZosterophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedZosterophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", BlockFoozia.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedFoozia.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPertica.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPertica.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTetraxylopteris.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedTetraxylopteris.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTmesipteris.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedTmesipteris.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPrimevalGrassItem.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPrimaevalGrass.block);
		OreDictionary.registerOre("plantPrehistoric", BlockFernEpiphyte.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedFernEpiphyte.block);
		OreDictionary.registerOre("plantPrehistoric", BlockNystroemia.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedNystroemia.block);
		OreDictionary.registerOre("plantPrehistoric", BlockPolyspermophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedPolyspermophyllum.block);
		OreDictionary.registerOre("plantPrehistoric", BlockTrichopitys.block);
		OreDictionary.registerOre("plantPrehistoric", ItemPetrifiedTrichopitys.block);

		OreDictionary.registerOre("plant", BlockSapling.block);
		OreDictionary.registerOre("plant", BlockGlossopterisSapling.block);
		OreDictionary.registerOre("plant", BlockGangamopterisSapling.block);
		OreDictionary.registerOre("plant", BlockLiriodendronSapling.block);
		OreDictionary.registerOre("plant", BlockMagnoliaSapling.block);
		OreDictionary.registerOre("plant", BlockRedwoodSapling.block);
		OreDictionary.registerOre("plant", BlockWollemiSapling.block);
		OreDictionary.registerOre("plant", BlockBothrodendronSapling.block);
		OreDictionary.registerOre("plant", BlockDiaphorodendronSapling.block);
		OreDictionary.registerOre("plant", BlockGinkgoSapling.block);
		OreDictionary.registerOre("plant", BlockAgathisSapling.block);
		OreDictionary.registerOre("plant", BlockMonkeypuzzleSapling.block);
		OreDictionary.registerOre("plant", BlockColumnarisSapling.block);
		OreDictionary.registerOre("plant", BlockBunyaSapling.block);
		OreDictionary.registerOre("plant", BlockTempskyaSapling.block);
		OreDictionary.registerOre("plant", BlockWilliamsoniaSapling.block);
		OreDictionary.registerOre("plant", BlockCycadeoideaSapling.block);
		OreDictionary.registerOre("plant", BlockZamitesSapling.block);
		OreDictionary.registerOre("plant", BlockSahnioxylonSapling.block);
		OreDictionary.registerOre("plant", BlockSigillariaSapling.block);
		OreDictionary.registerOre("plant", BlockCalamitesSapling.block);
		OreDictionary.registerOre("plant", BlockAraucarioxylonSapling.block);
		OreDictionary.registerOre("plant", BlockSciadopitysSapling.block);
		OreDictionary.registerOre("plant", BlockPleuromeiaSapling.block);
		OreDictionary.registerOre("plant", BlockBjuviaSapling.block);
		OreDictionary.registerOre("plant", BlockEncblueSapling.block);
		OreDictionary.registerOre("plant", BlockCycasSapling.block);
		OreDictionary.registerOre("plant", BlockLeptocycasSapling.block);
		OreDictionary.registerOre("plant", BlockDioonSapling.block);
		OreDictionary.registerOre("plant", BlockSpinyCycadSapling.block);
		OreDictionary.registerOre("plant", BlockPsaroniusSapling.block);
		OreDictionary.registerOre("plant", BlockTieteaSapling.block);
		OreDictionary.registerOre("plant", BlockDicksoniaSapling.block);
		OreDictionary.registerOre("plant", BlockZygopteridaceaeSapling.block);
		OreDictionary.registerOre("plant", BlockCordaitesSapling.block);
		OreDictionary.registerOre("plant", BlockWattiezaSapling.block);
		OreDictionary.registerOre("plant", BlockPodozamitesSapling.block);
		OreDictionary.registerOre("plant", BlockValmeyerodendronSapling.block);
		OreDictionary.registerOre("plant", BlockProteaSapling.block);
		OreDictionary.registerOre("plant", BlockProteaSapling1.block);
		OreDictionary.registerOre("plant", BlockWalchiaSapling.block);
		OreDictionary.registerOre("plant", BlockHymenaeaSapling.block);
		OreDictionary.registerOre("plant", BlockKomlopterisSapling.block);
		OreDictionary.registerOre("plant", BlockMedullosalesSapling.block);
		OreDictionary.registerOre("plant", BlockGigantopteridSapling.block);
		OreDictionary.registerOre("plant", BlockEmplectopterisSapling.block);
		OreDictionary.registerOre("plant", BlockBristleconeSapling.block);
		OreDictionary.registerOre("plant", BlockHironoiaSapling.block);
		OreDictionary.registerOre("plant", BlockArchaeopterisSapling.block);
		OreDictionary.registerOre("plant", BlockNilssoniocladusSapling.block);
		OreDictionary.registerOre("plant", BlockNilssoniaSapling.block);
		OreDictionary.registerOre("plant", BlockDawnRedwoodSapling.block);
		OreDictionary.registerOre("plant", BlockAlethopterisSapling.block);
		OreDictionary.registerOre("plant", BlockMapleSapling.block);
		OreDictionary.registerOre("plant", BlockArtocarpusSapling.block);
		OreDictionary.registerOre("plant", BlockEquisitesSapling.block);
		OreDictionary.registerOre("plant", BlockOmphalophloiosSapling.block);
		OreDictionary.registerOre("plant", BlockNothofagusSapling.block);
		OreDictionary.registerOre("plant", BlockYewSapling.block);
		OreDictionary.registerOre("plant", BlockBrachyphyllumSapling.block);
		OreDictionary.registerOre("plant", BlockPalaeostachyaSapling.block);
		OreDictionary.registerOre("plant", BlockSchizoneuraSapling.block);
		OreDictionary.registerOre("plant", BlockNoeggerathialesSapling.block);
		OreDictionary.registerOre("plant", BlockBanksia1Sapling.block);
		OreDictionary.registerOre("plant", BlockBanksia2Sapling.block);
		OreDictionary.registerOre("plant", BlockProtolepidodendropsisSapling.block);
		OreDictionary.registerOre("plant", BlockTaxodiumSapling.block);

		OreDictionary.registerOre("plant", BlockTreeLeaves.block);
		OreDictionary.registerOre("plant", BlockGlossopterisTreeLeaves.block);
		OreDictionary.registerOre("plant", BlockGangamopterisLeaves.block);
		OreDictionary.registerOre("plant", BlockLiriodendronLeaves.block);
		OreDictionary.registerOre("plant", BlockMagnoliaLeaves.block);
		OreDictionary.registerOre("plant", BlockRedwoodLeaves.block);
		OreDictionary.registerOre("plant", BlockWollemiLeaves.block);
		OreDictionary.registerOre("plant", BlockBothrodendronLeaves.block);
		OreDictionary.registerOre("plant", BlockDiaphorodendronLeaves.block);
		OreDictionary.registerOre("plant", BlockGinkgoLeaves.block);
		OreDictionary.registerOre("plant", BlockAgathisLeaves.block);
		OreDictionary.registerOre("plant", BlockMonkeypuzzleLeaves.block);
		OreDictionary.registerOre("plant", BlockColumnarisLeaves.block);
		OreDictionary.registerOre("plant", BlockBunyaLeaves.block);
		OreDictionary.registerOre("plant", BlockTempskyaLeavesPlaceable.block);
		OreDictionary.registerOre("plant", BlockWilliamsoniaShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockWilliamsoniaLeavesPlaceable.block);
		OreDictionary.registerOre("plant", BlockCycadeoideaLeavesPlaceable.block);
		OreDictionary.registerOre("plant", BlockZamitesShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockZamitesLeavesPlaceable.block);
		OreDictionary.registerOre("plant", BlockSahnioxylonShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockSahnioxylonFlowerPlaceable.block);
		OreDictionary.registerOre("plant", BlockSigillariaShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockCalamitesShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockCalamitesLeavesPlaceable.block);
		OreDictionary.registerOre("plant", BlockCalamitesBranch.block);
		OreDictionary.registerOre("plant", BlockAraucarioxylonLeaves.block);
		OreDictionary.registerOre("plant", BlockSciadopitysLeaves.block);
		OreDictionary.registerOre("plant", BlockPleuromeiaShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockEncblueShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockCycasShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockBjuviaShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockLeptocycasShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockDioonShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockSpinyCycadShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockPsaroniusLeavesPlaceable.block);
		OreDictionary.registerOre("plant", BlockPsaroniusLeavesSmallPlaceable.block);
		OreDictionary.registerOre("plant", BlockTieteaLeavesPlaceable.block);
		OreDictionary.registerOre("plant", BlockTieteaLeavesSmallPlaceable.block);
		OreDictionary.registerOre("plant", BlockDicksoniaLeavesPlaceable.block);
		OreDictionary.registerOre("plant", BlockZygopteridaceaeLeavesPlaceable.block);
		OreDictionary.registerOre("plant", BlockCordaitesLeaves.block);
		OreDictionary.registerOre("plant", BlockWattiezaShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockPodozamitesLeaves.block);
		OreDictionary.registerOre("plant", BlockProteaLeaves.block);
		OreDictionary.registerOre("plant", BlockProteaLeaves1.block);
		OreDictionary.registerOre("plant", BlockWalchiaLeaves.block);
		OreDictionary.registerOre("plant", BlockHymenaeaLeaves.block);
		OreDictionary.registerOre("plant", BlockKomlopterisLeaves.block);
		OreDictionary.registerOre("plant", BlockMedullosalesShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockGigantopteridShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockEmplectopterisShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockBristleconeLeaves.block);
		OreDictionary.registerOre("plant", BlockHironoiaLeaves.block);
		OreDictionary.registerOre("plant", BlockArchaeopterisLeavesPlaceable.block);
		OreDictionary.registerOre("plant", BlockArchaeopterisLeavesSmallPlaceable.block);
		OreDictionary.registerOre("plant", BlockNilssoniocladusShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockNilssoniaShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockDawnRedwoodLeaves.block);
		OreDictionary.registerOre("plant", BlockAlethopterisLeaves.block);
		OreDictionary.registerOre("plant", BlockMapleLeaves.block);
		OreDictionary.registerOre("plant", BlockArtocarpusLeaves.block);
		OreDictionary.registerOre("plant", BlockEquisitesStem.block);
		OreDictionary.registerOre("plant", BlockEquisitesShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockOmphalophloiosPlaceable.block);
		OreDictionary.registerOre("plant", BlockNothofagusLeaves.block);
		OreDictionary.registerOre("plant", BlockYewLeaves.block);
		OreDictionary.registerOre("plant", BlockBrachyphyllumLeaves.block);
		OreDictionary.registerOre("plant", BlockOdontopterisShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockNoeggerathialesShootPlaceable.block);
		OreDictionary.registerOre("plant", BlockTaxodiumLeaves.block);

		OreDictionary.registerOre("plant", BlockAntarcticycas.block);
		OreDictionary.registerOre("plant", BlockCaytoniales.block);
		OreDictionary.registerOre("plant", BlockCaytoniales2.block);
		OreDictionary.registerOre("plant", BlockSphenophyllales.block);
		OreDictionary.registerOre("plant", BlockGiantHorsetail.block);
		OreDictionary.registerOre("plant", BlockBolbitis.block);
		OreDictionary.registerOre("plant", BlockCooksonia.block);
		OreDictionary.registerOre("plant", BlockPsilophyton.block);
		OreDictionary.registerOre("plant", BlockAsteroxylon.block);
		OreDictionary.registerOre("plant", BlockPrototaxites.block);
		OreDictionary.registerOre("plant", BlockNematophyta.block);
		OreDictionary.registerOre("plant", BlockLepidopteris.block);
		OreDictionary.registerOre("plant", BlockBaikalophyllum.block);
		OreDictionary.registerOre("plant", BlockBaiera.block);
		OreDictionary.registerOre("plant", BlockPolyspermophyllum.block);
		OreDictionary.registerOre("plant", BlockNystroemia.block);
		OreDictionary.registerOre("plant", BlockTrichopitys.block);
		OreDictionary.registerOre("plant", ItemIsoetesItem.block);
		OreDictionary.registerOre("plant", BlockSphenophyllales1.block);
		OreDictionary.registerOre("plant", BlockClaytosmunda.block);
		OreDictionary.registerOre("plant", BlockWachtleria.block);
		OreDictionary.registerOre("plant", BlockScytophyllum.block);
		OreDictionary.registerOre("plant", BlockAethophyllum.block);
		OreDictionary.registerOre("plant", ItemBaragwanathiaItem.block);
		OreDictionary.registerOre("plant", BlockPrehistoricGroundCoverPlants.block);
		OreDictionary.registerOre("plant", BlockPrehistoricGroundCoverPlantsLush.block);
		OreDictionary.registerOre("plant", BlockPrehistoricGroundCoverPlantsSandy.block);
		OreDictionary.registerOre("plant", BlockPrehistoricGroundCoverPlantsPangaean.block);
		OreDictionary.registerOre("plant", BlockAncientMoss.block);
		OreDictionary.registerOre("plant", BlockMatonia.block);
		OreDictionary.registerOre("plant", BlockMatoniaLarge.block);
		OreDictionary.registerOre("plant", BlockDollyphyton.block);
		OreDictionary.registerOre("plant", BlockNeuropteridium.block);
		OreDictionary.registerOre("plant", BlockStauropteris.block);
		OreDictionary.registerOre("plant", BlockOsmunda.block);
		OreDictionary.registerOre("plant", BlockRhacophyton.block);
		OreDictionary.registerOre("plant", BlockCinnamonFern.block);
		OreDictionary.registerOre("plant", BlockMarattia.block);
		OreDictionary.registerOre("plant", BlockGuangdedendron.block);
		OreDictionary.registerOre("plant", BlockEdwardsiphyton.block);
		OreDictionary.registerOre("plant", BlockEphedra.block);
		OreDictionary.registerOre("plant", ItemArchaefructusItem.block);
		OreDictionary.registerOre("plant", ItemWaterHorsetailItem.block);
		OreDictionary.registerOre("plant", BlockWoodHorsetail.block);
		OreDictionary.registerOre("plant", BlockFieldHorsetail.block);
		OreDictionary.registerOre("plant", BlockUmaltolepis.block);
		OreDictionary.registerOre("plant", BlockSphenopteris.block);
		OreDictionary.registerOre("plant", BlockTyrmia.block);
		OreDictionary.registerOre("plant", BlockWielandiella.block);
		OreDictionary.registerOre("plant", ItemNathorstianaItem.block);
		OreDictionary.registerOre("plant", ItemNeocalamitesItem.block);
		OreDictionary.registerOre("plant", BlockSelaginella.block);
		OreDictionary.registerOre("plant", ItemCobbaniaItem.block);
		OreDictionary.registerOre("plant", BlockElkinsia.block);
		OreDictionary.registerOre("plant", BlockCallistophytales.block);
		OreDictionary.registerOre("plant", BlockPalaeognetaleana.block);
		OreDictionary.registerOre("plant", BlockPseudobornia.block);
		OreDictionary.registerOre("plant", BlockAdoketophyton.block);
		OreDictionary.registerOre("plant", BlockZosterophyllum.block);
		OreDictionary.registerOre("plant", BlockFoozia.block);
		OreDictionary.registerOre("plant", BlockPertica.block);
		OreDictionary.registerOre("plant", BlockTetraxylopteris.block);
		OreDictionary.registerOre("plant", BlockTmesipteris.block);
		OreDictionary.registerOre("plant", ItemPrimevalGrassItem.block);
		OreDictionary.registerOre("plant", BlockFernEpiphyte.block);
		OreDictionary.registerOre("plant", BlockNystroemia.block);
		OreDictionary.registerOre("plant", BlockPolyspermophyllum.block);
		OreDictionary.registerOre("plant", BlockTrichopitys.block);
	}

}
