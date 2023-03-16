package br.com.monteoliva.githublist.ui.features.main

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githublist.R
import br.com.monteoliva.githublist.databinding.ActivityMainBinding
import br.com.monteoliva.githublist.ui.features.BaseActivity
import br.com.monteoliva.githublist.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViews() {}

    override fun initViewModel() {}

    override fun back() { finish() }
}