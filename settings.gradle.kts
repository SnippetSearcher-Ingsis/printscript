pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        kotlin("jvm") version "2.0.0"
        id("org.jetbrains.kotlin.jvm") version "2.0.0"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "printscript"

include("lexer")
include("models")
include("parser")
include("interpreter")
include("engine")
include("linter")
include("formatter")
include("cli")
