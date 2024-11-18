package io.github.mitesh.brba.main

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import io.github.mitesh.brba.feature.bottombar.navigation.BottomBar
import io.github.mitesh.brba.feature.bottombar.navigation.bottomBarComposable
import io.github.mitesh.brba.feature.detail.navigaion.detailComposable

@Composable
fun BrbaApp(
    appState: BrbaAppState = rememberAppState(),
) {
    SharedTransitionLayout {
        NavHost(
            navController = appState.navController,
            startDestination = BottomBar,
            modifier = Modifier.fillMaxSize(),
        ) {
            bottomBarComposable(
                navController = appState.navController,
            )
            detailComposable()
        }
    }
}