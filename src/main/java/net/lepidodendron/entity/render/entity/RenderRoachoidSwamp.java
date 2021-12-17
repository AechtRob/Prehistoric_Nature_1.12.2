package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraRoachoidSwamp;
import net.lepidodendron.entity.model.entity.ModelRoachoid;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRoachoidSwamp extends RenderLiving<EntityPrehistoricFloraRoachoidSwamp> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/roachoid_swamp.png");

    public RenderRoachoidSwamp(RenderManager mgr) {
        super(mgr, new ModelRoachoid(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraRoachoidSwamp entity) {
        return RenderRoachoidSwamp.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraRoachoidSwamp entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}