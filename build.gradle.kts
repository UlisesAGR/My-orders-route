/*
 * build.gradle.kts - App
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
plugins {
    alias(libs.plugins.com.application) apply false
    alias(libs.plugins.com.library) apply false
    alias(libs.plugins.org.kotlin) apply false
    alias(libs.plugins.com.hilt) apply false
    alias(libs.plugins.androidx.navigation) apply false
    alias(libs.plugins.com.ksp) apply false
    alias(libs.plugins.com.google.services) apply false
    alias(libs.plugins.com.google.crashlytics) apply false
}