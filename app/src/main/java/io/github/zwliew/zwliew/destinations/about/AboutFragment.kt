package io.github.zwliew.zwliew.destinations.about


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.destinations.BaseFragment
import io.github.zwliew.zwliew.util.launchUrl

class AboutFragment(
    @LayoutRes override val layoutId: Int = R.layout.fragment_about,
    @MenuRes override val menuId: Int = R.menu.menu_generic_toolbar
) : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        with(view) {
            val resumeButton = findViewById<Button>(R.id.resume_button)
            resumeButton.setOnClickListener {
                launchUrl(this, context, "https://zwliew.netlify.com/res/resume.pdf")
            }
        }
        return view
    }
}
