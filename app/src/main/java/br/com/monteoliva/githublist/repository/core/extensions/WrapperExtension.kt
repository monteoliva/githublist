package br.com.monteoliva.githublist.repository.core.extensions

import br.com.monteoliva.githublist.repository.model.WsResult

fun <T> WsResult<T?>.wrapper(response: (Any) -> Unit) {
    this.let { result ->
        when (result) {
            is WsResult.Success<*> -> {
                result.data?.let { data -> response.invoke(data) }
            }
            is WsResult.Error -> {
                response.invoke(result.exception.message.toString())
            }
        }
    }
}