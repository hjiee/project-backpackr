package com.hjiee.appproject.view

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.hyojin.util.EndlessRecyclerViewScrollListener
import com.hjiee.appproject.R
import com.hjiee.appproject.base.BaseActivity
import com.hjiee.appproject.base.BaseRecyclerView
import com.hjiee.appproject.data.remote.network.Body
import com.hjiee.appproject.data.remote.network.ProductResponse
import com.hjiee.appproject.databinding.ActivityIndexBinding
import com.hjiee.appproject.databinding.RecyclerIndexItemBinding
import org.koin.android.ext.android.inject

class IndexActivity : BaseActivity<ActivityIndexBinding>(R.layout.activity_index) {

    private val indexViewModel by inject<IndexViewModel>()

    private val endlessScrollListener by lazy {
        object : EndlessRecyclerViewScrollListener(binding.rvIndex.layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                indexViewModel.loadMore(page + 1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        indexViewModel.loadProducts()
    }

    override fun initBind() {
        binding.apply {
            vm = indexViewModel
            rvIndex.apply {
                adapter = object : BaseRecyclerView.Adapter<ProductResponse, RecyclerIndexItemBinding, Body>(
                        layoutId = R.layout.recycler_index_item,
                        bindingVariableId = BR.body
                    ) {}
                addOnScrollListener(endlessScrollListener)
            }
        }
    }
}
