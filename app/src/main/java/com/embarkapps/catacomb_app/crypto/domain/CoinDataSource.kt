package com.embarkapps.catacomb_app.crypto.domain

import com.embarkapps.catacomb_app.core.domain.util.NetworkError
import com.embarkapps.catacomb_app.core.domain.util.Result
import com.embarkapps.catacomb_app.crypto.domain.model.Coin
import com.embarkapps.catacomb_app.crypto.domain.model.CoinMarket
import com.embarkapps.catacomb_app.crypto.domain.model.CoinPrice
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins() : Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
    suspend fun getCoinMarkets(
        coinId: String,
    ): Result<List<CoinMarket>, NetworkError>
}