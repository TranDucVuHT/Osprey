package com.osprey.home.ui.gateway

import android.app.Application
import com.osprey.core.viewmodel.BaseUiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    val app: Application,
) : BaseUiStateViewModel<NotificationUiState, NotificationUiEvent, NotificationUiAction>(app) {
    override fun initialState(): NotificationUiState = NotificationUiState()
    override fun handleAction(action: NotificationUiAction) {
        when (action) {
            else -> {}
        }
    }
}
