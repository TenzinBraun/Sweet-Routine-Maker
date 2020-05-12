package fr.iutbourg.sweetroutinemaker.data.networking

import com.google.firebase.auth.FirebaseAuth

private object FirebaseManagerImpl: FirebaseManager {
    override val auth: FirebaseAuth
        = FirebaseAuth.getInstance()

}

interface FirebaseManager {
    val auth: FirebaseAuth

    companion object Instance {
        val authInstance: FirebaseManager = FirebaseManagerImpl
    }
}