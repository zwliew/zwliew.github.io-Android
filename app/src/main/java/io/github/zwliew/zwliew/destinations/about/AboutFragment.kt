package io.github.zwliew.zwliew.destinations.about


import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProviders
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.destinations.BaseFragment
import io.github.zwliew.zwliew.util.launchUrl
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment(
    @LayoutRes override val layoutId: Int = R.layout.fragment_about,
    @MenuRes override val menuId: Int = R.menu.menu_generic_toolbar
) : BaseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure event handlers
        with(view) {
            resume_button.setOnClickListener {
                launchUrl(this, context, "https://zwliew.netlify.com/res/resume.pdf")
            }
        }

        val viewModel = ViewModelProviders.of(this).get(AboutViewModel::class.java)
        val listAdapter = AboutCategoryListAdapter(
            lifecycle, viewModel.educations, viewModel.activities, viewModel.achievements
        )

        // Set up ListView
        category_list.setAdapter(listAdapter)
        ViewCompat.setNestedScrollingEnabled(category_list, true)
    }
}
