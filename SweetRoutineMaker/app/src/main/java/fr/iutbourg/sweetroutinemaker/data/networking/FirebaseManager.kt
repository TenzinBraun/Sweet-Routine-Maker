package fr.iutbourg.sweetroutinemaker.data.networking

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

private object FirebaseManagerImpl: FirebaseManager {
    override val auth: FirebaseAuth
        = FirebaseAuth.getInstance()

    override val database: FirebaseDatabase
        = FirebaseDatabase.getInstance()

}

interface FirebaseManager {
    val auth: FirebaseAuth
    val database: FirebaseDatabase

    companion object Instance {
        val firebaseInstance: FirebaseManager = FirebaseManagerImpl
    }
}