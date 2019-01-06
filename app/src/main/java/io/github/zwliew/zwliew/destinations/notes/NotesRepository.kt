package io.github.zwliew.zwliew.destinations.notes

import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.BaseRepository
import io.github.zwliew.zwliew.Failed
import io.github.zwliew.zwliew.Loaded
import io.github.zwliew.zwliew.Loading
import io.github.zwliew.zwliew.util.retrofit
import java.net.UnknownHostException

object NotesRepository : BaseRepository<NotesData> {
    private val service = retrofit.create(NotesService::class.java)

    override val data = MutableLiveData<NotesData>()

    override suspend fun load() {
        data.value = data.value?.copy(status = Loading) ?: NotesData(Loading)

        try {
            val response = service.getNotesAsync().await()
            response.let {
                data.value = NotesData(Loaded, it.notes)
            }
        } catch (e: UnknownHostException) {
            // Handle no network connection
            data.value = data.value?.copy(status = Failed) ?: NotesData(Failed)
        }
    }
}