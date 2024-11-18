package io.github.mitesh.brba.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import io.github.mitesh.brba.core.datastore.model.DeviceData
import io.github.mitesh.brba.core.model.BrbaThemeMode
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeviceDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    object PreferencesKey {
        val MODE_THEME = intPreferencesKey("MODE_THEME")
    }

    val deviceDataFlow = dataStore.data.map { preferences ->
        DeviceData(
            themeMode = preferences[PreferencesKey.MODE_THEME]?.let {
                BrbaThemeMode.entries[it]
            } ?: BrbaThemeMode.Dark,
        )
    }

    suspend fun setThemeMode(themeMode: BrbaThemeMode) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.MODE_THEME] = themeMode.ordinal
        }
    }
}