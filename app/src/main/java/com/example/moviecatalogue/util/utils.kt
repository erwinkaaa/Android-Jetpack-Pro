package com.example.moviecatalogue.util

import android.view.View

const val TAG = "JetpackPro"

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.GONE
}