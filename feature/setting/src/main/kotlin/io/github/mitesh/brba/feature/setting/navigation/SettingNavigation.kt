package io.github.mitesh.brba.feature.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import io.github.mitesh.brba.feature.setting.SettingRoute

const val SETTING_ROUTE = "setting_route"

fun NavController.navigateSetting() {
    navigate(
        route = SETTING_ROUTE,
        navOptions = navOptions {
            popUpTo(graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        },
    )
}

fun NavGraphBuilder.settingComposable() {
    composable(
        route = SETTING_ROUTE,
    ) {
        SettingRoute()
    }
}