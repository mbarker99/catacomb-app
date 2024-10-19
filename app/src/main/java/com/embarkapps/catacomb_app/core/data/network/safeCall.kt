package com.embarkapps.catacomb_app.core.data.network

import com.embarkapps.catacomb_app.core.domain.util.NetworkError
import com.embarkapps.catacomb_app.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext


/*
    Note: catching generic exceptions within a suspend function may cause issues.
    If the coroutine is cancelled, it will throw a cancellation exception and be caught.
    As a quick fix, we can add the coroutineContext.ensureActive() line.
 */
suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch(e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN_ERROR)
    }

    return responseToResult(response)
}