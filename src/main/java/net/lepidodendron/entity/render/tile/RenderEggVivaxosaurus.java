package net.lepidodendron.entity.render.tile;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.block.BlockEggsVivaxosaurus;
import net.lepidodendron.entity.model.tile.ModelSmallEgg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderEggVivaxosaurus extends TileEntitySpecialRenderer<BlockEggsVivaxosaurus.TileEntityEggVivaxosaurus> {

    private final ModelSmallEgg small_egg;
    private static final ResourceLocation TEXTURE_VIVAXOSAURUS_EGG = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eggs_vivaxosaurus.png");

    public RenderEggVivaxosaurus() {
        this.small_egg = new ModelSmallEgg();
    }

    @Override
    public void render(BlockEggsVivaxosaurus.TileEntityEggVivaxosaurus entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        //EnumFacing facing = EnumFacing.NORTH;
        if (entity != null && entity.hasWorld()) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5F, y + 0.9F, z + 0.5F);
            GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_VIVAXOSAURUS_EGG);
            this.small_egg.renderAll(0.075F);
            GlStateManager.popMatrix();
        }
    }
}