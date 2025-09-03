package com.jnasif.moviegallery.utilities

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.NumberFormat

@BindingAdapter("imageUrl")
fun loadImage(view : ImageView, imageUrl : String){
    Glide.with(view.context).load(imageUrl).into(view)
}

@BindingAdapter("popularity")
fun moviePopularity(view: TextView, value: Double){
    val formatter = NumberFormat.getPercentInstance()
    val text = "${formatter.format(value)} %"
    view.text = text
}