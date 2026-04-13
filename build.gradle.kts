plugins {
    id("com.refinedmods.refinedarchitect.root")
    id("com.refinedmods.refinedarchitect.base")
    id("me.modmuss50.mod-publish-plugin") version "1.1.0"
}

val modVersion: String by project
val currentChangelog: String by project
val minecraftVersion: String by project

version = modVersion

publishMods {
    changelog = currentChangelog
    type = STABLE

    val cfOptions = curseforgeOptions {
        accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
        projectId = "452645"
        minecraftVersions.add(minecraftVersion)
    }

    val mrOptions = modrinthOptions {
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")
        projectId = "nQR8XALE"
        minecraftVersions.add(minecraftVersion)
    }

    curseforge("curseforgeFabric") {
        from(cfOptions)
        file(project(":fabric"))
        modLoaders.add("fabric")
        displayName = file.map { it.asFile.name }
        requires("refined-storage", "fabric-api")
    }

    curseforge("curseforgeNeoforge") {
        from(cfOptions)
        file(project(":neoforge"))
        modLoaders.add("neoforge")
        displayName = file.map { it.asFile.name }
        requires("refined-storage")
    }

    modrinth("modrinthFabric") {
        from(mrOptions)
        file(project(":fabric"))
        modLoaders.add("fabric")
        requires("refined-storage", "fabric-api")
    }

    modrinth("modrinthNeoforge") {
        from(mrOptions)
        file(project(":neoforge"))
        modLoaders.add("neoforge")
        requires("refined-storage")
    }
}

subprojects {
    group = "com.ultramega.interdimensionalwirelesstransmitter"
}