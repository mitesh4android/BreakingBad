package io.github.mitesh.brba.core.domain.repository

import androidx.annotation.Keep
import io.github.mitesh.brba.core.model.BrbaDeviceData
import io.github.mitesh.brba.core.model.BrbaThemeMode
import kotlinx.coroutines.flow.Flow

@Keep
interface DeviceRepository {

    val deviceData: Flow<BrbaDeviceData>

    suspend fun setThemeMode(themeMode: BrbaThemeMode)
}