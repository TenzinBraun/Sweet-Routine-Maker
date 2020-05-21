package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import fr.iutbourg.sweetroutinemaker.callback.FirebaseDatabaseAction
import fr.iutbourg.sweetroutinemaker.data.model.ChildProfile
import fr.iutbourg.sweetroutinemaker.data.model.User
import fr.iutbourg.sweetroutinemaker.data.repository.FirebaseRepository
import fr.iutbourg.sweetroutinemaker.data.repository.UserRepository

class ChildListViewModel(
    private val firebaseRepository: FirebaseRepository<List<ChildProfile>>
) : ViewModel() {

    fun addChildListForCurrentUser(children: List<ChildProfile>, nodes: DatabaseReference)
            = firebaseRepository.add(children, nodes)

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ChildListViewModel(FirebaseDatabaseAction.firebaseContext()) as T
        }
    }

}