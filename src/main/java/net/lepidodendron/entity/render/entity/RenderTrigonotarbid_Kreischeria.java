package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraTrigonotarbid_Kreischeria;
import net.lepidodendron.entity.model.entity.ModelTrigonotarbid;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTrigonotarbid_Kreischeria extends RenderLiving<EntityPrehistoricFloraTrigonotarbid_Kreischeria> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/trigonotarbid_2.png");

    public RenderTrigonotarbid_Kreischeria(RenderManager mgr) {
        super(mgr, new ModelTrigonotarbid(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraTrigonotarbid_Kreischeria entity) {
        return RenderTrigonotarbid_Kreischeria.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraTrigonotarbid_Kreischeria entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
        switch (entityLiving.getClimbingFacing()) {
            case DOWN:
            default:
                break;
            case EAST:
                //GlStateManager.translate(0.25F, 0.5F, 0.0F);
                GlStateManager.translate(0.18F, 0.5F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case WEST:
                //GlStateManager.translate(-0.25F, 0.5F, 0.0F);
                GlStateManager.translate(-0.18F, 0.5F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case NORTH:
                //GlStateManager.translate(0.0F, 0.5F, -0.25F);
                GlStateManager.translate(0.0F, 0.5F, -0.18F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                break;
            case SOUTH:
                //GlStateManager.translate(0.0F, 0.5F, 0.25F);
                GlStateManager.translate(0.0F, 0.5F, 0.18F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                break;
            case UP:
                GlStateManager.translate(0.0F, 0.5F, 0.0F);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        }

    }

}