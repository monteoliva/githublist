package br.com.monteoliva.githublist.repository.core

import android.content.Context
import javax.inject.Inject

import br.com.monteoliva.githublist.repository.api.ApiService
import br.com.monteoliva.githublist.repository.core.extensions.serverWrapper
import br.com.monteoliva.githublist.repository.model.WsResult
import br.com.monteoliva.githublist.repository.model.list.Repos

class RepositoryServer @Inject constructor(private val context: Context) {
    val server: ApiService
        get() = RetrofitMobile.invoke(context).create(ApiService::class.java)

    fun getList(page: Int, callback: (WsResult<Repos>) -> Unit) {
        val q    = "language:kotlin"
        val sort = "stars"
        server.getList(q, sort, page).serverWrapper { callback.invoke(it) }
    }

    private enum class Type { LOAD_FIRST, LOAD_UPDATE }
}