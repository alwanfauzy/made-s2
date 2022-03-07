package com.alwan.made1.di

import com.alwan.made1.core.domain.usecase.AnimeInteractor
import com.alwan.made1.core.domain.usecase.AnimeUseCase
import com.alwan.made1.detail.DetailViewModel
import com.alwan.made1.discover.DiscoverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AnimeUseCase> { AnimeInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DiscoverViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}