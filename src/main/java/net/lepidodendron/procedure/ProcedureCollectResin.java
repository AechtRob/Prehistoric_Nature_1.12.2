package net.lepidodendron.procedure;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;

import net.lepidodendron.item.ItemBottleOfResin;
//import net.lepidodendron.block.BlockAgathisLog;
//import net.lepidodendron.block.BlockWollemiLog;
//import net.lepidodendron.block.BlockMonkeypuzzleLog;
//import net.lepidodendron.block.BlockColumnarisLog;
//import net.lepidodendron.block.BlockBunyaLog;
//import net.lepidodendron.block.BlockAraucarioxylonLog;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.oredict.OreDictionary;

import net.lepidodendron.ElementsLepidodendronMod;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureCollectResin extends ElementsLepidodendronMod.ModElement {
	public ProcedureCollectResin(ElementsLepidodendronMod instance) {
		super(instance, 360);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure CollectResin!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure CollectResin!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure CollectResin!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure CollectResin!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure CollectResin!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");

		//Multiple error-handling/escaping here: the pickblock falls over in many ways with third party mods :/
		if (!(world.isRemote)) {
			if (entity instanceof EntityPlayer) {
				if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Items.GLASS_BOTTLE, (int) (1)).getItem()))
					{
					try {
						if (
							(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlock().getPickBlock(
									world.getBlockState(new BlockPos((int) x, (int) y, (int) z)),
									null,
									world,
									new BlockPos((int) x, (int) y, (int) z),
									null)) != null
							) {
								if (OreDictionary.containsMatch(false, OreDictionary.getOres("logResin"),
									(world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock().getPickBlock(
										world.getBlockState(new BlockPos((int) x, (int) y, (int) z)),
										null,
										world,
										new BlockPos((int) x, (int) y, (int) z),
										null)	)) {
				
								if (entity instanceof EntityLivingBase) {
									((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
								}
								((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(Items.GLASS_BOTTLE, (int) (1)).getItem(), -1, (int) 1, null);
								ItemStack _setstack = new ItemStack(ItemBottleOfResin.block, (int) (1));
								_setstack.setCount(1);
								ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
							}
						} 
					}
					catch (Exception e) {
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		EntityPlayer entity = event.getEntityPlayer();
		if (event.getHand() != entity.getActiveHand())
			return;

		int i = event.getPos().getX();
		int j = event.getPos().getY();
		int k = event.getPos().getZ();
		World world = event.getWorld();
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
