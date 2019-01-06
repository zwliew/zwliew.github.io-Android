package io.github.zwliew.zwliew.destinations.notes

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.destinations.BaseFragment
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment(
    @LayoutRes override val layoutId: Int = R.layout.fragment_notes,
    @MenuRes override val menuId: Int = R.menu.menu_notes_toolbar
) : BaseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Do all the initialization in onViewCreated() instead of onCreateView()
        // in order to use the synthetic properties in Kotlin Android Extensions
        val viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        val listAdapter = NotesListAdapter()

        // Bind ViewModel state
        viewModel.apply {
            notes.observe({ lifecycle }) {
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

        // Set up the RecyclerView
        list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(view.context)
            adapter = listAdapter
        }

        // Set up initial state
        viewModel.handleRefresh()
    }
}
