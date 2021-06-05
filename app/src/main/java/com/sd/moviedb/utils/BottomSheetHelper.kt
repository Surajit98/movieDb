package com.sd.moviedb.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.sd.moviedb.R
import com.sd.moviedb.callback.EmailExistsCallback
import com.sd.moviedb.callback.EmailVerificationCallback
import com.sd.moviedb.databinding.BottonSheetEmailExistsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

object BottomSheetHelper {


    fun showEmailResendSheet(context: Context, msg: String, callback: EmailVerificationCallback) {
        try {
            val sheetView: BottonSheetEmailExistsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.botton_sheet_email_exists, null, false
            )
            val mBottomDialogNotificationAction =
                BottomSheetDialog(context, R.style.bottomSheetDialogTheme)
            mBottomDialogNotificationAction.dismissWithAnimation = true
            mBottomDialogNotificationAction.setContentView(sheetView.root)
            sheetView.txtInfo.text = msg
            sheetView.btnOk.text = context.getString(R.string.resend_now)
            sheetView.btnCancel.setOnClickListener {
                mBottomDialogNotificationAction.dismiss()
                callback.cancel()
            }
            sheetView.btnOk.setOnClickListener {
                mBottomDialogNotificationAction.dismiss()
                callback.resend()
            }
            mBottomDialogNotificationAction.setCancelable(false)
            mBottomDialogNotificationAction.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showEmailExistsSheet(context: Context, msg: String, callback: EmailExistsCallback) {
        try {
            val sheetView: BottonSheetEmailExistsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.botton_sheet_email_exists, null, false
            )

            val mBottomDialogNotificationAction =
                BottomSheetDialog(context, R.style.bottomSheetDialogTheme)
            mBottomDialogNotificationAction.dismissWithAnimation = true
            mBottomDialogNotificationAction.setContentView(sheetView.root)
            sheetView.txtInfo.text = msg
            sheetView.btnCancel.visibility = View.GONE
            sheetView.btnCancel.setOnClickListener {
                mBottomDialogNotificationAction.dismiss()
                callback.cancel()
            }
            sheetView.btnOk.setOnClickListener {
                mBottomDialogNotificationAction.dismiss()
                callback.login()
            }
            mBottomDialogNotificationAction.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}