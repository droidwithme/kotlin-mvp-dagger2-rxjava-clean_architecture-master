package com.cloudwalker.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import javax.inject.Inject
import javax.inject.Singleton

private val TAG = Utils::class.java.simpleName

@Singleton
class Utils
@Inject
constructor(private val context: Context) {

    /* To Hide Keyboard */
    @Suppress("unused")
    fun getMainApplicationContext(): Context {
        return context
    }

    /* To Log In Debug Build */
    @Suppress("unused")
    fun showLog(TAG: String, message: String) {
        try {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /* To Show Toast */
    @Suppress("unused")
    fun showToast(message: String) {
        showLog(TAG, "showToast($context, $message)")
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /* To Hide Keyboard */
    @Suppress("unused")
    fun hideKeyboard(editText: EditText) {
        editText.clearFocus()
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            editText.windowToken,
            0
        )
    }

    /* To Show Keyboard */
    @Suppress("unused")
    fun showKeyboard(editText: EditText) {
        editText.requestFocus()
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
            editText,
            InputMethodManager.SHOW_IMPLICIT
        )
        editText.setSelection(editText.text.toString().length)
    }

    /* To Check Email Pattern */
    @Suppress("unused")
    fun isValidEmail(email: String): Boolean {
        showLog(TAG, "isValidEmail($email)")
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /* To Check If Network Is Available or not */
    @Suppress("unused")
    fun isNetworkAvailable(): Boolean {
        showLog(TAG, "isNetworkAvailable($context)")
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}