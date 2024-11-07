
import com.android.build.api.dsl.ApplicationExtension
import com.jlahougue.convention.configureComposeAndroid
import com.jlahougue.convention.configureComposeMultiplatform
import com.jlahougue.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformApplicationComposePlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("docutag.multiplatform.application")
                apply(libs.findPlugin("jetbrainsCompose").get().get().pluginId)
                apply(libs.findPlugin("compose.compiler").get().get().pluginId)
            }

            extensions.configure<KotlinMultiplatformExtension>(::configureComposeMultiplatform)
            val extension = extensions.getByType<ApplicationExtension>()
            configureComposeAndroid(extension)
        }
    }
}