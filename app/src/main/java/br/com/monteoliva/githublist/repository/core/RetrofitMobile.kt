package br.com.monteoliva.githublist.repository.core

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import br.com.monteoliva.githublist.R

object RetrofitMobile {
    operator fun invoke(context: Context): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(context.getString(R.string.API_URL))
            .client(OkHttp3.invoke())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
