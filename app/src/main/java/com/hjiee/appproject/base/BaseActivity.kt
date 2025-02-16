package com.hjiee.appproject.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hyden.util.LogUtil.LogW
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<B : ViewDataBinding>(
    private val layoutId : Int
) : AppCompatActivity() {

    lateinit var binding: B
    val compositeDisposable = CompositeDisposable()

    abstract fun initBind()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater,layoutId,null,false)
        setContentView(binding.root)
        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}