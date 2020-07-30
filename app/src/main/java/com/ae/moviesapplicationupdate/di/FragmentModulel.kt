package com.ae.moviesapplicationupdate.di

import com.ae.moviesapplicationupdate.ui.movies.view.MoviesFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.core.module.Module
import org.koin.dsl.module

val fragmentModule: Module = module {
    fragment { MoviesFragment() }
}