package com.embarkapps.catacomb_app.core.data.network

import com.embarkapps.catacomb_app.BuildConfig


/*
    Ktor doesn't have a "base url" function like Retrofit, so we add this function to
    handle url strings.
 */
fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }
}