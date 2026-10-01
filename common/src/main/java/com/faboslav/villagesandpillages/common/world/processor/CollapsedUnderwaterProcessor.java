package com.faboslav.villagesandpillages.common.world.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.util.RandomSource;

//? if < 26.2 {
/*import com.faboslav.villagesandpillages.common.init.VillagesAndPillagesProcessorTypes;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
*///?}

/**
 * Inspired by use in Better Fortresses mod
 *
 * @author YUNGNICKYOUNG
 * <a href="https://github.com/YUNG-GANG/YUNGs-Better-Fortresses">https://github.com/YUNG-GANG/YUNGs-Better-Fortresses</a>
 */
//? if >= 26.2 {
public final class CollapsedUnderwaterProcessor implements StructureProcessor
//?} else {
/*public final class CollapsedUnderwaterProcessor extends StructureProcessor
*///?}
{
	public static final MapCodec<CollapsedUnderwaterProcessor> CODEC = RecordCodecBuilder.mapCodec(instance -> instance
		.group(
			BlockState.CODEC.fieldOf("target_block").forGetter(config -> config.targetBlock),
			BlockState.CODEC.fieldOf("target_block_output").forGetter(config -> config.targetBlockOutput),
			Direction.CODEC.optionalFieldOf("direction", Direction.DOWN).forGetter(processor -> processor.direction),
			Codec.INT.optionalFieldOf("pillar_length", -1).forGetter(config -> config.length))
		.apply(instance, instance.stable(CollapsedUnderwaterProcessor::new)));

	public final BlockState targetBlock;
	public final BlockState targetBlockOutput;
	public final Direction direction;
	public final int length;

	private CollapsedUnderwaterProcessor(
		BlockState targetBlock,
		BlockState targetBlockOutput,
		Direction direction,
		int length
	) {
		this.targetBlock = targetBlock;
		this.targetBlockOutput = targetBlockOutput;
		this.direction = direction;
		this.length = length;
	}

	@Override
	public StructureTemplate.StructureBlockInfo processBlock(
		LevelReader levelReader,
		BlockPos pos,
		BlockPos pivot,
		//? if >= 26.2 {
		BlockPos templateRelativePos,
		//?} else {
		/*StructureTemplate.StructureBlockInfo blockInfoLocal,
		*///?}
		StructureTemplate.StructureBlockInfo blockInfoGlobal,
		StructurePlaceSettings structurePlaceSettings
	) {
		if (blockInfoGlobal.state().is(this.targetBlock.getBlock())) {
			if (
				levelReader instanceof WorldGenRegion worldGenRegion
				//? if >= 26.1 {
				&& !worldGenRegion.getCenter().equals(ChunkPos.containing(blockInfoGlobal.pos()))
				//?} else {
				/*&& !worldGenRegion.getCenter().equals(new ChunkPos(blockInfoGlobal.pos()))
				*///?}
			) {
				return blockInfoGlobal;
			}

			blockInfoGlobal = new StructureTemplate.StructureBlockInfo(
				blockInfoGlobal.pos(),
				Blocks.AIR.defaultBlockState(),
				blockInfoGlobal.nbt()
			);

			BlockPos.MutableBlockPos mutable = blockInfoGlobal.pos().mutable().move(this.direction);
			BlockState currentBlockState = levelReader.getBlockState(mutable);
			RandomSource random = structurePlaceSettings.getRandom(blockInfoGlobal.pos());
			int worldBottomY;
			int worldTopY;
			//? if >= 1.21.3 {
			worldBottomY = levelReader.getMinY();
			worldTopY = levelReader.getMaxY();
			//?} else {
			/*worldBottomY = levelReader.getMinBuildHeight();
			worldTopY = levelReader.getMaxBuildHeight();
			*///?}

			while (
				mutable.getY() > worldBottomY
				&& mutable.getY() < worldTopY
				&& (currentBlockState.isAir() || !levelReader.getFluidState(mutable).isEmpty())
				&& (this.length == -1 || blockInfoGlobal.pos().distManhattan(mutable) <= this.length)
			) {
				if (
					currentBlockState.isAir() == false
					&& levelReader.getFluidState(mutable).isEmpty() == false
					&& levelReader.getFluidState(mutable.below()).isEmpty()
					&& random.nextIntBetweenInclusive(0, 5) > 1
				) {
					BlockState modifiedTargetBlockOutput = this.targetBlockOutput;

					if (modifiedTargetBlockOutput.hasProperty(BlockStateProperties.WATERLOGGED)) {
						modifiedTargetBlockOutput = this.targetBlockOutput.setValue(BlockStateProperties.WATERLOGGED, true);
					}

					//? if >= 1.21.5 {
					levelReader.getChunk(mutable).setBlockState(mutable, modifiedTargetBlockOutput);
					//?} else {
					/*levelReader.getChunk(mutable).setBlockState(mutable, modifiedTargetBlockOutput, false);
					*///?}
					break;
				}

				mutable.move(this.direction);
				currentBlockState = levelReader.getBlockState(mutable);
			}
		}

		return blockInfoGlobal;
	}

	@Override
	//? if >= 26.2 {
	public MapCodec<CollapsedUnderwaterProcessor> codec() {
		return CODEC;
	}
	//?} else {
	/*protected StructureProcessorType<?> getType() {
		return VillagesAndPillagesProcessorTypes.COLLAPSED_UNDERWATER_PROCESSOR;
	}
	*///?}
}
