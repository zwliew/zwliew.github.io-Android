package io.github.zwliew.zwliew.destinations.notes

import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.BaseRepository
import io.github.zwliew.zwliew.util.retrofit

object NotesRepository : BaseRepository<NotesData> {
    private val service = retrofit.create(NotesService::class.java)

    override val data = MutableLiveData<NotesData>()

    override suspend fun load() {
        val response = service.getNotesAsync().await()
        response.let {
            data.value = NotesData(
                it.notes
            )
        }
    }
}