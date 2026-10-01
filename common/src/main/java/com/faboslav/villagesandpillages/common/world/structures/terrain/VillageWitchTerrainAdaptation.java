package com.faboslav.villagesandpillages.common.world.structures.terrain;

import net.minecraft.util.Util;
import net.minecraft.util.Mth;

/**
 * Inspired by use in YUNG's API mod
 *
 * @author YUNGNICKYOUNG
 * <a href="https://github.com/YUNG-GANG/YUNGs-API">https://github.com/YUNG-GANG/YUNGs-API</a>
 */
public final class VillageWitchTerrainAdaptation
{
	private static final int KERNEL_SIZE = 48;
	private static final int KERNEL_DISTANCE = 96;
	private static final int KERNEL_RADIUS = KERNEL_SIZE / 2;
	private static final float[] KERNEL = Util.make(new float[KERNEL_SIZE * KERNEL_SIZE * KERNEL_SIZE], kernel -> {
		for (int x = 0; x < KERNEL_SIZE; ++x) {
			for (int y = 0; y < KERNEL_SIZE; ++y) {
				for (int z = 0; z < KERNEL_SIZE; ++z) {
					double kernelX = x - KERNEL_RADIUS;
					double kernelY = y - KERNEL_RADIUS + 0.5D;
					double kernelZ = z - KERNEL_RADIUS;
					kernel[index(x, y, z)] = computeKernelValue(kernelX, kernelY, kernelZ);
				}
			}
		}
	});

	private VillageWitchTerrainAdaptation() {
	}

	private static float computeKernelValue(double xDistance, double yDistance, double zDistance) {
		double squaredDistance = Mth.lengthSquared(xDistance, yDistance, zDistance);
		return (float) Math.pow(Math.E, -squaredDistance / KERNEL_DISTANCE);
	}

	public static double computeDensityFactor(int xDistance, int yDistance, int zDistance, int yDistanceToPieceBottom) {
		int kernelX = xDistance + KERNEL_RADIUS;
		int kernelY = yDistance + KERNEL_RADIUS;
		int kernelZ = zDistance + KERNEL_RADIUS;

		if (!isInKernelRange(kernelX) || !isInKernelRange(kernelY) || !isInKernelRange(kernelZ)) {
			return 0.0D;
		}

		float kernelValue = KERNEL[index(kernelX, kernelY, kernelZ)];
		double actualYDistanceToPieceBottom = (double) yDistanceToPieceBottom + 0.5D;
		double squaredDistance = Mth.lengthSquared(xDistance, actualYDistanceToPieceBottom, zDistance);
		double multiplier = Math.abs(actualYDistanceToPieceBottom * Mth.invSqrt(squaredDistance / 2.0D) / 2.0D);
		int densityModifier = actualYDistanceToPieceBottom > 0.0D ? -1 : 0;

		return multiplier * kernelValue * densityModifier;
	}

	public static int getKernelRadius() {
		return KERNEL_RADIUS;
	}

	private static boolean isInKernelRange(int i) {
		return i >= 0 && i < KERNEL_SIZE;
	}

	private static int index(int x, int y, int z) {
		return z * KERNEL_SIZE * KERNEL_SIZE + x * KERNEL_SIZE + y;
	}
}
