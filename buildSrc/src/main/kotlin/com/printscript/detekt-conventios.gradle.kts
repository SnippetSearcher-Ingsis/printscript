buildscript {
    dependencies {
        // Other dependencies
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.6") // Replace with desired version
    }
}

plugins {

    // Apply the checkstyle Plugin to add support for code coverage in Java with Checkstyle
    id ("io.gitlab.arturbosch.detekt")
}

    detekt {
        buildUponDefaultConfig = false
        config.from("../detekt-config.yaml")
    }