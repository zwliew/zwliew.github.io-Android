package io.github.zwliew.zwliew.destinations.projects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor

class ProjectsViewModel : ViewModel() {
    // Coroutines
    // Ensure that only 1 request is buffered while another is being processed
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val actor = viewModelScope.actor<Unit>(capacity = Channel.CONFLATED) {
        for (event in channel) {
            refreshing.value = true
            ProjectsRepository.load()
            refreshing.value = false
        }
    }

    // Observable state
    val projects = Transformations.map(ProjectsRepository.data) {
        it.projects
    }
    val refreshing = MutableLiveData<Boolean>()

    fun handleRefresh() = actor.offer(Unit)
}