package io.github.mitesh.brba.core.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import io.github.mitesh.brba.core.designsystem.R
import io.github.mitesh.brba.core.theme.BrbaPreviewTheme

@Composable
fun BrbaTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    hazeState: HazeState,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = title ?: LocalContext.current.getString(R.string.top_bar_title),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Transparent),
        modifier = modifier
            .padding(start = 0.dp)
            .hazeChild(
                hazeState,
                style = HazeDefaults.style(
                    blurRadius = 12.dp,
                    noiseFactor = 0.1f,
                ),
            )
            .fillMaxWidth(),
        actions = actions,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    BrbaPreviewTheme {
        BrbaTopAppBar(
            title = "Title",
            hazeState = HazeState(),
        )
    }
}