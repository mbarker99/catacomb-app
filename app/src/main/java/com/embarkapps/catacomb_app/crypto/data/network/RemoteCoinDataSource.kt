package com.embarkapps.catacomb_app.crypto.data.network

import com.embarkapps.catacomb_app.core.data.network.constructUrl
import com.embarkapps.catacomb_app.core.data.network.safeCall
import com.embarkapps.catacomb_app.core.domain.util.NetworkError
import com.embarkapps.catacomb_app.core.domain.util.Result
import com.embarkapps.catacomb_app.core.domain.util.map
import com.embarkapps.catacomb_app.crypto.data.mapper.toCoin
import com.embarkapps.catacomb_app.crypto.data.mapper.toCoinMarket
import com.embarkapps.catacomb_app.crypto.data.mapper.toCoinPrice
import com.embarkapps.catacomb_app.crypto.data.network.dto.CoinHistoryDto
import com.embarkapps.catacomb_app.crypto.data.network.dto.CoinMarketResponseDto
import com.embarkapps.catacomb_app.crypto.data.network.dto.CoinsResponseDto
import com.embarkapps.catacomb_app.crypto.domain.CoinDataSource
import com.embarkapps.catacomb_app.crypto.domain.model.Coin
import com.embarkapps.catacomb_app.crypto.domain.model.CoinMarket
import com.embarkapps.catacomb_app.crypto.domain.model.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/${coinId}/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }

    override suspend fun getCoinMarkets(coinId: String): Result<List<CoinMarket>, NetworkError> {
        return safeCall<CoinMarketResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets/${coinId}/markets")
            ) {
                parameter("limit", 50)
            }
        }.map { response ->
            response.data.map { it.toCoinMarket() }
        }

    }
}