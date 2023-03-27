package br.com.monteoliva.githublist.ui.features.main

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githublist.R
import br.com.monteoliva.githublist.databinding.ActivityMainBinding
import br.com.monteoliva.githublist.repository.core.extensions.isPortrait
import br.com.monteoliva.githublist.repository.core.extensions.visibility
import br.com.monteoliva.githublist.repository.core.extensions.wrapper
import br.com.monteoliva.githublist.repository.model.data.Item
import br.com.monteoliva.githublist.repository.model.data.Repositories
import br.com.monteoliva.githublist.ui.adapter.ItemAdapter
import br.com.monteoliva.githublist.ui.features.BaseActivity
import br.com.monteoliva.githublist.utils.OnPaginationListener
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
                updateFirstList().observe(this@MainActivity) {
                    it.wrapper { data ->
                        when (data) {
                            is Repositories -> data.items?.let { it1 -> loadList(it1) }
                            is String       -> {
                                binding?.frameLayout?.let { it1 ->
                                    Snackbar.make(it1, data, Snackbar.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadList(items: MutableList<Item>) {
        val mLayoutManager = GridLayoutManager(
            this,
            when (this.isPortrait()) {
                true -> 1
                else -> 2
            })
        binding?.swipeRefresh?.isRefreshing?.let { setLoading(!it) }
        itemAdapter?.updateList(items, viewModel.pageNumber)
        binding?.rv?.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter       = itemAdapter
            addOnScrollListener(object : OnPaginationListener(mLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.apply {
                        isLoading = true
                        increment()
                    }
                }
                override fun isLastPage(): Boolean = viewModel.isLastPage
                override fun isLoading(): Boolean  = viewModel.isLoading
            })


            addScroll(mLayoutManager)
        }

        binding?.swipeRefresh?.isRefreshing = false
        setLoading(false)
    }

    override fun back() { finish() }
    override fun setLoading(isLoading: Boolean) { binding?.mainProgress?.visibility(isLoading) }

    private fun RecyclerView.addScroll(layoutManager: GridLayoutManager) {
        addOnScrollListener(object : OnPaginationListener(layoutManager) {
            override fun loadMoreItems() {
                viewModel.apply {
                    isLoading = true
                    increment()
                }
            }
            override fun isLastPage(): Boolean = viewModel.isLastPage
            override fun isLoading(): Boolean  = viewModel.isLoading
        })
    }


    companion object {
        private const val NUN_COLUMNS: Int = 2
    }
}