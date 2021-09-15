
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.entity.EntityPrehistoricFloraAttercopus;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockInsectEggsAttercopus extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:insect_eggs_attercopus")
	public static final Block block = null;
	public BlockInsectEggsAttercopus(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.insect_eggs_attercopus);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("insect_eggs_attercopus"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(BlockInsectEggsAttercopus.TileEntityCustom.class, "lepidodendron:tileentityinsect_eggs_attercopus");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:insect_eggs_attercopus", "inventory"));
	}
	public static class BlockCustom extends BlockInsectEggs {
		public BlockCustom() {
			setTranslationKey("pf_insect_eggs_attercopus");
			this.setTickRandomly(true);
			setCreativeTab(null);
		}

		@Override
		public void breakBlock(World world, BlockPos pos, IBlockState state) {
			world.removeTileEntity(pos);
			super.breakBlock(world, pos, state);
		}

		@Override
		public boolean hasTileEntity(IBlockState state) {
			return true;
		}

		@Nullable
		@Override
		public TileEntity createTileEntity(World world, IBlockState state) {
			return new BlockInsectEggsAttercopus.TileEntityCustom();
		}

		public BlockInsectEggsAttercopus.TileEntityCustom createNewTileEntity(World worldIn, int meta) {
			return new BlockInsectEggsAttercopus.TileEntityCustom();
		}

		@Override
		public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int eventID, int eventParam) {
			super.eventReceived(state, worldIn, pos, eventID, eventParam);
			TileEntity tileentity = worldIn.getTileEntity(pos);
			return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
		}

		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
		{
			super.updateTick(worldIn, pos, state, rand);

			if (worldIn.getBlockState(pos).getBlock() == this && !(worldIn.isRemote)) {
				//worldIn.destroyBlock(pos, false);
				Entity entity1 = ItemMonsterPlacer.spawnCreature(worldIn, EntityList.getKey(EntityPrehistoricFloraAttercopus.class), (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D);
				Entity entity2 = null;
				if (Math.random() > 0.75) {
					entity2 = ItemMonsterPlacer.spawnCreature(worldIn, EntityList.getKey(EntityPrehistoricFloraAttercopus.class), (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D);
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
				tooltip.add("Habitat: Land");
				super.addInformation(stack, player, tooltip, advanced);
			}
		}
	}

	public static class TileEntityCustom extends TileEntity {

	}
}
