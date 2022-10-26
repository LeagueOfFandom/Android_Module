package com.soma.lof.core

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.*

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher (val lofAppDispatchers: LofAppDispatchers)

enum class LofAppDispatchers {
    IO
}