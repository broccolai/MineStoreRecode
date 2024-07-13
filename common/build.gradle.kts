plugins {
    id("minestore.platform")
}
dependencies {
    implementation(projects.mineStoreAddonApi)
    implementation(libs.gson)
    implementation(libs.adventureTextSerializerPlain)
    implementation(libs.adventureTextSerializerLegacy)
    implementation(libs.adventureTextMiniMessage)
    implementation(libs.guava)
    implementation(libs.hikari)
    implementation(libs.mariadb)
    implementation(libs.mysql)
    implementation(libs.snakeyaml)

    compileOnly(libs.luckperms)

    implementation(libs.cloudAnnotations)
    implementation(libs.cloud)
}