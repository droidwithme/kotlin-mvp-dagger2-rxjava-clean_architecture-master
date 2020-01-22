package com.cloudwalker.demo.presentation.main.application

import android.content.res.Configuration
import androidx.multidex.MultiDexApplication
import com.google.gson.Gson
import com.cloudwalker.demo.presentation.main.dagger.components.ApplicationComponent
import com.cloudwalker.demo.presentation.main.dagger.injector.EnumInjector
import com.cloudwalker.utils.Utils
import javax.inject.Inject

private val TAG = MainApplication::class.java.simpleName

class MainApplication : MultiDexApplication() {

    // To provide application component for injecting dependencies
    val applicationComponent: ApplicationComponent get() = EnumInjector.INSTANCE.getApplicationComponent(this)

    @Inject
    lateinit var utils: Utils

    @Inject
    lateinit var gson: Gson

    /* Called before the first components of the application starts */
    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this)
        utils.showLog(TAG, "onCreate()")
    }

    /* Called whenever the configuration changes */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        utils.showLog(TAG, "onConfigurationChanged() method called.")
    }

    /* Called when the Android system requests that the application cleans up memory */
    override fun onLowMemory() {
        super.onLowMemory()
        utils.showLog(TAG, "onLowMemory() method called.")
    }

    /*
     * Called when the Android system requests that the application cleans up memory.
     * This message includes an indicator in which position the application is.
     * For example the constant TRIM_MEMORY_MODERATE indicates that the process is around the middle of the background LRU list;
     * freeing memory can help the system keep other processes running later in the list for better overall performance.
     * */
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        utils.showLog(TAG, "onTrimMemory() method called.")
    }

    /* Only for testing, not called in production */
    override fun onTerminate() {
        super.onTerminate()
        utils.showLog(TAG, "onTerminate() method called.")
    }
}