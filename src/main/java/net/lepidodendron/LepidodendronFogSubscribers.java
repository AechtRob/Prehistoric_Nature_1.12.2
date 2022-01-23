package net.lepidodendron;

import net.lepidodendron.util.EnumBiomeTypeCarboniferous;
import net.lepidodendron.util.EnumBiomeTypeJurassic;
import net.lepidodendron.util.EnumBiomeTypePermian;
import net.lepidodendron.world.biome.carboniferous.BiomeCarboniferous;
import net.lepidodendron.world.biome.devonian.BiomeDevonianSpikes;
import net.lepidodendron.world.biome.jurassic.BiomeJurassic;
import net.lepidodendron.world.biome.permian.BiomePermian;
import net.lepidodendron.world.biome.permian.BiomePermianHighlands;
import net.lepidodendron.world.biome.permian.BiomePermianMountains;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LepidodendronFogSubscribers {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onEvent(EntityViewRenderEvent.FogDensity event) {

		if (LepidodendronConfig.doFog) {

			//EntityPlayer player = Minecraft.getMinecraft().player;

			Block b = event.getState().getBlock();
			Entity player = event.getEntity();
			Biome biome = player.world.getBiome(player.getPosition());

			int fogBottom = 78;
			int fogTop = 98;
			float backgroundFog = 0.0025F;
			float fullFogAddition = 0.0985F;
			float fog = 0;
			float fog1 = 0;

			if (event.getEntity().world.isRemote && (player instanceof EntityPlayer)) {
				if (event.getEntity().world.provider.getDimension() == LepidodendronConfig.dimPrecambrian
						|| event.getEntity().world.provider.getDimension() == LepidodendronConfig.dimCambrian
						|| event.getEntity().world.provider.getDimension() == LepidodendronConfig.dimOrdovicianSilurian
						|| event.getEntity().world.provider.getDimension() == LepidodendronConfig.dimDevonian
						|| event.getEntity().world.provider.getDimension() == LepidodendronConfig.dimCarboniferous
						|| event.getEntity().world.provider.getDimension() == LepidodendronConfig.dimPermian
						|| event.getEntity().world.provider.getDimension() == LepidodendronConfig.dimTriassic
						|| event.getEntity().world.provider.getDimension() == LepidodendronConfig.dimJurassic
				) {
					if (!(player instanceof EntityLivingBase && ((EntityLivingBase) player).isPotionActive(MobEffects.BLINDNESS))) {
						if (!((b instanceof BlockLiquid) || (b instanceof BlockFluidBase) || event.getState().getMaterial() == Material.WATER)) {

							if ((!(b instanceof BlockLiquid)) && (!(b instanceof BlockFluidBase)) && event.getState().getMaterial() != Material.WATER
									&& biome == BiomeDevonianSpikes.biome && player.posY > player.world.getSeaLevel()) {
								fogBottom = 120;
								fogTop = 175;
								fog = backgroundFog + (fullFogAddition * ((float) (Math.min(fogTop - fogBottom, Math.max(0, player.posY - fogBottom)) / (fogTop - fogBottom))));
							} else if ((!(b instanceof BlockLiquid)) && (!(b instanceof BlockFluidBase)) && event.getState().getMaterial() != Material.WATER
									&& (biome == BiomePermianMountains.biome || biome == BiomePermianHighlands.biome) && player.posY >= player.world.getSeaLevel() - 4
									&& player.posY <= fogTop) {
								fog = backgroundFog + (fullFogAddition * ((float) (Math.min(fogTop - fogBottom, Math.max(0, player.posY - fogBottom)) / (fogTop - fogBottom))));
							} else if ((!(b instanceof BlockLiquid)) && (!(b instanceof BlockFluidBase)) && event.getState().getMaterial() != Material.WATER
									&& (biome == BiomePermianMountains.biome || biome == BiomePermianHighlands.biome) && player.posY >= player.world.getSeaLevel() - 4
									&& player.posY > fogTop) {
								int fogTopFree = 125;
								fog = backgroundFog + fullFogAddition - (fullFogAddition * ((float) (Math.min(fogTopFree - fogTop, Math.max(0, player.posY - fogTop)) / (fogTopFree - fogTop))));
							} else if ((!(b instanceof BlockLiquid)) && (!(b instanceof BlockFluidBase)) && event.getState().getMaterial() != Material.WATER
									&& biome instanceof BiomePermian && player.posY >= player.world.getSeaLevel() - 4) {
								BiomePermian biomePermian = (BiomePermian) biome;
								if (biomePermian.getBiomeType() == EnumBiomeTypePermian.Wetlands
										|| biomePermian.getBiomeType() == EnumBiomeTypePermian.Glossopteris) {
									fog = backgroundFog * 7F;
								}
							} else if ((!(b instanceof BlockLiquid)) && (!(b instanceof BlockFluidBase) && event.getState().getMaterial() != Material.WATER)
									&& biome instanceof BiomeCarboniferous && player.posY >= player.world.getSeaLevel() - 4) {
								BiomeCarboniferous biomeCarboniferous = (BiomeCarboniferous) biome;
								if (biomeCarboniferous.getBiomeType() == EnumBiomeTypeCarboniferous.Swamp) {
									fog = backgroundFog * 7F;
								}
							} else if ((!(b instanceof BlockLiquid)) && (!(b instanceof BlockFluidBase)) && event.getState().getMaterial() != Material.WATER
									&& biome instanceof BiomeJurassic && player.posY >= player.world.getSeaLevel() - 4) {
								BiomeJurassic biomeJurassic = (BiomeJurassic) biome;
								if (biomeJurassic.getBiomeType() == EnumBiomeTypeJurassic.Redwood) {
									fog = backgroundFog * 4F;
								}
							}

							if (player.world.provider.doesXZShowFog((int) player.posX, (int) player.posZ) && player.posY > player.world.getSeaLevel()) {
								fog1 = backgroundFog + fullFogAddition;
								if (player.world.isRainingAt(new BlockPos(player.getPosition()))) {
									float d = player.world.rainingStrength;
									fog1 = fog1 * d;
								}
							} else if (player.posY < player.world.getSeaLevel() - 4) {
								fog1 = backgroundFog * 2F;
								fog = backgroundFog * 2F;
							} else {
								fog1 = backgroundFog;
							}

							double doFog = Math.max((double) fog, (double) fog1);
							GlStateManager.setFog(GlStateManager.FogMode.EXP2);
							//System.err.println("Render fog: " + doFog);
							event.setDensity((float) doFog);
							event.setCanceled(true);
						}
					}
				}
			}
		}
	}
}
