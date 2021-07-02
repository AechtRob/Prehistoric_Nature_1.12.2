
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@ElementsLepidodendronMod.ModElement.Tag
public class BlockSandSticky extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:sand_sticky")
	public static final Block block = null;
	public BlockSandSticky(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.sand_sticky);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("sand_sticky"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:sand_sticky", "inventory"));
	}
	
	public static class BlockCustom extends Block implements ISustainsPlantType {
		public BlockCustom() {
			super(Material.SAND);
			setTranslationKey("pf_sand_sticky");
			setSoundType(SoundType.SAND);
			setCreativeTab(null);
			setLightLevel(0F);
			setLightOpacity(255);
			setHardness(0.5F);
			setResistance(0.5F);
			setHarvestLevel("shovel", 0);
		}

		public boolean canSustainPlantType(IBlockAccess world, BlockPos pos, EnumPlantType plantType)
		{
			// support desert, plains and cave plants
			if (plantType == EnumPlantType.Desert)
			{
				return true;
			}
			// support beach plants if there's water alongside
			if (plantType == EnumPlantType.Beach)
			{
				return (
						world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
								world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
								world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
								world.getBlockState(pos.south()).getMaterial() == Material.WATER
				);
			}
			return false;
		}

		@Override
		public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos pos) {
			return MapColor.SAND;
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.add(new ItemStack(Blocks.SAND, (int) (1), 0));
		}

		@Override
		protected boolean canSilkHarvest()
	    {
	        return true;
	    }

		@Override
		public ItemStack getSilkTouchDrop(IBlockState state)  {
			return new ItemStack(Blocks.SAND, (int) (1), 0);
		}

		public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
		{
			if (entityIn instanceof EntityPlayer)
			{
				if (!((EntityPlayer) entityIn).capabilities.isCreativeMode) {
					entityIn.setInWeb();
				}
			}
			else if (entityIn instanceof EntityLivingBase)
			{
				entityIn.setInWeb();
			}
		}

	}
}
