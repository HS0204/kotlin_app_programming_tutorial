package com.hs.roomwitharchitecture.fragment.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hs.roomwitharchitecture.R
import com.hs.roomwitharchitecture.databinding.FragmentSavedBinding

class SavedFragment : Fragment() {
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        val view = binding

        return view.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}