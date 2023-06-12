package com.hs.toolbartest.mainView

import androidx.fragment.app.activityViewModels
import com.hs.toolbartest.BaseFragment
import com.hs.toolbartest.databinding.FragmentProtectMain3Binding
import com.hs.toolbartest.visibility.VisibilityState
import com.hs.toolbartest.visibility.VisibilityViewModel

class ProtectMain3Fragment : BaseFragment<FragmentProtectMain3Binding>(FragmentProtectMain3Binding::inflate) {

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

}