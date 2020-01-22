package com.cloudwalker.demo.data.webservices.provider.cache

import android.content.Context
import com.cloudwalker.utils.Utils
import okhttp3.Cache
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

private val TAG = CacheProvider::class.java.simpleName

@Singleton
class CacheProvider
@Inject
constructor(private val context: Context, private val utils: Utils) {

    val cache: Cache? get() = provideCache()

    private fun provideCache(): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(File(context.cacheDir, "http-cache"), (10 * 1024 * 1024)) // 10 MB
            utils.showLog(TAG, "provideCache(): $cache")
        } catch (e: Exception) {
            utils.showLog(TAG, "Unable To Create Cache")
        }
        return cache
    }
}