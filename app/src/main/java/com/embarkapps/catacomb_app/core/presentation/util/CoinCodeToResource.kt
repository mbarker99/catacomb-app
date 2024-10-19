package com.embarkapps.catacomb_app.core.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import com.embarkapps.catacomb_app.R


fun getDrawableIdForCoin(symbol: String): Int {
    return when (symbol.uppercase()) {
        else -> R.drawable.ic_launcher_foreground
    }
}
