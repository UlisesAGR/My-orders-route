/*
 * SuccessDialogConfig.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.ui.view.dialog

import androidx.fragment.app.FragmentManager
import com.myorderroute.mobile.ui.view.dialog.SuccessDialogFragment.Companion.SUCCESS_DIALOG_FRAGMENT_TAG

class SuccessDialogConfig {

    private val successDialogFragment: SuccessDialogFragment by lazy {
        SuccessDialogFragment.newInstance(this)
    }

    fun showDialog(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(successDialogFragment, SUCCESS_DIALOG_FRAGMENT_TAG)
            .commitAllowingStateLoss()
    }

    fun setCancelable(isCancelable: Boolean) {
        successDialogFragment.isCancelable = isCancelable
    }
}
