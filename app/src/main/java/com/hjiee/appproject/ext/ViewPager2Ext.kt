package com.hjiee.appproject.ext

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.hjiee.appproject.base.BaseRecyclerView

@BindingAdapter(value = ["bindPages"])
fun ViewPager2.bindPages(items : List<Any>?) {
    items?.let {
        (adapter as? BaseRecyclerView.Adapter<Any,*, Any?>)?.run {
            updateItems(items)
        }
    }
}