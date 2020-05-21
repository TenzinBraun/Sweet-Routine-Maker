package fr.iutbourg.sweetroutinemaker.extension

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

fun <T> DatabaseReference.addChild(parentName: String, element: T) {
    this.child(parentName).setValue(element)
}

fun DatabaseReference.deleteChild(parentName: String, childKey: String) {
    this.child(parentName).push().setValue(childKey)
}