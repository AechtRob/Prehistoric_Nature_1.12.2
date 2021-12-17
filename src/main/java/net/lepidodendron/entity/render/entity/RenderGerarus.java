package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraGerarus;
import net.lepidodendron.entity.model.entity.ModelGerarus;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGerarus extends RenderLiving<EntityPrehistoricFloraGerarus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/gerarus.png");

    public RenderGerarus(RenderManager mgr) {
        super(mgr, new ModelGerarus(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraGerarus entity) {
        return RenderGerarus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraGerarus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}