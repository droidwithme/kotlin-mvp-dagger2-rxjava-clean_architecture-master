package com.cloudwalker.demo.presentation.ui.landing.dagger

import com.cloudwalker.demo.domain.executors.PostExecutionThread
import com.cloudwalker.demo.domain.executors.ThreadExecutor
import com.cloudwalker.demo.domain.fetchers.DomainFetcher
import com.cloudwalker.demo.domain.interactors.Interactor
import com.cloudwalker.demo.domain.modules.configuration.interactor.ConfigurationInteractor
import com.cloudwalker.demo.presentation.main.dagger.annotations.PerActivity
import com.cloudwalker.demo.presentation.main.dagger.modules.MainModule
import com.cloudwalker.demo.presentation.main.runtime.Constants
import com.cloudwalker.demo.presentation.ui.landing.ConfigurationConverter
import com.cloudwalker.demo.presentation.ui.landing.ConfigurationModelQ
import com.cloudwalker.utils.Utils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Named

private val TAG = ConfigurationModule::class.java.simpleName

@Module
class ConfigurationModule
constructor(
    private val configurationModelQ: ConfigurationModelQ,
    private val gson: Gson,
    private val utils: Utils
) : MainModule {

    private lateinit var configurationInteractor: ConfigurationInteractor

    @Provides
    @PerActivity
    @Named(Constants.configurationInteractor)
    override fun provideInteractor(
        domainFetcher: DomainFetcher,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
    ): Interactor {
        configurationInteractor = ConfigurationInteractor(
            ConfigurationConverter(gson, utils).transformModel(configurationModelQ),
            gson,
            domainFetcher,
            threadExecutor,
            postExecutionThread
        )
        utils.showLog(
            TAG,
            "provideInteractor($domainFetcher, $threadExecutor, $postExecutionThread) : $configurationInteractor"
        )
        return configurationInteractor
    }
}