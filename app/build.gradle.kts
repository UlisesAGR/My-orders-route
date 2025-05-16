/*
 * build.gradle.kts - Module app
 * Modified by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
plugins {
    alias(libs.plugins.com.application)
    alias(libs.plugins.org.kotlin)
    alias(libs.plugins.com.hilt)
    alias(libs.plugins.androidx.navigation)
    alias(libs.plugins.com.ksp)
    alias(libs.plugins.com.google.services)
    alias(libs.plugins.com.google.crashlytics)
}

android {
    namespace = BuildConfig.APP_NAMESPACE
    compileSdk = BuildConfig.COMPILE_SDK_VERSION

    defaultConfig {
        resValue("string", "APP_NAME", "\"${properties["app.name"]}\"")
        resValue("string", "MAPS_KEY", "\"${properties["maps.key"]}\"")

        buildConfigField("String", "BASE_URL", "\"http://demo4093551.mockable.io/\"")
        buildConfigField("String", "LOCATIONS_ENDPOINT", "\"locations\"")

        buildConfigField("String", "DATABASE_NAME", "\"${properties["database.name"]}\"")

        applicationId = BuildConfig.APP_ID
        minSdk = BuildConfig.MIN_SDK_VERSION
        targetSdk = BuildConfig.TARGET_SDK_VERSION
        versionCode = ReleaseConfig.VERSION_CODE
        versionName = ReleaseConfig.VERSION_NAME
        testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildTypes.RELEASE) {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            isMinifyEnabled = Build.Release.isMinifyEnabled
            isShrinkResources = Build.Release.isShrinkResources
            isDebuggable = Build.Release.enableUnitTestCoverage
            enableUnitTestCoverage = Build.Release.isDebuggable
        }
        getByName(BuildTypes.DEBUG) {
            isMinifyEnabled = Build.Debug.isMinifyEnabled
            isDebuggable = Build.Debug.isDebuggable
            enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    // Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.androidx.lifecycle.libs)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.org.coroutines)
    implementation(libs.androidx.runner)
    implementation(libs.com.hilt)
    implementation(libs.test.hilt)
    ksp(libs.com.hilt.compiler)
    implementation(libs.com.okhttp3)
    implementation(libs.bundles.com.retrofit.libs)
    implementation(libs.bundles.androidx.room.libs)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support)
    implementation(libs.androidx.splashscreen)
    implementation(libs.bundles.androidx.navigation.libs)
    implementation(libs.com.material3)
    implementation(libs.nl.konfetti)
    implementation(libs.bundles.com.firebase.libs)
    implementation(libs.bundles.com.google.gms)
    // Test
    testImplementation(libs.test.junit)
    testImplementation(libs.test.coroutines)
    testImplementation(libs.test.hilt)
    testImplementation(libs.bundles.test.mockito.libs)
    testImplementation(libs.test.robolectric)
    // AndroidTest
    androidTestImplementation(libs.androidTest.junit)
    androidTestImplementation(libs.androidTest.room)
    androidTestImplementation(libs.bundles.androidTest.espresso.libs)
}
