package io.github.mitesh.brba.feature.favorate.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import io.github.mitesh.brba.core.model.BrbaCharacter
import io.github.mitesh.brba.feature.favorate.FavoriteRoute

const val FAVORITE_ROUTE = "favorite_route"

fun NavController.navigateFavorite() {
    navigate(
        route = FAVORITE_ROUTE,
        navOptions = navOptions {
            popUpTo(graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        },
    )
}

context(SharedTransitionScope)
fun NavGraphBuilder.favoriteComposable(
    onCharacterClick: (BrbaCharacter) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    composable(
        route = FAVORITE_ROUTE,
    ) {
        FavoriteRoute(
            onCharacterClick = onCharacterClick,
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }
}