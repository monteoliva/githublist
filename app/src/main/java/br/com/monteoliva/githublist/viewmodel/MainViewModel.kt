package br.com.monteoliva.githublist.viewmodel

import androidx.lifecycle.ViewModel
import br.com.monteoliva.githublist.repository.core.RepositoryServer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repositoryServer: RepositoryServer) : ViewModel() {



}