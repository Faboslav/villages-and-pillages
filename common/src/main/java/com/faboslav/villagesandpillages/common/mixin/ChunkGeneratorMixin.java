package com.faboslav.villagesandpillages.common.mixin;

import com.faboslav.villagesandpillages.common.tag.VillagesAndPillagesTags;
import com.faboslav.villagesandpillages.common.world.structures.terrain.VillageWitchTerrainAdaptation;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin
{
	@WrapOperation(
		method = "createReferences",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/levelgen/structure/StructureStart;getBoundingBox()Lnet/minecraft/world/level/levelgen/structure/BoundingBox;"
		)
	)
	private BoundingBox villagesandpillages$inflateTerrainAdaptationBoundingBox(
		StructureStart structureStart,
		Operation<BoundingBox> original,
		@Local(argsOnly = true) WorldGenLevel worldGenLevel
	) {
		BoundingBox boundingBox = original.call(structureStart);

		//? if < 1.21.4 {
		/*Registry<Structure> structureRegistry = worldGenLevel.registryAccess().registryOrThrow(Registries.STRUCTURE);
		*///?} else {
		Registry<Structure> structureRegistry = worldGenLevel.registryAccess().lookupOrThrow(Registries.STRUCTURE);
		//?}

		if (!structureRegistry.wrapAsHolder(structureStart.getStructure()).is(VillagesAndPillagesTags.VILLAGE_WITCH)) {
			return boundingBox;
		}

		return boundingBox.inflatedBy(VillageWitchTerrainAdaptation.getKernelRadius());
	}
}
