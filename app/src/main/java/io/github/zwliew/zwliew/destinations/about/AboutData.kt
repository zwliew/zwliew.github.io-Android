package io.github.zwliew.zwliew.destinations.about

data class AboutList(
    val education: List<Education>,
    val activities: List<Activity>,
    val achievements: List<Achievement>
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

data class AboutCache(
    var initialized: Boolean = false,
    var educations: List<Education> = listOf(),
    var activities: List<Activity> = listOf(),
    var achievements: List<Achievement> = listOf()
)