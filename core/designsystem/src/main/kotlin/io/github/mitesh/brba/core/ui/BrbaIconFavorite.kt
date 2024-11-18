package io.github.mitesh.brba.core.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.mitesh.brba.core.theme.BrbaPreviewTheme
import io.github.mitesh.brba.core.theme.favorite

@Composable
fun BrbaIconFavorite(
    enable: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Icon(
        imageVector = if (enable) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
        tint = if (enable) favorite else Color.DarkGray,
        contentDescription = null,
        modifier = modifier
            .size(20.dp)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false),
            ),
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    BrbaPreviewTheme {
        Column {
            BrbaIconFavorite(
                enable = false,
                modifier = Modifier,
            ) {
            }

            BrbaIconFavorite(
                enable = true,
                modifier = Modifier,
            ) {
            }
        }
    }
}