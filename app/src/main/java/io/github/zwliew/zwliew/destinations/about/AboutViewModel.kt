package io.github.zwliew.zwliew.destinations.about

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor

class AboutViewModel : ViewModel() {
    // Ensure that only 1 request is buffered while another is being processed
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val actor = viewModelScope.actor<Unit>(capacity = Channel.CONFLATED) {
        for (event in channel) {
            educations.value = AboutRepository.loadEducations()
            activities.value = AboutRepository.loadActivities()
            achievements.value = AboutRepository.loadAchievements()
        }
    }

    // Observable state
    val educations = MutableLiveData<List<Education>>()
    val activities = MutableLiveData<List<Activity>>()
    val achievements = MutableLiveData<List<Achievement>>()

    init {
        // Populate initial data
        handleRefresh()
    }

    private fun handleRefresh() = actor.offer(Unit)
}