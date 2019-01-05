package io.github.zwliew.zwliew.destinations.projects

import io.github.zwliew.zwliew.util.retrofit

object ProjectsRepository {
    private val service = retrofit.create(ProjectsService::class.java)

    private val cache = ProjectsCache()

    suspend fun loadProjects(): List<Project> {

        // Check cache
        if (cache.initialized) {
            return cache.projects
        }

        // Fetch from network
        val response = service.getProjectsAsync().await()
        with(response.projects) {
            cache.projects = this
            cache.initialized = true
            return this
        }
    }
}