package com.faboslav.villagesandpillages.common.mixin;

import com.faboslav.villagesandpillages.common.world.checks.StructureBiomeCheck;
import com.faboslav.villagesandpillages.common.world.checks.StructureCheckData;
import com.faboslav.villagesandpillages.common.world.checks.StructureFlatnessCheck;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Predicate;

//? if < 1.21.4 {
/*import net.minecraft.core.registries.Registries;
*///?}

//? if >= 26.3 {
import net.minecraft.world.level.biome.Climate;
//?}

@Mixin(Structure.class)
public class StructureMixin
{
	@WrapMethod(
		method = "generate"
	)
	private StructureStart villagesandpillages$generate(
		//? if >= 1.21.4 {
		Holder<Structure> structure,
		ResourceKey<Level> level,
		//?}
		RegistryAccess registryAccess,
		ChunkGenerator chunkGenerator,
		BiomeSource biomeSource,
		//? if >= 26.3 {
		Climate.Sampler climateSampler,
		//?}
		RandomState randomState,
		StructureTemplateManager structureTemplateManager,
		long seed,
		ChunkPos chunkPos,
		int references,
		LevelHeightAccessor heightAccessor,
		Predicate<Holder<Biome>> validBiome,
		Operation<StructureStart> original
	) {
		//? if >= 26.3 {
		var structureStart = original.call(structure, level, registryAccess, chunkGenerator, biomeSource, climateSampler, randomState, structureTemplateManager, seed, chunkPos, references, heightAccessor, validBiome);
		//?} else if >= 1.21.4 {
		/*var structureStart = original.call(structure, level, registryAccess, chunkGenerator, biomeSource, randomState, structureTemplateManager, seed, chunkPos, references, heightAccessor, validBiome);
		*///?} else {
		/*var structureStart = original.call(registryAccess, chunkGenerator, biomeSource, randomState, structureTemplateManager, seed, chunkPos, references, heightAccessor, validBiome);
		*///?}
		//? if >= 1.21.4 {
		var structureId = structure.unwrapKey().map(ResourceKey::/*? if >= 1.21.11 {*/identifier/*?} else {*//*location*//*?}*/).orElse(null);
		//?} else {
		/*var structureId = registryAccess.registryOrThrow(Registries.STRUCTURE).getResourceKey((Structure) (Object) this).map(ResourceKey::location).orElse(null);
		*///?}

		if (structureStart == StructureStart.INVALID_START || !structureStart.isValid() || structureId == null || !structureId.toString().equals("villagesandpillages:village_witch")) {
			return structureStart;
		}

		StructureCheckData structureCheckData = new StructureCheckData(structureStart);

		boolean biomeCheckResult = StructureBiomeCheck.checkBiomes(structureCheckData, biomeSource, randomState);

		if (!biomeCheckResult) {
			return StructureStart.INVALID_START;
		}

		boolean flatnessCheckResult = StructureFlatnessCheck.checkFlatness(structureCheckData, chunkGenerator, heightAccessor, randomState);

		if (!flatnessCheckResult) {
			return StructureStart.INVALID_START;
		}

		return structureStart;
	}
}
