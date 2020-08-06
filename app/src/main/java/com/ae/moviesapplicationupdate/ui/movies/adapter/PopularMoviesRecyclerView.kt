package com.ae.moviesapplicationupdate.ui.movies.adapter

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.ae.moviesapplicationupdate.BuildConfig
import com.ae.moviesapplicationupdate.R
import com.ae.moviesapplicationupdate.common.dto.ResultsItem
import com.ae.moviesapplicationupdate.databinding.ItemBinding


import com.bumptech.glide.Glide


class PopularMoviesRecyclerView(private val context: Context):
    RecyclerView.Adapter<PopularMoviesRecyclerView.ViewHolder>() {
    private var moviesList: ArrayList<ResultsItem> = ArrayList()

    fun addItems(results: List<ResultsItem>) {
        moviesList.addAll(results)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(
            R.layout.item,
            p0, false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(moviesList[i], context)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemBinding.bind(itemView)

        fun bind(resultsItem: ResultsItem, context: Context) {
            binding.apply {
                title.text = resultsItem.title
                description.text = resultsItem.overview
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_IMAGES + resultsItem.backdropPath)
                    .centerCrop()
                    .into(image)
            }

            binding.cardContainer.setOnClickListener {view ->
                goToDetail(resultsItem, view)
            }


        }

        private fun goToDetail(resultsItem: ResultsItem, view: View) {
            view.findNavController().navigate(R.id.action_moviesFragment_to_detailFragment)
        }

    }


}