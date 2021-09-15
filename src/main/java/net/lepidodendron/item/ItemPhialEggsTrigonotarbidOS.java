
package net.lepidodendron.item;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.block.BlockInsectEggsTrigonotarbidOS;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsLepidodendronMod.ModElement.Tag
public class ItemPhialEggsTrigonotarbidOS extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:phial_eggs_trigonotarbid_os")
	public static final Item block = null;
	public ItemPhialEggsTrigonotarbidOS(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.phial_eggs_trigonotarbid_os);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("lepidodendron:phial_eggs_trigonotarbid_os", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setMaxDamage(0);
			maxStackSize = 8;
			setTranslationKey("pf_phial_eggs_trigonotarbid_os");
			setRegistryName("phial_eggs_trigonotarbid_os");
			setCreativeTab(null);
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
		public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
			{
				ItemStack itemstack = player.getHeldItem(hand);

				if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
					return EnumActionResult.FAIL;
				} else {
					if (BlockInsectEggsTrigonotarbidOS.block.canPlaceBlockOnSide(worldIn, pos.offset(facing), facing)) {
						worldIn.setBlockState(pos.offset(facing), BlockInsectEggsTrigonotarbidOS.block.getStateForPlacement(worldIn, pos.offset(facing), facing, hitX, hitY, hitZ, 0, (EntityLivingBase) player));
						SoundEvent soundevent = SoundEvents.BLOCK_SLIME_BREAK;
						worldIn.playSound(player, pos.offset(facing), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
						return EnumActionResult.SUCCESS;
					}
				}
				return EnumActionResult.PASS;
			}
		}
	}
}
