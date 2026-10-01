package com.faboslav.villagesandpillages.common.platform;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;

//? if >= 26.2 {
import com.mojang.serialization.MapCodec;
//?}

//? if < 26.2 {
/*import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
*///?}

public interface StructureProcessorTypes
{
	//? if >= 26.2 {
	void registerStructureProcessorType(
		String name,
		MapCodec<? extends StructureProcessor> structureProcessorCodec
	);
	//?} else {
	/*void registerStructureProcessorType(
		String name,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	);
	*///?}
}
