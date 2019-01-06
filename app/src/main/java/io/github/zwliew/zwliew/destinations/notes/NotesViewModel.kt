package io.github.zwliew.zwliew.destinations.notes

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach

class NotesViewModel : ViewModel() {
    // Ensure that only 1 request is buffered while another is being processed
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val actor = viewModelScope.actor<Unit>(capacity = Channel.CONFLATED) {
        consumeEach {
            NotesRepository.load()
        }
    }

    // Observable state
    val notes = Transformations.map(NotesRepository.data) {
        it.notes
    }
    val status = Transformations.map(NotesRepository.data) {
        it.status
    }

    fun handleRefresh() = actor.offer(Unit)
}