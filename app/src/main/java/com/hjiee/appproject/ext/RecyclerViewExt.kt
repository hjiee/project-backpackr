package com.hyden.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hjiee.appproject.base.BaseRecyclerView
import java.util.Collections.replaceAll

@BindingAdapter(value = ["bindItems"])
fun RecyclerView.bindItems(items : List<Any>?) {
    items?.let {
        (adapter as? BaseRecyclerView.Adapter<Any,*, Any?>)?.run {
            updateItems(items)
        }
    }
}

