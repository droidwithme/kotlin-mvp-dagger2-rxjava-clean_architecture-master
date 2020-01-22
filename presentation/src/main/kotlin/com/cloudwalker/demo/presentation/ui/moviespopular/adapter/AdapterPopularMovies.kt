package com.cloudwalker.demo.presentation.ui.moviespopular.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.runtime.Variables
import com.cloudwalker.demo.presentation.ui.moviespopular.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_popular_movies.view.*


class AdapterPopularMovies(var popularMoviesList: List<Result>, var clickListener: OnMovieClickListener) : RecyclerView.Adapter<AdapterPopularMovies.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_popular_movies, parent, false)
    )

    override fun getItemCount() = popularMoviesList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(popularMoviesList[position], clickListener)
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val movieBanner = view.itemImage
        private val movieDescription = view.itemDescription
        private val movieTitle = view.itemTitle
        private val moviePopularity = view.itemPopularity
        private val movieVotes = view.itemVoteCount
        private val movieSeeDetails = view.itemSeeMore

        fun bind(movie: Result, clickListener: OnMovieClickListener) {
            Picasso.get()
                .load(Variables.imageUrl + movie.backdropPath)
                .placeholder(R.drawable.cloudwalker_logo)
                .into(movieBanner)
            movieDescription.text = movie.overview
            movieTitle.text = "Title: ${movie.title}"
            moviePopularity.text = "Popularity: ${movie.popularity}"
            movieVotes.text = "Votes: ${movie.voteCount}"
            movieSeeDetails.setOnClickListener {
                clickListener.onMovieSelected(movie)
            }



        }
    }
}

interface OnMovieClickListener {
    fun onMovieSelected(movie: Result)
}