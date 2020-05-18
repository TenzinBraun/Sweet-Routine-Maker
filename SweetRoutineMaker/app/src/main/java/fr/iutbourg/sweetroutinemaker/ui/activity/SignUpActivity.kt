package fr.iutbourg.sweetroutinemaker.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.callback.FirebaseDatabaseAction
import fr.iutbourg.sweetroutinemaker.data.model.User
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.sign_up_activity.*

class SignUpActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userViewModel: UserViewModel
    private  val db = FirebaseManager.firebaseInstance.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)
        this.run {
            userViewModel = ViewModelProvider(this, UserViewModel).get()
        }

        auth = FirebaseManager.firebaseInstance.auth

        validate_sign_up_button.setOnClickListener {
            signUpUser()
        }

    }

    private fun signUpUser() {
        if (sign_up_email.text.toString().isEmpty()) {
            sign_up_email.error = "Please enter email"
            sign_up_email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(sign_up_email.text.toString()).matches()) {
            sign_up_email.error = "Please enter valid email"
            sign_up_email.requestFocus()
            return
        }

        if (sign_up_password.text.toString().isEmpty()) {
            sign_up_password.error = "Please enter password"
            sign_up_password.requestFocus()
            return
        }

        userViewModel.createUserWithEmailPassword(sign_up_email.text.toString(), sign_up_password.text.toString()).observe(this) {
            if (it != null) {
                userViewModel.addUserInFirebase(it, db)
                //add(it)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(baseContext, "Sign Up failed. Please try again", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*

    private fun signUpUser() {
        if (sign_up_email.text.toString().isEmpty()) {
            sign_up_email.error = "Please enter email"
            sign_up_email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(sign_up_email.text.toString()).matches()) {
            sign_up_email.error = "Please enter valid email"
            sign_up_email.requestFocus()
            return
        }

        if (sign_up_password.text.toString().isEmpty()) {
            sign_up_password.error = "Please enter password"
            sign_up_password.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(sign_up_email.text.toString(), sign_up_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                    }

                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Please try again", Toast.LENGTH_SHORT).show()
                }
            }
    }

     */
}