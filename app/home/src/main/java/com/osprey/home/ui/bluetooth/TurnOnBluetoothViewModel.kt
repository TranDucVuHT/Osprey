package com.osprey.home.ui.bluetooth

import android.app.Application
import com.osprey.core.viewmodel.BaseUiStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TurnOnBluetoothViewModel @Inject constructor(
    val app: Application,
) : BaseUiStateViewModel<TurnOnBluetoothUiState, TurnOnBluetoothUiEvent, TurnOnBluetoothUiAction>(app) {
    override fun initialState(): TurnOnBluetoothUiState = TurnOnBluetoothUiState()

    override fun handleAction(action: TurnOnBluetoothUiAction) {
        when (action) {
            else -> {}
        }
    }
}
