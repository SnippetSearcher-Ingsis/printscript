import org.gradle.api.Plugin
import org.gradle.api.Project
import com.diffplug.gradle.spotless.SpotlessExtension

class SpotlessPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.pluginManager.apply('com.diffplug.spotless')

        project.extensions.configure(SpotlessExtension) { spotless ->
            spotless.kotlin {
                target 'src/**/*.kt'
                ktlint('0.44.0').editorConfigOverride([
                        "indent_size": 2,
                        "max_line_length": "150",
                        "ktlint_code_style": "intellij_idea"
                ])
                target("src/**/*.kt")
            }
        }
    }
}
