package com.hjiee.appproject.data.repository.source

import com.hjiee.appproject.data.remote.network.Body
import io.reactivex.disposables.Disposable

interface ProductDataSource {

    fun loadProducts(
        page: Int,
        success: (List<Body>) -> Unit,
        failure: (String) -> Unit
    ): Disposable

    fun detailInfo(
        id: Int,
        success: (List<Body>) -> Unit,
        failure: (String) -> Unit
    ): Disposable
}