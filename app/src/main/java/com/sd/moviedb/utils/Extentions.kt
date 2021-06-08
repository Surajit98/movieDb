package com.sd.moviedb.utils


import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.sd.moviedb.BuildConfig
import com.sd.moviedb.R
import java.text.SimpleDateFormat
import java.util.*


inline fun Any.log(message: () -> String) {
    if (BuildConfig.DEBUG) {
        Log.d(this::class.java.simpleName, message())
    }
}

fun String?.formatDate(): String {
    return if (this.isNullOrEmpty()) ""
    else {
        val date = SimpleDateFormat(
            "yyyy-mm-dd",
            Locale.getDefault()
        ).parse(this)
        date?.let {
            SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(
                it
            )
        } ?: "N/A"

    }
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
