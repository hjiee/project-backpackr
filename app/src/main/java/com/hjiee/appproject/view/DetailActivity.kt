package com.hjiee.appproject.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
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
import com.hjiee.appproject.util.ConstValueUtil.Companion.CLICKITEMID
import com.hjiee.appproject.util.ConstValueUtil.Companion.CLICKITEMTHUMBNAIL
import com.hjiee.appproject.util.ImageTransformType
import com.hjiee.appproject.util.MyBounceInterpolator
import com.hjiee.appproject.util.PageChangedCallbackListener
import com.hyden.ext.loadUrl
import com.hyden.util.LogUtil.LogW
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.pager_detail_item.view.*
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

    override fun initBind() {
        binding.apply {
            vm = detailViewModel
            vpThumnail.apply {
                offscreenPageLimit = 3
                registerOnPageChangeCallback(PageChangedCallbackListener())
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
