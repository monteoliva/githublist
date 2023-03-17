package br.com.monteoliva.githublist.repository.core

import android.content.Context
import androidx.lifecycle.liveData
import br.com.monteoliva.githublist.R
import javax.inject.Inject

import br.com.monteoliva.githublist.repository.api.ApiService
import br.com.monteoliva.githublist.repository.model.Resultado

class RepositoryServer @Inject constructor(private val context: Context) {
    private val server: ApiService
        get() = RetrofitMobile.invoke(context).create(ApiService::class.java)

    fun getList(page: Int) = liveData {
        val q      = "language:kotlin"
        val sort   = "stars"
        val result = server.getList(q, sort, page)
        if (result.isSuccessful) {
            emit(Resultado.Success(data = result.body()))
        }
        else {
            emit(Resultado.Error(exception = Exception(context.getString(R.string.error))))
        }
    }
}