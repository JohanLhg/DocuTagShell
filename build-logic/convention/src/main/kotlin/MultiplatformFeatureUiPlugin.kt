
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiplatformFeatureUiPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("docutag.multiplatform.library.compose")
            }
        }
    }
}