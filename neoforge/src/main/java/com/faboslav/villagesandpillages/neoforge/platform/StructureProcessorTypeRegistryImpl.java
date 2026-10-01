package com.faboslav.villagesandpillages.neoforge.platform;

import com.faboslav.villagesandpillages.common.VillagesAndPillages;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.neoforged.neoforge.registries.DeferredRegister;

//? if >= 26.2 {
import com.mojang.serialization.MapCodec;
//?}

//? if < 26.2 {
/*import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
*///?}

public class StructureProcessorTypeRegistryImpl
{
	//? if >= 26.2 {
	public static final DeferredRegister<MapCodec<? extends StructureProcessor>> STRUCTURE_PROCESSOR_TYPES = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, VillagesAndPillages.MOD_ID);

	public static void registerStructureProcessorType(
		String name,
		MapCodec<? extends StructureProcessor> structureProcessorCodec
	) {
		STRUCTURE_PROCESSOR_TYPES.register(name, () -> structureProcessorCodec);
	}
	//?} else {
	/*public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR_TYPES = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, VillagesAndPillages.MOD_ID);

	public static void registerStructureProcessorType(
		String name,
		StructureProcessorType<? extends StructureProcessor> structureProcessorType
	) {
		STRUCTURE_PROCESSOR_TYPES.register(name, () -> structureProcessorType);
	}
	*///?}
}
