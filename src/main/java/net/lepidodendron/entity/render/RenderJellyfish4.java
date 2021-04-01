package net.lepidodendron.entity.render;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraJellyfish4;
import net.lepidodendron.entity.model.ModelJellyfish;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderJellyfish4 extends RenderLiving<EntityPrehistoricFloraJellyfish4> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/jellyfish_4.png");

    public RenderJellyfish4(RenderManager mgr) {
        super(mgr, new ModelJellyfish(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraJellyfish4 entity) {
        return RenderJellyfish4.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraJellyfish4 entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}