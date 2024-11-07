package com.jlahougue.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

private const val JDK_VERSION = 17
private val JVM_TARGET = JvmTarget.JVM_17
private val JAVA_VERSION = JavaVersion.VERSION_17

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.run {
        compileSdk = libs.findVersion("android-compileSdk").get().requiredVersion.toInt()

        defaultConfig {
            minSdk = libs.findVersion("android-minSdk").get().requiredVersion.toInt()
        }
        compileOptions {
            sourceCompatibility = JAVA_VERSION
            targetCompatibility = JAVA_VERSION
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    jvmToolchain(JDK_VERSION)

    // targets
    androidTarget {
        compilerOptions {
            jvmTarget.set(JVM_TARGET)
        }
    }

    jvm("desktop") {
        compilations.all {
            compileTaskProvider
        }
    }

    applyDefaultHierarchyTemplate()

    sourceSets.apply {
        commonMain {
            dependencies {
                api(libs.findLibrary("koin.core").get())
                implementation(libs.findLibrary("kotlinx.coroutines.core").get())
                implementation(libs.findLibrary("kotlinx.serialization").get())
            }
        }

        androidMain {
            dependencies {
                implementation(libs.findLibrary("koin.android").get() )
            }
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JAVA_VERSION
        targetCompatibility = JAVA_VERSION
    }
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget = JVM_TARGET
        }
    }
}