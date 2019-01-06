package io.github.zwliew.zwliew.destinations.about


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import io.github.zwliew.zwliew.Failed
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.destinations.BaseFragment
import io.github.zwliew.zwliew.util.API_BASE_URL
import io.github.zwliew.zwliew.util.viewUri
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment(
    @LayoutRes override val layoutId: Int = R.layout.fragment_about,
    @MenuRes override val menuId: Int = R.menu.menu_refreshable_toolbar
) : BaseFragment() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(AboutViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Do all the initialization in onViewCreated() instead of onCreateView()
        // in order to use the synthetic properties in Kotlin Android Extensions

        // Configure event handlers
        resume_button.setOnClickListener {
            viewUri(it, it.context, "${API_BASE_URL}res/resume.pdf")
        }

        val listAdapter = AboutCategoryListAdapter()

        // Bind ViewModel state
        viewModel.apply {
            data.observe({ lifecycle }) {
                listAdapter.apply {
                    educations = it.educations
                    activities = it.activities
                    achievements = it.achievements
                    notifyDataSetChanged()
                }
            }
            status.observe({ lifecycle }) {
                if (it == Failed) {
                    Snackbar.make(view, R.string.no_network_message, Snackbar.LENGTH_SHORT)
                        .setAction(R.string.retry_action) {
                            handleRefresh()
                        }.show()
                }
            }
        }

        // Set up ListView
        category_list.setAdapter(listAdapter)
        ViewCompat.setNestedScrollingEnabled(category_list, true)

        // Set up initial state
        viewModel.handleRefresh()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refresh) {
            viewModel.handleRefresh()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
