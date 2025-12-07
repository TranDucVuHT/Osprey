package com.osprey.scene.ui.topPager.automation

import android.app.Application
import com.osprey.core.viewmodel.BaseUiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AutomationViewModel @Inject constructor(
    val app: Application,
) : BaseUiStateViewModel<AutomationUiState, AutomationUiEvent,AutomationUiAction>(app) {
    override fun initialState(): AutomationUiState = AutomationUiState()
    override fun handleAction(action: AutomationUiAction) {
        when (action) {
            else -> {}
        }
    }
}