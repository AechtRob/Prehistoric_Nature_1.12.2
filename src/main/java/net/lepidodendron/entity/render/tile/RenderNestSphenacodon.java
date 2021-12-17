package net.lepidodendron.entity.render.tile;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockNestSphenacodon;
import net.lepidodendron.entity.model.tile.ModelMediumEgg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class RenderNestSphenacodon extends TileEntitySpecialRenderer<BlockNestSphenacodon.TileEntityCustom> {

    private final ModelMediumEgg medium_egg;
    private static final ResourceLocation TEXTURE_SPHENACODON_EGG = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eggs_sphenacodon.png");

    public RenderNestSphenacodon() {
        this.medium_egg = new ModelMediumEgg();
    }

    @Override
    public void render(BlockNestSphenacodon.TileEntityCustom entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        String eggRenderType = new Object() {
            public String getValue(BlockPos pos, String tag) {
                TileEntity tileEntity = entity.getWorld().getTileEntity(pos);
                if (tileEntity != null)
                    return tileEntity.getTileData().getString(tag);
                return "";
            }
        }.getValue(new BlockPos(entity.getPos()), "egg");

        if (eggRenderType.equals(LepidodendronMod.MODID + ":eggs_sphenacodon")) {

            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5F, y + 1.000F, z + 0.5F);
            GlStateManager.rotate(180, 0F, 0F, 1F);
            GlStateManager.scale(0.55F, 0.55F, 0.55F);
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_SPHENACODON_EGG);
            this.medium_egg.renderAll(0.075F);
            GlStateManager.popMatrix();

        }
    }
}