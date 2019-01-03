package io.github.zwliew.zwliew.routes.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.util.BASE_URL
import io.github.zwliew.zwliew.util.launchUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_note_item.*

class NotesListAdapter : ListAdapter<NoteSummary, NoteViewHolder>(DiffCallback) {
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
        containerView.setOnClickListener {
            launchUrl(it, it.context, "$BASE_URL/notes/$slug")
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