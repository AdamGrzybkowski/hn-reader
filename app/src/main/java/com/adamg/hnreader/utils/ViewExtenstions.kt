package com.adamg.hnreader.utils

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.fade() {
    this.visibility = View.INVISIBLE
}