package com.example.market.data.utils

import com.example.market.utils.SharedPref
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val isNotAuthorizationRequest = request.url.encodedPathSegments.contains("auth")
        val newRequest = if (isNotAuthorizationRequest) {
            request
        } else {
            request.newBuilder().addHeader(
                "Authorization", "Bearer-${SharedPref.pref.getString("token", "")}"
            ).build()
        }
        return chain.proceed(newRequest)
    }
}