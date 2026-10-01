val IS_CI = System.getenv("CI") == "true"

plugins {
	id("multiloader-common")
	id("fabric-loom-compat")
	id("dev.kikugie.fletching-table.fabric") version "0.1.0-alpha.23"
}

fletchingTable {
	j52j.register("main") {
		extension("json", "**/*.json5")
	}
}

if (stonecutter.eval(commonMod.mc, "<=1.21.11")) {
	loom {
		mixin {
			useLegacyMixinAp = false
		}
	}
}

dependencies {
	minecraft("com.mojang:minecraft:${commonMod.mc}")

	if (stonecutter.eval(commonMod.mc, "<=1.21.11")) {
		mappings(loom.layered {
			officialMojangMappings()

			if (!IS_CI) {
				commonMod.depOrNull("parchment")?.let { parchmentVersion ->
					parchment("org.parchmentmc.data:parchment-${commonMod.mc}:$parchmentVersion@zip")
				}
			}
		})
	}

	modCompileOnly("net.fabricmc:fabric-loader:${commonMod.dep("fabric_loader")}")
}

val commonJava: Configuration by configurations.creating {
	isCanBeResolved = false
	isCanBeConsumed = true
}

val commonResources: Configuration by configurations.creating {
	isCanBeResolved = false
	isCanBeConsumed = true
}

artifacts {
	afterEvaluate {
		val mainSourceSet = sourceSets.main.get()

		mainSourceSet.java.sourceDirectories.files.forEach {
			add(commonJava.name, it)
		}

		mainSourceSet.resources.sourceDirectories.files.forEach {
			add(commonResources.name, it)
		}
	}
}