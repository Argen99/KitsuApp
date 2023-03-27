package com.example.kitsuapp.core.extension

import android.view.View

/**
 * Этот код содержит три функции расширения для класса View, которые позволяют
 * устанавливать видимость представления:
 */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}