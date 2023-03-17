package br.com.monteoliva.githublist.repository.core.extensions

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import br.com.monteoliva.githublist.repository.model.WsResult

fun <T> Call<T>.serverWrapper(callback: (WsResult<T>) -> Unit) {
    this.apply {
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == 200) {
                    response.body()?.let {
                        callback.invoke(WsResult<T>().also { it1 ->
                            it1.codeError = 0
                            it1.message   = ""
                            it1.result    = it
                        })
                    }
                }
                else {
                    callback.invoke(WsResult<T>().also {
                        it.codeError = 9
                        it.message   = "Erro no sistema. Entre em contato com o administrador"
                    })
                }
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.invoke(WsResult<T>().also {
                    it.codeError = 9
                    it.message   = "Erro no sistema. Entre em contato com o administrador"
                })
            }
        })
    }
}