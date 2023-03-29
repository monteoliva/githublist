package br.com.monteoliva.githublist.repository.core.extensions

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observerOnce(observer: (T) -> Unit) {
    removeObserver {}
    observeForever {
        it?.let { observer.invoke(it) }
    }
}