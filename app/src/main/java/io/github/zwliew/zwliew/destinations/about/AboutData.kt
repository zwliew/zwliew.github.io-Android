package io.github.zwliew.zwliew.destinations.about

// Retrofit API data
data class AboutAPI(
    val education: List<Education>,
    val activities: List<Activity>,
    val achievements: List<Achievement>
)

// Internal data
data class AboutData(
    var educations: List<Education>,
    var activities: List<Activity>,
    var achievements: List<Achievement>
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