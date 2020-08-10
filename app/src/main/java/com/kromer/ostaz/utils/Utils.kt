package com.kromer.ostaz.utils

import android.app.Activity
import android.app.ProgressDialog

object Utils {
    fun showLoadingDialog(activity: Activity?, message: String?): ProgressDialog? {
        if (activity == null || activity.isFinishing) {
            return null
        }
        val progressDialog = ProgressDialog(activity)
        progressDialog.setMessage(message)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
        return progressDialog
    }

    fun hideLoadingDialog(mProgressDialog: ProgressDialog?, activity: Activity?) {
        if (activity != null && !activity.isFinishing && mProgressDialog != null && mProgressDialog
                .isShowing
        ) {
            mProgressDialog.dismiss()
        }
    }
}