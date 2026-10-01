package com.faboslav.villagesandpillages.common.versions;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

public final class VersionedNbt
{
	public static ListTag getList(CompoundTag nbt, String key) {
		//? if >= 1.21.5 {
		return nbt.getListOrEmpty(key);
		//?} else {
		/*return nbt.getList(key, 10);
		*///?}
	}
}
