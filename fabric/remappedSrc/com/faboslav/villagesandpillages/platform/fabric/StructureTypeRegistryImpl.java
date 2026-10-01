package com.faboslav.villagesandpillages.platform.fabric;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class StructureTypeRegistryImpl
{
	public static <T extends Structure> void registerStructureType(
		String name,
		StructureType<T> structureType
	) {
		Registry.register(Registries.STRUCTURE_TYPE, VillagesAndPillages.makeID(name), structureType);
	}
}
