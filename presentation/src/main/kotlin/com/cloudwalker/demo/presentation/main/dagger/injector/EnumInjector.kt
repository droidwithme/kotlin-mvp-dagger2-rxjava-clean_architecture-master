package com.cloudwalker.demo.presentation.main.dagger.injector

import com.cloudwalker.demo.presentation.main.application.MainApplication
import com.cloudwalker.demo.presentation.main.dagger.components.ApplicationComponent
import com.cloudwalker.demo.presentation.main.dagger.components.DaggerApplicationComponent
import com.cloudwalker.demo.presentation.main.dagger.modules.ApplicationModule
import com.cloudwalker.demo.presentation.ui.landing.dagger.ConfigurationComponent
import com.cloudwalker.demo.presentation.ui.landing.dagger.ConfigurationModule
import com.cloudwalker.demo.presentation.ui.landing.dagger.DaggerConfigurationComponent
import com.cloudwalker.demo.presentation.ui.moviedetails.dagger.component.DaggerMoviesDetailsComponent
import com.cloudwalker.demo.presentation.ui.moviedetails.dagger.component.MoviesDetailsComponent
import com.cloudwalker.demo.presentation.ui.moviedetails.dagger.module.MoviesDetailsModule
import com.cloudwalker.demo.presentation.ui.moviespopular.dagger.component.DaggerPopularMoviesComponent
import com.cloudwalker.demo.presentation.ui.moviespopular.dagger.component.PopularMoviesComponent
import com.cloudwalker.demo.presentation.ui.moviespopular.dagger.module.PopularMoviesModule
import com.cloudwalker.demo.presentation.ui.nowplaying.dagger.component.DaggerNowPlayingMoviesComponent
import com.cloudwalker.demo.presentation.ui.nowplaying.dagger.component.NowPlayingMoviesComponent
import com.cloudwalker.demo.presentation.ui.nowplaying.dagger.module.NowPlayingMoviesModule
import com.cloudwalker.demo.presentation.ui.searchmovie.dagger.component.DaggerSearchMoviesComponent
import com.cloudwalker.demo.presentation.ui.searchmovie.dagger.component.SearchMoviesComponent
import com.cloudwalker.demo.presentation.ui.searchmovie.dagger.module.SearchMoviesModule

enum class EnumInjector {
    INSTANCE;

    // Initialize & Return Application Component
    fun getApplicationComponent(mainApplication: MainApplication): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(mainApplication))
            .build()
    }

    // Initialize & Return Login Component
    fun getPopularMovieComponent(
        applicationComponent: ApplicationComponent,
        popularMoviesModule: PopularMoviesModule
    ): PopularMoviesComponent {
        return DaggerPopularMoviesComponent.builder()
            .applicationComponent(applicationComponent)
            .popularMoviesModule(popularMoviesModule)
            .build()
    }

    // Initialize & Return Login Component
    fun getNowPlayingMovieComponent(
        applicationComponent: ApplicationComponent,
        nowPlayingMoviesModule: NowPlayingMoviesModule
    ): NowPlayingMoviesComponent {
        return DaggerNowPlayingMoviesComponent.builder()
            .applicationComponent(applicationComponent)
            .nowPlayingMoviesModule(nowPlayingMoviesModule)
            .build()
    }


    // Initialize & Return Login Component
    fun getConfigurationComponent(
        applicationComponent: ApplicationComponent,
        configurationModule: ConfigurationModule
    ): ConfigurationComponent {
        return DaggerConfigurationComponent.builder()
            .applicationComponent(applicationComponent)
            .configurationModule(configurationModule)
            .build()
    }

    // Initialize & Return Login Component
    fun getMovieDetailsComponent(
        applicationComponent: ApplicationComponent,
        moviesDetailsModule: MoviesDetailsModule
    ): MoviesDetailsComponent {
        return DaggerMoviesDetailsComponent.builder()
            .applicationComponent(applicationComponent)
            .moviesDetailsModule(moviesDetailsModule)
            .build()
    }

    // Initialize & Return Login Component
    fun getSearchMovieComponent(
        applicationComponent: ApplicationComponent,
        searchMoviesModule: SearchMoviesModule
    ): SearchMoviesComponent {
        return DaggerSearchMoviesComponent.builder()
            .applicationComponent(applicationComponent)
            .searchMoviesModule(searchMoviesModule)
            .build()
    }

}