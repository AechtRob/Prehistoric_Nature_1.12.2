
package net.lepidodendron.item;

import net.lepidodendron.world.dimension.*;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumActionResult;
import net.minecraft.entity.player.EntityPlayer;

import net.lepidodendron.creativetab.TabLepidodendron;
import net.lepidodendron.ElementsLepidodendronMod;

@ElementsLepidodendronMod.ModElement.Tag
public class ItemBoneWand extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:bone_wand")
	public static final Item block = null;
	public ItemBoneWand(ElementsLepidodendronMod instance) {
		super(instance, 1593);
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
			setMaxDamage(0);
			maxStackSize = 1;
			setTranslationKey("pf_bone_wand");
			setRegistryName("bone_wand");
			setCreativeTab(TabLepidodendron.tab);
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
		@SideOnly(Side.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public EnumActionResult onItemUse(EntityPlayer entity, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY,
				float hitZ) {
			pos = pos.offset(facing);
			ItemStack itemstack = entity.getHeldItem(hand);
			if (!entity.canPlayerEdit(pos, facing, itemstack)) {
				return EnumActionResult.FAIL;
			} else {
				if (world.isAirBlock(pos))
					//WorldProterozoicWater.portal.portalSpawn(world, pos);
					WorldPrecambrian.portal.portalSpawn(world, pos);
					WorldCambrian.portal.portalSpawn(world, pos);
					WorldOrdovicianSilurian.portal.portalSpawn(world, pos);
					WorldDevonian.portal.portalSpawn(world, pos);
					WorldCarboniferous.portal.portalSpawn(world, pos);
					//WorldPermian.portal.portalSpawn(world, pos);
					itemstack.damageItem(1, entity);
					return EnumActionResult.SUCCESS;
			}
		}

	}
}
