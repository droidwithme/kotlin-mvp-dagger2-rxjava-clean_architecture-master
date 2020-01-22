package com.cloudwalker.demo.presentation.ui.nowplaying.views

import com.cloudwalker.demo.presentation.main.view.MainView
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR
import com.cloudwalker.demo.presentation.ui.nowplaying.model.NowPlayingMoviesModelR

interface NowPlayingMovieView : MainView {
    // To render received data
    fun renderData(nowPlayingMoviesModelR: NowPlayingMoviesModelR)
}