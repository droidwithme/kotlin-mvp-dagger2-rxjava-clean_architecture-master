package com.cloudwalker.demo.presentation.ui.moviespopular.views

import com.cloudwalker.demo.presentation.main.view.MainView
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR

interface PopularMovieView : MainView {
    // To render received data
    fun renderData(popularMoviesModelR: PopularMoviesModelR)
}