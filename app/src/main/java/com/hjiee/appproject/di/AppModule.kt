package com.hjiee.appproject.di

import com.hjiee.appproject.data.repository.ProductRepository
import org.koin.dsl.module

val appModule = module {
    single { ProductRepository(get()) }
}