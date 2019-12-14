package com.hyden.util

import android.view.View

interface ItemClickListener {
    fun <T> onItemClick(item: T,view : View)
}