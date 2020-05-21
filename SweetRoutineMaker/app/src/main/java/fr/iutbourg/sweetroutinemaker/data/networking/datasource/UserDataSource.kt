package fr.iutbourg.sweetroutinemaker.data.networking.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import fr.iutbourg.sweetroutinemaker.data.model.User
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager

private class UserDataSourceImpl(
    private val auth: FirebaseAuth,
    private val ref: DatabaseReference
) : UserDataSource {

    override suspend fun firebaseCreateNewUserWithEmailPassword(
        email: String,
        password: String
    ): User? {
        var user: User? = null
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val isNewUser: Boolean = task.getResult()?.additionalUserInfo!!.isNewUser
                val firebaseUser = auth.currentUser
                firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        user = User(
                            null,
                            firebaseUser.uid,
                            firebaseUser.displayName,
                            firebaseUser.email,
                            true,
                            isNewUser
                        )
                    }
                }
            }
        }
        return user
    }

    override suspend fun firebaseSignInWithEmailPassword(email: String, password: String): User? {
        var user: User? = null
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val isNewUser: Boolean = task.getResult()?.additionalUserInfo!!.isNewUser
                val firebaseUser = auth.currentUser

                if (firebaseUser != null) {
                    user = User(
                        null,
                        firebaseUser.uid,
                        firebaseUser.displayName,
                        firebaseUser.email,
                        true,
                        isNewUser
                    )
                }
            }

        }
        return user
    }
}

interface UserDataSource {

    suspend fun firebaseCreateNewUserWithEmailPassword(email: String, password: String): User?
    suspend fun firebaseSignInWithEmailPassword(email: String, password: String): User?

    companion object {
        val instance: UserDataSource by lazy {
            UserDataSourceImpl(
                FirebaseManager.firebaseInstance.auth,
                FirebaseManager.firebaseInstance.database.reference
            )
        }
    }
}