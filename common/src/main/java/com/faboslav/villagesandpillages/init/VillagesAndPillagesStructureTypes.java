package com.faboslav.villagesandpillages.init;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import com.faboslav.villagesandpillages.platform.StructureTypeRegistry;
import com.faboslav.villagesandpillages.world.structures.VillageWitchStructure;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public final class VillagesAndPillagesStructureTypes
{
	public static final TagKey<Structure> VILLAGE_WITCH = TagKey.of(RegistryKeys.STRUCTURE,
		Identifier.of(VillagesAndPillages.MOD_ID, "village_witch"));
	public static StructureType<VillageWitchStructure> VILLAGE_WITCH_STRUCTURE = () -> VillageWitchStructure.CODEC;

	static {
		StructureTypeRegistry.registerStructureType("village_witch_structure", VILLAGE_WITCH_STRUCTURE);
	}

	public static void init() {
	}

	private VillagesAndPillagesStructureTypes() {
	}
}
