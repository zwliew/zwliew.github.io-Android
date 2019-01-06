package io.github.zwliew.zwliew.destinations.projects

// Retrofit API data
data class ProjectsAPI(val projects: List<Project>)

// Internal data
data class ProjectsData(
    val projects: List<Project>
)
data class Project(
    val name: String,
    val tagline: String,
    val description: String,
    val href: String
)