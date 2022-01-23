
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronMobile;
import net.lepidodendron.entity.EntityPrehistoricFloraScutosaurus;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockNestScutosaurus extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:nest_scutosaurus")
	public static final Block block = null;
	public BlockNestScutosaurus(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.nest_scutosaurus);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("nest_scutosaurus"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(BlockNestScutosaurus.TileEntityCustom.class, "lepidodendron:tileentitynest_scutosaurus");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:nest_scutosaurus", "inventory"));
	}
	public static class BlockCustom extends Block implements IShearable {
		public BlockCustom() {
			super(Material.PLANTS);
			setTranslationKey("pf_nest_scutosaurus");
			setSoundType(SoundType.PLANT);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(TabLepidodendronMobile.tab);
		}

		@Nullable
		public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
		{
			return NULL_AABB;
		}

		@Override
		public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
		{
			return new AxisAlignedBB(0.0D, 0.0D, 0D, 1.0D, 0.45D, 1.0D);
		}

		@Override
		protected boolean canSilkHarvest() {
			return true;
		}

		@Override
		public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos) {
			return true;
		}

		@Override
		public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
			return NonNullList.withSize(1, new ItemStack(this, (int) (1)));
		}

		@Override
		public Item getItemDropped(IBlockState state, Random rand, int fortune) {
			return new ItemStack(Blocks.AIR, (int) (1)).getItem();
		}

		@SideOnly(Side.CLIENT)
		@Override
		public EnumBlockRenderType getRenderType(IBlockState state) {
			return EnumBlockRenderType.MODEL;
		}

		@Override
		public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
			return 10;
		}

		@Override
		public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
			return 10;
		}

		@Override
		public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos pos) {
			return MapColor.FOLIAGE;
		}

		@Override
		public void breakBlock(World world, BlockPos pos, IBlockState state) {
			if (!world.isRemote) {
				SpawnEggs(world, pos);
			}
			world.removeTileEntity(pos);

			super.breakBlock(world, pos, state);
		}

		@Override
		public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
			if (!worldIn.isRemote) {
				ItemStack stack = playerIn.getHeldItemMainhand();
				if (stack.getItem() == Items.SHEARS) {
					//This will harvest:
					if (!playerIn.capabilities.isCreativeMode) {
						List<EntityPrehistoricFloraScutosaurus> Scutosaurus = worldIn.getEntitiesWithinAABB(EntityPrehistoricFloraScutosaurus.class, new AxisAlignedBB(pos.add(-16, -8, -16), pos.add(16, 8, 16)));
						for (EntityPrehistoricFloraScutosaurus currentScutosaurus : Scutosaurus) {
							if (currentScutosaurus.isPFAdult()) {
								currentScutosaurus.setAttackTarget(playerIn);
								currentScutosaurus.setOneHit(true);
							}
						}
					}
				}
			}
			super.onBlockClicked(worldIn, pos, playerIn);
		}

		public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
		{
			String eggRenderType = "";
			eggRenderType = new Object() {
				public String getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getString(tag);
					return "";
				}
			}.getValue(new BlockPos(pos), "egg");

			if ((!player.capabilities.allowEdit) || (!player.getHeldItemMainhand().isEmpty()) || eggRenderType.equals(""))
			{
				return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
			}
			else {
				if (!((hand != player.getActiveHand()) && (hand == EnumHand.MAIN_HAND))) {
					ItemStack stackSeed = new ItemStack(BlockEggsScutosaurus.block, (int) (1));
					stackSeed.setCount(1);
					ItemHandlerHelper.giveItemToPlayer(player, stackSeed);
					TileEntity te = world.getTileEntity(pos);
					if (te != null) {
						te.getTileData().setString("egg", "");
					}
					IBlockState bstate = world.getBlockState(pos);
					world.notifyBlockUpdate(pos, bstate, bstate, 3);
					if (!player.capabilities.isCreativeMode) {
						List<EntityPrehistoricFloraScutosaurus> Scutosaurus = world.getEntitiesWithinAABB(EntityPrehistoricFloraScutosaurus.class, new AxisAlignedBB(pos.add(-16, -8, -16), pos.add(16, 8, 16)));
						for (EntityPrehistoricFloraScutosaurus currentScutosaurus : Scutosaurus) {
							if (currentScutosaurus.isPFAdult()) {
								currentScutosaurus.setAttackTarget(player);
								currentScutosaurus.setOneHit(true);
							}
						}
					}
					return true;
				}
				return true;
			}
		}

		public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
			//Aggro nearby adults:
			if (!player.capabilities.isCreativeMode) {
				List<EntityPrehistoricFloraScutosaurus> Scutosaurus = world.getEntitiesWithinAABB(EntityPrehistoricFloraScutosaurus.class, new AxisAlignedBB(pos.add(-16, -8, -16), pos.add(16, 8, 16)));
				for (EntityPrehistoricFloraScutosaurus currentScutosaurus : Scutosaurus) {
					if (currentScutosaurus.isPFAdult()) {
						currentScutosaurus.setAttackTarget(player);
						currentScutosaurus.setOneHit(true);
					}
				}
			}
			return super.removedByPlayer(state, world, pos, player, willHarvest);
		}

		public void SpawnEggs(World world, BlockPos pos) {
			String eggRenderType = new Object() {
				public String getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getString(tag);
					return "";
				}
			}.getValue(new BlockPos(pos), "egg");

			EntityItem entityToSpawn = null;
			if (!eggRenderType.equals("")) {
				Block blockSpawn = BlockEggsScutosaurus.block;
				//System.err.println("Block " + blockSpawn);
				if (blockSpawn != null) {
					entityToSpawn = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(blockSpawn, (int) (1)));
				}
			}
			//Spawn the eggs:
			if (!world.isRemote && entityToSpawn != null) {
				entityToSpawn.setPickupDelay(10);
				world.spawnEntity(entityToSpawn);
				//System.err.println("Spawned " + entityToSpawn);
			}
		}

		@Override
		public boolean hasTileEntity(IBlockState state) {
			return true;
		}

		@Nullable
		@Override
		public TileEntity createTileEntity(World world, IBlockState state) {
			return new BlockNestScutosaurus.TileEntityCustom();
		}

		public BlockNestScutosaurus.TileEntityCustom createNewTileEntity(World worldIn, int meta) {
			return new BlockNestScutosaurus.TileEntityCustom();
		}

		@Override
		public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int eventID, int eventParam) {
			super.eventReceived(state, worldIn, pos, eventID, eventParam);
			TileEntity tileentity = worldIn.getTileEntity(pos);
			return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
		}

		@Override
		public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing side) {
			return BlockFaceShape.UNDEFINED;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public BlockRenderLayer getRenderLayer() {
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
		public boolean isFullBlock(IBlockState state) {
			return false;
		}

		@Override
		public boolean isFullCube(IBlockState state) {
			return false;
		}

		@Override
		public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
			return false;
		}

	}

	public static class TileEntityCustom extends TileEntity {

		private String egg;

		@Override
		public SPacketUpdateTileEntity getUpdatePacket() {
			NBTTagCompound tag = new NBTTagCompound();
			this.writeToNBT(tag);
			return new SPacketUpdateTileEntity(pos, 1, tag);
		}

		@Override
		public void onDataPacket(NetworkManager netManager, SPacketUpdateTileEntity packet) {
			readFromNBT(packet.getNbtCompound());
		}

		@Override
		public NBTTagCompound getUpdateTag() {
			return this.writeToNBT(new NBTTagCompound());
		}

		@Override
		public void handleUpdateTag(NBTTagCompound tag) {
			this.readFromNBT(tag);
		}

		@Override
		public void readFromNBT(NBTTagCompound compound)
		{
			super.readFromNBT(compound);
			if (compound.hasKey("egg")) {
				this.egg = compound.getString("egg");
			}
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound compound)
		{
			super.writeToNBT(compound);
			if (this.hasEgg())
			{
				compound.setString("egg", this.egg);
			}
			return compound;
		}

		public boolean hasEgg()
		{
			return this.egg != null;
		}

	}
}
