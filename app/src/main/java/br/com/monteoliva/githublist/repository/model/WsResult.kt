package br.com.monteoliva.githublist.repository.model

sealed class WsResult<out R> {
    data class Success<out T>(val data: T?) : WsResult<T?>()
    data class Error(val exception: Exception) : WsResult<Nothing>()
}