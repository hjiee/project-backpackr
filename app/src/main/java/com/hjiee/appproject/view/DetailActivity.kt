package com.hjiee.appproject.view

import android.graphics.Paint
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.slider.Slider
import com.hjiee.appproject.R
import com.hjiee.appproject.base.BaseActivity
import com.hjiee.appproject.base.BaseRecyclerView
import com.hjiee.appproject.data.remote.network.Body
import com.hjiee.appproject.databinding.ActivityDetailBinding
import com.hjiee.appproject.databinding.PagerDetailItemBinding
import com.hjiee.appproject.util.PageChangedCallbackListener
import com.hyden.ext.loadUrl
import com.hyden.util.LogUtil.LogW
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    private val detailViewModel by inject<DetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getIntExtra("clicked_item",0).let {
            detailViewModel.loadDetailInfo(it)
        }
        initBind()
    }

    override fun initBind() {
        binding.apply {
            vm = detailViewModel
            vpThumnail.apply {
                offscreenPageLimit = 3
                registerOnPageChangeCallback(PageChangedCallbackListener())
                adapter = object : BaseRecyclerView.Adapter<String,PagerDetailItemBinding,String>(
                    layoutId = R.layout.pager_detail_item,
                    bindingVariableId = BR.imageUrl
                ) { }
            }

            fbClose.apply {
                setOnClickListener { finish() }
            }

            tvCost.apply {
                detailViewModel.detailInfo.observe(
                    this@DetailActivity,
                    Observer {
                        when(it.discount_rate.isNullOrEmpty()) {
                            true -> { /* no operation */ }
                            false -> {
                                paintFlags = this.paintFlags.or(Paint.STRIKE_THRU_TEXT_FLAG)
                                setTextAppearance(R.style.DetailCostTheme)
                            }
                        }
                    }
                )
            }
        }
    }
}
