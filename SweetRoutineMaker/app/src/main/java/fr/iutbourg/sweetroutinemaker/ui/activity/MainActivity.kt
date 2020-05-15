package fr.iutbourg.sweetroutinemaker.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import fr.iutbourg.sweetroutinemaker.R
import fr.iutbourg.sweetroutinemaker.data.model.ChildProfile
import fr.iutbourg.sweetroutinemaker.data.model.User
import fr.iutbourg.sweetroutinemaker.data.networking.FirebaseManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var currentUser: User = User()

    override fun onStart() {
        super.onStart()
        currentUser = intent.getSerializableExtra("user") as User
        val navController = findNavController(R.id.appFragmentContainer)
        val bundle = Bundle()
        if (currentUser.childProfile == null) {
            currentUser.childProfile = ArrayList()
            currentUser.childProfile?.add(ChildProfile(null, "Louis", null))
            currentUser.childProfile?.add(ChildProfile(null, "Hugo", null))
        }
        bundle.putSerializable("childrenList", currentUser.childProfile)
        navController.setGraph(R.navigation.navigation_controller, bundle)
    }

    /*fun testFragment(){
        val bundle = Bundle()
        val navHostFragment = appFragmentContainer as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation_controller)
        val testMano = currentUser.childProfile as MutableList<ChildProfile>
        testMano.add(ChildProfile(null, "Arthurito", null))
        bundle.putSerializable("childrenList", arrayOf(testMano))
        val navArgument = NavArgument.Builder().setDefaultValue()
        graph.add

    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        configureDrawerLayout()
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
    }

    // 3 - Configure NavigationView
    private fun configureNavigationView() {
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
