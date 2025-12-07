package com.osprey.mall.ui

import android.app.Application
import com.osprey.core.viewmodel.BaseUiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MallViewModel @Inject constructor(
    val app: Application,
) : BaseUiStateViewModel<MallUiState, MallUiEvent, MallUiAction>(app) {
    override fun initialState(): MallUiState = MallUiState()

    override fun handleAction(action: MallUiAction) {
        when (action) {
            else -> {}
        }
    }
}
