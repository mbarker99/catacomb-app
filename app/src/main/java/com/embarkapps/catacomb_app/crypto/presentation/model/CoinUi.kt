package com.embarkapps.catacomb_app.crypto.presentation.model

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.embarkapps.catacomb_app.core.presentation.util.getDrawableIdForCoin
import com.embarkapps.catacomb_app.crypto.domain.model.Coin
import com.embarkapps.catacomb_app.crypto.presentation.coindetail.DataPoint
import java.util.Locale

/*
    To keep the Presentation layer as "dumb" as possible, it is helpful
    to create UI-specific models that already have any formatted values available.
    This is so we can keep the business logic in the domain layer.

    Things like Icons are also usually put in these UI models as well, since those
    elements are purely UI-based.
 */
data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int,
    val coinPriceHistory: List<DataPoint> =  emptyList()
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        rank = rank,
        name =  name,
        symbol = symbol,
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        priceUsd = priceUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}
