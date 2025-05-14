package com.embarkapps.catacomb_app.crypto.presentation.coinlist.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.catacomb_app.crypto.presentation.model.DisplayableNumber
import com.embarkapps.catacomb_app.ui.theme.CatacombTheme
import com.embarkapps.catacomb_app.ui.theme.greenBackground

@Composable
fun PriceChange(
    change: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val changeColor = if (change.value < 0.0) {
        MaterialTheme.colorScheme.error
    } else {
        greenBackground
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (change.value < 0.0) {
                Icons.Default.KeyboardArrowDown
            } else {
                Icons.Default.KeyboardArrowUp
            },
            contentDescription = null,
            modifier = Modifier.size(10.dp),
            tint = changeColor
        )

        Text(text = "${change.formatted} %",
            color = changeColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@PreviewLightDark
@Composable
fun PriceChangePreview() {
    CatacombTheme {
        PriceChange(
            change = DisplayableNumber(value = 2.43, formatted = "2.43")
        )
    }

}