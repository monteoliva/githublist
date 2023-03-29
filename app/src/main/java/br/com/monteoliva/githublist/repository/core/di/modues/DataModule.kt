package br.com.monteoliva.githublist.repository.core.di.modues

import android.content.Context
import br.com.monteoliva.githublist.repository.core.RetrofitServer
import dagger.Binds
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
    fun bindRepositoryServer(@ApplicationContext context: Context) : Context = context
}