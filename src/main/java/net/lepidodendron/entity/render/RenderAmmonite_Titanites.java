package net.lepidodendron.entity.render;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraAmmonite_Titanites;
import net.lepidodendron.entity.model.ModelAcanthodes;
import net.lepidodendron.entity.model.ModelAmmonite150cm;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAmmonite_Titanites extends RenderLiving<EntityPrehistoricFloraAmmonite_Titanites> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ammonite_titanites.png");

    public RenderAmmonite_Titanites(RenderManager mgr) {
        super(mgr, new ModelAmmonite150cm(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraAmmonite_Titanites entity) {
        return RenderAmmonite_Titanites.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraAmmonite_Titanites entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}