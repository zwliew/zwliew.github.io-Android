package io.github.zwliew.zwliew.routes.notes

import android.view.View
import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.ScopedViewModel
import io.github.zwliew.zwliew.util.BASE_URL
import io.github.zwliew.zwliew.util.launchUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.withContext

class NotesViewModel : ScopedViewModel() {
    // Ensure that only 1 refresh request gets processed and that subsequent requests get discarded
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val actor = scope.actor<Unit>(capacity = Channel.CONFLATED) {
        for (event in channel) {
            refreshing.value = true
            notes.value = withContext(Dispatchers.IO) {
                NotesRepository.loadNotes()
            }
            refreshing.value = false
        }
    }

    // Observable data
    val notes = MutableLiveData<List<NoteSummary>>()
    val refreshing = MutableLiveData<Boolean>()

    init {
        // Populate initial data
        handleRefresh()
    }

    fun handleRefresh() = actor.offer(Unit)

    fun handleNoteClicked(view: View, slug: String) {
        // TODO: Parse markdown and display in app
        launchUrl(view, view.context, "$BASE_URL/notes/$slug")
    }
}