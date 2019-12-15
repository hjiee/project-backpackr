package com.hjiee.appproject.di

import com.hjiee.appproject.BuildConfig
import com.hjiee.appproject.data.remote.network.ProductsApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val connectTimeout = 30L
private const val readTimeout = 30L
private const val writeTimeout = 30L

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.MINUTES)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization","7748e1daa1f94a7bd1e43b1a5d24dc21")
                    .addHeader("Content-Type","application/json")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    single<Converter.Factory> {
        GsonConverterFactory.create()
    }

    single<CallAdapter.Factory> {
        RxJava2CallAdapterFactory.create()
    }

    single {
        Retrofit
            .Builder()
            .client(get())
            .baseUrl(BuildConfig.BASEURL)
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
            .create(ProductsApi::class.java)
    }
}