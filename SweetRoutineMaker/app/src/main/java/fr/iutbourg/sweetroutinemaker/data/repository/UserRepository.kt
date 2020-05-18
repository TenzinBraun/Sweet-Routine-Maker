package fr.iutbourg.sweetroutinemaker.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import fr.iutbourg.sweetroutinemaker.data.model.User
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.data.networking.datasource.UserDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepositoryImpl(
    private val auth: FirebaseAuth
) : UserRepository {

    override fun firebaseCreateNewUserWithEmailPassword(
        email: String,
        password: String,
        viewModelScope: CoroutineScope
    ): LiveData<User?> {
        val data = MutableLiveData<User>()
        viewModelScope.launch(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val isNewUser: Boolean = task.getResult()?.additionalUserInfo!!.isNewUser
                    val firebaseUser = auth.currentUser
                    firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            data.postValue(User(
                                null,
                                firebaseUser.uid,
                                firebaseUser.displayName,
                                firebaseUser.email,
                                true,
                                isNewUser
                            ))
                        }
                    }
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
        viewModelScope.launch(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val isNewUser: Boolean = task.getResult()?.additionalUserInfo!!.isNewUser
                    val firebaseUser = auth.currentUser

                    if (firebaseUser != null) {
                        data.postValue(User(
                            null,
                            firebaseUser.uid,
                            firebaseUser.displayName,
                            firebaseUser.email,
                            true,
                            isNewUser
                        ))
                    }
                }

            }
        }
        return data
    }

    override fun startListeningDataChanges(
        viewModelScope: CoroutineScope,
        user: User
    ): LiveData<User?> {
        val ref = FirebaseManager.firebaseInstance.database.reference
        val data = MutableLiveData<User?>()
        viewModelScope.launch(Dispatchers.IO) {
            ref.addValueEventListener(object: ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {}

                override fun onDataChange(snapshot: DataSnapshot) {
                    data.postValue(getUserFirebase(snapshot.value as Map<String, HashMap<String, Any>>, user))
                }
            })
        }
        return data
    }

    private fun getUserFirebase(map: Map<String, HashMap<String, Any>>?, user: User?): User {
        var currentUser = User()
        map?.let {
            for((key, value) in it.entries) {
                val tempUser = User(key, value)
                if (tempUser.uid == user?.uid)
                    currentUser = tempUser
            }
        }
        return currentUser
    }


}


interface UserRepository {
    fun firebaseCreateNewUserWithEmailPassword(
        email: String,
        password: String,
        viewModelScope: CoroutineScope
    ): LiveData<User?>

    fun firebaseSignInWithEmailPassword(
        email: String,
        password: String,
        viewModelScope: CoroutineScope
    ): LiveData<User?>

    fun startListeningDataChanges(viewModelScope: CoroutineScope, user: User): LiveData<User?>

    companion object {
        val userContext: UserRepository by lazy {
            UserRepositoryImpl(FirebaseManager.firebaseInstance.auth)
        }
    }
}