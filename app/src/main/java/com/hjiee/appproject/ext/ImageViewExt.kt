package com.hyden.ext

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestOptions
import com.hjiee.appproject.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

@BindingAdapter(value = ["loadUrl"])
fun ImageView.loadUrl(url: String?) {
    url?.let {
        val multiTransformation = MultiTransformation<Bitmap>(
            RoundedCornersTransformation(14,0)
        )
        Glide.with(this)
            .load(it)
            .error(R.drawable.baseline_storefront_24)
            .apply(RequestOptions.bitmapTransform(multiTransformation))
            .override(172,172)
//            .apply(RequestOptions.fitCenterTransform().centerCrop())
            .into(this)
    }
}