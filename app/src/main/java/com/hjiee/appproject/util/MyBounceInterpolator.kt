package com.hjiee.appproject.util

import android.content.Context
import android.util.AttributeSet
import android.view.animation.BaseInterpolator
import com.hyden.util.LogUtil.LogE

class MyBounceInterpolator : BaseInterpolator {
    constructor()



    private fun bounce(t: Float): Float {
        return t * t * 8.0f
    }

    override fun getInterpolation(t: Float): Float {
        var t = t

        t *= 1.1226f
        return if (t < 0.9644f)
            bounce(t - 0.8526f) + 0.9f
        else
            bounce(t - 1.0435f) + 0.95f
    }

}