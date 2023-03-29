package br.com.monteoliva.githublist.repository.core

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import javax.inject.Inject

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import br.com.monteoliva.githublist.repository.api.ApiService
import br.com.monteoliva.githublist.repository.core.extensions.wrapperResponse
import br.com.monteoliva.githublist.repository.model.WsResult
import br.com.monteoliva.githublist.repository.model.data.Repositories

class RepositoryServer @Inject constructor(private val context: Context,
                                           private val server: ApiService) {
    private val q    = "language:kotlin"
    private val sort = "stars"

    fun getFirstList(page: Int) : LiveData<WsResult<Repositories?>> {
        val liveData = MutableLiveData<WsResult<Repositories?>>()
        CoroutineScope(Dispatchers.IO).launch {
            liveData.postValue(server.getList(q, sort, page).wrapperResponse(context))
        }
        return liveData
    }

    fun getList(page: Int) = liveData {
        emit(server.getList(q, sort, page).wrapperResponse(context))
    }
}