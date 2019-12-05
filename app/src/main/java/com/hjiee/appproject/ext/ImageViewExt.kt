package com.hyden.ext

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestOptions
import com.hjiee.appproject.R
import com.hjiee.appproject.util.ImageTransformType
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

@BindingAdapter(value = ["loadUrl","tranformType"])
fun ImageView.loadUrl(
    url: String?,
    type: ImageTransformType?
) {
    url?.let {
        val multiTransformation = MultiTransformation<Bitmap>(
            RoundedCornersTransformation(14, 0)
        )
        val imageRequestBulider = Glide.with(this)
            .load(it)
            .error(R.drawable.baseline_storefront_24)

        imageRequestBulider.apply {
            when (type) {
                ImageTransformType.ROUND -> {
                    apply(RequestOptions.bitmapTransform(multiTransformation))
                }
                ImageTransformType.FIT -> {
                    apply(RequestOptions.fitCenterTransform().centerCrop())
                }
            }

        }.let { it.into(this) }


    }
}

