package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import fr.iutbourg.sweetroutinemaker.callback.FirebaseDatabaseAction
import fr.iutbourg.sweetroutinemaker.data.model.User
import fr.iutbourg.sweetroutinemaker.data.repository.FirebaseRepository
import fr.iutbourg.sweetroutinemaker.data.repository.UserRepository

class UserViewModel(
    private val userRepository: UserRepository,
    private val firebaseRepository: FirebaseRepository<User>
) : ViewModel() {

    fun createUserWithEmailPassword(email: String, password: String) = userRepository.firebaseCreateNewUserWithEmailPassword(email, password, viewModelScope)
    fun signInWithEmailAndPassword(email: String, password: String)  = userRepository.firebaseSignInWithEmailPassword(email, password, viewModelScope)

    fun getDataOfUser(user: User) = userRepository.startListeningDataChanges(viewModelScope, user)

    fun addUserInFirebase(user: User, nodes: DatabaseReference) = firebaseRepository.add(user, nodes)

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(UserRepository.userContext, FirebaseDatabaseAction.firebaseContext()) as T
        }
    }
}