import org.gradle.api.Project
import org.jetbrains.kotlinx.kover.api.KoverExtension // Nota: `api` en lugar de `KoverExtension` directamente
import org.jetbrains.kotlinx.kover.api.KoverReportTask

class KoverConfig {
    static void apply(Project project) {
        project.extensions.configure(KoverExtension) { kover ->
            kover.useJacoco()
        }

        project.tasks.withType(KoverReportTask) { task ->
            task.verify {
                rule {
                    minBound(0)
                }
            }
            task.defaults {
                html {
                    onCheck = true
                }
            }
        }
    }
}
