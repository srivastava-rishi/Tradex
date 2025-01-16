package com.rsstudio.tradex.component.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rsstudio.tradex.presentation.theme.midnightBlue

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(30.dp)
            .width(1.dp)
            .background(midnightBlue)
    )
}