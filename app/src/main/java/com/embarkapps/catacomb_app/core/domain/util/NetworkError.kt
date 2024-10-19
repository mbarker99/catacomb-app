package com.embarkapps.catacomb_app.core.domain.util

import com.embarkapps.catacomb_app.core.domain.util.Error

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN_ERROR
}