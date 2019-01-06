package io.github.zwliew.zwliew.destinations.notes

import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.BaseRepository
import io.github.zwliew.zwliew.Failed
import io.github.zwliew.zwliew.Loaded
import io.github.zwliew.zwliew.Loading
import io.github.zwliew.zwliew.util.retrofit
import java.net.UnknownHostException

object NotesRepository : BaseRepository<NotesState> {
    private val service = retrofit.create(NotesService::class.java)

    override val state = MutableLiveData<NotesState>()

    override suspend fun load() {
        state.value = state.value?.copy(status = Loading) ?: NotesState(Loading)

        try {
            val response = service.getNotesAsync().await()
            response.let {
                state.value = NotesState(Loaded, it.notes)
            }
        } catch (e: UnknownHostException) {
            // Handle no network connection
            state.value = state.value?.copy(status = Failed) ?: NotesState(Failed)
        }
    }
}