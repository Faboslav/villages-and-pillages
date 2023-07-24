package com.faboslav.villagesandpillages.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

public class StructureTypeRegistry
{
	@ExpectPlatform
	public static <T extends Structure> void registerStructureType(
		String name,
		StructureType<T> structureType
	) {
		throw new AssertionError();
	}
}
