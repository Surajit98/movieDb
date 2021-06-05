package com.sd.moviedb.ui.base

import android.app.Dialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.sd.moviedb.utils.ProgressHelper

abstract class BaseActivity : AppCompatActivity() {


    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        init()
    }


    abstract fun setBinding()

    abstract fun init()

    protected fun showDialog() {
        if (dialog == null) {
            dialog = ProgressHelper.showAlert(this)
        }
        if (!dialog!!.isShowing)
            dialog?.show()
    }

    protected fun dismissDialog() {
        if (dialog != null)
            dialog?.dismiss()
    }


    protected fun transparentStatusBar() {
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}