package com.faboslav.villagesandpillages.common.tag;

import com.faboslav.villagesandpillages.common.VillagesAndPillages;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;

public final class VillagesAndPillagesTags
{
	public static final TagKey<Biome> HAS_VILLAGE_WITCH = biomeTag("has_structure/village_witch");
	public static final TagKey<Structure> VILLAGE_WITCH = TagKey.create(Registries.STRUCTURE, VillagesAndPillages.makeId("village_witch"));

	private static TagKey<Biome> biomeTag(String name) {
		return TagKey.create(Registries.BIOME, VillagesAndPillages.makeId(name));
	}

	public static void init() {
	}
}
