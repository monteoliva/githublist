package br.com.monteoliva.githublist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import br.com.monteoliva.githublist.repository.core.RepositoryServer
import br.com.monteoliva.githublist.repository.model.WsResult
import br.com.monteoliva.githublist.repository.model.data.Repositories

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RepositoryServer) : ViewModel() {
    val page: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    var isLastPage: Boolean = false
    var isLoading: Boolean  = false

    fun initValue() {
        page.removeObserver {}
        page.postValue(1)
    }

    val updateFirstList : LiveData<WsResult<Repositories?>> get() = repository.getFirstList(pageNumber)
    val updateList      : LiveData<WsResult<Repositories?>> get() = repository.getList(pageNumber)

    fun increment() { page.postValue(page.value?.let { it + 1 }) }

    val pageNumber : Int get() = page.value ?: 0
}
