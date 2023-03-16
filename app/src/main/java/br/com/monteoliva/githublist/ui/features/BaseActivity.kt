package br.com.monteoliva.githublist.ui.features

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import br.com.monteoliva.githublist.R
import br.com.monteoliva.githublist.repository.core.extensions.leftToRight
import br.com.monteoliva.githublist.repository.core.extensions.rightToLeft
import br.com.monteoliva.githublist.repository.core.extensions.topToBottom
import br.com.monteoliva.githublist.repository.core.extensions.bottomToTop

abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {
    protected var binding: T? = null
    private var actionBar: ActionBar? = null
    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
    }

    override fun onResume() {
        super.onResume()
        initLocalViews()
    }

    private fun initLocalViews() {
        startInitViews()
        startInitViewModel()
    }

    private fun startInitViews() {
        Handler(Looper.getMainLooper()).postDelayed({ initViews() }, 60)
    }

    private fun startInitViewModel() {
        Handler(Looper.getMainLooper()).postDelayed({ initViewModel() }, 60)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            back()
            true
        }
        else {
            super.onKeyDown(keyCode, event)
        }
    }

    fun toolbarTitle(title: String) { mToolbar?.findViewById<TextView>(R.id.toolbar_title)?.text = title }

    fun msgBox(msg: String) {
        AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .apply {
                setTitle(R.string.btn_error)
                setMessage(msg)
                setCancelable(false)
                setPositiveButton("OK") { _: DialogInterface?, _: Int -> {} }
                create().show()
            }
    }

    fun animLeftToRight() { this.leftToRight() }
    fun animRightToLeft() { this.rightToLeft() }
    fun animTopToBottom() { this.topToBottom() }
    fun animBottomToTop() { this.bottomToTop() }

    @LayoutRes
    abstract fun getLayoutId() : Int
    abstract fun initViews()
    abstract fun initViewModel()
    abstract fun back()

    companion object {
        private const val TAG = "BaseActivity"
    }
}