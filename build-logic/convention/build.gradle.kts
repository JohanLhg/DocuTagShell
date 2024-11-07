plugins {
    `kotlin-dsl`
}

group = "com.jlahougue.docutag.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("multiplatformApplication") {
            id = "docutag.multiplatform.application"
            implementationClass = "MultiplatformApplicationPlugin"
        }
        register("multiplatformApplicationCompose") {
            id = "docutag.multiplatform.application.compose"
            implementationClass = "MultiplatformApplicationComposePlugin"
        }
        register("multiplatformLibrary") {
            id = "docutag.multiplatform.library"
            implementationClass = "MultiplatformLibraryPlugin"
        }
        register("multiplatformLibraryCompose") {
            id = "docutag.multiplatform.library.compose"
            implementationClass = "MultiplatformLibraryComposePlugin"
        }
        register("multiplatformFeatureUi") {
            id = "docutag.multiplatform.feature.ui"
            implementationClass = "MultiplatformFeatureUiPlugin"
        }
        register("jvmLibrary") {
            id = "docutag.jvm.library"
            implementationClass = "JvmLibraryPlugin"
        }
    }
}