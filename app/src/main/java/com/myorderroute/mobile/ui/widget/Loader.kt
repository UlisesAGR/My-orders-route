/*
 * Loader.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.myorderroute.mobile.R
import com.myorderroute.mobile.databinding.LoaderBinding
import com.myorderroute.mobile.util.log
import com.myorderroute.mobile.util.show

class Loader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        LoaderBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.Loader).apply {
            runCatching {
                setText(getString(R.styleable.Loader_textLoader))
            }.onSuccess {
                recycle()
            }.onFailure { exception ->
                log(
                    className = this@Loader,
                    message = exception.message ?: "",
                )
            }
        }
    }

    private fun setText(text: String?) {
        with(binding.textLoading) {
            if (!text.isNullOrEmpty()) {
                this.text = text
                show()
            }
        }
    }
}
