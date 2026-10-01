package com.faboslav.villagesandpillages.common.world.checks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.StructureStart;

public final class StructureCheckData
{
	private final StructureStart structureStart;
	private final BlockPos structureCenter;

	private int[][] structurePieceSamples = null;

	public StructureCheckData(StructureStart structureStart) {
		this.structureStart = structureStart;
		this.structureCenter = structureStart.getBoundingBox().getCenter();
	}

	public int[][] getStructurePieceSamples() {
		if (this.structurePieceSamples == null) {
			var structurePieces = StructurePieceSampler.getStructurePieces(this.structureStart);
			this.structurePieceSamples = StructurePieceSampler.getStructurePieceSamples(structurePieces, this.structureCenter);
		}

		return this.structurePieceSamples;
	}

	public BlockPos getStructureCenter() {
		return this.structureCenter;
	}
}
