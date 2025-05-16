/*
 * SuccessDialogFragment.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.ui.view.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.myorderroute.mobile.R
import com.myorderroute.mobile.databinding.FragmentSuccessDialogBinding
import com.myorderroute.mobile.util.startParty
import com.myorderroute.mobile.util.viewBinding

class SuccessDialogFragment : DialogFragment(R.layout.fragment_success_dialog) {

    private val binding by viewBinding(FragmentSuccessDialogBinding::bind)

    private lateinit var successDialogConfig: SuccessDialogConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitUi()
    }

    private fun setInitUi() {
        binding.successConfettiView.startParty()
    }

    companion object {
        const val SUCCESS_DIALOG_FRAGMENT_TAG = "SuccessDialogFragment"

        fun newInstance(
            successDialogConfig: SuccessDialogConfig,
        ): SuccessDialogFragment = SuccessDialogFragment().apply {
            this.successDialogConfig = successDialogConfig
        }
    }
}
