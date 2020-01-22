package com.cloudwalker.demo.presentation.main.dagger.components

import android.content.Context
import com.google.gson.Gson
import com.cloudwalker.demo.domain.executors.PostExecutionThread
import com.cloudwalker.demo.domain.executors.ThreadExecutor
import com.cloudwalker.demo.domain.fetchers.DomainFetcher
import com.cloudwalker.demo.presentation.main.application.MainApplication
import com.cloudwalker.demo.presentation.main.dagger.modules.ApplicationModule
import com.cloudwalker.utils.Utils
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    /* Inject Dependencies of Main Application */
    fun inject(mainApplication: MainApplication)

    fun provideContext(): Context

    fun provideUtils(): Utils

    fun provideGson(): Gson

    fun provideDataFetcher(): DomainFetcher

    fun provideThreadExecutor(): ThreadExecutor

    fun providePostExecutionThread(): PostExecutionThread
}