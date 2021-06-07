package com.sd.moviedb.utils


import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.util.*
import kotlin.math.ln
import kotlin.math.pow


object CustomBindingAdapter {

    @BindingAdapter("imgRes")
    @JvmStatic
    fun imgRes(view: ImageButton, resource: Int) {
        view.setImageResource(resource)
    }

    @BindingAdapter("backColor")
    @JvmStatic
    fun backColor(view: View, resource: String) {
        view.background.setTint(
            Color.parseColor(resource)
        )
    }

    @BindingAdapter("setImg")
    @JvmStatic
    fun setImg(img: ImageView, path: String?) {
        path?.let {
            Glide.with(img).load("path")
                .into(img)
        }


    }

    @BindingAdapter("date")
    @JvmStatic
    fun setDate(txt: TextView, date: Date) {
        txt.text = date.format()
    }

    @BindingAdapter("countToString")
    @JvmStatic
    fun countToString(txt: TextView, count: Int) {
        if (count < 1000) {
            txt.text = count.toString()
        } else {
            val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
            txt.text =
                String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
        }

    }


}


