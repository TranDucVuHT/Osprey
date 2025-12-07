package com.osprey.scene.ui.topPager.taptorun

import android.app.Application
import com.osprey.core.viewmodel.BaseUiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TapToRunViewModel @Inject constructor(
    val app: Application,
) : BaseUiStateViewModel<TapToRunUiState, TapToRunUiEvent,TapToRunUiAction>(app) {
    override fun initialState(): TapToRunUiState = TapToRunUiState()
    override fun handleAction(action: TapToRunUiAction) {
        when (action) {
            else -> {}
        }
    }
}