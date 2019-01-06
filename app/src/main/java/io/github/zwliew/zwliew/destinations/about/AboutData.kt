package io.github.zwliew.zwliew.destinations.about

import io.github.zwliew.zwliew.Empty
import io.github.zwliew.zwliew.Event
import io.github.zwliew.zwliew.RepositoryStatus

// Retrofit API data
data class AboutApi(
    val education: List<Education>,
    val activities: List<Activity>,
    val achievements: List<Achievement>
)

// Internal data
data class AboutState(
    val status: Event<RepositoryStatus> = Event(Empty),
    val data: AboutData = AboutData()
)

data class AboutData(
    val educations: List<Education> = listOf(),
    val activities: List<Activity> = listOf(),
    val achievements: List<Achievement> = listOf()
)

interface AboutCategoryItem {
    val href: String
}

data class Education(
    val name: String,
    val location: String,
    val period: String,
    override val href: String
) : AboutCategoryItem

data class Activity(
    val name: String,
    val period: String,
    override val href: String
) : AboutCategoryItem

data class Achievement(
    val name: String,
    val award: String,
    override val href: String
) : AboutCategoryItem