package net.lepidodendron.entity.render.tile;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockEggsProcynosuchus;
import net.lepidodendron.entity.model.tile.ModelSmallEgg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderEggProcynosuchus extends TileEntitySpecialRenderer<BlockEggsProcynosuchus.TileEntityEggProcynosuchus> {

    private final ModelSmallEgg small_egg;
    private static final ResourceLocation TEXTURE_Procynosuchus_EGG = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eggs_procynosuchus.png");

    public RenderEggProcynosuchus() {
        this.small_egg = new ModelSmallEgg();
    }

    @Override
    public void render(BlockEggsProcynosuchus.TileEntityEggProcynosuchus entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        //EnumFacing facing = EnumFacing.NORTH;
        if (entity != null && entity.hasWorld()) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5F, y + 0.55F, z + 0.5F);
            GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(0.3F, 0.3F, 0.3F);
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_Procynosuchus_EGG);
            this.small_egg.renderAll(0.075F);
            GlStateManager.popMatrix();
        }
    }
}