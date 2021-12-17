package net.lepidodendron.entity.render.tile;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockNestDimetrodon;
import net.lepidodendron.entity.model.tile.ModelMediumEgg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class RenderNestDimetrodon extends TileEntitySpecialRenderer<BlockNestDimetrodon.TileEntityCustom> {

    private final ModelMediumEgg medium_egg;
    private static final ResourceLocation TEXTURE_DIMETRODON_EGG = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eggs_dimetrodon.png");

    public RenderNestDimetrodon() {
        this.medium_egg = new ModelMediumEgg();
    }

    @Override
    public void render(BlockNestDimetrodon.TileEntityCustom entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        String eggRenderType = new Object() {
            public String getValue(BlockPos pos, String tag) {
                TileEntity tileEntity = entity.getWorld().getTileEntity(pos);
                if (tileEntity != null)
                    return tileEntity.getTileData().getString(tag);
                return "";
            }
        }.getValue(new BlockPos(entity.getPos()), "egg");

        if (eggRenderType.equals(LepidodendronMod.MODID + ":eggs_dimetrodon")) {

            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5F, y + 1.000F, z + 0.5F);
            GlStateManager.rotate(180, 0F, 0F, 1F);
            GlStateManager.scale(0.55F, 0.55F, 0.55F);
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_DIMETRODON_EGG);
            this.medium_egg.renderAll(0.075F);
            GlStateManager.popMatrix();

        }
    }
}