package com.faboslav.villagesandpillages.fabric.platform;

import com.faboslav.villagesandpillages.common.VillagesAndPillages;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
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
		Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, VillagesAndPillages.makeId(name), structureProcessorCodec);
	}
	//?} else {
	/*public void registerStructureProcessorType(
		String name,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	) {
		Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, VillagesAndPillages.makeId(name), structureProcessorType);
	}
	*///?}
}
