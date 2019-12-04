package com.hjiee.appproject.data.remote.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {

    @GET("products")
    fun loadItems(
        @Query("page") page : Int
    ) : Single<ProductResponse>

    @GET("products/{id}")
    fun loadDetail(
        @Path("id") id : Int
    ) : Single<ProductResponse>
}