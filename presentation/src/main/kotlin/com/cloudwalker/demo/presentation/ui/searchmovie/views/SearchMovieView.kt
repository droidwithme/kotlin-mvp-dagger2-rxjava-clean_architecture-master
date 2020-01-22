package com.cloudwalker.demo.presentation.ui.searchmovie.views

import com.cloudwalker.demo.presentation.main.view.MainView
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR
import com.cloudwalker.demo.presentation.ui.nowplaying.model.NowPlayingMoviesModelR
import com.cloudwalker.demo.presentation.ui.searchmovie.model.SearchMoviesModelR

interface SearchMovieView : MainView {
    // To render received data
    fun renderData(searchMoviesModelR: SearchMoviesModelR)
}