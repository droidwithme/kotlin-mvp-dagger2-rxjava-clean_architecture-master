package com.cloudwalker.demo.presentation.ui.commons


/**
 * Two common contracts used
 * to pass the movies: Result object between fragments
 */
interface OnMovieClickListener {
    fun onMovieSelected(movie: Result)
}

interface OnLoadMore {
    fun loadMore()
}