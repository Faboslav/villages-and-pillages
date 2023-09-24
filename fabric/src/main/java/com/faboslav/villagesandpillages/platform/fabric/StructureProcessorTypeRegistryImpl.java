package com.faboslav.villagesandpillages.platform.fabric;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.registry.Registry;

public class StructureProcessorTypeRegistryImpl
{
	public static void registerStructureProcessorType(
		String name,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	) {
		Registry.register(Registry.STRUCTURE_PROCESSOR, VillagesAndPillages.makeID(name), structureProcessorType);
	}
}
