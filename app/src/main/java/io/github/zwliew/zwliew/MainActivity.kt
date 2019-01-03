package io.github.zwliew.zwliew

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        bottom_navigation.setupWithNavController(navController)
        with(toolbar) {
            setSupportActionBar(this)
            setupWithNavController(
                navController, AppBarConfiguration(
                    setOf(
                        R.id.home_fragment, R.id.notes_fragment, R.id.projects_fragment, R.id.about_fragment
                    )
                )
            )
        }
    }
}
