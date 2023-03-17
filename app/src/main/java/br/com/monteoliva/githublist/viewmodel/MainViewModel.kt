package br.com.monteoliva.githublist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import br.com.monteoliva.githublist.repository.core.RepositoryServer
import br.com.monteoliva.githublist.repository.model.Resultado
import br.com.monteoliva.githublist.repository.model.data.Item
import br.com.monteoliva.githublist.repository.model.data.Repositories

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RepositoryServer) : ViewModel() {
    val page: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val responseServer: MutableLiveData<MutableList<Item>> by lazy { MutableLiveData<MutableList<Item>>() }

    fun initValue() {
        page.removeObserver {}
        page.postValue(1)
    }

    fun updateList() : LiveData<Resultado<Repositories?>> = repository.getList(pageNumber)

    fun increment() { page.postValue(page.value?.let { it + 1 }) }

    private val pageNumber : Int get() = page.value ?: 0
    val resultServer: MutableList<Item>? get() = responseServer.value
}