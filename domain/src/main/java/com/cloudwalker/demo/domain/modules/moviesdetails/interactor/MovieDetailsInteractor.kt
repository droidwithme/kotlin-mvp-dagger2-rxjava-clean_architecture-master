package com.cloudwalker.demo.domain.modules.moviesdetails.interactor

import com.cloudwalker.demo.domain.executors.PostExecutionThread
import com.cloudwalker.demo.domain.executors.ThreadExecutor
import com.cloudwalker.demo.domain.fetchers.DomainFetcher
import com.cloudwalker.demo.domain.interactors.Interactor
import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanQ
import com.cloudwalker.demo.domain.modules.moviesdetails.beans.MovieDetailsBeanQ
import com.google.gson.Gson
import io.reactivex.Observable
import javax.inject.Inject

class MovieDetailsInteractor
@Inject constructor(
    private val movieDetailsBeanQ: MovieDetailsBeanQ,
    private val gson: Gson,
    private val domainFetcher: DomainFetcher,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : Interactor(threadExecutor, postExecutionThread) {

    override fun buildInteractorObservable(): Observable<*> {
        return domainFetcher.getMovieDetails(movieDetailsBeanQ, gson)
    }
}