package net.lepidodendron.entity.render.tile;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockEggsOphiacodon;
import net.lepidodendron.entity.model.tile.ModelRottenLogEggs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderEggsOphiacodon extends TileEntitySpecialRenderer<BlockEggsOphiacodon.TileEntityEggsOphiacodon> {

    private final ModelRottenLogEggs rotten_wood_eggs;
    private static final ResourceLocation TEXTURE_OPHIACODON_EGGS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ophiacodon_eggs.png");

    public RenderEggsOphiacodon() {
        this.rotten_wood_eggs = new ModelRottenLogEggs();
    }

    @Override
    public void render(BlockEggsOphiacodon.TileEntityEggsOphiacodon entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        //EnumFacing facing = EnumFacing.NORTH;
        if (entity != null && entity.hasWorld()) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5F, y + 0.9F, z + 0.5F);
            GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_OPHIACODON_EGGS);
            this.rotten_wood_eggs.renderAll(0.075F);
            GlStateManager.popMatrix();
        }
    }
}