package com.hjiee.appproject.view

import androidx.core.util.LogWriter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hjiee.appproject.data.remote.network.Body
import com.hjiee.appproject.data.repository.ProductRepository
import com.hyden.util.LogUtil.LogW

class IndexViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {


    private val _productsInfo = MutableLiveData<List<Body>>()
    val productsInfo: LiveData<List<Body>> get() = _productsInfo

    private val _isMore = MutableLiveData<Boolean>()
    val isMore: LiveData<Boolean> get() = _isMore

    fun loadMore(
        page: Int = 1
    ) {
        _isMore.value = true
        loadProducts(page)
    }

    fun loadProducts(
        page: Int = 1
    ) {
        productRepository.loadProducts(
            page,
            success = { data ->
                if (_isMore.value ?: false) {
                    // 더불러오기
                    _productsInfo.value = _productsInfo.value?.let {
                        it.toMutableList().apply {
                            addAll(data)
                        }
                    }
                } else {
                    _productsInfo.value = data
                    _isMore.value = false
                }
            },
            failure = {
                LogW(it)
            })
    }
}