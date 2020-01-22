package com.cloudwalker.demo.domain.modules.searchmovie.interactor

import com.cloudwalker.demo.domain.executors.PostExecutionThread
import com.cloudwalker.demo.domain.executors.ThreadExecutor
import com.cloudwalker.demo.domain.fetchers.DomainFetcher
import com.cloudwalker.demo.domain.interactors.Interactor
import com.cloudwalker.demo.domain.modules.popularmovies.beans.PopularMoviesBeanQ
import com.cloudwalker.demo.domain.modules.searchmovie.beans.SearchMoviesBeanQ
import com.google.gson.Gson
import io.reactivex.Observable
import javax.inject.Inject

class SearchMoviesInteractor
@Inject constructor(
    private val searchMoviesBeanQ: SearchMoviesBeanQ,
    private val gson: Gson,
    private val domainFetcher: DomainFetcher,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : Interactor(threadExecutor, postExecutionThread) {

    override fun buildInteractorObservable(): Observable<*> {
        return domainFetcher.searchMovieByName(searchMoviesBeanQ, gson)
    }
}