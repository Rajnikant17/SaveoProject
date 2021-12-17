package com.example.saveo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.saveo.utils.DataState
import com.example.saveo.utils.hideLoading
import com.example.saveo.utils.showLoading

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {
    @get:LayoutRes
    abstract val fragmentLayoutResId: Int
    protected lateinit var binding: VDB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, fragmentLayoutResId, container, false/*, bindingComponent*/)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            it.lifecycleOwner = this
        }
    }

    val observer = Observer<DataState<*>> {
        when (it) {
            is DataState.Error<*> -> {
                // could handle various usecases of Error based on the Status code we set in the Datastate class . But here just simply showing one Toast error.
                hideLoading()
                Toast.makeText(requireActivity(), "Something went wrong or else please check internet", Toast.LENGTH_LONG).show()
            }
            is DataState.Loading -> {
                showLoading()
            }
            is DataState.Success<*> -> {
                hideLoading()
                receivedResponse(it.data)
            }
        }
    }
    abstract fun receivedResponse(item: Any?)
}