
package net.lepidodendron.block;

import net.lepidodendron.LepidodendronConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockFirePF extends BlockFire {

	@Override
	public int tickRate(World worldIn)
	{
		if (worldIn.provider.getDimension() == LepidodendronConfig.dimCarboniferous) {
			return 10;
		}
		return 30;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		if (worldIn.provider.getDimension() == LepidodendronConfig.dimCarboniferous) {
			return worldIn.getBlockState(pos.down()).isTopSolid() || this.canNeighborCatchFire(worldIn, pos)
				|| worldIn.getBlockState(pos.down()).getBlock().isFlammable(worldIn, pos.down(), EnumFacing.UP)
				|| worldIn.getBlockState(pos.up()).getBlock().isFlammable(worldIn, pos.up(), EnumFacing.DOWN);
		}
		return worldIn.getBlockState(pos.down()).isTopSolid() || this.canNeighborCatchFire(worldIn, pos);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (worldIn.provider.getDimension() == LepidodendronConfig.dimCarboniferous) {
			if (worldIn.getGameRules().getBoolean("doFireTick"))
			{
				if (!worldIn.isAreaLoaded(pos, 2)) return; // Forge: prevent loading unloaded chunks when spreading fire
				if (!this.canPlaceBlockAt(worldIn, pos))
				{
					worldIn.setBlockToAir(pos);
				}

				Block block = worldIn.getBlockState(pos.down()).getBlock();
				boolean flag = block.isFireSource(worldIn, pos.down(), EnumFacing.UP);

				int i = ((Integer)state.getValue(AGE)).intValue();

				if (!flag && worldIn.isRaining() && this.canDie(worldIn, pos) && rand.nextFloat() < 0.1F + (float)i * 0.02F)
				{
					worldIn.setBlockToAir(pos);
				}
				else
				{
					if (i < 8)
					{
						state = state.withProperty(AGE, Integer.valueOf(i + rand.nextInt(3) / 2));
						worldIn.setBlockState(pos, state, 4);
					}

					worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + rand.nextInt(5));

					if (!flag)
					{
						//if (!this.canNeighborCatchFire(worldIn, pos))
						//{
						//	if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP))
						//	{
						//		worldIn.setBlockToAir(pos);
						//	}

						//	return;
						//}

						if (!this.canCatchFire(worldIn, pos.down(), EnumFacing.UP) && i == 8 && rand.nextInt(40) == 0)
						{
							worldIn.setBlockToAir(pos);
							return;
						}
					}

					boolean flag1 = worldIn.isBlockinHighHumidity(pos);
					int j = 0;

					if (flag1)
					{
						j = -50;
					}

					this.tryCatchFire(worldIn, pos.east(), 600 + j, rand, i, EnumFacing.WEST);
					this.tryCatchFire(worldIn, pos.west(), 600 + j, rand, i, EnumFacing.EAST);
					this.tryCatchFire(worldIn, pos.down(), 500 + j, rand, i, EnumFacing.UP);
					this.tryCatchFire(worldIn, pos.up(), 1000 + j, rand, i, EnumFacing.DOWN);
					this.tryCatchFire(worldIn, pos.north(), 600 + j, rand, i, EnumFacing.SOUTH);
					this.tryCatchFire(worldIn, pos.south(), 600 + j, rand, i, EnumFacing.NORTH);

					for (int k = -2; k <= 2; ++k)
					{
						for (int l = -2; l <= 2; ++l)
						{
							for (int i1 = -1; i1 <= 4; ++i1)
							{
								if (k != 0 || i1 != 0 || l != 0)
								{
									int j1 = 100;

									if (i1 > 1)
									{
										j1 += (i1 - 1) * 100;
									}

									BlockPos blockpos = pos.add(k, i1, l);
									int k1 = this.getNeighborEncouragement(worldIn, blockpos);

									if (k1 > 0)
									{
										int l1 = (k1 + 40 + worldIn.getDifficulty().getId() * 7) / (i + 30);

										if (flag1)
										{
											l1 /= 10;
										}

										if (l1 > 0 && rand.nextInt(j1) <= l1 && (!worldIn.isRaining() || !this.canDie(worldIn, blockpos)))
										{
											int i2 = i + rand.nextInt(5) / 4;

											if (i2 > 8)
											{
												i2 = 8;
											}

											worldIn.setBlockState(blockpos, state.withProperty(AGE, Integer.valueOf(i2)), 3);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		else if (worldIn.getGameRules().getBoolean("doFireTick"))
		{
			if (!worldIn.isAreaLoaded(pos, 2)) return; // Forge: prevent loading unloaded chunks when spreading fire
			if (!this.canPlaceBlockAt(worldIn, pos))
			{
				worldIn.setBlockToAir(pos);
			}

			Block block = worldIn.getBlockState(pos.down()).getBlock();
			boolean flag = block.isFireSource(worldIn, pos.down(), EnumFacing.UP);

			int i = ((Integer)state.getValue(AGE)).intValue();

			if (!flag && worldIn.isRaining() && this.canDie(worldIn, pos) && rand.nextFloat() < 0.2F + (float)i * 0.03F)
			{
				worldIn.setBlockToAir(pos);
			}
			else
			{
				if (i < 15)
				{
					state = state.withProperty(AGE, Integer.valueOf(i + rand.nextInt(3) / 2));
					worldIn.setBlockState(pos, state, 4);
				}

				worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + rand.nextInt(10));

				if (!flag)
				{
					if (!this.canNeighborCatchFire(worldIn, pos))
					{
						if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP) || i > 3)
						{
							worldIn.setBlockToAir(pos);
						}

						return;
					}

					if (!this.canCatchFire(worldIn, pos.down(), EnumFacing.UP) && i == 15 && rand.nextInt(4) == 0)
					{
						worldIn.setBlockToAir(pos);
						return;
					}
				}

				boolean flag1 = worldIn.isBlockinHighHumidity(pos);
				int j = 0;

				if (flag1)
				{
					j = -50;
				}

				this.tryCatchFire(worldIn, pos.east(), 600 + j, rand, i, EnumFacing.WEST);
				this.tryCatchFire(worldIn, pos.west(), 600 + j, rand, i, EnumFacing.EAST);
				this.tryCatchFire(worldIn, pos.down(), 500 + j, rand, i, EnumFacing.UP);
				this.tryCatchFire(worldIn, pos.up(), 500 + j, rand, i, EnumFacing.DOWN);
				this.tryCatchFire(worldIn, pos.north(), 600 + j, rand, i, EnumFacing.SOUTH);
				this.tryCatchFire(worldIn, pos.south(), 600 + j, rand, i, EnumFacing.NORTH);

				for (int k = -1; k <= 1; ++k)
				{
					for (int l = -1; l <= 1; ++l)
					{
						for (int i1 = -1; i1 <= 4; ++i1)
						{
							if (k != 0 || i1 != 0 || l != 0)
							{
								int j1 = 100;

								if (i1 > 1)
								{
									j1 += (i1 - 1) * 100;
								}

								BlockPos blockpos = pos.add(k, i1, l);
								int k1 = this.getNeighborEncouragement(worldIn, blockpos);

								if (k1 > 0)
								{
									int l1 = (k1 + 40 + worldIn.getDifficulty().getId() * 7) / (i + 30);

									if (flag1)
									{
										l1 /= 2;
									}

									if (l1 > 0 && rand.nextInt(j1) <= l1 && (!worldIn.isRaining() || !this.canDie(worldIn, blockpos)))
									{
										int i2 = i + rand.nextInt(5) / 4;

										if (i2 > 15)
										{
											i2 = 15;
										}

										worldIn.setBlockState(blockpos, state.withProperty(AGE, Integer.valueOf(i2)), 3);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private void tryCatchFire(World worldIn, BlockPos pos, int chance, Random random, int age, EnumFacing face)
	{
		int i = worldIn.getBlockState(pos).getBlock().getFlammability(worldIn, pos, face);

		if (worldIn.provider.getDimension() == LepidodendronConfig.dimCarboniferous)
		{
			i = i * 10;
		}

		if (random.nextInt(chance) < i)
		{
			IBlockState iblockstate = worldIn.getBlockState(pos);

			int q = 10;
			if (worldIn.provider.getDimension() == LepidodendronConfig.dimCarboniferous) {
				q = 5;
			}

			if (random.nextInt(age + q) < 5 && !worldIn.isRainingAt(pos))
			{
				int j = age + random.nextInt(5) / 4;

				int p = 15;
				if (worldIn.provider.getDimension() == LepidodendronConfig.dimCarboniferous) {
					p = 8;
				}
				if (j > p)
				{
					j = p;
				}

				worldIn.setBlockState(pos, this.getDefaultState().withProperty(AGE, Integer.valueOf(j)), 3);
			}
			else
			{
				worldIn.setBlockToAir(pos);
			}

			if (iblockstate.getBlock() == Blocks.TNT)
			{
				Blocks.TNT.onPlayerDestroy(worldIn, pos, iblockstate.withProperty(BlockTNT.EXPLODE, Boolean.valueOf(true)));
			}
		}
	}

	private boolean canNeighborCatchFire(World worldIn, BlockPos pos)
	{
		for (EnumFacing enumfacing : EnumFacing.values())
		{
			if (this.canCatchFire(worldIn, pos.offset(enumfacing), enumfacing.getOpposite()))
			{
				return true;
			}
		}

		return false;
	}

	private int getNeighborEncouragement(World worldIn, BlockPos pos)
	{
		if (!worldIn.isAirBlock(pos))
		{
			return 0;
		}
		else
		{
			int i = 0;

			for (EnumFacing enumfacing : EnumFacing.values())
			{
				i = Math.max(worldIn.getBlockState(pos.offset(enumfacing)).getBlock().getFireSpreadSpeed(worldIn, pos.offset(enumfacing), enumfacing.getOpposite()), i);
			}

			if (worldIn.provider.getDimension() == LepidodendronConfig.dimCarboniferous) {
				i = i * 10;
			}
			return i;
		}
	}

}
