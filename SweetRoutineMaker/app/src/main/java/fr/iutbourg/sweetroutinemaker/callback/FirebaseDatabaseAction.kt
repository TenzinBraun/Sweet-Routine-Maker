package fr.iutbourg.sweetroutinemaker.callback

import androidx.lifecycle.LiveData
import com.google.firebase.database.DatabaseReference
import fr.iutbourg.sweetroutinemaker.data.repository.FirebaseRepository
import kotlinx.coroutines.CoroutineScope

interface FirebaseDatabaseAction<Model> {
    fun edit(model: Model, nodes: DatabaseReference)
    fun delete(model: Model, nodes: DatabaseReference)
    fun add(model: Model, nodes: DatabaseReference)
    fun getActivitiesForChild(nodes: DatabaseReference, viewModelScope: CoroutineScope): LiveData<Model>

    companion object Instance{
        fun <T> firebaseContext(): FirebaseRepository<T> {
            return FirebaseRepository()
        }
    }
}