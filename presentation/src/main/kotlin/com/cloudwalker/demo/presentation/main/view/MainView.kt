package com.cloudwalker.demo.presentation.main.view

import android.content.Context

/**
 * Interface for loading view with common methods to be implemented.
 *
 * Created by Praveen on 06-08-2018.
 */
interface MainView {
    /**
     * Show a view with a progress bar indicating a loading process.
     */
    fun showLoading()

    /**
     * Hide a loading view.
     */
    fun hideLoading()

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    fun showError(message: String)

    /**
     * Get a [android.content.Context].
     */
    fun context(): Context
}