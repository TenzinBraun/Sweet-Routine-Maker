package fr.iutbourg.sweetroutinemaker.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import fr.iutbourg.sweetroutinemaker.R
import kotlinx.android.synthetic.main.activity_main.*


class HomeScreenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        configureDrawerLayout()
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

    // 2 - Configure Drawer Layout
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
        TODO("Not yet implemented")
    }


}
