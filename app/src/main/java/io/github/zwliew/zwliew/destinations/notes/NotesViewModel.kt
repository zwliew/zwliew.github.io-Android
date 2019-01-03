package io.github.zwliew.zwliew.destinations.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.withContext

class NotesViewModel : ViewModel() {
    // Ensure that only 1 request is buffered while another is being processed
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val actor = viewModelScope.actor<Unit>(capacity = Channel.CONFLATED) {
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
}