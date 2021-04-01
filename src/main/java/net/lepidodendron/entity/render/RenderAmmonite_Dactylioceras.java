package net.lepidodendron.entity.render;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraAmmonite_Dactylioceras;
import net.lepidodendron.entity.model.ModelAcanthodes;
import net.lepidodendron.entity.model.ModelAmmonite15cm;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAmmonite_Dactylioceras extends RenderLiving<EntityPrehistoricFloraAmmonite_Dactylioceras> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ammonite_dactylioceras.png");

    public RenderAmmonite_Dactylioceras(RenderManager mgr) {
        super(mgr, new ModelAmmonite15cm(), 0.1f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraAmmonite_Dactylioceras entity) {
        return RenderAmmonite_Dactylioceras.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraAmmonite_Dactylioceras entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}