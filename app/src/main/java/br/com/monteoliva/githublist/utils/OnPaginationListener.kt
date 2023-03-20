package br.com.monteoliva.githublist.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener

abstract class OnPaginationListener : OnScrollListener {
    private var mLayoutManager: RecyclerView.LayoutManager

    constructor(layoutManager: LinearLayoutManager) { mLayoutManager = layoutManager }
    constructor(layoutManager: GridLayoutManager)   { mLayoutManager = layoutManager }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) { return }

        val totalItemCount           = mLayoutManager.itemCount
        val visibleItemCount         = mLayoutManager.childCount
        var firstVisibleItemPosition = 0

        if (mLayoutManager is LinearLayoutManager) {
            firstVisibleItemPosition = (mLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        }
        else if (mLayoutManager is GridLayoutManager) {
            firstVisibleItemPosition = (mLayoutManager as GridLayoutManager).findFirstVisibleItemPosition()
        }

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}