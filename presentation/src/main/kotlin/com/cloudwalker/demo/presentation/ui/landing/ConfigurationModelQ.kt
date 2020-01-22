package com.cloudwalker.demo.presentation.ui.landing

import com.cloudwalker.demo.presentation.main.runtime.Variables

/**
 * data class to get configuration
 *  all models class ends with Q are
 *  query classes that is used to post the parameters to retrofit API CALL
 */
data class ConfigurationModelQ(val apiKey: String = Variables.apiKey)
