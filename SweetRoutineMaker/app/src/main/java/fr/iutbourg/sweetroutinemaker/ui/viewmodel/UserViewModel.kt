package fr.iutbourg.sweetroutinemaker.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import fr.iutbourg.sweetroutinemaker.data.repository.UserRepository
import kotlin.coroutines.coroutineContext

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    fun createUserWithEmailPassword(email: String, password: String) = userRepository.firebaseCreateNewUserWithEmailPassword(email, password, viewModelScope)
    fun signInWithEmailAndPassword(email: String, password: String)  = userRepository.firebaseSignInWithEmailPassword(email, password, viewModelScope)

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(UserRepository.userContext) as T
        }
    }
}