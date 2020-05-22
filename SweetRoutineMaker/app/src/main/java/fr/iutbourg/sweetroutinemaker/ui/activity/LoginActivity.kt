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
import com.google.firebase.auth.FirebaseUser
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.User
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.ui.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.login_activity.*

const val REQUEST_CODE = 4500

class LoginActivity: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
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
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user", it) 
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
            edit_text_email.error = "Please enter email"
            edit_text_email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(edit_text_email.text.toString()).matches()) {
            edit_text_email.error = "Please enter valid email"
            edit_text_email.requestFocus()
            return
        }

        if (edit_text_password.text.toString().isEmpty()) {
            edit_text_password.error = "Please enter password"
            edit_text_password.requestFocus()
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

}