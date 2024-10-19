package com.embarkapps.catacomb_app.crypto.data.network.dto

import com.embarkapps.catacomb_app.crypto.data.network.dto.CoinDto
import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponseDto(
    val data: List<CoinDto>
)
