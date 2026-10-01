package com.faboslav.villagesandpillages.common.world.processor;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.util.RandomSource;

//? if < 26.2 {
/*import com.faboslav.villagesandpillages.common.init.VillagesAndPillagesProcessorTypes;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
*///?}

//? if >= 26.2 {
public final class VillageWitchOpenedDoorProcessor implements StructureProcessor
//?} else {
/*public final class VillageWitchOpenedDoorProcessor extends StructureProcessor
*///?}
{
	public static final MapCodec<VillageWitchOpenedDoorProcessor> CODEC = MapCodec.unit(VillageWitchOpenedDoorProcessor::new);

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
		Block block = currentBlockInfo.state().getBlock();
		if (
			block instanceof DoorBlock == false
			|| block.defaultMapColor() != Blocks.SPRUCE_PLANKS.defaultMapColor()
		) {
			return currentBlockInfo;
		}

		BlockPos.MutableBlockPos doorBlockPos = currentBlockInfo.pos().mutable();
		BlockState doorBlockState = currentBlockInfo.state();
		DoubleBlockHalf doubleBlockHalf = doorBlockState.getValue(DoorBlock.HALF);

		BlockPos.MutableBlockPos lowerDoorBlockPos = doorBlockPos.mutable();

		if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
			lowerDoorBlockPos.move(Direction.DOWN);
		}

		RandomSource random = structurePlaceSettings.getRandom(lowerDoorBlockPos);
		boolean isOpened = random.nextFloat() > 0.8F;

		return new StructureTemplate.StructureBlockInfo(
			doorBlockPos,
			doorBlockState.setValue(DoorBlock.OPEN, isOpened),
			null
		);
	}

	@Override
	//? if >= 26.2 {
	public MapCodec<VillageWitchOpenedDoorProcessor> codec() {
		return CODEC;
	}
	//?} else {
	/*protected StructureProcessorType<?> getType() {
		return VillagesAndPillagesProcessorTypes.VILLAGE_WITCH_OPENED_DOOR_PROCESSOR;
	}
	*///?}
}
