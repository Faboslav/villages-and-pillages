package com.faboslav.villagesandpillages.common.mixin;

import com.faboslav.villagesandpillages.common.api.VillageWitchTerrainAdaptationData;
import com.faboslav.villagesandpillages.common.tag.VillagesAndPillagesTags;
import com.faboslav.villagesandpillages.common.world.structures.terrain.VillageWitchTerrainAdaptation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.levelgen.Beardifier;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//? if >= 26.3 {
import net.minecraft.world.level.levelgen.densityfunction.DensityBuffer;
import net.minecraft.world.level.levelgen.densityfunction.DensityVolume;
import net.minecraft.world.level.levelgen.densityfunction.SamplerContext;
//?} else {
/*import net.minecraft.world.level.levelgen.DensityFunction;
*///?}

/**
 * Inspired by use in YUNG's API mod
 *
 * @author YUNGNICKYOUNG
 * <a href="https://github.com/YUNG-GANG/YUNGs-API">https://github.com/YUNG-GANG/YUNGs-API</a>
 */
@Mixin(Beardifier.class)
public abstract class BeardifierMixin implements VillageWitchTerrainAdaptationData
{
	@Unique
	private List<BoundingBox> villagesandpillages$pieces;

	@Unique
	private List<JigsawJunction> villagesandpillages$junctions;

	@WrapMethod(method = "forStructuresInChunk")
	private static Beardifier villagesandpillages$collectTerrainAdaptation(
		StructureManager structureManager,
		ChunkPos chunkPos,
		Operation<Beardifier> original
	) {
		Beardifier beardifier = original.call(structureManager, chunkPos);
		List<BoundingBox> pieces = new ArrayList<>();
		List<JigsawJunction> junctions = new ArrayList<>();
		int kernelRadius = VillageWitchTerrainAdaptation.getKernelRadius();
		int chunkMinBlockX = chunkPos.getMinBlockX();
		int chunkMinBlockZ = chunkPos.getMinBlockZ();

		//? if < 1.21.4 {
		/*Registry<Structure> structureRegistry = structureManager.registryAccess().registryOrThrow(Registries.STRUCTURE);
		*///?} else {
		Registry<Structure> structureRegistry = structureManager.registryAccess().lookupOrThrow(Registries.STRUCTURE);
		//?}
		Predicate<Structure> isVillageWitch = structure -> structureRegistry.wrapAsHolder(structure).is(VillagesAndPillagesTags.VILLAGE_WITCH);

		//? if >= 26.3 {
		for (StructureStart structureStart : structureManager.startsForStructure(chunkPos.x(), chunkPos.z(), isVillageWitch)) {
		//?} else {
		/*for (StructureStart structureStart : structureManager.startsForStructure(chunkPos, isVillageWitch)) {
		*///?}
			for (StructurePiece piece : structureStart.getPieces()) {
				if (!piece.isCloseToChunk(chunkPos, kernelRadius)) {
					continue;
				}

				if (piece instanceof PoolElementStructurePiece poolElementPiece) {
					if (poolElementPiece.getElement().getProjection() == StructureTemplatePool.Projection.RIGID) {
						pieces.add(poolElementPiece.getBoundingBox());
					}

					for (JigsawJunction jigsawJunction : poolElementPiece.getJunctions()) {
						int sourceX = jigsawJunction.getSourceX();
						int sourceZ = jigsawJunction.getSourceZ();

						if (
							sourceX > chunkMinBlockX - kernelRadius
							&& sourceZ > chunkMinBlockZ - kernelRadius
							&& sourceX < chunkMinBlockX + 15 + kernelRadius
							&& sourceZ < chunkMinBlockZ + 15 + kernelRadius
						) {
							junctions.add(jigsawJunction);
						}
					}
				} else {
					pieces.add(piece.getBoundingBox());
				}
			}
		}

		if (!pieces.isEmpty() || !junctions.isEmpty()) {
			//? if >= 26.3 {
			if (beardifier == Beardifier.EMPTY) {
				beardifier = new Beardifier(List.of(), List.of(), null);
			}
			//?} else if >= 1.21.9 {
			/*if (beardifier == Beardifier.EMPTY) {
				beardifier = new Beardifier(List.of(), List.of(), BoundingBox.infinite());
			}
			*///?}
			VillageWitchTerrainAdaptationData terrainAdaptationData = (VillageWitchTerrainAdaptationData) beardifier;
			terrainAdaptationData.villagesandpillages$setTerrainAdaptationPieces(pieces);
			terrainAdaptationData.villagesandpillages$setTerrainAdaptationJunctions(junctions);
		}

		return beardifier;
	}

