package com.embarkapps.catacomb_app.crypto.presentation.coindetail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.catacomb_app.crypto.domain.model.CoinMarket
import com.embarkapps.catacomb_app.crypto.presentation.model.toDisplayableNumber
import com.embarkapps.catacomb_app.ui.theme.CatacombTheme

@Composable
fun CoinMarketListItem(
    market: CoinMarket,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = market.marketId,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "$${market.priceUsd.toDisplayableNumber().formatted}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@PreviewLightDark
@Composable
private fun CoinMarketListItemPreview() {
    CatacombTheme {
        CoinMarketListItem(
            market = CoinMarket(
                marketId = "Binance",
                priceUsd = 6263.8645034633024446
            )
        )
    }
}