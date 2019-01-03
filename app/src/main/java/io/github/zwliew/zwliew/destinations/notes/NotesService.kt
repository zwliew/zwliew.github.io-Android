package io.github.zwliew.zwliew.destinations.notes

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface NotesService {
    @GET("res/data/notes.json")
    fun getNotesAsync(): Deferred<NoteList>

    @GET("res/data/notes/{slug}.md")
    fun getNote(@Path("slug") slug: String): Deferred<NoteDetail>
}