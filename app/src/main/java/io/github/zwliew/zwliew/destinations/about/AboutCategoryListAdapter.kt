package io.github.zwliew.zwliew.destinations.about

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import io.github.zwliew.zwliew.R
import io.github.zwliew.zwliew.util.viewUri

const val EDUCATIONS_TITLE = "Education"
const val ACTIVITIES_TITLE = "Activities"
const val ACHIEVEMENTS_TITLE = "Achievements"

class AboutCategoryListAdapter : BaseExpandableListAdapter() {
    var educations: List<Education> = listOf()
    var activities: List<Activity> = listOf()
    var achievements: List<Achievement> = listOf()

    override fun getGroup(groupPos: Int): List<AboutCategoryItem>? {
        return when (groupPos) {
            0 -> educations
            1 -> activities
            2 -> achievements
            else -> throw IllegalArgumentException()
        }
    }

    private fun getGroupTitle(groupPos: Int): String {
        return when (groupPos) {
            0 -> EDUCATIONS_TITLE
            1 -> ACTIVITIES_TITLE
            2 -> ACHIEVEMENTS_TITLE
            else -> throw IllegalArgumentException()
        }
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = false

    override fun hasStableIds() = false

    override fun getGroupView(groupPos: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.view_about_category_group, parent, false)
        val titleText = view.findViewById<TextView>(R.id.title_text)
        titleText.text = getGroupTitle(groupPos)
        return view
    }

    override fun getChildrenCount(groupPos: Int) = getGroup(groupPos)?.size ?: 0

    override fun getChild(groupPos: Int, childPos: Int) = getGroup(groupPos)?.get(childPos)

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()

    override fun getChildView(
        groupPos: Int,
        childPos: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val data = getChild(groupPos, childPos)
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.view_about_category_item, parent, false)
        view.run {
            val itemText = findViewById<TextView>(R.id.item_text)
            itemText.text = when (getGroupTitle(groupPos)) {
                EDUCATIONS_TITLE -> formatEducationText(data as Education)
                ACTIVITIES_TITLE -> formatActivityText(data as Activity)
                ACHIEVEMENTS_TITLE -> formatAchievementText(data as Achievement)
                else -> throw IllegalArgumentException()
            }
            setOnClickListener {
                viewUri(it, it.context, data.href)
            }
        }
        return view
    }

    private fun formatEducationText(data: Education): String {
        val (name, location, period, _) = data
        return "$name, $location ($period)"
    }

    private fun formatActivityText(data: Activity): String {
        val (name, period, _) = data
        return "$name ($period)"
    }

    private fun formatAchievementText(data: Achievement): String {
        val (name, award, _) = data
        return "$name - $award"
    }

    override fun getChildId(groupPos: Int, childPos: Int) = childPos.toLong()

    override fun getGroupCount() = 3
}