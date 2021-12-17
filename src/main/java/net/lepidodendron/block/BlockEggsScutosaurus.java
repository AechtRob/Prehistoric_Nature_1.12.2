
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronMobile;
import net.lepidodendron.entity.EntityPrehistoricFloraScutosaurus;
import net.lepidodendron.entity.base.EntityPrehistoricFloraAgeableBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockEggsScutosaurus extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:eggs_scutosaurus")
	public static final Block block = null;
	public BlockEggsScutosaurus(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.eggs_scutosaurus);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("eggs_scutosaurus"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:eggs_scutosaurus", "inventory"));
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(BlockEggsScutosaurus.TileEntityEggScutosaurus.class, "lepidodendron:tileentityeggs_scutosaurus");
	}
	
	public static class BlockCustom extends BlockReptileEggs {
		public BlockCustom() {
			setTranslationKey("pf_eggs_scutosaurus");
			this.setTickRandomly(true);
			setCreativeTab(TabLepidodendronMobile.tab);
		}

		public static int getIncubation() {return 6000;}

		@Override
		public void breakBlock(World world, BlockPos pos, IBlockState state) {
			world.removeTileEntity(pos);
			super.breakBlock(world, pos, state);
		}

		@Override
		public boolean hasTileEntity(IBlockState state) {
			return true;
		}

		@Nullable
		@Override
		public TileEntity createTileEntity(World world, IBlockState state) {
			return new BlockEggsScutosaurus.TileEntityEggScutosaurus();
		}

		public BlockEggsScutosaurus.TileEntityEggScutosaurus createNewTileEntity(World worldIn, int meta) {
			return new BlockEggsScutosaurus.TileEntityEggScutosaurus();
		}

		@Override
		public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int eventID, int eventParam) {
			super.eventReceived(state, worldIn, pos, eventID, eventParam);
			TileEntity tileentity = worldIn.getTileEntity(pos);
			return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
		}

		@Override
		public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
		{
			super.onBlockAdded(worldIn, pos, state);
			TileEntity te = worldIn.getTileEntity(pos);
			if (te != null) {
				if (te instanceof BlockEggsScutosaurus.TileEntityEggScutosaurus) {
					BlockEggsScutosaurus.TileEntityEggScutosaurus Egg = (BlockEggsScutosaurus.TileEntityEggScutosaurus) te;
					Egg.setIncubation(0);
				}
			}
		}

		@Override
		public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
			super.onBlockPlacedBy(worldIn, pos, state, placer, stack);

			TileEntity te = worldIn.getTileEntity(pos);
			if (te != null) {
				if (te instanceof BlockEggsScutosaurus.TileEntityEggScutosaurus) {
					BlockEggsScutosaurus.TileEntityEggScutosaurus Egg = (BlockEggsScutosaurus.TileEntityEggScutosaurus) te;
					Egg.setIncubation(0);
				}
			}
		}

		@Override
		public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
			Random rand = new Random();
			super.neighborChanged(state, world, pos, neighborBlock, fromPos);
			updateTick(world, pos, state, rand);
		}

		@Override
		public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
		{
			super.updateTick(worldIn, pos, state, rand);

			if (!canPlaceBlockAt(worldIn, pos)) {
				worldIn.setBlockToAir(pos);
				EntityItem entityToSpawn = null;
				entityToSpawn = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this, (int) (1)));
				if (!worldIn.isRemote && entityToSpawn != null) {
					entityToSpawn.setPickupDelay(10);
					worldIn.spawnEntity(entityToSpawn);
				}
				return;
			}

			int incubation = 0;
			TileEntity te = worldIn.getTileEntity(pos);
			if (te != null) {
				if (te instanceof BlockEggsScutosaurus.TileEntityEggScutosaurus) {
					BlockEggsScutosaurus.TileEntityEggScutosaurus Egg = (BlockEggsScutosaurus.TileEntityEggScutosaurus) te;
					incubation = Egg.getIncubation();
				}
			}
			if (incubation >= this.getIncubation()) {
				if (!(worldIn.isRemote)) {
					EntityPrehistoricFloraAgeableBase.summon(worldIn, EntityList.getKey(EntityPrehistoricFloraScutosaurus.class).toString(), "{AgeTicks:0}", (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D);
				}
				worldIn.destroyBlock(pos, false);
			}
		}

		@SideOnly(Side.CLIENT)
		@Override
		public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
			if (LepidodendronConfig.showTooltips) {
				tooltip.add("Type: Therapsid");
				tooltip.add("Periods: Permian");
				super.addInformation(stack, player, tooltip, advanced);
			}
		}

	}

	public static class TileEntityEggScutosaurus extends TileEntity implements ITickable {

		private int incubation;

		@Override
		public void update() {
			if (this.incubation <= BlockEggsScutosaurus.BlockCustom.getIncubation()) {
				++this.incubation; //increment the hidden tag up to 361 and stop there
			}
		}

		public int getIncubation() {
			return this.incubation;
		}

		public void setIncubation(int incubation) {
			this.incubation = incubation;
		}

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
		public void readFromNBT(NBTTagCompound compound) {
			super.readFromNBT(compound);
			if (compound.hasKey("incubation")) {
				this.incubation = compound.getInteger("incubation");
			}
		}

		@Override
		public NBTTagCompound writeToNBT(NBTTagCompound compound) {
			super.writeToNBT(compound);
			compound.setInteger("incubation", this.incubation);
			return compound;
		}
	}
}