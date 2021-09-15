package net.lepidodendron.entity.render.tile;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockEggsHylonomus;
import net.lepidodendron.entity.model.tile.ModelHylonomusEggs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderEggsHylonomus extends TileEntitySpecialRenderer<BlockEggsHylonomus.TileEntityCustom> {

    private final ModelHylonomusEggs hylonomus_eggs;
    private static final ResourceLocation TEXTURE_HYLONOMUS_EGGS = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/hylonomus_eggs.png");

    public RenderEggsHylonomus() {
        this.hylonomus_eggs = new ModelHylonomusEggs();
    }

    @Override
    public void render(BlockEggsHylonomus.TileEntityCustom entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        //EnumFacing facing = EnumFacing.NORTH;
        if (entity != null && entity.hasWorld()) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5F, y + 0.9F, z + 0.5F);
            GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_HYLONOMUS_EGGS);
            this.hylonomus_eggs.renderAll(0.075F);
            GlStateManager.popMatrix();
        }
    }
}