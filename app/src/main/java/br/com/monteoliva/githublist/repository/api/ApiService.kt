package br.com.monteoliva.githublist.repository.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

import br.com.monteoliva.githublist.repository.model.WsResult
import br.com.monteoliva.githublist.repository.model.list.Repos

interface ApiService {
    @GET("/search/repositories")
    fun getList(@Query("q")    q: String,
                @Query("sort") sort: String,
                @Query("page") page: Int) : Call<WsResult<Repos>>
}