package com.kromer.ostaz.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.kromer.ostaz.R
import com.kromer.ostaz.ui.base.BaseActivity
import com.kromer.ostaz.utils.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {

        val navGraphIds = listOf(
            R.navigation.navigation_home,
            R.navigation.navigation_dashboard,
            R.navigation.navigation_notifications
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = nav_view.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })

        currentNavController = controller
    }
}
