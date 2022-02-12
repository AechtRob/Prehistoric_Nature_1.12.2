
package net.lepidodendron.item;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.block.BlockCageSmall;
import net.lepidodendron.creativetab.TabLepidodendronMisc;
import net.lepidodendron.util.ModTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

@ElementsLepidodendronMod.ModElement.Tag
public class ItemCageSmallItem extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:cage_small_item")
	public static final Item block = null;
	public ItemCageSmallItem(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.cage_small_item);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(block, 0, new ModelResourceLocation("lepidodendron:cage_small_item", "inventory"));
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			setTranslationKey("pf_cage_small_item");
			setRegistryName("cage_small_item");
			setCreativeTab(TabLepidodendronMisc.tab);
			setMaxStackSize(1);
		}


		public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	    {

			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (!block.isReplaceable(worldIn, pos))
			{
				pos = pos.offset(facing);
			}

			ItemStack itemstack = player.getHeldItem(hand);

			if (player.canPlayerEdit(pos, facing, itemstack) && BlockCageSmall.block.canPlaceBlockAt(worldIn, pos)) {
				if (placeCage(worldIn, pos, BlockCageSmall.block, itemstack, player)) {
					SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, player);
					worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					itemstack.shrink(1);
					return EnumActionResult.SUCCESS;
				}
				else {
					return EnumActionResult.FAIL;
				}
			}
			else
			{
				return EnumActionResult.FAIL;
			}

	    }

		@Override
		@SideOnly(Side.CLIENT)
		public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
		{
			if (stack.hasTagCompound()) {
				if (stack.getTagCompound().hasKey("mob_name")) {
					tooltip.add("Contains : " + stack.getTagCompound().getString("mob_name"));
				}
			}
		}
	
	    public static boolean placeCage(World worldIn, BlockPos pos, Block cage, ItemStack stack, EntityPlayer player)
	    {
			List<Entity> entity = worldIn.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos));

			//Is there an entity here?
			if (stack.hasTagCompound() && !entity.isEmpty()) {
				//No room to place this filled cage:
				return false;
			}
			if (stack.hasTagCompound()) { //by default this is now a clear space for placement:
				//Place cage containing its Entity:
				IBlockState iblockstate = cage.getDefaultState();
				worldIn.setBlockState(pos, iblockstate, 3);
				worldIn.notifyNeighborsOfStateChange(pos, cage, false);
				spawnEntityFromItemStack(stack, worldIn, pos.getX() + 0.5D, pos.getY() + 0.0625D, pos.getZ() + 0.5D);
				return true;
			}
			else //Just place the cage, if it's possible to do so:
			{
				if (!entity.isEmpty()) {
					if (entity.size() == 1) { //One entity - we can capture this:
						for (Entity currentEntity : entity) {
							double width = currentEntity.getEntityBoundingBox().maxX-currentEntity.getEntityBoundingBox().minX;
							double depth = currentEntity.getEntityBoundingBox().maxZ-currentEntity.getEntityBoundingBox().minZ;
							double height = currentEntity.getEntityBoundingBox().maxY-currentEntity.getEntityBoundingBox().minY;

							if (height <= 0.9375 && width <= 1.0 && depth <= 1.0 && (!( currentEntity instanceof MultiPartEntityPart))) {
								//Check that the mob isn't already in a neigbouring cage:
								if (worldIn.getBlockState(currentEntity.getPosition()).getBlock() != BlockCageSmall.block) {
									currentEntity.setPosition(pos.getX() + 0.5D, pos.getY() + 0.0625D, pos.getZ() + 0.5D);
									//ModTriggers.USE_CAGE.trigger((EntityPlayerMP)player);
								}
								IBlockState iblockstate = cage.getDefaultState();
								worldIn.setBlockState(pos, iblockstate, 3);
								worldIn.notifyNeighborsOfStateChange(pos, cage, false);
								return true;
							}
						}
					}
				}
				else {
					IBlockState iblockstate = cage.getDefaultState();
					worldIn.setBlockState(pos, iblockstate, 3);
					worldIn.notifyNeighborsOfStateChange(pos, cage, false);
					return true;
				}
			}
			return false;
	    }

		public static Entity spawnEntityFromItemStack(ItemStack stack, World world, double x, double y, double z) {

			if (!isEntityFromItemStack(stack)) return null;

			NBTTagCompound entityNBT = (NBTTagCompound) stack.getTagCompound().getTag("Mob");

			Entity entity = EntityList.createEntityFromNBT(entityNBT, world);

			if (entity != null) {
				entity.setLocationAndAngles(x, y, z, 0,0 );
				if (!world.isRemote) {
					world.spawnEntity(entity);
				}
			}
			return entity;
		}

		public static boolean isEntityFromItemStack(ItemStack stack) {
			if (stack.hasTagCompound() == false
					|| !stack.getTagCompound().hasKey("Mob")) return false;

			return true;
		}

	}
}
