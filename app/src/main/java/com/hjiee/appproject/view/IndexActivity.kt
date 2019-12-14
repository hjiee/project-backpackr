package com.hjiee.appproject.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
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
import com.hjiee.appproject.util.ConstValueUtil.Companion.CLICKITEMID
import com.hjiee.appproject.util.ConstValueUtil.Companion.CLICKITEMTHUMBNAIL
import com.hyden.util.ItemClickListener
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

    private val clickEventListener by lazy {
        object : ItemClickListener {
            override fun <T> onItemClick(item: T, view: View) {
                Intent(this@IndexActivity, DetailActivity::class.java).apply {
                    putExtra(CLICKITEMID, (item as Body).id)
                    putExtra(CLICKITEMTHUMBNAIL, (item as Body).thumbnail_520)
                    val optios = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this@IndexActivity,
                        view,
                        ViewCompat.getTransitionName(view) ?: ""
                    )
                    startActivity(this, optios.toBundle())
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        indexViewModel.loadProducts()
        initBind()
    }

    override fun initBind() {
        binding.apply {
            vm = indexViewModel
            rvIndex.apply {
                adapter = object :
                    BaseRecyclerView.Adapter<ProductResponse, RecyclerIndexItemBinding, Body>(
                        layoutId = R.layout.recycler_index_item,
                        bindingVariableId = BR.body,
                        event = clickEventListener
                    ) {}
                addOnScrollListener(endlessScrollListener)
            }
        }
    }
}
