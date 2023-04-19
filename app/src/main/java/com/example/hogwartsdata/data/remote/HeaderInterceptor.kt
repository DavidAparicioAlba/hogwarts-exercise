package com.example.hogwartsdata.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        return chain.proceed(requestBuilder.build())
    }
}