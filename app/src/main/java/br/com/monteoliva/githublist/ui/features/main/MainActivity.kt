package br.com.monteoliva.githublist.ui.features.main

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint

import br.com.monteoliva.githublist.R
import br.com.monteoliva.githublist.databinding.ActivityMainBinding
import br.com.monteoliva.githublist.repository.core.extensions.isPortrait
import br.com.monteoliva.githublist.repository.core.extensions.observerOnce
import br.com.monteoliva.githublist.repository.core.extensions.visibility
import br.com.monteoliva.githublist.repository.core.extensions.wrapperResult
import br.com.monteoliva.githublist.repository.model.data.Item
import br.com.monteoliva.githublist.repository.model.data.Repositories
import br.com.monteoliva.githublist.ui.adapter.ItemAdapter
import br.com.monteoliva.githublist.ui.features.BaseActivity
import br.com.monteoliva.githublist.utils.OnPaginationListener
import br.com.monteoliva.githublist.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlin.properties.Delegates

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel: MainViewModel by viewModels()
    private var itemAdapter: ItemAdapter by Delegates.notNull()

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun initViews() {
        binding?.appBarMain?.let { setupToolBar(it.findViewById(R.id.toolbar)) }

        ItemAdapter().also { itemAdapter = it }

        binding?.swipeRefresh?.setOnRefreshListener { viewModel.initValue() }
    }

    override fun initViewModel() {
        viewModel.apply {
            initValue()
            page.observerOnce { page ->
                updateList.observerOnce {
                    it.wrapperResult { data ->
                        when (data) {
                            is Repositories -> data.items?.let { it1 ->
                                if (page == 1) { loadList(it1) }
                                else           { loadUpdateList(it1) }
                            }
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
        binding?.swipeRefresh?.isRefreshing?.let { binding?.mainProgress?.visibility(!it) }
        itemAdapter.updateList(items, viewModel.pageNumber)
        binding?.rv?.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter       = itemAdapter
            addOnScrollListener(object : OnPaginationListener(mLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.apply {
                        setProgress(true)
                        isLoading = true
                        increment()
                    }
                }
                override fun isLastPage(): Boolean = viewModel.isLastPage
                override fun isLoading(): Boolean  = viewModel.isLoading
            })
        }
        setLoading(false)
    }

    private fun loadUpdateList(items: MutableList<Item>) {
        itemAdapter.updateList(items, viewModel.pageNumber)
        setLoading(false)
    }

    override fun back() { finish() }
    override fun setLoading(isLoading: Boolean) {
        binding?.apply {
            swipeRefresh?.isRefreshing = isLoading
            mainProgress?.visibility(isLoading)
        }
        setProgress(isLoading)
        viewModel.isLoading = isLoading
    }

    private fun setProgress(isLoading: Boolean) { binding?.progressBar?.visibility(isLoading) }

    companion object {
        private const val NUN_COLUMNS: Int = 2
    }
}