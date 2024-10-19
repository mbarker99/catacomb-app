package com.embarkapps.catacomb_app.crypto.presentation.coinlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.catacomb_app.crypto.domain.model.Coin
import com.embarkapps.catacomb_app.crypto.presentation.model.CoinUi
import com.embarkapps.catacomb_app.crypto.presentation.model.toCoinUi
import com.embarkapps.catacomb_app.ui.theme.CatacombTheme

@Composable
fun CoinListItem(
    coinUi: CoinUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(coinUi.iconRes),
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(85.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = coinUi.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = coinUi.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$ ${coinUi.priceUsd.formatted}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.size(8.dp))
            PriceChange(
                change = coinUi.changePercent24Hr
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListItemPreview() {
    CatacombTheme {
        CoinListItem(
            coinUi = previewCoin,
            onClick = { /*TODO*/ },
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }

}

internal val previewCoin = Coin(
    id = "bitcoin",
    rank = 1,
    name = "Bitcoin",
    symbol = "BTC",
    marketCapUsd = 123456789.0,
    priceUsd = 65432.1,
    changePercent24Hr = 0.11324
).toCoinUi()