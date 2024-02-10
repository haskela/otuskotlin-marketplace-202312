rootProject.name = "otuskotlin-marketplace-202312"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

includeBuild("lessons")
includeBuild("otuskotlin-marketplace-be")
