package net.lepidodendron.procedure;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.BlockCalamitesRhizome;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureCalamitesRhizomeUpdateTick extends ElementsLepidodendronMod.ModElement {
	public ProcedureCalamitesRhizomeUpdateTick(ElementsLepidodendronMod instance) {
		super(instance, 375);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {

		//Do nothing is sprweading is turned off:
		if (!LepidodendronConfig.spreadCalamites)
			return;

		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure CalamitesRhizomeUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure CalamitesRhizomeUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure TestCalamitesRhizomeUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure CalamitesRhizomeUpdateTick!");
			return;
		}
		int xx = (int) dependencies.get("x");
		int yy = (int) dependencies.get("y");
		int zz = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");

		//Random random = 1;

		//Is this a world-gen one that is not allowed to spread?
		if (new Object() {
			public boolean getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(new BlockPos((int) xx, (int) yy, (int) zz), "worldgen")) {
			//Do nothing if spreading is turned off:
			if (!LepidodendronConfig.spreadWorldGenCalamites)
				return;
		}

		//System.err.println("Ticked");
		double spreadSpeed = LepidodendronConfig.spreadSpeedCalamites;
		if (spreadSpeed < 0) spreadSpeed = 0;
		if (spreadSpeed > 100) spreadSpeed = 100;
		
		if (Math.random() <= ((double)spreadSpeed/100))
		{
			boolean isNetherType = false;
			//System.err.println("Tick passed");
			//Get our start point:
			int i2 = (int) (new Object() {
				public double getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(new BlockPos((int) xx, (int) yy, (int) zz), "x")) ;
			
			int k2 = (int) (new Object() {
				public double getValue(BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(new BlockPos((int) xx, (int) yy, (int) zz), "z")) ;

			//Is this the parent or the child? Can it spread?
			if ((i2 != xx) || (k2 != zz)) {
				//This is a child, can we spawn with it?
				if (!LepidodendronConfig.spreadUnlimitedCalamites)
					return;
			}

			if ((i2 == -1) && (k2 == -1)) {
				//Ignore this specific coordinate - this is a fudge....
				return;
			}
			
			//System.err.println("Trying to spawn");
			//Try to spawn:
			Random random = new Random();
			if ((random.nextInt(1000000) + 1) <= 20000) {
			int count = random.nextInt(1) + 1;
			int spreadLimit = LepidodendronConfig.spreadLimitCalamites;
			if (spreadLimit < 0) spreadLimit = 0;
			for (int a = 0; a < count; a++) {
				int i = i2 + random.nextInt(spreadLimit);
				int k = k2 + random.nextInt(spreadLimit);
				int height = 255;
				if (isNetherType) {
					boolean notpassed = true;
					while (height > 0) {
						if (notpassed && world.isAirBlock(new BlockPos(i, height, k)))
							notpassed = false;
						else if (!notpassed && !world.isAirBlock(new BlockPos(i, height, k)))
							break;
						height--;
					}
				} else {
					while (height > 0) {
						if (!world.isAirBlock(new BlockPos(i, height, k)))
							break;
						height--;
					}
				}
				int j = height;
				//IBlockState blockAt = world.getBlockState(new BlockPos(i, j + 1, k));

				//check there isn't already a tree rhizome within 5 blocks in any direction:
				
				boolean rhizomeChck = false;
				int xct = -5;
				int yct;
				int zct;
				while ((xct <= 5) && (!rhizomeChck)) {
					yct = -5;
					while ((yct <= 5) && (!rhizomeChck)) {
						zct = -5;
						while ((zct <= 5) && (!rhizomeChck)) {
							if ((world.getBlockState(new BlockPos(i + xct, j + yct, k + zct))).getBlock() == BlockCalamitesRhizome.block) {
								rhizomeChck = true;
							}
							zct = zct + 1;
						}
						yct = yct + 1;
					}
					xct = xct + 1;
				}
				
				if (rhizomeChck)
					continue;
				
				boolean blockCriteria = false;
				int waterDist = LepidodendronConfig.spreadWaterCalamites;
				if (waterDist < 0) waterDist = 0;
				if (((world.getBlockState(new BlockPos(i, j, k))).getMaterial() == Material.GRASS)
					|| ((world.getBlockState(new BlockPos(i, j, k))).getMaterial() == Material.GROUND))
					{

						boolean waterCriteria = false;
						//Is there water nearby?
						xct = -waterDist;
						while ((xct <= waterDist) && (!waterCriteria)) {
							yct = -3;
							while ((yct <= 0) && (!waterCriteria)) {
								zct = -waterDist;
								while ((zct <= waterDist) && (!waterCriteria)) {
									if ((world.getBlockState(new BlockPos(i + xct, j + yct, k + zct))).getMaterial() == Material.WATER) {
										waterCriteria = true;
									}
									zct = zct + 1;
								}
								yct = yct + 1;
							}
							xct = xct + 1;
						}
						if (waterCriteria)
							blockCriteria = true;
					}

				//Allow to spawn IN shallow water:
				if (((world.getBlockState(new BlockPos(i, j, k))).getMaterial() == Material.WATER)
					&& (((world.getBlockState(new BlockPos(i, j - 1, k))).getMaterial() == Material.SAND) 
					|| ((world.getBlockState(new BlockPos(i, j - 1, k))).getMaterial() == Material.GROUND))) {
						blockCriteria = true;
						j = j - 1;
				}
				
				if (!blockCriteria)
					continue;
				
				if (world.isRemote)
					return;
				//Template template = ((WorldServer) world).getStructureTemplateManager().getTemplate(world.getMinecraftServer(),
				//		new ResourceLocation("lepidodendron", "spawnvoid"));
				//if (template == null)
				//	return;
				//Rotation rotation = Rotation.NONE;
				//Mirror mirror = Mirror.NONE;
				BlockPos spawnTo = new BlockPos(i, j + 1, k);
				//IBlockState iblockstate = world.getBlockState(spawnTo);
				//world.notifyBlockUpdate(spawnTo, iblockstate, iblockstate, 3);
				//template.addBlocksToWorldChunk(world, spawnTo, new PlacementSettings().setRotation(rotation).setMirror(mirror)
				//		.setChunk((ChunkPos) null).setReplacedBlock((Block) null).setIgnoreStructureBlock(false).setIgnoreEntities(false));
				int x = spawnTo.getX();
				int y = spawnTo.getY();
				int z = spawnTo.getZ();

				if (!world.isBlockLoaded(spawnTo)) {
					continue;
				}
				if (!world.isAreaLoaded(spawnTo, 3)) {
					continue;
				}
				
				boolean worldgen = false;
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("x", i);
					$_dependencies.put("y", j + 1);
					$_dependencies.put("z", k);
					$_dependencies.put("world", world);
					$_dependencies.put("worldgen", worldgen);
					$_dependencies.put("parentx", xx);
					$_dependencies.put("parenty", yy);
					$_dependencies.put("parentz", zz);
					ProcedureWorldGenCalamites.executeProcedure($_dependencies);
					world.playSound((EntityPlayer) null, x, y, z,
						(net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("")), SoundCategory.NEUTRAL,
						(float) spreadLimit, (float) 1);
					//System.err.println("Tree generated! " + x + " " + y + " " + z);

				}

				}
		
			}
			
		}

		//System.err.println("Proc ended");
	}
		
		public boolean shouldGenerateInDimension(int id, int[] dims) {
		int[] var2 = dims;
		int var3 = dims.length;
		for (int var4 = 0; var4 < var3; ++var4) {
			int dim = var2[var4];
			if (dim == id) {
				return true;
			}
		}
		return false;
	}

 	public static boolean matchBiome(Biome biome, String[] biomesList) {
    	
    	//String regName = biome.getRegistryName().toString();
    	
        String[] var2 = biomesList;
        int var3 = biomesList.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String checkBiome = var2[var4];
            if (checkBiome.equalsIgnoreCase(biome.getRegistryName().toString())) {
                return true;
            }
        }

        return false;
    }
	
}
