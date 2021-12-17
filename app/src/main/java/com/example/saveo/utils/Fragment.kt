package com.example.saveo.utils

import androidx.fragment.app.Fragment
import com.example.saveo.ui.BaseActivity

fun Fragment.showLoading() {
    when (activity) {
        is BaseActivity -> (activity as BaseActivity).showLoading()
    }
}

fun Fragment.hideLoading() {
    when (activity) {
        is BaseActivity -> (activity as BaseActivity).hideLoading()
    }
}