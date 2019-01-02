package io.github.zwliew.zwliew.routes.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.util.retrofit
import timber.log.Timber

object ProjectsRepository {
    private val service = retrofit.create(ProjectsService::class.java)

    private val cache = ProjectsCache()

    suspend fun loadProjects(): LiveData<List<Project>> {
        val projects = MutableLiveData<List<Project>>()

        // Check cache
        if (cache.initialized) {
            Timber.d("Cache already initialized")
            projects.value = cache.projects
            return projects
        }

        // Fetch from network
        val response = service.getProjectsAsync().await()
        with(response.projects) {
            cache.projects = this
            cache.initialized = true
            projects.value = this
        }
        return projects
    }
}