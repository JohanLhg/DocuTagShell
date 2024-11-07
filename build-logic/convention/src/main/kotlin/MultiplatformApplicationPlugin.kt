
import com.android.build.api.dsl.ApplicationExtension
import com.jlahougue.convention.configureKotlinAndroid
import com.jlahougue.convention.configureKotlinMultiplatform
import com.jlahougue.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class MultiplatformApplicationPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply(libs.findPlugin("kotlinMultiplatform").get().get().pluginId)
                apply(libs.findPlugin("androidApplication").get().get().pluginId)
                apply(libs.findPlugin("kotlin.serialization").get().get().pluginId)
            }

            extensions.configure<KotlinMultiplatformExtension>(::configureKotlinMultiplatform)
            val extension = extensions.getByType<ApplicationExtension>()
            configureKotlinAndroid(extension)
        }
    }
}