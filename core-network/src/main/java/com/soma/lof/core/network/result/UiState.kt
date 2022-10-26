package com.soma.lof.core.result

import com.soma.lof.core.result.UiState.*

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class UiState<out R> {
    data class Success<T>(val data: T): UiState<T>()
    data class Error(val exception: Exception): UiState<Nothing>()
    object Loading: UiState<Nothing>()

    override fun toString(): String {
        return when(this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [UiState] is of type [Success] & holds non-null [Success.data].
 */
val UiState<*>.succeeded
    get() = this is Success && data != null

fun <T> UiState<T>.successOrNull(): T? {
    return (this as? Success<T>)?.data
}

val <T> UiState<T>.data: T?
    get() = (this as? Success)?.data


