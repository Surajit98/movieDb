package com.sd.moviedb.utils


import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sd.moviedb.R


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
            Glide.with(img).load(path).into(img)
        } ?: Glide.with(img).load(path).into(img)


    }

    @BindingAdapter("date")
    @JvmStatic
    fun setDate(txt: TextView, date: String?) {
        val text = "${txt.context.getString(R.string.realesed_on)} ${date.formatDate()}"
        txt.text = text
    }


}


