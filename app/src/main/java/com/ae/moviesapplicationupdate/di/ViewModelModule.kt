package com.ae.moviesapplicationupdate.di

import com.ae.moviesapplicationupdate.data.services.MoviesServices
import com.ae.moviesapplicationupdate.ui.movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule: Module = module {

    /**
     * Provide MoviesViewModel
     */
    viewModel<MoviesViewModel> { MoviesViewModel(get<MoviesServices>()) }
}