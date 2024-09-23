buildscript {
    dependencies {
        // Other dependencies
        classpath("com.diffplug.spotless:spotless-plugin:6.7.1") // Replace with desired version
    }
}

plugins {

    // Apply the spotless Plugin to add support for code coverage in Java and Kotlin with Spotless.
    id ("com.diffplug.spotless")
}

    spotless {
        kotlin {
            ktlint()
                .editorConfigOverride(
                    mapOf(
                        "indent_size" to 2,
                        "max_line_length" to "150",
                        "ktlint_code_style" to "intellij_idea",
                    )
                )
            target("src/**/*.kt")
        }
    }
