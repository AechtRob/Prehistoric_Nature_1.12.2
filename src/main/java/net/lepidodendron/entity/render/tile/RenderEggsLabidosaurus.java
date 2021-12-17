package net.lepidodendron.entity.render.tile;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockEggsLabidosaurus;
import net.lepidodendron.entity.model.tile.ModelRottenLogEggs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderEggsLabidosaurus extends TileEntitySpecialRenderer<BlockEggsLabidosaurus.TileEntityEggsLabidosaurus> {

    private final ModelRottenLogEggs rotten_wood_eggs;
    private static final ResourceLocation TEXTURE_LABIDOSAURUS_EGGS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/labidosaurus_eggs.png");

    public RenderEggsLabidosaurus() {
        this.rotten_wood_eggs = new ModelRottenLogEggs();
    }

    @Override
    public void render(BlockEggsLabidosaurus.TileEntityEggsLabidosaurus entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        //EnumFacing facing = EnumFacing.NORTH;
        if (entity != null && entity.hasWorld()) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5F, y + 0.9F, z + 0.5F);
            GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_LABIDOSAURUS_EGGS);
            this.rotten_wood_eggs.renderAll(0.075F);
            GlStateManager.popMatrix();
        }
    }
}