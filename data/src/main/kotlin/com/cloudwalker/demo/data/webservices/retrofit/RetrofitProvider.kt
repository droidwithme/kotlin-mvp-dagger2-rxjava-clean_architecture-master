package com.cloudwalker.demo.data.webservices.retrofit

import com.cloudwalker.demo.data.runtime.Constants
import com.cloudwalker.demo.data.webservices.provider.okhttpclient.OkHttpClientProvider
import com.cloudwalker.utils.Utils
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

private val TAG = RetrofitProvider::class.java.simpleName

@Singleton
class RetrofitProvider
@Inject
constructor(private val okHttpClientProvider: OkHttpClientProvider, private val utils: Utils) {

    val retrofit: Retrofit get() = provideRetrofit()

    private fun provideRetrofit(): Retrofit {
        utils.showLog(TAG, "provideRetrofit(${Constants.BASE_URL})")
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClientProvider.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}