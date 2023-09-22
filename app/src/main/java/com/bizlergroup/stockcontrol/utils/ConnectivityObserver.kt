package com.bizlergroup.stockcontrol.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe(): Flow<StatusObserve>

    enum class StatusObserve{
        Available,Unavailable,Losing,Lost
    }
}