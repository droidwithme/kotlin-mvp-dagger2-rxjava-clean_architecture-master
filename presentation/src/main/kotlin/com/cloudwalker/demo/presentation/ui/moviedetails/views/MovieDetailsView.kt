package com.cloudwalker.demo.presentation.ui.moviedetails.views

import com.cloudwalker.demo.presentation.main.view.MainView
import com.cloudwalker.demo.presentation.ui.moviedetails.model.MoviesDetailsModelR
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR

interface MovieDetailsView : MainView {
    // To render received data
    fun renderData(moviesDetailsModelR: MoviesDetailsModelR)
}