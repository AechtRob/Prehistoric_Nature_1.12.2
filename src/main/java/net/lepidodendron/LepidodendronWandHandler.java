package net.lepidodendron;

import net.lepidodendron.enchantments.Enchantments;
import net.lepidodendron.item.ItemBoneWand;
import net.lepidodendron.item.ItemPetrified;
import net.lepidodendron.util.ModTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LepidodendronWandHandler {

    @SubscribeEvent
    public void useWand(PlayerInteractEvent.EntityInteract event)
    {

        EntityPlayer player = event.getEntityPlayer();
        Entity e = event.getTarget();
        World world = event.getWorld();

        if ((!(world.isRemote)) && (e instanceof EntityItem) && (player.getHeldItemMainhand().getItem() == new ItemStack(ItemBoneWand.block, (int) (1)).getItem())) {
            //Right clicked on dropped item with a bone wand:

            EntityItem ee = (EntityItem) e;
            ItemStack itemstack = ee.getItem();
            if (itemstack.getItem() instanceof ItemPetrified) { //The dropped item is a stack containing petrifieds

                ItemStack wandstack = player.getHeldItem(EnumHand.MAIN_HAND);
                int levelEnchantment = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(Enchantments.TIME_REVERSAL, wandstack);
                //System.err.println(levelEnchantment);
                if (levelEnchantment > 0) {
                    if (!itemstack.isEmpty()) {
                        Item item = itemstack.getItem();
                        ItemPetrified itemPetrified = (ItemPetrified) item;
                        //Create a real plant from this dropped on the ground in the same place:
                        if (itemPetrified.getPlantStack() != null) {
                            EntityItem entityToSpawn = new EntityItem(world, e.posX, e.posY, e.posZ, new ItemStack(itemPetrified.getPlantStack().getItem(), (int) (1)));
                            entityToSpawn.setPickupDelay(10);
                            world.spawnEntity(entityToSpawn);
                            itemstack.shrink(1);
                            world.addWeatherEffect(new EntityLightningBolt(world, (int) e.getPosition().getX(), (int) e.getPosition().getY(), (int) e.getPosition().getZ(), true));
                            if (!player.capabilities.isCreativeMode) {
                                wandstack.damageItem(1, player);
                            }
                            ModTriggers.REJUVENATE.trigger((EntityPlayerMP) player);
                        }
                    }
                }
            }
        }
        else if ((!(world.isRemote)) && (e instanceof EntityItemFrame) && (player.getHeldItemMainhand().getItem() == new ItemStack(ItemBoneWand.block, (int) (1)).getItem())) {
            //Right clicked on item frame with a bone wand:

            ItemStack wandstack = player.getHeldItem(EnumHand.MAIN_HAND);
            int levelEnchantment = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(Enchantments.TIME_REVERSAL, wandstack);
            //System.err.println(levelEnchantment);
            if (levelEnchantment > 0) {

                EntityItemFrame Frame = (EntityItemFrame) e;
                ItemStack itemstack = Frame.getDisplayedItem();

                if (!itemstack.isEmpty()) {
                    Item item = itemstack.getItem();
                    if (item instanceof ItemPetrified) {
                        ItemPetrified itemPetrified = (ItemPetrified) item;

                        //System.err.println(itemPetrified.getPlantStack().getItem());

                        //Create a real plant from this in the frame:
                        Frame.setItemRotation(7);
                        if (itemPetrified.getPlantStack() != null) {
                            Frame.setDisplayedItem(itemPetrified.getPlantStack());
                            world.addWeatherEffect(new EntityLightningBolt(world, (int) e.getPosition().getX(), (int) e.getPosition().getY(), (int) e.getPosition().getZ(), true));
                            if (!player.capabilities.isCreativeMode) {
                                wandstack.damageItem(1, player);
                            }
                            ModTriggers.REJUVENATE.trigger((EntityPlayerMP)player);
                        }
                    }
                }
            }
        }
    }
}