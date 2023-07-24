package com.faboslav.villagesandpillages.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;

public class StructureProcessorTypeRegistry
{
	@ExpectPlatform
	public static void registerStructureProcessorType(
		Identifier identifier,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static void postInit() {
		throw new AssertionError();
	}
}
