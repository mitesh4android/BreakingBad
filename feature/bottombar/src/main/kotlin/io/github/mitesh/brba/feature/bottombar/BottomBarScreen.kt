package io.github.mitesh.brba.feature.bottombar

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.mitesh.brba.core.ui.BrBaNavigationBar
import io.github.mitesh.brba.core.ui.BrbaNavigationBarItem
import io.github.mitesh.brba.feature.detail.navigaion.navigateToDetail
import io.github.mitesh.brba.feature.favorate.navigation.favoriteComposable
import io.github.mitesh.brba.feature.favorate.navigation.navigateFavorite
import io.github.mitesh.brba.feature.list.navigation.LIST_ROUTE
import io.github.mitesh.brba.feature.list.navigation.listComposable
import io.github.mitesh.brba.feature.list.navigation.navigateList
import io.github.mitesh.brba.feature.setting.navigation.navigateSetting
import io.github.mitesh.brba.feature.setting.navigation.settingComposable

@Composable
fun SharedTransitionScope.BottomBarScreen(
    navController: NavHostController,
    navTabController: NavHostController = rememberNavController(),
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        bottomBar = {
            val currentRoute =
                navTabController.currentBackStackEntryAsState().value?.destination?.route
            BrBaNavigationBar(
                modifier = Modifier
                    .navigationBarsPadding(),
            ) {
                for (tab in remember { Tab.entries }) {
                    BrbaNavigationBarItem(
                        onSelected = { currentRoute == tab.route },
                        onClick = {
                            if (currentRoute == tab.route) return@BrbaNavigationBarItem
                            when (tab) {
                                Tab.LIST -> navTabController.navigateList()
                                Tab.FAVORITE -> navTabController.navigateFavorite()
                                Tab.SETTING -> navTabController.navigateSetting()
                            }
                        },
                        icon = {
                            Icon(
                                painterResource(id = tab.icon),
                                contentDescription = tab.label,
                            )
                        },
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        NavHost(
            navController = navTabController,
            startDestination = LIST_ROUTE,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            listComposable(
                navigateToDetail = { navController.navigateToDetail(character = it) },
                animatedVisibilityScope = animatedVisibilityScope,
            )
            favoriteComposable(
                onCharacterClick = { navController.navigateToDetail(character = it) },
                animatedVisibilityScope = animatedVisibilityScope,
            )
            settingComposable()
        }
    }
}