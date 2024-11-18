package io.github.mitesh.brba.feature.list.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import io.github.mitesh.brba.core.model.BrbaCharacter
import io.github.mitesh.brba.feature.list.ListRoute

const val LIST_ROUTE = "list_route"

fun NavController.navigateList() {
    navigate(
        route = LIST_ROUTE,
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
fun NavGraphBuilder.listComposable(
    navigateToDetail: (BrbaCharacter) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    composable(
        route = LIST_ROUTE,
    ) {
        ListRoute(
            navigateToDetail = navigateToDetail,
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }
}