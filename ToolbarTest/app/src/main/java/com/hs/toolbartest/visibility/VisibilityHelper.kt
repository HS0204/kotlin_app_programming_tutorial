package com.hs.toolbartest.visibility

import android.app.ActionBar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import com.hs.toolbartest.R

class VisibilityHelper(
    private val state: VisibilityState,
    private val toolbar: Toolbar,
    private val backBtn: ActionBar?,
    private val title: TextView,
    private val icon: ImageView
) {
    fun set() {
        if (state.isToolbarVisible) {
            toolbar.visibility = View.VISIBLE
        } else {
            toolbar.visibility = View.GONE
        }

        if (state.toolbarColor != null) {
            toolbar.setBackgroundColor(state.toolbarColor)
        }

        if (state.isBackBtnVisible) {
            backBtn?.setDisplayHomeAsUpEnabled(true)
        } else {
            backBtn?.setDisplayHomeAsUpEnabled(false)
        }

        title.text = state.titleText

        if (state.isIconVisible) {
            icon.visibility = View.VISIBLE
        } else {
            icon.visibility = View.GONE
        }
    }
}