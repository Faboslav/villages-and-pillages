package com.faboslav.villagesandpillages.common.mixin;

import com.faboslav.villagesandpillages.common.tag.VillagesAndPillagesTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//? if >= 26.3 {
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
//?} else {
/*import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
*///?}

@Mixin(TreeFeature.class)
public class TreeFeatureMixin
{
	//? if >= 26.3 {
	@Inject(
		method = "place(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;)Z",
		at = @At(value = "HEAD"),
		cancellable = true
	)
	private void villagesandpillages$noTreeOrLessTreeInStructures(
		WorldGenLevel worldGenLevel,
		ChunkGenerator chunkGenerator,
		RandomSource random,
		BlockPos origin,
		CallbackInfoReturnable<Boolean> cir
	) {
		if (
			(worldGenLevel.getBlockState(origin.below()).is(Blocks.MOSS_BLOCK) || random.nextFloat() > 0.5F)
			&& worldGenLevel.getLevel().structureManager().getStructureWithPieceAt(origin, VillagesAndPillagesTags.VILLAGE_WITCH).isValid()
		) {
			cir.setReturnValue(false);
		}
	}
	//?} else {
	/*@Inject(
		method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
		at = @At(value = "HEAD"),
		cancellable = true
	)
	private void villagesandpillages$noTreeOrLessTreeInStructures(
		FeaturePlaceContext<TreeConfiguration> context,
		CallbackInfoReturnable<Boolean> cir
	) {
		WorldGenLevel worldGenLevel = context.level();
		BlockPos origin = context.origin();

		if (
			(worldGenLevel.getBlockState(origin.below()).is(Blocks.MOSS_BLOCK) || context.random().nextFloat() > 0.5F)
			&& worldGenLevel.getLevel().structureManager().getStructureWithPieceAt(origin, VillagesAndPillagesTags.VILLAGE_WITCH).isValid()
		) {
			cir.setReturnValue(false);
		}
	}
	*///?}
}
