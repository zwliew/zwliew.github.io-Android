package io.github.zwliew.zwliew.destinations.projects

import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.BaseRepository
import io.github.zwliew.zwliew.util.retrofit

object ProjectsRepository : BaseRepository<ProjectsData> {
    private val service = retrofit.create(ProjectsService::class.java)

    override val data = MutableLiveData<ProjectsData>()

    override suspend fun load() {
        val response = service.getProjectsAsync().await()
        response.let {
            data.value = ProjectsData(
                it.projects
            )
        }
    }
}