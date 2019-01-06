package io.github.zwliew.zwliew.destinations.projects

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach

class ProjectsViewModel : ViewModel() {
    // Ensure that only 1 request is buffered while another is being processed
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val actor = viewModelScope.actor<Unit>(capacity = Channel.CONFLATED) {
        consumeEach {
            ProjectsRepository.load()
        }
    }

    // Observable state
    val projects = Transformations.map(ProjectsRepository.data) {
        it.projects
    }
    val status = Transformations.map(ProjectsRepository.data) {
        it.status
    }

    fun handleRefresh() = actor.offer(Unit)
}