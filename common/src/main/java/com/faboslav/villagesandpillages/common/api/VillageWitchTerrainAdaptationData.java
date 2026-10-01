package com.faboslav.villagesandpillages.common.api;

import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;

import java.util.List;

/**
 * Inspired by use in YUNG's API mod
 *
 * @author YUNGNICKYOUNG
 * <a href="https://github.com/YUNG-GANG/YUNGs-API">https://github.com/YUNG-GANG/YUNGs-API</a>
 */
public interface VillageWitchTerrainAdaptationData
{
	void villagesandpillages$setTerrainAdaptationPieces(List<BoundingBox> pieces);

	void villagesandpillages$setTerrainAdaptationJunctions(List<JigsawJunction> junctions);
}
