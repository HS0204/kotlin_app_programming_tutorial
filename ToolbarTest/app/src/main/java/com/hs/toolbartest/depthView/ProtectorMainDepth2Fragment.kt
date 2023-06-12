package com.hs.toolbartest.depthView

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hs.toolbartest.BaseFragment
import com.hs.toolbartest.R
import com.hs.toolbartest.databinding.FragmentProtectorMainDepth2Binding
import com.hs.toolbartest.visibility.VisibilityState
import com.hs.toolbartest.visibility.VisibilityViewModel

class ProtectorMainDepth2Fragment :
    BaseFragment<FragmentProtectorMainDepth2Binding>(FragmentProtectorMainDepth2Binding::inflate) {

    private val viewModel: VisibilityViewModel by activityViewModels()

    override fun setVisibility() {
        val data = VisibilityState(
            isToolbarVisible = true,
            isBackBtnVisible = true,
            titleText = "상태바 변경",
            isIconVisible = false
        )

        viewModel.setVisibility(data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogButton.setOnClickListener {
            context?.let { it1 ->
                AlertDialog.Builder(it1).setTitle("타이틀").setMessage("메세지").show()
            }
        }

        binding.bottomButton.setOnClickListener {
            BottomSheetDialogFragment().show(childFragmentManager, "test")
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.black)
    }

    override fun onPause() {
        super.onPause()
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.white)
    }

}