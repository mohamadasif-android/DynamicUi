package com.tmobile.dynamicui.util

import android.graphics.Color
import android.util.TypedValue
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun setImageUrl(
    imageView: ImageView,
    imageUrl: String
) {
    if (imageUrl.isNotEmpty()) {
        Picasso.get()
            .load(imageUrl)
            .into(imageView)
    }
}

@BindingAdapter(value = ["hexColor", "fontSize"], requireAll = false)
fun setHexColor(
    textView: TextView,
    colorStr: String,
    fontSize: Int
) {
    if (!colorStr.isNullOrEmpty()) {
        textView.setTextColor(Color.parseColor(colorStr))
    }
    if (fontSize > 0) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize.toFloat())
    }
}
