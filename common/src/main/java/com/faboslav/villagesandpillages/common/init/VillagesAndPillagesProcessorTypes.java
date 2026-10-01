package com.faboslav.villagesandpillages.common.init;

import com.faboslav.villagesandpillages.common.platform.PlatformHooks;
import com.faboslav.villagesandpillages.common.world.processor.*;

//? if < 26.2 {
/*import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
*///?}

/**
 * @see StructureProcessorType
 */
public final class VillagesAndPillagesProcessorTypes
{
	//? if < 26.2 {
		/*//? if >= 1.21.1 {
		public static StructureProcessorType<PillarProcessor> PILLAR_PROCESSOR = () -> PillarProcessor.CODEC;
		public static StructureProcessorType<CollapsedUnderwaterProcessor> COLLAPSED_UNDERWATER_PROCESSOR = () -> CollapsedUnderwaterProcessor.CODEC;
		public static StructureProcessorType<VillageWitchOpenedDoorProcessor> VILLAGE_WITCH_OPENED_DOOR_PROCESSOR = () -> VillageWitchOpenedDoorProcessor.CODEC;
		public static StructureProcessorType<VillageWitchBrewingStandProcessor> VILLAGE_WITCH_BREWING_STAND_PROCESSOR = () -> VillageWitchBrewingStandProcessor.CODEC;
		public static StructureProcessorType<VillageWitchFlowerPotProcessor> VILLAGE_WITCH_FLOWER_POT_PROCESSOR = () -> VillageWitchFlowerPotProcessor.CODEC;
		public static StructureProcessorType<WaterlogProcessor> WATERLOG_PROCESSOR = () -> WaterlogProcessor.CODEC;
		//?} else {
		/^public static StructureProcessorType<PillarProcessor> PILLAR_PROCESSOR = () -> PillarProcessor.CODEC.codec();
		public static StructureProcessorType<CollapsedUnderwaterProcessor> COLLAPSED_UNDERWATER_PROCESSOR = () -> CollapsedUnderwaterProcessor.CODEC.codec();
		public static StructureProcessorType<VillageWitchOpenedDoorProcessor> VILLAGE_WITCH_OPENED_DOOR_PROCESSOR = () -> VillageWitchOpenedDoorProcessor.CODEC.codec();
		public static StructureProcessorType<VillageWitchBrewingStandProcessor> VILLAGE_WITCH_BREWING_STAND_PROCESSOR = () -> VillageWitchBrewingStandProcessor.CODEC.codec();
		public static StructureProcessorType<VillageWitchFlowerPotProcessor> VILLAGE_WITCH_FLOWER_POT_PROCESSOR = () -> VillageWitchFlowerPotProcessor.CODEC.codec();
		public static StructureProcessorType<WaterlogProcessor> WATERLOG_PROCESSOR = () -> WaterlogProcessor.CODEC.codec();
		^///?}
	*///?}

	public static void init() {
		//? if >= 26.2 {
		PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("pillar_processor", PillarProcessor.CODEC);
		PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("collapsed_underwater_processor", CollapsedUnderwaterProcessor.CODEC);
		PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("village_witch_opened_door_processor", VillageWitchOpenedDoorProcessor.CODEC);
		PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("village_witch_brewing_stand_processor", VillageWitchBrewingStandProcessor.CODEC);
		PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("village_witch_flower_pot_processor", VillageWitchFlowerPotProcessor.CODEC);
		PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("waterlog_processor", WaterlogProcessor.CODEC);
		//?} else {
		/*PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("pillar_processor", PILLAR_PROCESSOR);
		PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("collapsed_underwater_processor", COLLAPSED_UNDERWATER_PROCESSOR);
		PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("village_witch_opened_door_processor", VILLAGE_WITCH_OPENED_DOOR_PROCESSOR);
		PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("village_witch_brewing_stand_processor", VILLAGE_WITCH_BREWING_STAND_PROCESSOR);
		PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("village_witch_flower_pot_processor", VILLAGE_WITCH_FLOWER_POT_PROCESSOR);
		PlatformHooks.STRUCTURE_PROCESSOR_TYPES.registerStructureProcessorType("waterlog_processor", WATERLOG_PROCESSOR);
		*///?}
	}

	private VillagesAndPillagesProcessorTypes() {
	}
}
