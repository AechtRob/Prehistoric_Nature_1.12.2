package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraTitanichthys;
import net.lepidodendron.entity.model.entity.ModelTitanicthys;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTitanichthys extends RenderLiving<EntityPrehistoricFloraTitanichthys> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/titanichthys.png");
    private static final ResourceLocation TEXTURE_BABY = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/titanichthys_baby.png");

    public RenderTitanichthys(RenderManager mgr) {
        super(mgr, new ModelTitanicthys(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraTitanichthys entity) {
        float scale = entity.getAgeScale();
        //System.err.println("AgeScale: " + scale);
        if (scale < 0.2F) {
            return RenderTitanichthys.TEXTURE_BABY;
        }
        return RenderTitanichthys.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraTitanichthys entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraTitanichthys entity, float f) {
        float scale = entity.getAgeScale();
        if (scale < 0.1f) {scale = 0.1f;}
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.45F;
    }

}