package com.embarkapps.catacomb_app.crypto.data.mapper

import com.embarkapps.catacomb_app.crypto.data.network.dto.CoinDto
import com.embarkapps.catacomb_app.crypto.data.network.dto.CoinMarketDto
import com.embarkapps.catacomb_app.crypto.data.network.dto.CoinPriceDto
import com.embarkapps.catacomb_app.crypto.domain.model.Coin
import com.embarkapps.catacomb_app.crypto.domain.model.CoinMarket
import com.embarkapps.catacomb_app.crypto.domain.model.CoinPrice
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

fun CoinMarketDto.toCoinMarket(): CoinMarket {
    return CoinMarket(
        priceUsd = priceUsd,
        marketId = exchangeId
    )
}