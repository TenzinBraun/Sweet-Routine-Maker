package fr.iutbourg.sweetroutinemaker.data.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import fr.iutbourg.sweetroutinemaker.data.model.User
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepositoryImpl(
    private val auth: FirebaseAuth = FirebaseManager.authInstance.auth
) : UserRepository {

    override fun firebaseCreateNewUserWithEmailPassword(
        email: String,
        password: String,
        viewModelScope: CoroutineScope
    ): LiveData<User?> {
        val data = MutableLiveData<User>()
        viewModelScope.launch(Dispatchers.IO){
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val isNewUser: Boolean = task.getResult()?.additionalUserInfo!!.isNewUser
                    val firebaseUser  = auth.currentUser
                    firebaseUser?.sendEmailVerification()?.addOnCompleteListener{ task ->
                        if (task.isSuccessful) {
                            data.postValue(User (
                                firebaseUser.uid,
                                firebaseUser.displayName,
                                firebaseUser.email,
                                true,
                                isNewUser
                            ))
                        }
                        else {
                            data.postValue(null)
                        }
                    }
                }
                else {
                    data.postValue(null)
                }
            }
        }
        return data
    }

    override fun firebaseSignInWithEmailPassword(
        email: String,
        password: String,
        viewModelScope: CoroutineScope
    ): LiveData<User?> {
        val data = MutableLiveData<User?>()
        viewModelScope.launch(Dispatchers.IO){
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    val isNewUser: Boolean = task.getResult()?.additionalUserInfo!!.isNewUser
                    val firebaseUser  = auth.currentUser

                    if (firebaseUser != null) {
                        data.postValue(User (
                            firebaseUser.uid,
                            firebaseUser.displayName,
                            firebaseUser.email,
                            true,
                            isNewUser
                        ))
                    }
                }
                else {
                    data.postValue(null)
                }
            }
        }
        return data
    }


}


interface UserRepository {
    fun firebaseCreateNewUserWithEmailPassword(email: String, password: String, viewModelScope: CoroutineScope): LiveData<User?>
    fun firebaseSignInWithEmailPassword(email: String, password: String, viewModelScope: CoroutineScope): LiveData<User?>

    companion object {
        val userContext: UserRepository by lazy {
            UserRepositoryImpl()
        }
    }
}