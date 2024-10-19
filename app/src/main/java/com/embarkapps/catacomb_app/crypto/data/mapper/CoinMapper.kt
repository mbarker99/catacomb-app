package com.embarkapps.catacomb_app.crypto.data.mapper

import com.embarkapps.catacomb_app.crypto.data.network.dto.CoinDto
import com.embarkapps.catacomb_app.crypto.data.network.dto.CoinPriceDto
import com.embarkapps.catacomb_app.crypto.domain.Coin
import com.embarkapps.catacomb_app.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

fun CoinPriceDto.toCoinPrice() : CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault())
    )
}