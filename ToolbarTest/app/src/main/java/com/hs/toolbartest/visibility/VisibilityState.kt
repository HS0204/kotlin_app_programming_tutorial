package com.hs.toolbartest.visibility

import com.hs.toolbartest.R

data class VisibilityState(
    val isToolbarVisible: Boolean,
    val isBackBtnVisible: Boolean,
    val titleText: String,
    val toolbarColor: Int? = R.color.white,
    val isIconVisible: Boolean
) {
    companion object {
        val initiate = VisibilityState(
            isToolbarVisible = true,
            isBackBtnVisible = false,
            titleText = "",
            toolbarColor = R.color.white,
            isIconVisible = false
        )
    }
}
