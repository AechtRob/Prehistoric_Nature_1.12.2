package net.lepidodendron.procedure;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockMacroneuropterisLeaves;
import net.lepidodendron.block.BlockMacroneuropterisLeaves1;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureMacroneuropterisStrobilusNeighbourBlockChanges extends ElementsLepidodendronMod.ModElement {
	public ProcedureMacroneuropterisStrobilusNeighbourBlockChanges(ElementsLepidodendronMod instance) {
		super(instance, 1182);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MacroneuropterisStrobilusNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MacroneuropterisStrobilusNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MacroneuropterisStrobilusNeighbourBlockChanges!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MacroneuropterisStrobilusNeighbourBlockChanges!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((!((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == BlockMacroneuropterisLeaves1.block.getDefaultState()
				.getBlock()))
				&& ((!((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == BlockMacroneuropterisLeaves.block
						.getDefaultState().getBlock())) && ((new Object() {
							public boolean getValue(BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getBoolean(tag);
								return false;
							}
						}.getValue(new BlockPos((int) x, (int) y, (int) z), "decayable")) == (true))))) {
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
		}
	}
}
