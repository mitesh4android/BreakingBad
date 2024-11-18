package io.github.mitesh.brba.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.mitesh.brba.core.domain.repository.DeviceRepository
import io.github.mitesh.brba.core.model.BrbaDeviceData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface MainUiState {
    data class Success(val deviceData: BrbaDeviceData) : MainUiState
    data object Loading : MainUiState
}

@HiltViewModel
class MainViewModel @Inject constructor(
    deviceRepository: DeviceRepository,
) : ViewModel() {

    val uiState: StateFlow<MainUiState> = deviceRepository.deviceData
        .map { MainUiState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            initialValue = MainUiState.Loading,
            started = SharingStarted.WhileSubscribed(5000),
        )
}