package com.hs.toolbartest

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hs.toolbartest.databinding.FragmentHostBinding
import com.hs.toolbartest.visibility.VisibilityHelper
import com.hs.toolbartest.visibility.VisibilityState
import com.hs.toolbartest.visibility.VisibilityViewModel

class HostFragment : BaseFragment<FragmentHostBinding>(FragmentHostBinding::inflate) {

    private lateinit var hostNavController: NavController

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, backPressedCallback)

        // 네비게이션
        val navHostFragment =
            childFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        hostNavController = navHostFragment.navController

        hostNavController.addOnDestinationChangedListener { controller, destination, args ->
            backPressedCallback.isEnabled = true
        }

        // 툴바
        activity?.setActionBar(binding.hostToolbar.toolbarLayout)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.actionBar?.setDisplayShowTitleEnabled(false)
        viewModel.visibilityState.observe(requireActivity()) { state ->
            VisibilityHelper(
                state = state,
                toolbar = binding.hostToolbar.toolbarLayout,
                backBtn = activity?.actionBar,
                title = binding.hostToolbar.toolbarTitle,
                icon = binding.hostToolbar.toolbarIcon
            ).set()
        }

        binding.hostToolbar.toolbarLayout.setNavigationOnClickListener {
            hostNavController.popBackStack()
        }

        // 바텀네비
        binding.bottomNav.setupWithNavController(hostNavController)
    }

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            hostNavController.popBackStack()
        }
    }
}