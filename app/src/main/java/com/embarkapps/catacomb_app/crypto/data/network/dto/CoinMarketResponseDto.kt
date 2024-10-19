package com.embarkapps.catacomb_app.crypto.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinMarketResponseDto(
    val data: List<CoinMarketDto>
)