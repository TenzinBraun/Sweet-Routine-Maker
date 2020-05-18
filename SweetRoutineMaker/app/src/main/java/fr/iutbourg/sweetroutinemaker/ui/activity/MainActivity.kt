package fr.iutbourg.sweetroutinemaker.ui.activity

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.PictureTodo
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.extension.addElement
import fr.iutbourg.sweetroutinemaker.extension.toBase64
import fr.iutbourg.sweetroutinemaker.ui.widget.TagAddDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, TagListHandler {

    private val firebaseReference = FirebaseManager.firebaseInstance.database.reference
    private var tagList = emptyList<String>()
    private val pictures = mutableListOf<PictureTodo>()
    private lateinit var userKey : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        configureDrawerLayout()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val pictureImportedToBase64 = mutableListOf<String>()
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val dataTarget: Parcelable
            data?.let {
                if (it.clipData != null) {
                    dataTarget = it.clipData as ClipData
                    for (i in 0 until dataTarget.itemCount) {
                        val uri = dataTarget.getItemAt(i).uri
                        convertUriToBase64(uri, pictureImportedToBase64)
                    }
                } else {
                    dataTarget = it.data as Uri
                    convertUriToBase64(dataTarget, pictureImportedToBase64)
                }
            }

            pictureImportedToBase64.forEach {
                pictures.addElement(PictureTodo(it, tagList))
            }
//            firebaseReference.child(userKey).child("/picture").setValue(pictures)

        }
    }


    private fun waitingToImplement(){
        val pictures : () -> (Unit) = {
            val listOfPictures = mutableListOf<PictureTodo>()
//            pictureImportedToBase64.forEach {
//                listOfPictures.add(PictureTodo(null, pictureImportedToBase64))
//            }
        }
    }

    private fun convertUriToBase64(
        uri: Uri?,
        pictureImportedToBase64: MutableList<String>
    ) {
        uri?.let {
            val inputStream = contentResolver.openInputStream(uri)
            val base64 = BitmapFactory.decodeStream(inputStream).toBase64()
            pictureImportedToBase64.add(base64)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(appToolbar)
        appToolbar.setNavigationOnClickListener {
            onNavigateUp()
        }
    }

    override fun onNavigateUp(): Boolean {
        return findNavController(R.id.appFragmentContainer).navigateUp()
    }

    private fun configureDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            appToolbar,
            R.string.nav_app_bar_open_drawer_description,
            R.string.nav_app_bar_navigate_up_description
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.importPhoto) {

            TagAddDialog(this).show()


            //TODO: Show Dialog to add Tag into pictures that would be imported
            //TODO: Then run intent to select one or multiple photos

        }
        return true
    }

    override fun importTagList(list: List<String>) {
        tagList = list
        val intent =
            Intent(Intent.ACTION_GET_CONTENT).putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                .setType("image/*")
        startActivityForResult(intent, REQUEST_CODE)
    }
}


interface TagListHandler {
    fun importTagList(list: List<String>)
}