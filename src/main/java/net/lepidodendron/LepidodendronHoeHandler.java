package net.lepidodendron;

import net.lepidodendron.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LepidodendronHoeHandler {

    @SubscribeEvent
    public void useHoe(UseHoeEvent event)
    {
        if (event.getResult() != Event.Result.DEFAULT || event.isCanceled())
        {
            return;
        }

        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        boolean result = false;

        //Toggle rippled blocks etc:
        if (state == Blocks.GRAVEL.getStateFromMeta(0) || block == BlockGravelSticky.block)
        {
            result = true;
            world.setBlockState(pos, BlockGravelWavy.block.getDefaultState());
        }
        else {
            if (block == BlockGravelWavy.block || block == BlockGravelWavySticky.block)
            {
                result = true;
                world.setBlockState(pos, Blocks.GRAVEL.getStateFromMeta(0));
            }
        }

        if (state == Blocks.SAND.getStateFromMeta(0) || block == BlockSandSticky.block)
        {
            result = true;
            world.setBlockState(pos, BlockSandWavy.block.getDefaultState());
        }
        else {
            if (block == BlockSandWavy.block || block == BlockSandWavySticky.block)
            {
                result = true;
                world.setBlockState(pos, Blocks.SAND.getStateFromMeta(0));
            }
        }

        if (state == Blocks.SAND.getStateFromMeta(1) || block == BlockSandRedSticky.block)
        {
            result = true;
            world.setBlockState(pos, BlockSandRedWavy.block.getDefaultState());
        }
        else {
            if (block == BlockSandRedWavy.block || block == BlockSandRedWavySticky.block)
            {
                result = true;
                world.setBlockState(pos, Blocks.SAND.getStateFromMeta(1));
            }
        }

        if (block == BlockSandPangaean.block || block == BlockSandPangaeanSticky.block)
        {
            result = true;
            world.setBlockState(pos, BlockSandPangaeanWavy.block.getDefaultState());
        }
        else {
            if (block == BlockSandPangaeanWavy.block || block == BlockSandPangaeanWavySticky.block)
            {
                result = true;
                world.setBlockState(pos, BlockSandPangaean.block.getDefaultState());
            }
        }

        if (((block == BlockPrehistoricGroundLush.block)
                || (block == BlockPrehistoricGroundBasic.block)
                || (block == BlockPrehistoricGroundSand.block)
                || (block == BlockPrehistoricGroundSandRed.block)
                || (block == BlockPrehistoricGroundSandPangaean.block)
                || (block == BlockCoarseSandyDirtPangaean.block)
                || (block == BlockCoarseSandyDirt.block)
                || (block == BlockCoarseSandyDirtRed.block)
            )
                && world.isAirBlock(pos.up()))
        {
            //result = true;
            //if () //Is Coarse
            //{
            //    world.setBlockState(pos, non-coarse-dirt-type); //Change to non-coarse
            //} else
            //{
                //world.setBlockState(pos, farmland-type); //Change to farmland
            //}
        }

        if (result)
        {
            event.setResult(Event.Result.ALLOW);

            world.playSound(event.getEntityPlayer(), pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            event.getEntityPlayer().swingArm(getHandForItemAndMeta(event.getEntityPlayer(), event.getCurrent().getItem(), event.getCurrent().getMetadata()));
            event.getCurrent().damageItem(1, event.getEntityPlayer());
        }
    }

    private static EnumHand getHandForItemAndMeta(EntityPlayer player, Item item, int meta)
    {
        for (EnumHand hand : EnumHand.values())
        {
            ItemStack heldStack = player.getHeldItem(hand);

            if (!heldStack.isEmpty() && heldStack.getItem() == item && heldStack.getMetadata() == meta)
                return hand;
        }

        return EnumHand.MAIN_HAND;
    }
}