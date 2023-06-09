package br.com.monteoliva.githublist.repository.core.di.modues

import android.content.Context
import br.com.monteoliva.githublist.repository.api.ApiService
import br.com.monteoliva.githublist.repository.core.RetrofitMobile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun bindContext(@ApplicationContext context: Context) : Context = context

    @Singleton
    @Provides
    fun bindApiService(service: RetrofitMobile) : ApiService = service.apiService
}