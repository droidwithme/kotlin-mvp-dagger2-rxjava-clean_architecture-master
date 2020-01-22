package com.cloudwalker.demo.presentation.ui.moviespopular.dagger.module

import com.cloudwalker.demo.domain.executors.PostExecutionThread
import com.cloudwalker.demo.domain.executors.ThreadExecutor
import com.cloudwalker.demo.domain.fetchers.DomainFetcher
import com.cloudwalker.demo.domain.interactors.Interactor
import com.cloudwalker.demo.domain.modules.popularmovies.interactor.PopularMoviesInteractor
import com.cloudwalker.demo.presentation.main.dagger.annotations.PerActivity
import com.cloudwalker.demo.presentation.main.dagger.modules.MainModule
import com.cloudwalker.demo.presentation.main.runtime.Constants
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelQ
import com.cloudwalker.demo.presentation.ui.moviespopular.transformer.PopularMoviesConverter
import com.cloudwalker.utils.Utils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Named

private val TAG = PopularMoviesModule::class.java.simpleName

/**
 * All the dependencies fullfilled by dagger from the application component
 * now this will provide the interactor
 */
@Module
class PopularMoviesModule
constructor(
    private val popularMoviesModelQ: PopularMoviesModelQ,
    private val gson: Gson,
    private val utils: Utils
) : MainModule {

    private lateinit var movieInteractorPopular: PopularMoviesInteractor

    @Provides
    @PerActivity
    @Named(Constants.popularMoviesInteractor)
    override fun provideInteractor(
        domainFetcher: DomainFetcher,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): Interactor {
        movieInteractorPopular = PopularMoviesInteractor(
            PopularMoviesConverter(gson, utils).transformModel(popularMoviesModelQ),
                    gson,
            domainFetcher,
            threadExecutor,
            postExecutionThread
        )
        utils.showLog(
            TAG,
            "provideInteractor($domainFetcher, $threadExecutor, $postExecutionThread) : $movieInteractorPopular"
        )
        return movieInteractorPopular
    }
}