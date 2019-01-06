package io.github.zwliew.zwliew.destinations.about


import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProviders
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.destinations.BaseFragment
import io.github.zwliew.zwliew.util.API_BASE_URL
import io.github.zwliew.zwliew.util.viewUri
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment(
    @LayoutRes override val layoutId: Int = R.layout.fragment_about,
    @MenuRes override val menuId: Int = R.menu.menu_generic_toolbar
) : BaseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Do all the initialization in onViewCreated() instead of onCreateView()
        // in order to use the synthetic properties in Kotlin Android Extensions

        // Configure event handlers
        resume_button.setOnClickListener {
            viewUri(it, it.context, "${API_BASE_URL}res/resume.pdf")
        }

        // Set up ListView
        val viewModel = ViewModelProviders.of(this).get(AboutViewModel::class.java)
        val listAdapter = AboutCategoryListAdapter().apply {
            viewModel.educations.observe({ lifecycle }) {
                this.educations = it
                notifyDataSetChanged()
            }
            viewModel.activities.observe({ lifecycle }) {
                this.activities = it
                notifyDataSetChanged()
            }
            viewModel.achievements.observe({ lifecycle }) {
                this.achievements = it
                notifyDataSetChanged()
            }
        }
        category_list.setAdapter(listAdapter)
        ViewCompat.setNestedScrollingEnabled(category_list, true)

        // Set up initial state
        viewModel.handleRefresh()
    }
}
