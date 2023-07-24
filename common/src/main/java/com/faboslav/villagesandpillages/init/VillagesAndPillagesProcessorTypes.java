package com.faboslav.villagesandpillages.init;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import com.faboslav.villagesandpillages.platform.StructureProcessorTypeRegistry;
import com.faboslav.villagesandpillages.world.processor.*;
import net.minecraft.structure.processor.StructureProcessorType;

/**
 * @see StructureProcessorType
 */
public final class VillagesAndPillagesProcessorTypes
{
	public static StructureProcessorType<PillarProcessor> PILLAR_PROCESSOR = () -> PillarProcessor.CODEC;
	public static StructureProcessorType<CollapsedUnderwaterProcessor> COLLAPSED_UNDERWATER_PROCESSOR = () -> CollapsedUnderwaterProcessor.CODEC;
	public static StructureProcessorType<VillageWitchBrewingStandProcessor> VILLAGE_WITCH_BREWING_STAND_PROCESSOR = () -> VillageWitchBrewingStandProcessor.CODEC;
	public static StructureProcessorType<VillageWitchFlowerPotProcessor> VILLAGE_WITCH_FLOWER_POT_PROCESSOR = () -> VillageWitchFlowerPotProcessor.CODEC;
	public static StructureProcessorType<WaterlogProcessor> WATERLOG_PROCESSOR = () -> WaterlogProcessor.CODEC;

	public static void init() {
		VillagesAndPillagesStructureTypes.init();
	}

	public static void postInit() {
		StructureProcessorTypeRegistry.registerStructureProcessorType(VillagesAndPillages.makeID("pillar_processor"), PILLAR_PROCESSOR);
		StructureProcessorTypeRegistry.registerStructureProcessorType(VillagesAndPillages.makeID("collapsed_underwater_processor"), COLLAPSED_UNDERWATER_PROCESSOR);
		StructureProcessorTypeRegistry.registerStructureProcessorType(VillagesAndPillages.makeID("village_witch_brewing_stand_processor"), VILLAGE_WITCH_BREWING_STAND_PROCESSOR);
		StructureProcessorTypeRegistry.registerStructureProcessorType(VillagesAndPillages.makeID("village_witch_flower_pot_processor"), VILLAGE_WITCH_FLOWER_POT_PROCESSOR);
		StructureProcessorTypeRegistry.registerStructureProcessorType(VillagesAndPillages.makeID("waterlog_processor"), WATERLOG_PROCESSOR);
		StructureProcessorTypeRegistry.postInit();
	}

	private VillagesAndPillagesProcessorTypes() {
	}
}
