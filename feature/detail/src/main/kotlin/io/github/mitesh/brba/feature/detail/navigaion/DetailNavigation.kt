package io.github.mitesh.brba.feature.detail.navigaion

import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.mitesh.brba.core.model.BrbaCharacter
import io.github.mitesh.brba.feature.detail.DetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class Detail(val id: Long, val image: String)

fun NavController.navigateToDetail(character: BrbaCharacter) {
    this.navigate(
        io.github.mitesh.brba.feature.detail.navigaion.Detail(
            id = character.charId,
            image = character.img
        ),
    )
}

context(SharedTransitionScope)
fun NavGraphBuilder.detailComposable() {
    composable<io.github.mitesh.brba.feature.detail.navigaion.Detail> {
        DetailRoute(
            animatedVisibilityScope = this,
        )
    }
}