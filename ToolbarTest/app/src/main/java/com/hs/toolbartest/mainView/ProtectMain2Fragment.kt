package com.hs.toolbartest.mainView

import androidx.fragment.app.activityViewModels
import com.hs.toolbartest.BaseFragment
import com.hs.toolbartest.databinding.FragmentProtectMain2Binding
import com.hs.toolbartest.visibility.VisibilityState
import com.hs.toolbartest.visibility.VisibilityViewModel


class ProtectMain2Fragment : BaseFragment<FragmentProtectMain2Binding>(FragmentProtectMain2Binding::inflate) {

    private val viewModel: VisibilityViewModel by activityViewModels()

    override fun setVisibility() {
        val data = VisibilityState(
            isToolbarVisible = false,
            isBackBtnVisible = false,
            titleText = "",
            isIconVisible = false
        )

        viewModel.setVisibility(data)
    }

}