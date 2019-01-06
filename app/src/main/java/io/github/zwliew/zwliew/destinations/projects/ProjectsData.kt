package io.github.zwliew.zwliew.destinations.projects

import io.github.zwliew.zwliew.Empty
import io.github.zwliew.zwliew.RepositoryStatus

// Retrofit API data
data class ProjectsApi(val projects: List<Project>)

// Internal data
data class ProjectsData(
    val status: RepositoryStatus = Empty,
    val projects: List<Project> = listOf()
)
data class Project(
    val name: String,
    val tagline: String,
    val description: String,
    val href: String
)