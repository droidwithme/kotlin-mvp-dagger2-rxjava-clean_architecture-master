package com.cloudwalker.demo.presentation.ui.landing

import com.cloudwalker.demo.presentation.main.view.MainView
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelR

interface ConfigurationView : MainView {
    // To render received data
    fun renderData(configurationModelR: ConfigurationModelR)
}