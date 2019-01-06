package io.github.zwliew.zwliew.destinations.notes

import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.*
import io.github.zwliew.zwliew.util.retrofit
import java.net.UnknownHostException

object NotesRepository : BaseRepository<NotesState> {
    private val service = retrofit.create(NotesService::class.java)

    override val state = MutableLiveData<NotesState>()

    override suspend fun load() {
        state.value = state.value?.copy(status = Event(Loading)) ?: NotesState(Event(Loading))

        try {
            val response = service.getNotesAsync().await()
            response.let {
                state.value = NotesState(Event(Loaded), it.notes)
            }
        } catch (e: UnknownHostException) {
            // Handle no network connection
            state.value = state.value?.copy(status = Event(Failed)) ?: NotesState(Event(Failed))
        }
    }
}