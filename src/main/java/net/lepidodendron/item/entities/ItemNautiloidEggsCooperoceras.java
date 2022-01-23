
package net.lepidodendron.item.entities;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronMobile;
import net.lepidodendron.entity.EntityPrehistoricFloraCooperoceras;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@ElementsLepidodendronMod.ModElement.Tag
public class ItemNautiloidEggsCooperoceras extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:eggs_cooperoceras")
	public static final Item block = null;
	public ItemNautiloidEggsCooperoceras(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.eggs_cooperoceras);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("lepidodendron:entities/eggs_cooperoceras", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setTranslationKey("pf_eggs_cooperoceras");
			setRegistryName("eggs_cooperoceras");
			setCreativeTab(TabLepidodendronMobile.tab);
			setMaxStackSize(16);
		}

		public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	    {
	        ItemStack itemstack = playerIn.getHeldItem(handIn);
	        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

	        if (raytraceresult == null)
	        {
	            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
	        }
	        else
	        {
	            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
	            {
	                BlockPos blockpos = raytraceresult.getBlockPos();

	                if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack))
	                {
	                    return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
	                }

	                IBlockState iblockstate = worldIn.getBlockState(blockpos);

					if (iblockstate.getMaterial() == Material.WATER) {
						if (!(worldIn.isRemote)) {
							EntityPrehistoricFloraCooperoceras.summon(worldIn, EntityList.getKey(EntityPrehistoricFloraCooperoceras.class).toString(), "{AgeTicks:0}", blockpos.getX() + 0.5, blockpos.getY() + 0.5, blockpos.getZ() + 0.5);
						}
						if (!playerIn.capabilities.isCreativeMode) {
							itemstack.shrink(1);
						}
						playerIn.addStat(StatList.getObjectUseStats(this));
						worldIn.playSound(playerIn, blockpos, SoundEvents.ENTITY_BOBBER_SPLASH, SoundCategory.BLOCKS, 1.0F, 1.0F);
						return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
					}
				}
	
	            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
	        }
	    }

		@SideOnly(Side.CLIENT)
		@Override
		public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
			if (LepidodendronConfig.showTooltips) {
				tooltip.add("Type: Nautiloid");
				tooltip.add("Periods: Permian");
				super.addInformation(stack, player, tooltip, advanced);
			}
		}
	}

}
