
package net.lepidodendron.world.structure;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.BlockLavaRock;
import net.lepidodendron.block.BlockVolcanicAsh;
import net.lepidodendron.block.BlockVolcanicAshDark;
import net.lepidodendron.block.BlockVolcanicAshLight;
import net.lepidodendron.world.biome.BiomeCambrianBiome;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class StructureSpawnCambrianVolcano extends ElementsLepidodendronMod.ModElement {
	public StructureSpawnCambrianVolcano(ElementsLepidodendronMod instance) {
		super(instance, 48);
	}

	@Override
	public void generateWorld(Random random, int i2, int k2, World world, int dimID, IChunkGenerator cg, IChunkProvider cp) {
		if (dimID != LepidodendronConfig.dimCambrian)
			return;
		Biome biome = world.getBiome(new BlockPos(i2, world.getSeaLevel(), k2));
		if (biome != BiomeCambrianBiome.biome)
			return;

		int GenChance = 30000;

		if ((random.nextInt(1000000) + 1) <= GenChance) {
			int count = random.nextInt(1) + 1;
			for (int a = 0; a < count; a++) {
				int i = i2 + random.nextInt(16) + 8;
				int k = k2 + random.nextInt(16) + 8;
				int height = 255;
				while (height > 0) {
					if (
						(!world.isAirBlock(new BlockPos(i, height, k)))
								&& ((world.getBlockState(new BlockPos(i, height, k))).getMaterial() != Material.VINE)
								&& ((world.getBlockState(new BlockPos(i, height, k))).getMaterial() != Material.WEB)
								&& ((world.getBlockState(new BlockPos(i, height, k))).getMaterial() != Material.PLANTS)
								&& ((world.getBlockState(new BlockPos(i, height, k))).getMaterial() != Material.WATER)
					)
						break;
					height--;
				}

				int j = height;

				if (world.isRemote)
					return;

				BlockPos pos = new BlockPos(i, height + 1, k);
				int coneRadius = random.nextInt(30) + 30;
				int coneHeight = random.nextInt(30) + 30;
				int lavaRadius = random.nextInt(10) + 5;

				if (coneHeight - lavaRadius < j || coneHeight - lavaRadius < coneRadius || !world.canSeeSky(pos.add(0, coneHeight - lavaRadius, 0))) {
					return;
				}

				System.err.println("Volcano: " + pos.getX() + " " + pos.getZ());

				int yct = 1;
				int xct = -(coneRadius - 1);
				boolean doBlock = true;
				while (yct <= coneHeight - lavaRadius) {

					while (xct <= coneRadius) {
						int zct = -coneRadius;
						while (zct <= (coneRadius - 1)) {
							doBlock = true;
							if (yct > 1 && Math.random() > 0.02
									&& (Math.pow((int) Math.abs(xct), 2) + Math.pow((int) Math.abs(zct), 2) == Math.pow((int) coneRadius, 2))) {
								doBlock = false;
							}
							if (yct > 1 && world.getBlockState(new BlockPos(pos.getX() + xct, yct - 1, pos.getZ() + zct)).getBlock() != BlockLavaRock.block) {
								doBlock = false;
							}

							if (doBlock && Math.pow((int) Math.abs(xct), 2) + Math.pow((int) Math.abs(zct), 2) <= Math.pow((int) coneRadius, 2)) {
								BlockPos bp = new BlockPos(pos.getX() + xct, yct, pos.getZ() + zct);
								if (Math.random() > 0.06) {
									world.setBlockState(bp, BlockLavaRock.block.getDefaultState(), 2);
								} else { //Ashes:
									double randomiser = Math.random();
									if (randomiser > 0.66) {
										world.setBlockState(bp, BlockVolcanicAsh.block.getDefaultState(), 2);
									} else {
										if (Math.random() > 0.33) {
											world.setBlockState(bp, BlockVolcanicAshLight.block.getDefaultState(), 2);
										} else {
											world.setBlockState(bp, BlockVolcanicAshDark.block.getDefaultState(), 2);
										}
									}
								}
							}
							zct = zct + 1;
						}
						xct = xct + 1;
					}
				yct = yct + 1;
				}
			}
		}
	}
}
