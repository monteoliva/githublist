package br.com.monteoliva.githublist.repository.core

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import javax.inject.Inject

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

import br.com.monteoliva.githublist.R
import br.com.monteoliva.githublist.repository.api.ApiService
import br.com.monteoliva.githublist.repository.model.WsResult
import br.com.monteoliva.githublist.repository.model.data.Repositories

class RepositoryServer @Inject constructor(private val context: Context) {
    private val q    = "language:kotlin"
    private val sort = "stars"
    private val server: ApiService
        get() = RetrofitMobile.invoke(context).create(ApiService::class.java)

    fun getFirstList(page: Int) : LiveData<WsResult<Repositories?>> {
        val liveData = MutableLiveData<WsResult<Repositories?>>()
        CoroutineScope(IO).launch {
            val result = server.getList(q, sort, page)
            if (result.isSuccessful) {
                liveData.postValue(WsResult.Success(data = result.body()))
            }
            else {
                liveData.postValue(WsResult.Error(exception = Exception(context.getString(R.string.error))))
            }
        }
        return liveData
    }

    fun getList(page: Int) = liveData {
        val result = server.getList(q, sort, page)
        if (result.isSuccessful) {
            emit(WsResult.Success(data = result.body()))
        }
        else {
            emit(WsResult.Error(exception = Exception(context.getString(R.string.error))))
        }
    }
}