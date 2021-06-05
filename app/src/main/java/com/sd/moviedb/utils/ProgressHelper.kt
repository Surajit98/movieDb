package com.sd.moviedb.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.sd.moviedb.R

object ProgressHelper {


    fun showAlert(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.alert_dialog_loading)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.window!!.setGravity(Gravity.BOTTOM)
        // dialog.show()
        return dialog

    }
}