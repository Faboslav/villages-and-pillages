package com.faboslav.villagesandpillages.mixin;

import com.faboslav.villagesandpillages.VillagesAndPillages;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeFeature.class)
public final class LessTreesInVillageWitchMixin
{
	@Inject(
		method = "generate(Lnet/minecraft/world/gen/feature/util/FeatureContext;)Z",
		at = @At(value = "HEAD"),
		cancellable = true
	)
	private void villagesAndPillages_lessSwampInStructures(
		FeatureContext<TreeFeatureConfig> context,
		CallbackInfoReturnable<Boolean> cir
	) {
		if (context.getWorld() instanceof ChunkRegion) {
			BlockPos originBlock = context.getOrigin();
			BlockPos.Mutable mutable = originBlock.mutableCopy();

			StructureWorldAccess world = context.getWorld();
			Registry<Structure> structureRegistry = world.getRegistryManager().getOptional(RegistryKeys.STRUCTURE).orElseThrow();
			StructureAccessor structureAccessor = world.toServerWorld().getStructureAccessor();

			if (
				context.getRandom().nextFloat() > 0.5
				&& structureAccessor.getStructureAt(
					mutable,
					structureRegistry.get(VillagesAndPillages.makeID("village_witch"))
				).hasChildren()
			) {
				cir.setReturnValue(false);
			}
		}
	}
}
