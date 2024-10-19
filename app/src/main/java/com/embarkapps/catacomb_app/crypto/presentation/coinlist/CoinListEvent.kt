package com.embarkapps.catacomb_app.crypto.presentation.coinlist

import com.embarkapps.catacomb_app.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}