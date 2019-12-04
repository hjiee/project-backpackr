package com.hjiee.appproject.di

import com.hjiee.appproject.view.DetailViewModel
import com.hjiee.appproject.view.IndexViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { IndexViewModel(get()) }
    viewModel { DetailViewModel() }
}