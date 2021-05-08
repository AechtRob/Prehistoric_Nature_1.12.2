package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraTitanicthys;
import net.lepidodendron.entity.model.entity.ModelTitanicthys;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTitanicthys extends RenderLiving<EntityPrehistoricFloraTitanicthys> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/titanichthys.png");

    public RenderTitanicthys(RenderManager mgr) {
        super(mgr, new ModelTitanicthys(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraTitanicthys entity) {
        return RenderTitanicthys.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraTitanicthys entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}