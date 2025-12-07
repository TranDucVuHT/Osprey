package com.osprey.profile.ui

import android.app.Application
import com.osprey.core.viewmodel.BaseUiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val app: Application,
) : BaseUiStateViewModel<ProfileUiState, ProfileUiEvent, ProfileUiAction>(app) {
    override fun initialState(): ProfileUiState = ProfileUiState()
    override fun handleAction(action: ProfileUiAction) {
        when (action) {
            else -> {}
        }
    }
}