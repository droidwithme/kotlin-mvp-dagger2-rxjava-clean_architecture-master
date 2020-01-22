package com.cloudwalker.demo.presentation.main.exceptions

import com.cloudwalker.demo.presentation.R
import com.cloudwalker.utils.Utils
import java.net.UnknownHostException

private val TAG = ErrorMessageFactory::class.java.simpleName

/**
 * Generic Error Factory to handle all errors
 *
 * Created by Praveen on 06-08-2018.
 */
object ErrorMessageFactory {
    /**
     * Creates a String representing an error message.
     *
     * @param exception An exception used as a condition to retrieve the correct error message.
     * @return [String] an error message.
     */
    fun create(utils: Utils, exception: Exception?): String {
        var message = utils.getMainApplicationContext().getString(R.string.common_exception_something_went_wrong)
        utils.showLog(TAG, "Exception is : ${exception!!.message}")
        //Exception messages
        if (exception is UnknownHostException) {
            message = utils.getMainApplicationContext().getString(R.string.exception_message_unknown_host_exception)
        }
        return message
    }
}