/*
 * EmptyStateList.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.myorderroute.mobile.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.myorderroute.mobile.R
import com.myorderroute.mobile.databinding.EmptyStateBinding
import com.myorderroute.mobile.util.log
import com.myorderroute.mobile.util.show

class EmptyState @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        EmptyStateBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.EmptyState).apply {
            runCatching {
                setImage(getResourceId(R.styleable.EmptyState_imageEmptyState, 0))
                setTitle(getString(R.styleable.EmptyState_titleEmptyState))
                setSubtitle(getString(R.styleable.EmptyState_subtitleEmptyState))
            }.onSuccess {
                recycle()
            }.onFailure { exception ->
                log(
                    className = this@EmptyState,
                    message = exception.message ?: "",
                )
            }
        }
    }

    private fun setImage(image: Int) {
        with(binding.emptyStateImageView) {
            if (image != 0) {
                setImageResource(image)
                show()
            }
        }
    }

    private fun setTitle(title: String?) {
        with(binding.titleTextView) {
            if (!title.isNullOrEmpty()) {
                text = title
                show()
            }
        }
    }

    private fun setSubtitle(subtitle: String?) {
        with(binding.subtitleTextView) {
            if (!subtitle.isNullOrEmpty()) {
                text = subtitle
                show()
            }
        }
    }
}
