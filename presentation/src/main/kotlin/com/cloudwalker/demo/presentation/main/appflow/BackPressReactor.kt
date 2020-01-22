package com.cloudwalker.demo.presentation.main.appflow

import android.app.AlertDialog
import android.os.Build
import androidx.fragment.app.FragmentActivity
import com.cloudwalker.demo.presentation.main.fragment.FragmentEnum
import com.cloudwalker.utils.Utils

object BackPressReactor {
    private val TAG = BackPressReactor::class.java.simpleName

    /**
     * To Enable & Disable Back Navigation
     */
    @Suppress("unused")
    private var isBackNavigationAllowed = true

    private fun exitApplication(fragmentActivity: FragmentActivity, utils: Utils) {
        utils.showLog(TAG, "exitApplication($fragmentActivity,$utils")
        val alertDialog: AlertDialog
        try {
            val builder = AlertDialog.Builder(fragmentActivity)
            builder.setCancelable(false)
            builder.setTitle("Exit Application")
            builder.setMessage("Are you sure you want to exit?")
                .setPositiveButton(
                    "Exit"
                ) { dialog, _ ->
                    dialog.dismiss()
                    if (Build.VERSION.SDK_INT > 21) {
                        fragmentActivity.finishAndRemoveTask()
                    } else {
                        fragmentActivity.finishAffinity()
                    }
                    System.exit(0)
                }

            builder.setNegativeButton(
                "Cancel"
            ) { dialog, _ ->
                dialog.dismiss()
            }
            // Create the AlertDialog object and return it
            alertDialog = builder.create()
            alertDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /* Function to perform fragment changes on back press*/
    fun backPressed(fragmentActivity: FragmentActivity, resID: Int, utils: Utils) {
        val fragment = fragmentActivity.supportFragmentManager.findFragmentById(resID)!!
        utils.showLog(TAG, "Found fragment : ${fragment::class.java.simpleName}")
        when (fragment::class.java.simpleName) {
            FragmentEnum.SPLASHSCREEN.code -> {
                utils.showLog(TAG, "Current Fragment SplashScreen")
            }
            FragmentEnum.MOVIESHOME.code -> {
                utils.showLog(TAG, "Current Fragment MobileScreen")
                exitApplication(fragmentActivity, utils)
            }
        }
    }
}