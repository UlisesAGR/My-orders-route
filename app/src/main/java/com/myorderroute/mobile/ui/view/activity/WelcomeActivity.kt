/*
 * WelcomeActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.ui.view.activity

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.myorderroute.mobile.R
import com.myorderroute.mobile.databinding.ActivityWelcomeBinding
import com.myorderroute.mobile.util.nextActivity
import com.myorderroute.mobile.util.toast
import com.myorderroute.mobile.util.viewBinding

class WelcomeActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityWelcomeBinding::inflate)

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                nextActivity(OrderRouteActivity())
            } else {
                toast(getString(R.string.please_grant_permission))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        splash.setKeepOnScreenCondition { false }
        setInit()
    }

    private fun setInit() {
        setListeners()
    }

    private fun setListeners() {
        binding.continueButton.setOnClickListener {
            validateLocationPermissions()
        }
    }

    private fun validateLocationPermissions() {
        requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }
}
