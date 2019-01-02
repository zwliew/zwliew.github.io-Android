package io.github.zwliew.zwliew.routes.projects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor

class ProjectsViewModel : ViewModel() {
    // Coroutines
    private val scope = CoroutineScope(Dispatchers.Main)
    // Ensure that only 1 refresh request gets processed and that subsequent requests get discarded
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val actor = scope.actor<Unit>(capacity = Channel.CONFLATED) {
        for (event in channel) {
            refreshing.value = true
            projects.value = ProjectsRepository.loadProjects().value
            refreshing.value = false
        }
    }

    // Observable data
    val projects = MutableLiveData<List<Project>>()
    val refreshing = MutableLiveData<Boolean>()

    @UseExperimental(ExperimentalCoroutinesApi::class)
    override fun onCleared() {
        super.onCleared()

        scope.cancel()
    }

    fun handleRefresh() = actor.offer(Unit)
}