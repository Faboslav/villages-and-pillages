package com.faboslav.villagesandpillages.common.world.processor;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

//? if < 26.2 {
/*import com.faboslav.villagesandpillages.common.init.VillagesAndPillagesProcessorTypes;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
*///?}

//? if >= 26.2 {
public class WaterlogProcessor implements StructureProcessor
//?} else {
/*public class WaterlogProcessor extends StructureProcessor
*///?}
{
	public static final MapCodec<WaterlogProcessor> CODEC = MapCodec.unit(WaterlogProcessor::new);

	@Override
	public StructureTemplate.StructureBlockInfo processBlock(
		LevelReader levelReader,
		BlockPos pos,
		BlockPos pivot,
		//? if >= 26.2 {
		BlockPos templateRelativePos,
		//?} else {
		/*StructureTemplate.StructureBlockInfo originalBlockInfo,
		*///?}
		StructureTemplate.StructureBlockInfo currentBlockInfo,
		StructurePlaceSettings structurePlaceSettings
	) {
		return currentBlockInfo;
	}

	@Override
	//? if >= 26.2 {
	public MapCodec<WaterlogProcessor> codec() {
		return CODEC;
	}
	//?} else {
	/*protected StructureProcessorType<?> getType() {
		return VillagesAndPillagesProcessorTypes.WATERLOG_PROCESSOR;
	}
	*///?}
}
