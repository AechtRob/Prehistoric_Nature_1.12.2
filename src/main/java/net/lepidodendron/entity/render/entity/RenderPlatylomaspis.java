package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraPlatylomaspis;
import net.lepidodendron.entity.model.entity.ModelPlatylomaspis;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPlatylomaspis extends RenderLiving<EntityPrehistoricFloraPlatylomaspis> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/platylomaspis.png");

    public RenderPlatylomaspis(RenderManager mgr) {
        super(mgr, new ModelPlatylomaspis(), 0.3f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraPlatylomaspis entity) {
        return RenderPlatylomaspis.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraPlatylomaspis entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}