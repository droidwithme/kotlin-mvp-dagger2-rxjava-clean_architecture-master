package com.cloudwalker.demo.data.webservices.restclient

import com.cloudwalker.demo.data.webservices.api.CloudWalkerAPI
import com.cloudwalker.demo.data.webservices.retrofit.RetrofitProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestClient
@Inject
constructor(private val retrofitProvider: RetrofitProvider) {

    val cloudWalkerAPI: CloudWalkerAPI get() = setupCloudWalkerAPI()

    private fun setupCloudWalkerAPI(): CloudWalkerAPI {
        return retrofitProvider.retrofit.create(CloudWalkerAPI::class.java)
    }
}