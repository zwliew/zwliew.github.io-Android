package io.github.zwliew.zwliew.destinations.projects

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ProjectsService {
    @GET("res/data/projects.json")
    fun getProjectsAsync(): Deferred<ProjectList>
}