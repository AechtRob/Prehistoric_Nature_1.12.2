package net.lepidodendron.entity.render.tile;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockNestProcynosuchus;
import net.lepidodendron.entity.model.tile.ModelSmallEgg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class RenderNestProcynosuchus extends TileEntitySpecialRenderer<BlockNestProcynosuchus.TileEntityCustom> {

    private final ModelSmallEgg small_egg;
    private static final ResourceLocation TEXTURE_Procynosuchus_EGG = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eggs_procynosuchus.png");

    public RenderNestProcynosuchus() {
        this.small_egg = new ModelSmallEgg();
    }

    @Override
    public void render(BlockNestProcynosuchus.TileEntityCustom entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        String eggRenderType = new Object() {
            public String getValue(BlockPos pos, String tag) {
                TileEntity tileEntity = entity.getWorld().getTileEntity(pos);
                if (tileEntity != null)
                    return tileEntity.getTileData().getString(tag);
                return "";
            }
        }.getValue(new BlockPos(entity.getPos()), "egg");

        if (eggRenderType.equals(LepidodendronMod.MODID + ":eggs_procynosuchus")) {

            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5F, y + 0.55F, z + 0.5F);
            GlStateManager.rotate(180, 0F, 0F, 1F);
            GlStateManager.scale(0.3F, 0.3F, 0.3F);
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_Procynosuchus_EGG);
            this.small_egg.renderAll(0.075F);
            GlStateManager.popMatrix();
        }
    }
}