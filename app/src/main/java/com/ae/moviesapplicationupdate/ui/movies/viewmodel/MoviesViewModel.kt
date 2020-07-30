package com.ae.moviesapplicationupdate.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ae.moviesapplicationupdate.common.dto.MoviesResponse
import com.ae.moviesapplicationupdate.data.services.MoviesServices
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import retrofit2.Retrofit

class MoviesViewModel(private val moviesServices: MoviesServices): ViewModel() {

    val movies: LiveData<Response<MoviesResponse>> = liveData(Dispatchers.IO) {
        val result = moviesServices.getPopularMovies()
        //emit(Resource.loading(data = null))

        try {
            emit(result)
            //emit(Resource.success(result))
        } catch (e: Exception) {
            //emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }

}