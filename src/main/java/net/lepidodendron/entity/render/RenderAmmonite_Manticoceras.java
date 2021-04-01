package net.lepidodendron.entity.render;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraAmmonite_Manticoceras;
import net.lepidodendron.entity.model.ModelAcanthodes;
import net.lepidodendron.entity.model.ModelAmmonite50cm;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAmmonite_Manticoceras extends RenderLiving<EntityPrehistoricFloraAmmonite_Manticoceras> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ammonite_manticoceras.png");

    public RenderAmmonite_Manticoceras(RenderManager mgr) {
        super(mgr, new ModelAmmonite50cm(), 0.2f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraAmmonite_Manticoceras entity) {
        return RenderAmmonite_Manticoceras.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraAmmonite_Manticoceras entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}