package io.github.zwliew.zwliew.destinations.about

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach

class AboutViewModel : ViewModel() {
    // Ensure that only 1 request is buffered while another is being processed
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val actor = viewModelScope.actor<Unit>(capacity = Channel.CONFLATED) {
        consumeEach {
            AboutRepository.load()
        }
    }

    // Observable state
    val educations = Transformations.map(AboutRepository.data) {
        it.educations
    }
    val activities = Transformations.map(AboutRepository.data) {
        it.activities
    }
    val achievements = Transformations.map(AboutRepository.data) {
        it.achievements
    }
    val status = Transformations.map(AboutRepository.data) {
        it.status
    }

    fun handleRefresh() = actor.offer(Unit)
}