package io.github.zwliew.zwliew.destinations.notes

data class NoteDetail(val text: String)

data class NoteList(val notes: List<NoteSummary>)

data class NoteSummary(
    val slug: String,
    val title: String,
    val summary: String
)

data class NotesCache(
    var initialized: Boolean = false,
    var summaries: List<NoteSummary> = listOf(),
    val details: Map<String, String> = mapOf()
)