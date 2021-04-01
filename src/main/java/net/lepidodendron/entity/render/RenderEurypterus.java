package net.lepidodendron.entity.render;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraEurypterus;
import net.lepidodendron.entity.model.ModelEurypterus;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEurypterus extends RenderLiving<EntityPrehistoricFloraEurypterus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eurypterus.png");

    public RenderEurypterus(RenderManager mgr) {
        super(mgr, new ModelEurypterus(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraEurypterus entity) {
        return RenderEurypterus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraEurypterus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}