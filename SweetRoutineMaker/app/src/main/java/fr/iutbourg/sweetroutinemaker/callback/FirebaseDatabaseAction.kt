package fr.iutbourg.sweetroutinemaker.callback

import com.google.firebase.database.DatabaseReference
import fr.iutbourg.sweetroutinemaker.data.repository.FirebaseRepository

interface FirebaseDatabaseAction<Model> {
    fun edit(model: Model, nodes: DatabaseReference)
    fun delete(model: Model, nodes: DatabaseReference)
    fun add(model: Model, nodes: DatabaseReference)

    companion object Instance{
        fun <T> firebaseContext(): FirebaseRepository<T> {
            return FirebaseRepository()
        }
    }
}