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
            AboutRepository.loadAbout().let {
                educations.value = it.education
                activities.value = it.activities
                achievements.value = it.achievements
            }
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