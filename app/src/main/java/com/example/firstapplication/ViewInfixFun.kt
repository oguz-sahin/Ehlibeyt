package com.example.firstapplication

import android.util.Log
import android.view.View

fun View.Visible() {

    this.visibility = View.VISIBLE

}

fun View.Gone() {
    this.visibility = View.GONE
}

fun String.log() {

    Log.e(this, this)

}










