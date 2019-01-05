package io.github.zwliew.zwliew.destinations.projects

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.util.viewUri
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_project_item.*

class ProjectsListAdapter : ListAdapter<Project, ProjectViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_project_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProjectViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    private lateinit var href: String

    init {
        description_text.movementMethod = ScrollingMovementMethod()
        with(containerView) {
            setOnClickListener {
                viewUri(it, it.context, href)
            }
        }
    }

    fun bind(project: Project) {
        with(project) {
            this@ProjectViewHolder.href = href
            text.text = name
            description_text.text = description
        }
    }
}

object DiffCallback : DiffUtil.ItemCallback<Project>() {
    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }
}