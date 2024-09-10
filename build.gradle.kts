plugins {
    id("java")
    kotlin("jvm") version "2.0.0"
    id("com.diffplug.spotless") version "6.7.1"
    id("org.jetbrains.kotlinx.kover") version "0.7.6"
    id("io.gitlab.arturbosch.detekt") version "1.23.6"
    `maven-publish`
}

repositories {
    mavenCentral()
}

// Comment to commit

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

allprojects {
    group = "com.printscript"
    version = "1.1-SNAPSHOT"

    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "org.jetbrains.kotlinx.kover")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
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

    detekt {
        buildUponDefaultConfig = false
        config.from("../detekt-config.yaml")
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(21))
    }
}

subprojects {
    apply(plugin = "maven-publish")

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                artifactId = project.name.lowercase()
                from(components["java"])
            }
        }

        repositories {
            maven {
                name = "GithubPackages"
                url = uri("https://maven.pkg.github.com/pedroramirezneira/printscript")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}
