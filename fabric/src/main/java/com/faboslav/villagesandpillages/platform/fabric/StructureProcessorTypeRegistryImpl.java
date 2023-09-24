package com.faboslav.villagesandpillages.platform.fabric;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;

public class StructureProcessorTypeRegistryImpl
{
	public static void registerStructureProcessorType(
		String name,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	) {
		Registry.register(Registries.STRUCTURE_PROCESSOR, VillagesAndPillages.makeID(name), structureProcessorType);
	}
}
