package br.com.monteoliva.githublist.ui.features.main

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githublist.R
import br.com.monteoliva.githublist.databinding.ActivityMainBinding
import br.com.monteoliva.githublist.repository.core.extensions.isPortrait
import br.com.monteoliva.githublist.repository.core.extensions.visibility
import br.com.monteoliva.githublist.repository.model.WsResult
import br.com.monteoliva.githublist.repository.model.data.Item
import br.com.monteoliva.githublist.ui.adapter.ItemAdapter
import br.com.monteoliva.githublist.ui.features.BaseActivity
import br.com.monteoliva.githublist.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel: MainViewModel by viewModels()
    private var itemAdapter: ItemAdapter? = null

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun initViews() {
        binding?.appBarMain?.let { setupToolBar(it.findViewById(R.id.toolbar)) }

        ItemAdapter().also { itemAdapter = it }

        binding?.swipeRefresh?.setOnRefreshListener { viewModel.initValue() }
    }

    override fun initViewModel() {
        viewModel.apply {
            initValue()
            page.observe(this@MainActivity) {
                updateList().observe(this@MainActivity) {
                    it?.let { resultado ->
                        when(resultado) {
                            is WsResult.Success -> {
                                resultado.data?.let { data -> data.items?.let { it1 -> loadList(it1) } }
                            }
                            is WsResult.Error -> {
                                binding?.frameLayout?.let { it1 ->
                                    Snackbar.make(
                                        it1,
                                        resultado.exception.message.toString(),
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadList(items: MutableList<Item>) {
        binding?.swipeRefresh?.isRefreshing?.let { setLoading(!it) }
        itemAdapter?.updateList(items)
        binding?.rv?.apply {
            setHasFixedSize(true)
          //layoutManager =
            layoutManager = when (context.isPortrait()) {
                true -> LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                else -> GridLayoutManager(context, NUN_COLUMNS)
            }
            adapter = itemAdapter



        }

        binding?.swipeRefresh?.isRefreshing = false
        setLoading(false)
    }

    override fun back() { finish() }
    override fun setLoading(isLoading: Boolean) { binding?.mainProgress?.visibility(isLoading) }

    companion object {
        private val NUN_COLUMNS: Int = 2
    }
}