package com.hjiee.appproject.util

import androidx.viewpager2.widget.ViewPager2
import com.hyden.util.LogUtil

class PageChangedCallbackListener : ViewPager2.OnPageChangeCallback() {
    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)

    }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        LogUtil.LogW("test : $position")
    }
}