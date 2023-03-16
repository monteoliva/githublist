package br.com.monteoliva.githublist.repository.core

import android.content.Context
import javax.inject.Inject

import br.com.monteoliva.githublist.repository.api.ApiService

class RepositoryServer @Inject constructor(private val context: Context) {
    val server: ApiService
        get() = RetrofitMobile.invoke(context).create(ApiService::class.java)






    private enum class Type { LOAD_FIRST, LOAD_UPDATE }
}