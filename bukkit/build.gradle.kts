plugins {
    id("minestore.platform")
    alias(libs.plugins.run.paper)
}

dependencies {
    implementation(projects.mineStoreCommon)

    compileOnly(libs.spigot)
    implementation(libs.adventure)
    implementation(libs.adventurePlatformBukkit)

    compileOnly(libs.vault)
    compileOnly(libs.placeholderApi)

    implementation(libs.cloudPaper)
}

tasks {
    runServer {
        minecraftVersion("1.20.6")
    }
}