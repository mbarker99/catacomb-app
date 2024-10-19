package com.embarkapps.catacomb_app.crypto.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinHistoryDto(
    val data: List<CoinPriceDto>
)
