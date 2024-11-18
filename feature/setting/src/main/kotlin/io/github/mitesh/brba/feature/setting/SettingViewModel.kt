package io.github.mitesh.brba.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mitesh.brba.core.domain.repository.DeviceRepository
import io.github.mitesh.brba.core.model.BrbaDeviceData
import io.github.mitesh.brba.core.model.BrbaThemeMode
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingData(
    val deviceData: BrbaDeviceData,
)

sealed interface SettingUiState {
    data class Success(val settingData: SettingData) : SettingUiState
    data object Loading : SettingUiState
}

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val deviceRepository: DeviceRepository,
) : ViewModel() {

    val uiState: StateFlow<SettingUiState> = deviceRepository.deviceData
        .map { themeMode ->
            SettingUiState.Success(SettingData(deviceData = themeMode))
        }
        .stateIn(
            scope = viewModelScope,
            initialValue = SettingUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000),
        )

    fun onChangeThemeClick(themeMode: BrbaThemeMode) {
        viewModelScope.launch {
            deviceRepository.setThemeMode(themeMode)
        }
    }
}