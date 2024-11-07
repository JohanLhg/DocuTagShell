package com.jlahougue.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureComposeAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.run {
        val composeExtension = extensions.getByType<ComposeExtension>()
        buildFeatures {
            compose = true
        }
        dependencies {
            "debugImplementation"(composeExtension.dependencies.uiTooling)
        }
    }
}

internal fun Project.configureComposeMultiplatform(
    extension: KotlinMultiplatformExtension
) = extension.run {
    val composeExtension = extensions.getByType<ComposeExtension>()

    sourceSets.apply {
        commonMain {
            dependencies {
                implementation(composeExtension.dependencies.runtime)
                implementation(composeExtension.dependencies.foundation)
                implementation(composeExtension.dependencies.material)
                implementation(composeExtension.dependencies.material3)
                implementation(composeExtension.dependencies.material3AdaptiveNavigationSuite)
                implementation(composeExtension.dependencies.ui)
                implementation(composeExtension.dependencies.components.resources)
                implementation(composeExtension.dependencies.components.uiToolingPreview)
                implementation(libs.findLibrary("androidx.lifecycle.viewmodel").get() )
                implementation(libs.findLibrary("androidx.lifecycle.runtime.compose").get() )
                implementation(libs.findLibrary("androidx.navigation.compose").get() )

                implementation(libs.findLibrary("koin.compose").get() )
                implementation(libs.findLibrary("koin.compose.viewmodel").get() )
            }
        }

        androidMain {
            dependencies {
                implementation(composeExtension.dependencies.preview)
                implementation(libs.findLibrary("androidx.activity.compose").get() )
                implementation(libs.findLibrary("koin.androidx.compose").get() )
            }
        }
    }
}