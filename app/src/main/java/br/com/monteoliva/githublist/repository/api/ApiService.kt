package br.com.monteoliva.githublist.repository.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

import br.com.monteoliva.githublist.repository.model.WsResult
import br.com.monteoliva.githublist.repository.model.list.Repos

interface ApiService {
    @GET("/search/repositories")
    fun getAttachment(@Query("q") q: String = "language:kotlin",
                      @Query("sort") sort: String = "stars",
                      @Query("page") page: Int) : Call<WsResult<Repos>>
}