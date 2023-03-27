package com.example.kitsuapp.core.extension

import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

/**
 * Расширение для загрузки изображения из заданного URL-адреса с помощью библиотеки Glide
 * и отображает его в ImageView.
 */
fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}