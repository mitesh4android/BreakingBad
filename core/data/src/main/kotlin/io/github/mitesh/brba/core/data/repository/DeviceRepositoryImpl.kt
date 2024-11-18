package io.github.mitesh.brba.core.data.repository

import io.github.mitesh.brba.core.datastore.DeviceDataSource
import io.github.mitesh.brba.core.domain.repository.DeviceRepository
import io.github.mitesh.brba.core.model.BrbaDeviceData
import io.github.mitesh.brba.core.model.BrbaThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val deviceDataSource: DeviceDataSource,
) : DeviceRepository {

    override val deviceData: Flow<BrbaDeviceData> = deviceDataSource.deviceDataFlow
        .map {
            BrbaDeviceData(
                themeMode = it.themeMode,
            )
        }

    override suspend fun setThemeMode(themeMode: BrbaThemeMode) {
        deviceDataSource.setThemeMode(themeMode)
    }
}