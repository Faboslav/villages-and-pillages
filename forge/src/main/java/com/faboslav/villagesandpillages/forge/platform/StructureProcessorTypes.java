package com.faboslav.villagesandpillages.forge.platform;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public final class StructureProcessorTypes implements com.faboslav.villagesandpillages.common.platform.StructureProcessorTypes
{
	@Override
	public void registerStructureProcessorType(
		String name,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	) {
		StructureProcessorTypeRegistryImpl.registerStructureProcessorType(name, structureProcessorType);
	}
}
