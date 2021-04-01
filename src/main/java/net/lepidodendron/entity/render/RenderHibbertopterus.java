package net.lepidodendron.entity.render;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraHibbertopterus;
import net.lepidodendron.entity.model.ModelHibbertopterus;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHibbertopterus extends RenderLiving<EntityPrehistoricFloraHibbertopterus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/hibbertopterus.png");

    public RenderHibbertopterus(RenderManager mgr) {
        super(mgr, new ModelHibbertopterus(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraHibbertopterus entity) {
        return RenderHibbertopterus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraHibbertopterus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}