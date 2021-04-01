package net.lepidodendron.entity.render;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraCyrtoceras;
import net.lepidodendron.entity.model.ModelCyrtoceras;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCyrtoceras extends RenderLiving<EntityPrehistoricFloraCyrtoceras> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/cyrtoceras.png");

    public RenderCyrtoceras(RenderManager mgr) {
        super(mgr, new ModelCyrtoceras(), 0.1f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraCyrtoceras entity) {
        return RenderCyrtoceras.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraCyrtoceras entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}