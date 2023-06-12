package com.hs.toolbartest.depthView

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hs.toolbartest.BaseFragment
import com.hs.toolbartest.R
import com.hs.toolbartest.databinding.FragmentProtectorMainDepth1Binding
import com.hs.toolbartest.visibility.VisibilityState
import com.hs.toolbartest.visibility.VisibilityViewModel

class ProtectorMainDepth1Fragment : BaseFragment<FragmentProtectorMainDepth1Binding>(FragmentProtectorMainDepth1Binding::inflate) {

    private val viewModel: VisibilityViewModel by activityViewModels()

    override fun setVisibility() {
        val data = VisibilityState(
            isToolbarVisible = true,
            isBackBtnVisible = true,
            titleText = "",
            toolbarColor = R.color.black,
            isIconVisible = false
        )

        viewModel.setVisibility(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moveBtn.setOnClickListener {
            findNavController().navigate(R.id.action_protectorMainDepth1Fragment_to_protectorMainDepth2Fragment)
        }
    }
}