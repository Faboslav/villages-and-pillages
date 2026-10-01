package com.faboslav.villagesandpillages.common.world.processor;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

//? if < 26.2 {
/*import com.faboslav.villagesandpillages.common.init.VillagesAndPillagesProcessorTypes;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
*///?}

//? if >= 26.2 {
public class VillageWitchFlowerPotProcessor implements StructureProcessor
//?} else {
/*public class VillageWitchFlowerPotProcessor extends StructureProcessor
*///?}
{
	public static final MapCodec<VillageWitchFlowerPotProcessor> CODEC = MapCodec.unit(VillageWitchFlowerPotProcessor::new);

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
		if (currentBlockInfo.state().is(Blocks.FLOWER_POT) == false) {
			return currentBlockInfo;
		}

		//? if >= 26.2 {
		RandomSource random = structurePlaceSettings.getRandom(templateRelativePos);
		//?} else {
		/*RandomSource random = structurePlaceSettings.getRandom(originalBlockInfo.pos());
		*///?}

		return new StructureTemplate.StructureBlockInfo(
			currentBlockInfo.pos(),
			this.getRandomFlowerPot(random),
			null
		);
	}

	private BlockState getRandomFlowerPot(RandomSource random) {
		float value = random.nextFloat();

		if (value < 0.20F) {
			return Blocks.POTTED_DEAD_BUSH.defaultBlockState();
		}

		if (value < 0.35F) {
			return Blocks.POTTED_BLUE_ORCHID.defaultBlockState();
		}

		if (value < 0.50F) {
			return Blocks.POTTED_FLOWERING_AZALEA.defaultBlockState();
		}

		if (value < 0.60F) {
			return Blocks.POTTED_BROWN_MUSHROOM.defaultBlockState();
		}

		if (value < 0.70F) {
			return Blocks.POTTED_RED_MUSHROOM.defaultBlockState();
		}

		return Blocks.FLOWER_POT.defaultBlockState();
	}

	@Override
	//? if >= 26.2 {
	public MapCodec<VillageWitchFlowerPotProcessor> codec() {
		return CODEC;
	}
	//?} else {
	/*protected StructureProcessorType<?> getType() {
		return VillagesAndPillagesProcessorTypes.VILLAGE_WITCH_FLOWER_POT_PROCESSOR;
	}
	*///?}
}
