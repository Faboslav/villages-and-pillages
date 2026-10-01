package com.faboslav.villagesandpillages.neoforge.platform;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;

//? if >= 26.2 {
import com.mojang.serialization.MapCodec;
//?}

//? if < 26.2 {
/*import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
*///?}

public final class StructureProcessorTypes implements com.faboslav.villagesandpillages.common.platform.StructureProcessorTypes
{
	@Override
	//? if >= 26.2 {
	public void registerStructureProcessorType(
		String name,
		MapCodec<? extends StructureProcessor> structureProcessorCodec
	) {
		StructureProcessorTypeRegistryImpl.registerStructureProcessorType(name, structureProcessorCodec);
	}
	//?} else {
	/*public void registerStructureProcessorType(
		String name,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	) {
		StructureProcessorTypeRegistryImpl.registerStructureProcessorType(name, structureProcessorType);
	}
	*///?}
}
