package com.julickot.friendsapp

import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.julickot.friendsapp.ui.details.ColorIndicatorView


@BindingAdapter("imageByFruit")
fun imageByFruit(view: ImageView, value: String?) {
    val resId = when (value) {
        "apple" -> R.drawable.ic_apple
        "banana" -> R.drawable.ic_banana
        "strawberry" -> R.drawable.ic_strawberry
        else -> R.drawable.ic_baseline_block_24
    }
    view.setImageResource(resId)
}

@BindingAdapter("colorName")
fun setColorByName(view: ColorIndicatorView, value: String?) {
    val color = when (value) {
        "blue" -> ContextCompat.getColor(view.context, R.color.eyeColor_blue)
        "brown" -> ContextCompat.getColor(view.context, R.color.eyeColor_brown)
        "green" -> ContextCompat.getColor(view.context, R.color.eyeColor_green)
        else -> Color.WHITE
    }
    view.setColor(color)
}

@BindingAdapter(value = ["android:text", "fromDateFormat", "toDateFormat"], requireAll = true)
fun setDateFormat(view: TextView, date: String?, fromFormat: String?, toFormat: String?) {
    if (!date.isNullOrBlank() &&
        !fromFormat.isNullOrBlank() &&
        !toFormat.isNullOrBlank()
    ) {
        view.text = stringDateFormatter(date, fromFormat, toFormat)
    }
}


fun stringDateFormatter(date: String, fromFormat: String, toFormat: String): String {
    SimpleDateFormat(fromFormat).parse(date)?.let {
        return SimpleDateFormat(toFormat).format(it)
    } ?: run {
        return ""
    }
}


