package com.cloudwalker.demo.presentation.ui.landing.dagger

import com.cloudwalker.demo.presentation.main.dagger.annotations.PerActivity
import com.cloudwalker.demo.presentation.main.dagger.components.ApplicationComponent
import com.cloudwalker.demo.presentation.main.dagger.components.MainComponent
import com.cloudwalker.demo.presentation.ui.landing.LandingFragment
import com.cloudwalker.demo.presentation.ui.moviespopular.dagger.module.PopularMoviesModule
import com.cloudwalker.demo.presentation.ui.moviespopular.fragments.PopularMoviesFragment
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ConfigurationModule::class])
interface ConfigurationComponent : MainComponent {
    /* Inject Dependencies of movies Fragment */
    fun inject(landingFragment: LandingFragment)
}