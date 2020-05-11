package fr.iutbourg.sweetroutinemaker.extension

import com.google.firebase.database.DatabaseReference

fun <T> DatabaseReference.addChild(parentName: String, element: T){
    this.child(parentName).setValue(element)
}

fun DatabaseReference.deleteChild(parentName: String, childKey: String){
    this.child(parentName).push().setValue(childKey)
}