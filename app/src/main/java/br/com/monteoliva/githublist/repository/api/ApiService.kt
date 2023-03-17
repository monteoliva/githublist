package br.com.monteoliva.githublist.repository.api

import br.com.monteoliva.githublist.repository.model.Resultado
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

import br.com.monteoliva.githublist.repository.model.data.Repositories

interface ApiService {
    @GET("/search/repositories")
    suspend fun getList(
        @Query("q")    q: String,
        @Query("sort") sort: String,
        @Query("page") page: Int) : Response<Repositories?>
}