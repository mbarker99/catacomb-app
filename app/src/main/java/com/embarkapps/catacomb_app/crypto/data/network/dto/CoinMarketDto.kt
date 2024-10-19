package com.embarkapps.catacomb_app.crypto.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinMarketDto(
    val exchangeId: String,
    val priceUsd: Double,
)