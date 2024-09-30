buildscript {
    dependencies {
        // Other dependencies
        classpath("org.jetbrains.kotlinx.kover:kover-plugin:0.7.6") // Replace with desired version
    }
}

plugins {
    // Apply the kover Plugin to add support for code coverage in Java and Kotlin with Kover
    id ("org.jetbrains.kotlinx.kover")
}

kover {
    useJacoco()
}

koverReport {
    verify {
        rule {
            minBound(0)
        }
    }
    defaults {
        html {
            onCheck = true
        }
    }
}