package com.hjiee.appproject.util

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.hjiee.appproject.R

class LineIndicator : LinearLayout {

    private val parentView by lazy {
        LayoutInflater.from(context).inflate(R.layout.line_indicator,this,false)
    }

    constructor(
        context: Context
    ) : super(context) {
        initView()
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet
    ) : super(context, attributeSet) {
        initView()
        getAttrs(attributeSet)
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet, defStyleAttr: Int
    ) : super(context, attributeSet, defStyleAttr) {
        initView()
        getAttrs(attributeSet, defStyleAttr)

    }

    private fun initView() {
        addView(parentView)
    }

    private fun getAttrs(attributeSet: AttributeSet) {
        var typedArray: TypedArray =
            context.obtainStyledAttributes(
                attributeSet,
                R.styleable.LineIndicator
            )
        setTypeArray(typedArray)
    }

    private fun getAttrs(attributeSet: AttributeSet, defStyleAttr: Int) {
        var typedArray: TypedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.LineIndicator,
            defStyleAttr,
            0
        )
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        typedArray.recycle()
    }

}