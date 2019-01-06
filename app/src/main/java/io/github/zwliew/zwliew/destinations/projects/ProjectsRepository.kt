package io.github.zwliew.zwliew.destinations.projects

import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.BaseRepository
import io.github.zwliew.zwliew.Failed
import io.github.zwliew.zwliew.Loaded
import io.github.zwliew.zwliew.Loading
import io.github.zwliew.zwliew.util.retrofit
import java.net.UnknownHostException

object ProjectsRepository : BaseRepository<ProjectsState> {
    private val service = retrofit.create(ProjectsService::class.java)

    override val state = MutableLiveData<ProjectsState>()

    override suspend fun load() {
        state.value = state.value?.copy(status = Loading) ?: ProjectsState(Loading)
        try {
            val response = service.getProjectsAsync().await()
            response.let {
                state.value = ProjectsState(Loaded, it.projects)
            }
        } catch (e: UnknownHostException) {
            // Handle no network connection
            state.value = state.value?.copy(status = Failed) ?: ProjectsState(Failed)
        }
    }
}