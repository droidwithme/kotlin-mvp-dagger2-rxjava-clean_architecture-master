package com.cloudwalker.demo.presentation.ui.moviedetails.dagger.module

import com.cloudwalker.demo.domain.executors.PostExecutionThread
import com.cloudwalker.demo.domain.executors.ThreadExecutor
import com.cloudwalker.demo.domain.fetchers.DomainFetcher
import com.cloudwalker.demo.domain.interactors.Interactor
import com.cloudwalker.demo.domain.modules.moviesdetails.interactor.MovieDetailsInteractor
import com.cloudwalker.demo.domain.modules.popularmovies.interactor.PopularMoviesInteractor
import com.cloudwalker.demo.presentation.main.dagger.annotations.PerActivity
import com.cloudwalker.demo.presentation.main.dagger.modules.MainModule
import com.cloudwalker.demo.presentation.main.runtime.Constants
import com.cloudwalker.demo.presentation.ui.moviedetails.model.MoviesDetailsModelQ
import com.cloudwalker.demo.presentation.ui.moviedetails.transformer.MoviesDetailsConverter
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelQ
import com.cloudwalker.demo.presentation.ui.moviespopular.transformer.PopularMoviesConverter
import com.cloudwalker.utils.Utils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Named

private val TAG = MoviesDetailsModule::class.java.simpleName

@Module
class MoviesDetailsModule
constructor(
    private val moviesDetailsModelQ: MoviesDetailsModelQ,
    private val gson: Gson,
    private val utils: Utils
) : MainModule {

    private lateinit var moviesDetailsInteractor: Interactor

    @Provides
    @PerActivity
    @Named(Constants.movieDetailsInteractor)
    override fun provideInteractor(
        domainFetcher: DomainFetcher,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): Interactor {
        moviesDetailsInteractor = MovieDetailsInteractor(
            MoviesDetailsConverter(gson, utils).transformModel(moviesDetailsModelQ),
                    gson,
            domainFetcher,
            threadExecutor,
            postExecutionThread
        )
        utils.showLog(
            TAG,
            "provideInteractor($domainFetcher, $threadExecutor, $postExecutionThread) : $moviesDetailsInteractor"
        )
        return moviesDetailsInteractor
    }
}