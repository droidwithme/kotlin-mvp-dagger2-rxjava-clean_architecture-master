package com.cloudwalker.demo.presentation.main.fragment

import com.cloudwalker.demo.presentation.ui.dashboard.fragments.DashboardFragment
import com.cloudwalker.demo.presentation.ui.landing.LandingFragment
import com.cloudwalker.demo.presentation.ui.moviedetails.fragments.MoviesDetailsFragment
import com.cloudwalker.demo.presentation.ui.movieshome.fragments.MoviesFragment
import com.cloudwalker.demo.presentation.ui.moviespopular.fragments.PopularMoviesFragment
import com.cloudwalker.demo.presentation.ui.splash.fragments.SplashFragment

/**
 * Enum To Get Fragment Names
 *
 * Created by Praveen on 06-08-2018.
 */
enum class FragmentEnum(val code: String) {
    SPLASHSCREEN(SplashFragment::class.java.simpleName),
    LANDSCREEN(LandingFragment::class.java.simpleName),
    MOVIESHOME(MoviesFragment::class.java.simpleName),
    MOVIEDETAILS(MoviesDetailsFragment::class.java.simpleName),
    POPULARMOVIESSCREEN(PopularMoviesFragment::class.java.simpleName),
    NOWPLAYINGMOVIESSCREEN(PopularMoviesFragment::class.java.simpleName),
    SEARCHMOVIES(PopularMoviesFragment::class.java.simpleName),
}
