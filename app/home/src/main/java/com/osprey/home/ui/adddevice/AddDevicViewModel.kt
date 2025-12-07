    package com.osprey.home.ui.adddevice

    import android.app.Application
    import androidx.lifecycle.viewModelScope
    import com.osprey.core.viewmodel.BaseUiStateViewModel
    import com.osprey.domain.home.model.local.Device_Profile
    import com.osprey.domain.home.usecase.GetAllDeviceUseCase
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.launch
    import javax.inject.Inject


    @HiltViewModel
    class AddDevicViewModel @Inject constructor(
        val app: Application,
        private val getAllDeviceUseCase: GetAllDeviceUseCase,
    ) : BaseUiStateViewModel<AddDeviceUiState, AddDeviceUiEvent, AddDeviceUiAction>(app) {
        override fun initialState(): AddDeviceUiState = AddDeviceUiState()
        override fun onCreate() {
            super.onCreate()
            loadDeviceSample()
        }

        override fun handleAction(action: AddDeviceUiAction) {
            when (action) {
                is AddDeviceUiAction.AddBluetoothDevice -> addDeviceToList(action.device)
            }
        }

        private fun addDeviceToList(device: Device_Profile) {
            val current = uiState.value.deviceProfile.toMutableList()
            if (current.none { it.description == device.description }) {
                current.add(device)
                updateState { it.copy(deviceProfile = current) }
            }
        }

        private fun loadDeviceSample() {
            viewModelScope.launch(Dispatchers.IO) {
                val listDevice = getAllDeviceUseCase()
                updateState { it.copy(deviceProfile = listDevice) }
            }
        }
    }
