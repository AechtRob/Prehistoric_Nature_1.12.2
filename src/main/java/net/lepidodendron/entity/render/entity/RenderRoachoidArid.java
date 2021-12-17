package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraRoachoidArid;
import net.lepidodendron.entity.model.entity.ModelRoachoid;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRoachoidArid extends RenderLiving<EntityPrehistoricFloraRoachoidArid> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/roachoid_arid.png");

    public RenderRoachoidArid(RenderManager mgr) {
        super(mgr, new ModelRoachoid(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraRoachoidArid entity) {
        return RenderRoachoidArid.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraRoachoidArid entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}