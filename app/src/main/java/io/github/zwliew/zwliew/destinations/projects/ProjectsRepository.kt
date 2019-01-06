package io.github.zwliew.zwliew.destinations.projects

import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.*
import io.github.zwliew.zwliew.util.retrofit
import java.net.UnknownHostException

object ProjectsRepository : BaseRepository<ProjectsState> {
    private val service = retrofit.create(ProjectsService::class.java)

    override val state = MutableLiveData<ProjectsState>()

    override suspend fun load() {
        state.value = state.value?.copy(status = Event(Loading)) ?: ProjectsState(Event(Loading))
        try {
            val response = service.getProjectsAsync().await()
            response.let {
                state.value = ProjectsState(Event(Loaded), it.projects)
            }
        } catch (e: UnknownHostException) {
            // Handle no network connection
            state.value = state.value?.copy(status = Event(Failed)) ?: ProjectsState(Event(Failed))
        }
    }
}