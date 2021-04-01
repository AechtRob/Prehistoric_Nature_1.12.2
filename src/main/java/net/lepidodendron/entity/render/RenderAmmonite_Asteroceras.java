package net.lepidodendron.entity.render;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraAmmonite_Asteroceras;
import net.lepidodendron.entity.model.ModelAcanthodes;
import net.lepidodendron.entity.model.ModelAmmonite50cm;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAmmonite_Asteroceras extends RenderLiving<EntityPrehistoricFloraAmmonite_Asteroceras> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ammonite_asteroceras.png");

    public RenderAmmonite_Asteroceras(RenderManager mgr) {
        super(mgr, new ModelAmmonite50cm(), 0.2f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraAmmonite_Asteroceras entity) {
        return RenderAmmonite_Asteroceras.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraAmmonite_Asteroceras entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}