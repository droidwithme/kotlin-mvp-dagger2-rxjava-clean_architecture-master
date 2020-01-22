package com.cloudwalker.demo.presentation.ui.commons



interface OnMovieClickListener {
    fun onMovieSelected(movie: Result)
}

interface OnLoadMore {
    fun loadMore()
}