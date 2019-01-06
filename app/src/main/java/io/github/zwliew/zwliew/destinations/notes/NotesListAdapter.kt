package io.github.zwliew.zwliew.destinations.notes

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.util.API_BASE_URL
import io.github.zwliew.zwliew.util.viewUri
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_note_item.*

class NotesListAdapter : ListAdapter<Note, NoteViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NoteViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    private lateinit var slug: String

    init {
        summary_text.movementMethod = ScrollingMovementMethod()
        containerView.setOnClickListener {
            // TODO: Launch in app
            viewUri(it, it.context, "${API_BASE_URL}notes/$slug")
        }
    }

    fun bind(note: Note) {
        note.let {
            slug = it.slug
            title_text.text = it.title
            summary_text.text = it.summary
        }
    }
}

object DiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.slug == newItem.slug
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}