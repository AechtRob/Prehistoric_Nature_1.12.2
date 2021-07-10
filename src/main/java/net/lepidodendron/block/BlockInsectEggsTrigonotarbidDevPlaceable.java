
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronMobile;
import net.lepidodendron.entity.EntityPrehistoricFloraTrigonotarbid_Palaeocharinus;
import net.lepidodendron.item.ItemPhial;
import net.lepidodendron.item.ItemPhialEggsTrigonotarbidDev;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockInsectEggsTrigonotarbidDevPlaceable extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:insect_eggs_trigonotarbid_dev")
	public static final Block block = null;
	public BlockInsectEggsTrigonotarbidDevPlaceable(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.insect_eggs_trigonotarbid_dev);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("insect_eggs_trigonotarbid_dev"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:insect_eggs_trigonotarbid_dev", "inventory"));
	}
	
	public static class BlockCustom extends BlockInsectEggs {
		public BlockCustom() {
			setTranslationKey("pf_insect_eggs_trigonotarbid_dev");
			this.setTickRandomly(true);
			setCreativeTab(TabLepidodendronMobile.tab);
		}

		@Override
		public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
			return NonNullList.withSize(1, new ItemStack(BlockInsectEggsTrigonotarbidDevPlaceable.block, (int) (1)));
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockInsectEggsTrigonotarbidDevPlaceable.block, (int) (1));
		}

		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
		{
			super.updateTick(worldIn, pos, state, rand);

			if (worldIn.getBlockState(pos).getBlock() == this && !(worldIn.isRemote)) {
				worldIn.destroyBlock(pos, false);
				Entity entity1 = null;
				Entity entity2 = null;

				entity1 = ItemMonsterPlacer.spawnCreature(worldIn, EntityList.getKey(EntityPrehistoricFloraTrigonotarbid_Palaeocharinus.class), (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D);
				if (Math.random() > 0.75) {
					entity2 = ItemMonsterPlacer.spawnCreature(worldIn, EntityList.getKey(EntityPrehistoricFloraTrigonotarbid_Palaeocharinus.class), (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D);
				}

				if (entity1 != null || entity2 != null) {
					worldIn.destroyBlock(pos, false);
				}
			}
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
			if (LepidodendronConfig.showTooltips) {
				tooltip.add("Type: Arachnid");
				tooltip.add("Periods: Devonian");
				super.addInformation(stack, player, tooltip, advanced);
			}
		}

		public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
		{
			if (!player.capabilities.allowEdit)
			{
				return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
			}
			else {
				if (player.getHeldItemMainhand().getItem() == new ItemStack(ItemPhial.block, (int) (1)).getItem()) {
					player.inventory.clearMatchingItems(new ItemStack(ItemPhial.block, (int) (1)).getItem(), -1, (int) 1, null);
					ItemStack _setstack = new ItemStack(ItemPhialEggsTrigonotarbidDev.block, (int) (1));
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(player, _setstack);
					world.setBlockToAir(pos);
				}
				return true;
			}
		}
	}
}
