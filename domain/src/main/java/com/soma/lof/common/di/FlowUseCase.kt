package com.soma.lof.common.di

import com.soma.lof.core_network.result.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: P): Flow<UiState<R>> = execute(parameters)
        .catch { e -> emit(UiState.Error(Exception())) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<UiState<R>>
}