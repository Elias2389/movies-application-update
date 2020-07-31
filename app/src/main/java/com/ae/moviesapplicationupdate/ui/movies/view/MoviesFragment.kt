package com.ae.moviesapplicationupdate.ui.movies.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.ae.moviesapplicationupdate.R
import com.ae.moviesapplicationupdate.common.dto.Status
import com.ae.moviesapplicationupdate.data.services.MoviesServices
import com.ae.moviesapplicationupdate.ui.movies.viewmodel.MoviesViewModel
import org.koin.android.ext.android.inject

@Suppress("UNREACHABLE_CODE")
class MoviesFragment : Fragment() {

    private val moviesViewModel by inject<MoviesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.movies.observe(viewLifecycleOwner, Observer {
            Log.i("Message:" , it.toString())
        })
    }

}