	//? if >= 26.3 {
	@WrapMethod(method = "sampleVolume")
	private void villagesandpillages$applyTerrainAdaptation(
		SamplerContext context,
		DensityBuffer outputBuffer,
		DensityVolume volume,
		Operation<Void> original
	) {
		original.call(context, outputBuffer, volume);

		if (this.villagesandpillages$hasNoTerrainAdaptationData()) {
			return;
		}

		for (int z = 0; z < volume.sizeZ(); z++) {
			int blockZ = volume.blockZ(z);

			for (int x = 0; x < volume.sizeX(); x++) {
				int blockX = volume.blockX(x);

				for (int y = 0; y < volume.sizeY(); y++) {
					int blockY = volume.blockY(y);
					int index = volume.indexUnchecked(x, y, z);

					outputBuffer.addTo(index, (float) this.villagesandpillages$computeDensityDelta(blockX, blockY, blockZ));
				}
			}
		}
	}
	//?} else {
	/*@WrapMethod(method = "compute")
	private double villagesandpillages$applyTerrainAdaptation(
		DensityFunction.FunctionContext context,
		Operation<Double> original
	) {
		double density = original.call(context);

		if (this.villagesandpillages$hasNoTerrainAdaptationData()) {
			return density;
		}

		return density + this.villagesandpillages$computeDensityDelta(context.blockX(), context.blockY(), context.blockZ());
	}
	*///?}

	@Unique
	private boolean villagesandpillages$hasNoTerrainAdaptationData() {
		List<BoundingBox> pieces = this.villagesandpillages$pieces;
		List<JigsawJunction> junctions = this.villagesandpillages$junctions;

		return pieces == null
			|| junctions == null
			|| (pieces.isEmpty() && junctions.isEmpty());
	}

	@Unique
	private double villagesandpillages$computeDensityDelta(int x, int y, int z) {
		double delta = 0.0D;

		for (BoundingBox boundingBox : this.villagesandpillages$pieces) {
			int xDistance = Math.max(0, Math.max(boundingBox.minX() - x, x - boundingBox.maxX()));
			int yDistance = Math.max(0, Math.max(boundingBox.minY() - y, y - boundingBox.maxY()));
			int zDistance = Math.max(0, Math.max(boundingBox.minZ() - z, z - boundingBox.maxZ()));
			int yDistanceToPieceBottom = y - boundingBox.minY();

			delta += VillageWitchTerrainAdaptation.computeDensityFactor(xDistance, yDistance, zDistance, yDistanceToPieceBottom) * 0.8D;
		}

		for (JigsawJunction jigsawJunction : this.villagesandpillages$junctions) {
			int groundY = jigsawJunction.getSourceGroundY();
			int xDistance = x - jigsawJunction.getSourceX();
			int yDistance = y - groundY;
			int zDistance = z - jigsawJunction.getSourceZ();

			delta += VillageWitchTerrainAdaptation.computeDensityFactor(xDistance, yDistance, zDistance, yDistance) * 0.4D;
		}

		return delta;
	}

	@Unique
	@Override
	public void villagesandpillages$setTerrainAdaptationPieces(List<BoundingBox> pieces) {
		this.villagesandpillages$pieces = pieces;
	}

	@Unique
	@Override
	public void villagesandpillages$setTerrainAdaptationJunctions(List<JigsawJunction> junctions) {
		this.villagesandpillages$junctions = junctions;
	}
}
