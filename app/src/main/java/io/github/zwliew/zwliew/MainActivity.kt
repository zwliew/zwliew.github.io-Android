package io.github.zwliew.zwliew

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

const val ACTION_VIEW_NOTES = "io.github.zwliew.zwliew.action.VIEW_NOTES"
const val ACTION_VIEW_PROJECTS = "io.github.zwliew.zwliew.action.VIEW_PROJECTS"
const val ACTION_VIEW_ABOUT = "io.github.zwliew.zwliew.action.VIEW_ABOUT"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        bottom_navigation.setupWithNavController(navController)
        toolbar.apply {
            setSupportActionBar(this)
            setupWithNavController(
                navController, AppBarConfiguration(
                    setOf(
                        R.id.home_fragment, R.id.notes_fragment, R.id.projects_fragment, R.id.about_fragment
                    )
                )
            )
        }

        // Handle app shortcut intents
        when (intent.action) {
            ACTION_VIEW_NOTES -> R.id.notes_fragment
            ACTION_VIEW_PROJECTS -> R.id.projects_fragment
            ACTION_VIEW_ABOUT -> R.id.about_fragment
            else -> null
        }?.let {
            navController.navigate(it)
        }
    }
}
