package com.osprey.home.ui.home

import android.app.Application
import com.osprey.core.viewmodel.BaseUiStateViewModel
import com.osprey.core.viewmodel.EmptyUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val app: Application,
) : BaseUiStateViewModel<HomeUiState, EmptyUiEvent, HomeUiAction>(app) {
    override fun initialState(): HomeUiState = HomeUiState()

    override fun handleAction(action: HomeUiAction) {
        when (action) {
            else -> {}
        }
    }
}
