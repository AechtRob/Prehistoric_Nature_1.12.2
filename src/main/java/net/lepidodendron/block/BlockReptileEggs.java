
package net.lepidodendron.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockReptileEggs extends Block {

	public BlockReptileEggs() {
		super(Material.PLANTS);
		setSoundType(SoundType.SLIME);
		setHardness(1.0F);
		setResistance(0.0F);
		setLightLevel(0F);
		setLightOpacity(0);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this, (int) (1));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.45D, 0.75D);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer()
{
	return BlockRenderLayer.CUTOUT;
}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 60;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 30;
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos pos) {
		return MapColor.FOLIAGE;
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return false;
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
		Random rand = new Random();
		super.neighborChanged(state, world, pos, neighborBlock, fromPos);
		super.updateTick(world, pos, state, rand);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!canPlaceBlockAt(worldIn, pos)) {
			worldIn.setBlockToAir(pos);
		}
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		if ((worldIn.getBlockState(pos).getMaterial() != Material.WATER) && (worldIn.getBlockState(pos).getMaterial() != Material.LAVA)) {
			return super.canPlaceBlockAt(worldIn, pos);
		}
		return false;
	}

	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side)
	{
		boolean blockface  = true;
		if (side == EnumFacing.NORTH) {
			blockface = false;
		}
		if (side == EnumFacing.SOUTH) {
			blockface = false;
		}
		if (side == EnumFacing.EAST) {
			blockface = false;
		}
		if (side == EnumFacing.WEST) {
			blockface = false;
		}
		if (side == EnumFacing.UP) {
			if (worldIn.getBlockState(pos.down()).getBlockFaceShape(worldIn, pos.down(), side) != BlockFaceShape.SOLID)
				blockface = false;
		}
		if (side == EnumFacing.DOWN) {
			blockface = false;
		}

		return (blockface && canPlaceBlockAt(worldIn, pos));

	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
		return true;
	}

}
