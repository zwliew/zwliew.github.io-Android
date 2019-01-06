package io.github.zwliew.zwliew.destinations.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor

class NotesViewModel : ViewModel() {
    // Ensure that only 1 request is buffered while another is being processed
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val actor = viewModelScope.actor<Unit>(capacity = Channel.CONFLATED) {
        for (event in channel) {
            refreshing.value = true
            NotesRepository.load()
            refreshing.value = false
        }
    }

    // Observable state
    val notes = Transformations.map(NotesRepository.data) {
        it.notes
    }
    val refreshing = MutableLiveData<Boolean>()

    fun handleRefresh() = actor.offer(Unit)
}