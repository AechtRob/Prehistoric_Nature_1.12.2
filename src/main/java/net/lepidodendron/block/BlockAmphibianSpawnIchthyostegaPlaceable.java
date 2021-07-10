
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronMobile;
import net.lepidodendron.entity.EntityPrehistoricFloraIchthyostega;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.lepidodendron.item.entities.ItemBucketSpawnIchthyostega;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
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
public class BlockAmphibianSpawnIchthyostegaPlaceable extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:amphibian_spawn_ichthyostega")
	public static final Block block = null;
	public BlockAmphibianSpawnIchthyostegaPlaceable(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.amphibian_spawn_ichthyostega);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("amphibian_spawn_ichthyostega"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:amphibian_spawn_ichthyostega", "inventory"));
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockAmphibianSpawnIchthyostegaPlaceable.LEVEL).build());
	}
	
	public static class BlockCustom extends BlockMobSpawn  {
		public BlockCustom() {
			setTranslationKey("pf_amphibian_spawn_ichthyostega");
			this.setTickRandomly(true);
			setCreativeTab(TabLepidodendronMobile.tab);
		}

		@Override
		public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
			return NonNullList.withSize(1, new ItemStack(BlockAmphibianSpawnIchthyostegaPlaceable.block, (int) (1)));
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockAmphibianSpawnIchthyostegaPlaceable.block, (int) (1));
		}

		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) 
		{
			super.updateTick(worldIn, pos, state, rand);
			if (!(worldIn.isRemote)) {
				Entity entity = ItemMonsterPlacer.spawnCreature(worldIn, EntityList.getKey(EntityPrehistoricFloraIchthyostega.class), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
				if (entity != null) {
					EntityPrehistoricFloraAgeableBase ee = (EntityPrehistoricFloraAgeableBase) entity;
					ee.setAgeTicks(1);
					worldIn.destroyBlock(pos, false);
				}
			}
		}

		@Override
		public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

			ItemStack itemstack = playerIn.getHeldItem(hand);

			if (!itemstack.isEmpty()) {
				if (itemstack.getItem() == Items.WATER_BUCKET) {
					playerIn.inventory.clearMatchingItems(new ItemStack(Items.WATER_BUCKET, (int) (1)).getItem(), -1, (int) 1, null);
					SoundEvent soundevent = SoundEvents.ITEM_BUCKET_FILL;
					playerIn.getEntityWorld().playSound(playerIn, playerIn.getPosition(), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
					ItemStack itemstack1 = new ItemStack(ItemBucketSpawnIchthyostega.block, (int) (1));
					itemstack1.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(playerIn, itemstack1);
					worldIn.setBlockToAir(pos);
					return true;
				}
			}
			return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
			if (LepidodendronConfig.showTooltips) {
				tooltip.add("Type: Fish/Stem-Amphibian");
				tooltip.add("Periods: Devonian");
				tooltip.add("Habitat: Swamps, rivers");
				super.addInformation(stack, player, tooltip, advanced);
			}
		}
	}
}