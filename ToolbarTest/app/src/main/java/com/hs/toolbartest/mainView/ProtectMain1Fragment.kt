package com.hs.toolbartest.mainView

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hs.toolbartest.BaseFragment
import com.hs.toolbartest.R
import com.hs.toolbartest.databinding.FragmentProtectMain1Binding
import com.hs.toolbartest.visibility.VisibilityState
import com.hs.toolbartest.visibility.VisibilityViewModel

class ProtectMain1Fragment : BaseFragment<FragmentProtectMain1Binding>(FragmentProtectMain1Binding::inflate) {

    private val viewModel: VisibilityViewModel by activityViewModels()

    override fun setVisibility() {
        val data = VisibilityState(
            isToolbarVisible = true,
            isBackBtnVisible = false,
            titleText = "",
            isIconVisible = false
        )

        viewModel.setVisibility(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moveBtn.setOnClickListener {
            findNavController().navigate(R.id.action_main1Fragment_to_protectorMainDepth1Fragment)
        }
    }
}