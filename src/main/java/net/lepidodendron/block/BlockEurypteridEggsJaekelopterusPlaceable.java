
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.entity.EntityPrehistoricFloraJaekelopterus;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockEurypteridEggsJaekelopterusPlaceable extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:eurypterid_eggs_jaekelopterus")
	public static final Block block = null;
	public BlockEurypteridEggsJaekelopterusPlaceable(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.eurypterid_eggs_jaekelopterus);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("eurypterid_eggs_jaekelopterus"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:eurypterid_eggs_jaekelopterus", "inventory"));
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockEurypteridEggsJaekelopterus.BlockCustom.LEVEL).build());
	}
	
	public static class BlockCustom extends BlockMobSpawn {
		public BlockCustom() {
			setTranslationKey("pf_eurypterid_eggs_jaekelopterus");
			this.setTickRandomly(true);
			setCreativeTab(null);
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockEurypteridEggsJaekelopterusPlaceable.block, (int) (1));
		}

		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
		{
			super.updateTick(worldIn, pos, state, rand);

			if (worldIn.getBlockState(pos).getBlock() == this && !(worldIn.isRemote)) {
				worldIn.destroyBlock(pos, false);
				Entity entity1 = ItemMonsterPlacer.spawnCreature(worldIn, EntityList.getKey(EntityPrehistoricFloraJaekelopterus.class), (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D);
				EntityPrehistoricFloraAgeableBase ee1 = (EntityPrehistoricFloraAgeableBase) entity1;
				ee1.setAgeTicks(1);
				Entity entity2 = null;
				if (Math.random() > 0.75) {
					entity2 = ItemMonsterPlacer.spawnCreature(worldIn, EntityList.getKey(EntityPrehistoricFloraJaekelopterus.class), (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D);
					EntityPrehistoricFloraAgeableBase ee2 = (EntityPrehistoricFloraAgeableBase) entity2;
					ee2.setAgeTicks(1);
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
				tooltip.add("Type: Eurypterid");
				tooltip.add("Periods: Devonian");
				tooltip.add("Habitat: Marine");
				super.addInformation(stack, player, tooltip, advanced);
			}
		}

	}
}
