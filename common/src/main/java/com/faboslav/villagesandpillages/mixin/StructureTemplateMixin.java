package com.faboslav.villagesandpillages.mixin;

import com.faboslav.villagesandpillages.init.VillagesAndPillagesProcessorTypes;
import com.faboslav.villagesandpillages.mixin.accessor.StructureProcessorAccessor;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Makes it so a block's waterlogged state is not based solely on the presence of water at the block's position.
 *
 * @author TelepathicGrunt
 */
@Mixin(StructureTemplate.class)
public class StructureTemplateMixin
{
	@Inject(
		method = "place",
		at = @At(value = "HEAD")
	)
	private void betterdeserttemples_preventAutoWaterlogging(
		ServerWorldAccess world,
		BlockPos pos,
		BlockPos pivot,
		StructurePlacementData placementData,
		net.minecraft.util.math.random.Random random,
		int flags,
		CallbackInfoReturnable<Boolean> cir
	) {
		if (
			placementData.getProcessors()
				.stream()
				.anyMatch(processor -> {
					return ((StructureProcessorAccessor) processor).callGetType() == VillagesAndPillagesProcessorTypes.WATERLOG_PROCESSOR;
				})
		) {
			placementData.setPlaceFluids(false);
		}
	}
}
