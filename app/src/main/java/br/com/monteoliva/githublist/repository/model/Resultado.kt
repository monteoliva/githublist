package br.com.monteoliva.githublist.repository.model

sealed class Resultado<out R> {
    data class Success<out T>(val data: T?) : Resultado<T?>()
    data class Error(val exception: Exception) : Resultado<Nothing>()
}