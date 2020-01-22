package com.cloudwalker.demo.presentation.ui.nowplaying.dagger.module

import com.cloudwalker.demo.domain.executors.PostExecutionThread
import com.cloudwalker.demo.domain.executors.ThreadExecutor
import com.cloudwalker.demo.domain.fetchers.DomainFetcher
import com.cloudwalker.demo.domain.interactors.Interactor
import com.cloudwalker.demo.domain.modules.nowplaying.interactor.NowPlayingMoviesInteractor
import com.cloudwalker.demo.domain.modules.popularmovies.interactor.PopularMoviesInteractor
import com.cloudwalker.demo.presentation.main.dagger.annotations.PerActivity
import com.cloudwalker.demo.presentation.main.dagger.modules.MainModule
import com.cloudwalker.demo.presentation.main.runtime.Constants
import com.cloudwalker.demo.presentation.ui.moviespopular.model.PopularMoviesModelQ
import com.cloudwalker.demo.presentation.ui.moviespopular.transformer.PopularMoviesConverter
import com.cloudwalker.demo.presentation.ui.nowplaying.model.NowPlayingMoviesModelQ
import com.cloudwalker.demo.presentation.ui.nowplaying.transformer.NowPlayingMoviesConverter
import com.cloudwalker.utils.Utils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Named

private val TAG = NowPlayingMoviesModule::class.java.simpleName

@Module
class NowPlayingMoviesModule
constructor(
    private val nowPlayingMoviesModelQ: NowPlayingMoviesModelQ,
    private val gson: Gson,
    private val utils: Utils
) : MainModule {

    private lateinit var nowPlayingMoviesInteractor: NowPlayingMoviesInteractor

    @Provides
    @PerActivity
    @Named(Constants.nowPlayingMoviesInteractor)
    override fun provideInteractor(
        domainFetcher: DomainFetcher,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): Interactor {
        nowPlayingMoviesInteractor = NowPlayingMoviesInteractor(
            NowPlayingMoviesConverter(gson, utils).transformModel(nowPlayingMoviesModelQ),
                    gson,
            domainFetcher,
            threadExecutor,
            postExecutionThread
        )
        utils.showLog(
            TAG,
            "provideInteractor($domainFetcher, $threadExecutor, $postExecutionThread) : $nowPlayingMoviesInteractor"
        )
        return nowPlayingMoviesInteractor
    }
}