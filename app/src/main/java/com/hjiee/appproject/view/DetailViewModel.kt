package com.hjiee.appproject.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hjiee.appproject.base.BaseViewModel
import com.hjiee.appproject.data.remote.network.Body
import com.hjiee.appproject.data.repository.ProductRepository
import com.hyden.util.LogUtil

class DetailViewModel(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private val _detailInfo = MutableLiveData<Body>()
    val detailInfo: LiveData<Body> get() = _detailInfo

    private val _thumbnailList = MutableLiveData<List<String>>()
    val thumbnailList: LiveData<List<String>> get() = _thumbnailList

    fun loadDetailInfo(
        id: Int
    ) {
        addDisposable(
            productRepository.detailInfo(
                id = id,
                success = { data ->
                    _detailInfo.value = data[0]
                    _thumbnailList.value = data[0].thumbnail_list_320?.split("#")
                },
                failure = {
                    LogUtil.LogW(it)
                })
        )
    }

}