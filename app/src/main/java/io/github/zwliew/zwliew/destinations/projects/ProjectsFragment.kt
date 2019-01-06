package io.github.zwliew.zwliew.destinations.projects


import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.destinations.BaseFragment
import kotlinx.android.synthetic.main.fragment_projects.*

class ProjectsFragment(
    @LayoutRes override val layoutId: Int = R.layout.fragment_projects,
    @MenuRes override val menuId: Int = R.menu.menu_refreshable_toolbar
) : BaseFragment() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ProjectsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Do all the initialization in onViewCreated() instead of onCreateView()
        // in order to use the synthetic properties in Kotlin Android Extensions
        val listAdapter = ProjectsListAdapter()

        // Bind ViewModel state
        viewModel.apply {
            projects.observe({ lifecycle }) {
                listAdapter.submitList(it)
            }
            refreshing.observe({ lifecycle }) {
                layout.isRefreshing = it
            }
        }

        // Set up the SwipeRefreshLayout
        layout.setOnRefreshListener {
            viewModel.handleRefresh()
        }

        // Set up RecyclerView
        list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(view.context)
            adapter = listAdapter
        }

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
