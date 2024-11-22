package com.faboslav.villagesandpillages.mixin;

import com.faboslav.villagesandpillages.init.VillagesAndPillagesStructureTypes;
import com.yungnickyoung.minecraft.yungsapi.util.MixinUtils;
import net.minecraft.block.Blocks;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeFeature.class)
public class TreeFeatureMixin
{
	@Inject(
		method = "generate(Lnet/minecraft/world/gen/feature/util/FeatureContext;)Z",
		at = @At(value = "HEAD"),
		cancellable = true
	)
	private void villagesAndPillages_noTreeOrLessTreeInStructures(
		FeatureContext<TreeFeatureConfig> context,
		CallbackInfoReturnable<Boolean> cir
	) {
		if (!(context.getWorld() instanceof ChunkRegion worldGenRegion)) return;

		if ((worldGenRegion.getBlockState(context.getOrigin().down()).isOf(Blocks.MOSS_BLOCK) || context.getRandom().nextFloat() > 0.5) && MixinUtils.isPositionInTaggedStructure(worldGenRegion, context.getOrigin(), VillagesAndPillagesStructureTypes.VILLAGE_WITCH)) {
			cir.setReturnValue(false);
		}
	}
}
