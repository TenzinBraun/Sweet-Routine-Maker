package fr.iutbourg.sweetroutinemaker.extension

import android.view.View
import android.view.View.*

fun View.hide(){
    visibility = GONE
}

fun View.show(){
    visibility = VISIBLE
}