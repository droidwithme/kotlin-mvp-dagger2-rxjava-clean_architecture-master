package com.cloudwalker.demo.presentation.main.dagger.modules

import android.content.Context
import com.google.gson.Gson
import com.cloudwalker.demo.data.executors.DataJobExecutor
import com.cloudwalker.demo.data.fetchers.DataFetcher
import com.cloudwalker.demo.domain.executors.PostExecutionThread
import com.cloudwalker.demo.domain.executors.ThreadExecutor
import com.cloudwalker.demo.domain.fetchers.DomainFetcher
import com.cloudwalker.demo.presentation.main.executors.UIThreadExecutor
import com.cloudwalker.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger Module for Application
 *
 * Created by Praveen on 06-08-2018.
 */
@Module
class ApplicationModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideUtils(): Utils {
        return Utils(context)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideDataFetcher(dataFetcher: DataFetcher): DomainFetcher {
        return dataFetcher
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: DataJobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThreadExecutor): PostExecutionThread {
        return uiThread
    }
}