package com.hjiee.appproject.view

import android.animation.ObjectAnimator
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.core.view.marginBottom
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.hjiee.appproject.R
import com.hjiee.appproject.base.BaseActivity
import com.hjiee.appproject.base.BaseRecyclerView
import com.hjiee.appproject.databinding.ActivityDetailBinding
import com.hjiee.appproject.databinding.PagerDetailItemBinding
import com.hjiee.appproject.ext.setMargins
import com.hjiee.appproject.util.ConstValueUtil.Companion.CLICKITEMID
import com.hjiee.appproject.util.MyBounceInterpolator
import com.hyden.util.LogUtil.LogW
import org.koin.android.ext.android.inject

class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    private val detailViewModel by inject<DetailViewModel>()
    private val valueStartY = -50f
    private val valueEndY = 0f
    private val valueDuration = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.apply {
            getIntExtra(CLICKITEMID, 0).let { detailViewModel.loadDetailInfo(it) }
        }
        initBind()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        binding.cvNoti.apply {
            LogW("${binding.btnBuy.height}")
            LogW("${binding.btnBuy.marginBottom}")
            setMargins(bottom = binding.btnBuy.height + binding.btnBuy.marginBottom + 40)
        }
    }

    override fun initBind() {
        binding.apply {
            vm = detailViewModel
            vpThumnail.apply {
                offscreenPageLimit = 3
                adapter = object : BaseRecyclerView.Adapter<String, PagerDetailItemBinding, String>(
                    layoutId = R.layout.pager_detail_item,
                    bindingVariableId = BR.imageUrl
                ) {}
                indicator.setViewPager(this)
                currentItem = 1
                adapter?.registerAdapterDataObserver(indicator.adapterDataObserver)
            }

            fbClose.apply {
                setOnClickListener { finish() }
            }

            btnBuy.apply {
                ObjectAnimator.ofFloat(this, "translationY", valueStartY, valueEndY).apply {
                    duration = valueDuration
                    interpolator = MyBounceInterpolator()
                    repeatCount = 0
                    start()
                }
            }

            // 할인 정보가 없을 경우 텍스트 스타일 변경
            detailViewModel.detailInfo.observe(
                this@DetailActivity,
                Observer {
                    when (it.discount_rate.isNullOrEmpty()) {
                        true -> {
                            tvDiscountCost.visibility = View.GONE
                            tvDiscountRate.visibility = View.GONE
                        }
                        false -> {
                            tvCost.apply {
                                paintFlags = this.paintFlags.or(Paint.STRIKE_THRU_TEXT_FLAG)
                                setTextAppearance(R.style.DetailCostTheme)
                            }
                        }
                    }
                }
            )
        }
    }
}
