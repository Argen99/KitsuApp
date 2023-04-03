package com.example.kitsuapp.core.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kitsuapp.R

/**
 * Расширение для загрузки изображения из заданного URL-адреса с помощью библиотеки Glide
 * и отображает его в ImageView.
 */
fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.place_holder)
        .into(this)
}