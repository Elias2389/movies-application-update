package com.ae.moviesapplicationupdate.ui.movies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ae.moviesapplicationupdate.BuildConfig
import com.ae.moviesapplicationupdate.common.dto.Status
import com.ae.moviesapplicationupdate.databinding.FragmentMoviesBinding
import com.ae.moviesapplicationupdate.ui.movies.adapter.PopularMoviesRecyclerView
import com.ae.moviesapplicationupdate.ui.movies.viewmodel.MoviesViewModel
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject


class MoviesFragment : Fragment() {

    private val binding get() = moviesBinding!!
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: PopularMoviesRecyclerView
    private var moviesBinding: FragmentMoviesBinding? = null

    private val moviesViewModel by inject<MoviesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        moviesBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        getLatestMovie()
        getPopularMovies()

    }

    private fun getLatestMovie() {
        moviesViewModel.latest.observe(viewLifecycleOwner, Observer { resourse ->
            when(resourse.status) {
                Status.LOADING -> {
                    print("loading")
                }
                Status.SUCCESS -> {
                    Glide.with(requireContext())
                        .load(BuildConfig.BASE_URL_IMAGES + resourse?.data?.body()?.results?.get(2)?.posterPath)
                        .fitCenter()
                        .into(moviesBinding?.latestImage!!)

                    resourse?.data?.body()?.results?.let { adapter.addItems(it) }
                }
                Status.ERROR -> {}
            }

        })
    }


    private fun getPopularMovies() {
        moviesViewModel.popularMovies.observe(viewLifecycleOwner, Observer { resourse ->
            when(resourse.status) {
                Status.LOADING -> {
                    print("loading")
                }
                Status.SUCCESS -> {
                    resourse?.data?.body()?.results?.let {
                        adapter.addItems(it)
                        adapter.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {}
            }

        })
    }

    private fun setupAdapter() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        moviesBinding?.popularMoviesContainer?.layoutManager = layoutManager
        adapter = PopularMoviesRecyclerView(requireContext())
        moviesBinding?.popularMoviesContainer?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
            moviesBinding = null
    }

}