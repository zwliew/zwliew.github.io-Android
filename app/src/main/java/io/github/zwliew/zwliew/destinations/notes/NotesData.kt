package io.github.zwliew.zwliew.destinations.notes

import io.github.zwliew.zwliew.Empty
import io.github.zwliew.zwliew.Event
import io.github.zwliew.zwliew.RepositoryStatus

// Retrofit API data
data class NotesApi(val notes: List<Note>)

// Internal data
data class NotesState(
    val status: Event<RepositoryStatus> = Event(Empty),
    val notes: List<Note> = listOf()
)

data class NoteDetail(val text: String)

data class Note(
    val slug: String,
    val title: String,
    val summary: String
)