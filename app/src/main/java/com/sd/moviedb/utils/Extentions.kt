package com.sd.moviedb.utils


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.sd.moviedb.R
import java.text.SimpleDateFormat
import java.util.*


fun Activity.setTransparentStatusBar() {
    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        statusBarColor = Color.TRANSPARENT
    }
}

fun String?.isValidEmail() =
    !this.isNullOrBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

inline fun Any.log(message: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.d(this::class.java.simpleName, message())
    }
}

fun Date?.format(): String {
    return if (this == null) "" else SimpleDateFormat(
        "dd MMM, yyyy hh:mm a",
        Locale.getDefault()
    ).format(this)
}

fun Activity.shareText(text: String) {

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(
        Intent.EXTRA_SUBJECT,
        getString(R.string.app_name)
    )
    intent.putExtra(Intent.EXTRA_TEXT, text)
    ContextCompat.startActivity(
        this,
        Intent.createChooser(intent, getString(R.string.share_using)), null
    )

}
