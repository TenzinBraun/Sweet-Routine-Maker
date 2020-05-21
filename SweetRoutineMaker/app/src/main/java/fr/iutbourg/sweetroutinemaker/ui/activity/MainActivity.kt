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
import fr.iutbourg.sweetroutinemaker.data.model.ChildProfile
import fr.iutbourg.sweetroutinemaker.data.model.PictureTodo
import fr.iutbourg.sweetroutinemaker.data.model.User
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import fr.iutbourg.sweetroutinemaker.data.utils.PreferencesUtils
import fr.iutbourg.sweetroutinemaker.extension.addElement
import fr.iutbourg.sweetroutinemaker.extension.toBase64
import fr.iutbourg.sweetroutinemaker.ui.widget.TagAddDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, TagListHandler {

    private val firebaseReference = FirebaseManager.firebaseInstance.database.reference
    private var tags = emptyList<String>()
    private val pictures = mutableListOf<PictureTodo>()
    private var currentUser: User = User()

    override fun onStart() {
        super.onStart()
        currentUser = intent.getSerializableExtra("user") as User
        val navController = findNavController(R.id.appFragmentContainer)
        val bundle = Bundle()
        if (currentUser.children == null) {
            currentUser.children = ArrayList()
            /*currentUser.childProfile?.add(ChildProfile(null, "Louis", null))
            currentUser.childProfile?.add(ChildProfile(null, "Hugo", null))*/
        }

        PreferencesUtils.saveString("userKey", currentUser.key!!, this)
        bundle.putSerializable("childrenList", currentUser.children)
        navController.setGraph(R.navigation.navigation_controller, bundle)
    }


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
            data?.let {
                val dataTarget: Parcelable

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
                pictures.addElement(PictureTodo(it, tags))
            }

            currentUser.key?.let {
                firebaseReference.child("/$it").child("/pictures").setValue(pictures)
                firebaseReference.child("/$it").child("/tags").setValue(tags)
            }
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
  
    override fun onNavigateUp(): Boolean {
        return findNavController(R.id.appFragmentContainer).navigateUp()
    }

    private fun setupToolbar() {
        setSupportActionBar(appToolbar)
        appToolbar.setNavigationOnClickListener {
            onNavigateUp()
        }
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

    // 3 - Configure NavigationView
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.importPhoto) {
            TagAddDialog(this).show()
        }
        return true
    }

    override fun importTagList(list: List<String>) {
        tags = list
        val intent =
            Intent(Intent.ACTION_GET_CONTENT).putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                .setType("image/*")
        startActivityForResult(intent, REQUEST_CODE)
    }
}

interface TagListHandler {
    fun importTagList(list: List<String>)
}