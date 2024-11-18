package io.github.mitesh.brba.core.common.result

import io.github.mitesh.brba.core.common.result.Result.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data object Loading : Result<Nothing>
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable) : Result<Nothing>
}

val Result<*>.succeeded
    get() = this is Success && data != null

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Success<T>)?.data ?: fallback
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> = this
    .map<T, Result<T>> { Success(it) }
    .onStart { emit(Result.Loading) }
    .catch { e -> emit(Result.Error(e)) }