package com.ae.moviesapplicationupdate.di

import com.ae.moviesapplicationupdate.ui.movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { MoviesViewModel() }
}