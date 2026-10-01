plugins {
	`multiloader-loader`
	id("net.neoforged.moddev")
	id("dev.kikugie.fletching-table.neoforge") version "0.1.0-alpha.23"
}

fletchingTable {
	j52j.register("main") {
		extension("json", "**/*.json5")
	}
}

neoForge {
	enable {
		version = commonMod.dep("neoforge")
	}
}

neoForge {
	runs {
		register("client") {
			client()
			ideFolderName = "NeoForge"
			ideName = "NeoForge Client (${project.path})"
		}
		register("server") {
			server()
			ideFolderName = "NeoForge"
			ideName = "NeoForge Server (${project.path})"
		}
	}

	parchment {
		commonMod.depOrNull("parchment")?.let {
			mappingsVersion = it
			minecraftVersion = commonMod.mc
		}
	}

	mods {
		register(commonMod.id) {
			sourceSet(sourceSets.main.get())
		}
	}
}

sourceSets.main {
	resources.srcDir("src/generated/resources")
}

if (stonecutter.current.isActive) tasks.register("buildActive") {
	dependsOn("build")
}