package io.github.zwliew.zwliew.routes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.zwliew.zwliew.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_note_item.*

class NotesListAdapter(
    private val handleClick: (View, String) -> Unit
) : ListAdapter<NoteSummary, NoteViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_note_item, parent, false)
        ) { view, slug ->
            handleClick(view, slug)
        }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NoteViewHolder(
    override val containerView: View,
    private val handleClick: (View, String) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    private lateinit var slug: String

    init {
        with(containerView) {
            setOnClickListener {
                handleClick(it, slug)
            }
        }
    }

    fun bind(note: NoteSummary) {
        with(note) {
            this@NoteViewHolder.slug = slug
            title_text.text = title
            summary_text.text = summary
        }
    }
}

object DiffCallback : DiffUtil.ItemCallback<NoteSummary>() {
    override fun areItemsTheSame(oldItem: NoteSummary, newItem: NoteSummary): Boolean {
        return oldItem.slug == newItem.slug
    }

    override fun areContentsTheSame(oldItem: NoteSummary, newItem: NoteSummary): Boolean {
        return oldItem == newItem
    }
}