pluginManagement {
    plugins {
        kotlin("jvm") version "2.0.0"
        id("org.jetbrains.kotlin.jvm") version "2.0.0"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "ing-sis-PrintScreen"
include("lexer")
include("models")
include("parser")
include("interpreter")
include("engine")
include("linter")
include("formatter")
include(":integral")
include("cli")
include("cli")
