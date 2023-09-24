package com.faboslav.villagesandpillages.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;

public class StructureProcessorTypeRegistry
{
	@ExpectPlatform
	public static void registerStructureProcessorType(
		String name,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	) {
		throw new AssertionError();
	}
}
