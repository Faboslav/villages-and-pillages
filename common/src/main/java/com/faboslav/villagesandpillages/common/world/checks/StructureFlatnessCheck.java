package com.faboslav.villagesandpillages.common.world.checks;

import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;

public final class StructureFlatnessCheck
{
	public static boolean checkFlatness(
		StructureCheckData structureCheckData,
		ChunkGenerator chunkGenerator,
		LevelHeightAccessor heightAccessor,
		RandomState randomState
	) {
		var structurePieceSamples = structureCheckData.getStructurePieceSamples();
		int maxHeightDifference = 24;

		int totalFlatnessChecks = structurePieceSamples.length;
		int minHeight = Integer.MAX_VALUE;
		int maxHeight = Integer.MIN_VALUE;

		for (int currentFlatnessCheck = 0; currentFlatnessCheck < totalFlatnessChecks; currentFlatnessCheck++) {
			int x = structurePieceSamples[currentFlatnessCheck][0];
			int z = structurePieceSamples[currentFlatnessCheck][1];

			int firstOceanFloorOccupiedHeight = chunkGenerator.getFirstOccupiedHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState);

			if (currentFlatnessCheck == 0) {
				minHeight = firstOceanFloorOccupiedHeight;
				maxHeight = firstOceanFloorOccupiedHeight;
			}

			if (firstOceanFloorOccupiedHeight > maxHeight) {
				maxHeight = firstOceanFloorOccupiedHeight;
				if (maxHeight - minHeight > maxHeightDifference) {
					return false;
				}
			}

			if (firstOceanFloorOccupiedHeight < minHeight) {
				minHeight = firstOceanFloorOccupiedHeight;
				if (maxHeight - minHeight > maxHeightDifference) {
					return false;
				}
			}
		}

		return true;
	}
}