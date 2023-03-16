package br.com.monteoliva.githublist.repository.core

import okhttp3.OkHttpClient

object OkHttp3 {
    operator fun invoke(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(MicroServiceInterceptor())
            .build()
    }
}