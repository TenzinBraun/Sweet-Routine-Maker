package fr.iutbourg.sweetroutinemaker.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.callback.FirebaseDatabaseAction
import fr.iutbourg.sweetroutinemaker.data.model.User
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.data.utils.PreferencesUtils
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.sign_up_activity.*

const val REQUEST_CODE = 4500

class LoginActivity: AppCompatActivity(), FirebaseDatabaseAction<User> {
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseManager.firebaseInstance.database.reference
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        this.run {
            userViewModel = ViewModelProvider(this, UserViewModel).get()
        }

        auth = FirebaseManager.firebaseInstance.auth

        sign_up_button.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        login_button.setOnClickListener {
            login()
        }

    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                userViewModel.getDataOfUser(User(uid = currentUser.uid)).observe(this) {
                    // TODO get user and send to next activity
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user", it) // or send childrenList instead
                    startActivity(intent)

                }
            } else {
                Toast.makeText(baseContext, "Please verify your email", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(baseContext, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun login() {
        if (edit_text_email.text.toString().isEmpty()) {
            sign_up_email.error = "Please enter email"
            sign_up_email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(edit_text_email.text.toString()).matches()) {
            sign_up_email.error = "Please enter valid email"
            sign_up_email.requestFocus()
            return
        }

        if (edit_text_password.text.toString().isEmpty()) {
            sign_up_password.error = "Please enter password"
            sign_up_password.requestFocus()
            return
        }

        userViewModel.signInWithEmailAndPassword(edit_text_email.text.toString(), edit_text_password.text.toString()).observe(this) {
            if (it != null) {
                val user = auth.currentUser
                updateUI(user)

            } else {
                Toast.makeText(baseContext, "Login failed", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }

    override fun edit(position: Int, model: User) {

    }

    override fun delete(model: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(model: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
/*
    private fun login() {
        if (edit_text_email.text.toString().isEmpty()) {
            sign_up_email.error = "Please enter email"
            sign_up_email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(edit_text_email.text.toString()).matches()) {
            sign_up_email.error = "Please enter valid email"
            sign_up_email.requestFocus()
            return
        }

        if (edit_text_password.text.toString().isEmpty()) {
            sign_up_password.error = "Please enter password"
            sign_up_password.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(edit_text_email.text.toString(), edit_text_password.text.toString()).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                updateUI(user)
            } else {
                Toast.makeText(baseContext, "Login failed", Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }

    }

 */
}