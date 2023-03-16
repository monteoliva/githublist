package br.com.monteoliva.githublist.ui.features.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githublist.R
import br.com.monteoliva.githublist.databinding.ActivitySplashScreenBinding
import br.com.monteoliva.githublist.ui.features.BaseActivity
import br.com.monteoliva.githublist.ui.features.main.MainActivity

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : BaseActivity<ActivitySplashScreenBinding>() {
    private val seconds: Long = 2000

    override fun getLayoutId(): Int = R.layout.activity_splash_screen
    override fun initViews() { load() }
    override fun initViewModel() {}
    override fun back() { finish() }

    private fun load() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            overridePendingTransition(R.anim.lefttoright, R.anim.stable)
        }, seconds)
    }
}