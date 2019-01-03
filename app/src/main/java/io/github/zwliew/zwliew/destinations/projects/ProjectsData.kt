package io.github.zwliew.zwliew.destinations.projects

data class ProjectList(val projects: List<Project>)

data class Project(
    val name: String,
    val tagline: String,
    val description: String,
    val href: String
)

data class ProjectsCache(
    var initialized: Boolean = false,
    var projects: List<Project> = listOf()
)