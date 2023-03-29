package br.com.monteoliva.githublist.repository.core.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings.Secure
import android.util.Base64
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresPermission
import com.bumptech.glide.Glide
import kotlinx.coroutines.ExperimentalCoroutinesApi


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

val currentTime: Long get() = System.currentTimeMillis()

fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context).load(url).into(this)
}

fun Context.isPortrait() : Boolean =
    this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

fun Context.isLandscape() : Boolean =
    this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

fun String.baseEncrypt() : String = Base64.encodeToString(this.encodeToByteArray(), Base64.DEFAULT)
fun String.baseDecrypt() : String = Base64.decode(this, Base64.DEFAULT).toString()

//@SuppressLint("HardwareIds")
//fun Context.getAndroidId() : String = Secure.getString(this.contentResolver, this)

@ExperimentalCoroutinesApi
@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
@Suppress("DEPRECATION")
fun Context.isOnline() : Boolean {
    var connected: Boolean
    @Suppress("LiftReturnOrAssignment")
    this.let {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = cm.activeNetwork ?: return false
            val actNw = cm.getNetworkCapabilities(networkCapabilities) ?: return false
            connected = actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
        else {
            connected = cm.activeNetworkInfo?.isConnectedOrConnecting == true
        }
    }
    return connected
}