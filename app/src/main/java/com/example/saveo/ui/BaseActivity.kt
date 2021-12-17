package com.example.saveo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.example.saveo.R
import com.example.saveo.databinding.ActivityBaseBinding

abstract class BaseActivity : AppCompatActivity() {
    var binding: ActivityBaseBinding? = null
    @get:LayoutRes

    abstract val layoutResourceId: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(this),
                R.layout.activity_base,
                null,
                false
            )
        setContentView(binding?.root)
        layoutInflater.inflate(layoutResourceId, binding?.container)
    }

    fun showLoading() {
        binding?.pbProgress?.let {
            it.visibility = View.VISIBLE
            Log.d("hgchchjkjj","showing")
        }
    }

    fun hideLoading() {
        binding?.pbProgress?.let {
            it.visibility = View.GONE
            Log.d("hgchchjkjj","hiding")
        }
    }
}