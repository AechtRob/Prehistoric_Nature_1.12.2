package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraKaibabvenator;
import net.lepidodendron.entity.model.entity.ModelKaibabvenator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderKaibabvenator extends RenderLiving<EntityPrehistoricFloraKaibabvenator> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/kaibabvenator.png");

    public RenderKaibabvenator(RenderManager mgr) {
        super(mgr, new ModelKaibabvenator(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraKaibabvenator entity) {
        return RenderKaibabvenator.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraKaibabvenator entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraKaibabvenator entity, float f) {
        float scale = entity.getAgeScale();
        if (scale < 0.1f) {scale = 0.1f;}
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.55F;
    }

}