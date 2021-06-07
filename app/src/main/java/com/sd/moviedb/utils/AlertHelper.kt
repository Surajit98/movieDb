package com.sd.moviedb.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.sd.moviedb.R
import com.sd.moviedb.databinding.AlertDialogOkCancelBinding
import com.sd.moviedb.callback.AlertCallback
import com.sd.moviedb.databinding.AlertDialogInputEmailBinding
import kotlin.math.roundToInt

object AlertHelper {


    fun showAlertOk(context: Context, msg: String): Dialog {
        val dialog = Dialog(context, R.style.DialogTheme)
        val binding: AlertDialogOkCancelBinding = DataBindingUtil.inflate(
            (context as Activity).layoutInflater,
            R.layout.alert_dialog_ok_cancel,
            null,
            false
        )
        dialog.setContentView(binding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window?.setDimAmount(.8f)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = (context.resources.displayMetrics.widthPixels * 0.95).roundToInt()
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes = lp
        binding.txtInfo.text = msg
        binding.btnCancel.visibility = View.GONE
        binding.btnOk.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
        return dialog

    }

}