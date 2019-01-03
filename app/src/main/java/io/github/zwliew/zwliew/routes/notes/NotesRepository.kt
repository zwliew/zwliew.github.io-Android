package io.github.zwliew.zwliew.routes.notes

import io.github.zwliew.zwliew.util.retrofit
import timber.log.Timber

object NotesRepository {
    private val service = retrofit.create(NotesService::class.java)

    private val cache = NotesCache()

    suspend fun loadNotes(): List<NoteSummary> {
        // Check cache
        if (cache.initialized) {
            Timber.d("Cache already initialized")
            return cache.summaries
        }

        // Fetch from network
        val response = service.getNotesAsync().await()
        with(response.notes) {
            cache.summaries = this
            cache.initialized = true
            return this
        }
    }
}