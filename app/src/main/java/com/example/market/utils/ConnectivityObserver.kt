package com.example.market.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<StatusObserve>

    enum class StatusObserve{
        Available,Unavailable,Losing,Lost
    }
}