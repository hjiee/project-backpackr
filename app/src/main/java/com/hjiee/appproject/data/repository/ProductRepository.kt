package com.hjiee.appproject.data.repository

import com.hjiee.appproject.data.remote.network.ProductsApi
import com.hjiee.appproject.data.remote.network.Body
import com.hjiee.appproject.data.repository.source.ProductDataSource
import com.hjiee.appproject.ext.isSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductRepository(
    private val productsApi: ProductsApi
) : ProductDataSource {

    override fun loadProducts(
        page: Int,
        success: (List<Body>) -> Unit,
        failure: (String) -> Unit
    ): Disposable {
        return productsApi.loadItems(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                when(response.statusCode.isSuccess()) {
                    true -> success.invoke(response.body)
                    false -> failure.invoke(response.statusCode.toString())
                }
            }, {
                failure.invoke(it.toString())
            })
    }

    override fun detailInfo(
        id: Int,
        success: (List<Body>) -> Unit,
        failure: (String) -> Unit
    ): Disposable {
        return productsApi.loadDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                when(response.statusCode.isSuccess()) {
                    true -> success.invoke(response.body)
                    false -> failure.invoke(response.statusCode.toString())
                }
            }, {
                failure.invoke(it.toString())
            })
    }
}