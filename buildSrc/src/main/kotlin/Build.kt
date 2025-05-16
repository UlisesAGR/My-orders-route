/*
 * Build.kt - Module buildSrc
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
sealed class Build {

    open val isMinifyEnabled = false
    open val isShrinkResources = false
    open val enableUnitTestCoverage = false
    open val isDebuggable = false

    object Debug : Build() {
        override val isMinifyEnabled = false
        override val isShrinkResources = false
        override val isDebuggable = true
        override val enableUnitTestCoverage = true
    }

    object Release : Build() {
        override val isMinifyEnabled = true
        override val isShrinkResources = true
        override val isDebuggable = false
        override val enableUnitTestCoverage = false
    }
}
