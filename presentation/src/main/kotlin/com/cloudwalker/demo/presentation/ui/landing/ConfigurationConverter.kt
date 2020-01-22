package com.cloudwalker.demo.presentation.ui.landing

import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanQ
import com.cloudwalker.demo.domain.modules.configuration.beans.ConfigurationBeanR
import com.cloudwalker.utils.Utils
import com.google.gson.Gson
import javax.inject.Inject

private val TAG = ConfigurationConverter::class.java.simpleName

/**
 * Converter used to convert data layer's bean in to
 * presentation layer's model classes.
 */
class ConfigurationConverter
@Inject
constructor(private val gson: Gson, private val utils: Utils) {
    // Convert Mobile Domain Bean to Presentation Model
    fun transformBean(configurationBeanR: ConfigurationBeanR): ConfigurationModelR {
        utils.showLog(TAG, "transformBean($configurationBeanR)")
        return gson.fromJson(gson.toJson(configurationBeanR), ConfigurationModelR::class.java)
    }

    // Convert Mobile Presentation Model to Domain Bean
    fun transformModel(configurationModelQ: ConfigurationModelQ): ConfigurationBeanQ {
        utils.showLog(TAG, "transformModel($configurationModelQ)")
        return gson.fromJson(gson.toJson(configurationModelQ), ConfigurationBeanQ::class.java)
    }
}