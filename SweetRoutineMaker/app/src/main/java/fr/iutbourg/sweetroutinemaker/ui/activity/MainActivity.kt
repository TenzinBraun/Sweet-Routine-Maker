package fr.iutbourg.sweetroutinemaker.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userKey = intent.getSerializableExtra("user")
    }
}
