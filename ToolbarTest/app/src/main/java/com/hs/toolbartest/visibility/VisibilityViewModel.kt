package com.hs.toolbartest.visibility

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VisibilityViewModel: ViewModel() {

    private val _visibilityState: MutableLiveData<VisibilityState> = MutableLiveData()
    val visibilityState = _visibilityState

    init {
        _visibilityState.value = VisibilityState.initiate
    }

    fun setVisibility(state: VisibilityState) {
        _visibilityState.value = state
    }
}