package com.faboslav.villagesandpillages.common.world.checks;

import com.faboslav.villagesandpillages.common.tag.VillagesAndPillagesTags;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.RandomState;

public final class StructureBiomeCheck
{
	public static boolean checkBiomes(
		StructureCheckData structureCheckData,
		BiomeSource biomeSource,
		RandomState randomState
	) {
		int blockY = structureCheckData.getStructureCenter().getY();
		int sampleQuartY = QuartPos.fromBlock(blockY);
		//? if >= 26.3 {
		var resolver = biomeSource.createCachingResolver(randomState);
		//?} else {
		/*var sampler = randomState.sampler();
		*///?}

		for (int[] pos : structureCheckData.getStructurePieceSamples()) {
			int blockX = pos[0];
			int blockZ = pos[1];

			int quartX = QuartPos.fromBlock(blockX);
			int quartZ = QuartPos.fromBlock(blockZ);

			//? if >= 26.3 {
			Holder<Biome> biome = resolver.getNoiseBiome(quartX, sampleQuartY, quartZ);
			//?} else {
			/*Holder<Biome> biome = biomeSource.getNoiseBiome(quartX, sampleQuartY, quartZ, sampler);
			*///?}

			if (!biome.is(VillagesAndPillagesTags.HAS_VILLAGE_WITCH)) {
				return false;
			}
		}

		return true;
	}
}