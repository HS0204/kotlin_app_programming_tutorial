package com.hs.toolbartest.mainView

import androidx.fragment.app.activityViewModels
import com.hs.toolbartest.BaseFragment
import com.hs.toolbartest.databinding.FragmentProtectMain4Binding
import com.hs.toolbartest.visibility.VisibilityState
import com.hs.toolbartest.visibility.VisibilityViewModel

class ProtectMain4Fragment : BaseFragment<FragmentProtectMain4Binding>(FragmentProtectMain4Binding::inflate) {

    private val viewModel: VisibilityViewModel by activityViewModels()

    override fun setVisibility() {
        val data = VisibilityState(
            isToolbarVisible = true,
            isBackBtnVisible = false,
            titleText = "fragment4",
            isIconVisible = true
        )

        viewModel.setVisibility(data)
    }

}