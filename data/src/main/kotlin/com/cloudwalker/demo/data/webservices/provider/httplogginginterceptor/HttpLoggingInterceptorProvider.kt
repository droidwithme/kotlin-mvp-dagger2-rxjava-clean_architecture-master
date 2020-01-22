package com.cloudwalker.demo.data.webservices.provider.httplogginginterceptor

import com.cloudwalker.utils.Utils
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

private val TAG = HttpLoggingInterceptorProvider::class.java.simpleName

@Singleton
class HttpLoggingInterceptorProvider
@Inject
constructor(private val utils: Utils) {

    val instance: HttpLoggingInterceptor get() = provideHttpLoggingInterceptor()

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        utils.showLog(TAG, "provideHttpLoggingInterceptor() : $httpLoggingInterceptor")
        return httpLoggingInterceptor
    }
}