package com.cloudwalker.demo.presentation.ui.moviedetails.dagger.component

import com.cloudwalker.demo.presentation.main.dagger.annotations.PerActivity
import com.cloudwalker.demo.presentation.main.dagger.components.ApplicationComponent
import com.cloudwalker.demo.presentation.main.dagger.components.MainComponent
import com.cloudwalker.demo.presentation.ui.moviedetails.dagger.module.MoviesDetailsModule
import com.cloudwalker.demo.presentation.ui.moviedetails.fragments.MoviesDetailsFragment
import com.cloudwalker.demo.presentation.ui.moviespopular.fragments.PopularMoviesFragment
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [MoviesDetailsModule::class])
interface MoviesDetailsComponent : MainComponent {
    /* Inject Dependencies of movies Fragment */
    fun inject(moviesDetailsFragment: MoviesDetailsFragment)
}