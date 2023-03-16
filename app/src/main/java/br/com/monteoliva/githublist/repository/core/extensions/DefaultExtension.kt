package br.com.monteoliva.githublist.repository.core.extensions

import android.view.View

fun View.enabled(active: Boolean) { this.isEnabled = active }

fun random() : Long = (Math.random() * 9999999).toLong()

fun String.validation() : String {
    return if (this.trim().isEmpty()) { "" }
    else if (this.trim().isBlank()) { "" }
    else if (this.trim() == "null") { "" }
    else                            { this.trim() }
}

fun View.visible()   { this.visibility = View.VISIBLE   }
fun View.invisible() { this.visibility = View.INVISIBLE }
fun View.gone()      { this.visibility = View.GONE      }

fun View.visibility(hasVisible: Boolean) {
    if (hasVisible) { this.visible() } else { this.gone() }
}

fun View.isVisible():   Boolean = this.visibility == View.VISIBLE
fun View.isInvisible(): Boolean = this.visibility == View.INVISIBLE
fun View.isGone():      Boolean = this.visibility == View.GONE
