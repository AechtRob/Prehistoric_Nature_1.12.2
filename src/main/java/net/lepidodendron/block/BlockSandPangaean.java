
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.creativetab.TabLepidodendron;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockSandPangaean extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:sand_pangaean")
	public static final Block block = null;
	public BlockSandPangaean(ElementsLepidodendronMod instance) {
		super(instance, 526);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("sand_pangaean"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:sand_pangaean", "inventory"));
	}
	
	public static class BlockCustom extends BlockFalling implements ISustainsPlantType {
		public BlockCustom() {
			super(Material.SAND);
			this.setSoundType(SoundType.SAND);
			setHardness(0.5F);
			setResistance(0.5F);
			setTranslationKey("sand_pangaean");
			setHarvestLevel("shovel", 0);
			setCreativeTab(TabLepidodendron.tab);
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
		public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
		{
			return this.canSustainPlantType(world, pos, plantable.getPlantType(world, pos.offset(direction)));
		}
	    
	}
}
