plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    id("com.google.protobuf")


}
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.1"
    }
//  heleditor_basics

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                val java by registering {
                    option("lite")
                }
                val kotlin by registering {
                    option("lite")
                }
            }
        }
    }
}
android {
    namespace = "com.vajan.vajan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vajan.vajan"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig=true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.room.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.androidx.material.icons.extended.android)
    implementation(libs.androidx.compose.bom.v20240201)
    implementation ("androidx.navigation:navigation-compose")
    implementation (libs.androidx.hilt.navigation.compose)
    implementation(libs.coil.compose)
    implementation (libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation (libs.converter.gson)

    implementation ("androidx.room:room-runtime:2.6.1")
    ksp ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    implementation ("com.google.dagger:hilt-android:2.51")
    kapt ("com.google.dagger:hilt-compiler:2.51")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")
    kapt ("com.google.dagger:dagger-android-processor:2.11" )

    implementation ("androidx.navigation:navigation-compose")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation( "androidx.navigation:navigation-compose:2.7.7")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")
    implementation(libs.androidx.datastore)
    implementation  (libs.protobuf.kotlin.lite)







}

kapt {
    correctErrorTypes = true
}