package net.lepidodendron.entity.render.tile;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockEggsDimetrodon;
import net.lepidodendron.entity.model.tile.ModelMediumEgg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderEggDimetrodon extends TileEntitySpecialRenderer<BlockEggsDimetrodon.TileEntityEggDimetrodon> {

    private final ModelMediumEgg medium_egg;
    private static final ResourceLocation TEXTURE_DIMETRODON_EGG = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eggs_dimetrodon.png");

    public RenderEggDimetrodon() {
        this.medium_egg = new ModelMediumEgg();
    }

    @Override
    public void render(BlockEggsDimetrodon.TileEntityEggDimetrodon entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        //EnumFacing facing = EnumFacing.NORTH;
        if (entity != null && entity.hasWorld()) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5F, y + 1.000F, z + 0.5F);
            GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(0.55F, 0.55F, 0.55F);
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_DIMETRODON_EGG);
            this.medium_egg.renderAll(0.075F);
            GlStateManager.popMatrix();
        }
    }
}