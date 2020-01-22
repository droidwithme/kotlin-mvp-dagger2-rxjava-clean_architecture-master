package com.cloudwalker.demo.presentation.main.dagger.modules

import com.cloudwalker.demo.domain.executors.PostExecutionThread
import com.cloudwalker.demo.domain.executors.ThreadExecutor
import com.cloudwalker.demo.domain.fetchers.DomainFetcher
import com.cloudwalker.demo.domain.interactors.Interactor

/**
 * Dagger Module Proto-Type For Clean Architecture
 * To be implemented by all UI level module
 *
 * Created by Praveen on 06-08-2018.
 */
interface MainModule {
    fun provideInteractor(
        domainFetcher: DomainFetcher,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): Interactor
}