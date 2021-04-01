package net.lepidodendron.entity.render;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraJellyfish3;
import net.lepidodendron.entity.model.ModelJellyfish;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderJellyfish3 extends RenderLiving<EntityPrehistoricFloraJellyfish3> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/jellyfish_3.png");

    public RenderJellyfish3(RenderManager mgr) {
        super(mgr, new ModelJellyfish(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraJellyfish3 entity) {
        return RenderJellyfish3.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraJellyfish3 entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}