import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class DetektPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.pluginManager.apply('io.gitlab.arturbosch.detekt')

        project.extensions.configure(DetektExtension) { detekt ->
            detekt.toolVersion = "1.23.0"
            detekt.config = project.files("$rootDir/detekt-config.yaml") // Archivo de configuración
            detekt.parallel = true // Habilitar análisis en paralelo
            detekt.source = project.files("src/main/kotlin", "src/test/kotlin") // Directorios a analizar
        }

        project.tasks.register("detektAll") {
            group = "verification"
            description = "Run Detekt for all source sets"
            dependsOn project.tasks.named("detekt")
        }
    }
}
