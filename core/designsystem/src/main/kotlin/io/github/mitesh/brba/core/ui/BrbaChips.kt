package io.github.mitesh.brba.core.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.mitesh.brba.core.theme.BrbaPreviewTheme

@Composable
fun BrbaChips(
    modifier: Modifier = Modifier,
    chipList: List<String>,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
    ) {
        chipList.forEach { chip ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(16.dp),
                    )
                    .background(MaterialTheme.colorScheme.onTertiary)
                    .padding(horizontal = 12.dp, vertical = 6.dp),
            ) {
                Text(
                    text = chip,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    BrbaPreviewTheme {
        BrbaChips(
            chipList = listOf(
                "Breaking Bad1",
                "Breaking Bad3",
                "Breaking Bad3",
                "Breaking Bad3",
                "Breaking Bad3",
                "Breaking Bad3",
                "Breaking Bad5",
                "Better Call Saul6",
            ),
        )
    }
}