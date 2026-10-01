package com.faboslav.villagesandpillages.common.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureEntityInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//? if < 26.2 {
/*import com.faboslav.villagesandpillages.common.init.VillagesAndPillagesProcessorTypes;
import com.faboslav.villagesandpillages.common.mixin.accessor.StructureProcessorAccessor;
*///?}

//? if >= 26.2 {
import com.faboslav.villagesandpillages.common.world.processor.WaterlogProcessor;
//?}

//? if >= 1.21.1 {
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
//?}

/**
 * Makes it so a block's waterlogged state is not based solely on the presence of water at the block's position.
 *
 * @author TelepathicGrunt
 */
@Mixin(StructureTemplate.class)
public class StructureTemplateMixin
{
	@Inject(
		method = "placeInWorld",
		at = @At(value = "HEAD")
	)
	private void villagesandpillages$preventAutoWaterlogging(
		ServerLevelAccessor serverLevelAccessor,
		BlockPos pos,
		BlockPos pivot,
		StructurePlaceSettings structurePlaceSettings,
		RandomSource random,
		int flags,
		CallbackInfoReturnable<Boolean> cir
	) {
		if (
			structurePlaceSettings.getProcessors()
				.stream()
				//? if >= 26.2 {
				.anyMatch(processor -> processor.codec() == WaterlogProcessor.CODEC)
				//?} else {
				/*.anyMatch(processor -> ((StructureProcessorAccessor) processor).callGetType() == VillagesAndPillagesProcessorTypes.WATERLOG_PROCESSOR)
				*///?}
		) {
			//? if >= 1.21.1 {
			structurePlaceSettings.setLiquidSettings(LiquidSettings.IGNORE_WATERLOGGING);
			//?} else {
			/*structurePlaceSettings.setKeepLiquids(false);
			*///?}
		}
	}
}
