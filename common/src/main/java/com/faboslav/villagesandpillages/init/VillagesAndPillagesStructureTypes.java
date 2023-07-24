package com.faboslav.villagesandpillages.init;

import com.faboslav.villagesandpillages.platform.StructureTypeRegistry;
import com.faboslav.villagesandpillages.world.structures.VillageWitchStructure;
import net.minecraft.world.gen.structure.StructureType;

public final class VillagesAndPillagesStructureTypes
{
	public static StructureType<VillageWitchStructure> VILLAGE_WITCH_STRUCTURE = () -> VillageWitchStructure.CODEC;

	static {
		StructureTypeRegistry.registerStructureType("village_witch_structure", VILLAGE_WITCH_STRUCTURE);
	}

	public static void init() {
	}

	private VillagesAndPillagesStructureTypes() {
	}
}
