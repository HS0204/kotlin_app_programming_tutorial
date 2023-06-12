package com.hs.toolbartest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.hs.toolbartest.visibility.VisibilityViewModel

abstract class BaseFragment<B: ViewBinding>(private val inflate: (LayoutInflater) -> B) : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    abstract fun setVisibility()

    protected val visibilityViewModel: VisibilityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setVisibility()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}