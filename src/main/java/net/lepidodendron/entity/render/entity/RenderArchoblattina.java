package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraArchoblattina;
import net.lepidodendron.entity.model.entity.ModelArchoblattina;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderArchoblattina extends RenderLiving<EntityPrehistoricFloraArchoblattina> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/archoblattina.png");

    public RenderArchoblattina(RenderManager mgr) {
        super(mgr, new ModelArchoblattina(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraArchoblattina entity) {
        return RenderArchoblattina.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraArchoblattina entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraArchoblattina entity, float f) {
        GlStateManager.scale(1.55, 1.55, 1.55);
    }

}