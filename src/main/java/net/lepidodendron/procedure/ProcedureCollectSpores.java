package net.lepidodendron.procedure;

import net.lepidodendron.block.*;
import net.lepidodendron.item.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import net.minecraftforge.items.ItemHandlerHelper;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureCollectSpores extends ElementsLepidodendronMod.ModElement {
	public ProcedureCollectSpores(ElementsLepidodendronMod instance) {
		super(instance, 360);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {

		if (LepidodendronConfig.doSpores) {
	
			if (dependencies.get("entity") == null) {
				System.err.println("Failed to load dependency entity for procedure CollectSpores!");
				return;
			}
			if (dependencies.get("x") == null) {
				System.err.println("Failed to load dependency x for procedure CollectSpores!");
				return;
			}
			if (dependencies.get("y") == null) {
				System.err.println("Failed to load dependency y for procedure CollectSpores!");
				return;
			}
			if (dependencies.get("z") == null) {
				System.err.println("Failed to load dependency z for procedure CollectSpores!");
				return;
			}
			if (dependencies.get("world") == null) {
				System.err.println("Failed to load dependency world for procedure CollectSpores!");
				return;
			}
			Entity entity = (Entity) dependencies.get("entity");
			int x = (int) dependencies.get("x");
			int y = (int) dependencies.get("y");
			int z = (int) dependencies.get("z");
			World world = (World) dependencies.get("world");
			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockBothrodendronStrobilus.block) {
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemBothrodendronSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}
	
	
			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockDiaphorodendronStrobilus.block) {
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemDiaphorodendronSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}
	
			
			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockLepidodendronStrobilus.block) {
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemLepidodendronSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}
	
			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockSigillariaStrobilus.block) {
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemSigillariaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockTempskyaLeaves.block) 
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockTempskyaLeavesPlaceable.block)) {
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemTempskyaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}


			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockCalamitesStrobilus.block) {
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemCalamitesSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}


			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPleuromeiaShootTopFlower.block) {
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemPleuromeiaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPsaroniusLeaves.block) 
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPsaroniusLeavesPlaceable.block) 
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPsaroniusLeavesSmall.block) 
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPsaroniusLeavesSmallPlaceable.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPsaroniusLeavesTop.block)) 
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemPsaroniusSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockDicksoniaLeaves.block) 
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockDicksoniaLeavesPlaceable.block) 
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockDicksoniaLeaves2.block) 
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockDicksoniaLeaves3.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockDicksoniaLeaves4.block)) 
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemDicksoniaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockSphenophyllales.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemSphenophyllalesSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockGiantHorsetailFlowerShoot.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemHorsetailSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockCooksoniaSpore.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemCooksoniaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPsilophytonSpore.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemPsilophytonSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockAsteroxylon.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemAsteroxylonSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPrototaxitesStrobilus.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemPrototaxitesSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockWattiezaShoot.block)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockWattiezaShootPlaceable.block)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockWattiezaShoot02.block)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockWattiezaShoot03.block)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockWattiezaShootSide02.block)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockWattiezaShootSide03.block)
			)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemWattiezaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockValmeyerodendronStrobilus.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemValmeyerodendronSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockIsoetes.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemIsoetesSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockSphenophyllales1.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemSphenophyllales1Spores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockClaytosmunda.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemClaytosmundaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockWachtleria.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemWachtleriaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockBaragwanathia.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemBaragwanathiaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockMatonia.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemSmallMatoniaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockMatoniaLarge.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemLargeMatoniaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (
				((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockArchaeopterisLeaves.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockArchaeopterisLeavesPlaceable.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockArchaeopterisLeavesSmall.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockArchaeopterisLeavesSmallPlaceable.block)
				)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemArchaeopterisSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockDollyphyton.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemDollyphytonSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockRhacophyton.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemRhacophytonSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockOsmunda.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemOsmundaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockStauropteris.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemStauropterisSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockNeuropteridium.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemNeuropteridiumSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockCinnamonFern.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemCinnamonFernSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockGuangdedendronTop.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemGuangdedendronSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockMarattia.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemMarattiaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockWaterHorsetail.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemWaterHorsetailSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockWoodHorsetail.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemWoodHorsetailSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockFieldHorsetail.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemFieldHorsetailSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockEquisitesStrobilus.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemEquisetitesSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockNathorstiana.block)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemNathorstianaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockOmphalophloiosPlaceable.block)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockOmphalophloiosCentre.block)
			|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockOmphalophloiosTop.block))
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemOmphalophloiosSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockNeocalamites3.block))
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemNeocalamitesSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (
				((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPseudobornia.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPseudobornia2.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPseudobornia3.block)
				)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemPseudoborniaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockAdoketophytonSpore.block))
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemAdoketophytonSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockZosterophyllumSpore.block))
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemZosterophyllumSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPerticaSpore.block))
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemPerticaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockFooziaSpore.block))
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemFooziaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockTetraxylopterisSpore.block))
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemTetraxylopterisSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockTmesipteris.block))
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemTmesipterisSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (
				((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockAnkyropterisStem.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockAnkyropterisStemNE.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockAnkyropterisStemNW.block)
				)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemAnkyropterisSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (
					((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPalaeostachyaTop.block)
							|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockPalaeostachyaBottom.block)
			)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemPalaeostachyaSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (
					((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockSchizoneura1.block)
							|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockSchizoneura2.block)
			)
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemSchizoneuraSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
					}
				}
			}

			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockNoeggerathialesShoot.block)
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BlockNoeggerathialesShootPlaceable.block))
			{
				if (!(world.isRemote)) {
					if (entity instanceof EntityLivingBase) {
						((EntityLivingBase) entity).swingArm(EnumHand.MAIN_HAND);
					}
					if (entity instanceof EntityPlayer) {
						if ((((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem() == new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem())) {
							((EntityPlayer) entity).inventory.clearMatchingItems(new ItemStack(ItemSporeCollectionEnvelope.block, (int) (1)).getItem(), -1, (int) 1, null);
							ItemStack _setstack = new ItemStack(ItemNoeggerathialesSpores.block, (int) (1));
							_setstack.setCount(1);
							ItemHandlerHelper.giveItemToPlayer(((EntityPlayer) entity), _setstack);
						}
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
