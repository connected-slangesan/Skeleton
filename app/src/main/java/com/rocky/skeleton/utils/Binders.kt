package com.rocky.skeleton.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.rocky.skeleton.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Picasso.get().load(url).placeholder(R.mipmap.ic_launcher_round)
        .into(view)
}