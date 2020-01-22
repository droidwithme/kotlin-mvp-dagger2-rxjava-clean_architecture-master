package com.cloudwalker.demo.presentation.ui.moviespopular.dagger.component

import com.cloudwalker.demo.presentation.main.dagger.annotations.PerActivity
import com.cloudwalker.demo.presentation.main.dagger.components.ApplicationComponent
import com.cloudwalker.demo.presentation.main.dagger.components.MainComponent
import com.cloudwalker.demo.presentation.ui.moviespopular.dagger.module.PopularMoviesModule
import com.cloudwalker.demo.presentation.ui.moviespopular.fragments.PopularMoviesFragment
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [PopularMoviesModule::class])
interface PopularMoviesComponent : MainComponent {
    /* Inject Dependencies of movies Fragment */
    fun inject(popularMoviesFragment: PopularMoviesFragment)
}