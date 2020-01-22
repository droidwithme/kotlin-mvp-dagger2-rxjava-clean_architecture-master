package com.cloudwalker.demo.presentation.ui.nowplaying.dagger.component

import com.cloudwalker.demo.presentation.main.dagger.annotations.PerActivity
import com.cloudwalker.demo.presentation.main.dagger.components.ApplicationComponent
import com.cloudwalker.demo.presentation.main.dagger.components.MainComponent
import com.cloudwalker.demo.presentation.ui.moviespopular.dagger.module.PopularMoviesModule
import com.cloudwalker.demo.presentation.ui.moviespopular.fragments.PopularMoviesFragment
import com.cloudwalker.demo.presentation.ui.nowplaying.dagger.module.NowPlayingMoviesModule
import com.cloudwalker.demo.presentation.ui.nowplaying.fragments.NowPlayingMoviesFragment
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [NowPlayingMoviesModule::class])
interface NowPlayingMoviesComponent : MainComponent {
    /* Inject Dependencies of movies Fragment */
    fun inject(nowPlaying: NowPlayingMoviesFragment)
}