package io.github.zwliew.zwliew.destinations.projects

import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.BaseRepository
import io.github.zwliew.zwliew.Failed
import io.github.zwliew.zwliew.Loaded
import io.github.zwliew.zwliew.Loading
import io.github.zwliew.zwliew.util.retrofit
import java.net.UnknownHostException

object ProjectsRepository : BaseRepository<ProjectsData> {
    private val service = retrofit.create(ProjectsService::class.java)

    override val data = MutableLiveData<ProjectsData>()

    override suspend fun load() {
        data.value = data.value?.copy(status = Loading) ?: ProjectsData(Loading)
        try {
            val response = service.getProjectsAsync().await()
            response.let {
                data.value = ProjectsData(Loaded, it.projects)
            }
        } catch (e: UnknownHostException) {
            // Handle no network connection
            data.value = data.value?.copy(status = Failed) ?: ProjectsData(Failed)
        }
    }
}