
package net.lepidodendron.item;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronMisc;
import net.lepidodendron.enchantments.Enchantments;
import net.lepidodendron.world.dimension.cambrian.WorldCambrian;
import net.lepidodendron.world.dimension.carboniferous.WorldCarboniferous;
import net.lepidodendron.world.dimension.devonian.WorldDevonian;
import net.lepidodendron.world.dimension.ordoviciansilurian.WorldOrdovicianSilurian;
import net.lepidodendron.world.dimension.permian.WorldPermian;
import net.lepidodendron.world.dimension.precambrian.WorldPrecambrian;
import net.lepidodendron.world.dimension.triassic.WorldTriassic;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@ElementsLepidodendronMod.ModElement.Tag
public class ItemBoneWand extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:bone_wand")
	public static final Item block = null;
	public ItemBoneWand(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.bone_wand);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("lepidodendron:bone_wand", "inventory"));
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(100);
			maxStackSize = 1;
			setTranslationKey("pf_bone_wand");
			setRegistryName("bone_wand");
			setCreativeTab(TabLepidodendronMisc.tab);
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getMaxItemUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, IBlockState par2Block) {
			return 1F;
		}

		@Override
		public EnumActionResult onItemUse(EntityPlayer entity, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY,
				float hitZ) {
			pos = pos.offset(facing);
			ItemStack itemstack = entity.getHeldItem(hand);
			int levelEnchantment = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(Enchantments.TIME_REVERSAL, itemstack);
			//System.err.println(levelEnchantment);
			if (levelEnchantment > 0) {
				if (!entity.canPlayerEdit(pos, facing, itemstack)) {
					return EnumActionResult.FAIL;
				}
				else {
					if (world.isAirBlock(pos)) {
						//WorldProterozoicWater.portal.portalSpawn(world, pos);
						WorldPrecambrian.portal.portalSpawn(world, pos);
						WorldCambrian.portal.portalSpawn(world, pos);
						WorldOrdovicianSilurian.portal.portalSpawn(world, pos);
						WorldDevonian.portal.portalSpawn(world, pos);
						WorldCarboniferous.portal.portalSpawn(world, pos);
						WorldPermian.portal.portalSpawn(world, pos);
						WorldTriassic.portal.portalSpawn(world, pos);
						if (!entity.capabilities.isCreativeMode) {
							itemstack.damageItem(1, entity);
						}
						return EnumActionResult.SUCCESS;
					}
				}
			}
			return EnumActionResult.FAIL;
		}

		@Override
		public boolean isRepairable() {
			return false;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
			if (LepidodendronConfig.showTooltips) {
				tooltip.add("Enchant with the Enchantment of Time Reversal");
			}
			super.addInformation(stack, player, tooltip, advanced);
		}
	}
}
