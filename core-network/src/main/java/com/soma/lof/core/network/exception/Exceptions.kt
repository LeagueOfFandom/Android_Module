package com.soma.lof.core.network.exception

class EmptyBodyException(message: String? = "") : Exception(message)
class NetworkFailureException(message: String? = "") : Exception(message)
class JwtTokenEmptyException(message: String? = "JWT가 존재하지 않습니다") : Exception(message)