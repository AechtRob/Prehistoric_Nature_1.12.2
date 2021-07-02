
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.item.ItemPhial;
import net.lepidodendron.item.ItemPhialEggsSchinderhannes;
import net.lepidodendron.world.MobSpawnGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;


@ElementsLepidodendronMod.ModElement.Tag
public class BlockInsectEggsSchinderhannes extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:eggs_schinderhannes_worldgen")
	public static final Block block = null;
	public BlockInsectEggsSchinderhannes(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.eggs_schinderhannes_worldgen);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("eggs_schinderhannes_worldgen"));
		//elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
		//new ModelResourceLocation("lepidodendron:insect_eggs_eoarthropleura_worldgen", "inventory"));
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockInsectEggsSchinderhannes.BlockCustom.LEVEL).build());
	}

	@Override
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {

		if (dimID != LepidodendronConfig.dimDevonian) {
			return;
		}
		int minWaterDepth = 2;
		int waterDepthCheckMax = 4;
		int startHeight = world.getSeaLevel() - waterDepthCheckMax;
		for (int i = 0; i < (int) 3; i++) {
			int l6 = chunkX + random.nextInt(16) + 8;
			int i11 = random.nextInt(128 - startHeight) + startHeight;
			int l14 = chunkZ + random.nextInt(16) + 8;
			(new MobSpawnGenerator((Block) block)).generate(world, random, new BlockPos(l6, i11, l14), minWaterDepth, waterDepthCheckMax);
		}
	}

	public static class BlockCustom extends BlockMobSpawn {
		public BlockCustom() {
			setTranslationKey("pf_eggs_schinderhannes_worldgen");
			//this.setTickRandomly(true);
			setCreativeTab(null);
		}

		@Override
		public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
			return NonNullList.withSize(1, new ItemStack(BlockInsectEggsSchinderhannesPlaceable.block, (int) (1)));
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockInsectEggsSchinderhannesPlaceable.block, (int) (1));
		}

		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
		{
			super.updateTick(worldIn, pos, state, rand);

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
					ItemStack _setstack = new ItemStack(ItemPhialEggsSchinderhannes.block, (int) (1));
					_setstack.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(player, _setstack);
					world.setBlockToAir(pos);
				}
				return true;
			}
		}
	}
}
