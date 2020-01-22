package com.cloudwalker.demo.presentation.ui.moviespopular.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cloudwalker.demo.presentation.R
import com.cloudwalker.demo.presentation.main.runtime.Variables
import com.cloudwalker.demo.presentation.ui.commons.OnLoadMore
import com.cloudwalker.demo.presentation.ui.commons.OnMovieClickListener
import com.cloudwalker.demo.presentation.ui.commons.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_footer.view.*
import kotlinx.android.synthetic.main.view_popular_movies.view.*


class AdapterPopularMovies(
    var popularMoviesList: List<Result>,
    var clickListener: OnMovieClickListener,
    var onLoadMore: OnLoadMore
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    fun addMoreMovies(moreMovies: List<Result>) {
        listOfMovies!!.addAll(moreMovies)
        notifyDataSetChanged()
    }

    var FOOTER_VIEW = 101
    var listOfMovies: ArrayList<Result>? = ArrayList(popularMoviesList)
    override fun onCreateViewHolder(parent: ViewGroup,  viewType: Int): RecyclerView.ViewHolder {
        var view: View
        return if (viewType == FOOTER_VIEW) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_footer, parent, false)
            FooterViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_popular_movies, parent, false)
            MyViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            if (holder is FooterViewHolder) {
                holder.bind(onLoadMore)
            } else if (holder is MyViewHolder) {
                holder.bind(listOfMovies!![position], clickListener)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == listOfMovies!!.size) {
            FOOTER_VIEW
        } else super.getItemViewType(position)

    }

    override fun getItemCount(): Int {
        if (listOfMovies == null) {
            return 0
        }
        return if (listOfMovies!!.size === 0) {
            //Return 1 here to show nothing
            1
        } else listOfMovies!!.size + 1

        // Add extra view to show the footer view
    }

    // Now define the viewholder for Normal list item
    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val btnLoadMore = itemView.btnLoadMore
        fun bind(onLoadMore: OnLoadMore) {
            btnLoadMore.setOnClickListener {
                onLoadMore.loadMore()
            }
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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
