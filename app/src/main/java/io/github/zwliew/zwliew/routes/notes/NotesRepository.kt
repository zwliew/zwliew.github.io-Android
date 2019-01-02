package io.github.zwliew.zwliew.routes.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.util.retrofit
import timber.log.Timber

object NotesRepository {
    private val service = retrofit.create(NotesService::class.java)

    private val cache = NotesCache()

    suspend fun loadNotes(): LiveData<List<NoteSummary>> {
        val notes = MutableLiveData<List<NoteSummary>>()

        // Check cache
        if (cache.initialized) {
            Timber.d("Cache already initialized")
            notes.value = cache.summaries
            return notes
        }

        // Fetch from network
        val response = service.getNotesAsync().await()
        with(response.notes) {
            cache.summaries = this
            cache.initialized = true
            notes.value = this
        }
        return notes
    }
}