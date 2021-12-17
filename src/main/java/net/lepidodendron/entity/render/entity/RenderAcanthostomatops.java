package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraAcanthostomatops;
import net.lepidodendron.entity.model.entity.ModelAcanthostomatops;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAcanthostomatops extends RenderLiving<EntityPrehistoricFloraAcanthostomatops> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/acanthostomatops.png");

    public RenderAcanthostomatops(RenderManager mgr) {
        super(mgr, new ModelAcanthostomatops(), 0.22f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraAcanthostomatops entity) {
        return RenderAcanthostomatops.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraAcanthostomatops entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraAcanthostomatops entity, float f) {
        float scale = entity.getAgeScale();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.15F;
    }

}