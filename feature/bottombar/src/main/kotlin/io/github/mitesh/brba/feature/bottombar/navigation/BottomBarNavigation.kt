package io.github.mitesh.brba.feature.bottombar.navigation

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.github.mitesh.brba.feature.bottombar.BottomBarScreen
import kotlinx.serialization.Serializable

@Serializable
object BottomBar

context(SharedTransitionScope)
fun NavGraphBuilder.bottomBarComposable(
    navController: NavHostController,
) {
    composable<BottomBar> {
        BottomBarScreen(
            navController = navController,
            animatedVisibilityScope = this,
        )
    }
}