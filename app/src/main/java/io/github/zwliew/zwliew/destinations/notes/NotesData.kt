package io.github.zwliew.zwliew.destinations.notes

// Retrofit API data
data class NotesAPI(val notes: List<Note>)

// Internal data
data class NotesData(
    var notes: List<Note>
)

data class NoteDetail(val text: String)

data class Note(
    val slug: String,
    val title: String,
    val summary: String
)