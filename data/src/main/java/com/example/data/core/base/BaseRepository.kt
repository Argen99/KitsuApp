package com.example.data.core.base

import com.example.domain.either.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {
    internal fun <T> makeNetworkRequest(
        gatherIfSucceed: ((T) -> Unit)? = null,
        request: suspend () -> T
    ) =
        flow<Either<String, T>> {
            request().also {
                gatherIfSucceed?.invoke(it)
                emit(Either.Right(value = it))
            }
        }.flowOn(Dispatchers.IO).catch { exception ->
            emit(Either.Left(value = exception.localizedMessage ?: "Error Occurred!"))
        }
}