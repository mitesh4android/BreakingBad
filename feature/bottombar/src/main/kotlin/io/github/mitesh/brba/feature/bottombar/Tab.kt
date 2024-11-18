package io.github.mitesh.brba.feature.bottombar

import io.github.mitesh.brba.feature.favorate.navigation.FAVORITE_ROUTE
import io.github.mitesh.brba.feature.list.navigation.LIST_ROUTE
import io.github.mitesh.brba.feature.setting.navigation.SETTING_ROUTE

enum class Tab(
    val label: String,
    val icon: Int,
    val route: String,
) {
    LIST(label = "Character", icon = R.drawable.ic_account_cowboy_hat, route = LIST_ROUTE),
    FAVORITE(label = "Favorite", icon = R.drawable.ic_heart, route = FAVORITE_ROUTE),
    SETTING(label = "Setting", icon = R.drawable.ic_flask_empty, route = SETTING_ROUTE),
}