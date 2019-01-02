package io.github.zwliew.zwliew.routes.notes

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.zwliew.zwliew.util.BASE_URL
import io.github.zwliew.zwliew.util.launchUrl
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor

class NotesViewModel : ViewModel() {
    // Coroutines
    private val scope = CoroutineScope(Dispatchers.Main)
    // Ensure that only 1 refresh request gets processed and that subsequent requests get discarded
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val actor = scope.actor<Unit>(capacity = Channel.CONFLATED) {
        for (event in channel) {
            refreshing.value = true
            notes.value = NotesRepository.loadNotes().value
            refreshing.value = false
        }
    }

    // Observable data
    val notes = MutableLiveData<List<NoteSummary>>()
    val refreshing = MutableLiveData<Boolean>()

    @UseExperimental(ExperimentalCoroutinesApi::class)
    override fun onCleared() {
        super.onCleared()

        scope.cancel()
    }

    fun handleRefresh() = actor.offer(Unit)

    fun handleNoteClicked(view: View, slug: String) {
        // TODO: Parse markdown and display in app
        launchUrl(view, view.context, "$BASE_URL/notes/$slug")
    }
}