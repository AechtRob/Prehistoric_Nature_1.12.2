package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraAmmonite_Pachydesmoceras;
import net.lepidodendron.entity.model.entity.ModelAmmonite100cm;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAmmonite_Pachydesmoceras extends RenderLiving<EntityPrehistoricFloraAmmonite_Pachydesmoceras> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ammonite_pachydesmoceras.png");

    public RenderAmmonite_Pachydesmoceras(RenderManager mgr) {
        super(mgr, new ModelAmmonite100cm(), 0.65f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraAmmonite_Pachydesmoceras entity) {
        return RenderAmmonite_Pachydesmoceras.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraAmmonite_Pachydesmoceras entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}