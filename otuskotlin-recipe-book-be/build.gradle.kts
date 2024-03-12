plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

group = "com.otus.otuskotlin.marketplace"
version = "0.0.1"

subprojects {
    repositories {
        mavenCentral()
    }
    group = rootProject.group
    version = rootProject.version
}

tasks {
    create("build") {
        group = "build"
        dependsOn(project(":otuskotlin-recipe-book-tmp").getTasksByName("build",false))
    }
    create("check") {
        group = "verification"
        subprojects.forEach { proj ->
            println("PROJ $proj")
            proj.getTasksByName("check", false).also {
                this@create.dependsOn(it)
            }
        }
    }
}