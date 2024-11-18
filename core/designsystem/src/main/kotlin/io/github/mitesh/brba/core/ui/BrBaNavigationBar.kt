package io.github.mitesh.brba.core.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BrBaNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        windowInsets = WindowInsets(0.dp),
        content = content,
    )
}

@Composable
fun RowScope.BrbaNavigationBarItem(
    label: String? = null,
    onSelected: () -> Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
) {
    NavigationBarItem(
        label = {
            label?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        },
        selected = onSelected.invoke(),
        onClick = onClick,
        icon = icon,
        colors = NavigationBarItemDefaults.colors(),
        modifier = Modifier
            .fillMaxWidth(),
    )
}