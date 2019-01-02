package io.github.zwliew.zwliew.routes.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.util.lazyUnsafe
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment() {
    private val viewModel by lazyUnsafe {
        ViewModelProviders.of(this).get(NotesViewModel::class.java)
    }
    private val listAdapter by lazyUnsafe {
        NotesListAdapter { v, slug ->
            viewModel.handleNoteClicked(v, slug)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        // Bind ViewModel state
        viewModel.notes.observe(this, Observer {
            listAdapter.submitList(it)
        })
        viewModel.refreshing.observe(this, Observer {
            layout.isRefreshing = it
        })

        // Refresh to populate initial data
        viewModel.handleRefresh()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Do all the initialization in onViewCreated() instead of onCreateView()
        // in order to use the synthetic properties in Kotlin Android Extensions

        // Set up the SwipeRefreshLayout
        layout.setOnRefreshListener {
            viewModel.handleRefresh()
        }

        // Set up the RecyclerView
        with(list) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(view.context)
            adapter = listAdapter
        }
    }
}
