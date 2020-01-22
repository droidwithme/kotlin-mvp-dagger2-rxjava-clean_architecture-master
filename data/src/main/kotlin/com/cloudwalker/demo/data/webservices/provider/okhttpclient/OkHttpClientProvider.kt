package com.cloudwalker.demo.data.webservices.provider.okhttpclient

import com.cloudwalker.demo.data.webservices.provider.cache.CacheProvider
import com.cloudwalker.demo.data.webservices.provider.httplogginginterceptor.HttpLoggingInterceptorProvider
import com.cloudwalker.demo.data.webservices.provider.interceptor.InterceptorProvider
import com.cloudwalker.utils.Utils
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

private val TAG = OkHttpClientProvider::class.java.simpleName

@Singleton
class OkHttpClientProvider
@Inject
constructor(
    private val httpLoggingInterceptor: HttpLoggingInterceptorProvider,
    private val interceptorProvider: InterceptorProvider,
    private val cacheProvider: CacheProvider,
    private val utils: Utils
) {

    val okHttpClient: OkHttpClient get() = setupOkHttpClient()

    private fun setupOkHttpClient(): OkHttpClient {
        utils.showLog(TAG, "setupOkHttpClient()")
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor.instance)
            .addInterceptor(interceptorProvider.offlineCacheInterceptor)
            .addNetworkInterceptor(interceptorProvider.interceptor)
            .cache(cacheProvider.cache).build()
    }
}